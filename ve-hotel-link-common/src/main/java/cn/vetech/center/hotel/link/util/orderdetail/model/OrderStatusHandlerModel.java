package cn.vetech.center.hotel.link.util.orderdetail.model;

/**
 * @author chengwanshan
 * @since 2021/7/9 11:00
 */
public class OrderStatusHandlerModel {
    /**
     * 必传   平台    cps:cps   费控：charge   asms：asms    差旅：cloud
     */
    private String platform;
    /**
     * 必传   支付方式  0：现付   1：预付
     */
    private String payment;
    /**
     * 必传   本地订单状态
     */
    private String ptOrderStatus;
    /**
     * 非必传   预付订单，NOSHOW情况下，是否收费     0或空：收费    1：不收费
     */
    private String isChargeForNowshow;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPtOrderStatus() {
        return ptOrderStatus;
    }

    public void setPtOrderStatus(String ptOrderStatus) {
        this.ptOrderStatus = ptOrderStatus;
    }

    public String getIsChargeForNowshow() {
        return isChargeForNowshow;
    }

    public void setIsChargeForNowshow(String isChargeForNowshow) {
        this.isChargeForNowshow = isChargeForNowshow;
    }
}
