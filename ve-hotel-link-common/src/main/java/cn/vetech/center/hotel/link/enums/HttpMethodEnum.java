package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2022/8/4 11:55
 */
public enum HttpMethodEnum {
    /**
     * GET
     */
    GET("GET", "GET请求"),
    /**
     * POST
     */
    POST("POST", "POST请求"),
    /**
     * PUT
     */
    PUT("PUT", "PUT请求"),
    /**
     * DELETE
     */
    DELETE("DELETE", "DELETE请求"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HttpMethodEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
