package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request;

import java.util.List;

/**
 * 易旅分销 V2 下单入住人房间信息
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderBookPaxRoom {
    /**
     * 房间序号，从 1 开始
     */
    private Integer roomIndex;
    /**
     * 入住人列表
     */
    private List<YlfxV2OrderBookPax> paxNames;

    public Integer getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(Integer roomIndex) {
        this.roomIndex = roomIndex;
    }

    public List<YlfxV2OrderBookPax> getPaxNames() {
        return paxNames;
    }

    public void setPaxNames(List<YlfxV2OrderBookPax> paxNames) {
        this.paxNames = paxNames;
    }
}
