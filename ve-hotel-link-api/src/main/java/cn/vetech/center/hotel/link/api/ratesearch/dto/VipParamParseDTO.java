package cn.vetech.center.hotel.link.api.ratesearch.dto;

public class VipParamParseDTO extends VipBaseDTO {
    /**
     * 用户注册会员信息
     */
    private UserVipExtInfo userVipExtInfo;

    public UserVipExtInfo getUserVipExtInfo() {
        return userVipExtInfo;
    }

    public void setUserVipExtInfo(UserVipExtInfo userVipExtInfo) {
        this.userVipExtInfo = userVipExtInfo;
    }
}