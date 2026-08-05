package cn.vetech.center.hotel.link.api.realtimeprice.dto;

import java.io.Serializable;

/**
 * 实时报价拓展信息
 *
 * @author luqs
 * @version v1.0
 */
public class RealTimePriceExtDTO implements Serializable {
    private static final long serialVersionUID = -7820582286957617128L;
    /**
     * 列表最低价获取方式，0：批量同步获取；1：单个异步获取
     */
    private String listPriceGetWay;
    /**
     * 等待时间(ms)
     */
    private Integer waitTime;
    /**
     * 酒店信息
     */
    private HotelItemDTO hotelItem;
    /**
     * 请求来源，charge：费控；ve：独立link-ve分发，默认charge
     */
    private String requestSource;

    public String getListPriceGetWay() {
        return listPriceGetWay;
    }

    public void setListPriceGetWay(String listPriceGetWay) {
        this.listPriceGetWay = listPriceGetWay;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public HotelItemDTO getHotelItem() {
        return hotelItem;
    }

    public void setHotelItem(HotelItemDTO hotelItem) {
        this.hotelItem = hotelItem;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }
}
