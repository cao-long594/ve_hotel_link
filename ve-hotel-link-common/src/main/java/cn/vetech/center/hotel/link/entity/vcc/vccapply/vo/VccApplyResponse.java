package cn.vetech.center.hotel.link.entity.vcc.vccapply.vo;

import org.omg.PortableInterceptor.ACTIVE;

/**
 * @author xiaotengyu
 * @since 2021/9/27 14:12
 */
public class VccApplyResponse {


    /**
     *商户编号
     */
    private String merchantNo;
    /**
     *卡币种
     */
    private String billCurrency;
    /**
     *Tranhub 系统卡唯一识别号
     */
    private String cardOrderNo;
    /**
     * 商户请求号
     */
    private String merTradeNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * cvv
     */
    private String cvv;
    /**
     * 有效期
     */
    private String expDate;
    /**
     *
     */
    private String firstName;
    /**
     *
     */
    private String lastName;
    /**
     *  是否单次卡 true 单次卡 false 多次卡
     */
    private String singleCard;
    /**
     * 卡余额
     */
    private String bookBalance;
    /**
     * 伸卡金额
     */
    private String applyAmount;
    /**
     * 卡状态 NIT 开卡申请中 FAILED 开卡失败 ACTIVE 激活 CANCELING 销卡处理中 CANCELED 已注销 CLOSE 已失效
     */
    private String cardState;
    /**
     * yyyy-MM-dd HH:mm:ss  卡激活时间
     */
    private String activeDateTime;
    /**
     * 卡有效截止日 期
     */
    private String useEndDate;
    /**
     * Visa、Master 卡组织
     */
    private String cardOrganization;
    /**
     * 发卡行
     */
    private String cardBank;
    /**
     * 返回状态 000000 为成功 返回信息 r
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

    public String getBillCurrency() {
        return billCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public String getCardOrderNo() {
        return cardOrderNo;
    }

    public void se