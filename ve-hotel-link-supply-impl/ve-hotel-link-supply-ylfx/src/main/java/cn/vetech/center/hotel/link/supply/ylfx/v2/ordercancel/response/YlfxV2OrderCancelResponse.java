package cn.vetech.center.hotel.link.supply.ylfx.v2.ordercancel.response;

/**
 * 易旅分销 V2 取消订单响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderCancelResponse {
    /**
     * 响应编码：200 成功
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 取消结果数据
     */
    private YlfxV2OrderCancelData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public YlfxV2OrderCancelData getData() {
        return data;
    }

    public void setData(YlfxV2OrderCancelData data) {
        this.data = data;
    }
}
