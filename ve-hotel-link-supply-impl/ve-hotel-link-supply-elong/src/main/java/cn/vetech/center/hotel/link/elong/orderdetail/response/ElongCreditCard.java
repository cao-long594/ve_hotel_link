package cn.vetech.center.hotel.link.elong.orderdetail.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2021/6/10 17:57
 */
public class ElongCreditCard {

    /***
     * Auth—授权 CancelAuth-取消授权 Charge-授权后扣款 Refund-退款 DirectCharge-直接扣款
     */
    @JsonProperty("ProcessType")
    private String processType;
    /***
     * UnProcess-未处理 Succeed-成功 Processing-处理中 Fail-失败
     */
    @JsonProperty("Status")
    private String status;
    /***
     * v1.07新增，注意预付订单在ProcessType为Refund时，此处金额不一定是实际退款金额，还需要结合RefundAmount字段查看，具体逻辑查看：http://open.elong.com/faq/detail?plt=2&id=122。
     */
    @JsonProperty("Amount")
    private String amount;
    /***
     *  v1.19新增(仅订单创建的是使用新支付流程)
     */
    @JsonProperty("Notes")
    private String notes;
    /***
     * v1.19新增(仅订单创建的是使用新支付流程)。如果可以继续支付，请使用hotel.order.pay 继续完成支付
     */
    @JsonProperty("IsPayable")
    private String isPayable;
    /***
     * v1.19新增(仅订单创建的是使用新支付流程)。过了最晚支付时间订单将自动取消
     */
    @JsonProperty("LatestPayTime")
    private String latestPayTime;
    /***
     * 已弃用，仅有默认值0
     */
    @JsonProperty("ExpirationYear")
    private String expirationYear;
    /***
     * 已弃用，仅有默认值0
     */
    @JsonProperty("ExpirationMonth")
    private String expirationMonth;
    /***
     * 已弃用，仅有默认值IdentifyCard
     */
    @JsonProperty("IdType")
    private String idType;

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public 