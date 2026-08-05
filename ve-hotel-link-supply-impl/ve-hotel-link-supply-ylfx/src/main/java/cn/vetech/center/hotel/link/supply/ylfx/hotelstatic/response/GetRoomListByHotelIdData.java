package cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.response;

/**
 * @author 6161
 * @date 2024/07/19
 */
public class GetRoomListByHotelIdData {
    /**
     *酒店ID
     */
    private String hotelId;
    /**
     *房型ID
     */
    private String roomtypeId;
    /**
     *房型名称
     */
    private String roomtypeName;
    /**
     *是否有效： 0：无效 1. 有效
     */
    private Integer active;
    /**
     *代理通母房型ID
     */
    private String dltmasterroomtypeid;

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

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getDltmasterroomtypeid() {
        return dltmasterroomtypeid;
    }

    public void setDltmasterroomtypeid(String dltmasterroomtypeid) {
        this.dltmasterroomtypeid = dltmasterroomtypeid;
    }
}
