package cn.vetech.center.hotel.link.elong.ordercancel.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 艺龙订单详情请求对象
 *
 * @author gaojin
 */
public class ElongOrderCancelRequest extends ElongRequest {
    /**
     * 订单编号
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 取消类型
     * 对酒店相关条件不满意,航班推迟
     * 价格过高，客人不接受,通过其它途径预订
     * 行程变更,*已换酒店,重单,其它
     */
    @JsonProperty("CancelCode")
    private String cancelCode;
    /**
     * 具体原因
     */
    @JsonProperty("Reason")
    private String reason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public void setCancelCode(String cancelCode) {
        this.cancelCode = cancelCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
