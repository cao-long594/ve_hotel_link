package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 响应结果Result
 *
 * @author gaojin
 */
public class ElongRateSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 查询到的酒店总数
     */
    @JsonProperty("Count")
    private String count;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 酒店结果集
     */
    @JsonProperty("Hotels")
    private List<ElongSearchHotel> hotels;
    /**
     * 酒店结果集
     */
    @JsonProperty("ExchangeRateList")
    private List<ExchangeRate> exchanges;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ElongSearchHotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<ElongSearchHotel> hotels) {
        this.hotels = hotels;
    }

    public List<ExchangeRate> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<ExchangeRate> exchanges) {
        this.exchanges = exchanges;
    }
}
