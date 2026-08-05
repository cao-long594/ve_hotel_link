package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class GetRoomStatusDailyResponse extends YlfxBaseResponse {
    /**
     * 每日房态列表
     */
    @JsonProperty("data")
    private List<RoomStatusDailyData> roomStatusDailyDataList;

    public List<RoomStatusDailyData> getRoomStatusDailyDataList() {
        return roomStatusDailyDataList;
    }

    public void setRoomStatusDailyDataList(List<RoomStatusDailyData> roomStatusDailyDataList) {
        this.roomStatusDailyDataList = roomStatusDailyDataList;
    }
}
