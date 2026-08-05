package cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.vo;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:17
 */
public class EjiehuiVCCCardOpenInfoResponse {
    /**
     * 平台订单号	String	系统返回
     */
    private String orderNo;
    /**
     * 卡号	String	621735*****1909
     */
    private String cardNo;
    /**
     * 有效期	String	格式：MM/YY
     */
    private String validityDate;
    /**
     * 卡片cvv码	String	234
     */
    private String cvvPassword;
    /**
     * 卡组织	String
     */
    private String cardType;
    /**
     * 卡使用次数	Integer	1（多次）；0（多次）；
     */
    private String useTimes;
    /**
     * 刷卡金额	String	小数两位（0.01）
     */
    private String cardAmount;
    /**
     * 刷卡币种	String	CNY
     */
    private String cardCurrency;
    /**
     * 开卡时间	String	格式：yyyy-MM-dd HH:mm:ss
     */
    private String startTime;
    /**
     * 失效时间	String	格式：yyyy-MM-dd HH:mm:ss
     */
    private String endTime;
    /**
     * 结算金额	String	小数两位（0.01）（实际开卡结算金额）表示实际卡里可用金额
     */
    private String billAmount;
    /**
     * 结算币种	String	USD
     */
    private String billCurrency;
    /**
     * 本币汇率	String	结算币种和刷卡币种的兑换汇率，只是参考值，实际以清算处理
     */
    private String bankRate;
    /**
     * 开卡服务费	String	以合同约定处理，如无：返回 NULL
     */
    private String serviceFee;
    /**
     * 货币兑换费	String	由卡类型决定，如无：返回 NULL
     */
    private String currencyExchangeFee;
    /**
     * 开卡实际扣款金额	String	实际从账户扣款金额
     */
    private String actualConvertedAmount;
    /**
     * 风控信息	String	原值返回（fight或hotel或ecom）
     */
    private String supportedMccGroup;
    /**
     * 卡状态	String	1:激活
     */
    private String status;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;