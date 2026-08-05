package cn.vetech.center.hotel.link.entity.vcc;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:09
 */
public class VccBaseRequest {

    /**
     * 商户编号  商户编号必须上送  商户编号长度为10
     */
    protected String merchantNo;
    /**
     * 产品编码  产品编码必须上送
     */
    protected String productCode;
    /**
     * 请求号
     */
    protected String requestNo;

    /**
     * content
     */
    protected String content;
    /**
     * sign
     */
    protected String sign;

    /**
     * v
     */
    protected String vccCardType;

    public String getVccCardType() {
        return vccCardType;
    }

    public void setVccCardType(String vccCardType) {
        this.vccCardType = vccCardType;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
