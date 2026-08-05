package cn.vetech.center.hotel.link.vcc.ejiehui.common;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:05
 */
public class EjiehuiVCCParam<T> {
    /**
     * 1667899926	Unix时间戳和平台服务器时间相差超过10分钟会报错
     */
    private String timestamp;
    /**
     * 3.0	API协议版本,当前版本为3.0
     */
    private String v;
    /**
     * card.status.info	调用的API接口名称
     */
    private String method;
    /**
     * {"orderNo":"8567*****"}	标准JSON类型
     */
    private T data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
