package cn.vetech.center.hotel.link.entity.vcc.vccquery.vo;

import java.math.BigDecimal;

/**
 * @author chengwanshan
 * @since 2025/8/4 13:40
 */
public class VccQueryBill {
    /**
     * 签名序号
     */
    private String index;
    /**
     * 卡类型
     */
    private String vccCardType;
    /**
     * 账单号
     */
    private String billNo;
    /**
     * 商户请求号
     */
    private String merTradeNo;
    /**
     * 卡唯一识别号
     */
    private String cardOrderNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 账户币种
     */
    private String billCurrency;
    /**
     * 账户币种金额
     */
    private BigDecimal billAmount;
    /**
     * 交易币种
     */
    private String saleCurrency;
    /**
     * 交易币种金额
     */
    private BigDecimal saleAmount;
    /**
     * 关联授权账单号
     */
    private String authBillNo;
    /**
     * 授权日期
     */
    private String tranDate;
    /**
     * 授权时间
     */
    private String tranDateTime;
    /**
     * 结算日期
     */
    private String settleDate;
    /**
     * 结算时间
     */
    private String settleDateTime;
    /**
     * 交易类型
     */
    private String tranType;
    /**
     * 账单结算账户
     */
    private String settleAccType;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 账单类型
     */
    private String billType;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getVccCardType() {
        return vccCardType;
    }

    public void setVccCardType(String vccCardType) {
        this.vccCardType = vccCardType;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getMerTradeNo() {
        return merTradeNo;
    }

    public void setMerTradeNo(String merTradeNo) {
        