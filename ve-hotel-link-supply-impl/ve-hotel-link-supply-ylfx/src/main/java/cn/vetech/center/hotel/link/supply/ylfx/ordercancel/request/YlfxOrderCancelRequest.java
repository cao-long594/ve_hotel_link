package cn.vetech.center.hotel.link.supply.ylfx.ordercancel.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderCancelRequest extends YlfxBaseRequest {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 渠道端订单ID
     */
    private String cusOrderId;
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 取消原因
     */
    private String reason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
