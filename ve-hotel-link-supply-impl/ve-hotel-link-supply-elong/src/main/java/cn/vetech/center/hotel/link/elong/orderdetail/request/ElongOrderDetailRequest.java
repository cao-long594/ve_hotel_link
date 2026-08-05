package cn.vetech.center.hotel.link.elong.orderdetail.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 艺龙订单详情请求对象
 *
 * @author gaojin
 */
public class ElongOrderDetailRequest extends ElongRequest {
    /**
     * 订单编号
     * 如果OrderId不为0，以OrderId为主
     */
    @JsonProperty("OrderId")
    private String orderId;
    /**
     * 联盟的订单编号
     * 当OrderId=0的时候，则按AffiliateConfirmationId查询
     */
    @JsonProperty("AffiliateConfirmationId")
    private String affiliateConfirmationId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAffiliateConfirmationId() {
        return affiliateConfirmationId;
    }

    public void setAffiliateConfirmationId(String affiliateConfirmationId) {
        this.affiliateConfirmationId = affiliateConfirmationId;
    }
}
