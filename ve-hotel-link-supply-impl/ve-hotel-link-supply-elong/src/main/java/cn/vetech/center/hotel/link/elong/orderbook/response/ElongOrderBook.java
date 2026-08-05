package cn.vetech.center.hotel.link.elong.orderbook.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class ElongOrderBook implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 最晚取消时间
     * 如果日期为9999-12-30 23:00:00等条件代表不限制取消时间，
     * 不限制取消时间的订单，最晚取消时间为最早到店时间之前可以取消
     */
    @JsonProperty("CancelTime")
    private String cancelTime;
    /**
     * 担保金额
     * 如果此订单是担保订单，则在此列出担保金额，
     * 币种是人民币(如果提交订单时候的是港币，这里也会被换算成对应金额的人民币)
     */
    @JsonProperty("GuaranteeAmount")
    private String guaranteeAmount;
    /**
     * 货币类型
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 是否是即时确认
     * 采用这个属性，就不需要再请求hotel.order.instant接口了。
     * 即时确认只说明这个库存确认方式，最终能否确认给客人还需要考虑订单是否为担保订单(担保订单需要担保成功后才能确认)
     */
    @JsonProperty("IsInstantConfirm")
    private String isInstantConfirm;
    /**
     * 支付最后期限
     * 如果担保预付订单，提交的信用卡因某种原因支付失败，系统可以保留一段时间，继续支付
     * 如果这个时间点还没有成功支付，系统将自动取消订单。 继续支付请使用 hotel.order.pay接口
     */
    @JsonProperty("PaymentDeadlineTime")
    private String paymentDeadlineTime;
    /**
     * 支付错误信息
     */
    @JsonProperty("PaymentMessage")
    private String paymentMessage;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getCurrencyCode() {
        return currencyCo