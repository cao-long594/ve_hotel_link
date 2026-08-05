package cn.vetech.center.hotel.link.api.redinvoice.dto;

import java.math.BigDecimal;

/**
 * @author chengwanshan
 * @since 2025/3/25 14:18
 */
public class RedInvoiceInfo {
    /**
     * 供应商订单编号
     */
    private String ddbh;
    /**
     * 差旅系统红冲发票申请单号id
     */
    private String id;
    /**
     * 要作废红冲的蓝字发票号码
     */
    private String originalInvoiceNo;
    /**
     * 红字冲销金额，目前系统仅支持全额红冲
     */
    private BigDecimal invoiceTotalPrice;
    /**
     * 红字冲销税额
     */
    private BigDecimal invoiceTotalTax;
    /**
     * 红字发票冲红原因代码 01:开票有误 02:销货退回 03:服务中止 04:销售折让，  默认 01
     */
    private String redInvoiceLabel;

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalInvoiceNo() {
        return originalInvoiceNo;
    }

    public void setOriginalInvoiceNo(String originalInvoiceNo) {
        this.originalInvoiceNo = originalInvoiceNo;
    }

    public BigDecimal getInvoiceTotalPrice() {
        return invoiceTotalPrice;
    }

    public void setInvoiceTotalPrice(BigDecimal invoiceTotalPrice) {
        this.invoiceTotalPrice = invoiceTotalPrice;
    }

    public BigDecimal getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(BigDecimal invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    public String getRedInvoiceLabel() {
        return redInvoiceLabel;
    }

    public void setRedInvoiceLabel(String redInvoiceLabel) {
        this.redInvoiceLabel = redInvoiceLabel;
    }
}
