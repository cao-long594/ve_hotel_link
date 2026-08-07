package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

import java.util.List;

/**
 * 易旅分销 V2 可订房型
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchRoom {
    /**
     * 酒店编码
     */
    private String hotelCode;
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
    /**
     * 最大入住人数
     */
    private Integer maxOccupancy;
    /**
     * 最大成人入住人数
     */
    private Integer maxAdultOccupancy;
    /**
     * 价格计划列表
     */
    private List<YlfxV2RateSearchRate> rates;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

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

    public Integer getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(Integer maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public Integer getMaxAdultOccupancy() {
        return maxAdultOccupancy;
    }

    public void setMaxAdultOccupancy(Integer maxAdultOccupancy) {
        this.maxAdultOccupancy = maxAdultOccupancy;
    }

    public List<YlfxV2RateSearchRate> getRates() {
        return rates;
    }

    public void setRates(List<YlfxV2RateSearchRate> rates) {
        this.rates = rates;
    }
}
