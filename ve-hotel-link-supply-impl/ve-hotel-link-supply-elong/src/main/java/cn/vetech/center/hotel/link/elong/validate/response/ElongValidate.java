package cn.vetech.center.hotel.link.elong.validate.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xingyanyan on 2017/12/29.
 */
public class ElongValidate {
    /**
     * 验证结果
     * OK:  正常可预订
     * Product：产品无效或关房
     * Inventory：房量不够
     * Rate:价格不符
     */
    @JsonProperty("ResultCode")
    private String resultCode;
    /**
     * 具体结果信息
     */
    @JsonProperty("ErrorMessage")
    private String errorMessage;
    /**
     * 担保金额
     * 如果是担保订单才有这个值
     */
    @JsonProperty("GuaranteeRate")
    private String guaranteeRate;
    /**
     * 担保金额的货币类型
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 最晚取消时间
     * 担保订单可取消的时间，如果返回的时间小于当前时间，则代表此订单不可变更取消
     */
    @JsonProperty("CancelTime")
    private String cancelTime;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(String guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }
}
