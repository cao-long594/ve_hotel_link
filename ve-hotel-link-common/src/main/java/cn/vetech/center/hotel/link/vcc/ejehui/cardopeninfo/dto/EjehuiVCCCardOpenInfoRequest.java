package cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.dto;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:16
 */
public class EjiehuiVCCCardOpenInfoRequest {
    /**
     * 卡片类型	    String	是	见说明1（测试可用：A_ENM_V_X）
     */
    private String cardType;
    /**
     * 刷卡金额	    String	是	需要支付的订单金额
     */
    private String cardAmount;
    /**
     * 刷卡币种	    String	是	支付订单金额对应的币种（如3.3货币编号）
     */
    private String cardCurrency;
    /**
     * 结算币种	    String	是
     */
    private String billCurrency;
    /**
     * 交易次数	    Integer	是	0（单次）；1（多次）
     */
    private Integer useTime;
    /**
     * 卡到期时间	String	是	格式：yyyy-MM-dd HH:mm:ss说明：此时间需大于当天日期
     */
    private String endTime;
    /**
     * 备注	        String	否
     */
    private String extInfoyx;
    /**
     * 卡bin	    String	否
     */
    private String cardBin;
    /**
     * 币种转换	    Integer	否	1（转换)；如：cardCurrecny =“THB”,系统自动转换为cardCurrecny =billCurrency；cardAmount为THB按银行汇率预估金额确认开卡，并返回的cardCurrecny =billCurrency；
     */
    private Integer isCardCurrency;
    /**
     * 订单信息
     */
    private EjiehuiVCCCardOpenInfoRiskVerifyInfo riskVerifyInfo;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(String cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getCardCurrency() {
        return cardCurrency;
    }

    public void setCardCurrency(String cardCurrency) {
        this.cardCurrency = cardCurrency;
    }

    public String getBillCurrency() {
        return billCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime)