package cn.vetech.center.hotel.link.api.ratesearch.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 房源商映射关系类
 * 查询报价时用
 *
 * @author gaojin
 */
public class Mapper implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房源商编号
     */
    @ApiModelProperty(value = "房源商编号", dataType = "string")
    private String fybh;
    /**
     * 房源商酒店ID
     */
    @ApiModelProperty(value = "房源商酒店ID", dataType = "string")
    private String hotelid;
    /**
     * 房源商房型ID
     */
    @ApiModelProperty(value = "房源商房型ID", dataType = "string")
    private String roomid;

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}
