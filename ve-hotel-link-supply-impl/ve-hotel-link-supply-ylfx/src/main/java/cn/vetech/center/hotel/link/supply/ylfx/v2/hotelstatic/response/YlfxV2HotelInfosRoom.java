package cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.response;

/**
 * 易旅分销 V2 酒店房型信息
 *
 * @author 6161
 * @date 2026/08/07
 */
public class YlfxV2HotelInfosRoom {
    /**
     * 房型编码
     */
    private String roomCode;
    /**
     * 房型中文名称
     */
    private String roomNameCn;
    /**
     * 房型英文名称
     */
    private String roomNameEn;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomNameCn() {
        return roomNameCn;
    }

    public void setRoomNameCn(String roomNameCn) {
        this.roomNameCn = roomNameCn;
    }

    public String getRoomNameEn() {
        return roomNameEn;
    }

    public void setRoomNameEn(String roomNameEn) {
        this.roomNameEn = roomNameEn;
    }
}
