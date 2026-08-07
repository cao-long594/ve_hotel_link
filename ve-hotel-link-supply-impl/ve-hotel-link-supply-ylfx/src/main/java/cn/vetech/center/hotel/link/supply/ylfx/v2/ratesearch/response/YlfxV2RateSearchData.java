package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

import java.util.List;

/**
 * 易旅分销 V2 可订产品数据
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchData {
    /**
     * 酒店编码
     */
    private String hotelCode;
    /**
     * 币种
     */
    private String currencyCode;
    /**
     * 房型列表
     */
    private List<YlfxV2RateSearchRoom> rooms;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<YlfxV2RateSearchRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<YlfxV2RateSearchRoom> rooms) {
        this.rooms = rooms;
    }
}
