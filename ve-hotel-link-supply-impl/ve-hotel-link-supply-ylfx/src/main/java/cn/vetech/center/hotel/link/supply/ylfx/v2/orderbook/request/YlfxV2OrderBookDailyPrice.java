package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request;

/**
 * 易旅分销 V2 下单每日价格
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderBookDailyPrice {
    /**
     * 日期
     */
    private String date;
    /**
     * 价格
     */
    private String price;

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
}
