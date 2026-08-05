package cn.vetech.center.hotel.link.elong.ordercancel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2022-07-27 11:26
 */
public class ElongOrderIncrInfo {

    /**
     * 增长id	Long	N
     */
    @JsonProperty("LastId")
    private String lastId;
    /**
     * 变化时间	DateTime	N
     */
    @JsonProperty("Time")
    private String time;
    /**
     * 订单ID	Long	N
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 订单状态	String(2)	N
     */
    @JsonProperty("Status")
    private String status;

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
