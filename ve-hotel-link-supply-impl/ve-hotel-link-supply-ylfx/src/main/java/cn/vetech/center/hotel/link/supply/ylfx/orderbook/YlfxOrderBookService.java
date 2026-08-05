package cn.vetech.center.hotel.link.supply.ylfx.orderbook;

import cn.vetech.center.hotel.link.api.orderbook.dto.BookContact;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.orderbook.request.CheckinPerson;
import cn.vetech.center.hotel.link.supply.ylfx.orderbook.request.YlfxOrderBookRequest;
import cn.vetech.center.hotel.link.supply.ylfx.orderbook.response.YlfxOrderBookData;
import cn.vetech.center.hotel.link.supply.ylfx.orderbook.response.YlfxOrderBookResponse;
import cn.vetech.center.hotel.link.supply.ylfx.validate.request.DailyPrice;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxOrderBookService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxOrderBookService.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 下单
     *
     * @param dto    dto
     * @param config config
     * @return LinkHotelOrderBookVO
     */

public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        try {
            YlfxOrderBookRequest request = convertBookReq(dto, config);
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.BOOK);
            YlfxOrderBookResponse response = JacksonUtils.parseNonEmpty(res, YlfxOrderBookResponse.class);
            if (Objects.isNull(response)) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, "response为空");
            }
            if (!StringUtils.equals("200", response.getCode())) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, response.getMessage());
            }
            YlfxOrderBookData data = response.getData();
            if (Objects.isNull(data) || StringUtils.isBlank(data.getOrderId())) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, "订单数据为空");
            }
            LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
            vo.setOrderId(data.getOrderId());
            return OrderBookApiRes.success(vo);
        } catch (Exception e) {
            logger.warn("接口异常【{}】", e.getMessage(), e);
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, "接口异常");
        }
    }
    /**
     * 下单参数
     *
     * @param dto    dto
     * @param config config
     * @return YlfxOrderBookRequest
     */
    private YlfxOrderBookRequest convertBookReq(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        YlfxOrderBookRequest request = new YlfxOrderBookRequest();
        request.setCusOrderId(dto.getLocalOrderId());
        request.setHotelId(dto.getHotelId());
        request.setProductId(dto.getRatePlanId());
        request.setRoomCount(NumberUtils.toInt(dto.getNumberOfRooms()));
        request.setHotelId(dto.getHotelId());
        request.setProductId(dto.getRatePlanId());
        request.setCheckinDate(VeDateUtils.dateStrToDateNum(dto.getCheckInDate()));
        request.setCheckoutDate(VeDateUtils.dateStrToDateNum(dto.getCheckOutDate()));
        request.setRoomCount(NumberUtils.toInt(dto.getNumberOfRooms()));
        request.setTotalPrice(dto.getTotalPrice());
        List<DailyPrice> dailyList = dto.getNightlyRates().stream().map(bookNightlyRate -> {
            DailyPrice dailyPrice = new DailyPrice();
            dailyPrice.setNight(VeDateUtils.dateStrToDateNum(bookNightlyRate.getDate()));
            dailyPrice.setPrice(bookNightlyRate.getPriceAfterTax());
            return dailyPrice;
            }).collect(Collectors.toList());
        request.setDailyList(dailyList);
        YlfxGysxdbj gysxdbj = JacksonUtils.parseNonEmpty(dto.getGysxdbj(), YlfxGysxdbj.class);
        request.setInvoiceMode(gysxdbj.getInvoiceMode());
        if (CommonMagicNumber.INT1.equals(gysxdbj.getInvoiceMode())) {
            request.setDailySellList(dailyList);
            request.setTotalSellPrice(dto.getTotalPrice());
        }
        request.setRemark(dto.getNoteToHotel());
        BookContact bookContact = OrderBookCommonUtils.convertBookContact(config.getLxrxm(), config.getLxrdh(), config.getLxryx(), dto.getContact(), dto.getOrderRooms());
        request.setContactName(bookContact.getName());
        request.setContactPhone(bookContact.getPhone());
        if (CollectionUtils.isEmpty(dto.getOrderRooms())) {
            return request;
        }
        List<BookOrderRoom> orderRooms = dto.getOrderRooms();
        BookOrderRoom bookOrderRoom = orderRooms.get(0);
        if (CollectionUtils.isEmpty(bookOrderRoom.getCustomers())) {
            return request;
        }
        List<CheckinPerson> checkinPersons = bookOrderRoom.getCustomers().stream().map(customer -> {
            CheckinPerson checkinPerson = new CheckinPerson();
            checkinPerson.setName(customer.getName());
            checkinPerson.setMobile(customer.getMobile());
            return checkinPerson;
        }).collect(Collectors.toList());
        request.setCheckinPersons(checkinPersons);
        return request;
    }
}