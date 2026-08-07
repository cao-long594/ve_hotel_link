package cn.vetech.center.hotel.link.supply.ylfx.v2.validate.response;

/**
 * 易旅分销 V2 产品可订校验响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2ValidateResponse {
    /**
     * 响应编码：200 成功
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 校验结果
     */
    private YlfxV2ValidateData data;

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

    public YlfxV2ValidateData getData() {
        return data;
    }

    public void setData(YlfxV2ValidateData data) {
        this.data = data;
    }
}
