package cn.vetech.center.hotel.link.api.ratesearch.dto;

/**
 * @author chengwanshan
 * @since 2021/12/30 11:16
 */
public class UserVipExtInfo {

    /**
     * 房源编号， 如：31200830（美团）
     */
    private String fybh;
    /**
     * 预订用户手机号【必填】。用户查询会员价绑定的fybh对应房源商的手机号
     * 如：用户甲，手机号1绑定美团会员价，手机号2绑定携程会员价
     * 目前费控前端有控制，每个用户一个房源商只能绑定一个手机号，一个手机号可以绑定多个房源商
     */
    private String phoneNumber;
    /**
     * 携程会员用户真实UID
     */
    private String ydrUid;

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getYdrUid() {
        return ydrUid;
    }

    public void setYdrUid(String ydrUid) {
        this.ydrUid = ydrUid;
    }
}
