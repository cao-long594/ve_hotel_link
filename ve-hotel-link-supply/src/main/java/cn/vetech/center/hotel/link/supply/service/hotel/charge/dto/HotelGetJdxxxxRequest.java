package cn.vetech.center.hotel.link.supply.service.hotel.charge.dto;


import cn.vetech.center.hotel.link.supply.cps.base.OpenApiRequest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gaojin
 */
@XmlRootElement(name = "request")
public class HotelGetJdxxxxRequest extends OpenApiRequest {
    /**
     *
     */
    private String jdid;// 酒店ID
    /**
     *
     */
    private String infoType;// 0表示基本信息 1房型信息 2图片信息3联系人信息 4周边信息5外部酒店信息 多个逗号分隔 不传表示所有
    /**
     *
     */
    private String platform;// 请求平台来源 1网站 2手机客户端

    public String getJdid() {
        return jdid;
    }
    /**
     * 总公司编号
     */
    private String compid;
    /**
     * 酒店ID集合,多个用,分割
     */
    private String jdids;

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }
    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getJdids() {
        return jdids;
    }

    public void setJdids(String jdids) {
        this.jdids = jdids;
    }
}
