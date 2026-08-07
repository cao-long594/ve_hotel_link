package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

/**
 * 易旅分销 V2 取消政策
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchCancelPolicy {
    /**
     * 费用生效时间
     */
    private String from;
    /**
     * 扣款金额
     */
    private String amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
