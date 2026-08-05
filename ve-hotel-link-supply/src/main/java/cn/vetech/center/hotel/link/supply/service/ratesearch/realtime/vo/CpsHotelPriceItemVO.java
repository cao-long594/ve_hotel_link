package cn.vetech.center.hotel.link.supply.service.ratesearch.realtime.vo;

import java.io.Serializable;

/**
 * cps酒店价格
 *
 * @author luqs
 * @version v1.0
 */
public class CpsHotelPriceItemVO implements Serializable {
    private static final long serialVersionUID = -4918636495637361754L;
    /**
     * 本地酒店id
     */
    private String localHotelId;
    /**
     * 1正常  2 价格未开启缓存 3 超时未返回 4执行异常
     */
    private String priceStatus;
    /**
     * 价格数据，价格数据太大，压缩传输
     */
    private String searchRoomGzipStr;

    public String getLocalHotelId() {
        return localHotelId;
    }

    public void setLocalHotelId(String localHotelId) {
        this.localHotelId = localHotelId;
    }

    public String getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(String priceStatus) {
        this.priceStatus = priceStatus;
    }

    public String getSearchRoomGzipStr() {
        return searchRoomGzipStr;
    }

    public void setSearchRoomGzipStr(String searchRoomGzipStr) {
        this.searchRoomGzipStr = searchRoomGzipStr;
    }
}
