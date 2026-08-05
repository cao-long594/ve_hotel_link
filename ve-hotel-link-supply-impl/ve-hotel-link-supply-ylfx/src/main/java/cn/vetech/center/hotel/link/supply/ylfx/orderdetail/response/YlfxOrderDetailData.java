package cn.vetech.center.hotel.link.supply.ylfx.orderdetail.response;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class YlfxOrderDetailData {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 渠道端订单ID
     */
    private String cusOrderId;
    /**
     * 1. 预订处理中 2. 预订已经确认  3. 预订已经拒绝    4. 取消处理中 5. 取消已经确认 6. 取消已经拒绝
     */
    private Integer status;
    /**
     * 结果编码：0. 查询成功 1. 查询失败
     */
    private Integer code;
    /**
     * 失败原因描叙
     */
    private String desc;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
