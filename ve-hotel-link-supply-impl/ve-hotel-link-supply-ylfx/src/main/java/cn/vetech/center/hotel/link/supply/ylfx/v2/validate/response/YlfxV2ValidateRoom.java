package cn.vetech.center.hotel.link.supply.ylfx.v2.validate.response;

import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response.YlfxV2RateSearchRate;

/**
 * 易旅分销 V2 产品可订校验房型
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2ValidateRoom {
    /**
     * 房型编码
     */
    private String roomCode;
    /**
     * 校验后的报价
     */
    private YlfxV2RateSearchRate rate;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public YlfxV2RateSearchRate getRate() {
        return rate;
    }

    public void setRate(YlfxV2RateSearchRate rate) {
        this.rate = rate;
    }
}
