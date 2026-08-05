package cn.vetech.center.hotel.link.elong.ratesearch.request;

import java.io.Serializable;

/**
 * 此类用于转换房型使用，不存在请求和返回
 *
 * @author lixuan
 * @since 2018/5/28.
 */
public class ElongRoomConvert implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String roomTypeId;
    /**
     *
     */
    private String roomId;
    /**
     *
     */
    private Boolean status;
    /**
     *
     */
    private String hotelCode;

    public ElongRoomConvert() {
    }


    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
}
