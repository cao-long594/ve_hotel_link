package cn.vetech.center.hotel.link.entity.vcc.vccapply.dto;

import cn.vetech.center.hotel.link.entity.vcc.VccBaseRequest;

import java.math.BigDecimal;

/**
 * @author xiaotengyu
 * @since 2021/9/27 14:05
 */
public class VccApplyRequest extends VccBaseRequest {

    /**
     * 商户请求号 商 户 全 局 唯 一号，唯一关 联一张卡
     */
    private String merTradeNo;
    /**
     * 伸卡币种 伸 卡 账 户 的 币种  CNY USD HKD
     */
    private String currency;
    /**
     * 伸卡额度
     */
    private BigDecimal amount;
    /**
     *卡有效截止日 期  必 须 是 1 个 月 以 后 的 一 个日期； 单 次 卡 有 效 期最长 1 年； 多 次 卡 有 效 期最长 2 年
     */
    private String useEndDate;
    /**
     * 是否单次卡  true 为 单 次 卡false 为多次 卡
     */
    private Boolean singleUse;
    /**
     * 备注信息
     */
    private String remark;

    /**
     *firstName
     */
    private String firstName;
    /**
     *lastName
     */
    private String lastName;

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

    public String getMerTradeNo() {
        return merTradeNo;
    }

    public void setMerTradeNo(String merTradeNo) {
        this.merTradeNo = merTradeNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(String useEndDate) {
        this.useEndDate = useEndDate;
    }

    public Boolean getSingleUse() {
        return s