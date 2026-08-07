package cn.vetech.center.hotel.link.util.validate.pojo;

import java.io.Serializable;

/**
 * @author chengwanshan
 * @since 2022/3/23 19:24
 */
public class DailyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当天日期，yyyy-MM-dd
     */
    private String date;
    /**
     * 房价
     */
    private String price;
    /**
     * 房量
     */
    private String roomQuantity;

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

    public String getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(String roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}
