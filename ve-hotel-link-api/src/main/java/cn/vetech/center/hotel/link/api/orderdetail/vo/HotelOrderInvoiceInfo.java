package cn.vetech.center.hotel.link.api.orderdetail.vo;

/**
 * @author chengwanshan
 * @since 2025/1/21 14:49
 */
public class HotelOrderInvoiceInfo {
    /**
     * 发票文件base64码
     */
    private String encodeFileStr;
    /**
     * 发票文件格式，pdf
     */
    private String invoiceFileType;

    public String getEncodeFileStr() {
        return encodeFileStr;
    }

    public void setEncodeFileStr(String encodeFileStr) {
        this.encodeFileStr = encodeFileStr;
    }

    public String getInvoiceFileType() {
        return invoiceFileType;
    }

    public void setInvoiceFileType(String invoiceFileType) {
        this.invoiceFileType = invoiceFileType;
    }
}
