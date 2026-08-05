package cn.vetech.center.hotel.link.api.ratesearch.vo;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2024/12/5 19:31
 */
public class HotelBedInfo {
    /**
     * 床型ID
     */
    private String bedId;
    /**
     * 床型描述
     */
    private String description;
    /**
     * 床型明细列表
     */
    private List<HotelBedConfiguration> configurationList;
    /**
     * 拓展信息
     */
    private String expandInfo;

    public String getExpandInfo() {
        return expandInfo;
    }

    public void setExpandInfo(String expandInfo) {
        this.expandInfo = expandInfo;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HotelBedConfiguration> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<HotelBedConfiguration> configurationList) {
        this.configurationList = configurationList;
    }
}
