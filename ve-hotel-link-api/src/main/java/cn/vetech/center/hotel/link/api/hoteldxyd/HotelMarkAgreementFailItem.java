package cn.vetech.center.hotel.link.api.hoteldtxyjd;

/**
 * @author vetech
 * @since 25/07/25
 */
public class HotelMarkAgreemenFailItem {

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 酒店id类型
     */
    private String hotelIdType;

    /**
     * 消息
     */
    private String msg;

    public HotelMarkAgreemenFailItem(String hotelId, String hotelIdType, String msg) {
        this.hotelId = hotelId;
        this.hotelIdType = hotelIdType;
        this.msg = msg;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelIdType() {
        return hotelIdType;
    }

    public void setHotelIdType(String hotelIdType) {
        this.hotelIdType = hotelIdType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
