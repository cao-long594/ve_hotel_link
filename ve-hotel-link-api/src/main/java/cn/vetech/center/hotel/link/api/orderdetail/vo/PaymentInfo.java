package cn.vetech.center.hotel.link.api.orderdetail.vo;

import java.math.BigDecimal;

/**
 * @author pengyefei
 * @version 1.0
 * @since 2022/11/29 14:59
 */
public class PaymentInfo {
    /**
     * 支付账号
     */
    private String account;
    /**
     * 平台流水号
     */
    private String transactionID;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 类型：
     * 1：付款
     * 2：退款
     */
    private String payMode;
    /**
     * 支付方式
     * 10:VCC支付
     */
    private String payType;
    /**
     * 交易时间
     * 付款的就是付款时间，退款的就是退款时间
     */
    private String payTime;

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
}
