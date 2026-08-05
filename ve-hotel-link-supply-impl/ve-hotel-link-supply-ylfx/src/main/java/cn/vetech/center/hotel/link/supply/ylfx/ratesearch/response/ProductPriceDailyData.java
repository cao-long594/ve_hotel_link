package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class ProductPriceDailyData {
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 房型ID
     */
    private String roomtypeId;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 日期：格式yyyyMMdd
     */
    private String night;
    /**
     * 价格，单位元，保留两位小数
     */
    private String price;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(String roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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
