package cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class GetRoomListByHotelIdResponse extends YlfxBaseResponse {
    /**
     * 房型列表
     */
    @JsonProperty("data")
    private List<GetRoomListByHotelIdData> roomList;

    public List<GetRoomListByHotelIdData> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<GetRoomListByHotelIdData> roomList) {
        this.roomList = roomList;
    }
}
