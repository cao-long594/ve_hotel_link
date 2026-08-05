package cn.vetech.center.hotel.link.elong.orderdetail.response;

import java.util.Date;

/**
 * @author xiaotengyu
 * @since 2022-07-27 18:02
 */
public class ElongRefund {

    /**
     * 每笔退款金额
     */
    private Double refundAmount;

    /**
     * 每笔退款的时间
     */
    private String refundTime;

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }
}
