package cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.response;

/**
 * 易旅分销 V2 查询订单响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderDetailResponse {
    /**
     * 响应编码：200 成功
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 订单数据
     */
    private YlfxV2OrderDetailData data;

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

    public YlfxV2OrderDetailData getData() {
        return data;
    }

    public void setData(YlfxV2OrderDetailData data) {
        this.data = data;
    }
}
