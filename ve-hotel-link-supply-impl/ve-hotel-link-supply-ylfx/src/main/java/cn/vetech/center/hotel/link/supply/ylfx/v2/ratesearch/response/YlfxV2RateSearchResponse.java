package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

/**
 * 易旅分销 V2 可订产品查询响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchResponse {
    /**
     * 响应编码：200 成功
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 可订产品数据
     */
    private YlfxV2RateSearchData data;

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

    public YlfxV2RateSearchData getData() {
        return data;
    }

    public void setData(YlfxV2RateSearchData data) {
        this.data = data;
    }
}
