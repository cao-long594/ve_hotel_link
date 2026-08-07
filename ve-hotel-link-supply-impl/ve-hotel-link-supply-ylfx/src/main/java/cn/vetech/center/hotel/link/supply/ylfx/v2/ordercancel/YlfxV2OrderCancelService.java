package cn.vetech.center.hotel.link.supply.ylfx.v2.ordercancel;

import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ordercancel.OrderCancelApiRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 易旅分销 V2 取消订单服务。 */
@Service
public class YlfxV2OrderCancelService {
    private static final String URI = "/open/booking/cancel";
    @Autowired private YlfxV2UtilsService utilsService;
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto, YlfxConfig config) {
        try {
            Request request = new Request(config.getCustomerCode(), dto.getLocalOrderId());
            String responseBody = utilsService.sendPost(request, config, URI);
            Response response = JacksonUtils.parseNonEmpty(responseBody, Response.class);
            if (response == null || !"200".equals(response.getCode()) || response.getData() == null) {
                return OrderCancelApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            String status = response.getData().getOrderStatus();
            if ("CANCELLED".equals(status)) {
                return OrderCancelApiRes.success(new LinkHotelOrderCancelVO());
            }
            if ("CONFIRM_PENDING".equals(status) || "CONFIRMED".equals(status)) {
                return OrderCancelApiRes.failCanceling("订单正在取消中");
            }
            return OrderCancelApiRes.fail(response.getMessage());
        } catch (Exception e) {
            return OrderCancelApiRes.fail("接口异常");
        }
    }
    public static class Request { private String customerCode,cusOrderNo; public Request(){} public Request(String c,String o){customerCode=c;cusOrderNo=o;} public String getCustomerCode(){return customerCode;} public void setCustomerCode(String v){customerCode=v;} public String getCusOrderNo(){return cusOrderNo;} public void setCusOrderNo(String v){cusOrderNo=v;} }
    public static class Response { private String code,message; private Data data; public String getCode(){return code;} public void setCode(String v){code=v;} public String getMessage(){return message;} public void setMessage(String v){message=v;} public Data getData(){return data;} public void setData(Data v){data=v;} }
    public static class Data { private String orderStatus; public String getOrderStatus(){return orderStatus;} public void setOrderStatus(String v){orderStatus=v;} }
}
