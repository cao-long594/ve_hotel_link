package cn.vetech.center.hotel.link.elong.orderbook.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author gaojin
 */
public class ElongBookDateBreakFast implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日期
     */
    @JsonProperty("Date")
    private String date;
    /**
     * 包含的早餐份数
     */
    @JsonProperty("BreakFastCount")
    private String breakFastCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBreakFastCount() {
        return breakFastCount;
    }

    public void setBreakFastCount(String breakFastCount) {
        this.breakFastCount = breakFastCount;
    }
}
