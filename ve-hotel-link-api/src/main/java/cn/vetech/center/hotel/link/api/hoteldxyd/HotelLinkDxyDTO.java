package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 酒店单体协议酒店对象
 * @author 1
 */
@XmlRootElement(name = "request")
public class HotelLinkDtxyDTO extends LinkHotelDTO {
    /**
     * 单体协议酒店字符串
     */
    private String jsonString;
    /**
     * 价格来源
     */
    private String jgly;
    /**
     * 总公司
     */
    private String compid;

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getJgly() {
        return jgly;
    }

    public void setJgly(String jgly) {
        this.jgly = jgly;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
