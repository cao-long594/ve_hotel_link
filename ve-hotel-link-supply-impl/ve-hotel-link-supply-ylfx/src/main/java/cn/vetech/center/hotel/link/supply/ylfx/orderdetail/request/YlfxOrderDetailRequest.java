package cn.vetech.center.hotel.link.supply.ylfx.orderdetail.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderDetailRequest extends YlfxBaseRequest {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 渠道端订单ID
     */
    private String cusOrderId;

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
}
