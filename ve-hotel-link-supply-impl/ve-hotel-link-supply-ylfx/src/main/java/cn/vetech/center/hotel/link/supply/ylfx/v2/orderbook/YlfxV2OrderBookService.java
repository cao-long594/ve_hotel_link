package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook;

import cn.vetech.center.hotel.link.api.orderbook.dto.BookCustomer;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/** 易旅分销 V2 创建订单服务。 */
@Service
public class YlfxV2OrderBookService {
    private static final String URI = "/open/booking/create";
    @Autowired private YlfxV2UtilsService utilsService;
    public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        try {
            Request request = convertRequest(dto, config);
            String responseBody = utilsService.sendPost(request, config, URI);
            Response response = JacksonUtils.parseNonEmpty(responseBody, Response.class);
            if (response == null || !"200".equals(response.getCode()) || response.getData() == null || StringUtils.isBlank(response.getData().getOrderId())) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, response == null ? "响应结果为空" : response.getMessage());
            }
            LinkHotelOrderBookVO vo = new LinkHotelOrderBookVO();
            vo.setOrderId(response.getData().getOrderId());
            vo.setTrueStatus(response.getData().getOrderStatus());
            vo.setGysxdbj(buildGysxdbj(dto));
            return OrderBookApiRes.success(vo);
        } catch (Exception e) { return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001, "接口异常"); }
    }
    private Request convertRequest(LinkHotelOrderBookDTO dto, YlfxConfig config) {
        Request request = new Request();
        request.customerCode = config.getCustomerCode();
        request.cusOrderNo = dto.getLocalOrderId();
        request.hotelCode = dto.getHotelId();
        request.roomCode = dto.getRoomId();
        request.rateCode = dto.getRatePlanId();
        request.checkIn = dto.getCheckInDate();
        request.checkOut = dto.getCheckOutDate();
        request.roomCount = dto.getNumberOfRooms();
        request.totalPrice = dto.getTotalPrice();
        request.currencyCode = dto.getCurrencyCode();
        request.remark = dto.getNoteToHotel();
        request.dailyPriceList = convertDailyPriceList(dto);
        convertContact(dto, request);
        request.paxNameRooms = convertPaxNameRooms(dto);
        return request;
    }
    private void convertContact(LinkHotelOrderBookDTO dto, Request request) {
        if (dto.getContact() == null) {
            return;
        }
        String[] name = splitName(dto.getContact().getName());
        request.contactLastName = name[0];
        request.contactFirstName = name[1];
    }
    private List<PaxRoom> convertPaxNameRooms(LinkHotelOrderBookDTO dto) {
        List<PaxRoom> rooms = new ArrayList<>();
        if (CollectionUtils.isEmpty(dto.getOrderRooms())) {
            return rooms;
        }
        for (int i = 0; i < dto.getOrderRooms().size(); i++) {
            BookOrderRoom orderRoom = dto.getOrderRooms().get(i);
            PaxRoom room = new PaxRoom();
            room.roomIndex = i + 1;
            room.paxNames = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(orderRoom.getCustomers())) {
                for (BookCustomer customer : orderRoom.getCustomers()) {
                    Pax pax = new Pax();
                    String[] name = splitName(customer.getName());
                    pax.lastName = name[0];
                    pax.firstName = name[1];
                    pax.type = "Adults";
                    room.paxNames.add(pax);
                }
            }
            rooms.add(room);
        }
        return rooms;
    }
    private List<DailyPrice> convertDailyPriceList(LinkHotelOrderBookDTO dto) {
        List<DailyPrice> dailyPriceList = new ArrayList<>();
        if (CollectionUtils.isEmpty(dto.getNightlyRates())) {
            return dailyPriceList;
        }
        for (cn.vetech.center.hotel.link.api.orderbook.dto.BookNightlyRate nightlyRate : dto.getNightlyRates()) {
            DailyPrice dailyPrice = new DailyPrice();
            dailyPrice.date = nightlyRate.getDate();
            dailyPrice.price = nightlyRate.getPriceAfterTax();
            dailyPriceList.add(dailyPrice);
        }
        return dailyPriceList;
    }
    private String buildGysxdbj(LinkHotelOrderBookDTO dto) {
        YlfxGysxdbj mark = new YlfxGysxdbj();
        mark.setHotelId(dto.getHotelId());
        mark.setRoomCode(dto.getRoomId());
        return JacksonUtils.toJsonWithNonEmpty(mark);
    }
    private String[] splitName(String name) { if (StringUtils.isBlank(name)) return new String[]{"", ""}; int space=name.indexOf(' '); return space>0 ? new String[]{name.substring(0,space),name.substring(space+1)} : new String[]{name,""}; }
    public static class Request { public String customerCode,cusOrderNo,hotelCode,roomCode,rateCode,checkIn,checkOut,roomCount,totalPrice,currencyCode,contactLastName,contactFirstName,remark; public List<PaxRoom> paxNameRooms; public List<DailyPrice> dailyPriceList; }
    public static class DailyPrice { public String date,price; }
    public static class PaxRoom { public Integer roomIndex; public List<Pax> paxNames; }
    public static class Pax { public String lastName,firstName,type; }
    public static class Response { private String code,message; private Data data; public String getCode(){return code;} public void setCode(String v){code=v;} public String getMessage(){return message;} public void setMessage(String v){message=v;} public Data getData(){return data;} public void setData(Data v){data=v;} }
    public static class Data { private String orderId,orderStatus; public String getOrderId(){return orderId;} public void setOrderId(String v){orderId=v;} public String getOrderStatus(){return orderStatus;} public void setOrderStatus(String v){orderStatus=v;} }
}
