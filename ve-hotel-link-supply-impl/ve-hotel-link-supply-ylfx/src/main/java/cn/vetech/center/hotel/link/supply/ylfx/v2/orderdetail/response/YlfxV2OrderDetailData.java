package cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.response;

/**
 * 易旅分销 V2 查询订单数据
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderDetailData {
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
