package cn.vetech.center.hotel.link.supply.service.mapping.model;

import java.util.List;

/**
 * 酒店基础信息
 *
 * @author luqs
 * @version v1.0
 **/
public class HotelBaseInfo {
    /**
     * 房源酒店id
     */
    private String hotelId;
    /**
     * 房源城市id
     */
    private String cityId;
    /**
     * 房型信息列表
     */
    private List<RoomBaseInfo> roomList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<RoomBaseInfo> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomBaseInfo> roomList) {
        this.roomList = roomList;
    }
}
