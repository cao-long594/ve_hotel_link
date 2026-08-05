package cn.vetech.center.hotel.link.entity.vcc.vccquery.vo;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2021/9/27 14:12
 */
public class VccQueryResponse {

    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 账单列表
     */
    private List<VccQueryBill> bills;
    /**
     * 返回状态
     */
    private String responseCode;
    /**
     * 返回信息
     */
    private String responseMessage;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public List<VccQueryBill> getBills() {
        return bills;
    }

    public void setBills(List<VccQueryBill> bills) {
        this.bills = bills;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
