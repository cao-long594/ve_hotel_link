package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ExchangeRate {
    /**
     * 货币编码
     */
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    /**
     * 汇率值
     */
    @JsonProperty("Rate")
    private String rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
