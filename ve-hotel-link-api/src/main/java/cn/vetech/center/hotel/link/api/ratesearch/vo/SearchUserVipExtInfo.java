package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * @author chengwanshan
 * @since 2022/1/5 17:15
 */
public class SearchUserVipExtInfo {
    /**
     * 房源编号， 如：31200830（美团）
     */
    private String fybh;
    /**
     * 会员id
     */
    private String ydrUid;
    /**
     * 手机号
     */
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public String getYdrUid() {
        return ydrUid;
    }

    public void setYdrUid(String ydrUid) {
        this.ydrUid = ydrUid;
    }
}
