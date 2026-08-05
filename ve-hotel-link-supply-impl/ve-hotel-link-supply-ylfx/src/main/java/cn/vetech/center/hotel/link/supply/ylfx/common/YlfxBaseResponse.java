package cn.vetech.center.hotel.link.supply.ylfx.common;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxBaseResponse {
    /**
     * 响应码：200：成功，403：授权错误，其他值：系统错误
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;

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
}
