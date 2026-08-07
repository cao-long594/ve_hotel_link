package cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail;

import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailApiRes;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 易旅分销 V2 查询订单服务。 */
@Service
public class YlfxV2OrderDetailService {
    private static final String URI = "/open/booking/search";
    @Autowired private YlfxV2UtilsService utilsService;
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto, YlfxConfig config) {
        try {
            Request request = new Request(config.getCustomerCode(), dto.getLocalOrderId());
            String responseBody = utilsService.sendPost(request, config, URI);
            Response response = JacksonUtils.parseNonEmpty(responseBody, Response.class);
            if (response == null || !"200".equals(response.getCode()) || response.getData() == null) {
                return OrderDetailApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            String status = response.getData().getOrderStatus();
            if ("NO_ORDER_FOUND".equals(status)) {
                return OrderDetailApiRes.fail(response.getMessage());
            }
            LinkHotelOrderDetailVO vo = convertResponse(response.getData(), dto);
            return OrderDetailApiRes.success(vo);
        } catch (Exception e) {
            return OrderDetailApiRes.fail("接口异常");
        }
    }
    private LinkHotelOrderDetailVO convertResponse(Data data, LinkHotelOrderDetailDTO dto) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setOrderId(data.getOrderId());
        vo.setTrueStatus(data.getOrderStatus());
        vo.setTrueStatusMs(data.getOrderStatus());
        vo.setShowStatus(OrderDetailHandler.getShowStatus(dto.getCpsOrderStatus(), dto.getPayment(), dto.getPt(), convertStatus(data.getOrderStatus())));
        return vo;
    }
    private HotelGysOrderStatusEnum convertStatus(String status) { if("CONFIRMED".equals(status)) return HotelGysOrderStatusEnum.AFTER_CONFIRM; if("CONFIRM_PENDING".equals(status)) return HotelGysOrderStatusEnum.BEFORE_CONFIRM; if("CANCELLED".equals(status)||"REFUSED".equals(status)) return HotelGysOrderStatusEnum.CANCEL; return HotelGysOrderStatusEnum.ERROR; }
    public static class Request { private String customerCode,cusOrderNo; public Request(){} public Request(String c,String o){customerCode=c;cusOrderNo=o;} public String getCustomerCode(){return customerCode;} public void setCustomerCode(String v){customerCode=v;} public String getCusOrderNo(){return cusOrderNo;} public void setCusOrderNo(String v){cusOrderNo=v;} }
    public static class Response { private String code,message; private Data data; public String getCode(){return code;} public void setCode(String v){code=v;} public String getMessage(){return message;} public void setMessage(String v){message=v;} public Data getData(){return data;} public void setData(Data v){data=v;} }
    public static class Data { private String orderId,orderStatus; public String getOrderId(){return orderId;} public void setOrderId(String v){orderId=v;} public String getOrderStatus(){return orderStatus;} public void setOrderStatus(String v){orderStatus=v;} }
}
