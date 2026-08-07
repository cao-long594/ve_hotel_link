package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.response;

/**
 * 易旅分销 V2 创建订单数据
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderBookData {
    /**
     * 供应商订单号
     */
    private String orderId;
    /**
     * 订单状态
     */
    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
