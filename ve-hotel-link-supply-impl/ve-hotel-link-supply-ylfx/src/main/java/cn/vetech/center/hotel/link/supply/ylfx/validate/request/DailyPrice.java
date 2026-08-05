package cn.vetech.center.hotel.link.supply.ylfx.validate.request;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class DailyPrice {
    /**
     * 日期
     */
    private String night;
    /**
     * 价格
     */
    private String price;

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
