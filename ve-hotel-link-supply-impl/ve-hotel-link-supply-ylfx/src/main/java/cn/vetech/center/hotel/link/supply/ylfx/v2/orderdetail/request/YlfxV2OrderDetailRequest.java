package cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.request;

/**
 * 易旅分销 V2 查询订单请求
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderDetailRequest {
    /**
     * 客户编码
     */
    private String customerCode;
    /**
     * 渠道端订单号
     */
    private String cusOrderNo;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCusOrderNo() {
        return cusOrderNo;
    }

    public void setCusOrderNo(String cusOrderNo) {
        this.cusOrderNo = cusOrderNo;
    }
}
