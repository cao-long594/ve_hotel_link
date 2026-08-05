package cn.vetech.center.hotel.link.elong.orderdetail.response;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2022-07-27 18:01
 */
public class ElongRefundDetail {

    /**
     * 总的退款金额
     */
    private Double refundAmount;
    /**
     * 每笔退款明细
     */
    private List<ElongRefund> refundDetails;

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public List<ElongRefund> getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(List<ElongRefund> refundDetails) {
        this.refundDetails = refundDetails;
    }
}
