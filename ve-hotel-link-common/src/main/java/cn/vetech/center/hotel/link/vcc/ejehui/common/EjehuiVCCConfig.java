package cn.vetech.center.hotel.link.vcc.ejiehui.common;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:36
 */
public class EjiehuiVccConfig {

    /**
     * baseUrl
     */
    private String baseUrl;
    /**
     * appKey
     */
    private String appKey;
    /**
     * appSecret
     */
    private String appSecret;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
