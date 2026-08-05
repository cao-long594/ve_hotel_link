package cn.vetech.center.hotel.link.api.ratesearch.dto;

import java.util.List;

/**
 * @author vetech
 * @since 2023/11/10
 * vip会员信息
 */
public class VipParamInfoToCpsAsmsDTO extends VipBaseDTO {

    /**
     * 用户注册会员信息
     */
    private List<UserVipExtInfo> userVipExtInfoList;


    /**
     * 开启房源集合
     */
    private List<String> kqhyjFysList;


    public List<UserVipExtInfo> getUserVipExtInfoList() {
        return userVipExtInfoList;
    }

    public void setUserVipExtInfoList(List<UserVipExtInfo> userVipExtInfoList) {
        this.userVipExtInfoList = userVipExtInfoList;
    }

    public List<String> getKqhyjFysList() {
        return kqhyjFysList;
    }

    public void setKqhyjFysList(List<String> kqhyjFysList) {
        this.kqhyjFysList = kqhyjFysList;
    }
}
