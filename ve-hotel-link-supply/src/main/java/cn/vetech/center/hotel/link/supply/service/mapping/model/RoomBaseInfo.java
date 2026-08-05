package cn.vetech.center.hotel.link.supply.service.mapping.model;

/**
 * 酒店房型信息
 *
 * @author luqs
 * @version v1.0
 **/
public class RoomBaseInfo {
    /**
     * 房型id
     */
    private String roomId;
    /**
     * 房型名称
     */
    private String roomName;
    /**
     * 房间数
     */
    private Integer roomNum;
    /**
     * 床型
     */
    private String bedType;
    /**
     * 床数
     */
    private Integer bedNum;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }
}
