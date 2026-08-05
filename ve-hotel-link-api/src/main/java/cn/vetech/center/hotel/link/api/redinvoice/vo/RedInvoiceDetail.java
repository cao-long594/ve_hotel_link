package cn.vetech.center.hotel.link.api.redinvoice.vo;

/**
 * @author chengwanshan
 * @since 2025/3/25 15:34
 */
public class RedInvoiceDetail {

    /**
     * 供应系统红字发票申请单号,返回 ID 代表请求申请收到，具体发票开具状态看发票状态
     */
    private String fpsgid;
    /**
     * 发票申请单号企业差旅，请求方发票申请的 ID
     */
    private String cgfpid;
    /**
     * 对应蓝字发票号码
     */
    private String originalInvoiceNo;
    /**
     * 红字发票状态:0申请中，1 已开票， 3 取消。 默认0
     */
    private String redInvoiceState;

    public String getFpsgid() {
        return fpsgid;
    }

    public void setFpsgid(String fpsgid) {
        this.fpsgid = fpsgid;
    }

    public String getCgfpid() {
        return cgfpid;
    }

    public void setCgfpid(String cgfpid) {
        this.cgfpid = cgfpid;
    }

    public String getOriginalInvoiceNo() {
        return originalInvoiceNo;
    }

    public void setOriginalInvoiceNo(String originalInvoiceNo) {
        this.originalInvoiceNo = originalInvoiceNo;
    }

    public String getRedInvoiceState() {
        return redInvoiceState;
    }

    public void setRedInvoiceState(String redInvoiceState) {
        this.redInvoiceState = redInvoiceState;
    }
}
