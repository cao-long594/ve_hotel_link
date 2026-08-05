package cn.vetech.center.hotel.link.vcc.ejiehui.common;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:04
 */
public class EjiehuiVCCBaseRequest {
    /**
     * 平台分配给第三方的业务 ID
     */
    private String appKey;
    /**
     * 业务请求数据标准JSON类型
     */
    private String param;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
