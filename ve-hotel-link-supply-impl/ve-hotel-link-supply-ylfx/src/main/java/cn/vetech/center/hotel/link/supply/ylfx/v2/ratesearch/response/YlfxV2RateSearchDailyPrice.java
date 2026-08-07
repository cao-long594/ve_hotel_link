package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

/**
 * 易旅分销 V2 每日价格
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchDailyPrice {
    /**
     * 日期
     */
    private String date;
    /**
     * 价格
     */
    private String price;
    /**
     * 币种
     */
    private String currencyCode;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
