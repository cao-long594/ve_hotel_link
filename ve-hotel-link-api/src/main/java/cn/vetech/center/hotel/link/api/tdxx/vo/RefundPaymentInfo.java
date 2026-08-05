package cn.vetech.center.hotel.link.api.tdxx.vo;

/**
 * @author chengwanshan
 * @since 2024/1/17 13:51
 */
public class RefundPaymentInfo {
    /**
     * 退款途径：
     * CorpAccount-退公司账户；
     * Personal-退个人账户
     */
    private String refundChannel;
    /**
     * 退款时间
     */
    private String refundTime;
    /**
     * 退款金额
     */
    private String amount;
    /**
     * 退款 billNo
     */
    private String billNo;

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}
