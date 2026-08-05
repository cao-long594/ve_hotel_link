package cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.dto;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:24
 */
public class EjiehuiVCCCardOpenInfoRiskVerifyInfo {
    /**
     * 风控订单	String	是	flight（机票），hotel（酒店及航旅其它），ecom（电商）
     */
    private String riskOrderType;
    // 下面参数建议 （单次卡）提供:
    // 说明1： （单次卡）提供:	见网站登录，账户管理里的接口管理（卡类型）
    // 说明2： （单次卡）提供:	T_HMS_V’：刷卡币种只能是USD；‘A_ENM_M’和‘A_ENM_V’开卡（币种不一致）时，（为防止结算时因汇率变动而交易结算失败，预增加1%的存入卡中）； ‘A_ENM_V’：开卡收取货币兑换费（详细请咨询商务）；
    /**
     * 产品名称	String	否	航空公司 或 酒店名称 或 产品名称
     */
    private String riskOrderTitle;
    /**
     * 订单信息	String	否	机票：航线
     */
    private String riskOrderGuestName;
    /**
     * 订单生效日期	String	否	格式：yyyy-MM-ddHH
     */
    private String riskOrderActiveDate;
    /**
     * 订单到期日期	String	否	格式：yyyy-MM-ddHH
     */
    private String riskOrderExpiryDate;
    /**
     * 订单金额	String	否	格式：179.01
     */
    private String riskOrderAmount;

    public String getRiskOrderType() {
        return riskOrderType;
    }

    public void setRiskOrderType(String riskOrderType) {
        this.riskOrderType = riskOrderType;
    }

    public String getRiskOrderTitle() {
        return riskOrderTitle;
    }

    public void setRiskOrderTitle(String riskOrderTitle) {
        this.riskOrderTitle = riskOrderTitle;
    }

    public String getRiskOrderGuestName() {
        return riskOrderGuestName;
    }

    public void setRiskOrderGuestName(String riskOrderGuestName) {
        this.riskOrderGuestName = riskOrderGuestName;
    }

    public String getRiskOrderActiveDate() {
        return riskOrderActiveDate;
    }

    public void setRiskOrderActiveDate(String riskOrderActiveDate) {
        this.riskOrderActiveDate = riskOrderActiveDate;
    }

    public String getRiskOrderExpiryDate() {
        return riskOrderExpiryDate;
    }

    public void setRiskOrderExpiryDate(String riskOrderExpiryDate) {
        this.riskOrderExpiryDate 