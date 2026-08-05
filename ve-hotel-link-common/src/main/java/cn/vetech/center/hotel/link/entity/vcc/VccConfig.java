package cn.vetech.center.hotel.link.entity.vcc;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:36
 */
public class VccConfig {

    /**
     * baseUrl
     */
    private String baseUrl;
    /**
     * sha256Key
     */
    private String sha256Key;
    /**
     * aesKey
     */
    private String aesKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSha256Key() {
        return sha256Key;
    }

    public void setSha256Key(String sha256Key) {
        this.sha256Key = sha256Key;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
