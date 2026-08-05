package cn.vetech.center.hotel.link.supply.ylfx.common;

import cn.vetech.center.hotel.link.supply.base.config.BaseConfig;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxConfig extends BaseConfig {
    /**
     * 接口版本，空值按旧版接口处理
     */
    private String apiVersion;
    /**
     * 接口地址
     */
    private String url;
    /**
     * appId
     */
    private String appId;
    /**
     * appKey
     */
    private String appKey;
    /**
     * V2 请求头 appid
     */
    private String appid;
    /**
     * V2 签名密钥，仅用于本地签名计算
     */
    private String secret;
    /**
     * V2 客户编码
     */
    private String customerCode;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
