package cn.vetech.center.hotel.link.elong.notice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2021/7/14 17:12
 */
public class ElongNoticeOrder {
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
    /**
     * 入住日期	DateTime	N
     */
    @JsonProperty("ArrivalDate")
    private String arrivalDate;
    /**
     * 离店日期	DateTime	N
     */
    @JsonProperty("DepartureDate")
    private String departureDate;
    /**
     * 总价	Decimal	N
     */
    @JsonProperty("TotalPrice")
    private String totalPrice;
    /**
     * 房间数量	Int	N
     */
    @JsonProperty("NumberOfRooms")
    private String numberOfRooms;
    /**
     * 合作伙伴从成单接口传入的订单号     String	Y
     */
    @JsonProperty("AffiliateConfirmationId")
    private String affiliateConfirmationId;
    /**
     * 支付状态  Int	Y
     * -1 -- 无支付信息
     * 1 -- 等待担保或支付
     * 2 -- 担保或支付中
     * 3 -- 担保或支付成功
     * 4 -- 担保或支付失败
     * 5 -- 暂缓
     */
    @JsonProperty("PayStatus")
    private String payStatus;

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

    