package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

/**
 * 易旅分销 V2 酒店附加费
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchHotelFee {
    /**
     * 费用类型
     */
    private String type;
    /**
     * 费用金额
     */
    private String amount;
    /**
     * 币种
     */
    private String currencyCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
