package cn.vetech.center.hotel.link.api.ratesearch.dto;

/**
 *
 * 预定用户信息，对接美团会员价新增
 * 使用 vip开头实体
 * @author chengwanshan
 * @since 2021/11/18 19:50
 */
@Deprecated
public class UserExtInfo {
    /**
     * 预订用户当前所在IP地址【必填】
     */
    private String ydrIp;
    /**
     * 预订用户当前所在百度纬度【非必填】
     */
    private String ydrLat;
    /**
     * 预订用户当前所在百度经度【非必填】
     */
    private String ydrLng;
    /**
     * 预订用户手机号【必填】
     */
    private String ydrPhoneNumber;
    /**
     * 预订终端枚举 Android、iPhone、PC\Touch
     */
    private String ydrYdzd;
    /**
     * //唯一设备id
     */
    private String deviceUniqueId;

    public String getDeviceUniqueId() {
        return deviceUniqueId;
    }

    public void setDeviceUniqueId(String deviceUniqueId) {
        this.deviceUniqueId = deviceUniqueId;
    }

    public String getYdrIp() {
        return ydrIp;
    }

    public void setYdrIp(String ydrIp) {
        this.ydrIp = ydrIp;
    }

    public String getYdrLat() {
        return ydrLat;
    }

    public void setYdrLat(String ydrLat) {
        this.ydrLat = ydrLat;
    }

    public String getYdrLng() {
        return ydrLng;
    }

    public void setYdrLng(String ydrLng) {
        this.ydrLng = ydrLng;
    }

    public String getYdrPhoneNumber() {
        return ydrPhoneNumber;
    }

    public void setYdrPhoneNumber(String ydrPhoneNumber) {
        this.ydrPhoneNumber = ydrPhoneNumber;
    }

    public String getYdrYdzd() {
        return ydrYdzd;
    }

    public void setYdrYdzd(String ydrYdzd) {
        this.ydrYdzd = ydrYdzd;
    }
}
