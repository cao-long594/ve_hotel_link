package cn.vetech.center.hotel.link.api.hotelgetcitycircle;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author chenming
 * @version [1.0, 六月 12, 2017]
 */
@XmlRootElement(name = "request")
public class HotelLinkGetCityCircleDTO extends LinkHotelDTO {
    /**
     * 城市编号
     */
    private String csbh;
    /**
     * 行政区编号
     */
    private String xzqbh;
    /**
     * 酒店id
     */
    private String jdid;
    /**
     * 经度
     */
    private String jd;
    /**
     * 纬度
     */
    private String wd;
    /**
     * 国家编号
     */
    private String gjbh;
    /**
     * 请求的数据类型，0：商圈，1：商业区，多值逗号分隔，默认0（兼容历史接口）
     */
    private String reqDataType = "0";

    public String getGjbh() {
        return gjbh;
    }

    public void setGjbh(String gjbh) {
        this.gjbh = gjbh;
    }

    public String getCsbh() {
        return csbh;
    }

    public void setCsbh(String csbh) {
        this.csbh = csbh;
    }

    public String getXzqbh() {
        return xzqbh;
    }

    public void setXzqbh(String xzqbh) {
        this.xzqbh = xzqbh;
    }

    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getReqDataType() {
        return reqDataType;
    }

    public void setReqDataType(String reqDataType) {
        this.reqDataType = reqDataType;
    }
}
