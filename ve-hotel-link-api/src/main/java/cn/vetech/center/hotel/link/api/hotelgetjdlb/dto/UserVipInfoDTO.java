package cn.vetech.center.hotel.link.api.hotelgetjdlb.dto;

import java.io.Serializable;

/**
 * 用户会员信息
 *
 * @author chengwanshan
 * @since 2021/12/30 14:10
 */
public class UserVipInfoDTO implements Serializable {
    /**
     * 房源编号， 如：31200830
     */
    private String supplyNo;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 会员id，即房源商的会员id
     */
    private String vipId;

    public String getSupplyNo() {
        return supplyNo;
    }

    public void setSupplyNo(String supplyNo) {
        this.supplyNo = supplyNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }
}
