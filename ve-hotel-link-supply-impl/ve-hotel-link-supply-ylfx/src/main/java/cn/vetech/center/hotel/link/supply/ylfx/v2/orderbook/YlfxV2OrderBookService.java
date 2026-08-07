package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook;

import cn.vetech.center.hotel.link.api.orderbook.dto.BookCustomer;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookNightlyRate;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.enums.YlfxV2MethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request.YlfxV2OrderBookDailyPrice;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request.YlfxV2OrderBookPax;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request.YlfxV2OrderBookPaxRoom;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request.YlfxV2OrderBookRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.response.YlfxV2OrderBookResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 易旅分销 V2 创建订单服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2OrderBookService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2OrderBookService.class);
    /**
     * V2 公共通信服务
     */
    @Autowired
    private YlfxV2UtilsService utilsService;

    /**
     * 创建订单
     *
     * @param dto 标准下单请求
     * @param config 易旅分销配置
     * @return 标准下单结果
     */
    public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        try {
            YlfxV2OrderBookRequest request = convertRequest(dto, config);
            String responseBody = utilsService.sendPost(request, config, YlfxV2MethodEnum.BOOK);
            YlfxV2OrderBookResponse response = JacksonUtils.parseNonEmpty(responseBody, YlfxV2OrderBookResponse.class);
            if (response == null || !StringUtils.equals("200", response.getCode())
                    || response.getData() == null || StringUtils.isBlank(response.getData().getOrderId())) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001,
                        response == null ? "响应结果为空" : response.getMessage());
            }
            LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
            vo.setOrderId(response.getData().getOrderId());
            vo.setTrueStatus(response.getData().getOrderStatus());
            vo.setGysxdbj(buildGysxdbj(dto));
            return OrderBookApiRes.success(vo);
        } catch (Exception e) {
            LOGGER.warn("易旅分销 V2 创建订单接口异常【{}】", e.getMessage(), e);
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, "接口异常");
        }
    }

    /**
     * 转换 V2 下单请求
     *
     * @param dto 标准下单请求
     * @param config 易旅分销配置
     * @return V2 下单请求
     */
    private YlfxV2OrderBookRequest convertRequest(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        YlfxV2OrderBookRequest request = new YlfxV2OrderBookRequest();
        request.setCustomerCode(config.getCustomerCode());
        request.setCusOrderNo(dto.getLocalOrderId());
        request.setHotelCode(dto.getHotelId());
        request.setRoomCode(dto.getRoomId());
        request.setRateCode(dto.getRatePlanId());
        request.setCheckIn(dto.getCheckInDate());
        request.setCheckOut(dto.getCheckOutDate());
        request.setRoomCount(dto.getNumberOfRooms());
        request.setTotalPrice(dto.getTotalPrice());
        request.setCurrencyCode(dto.getCurrencyCode());
        request.setRemark(dto.getNoteToHotel());
        request.setDailyPriceList(convertDailyPriceList(dto));
        convertContact(dto, request);
        request.setPaxNameRooms(convertPaxNameRooms(dto));
        return request;
    }

    /**
     * 转换联系人姓名
     *
     * @param dto 标准下单请求
     * @param request V2 下单请求
     */
    private void convertContact(LinkHotelOrderBookDTO dto, YlfxV2OrderBookRequest request) {
        if (dto.getContact() == null) {
            return;
        }
        String[] name = splitName(dto.getContact().getName());
        request.setContactLastName(name[0]);
        request.setContactFirstName(name[1]);
    }

    /**
     * 转换入住人房间信息
     *
     * @param dto 标准下单请求
     * @return V2 入住人房间列表
     */
    private List<YlfxV2OrderBookPaxRoom> convertPaxNameRooms(LinkHotelOrderBookDTO dto) {
        List<YlfxV2OrderBookPaxRoom> rooms = new ArrayList<>();
        if (CollectionUtils.isEmpty(dto.getOrderRooms())) {
            return rooms;
        }
        for (int i = 0; i < dto.getOrderRooms().size(); i++) {
            BookOrderRoom orderRoom = dto.getOrderRooms().get(i);
            YlfxV2OrderBookPaxRoom room = new YlfxV2OrderBookPaxRoom();
            room.setRoomIndex(i + 1);
            room.setPaxNames(new ArrayList<>());
            if (CollectionUtils.isNotEmpty(orderRoom.getCustomers())) {
                for (BookCustomer customer : orderRoom.getCustomers()) {
                    YlfxV2OrderBookPax pax = new YlfxV2OrderBookPax();
                    String[] name = splitName(customer.getName());
                    pax.setLastName(name[0]);
                    pax.setFirstName(name[1]);
                    pax.setType("Adults");
                    room.getPaxNames().add(pax);
                }
            }
            rooms.add(room);
        }
        return rooms;
    }

    /**
     * 转换每日价格列表
     *
     * @param dto 标准下单请求
     * @return V2 每日价格列表
     */
    private List<YlfxV2OrderBookDailyPrice> convertDailyPriceList(LinkHotelOrderBookDTO dto) {
        List<YlfxV2OrderBookDailyPrice> dailyPriceList = new ArrayList<>();
        if (CollectionUtils.isEmpty(dto.getNightlyRates())) {
            return dailyPriceList;
        }
        for (BookNightlyRate nightlyRate : dto.getNightlyRates()) {
            YlfxV2OrderBookDailyPrice dailyPrice = new YlfxV2OrderBookDailyPrice();
            dailyPrice.setDate(nightlyRate.getDate());
            dailyPrice.setPrice(nightlyRate.getPriceAfterTax());
            dailyPriceList.add(dailyPrice);
        }
        return dailyPriceList;
    }

    /**
     * 构造供应商扩展标记
     *
     * @param dto 标准下单请求
     * @return 供应商扩展标记
     */
    private String buildGysxdbj(LinkHotelOrderBookDTO dto) {
        YlfxGysxdbj mark = new YlfxGysxdbj();
        mark.setHotelId(dto.getHotelId());
        return JacksonUtils.toJsonWithNonEmpty(mark);
    }

    /**
     * 拆分姓名
     *
     * @param name 姓名
     * @return 姓和名
     */
    private String[] splitName(String name) {
        if (StringUtils.isBlank(name)) {
            return new String[]{"", ""};
        }
        int space = name.indexOf(' ');
        return space > 0 ? new String[]{name.substring(0, space), name.substring(space + 1)} : new String[]{name, ""};
    }
}
