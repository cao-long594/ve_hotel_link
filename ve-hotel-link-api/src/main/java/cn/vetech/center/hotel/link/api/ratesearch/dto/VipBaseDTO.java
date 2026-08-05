package cn.vetech.center.hotel.link.api.ratesearch.dto;

import java.io.Serializable;

/**
 * @author vetech
 * @since 2023/11/10
 */
public class VipBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 预订人ip
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
     * 预订终端枚举 Android、iPhone、PC\Touch
     */
    private String ydrYdzd;

    /**
     * 预订人手机号
     */
    private String ydrPhoneNumber;

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

    public String getYdrYdzd() {
        return ydrYdzd;
    }

    public void setYdrYdzd(String ydrYdzd) {
        this.ydrYdzd = ydrYdzd;
    }

    public String getYdrPhoneNumber() {
        return ydrPhoneNumber;
    }

    public void setYdrPhoneNumber(String ydrPhoneNumber) {
        this.ydrPhoneNumber = ydrPhoneNumber;
    }
}
