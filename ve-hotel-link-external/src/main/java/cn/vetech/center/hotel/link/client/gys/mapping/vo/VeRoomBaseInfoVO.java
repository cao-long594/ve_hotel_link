package cn.vetech.center.hotel.link.client.gys.mapping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 房型信息
 *
 * @author luqs
 * @version v1.0
 */
@ApiModel(value = "房型信息")
public class VeRoomBaseInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 房型id
     */
    @ApiModelProperty(value = "房型id", dataType = "string")
    private String roomId;
    /**
     * 房型名称
     */
    @ApiModelProperty(value = "房型名称", dataType = "string")
    private String roomName;
    /**
     * 房间数
     */
    @ApiModelProperty(value = "房间数", dataType = "string")
    private String roomNum;
    /**
     * 床型
     */
    @ApiModelProperty(value = "床型", dataType = "string")
    private String bedType;
    /**
     * 床数
     */
    @ApiModelProperty(value = "床数", dataType = "string")
    private String bedNum;

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

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }
}
