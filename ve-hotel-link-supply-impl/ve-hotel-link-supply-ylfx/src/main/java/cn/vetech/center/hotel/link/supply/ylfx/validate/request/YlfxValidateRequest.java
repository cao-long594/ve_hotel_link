package cn.vetech.center.hotel.link.supply.ylfx.validate.request;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseRequest;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxValidateRequest extends YlfxBaseRequest {
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 每日房间数
     */
    private int roomCount;
    /**
     * 入住日期
     */
    private String checkinDate;
    /**
     * 离店日期
     */
    private String checkoutDate;
    /**
     * 每日价格列表
     */
    private List<DailyPrice> dailyList;
    /**
     * 总价
     */
    private String totalPrice;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public List<DailyPrice> getDailyList() {
        return dailyList;
    }

    public void setDailyList(List<DailyPrice> dailyList) {
        this.dailyList = dailyList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
