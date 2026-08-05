package cn.vetech.center.hotel.link.api.ratesearch.vo;

import java.math.BigDecimal;

/**
 * @author chengwanshan
 * @since 2024/2/29 16:08
 */
public class FeeInfo {
    /**
     * 费用
     */
    private BigDecimal fee;
    /**
     * 币种
     */
    private String currency;
    /**
     *  币种名称，参考枚举CurrencyEnum
     */
    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
