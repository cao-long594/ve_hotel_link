package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class RoomStatusDailyData {
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 房型ID
     */
    private String roomtypeId;
    /**
     * 日期：格式yyyyMMdd
     */
    private String night;
    /**
     * 房态： 0. 关房 1. 开房
     */
    private Integer roomStatus;
    /**
     * 房量
     */
    private Integer roomCount;
    /**
     * 是否可超预订：0. 不可超 1. 可超
     */
    private Integer overBooking;

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

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getOverBooking() {
        return overBooking;
    }

    public void setOverBooking(Integer overBooking) {
        this.overBooking = overBooking;
    }
}
