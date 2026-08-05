package cn.vetech.center.hotel.link.api.orderbook.dto;

import java.math.BigDecimal;

/**
 * @author chengwanshan
 * @since 2023/10/19 17:37
 */
public class MixPayInfo {
    /**
     * 1 公账支付，5 个人支付（2，3，4，5默认都是个人支付，这里不影响后面个付支付渠道得选择）
     */
    private String payWay;
    /**
     * 是 对应金额
     */
    private BigDecimal payAmount;

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
