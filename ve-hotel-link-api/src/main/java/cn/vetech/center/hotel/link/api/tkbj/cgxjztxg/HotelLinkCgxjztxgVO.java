package cn.vetech.center.hotel.link.api.tfxbj.cgxjdztxg;


import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xingyanyan
 * @since 2019/10/15 10:48
 */
@XmlRootElement(name = "request")
public class HotelLinkCgxjdztxgDTO extends LinkHotelDTO {
    /**
     * a
     */
    private String xjdid;

    /**
     * a
     */
    private String qxyy;

    /**
     * a
     */
    private String bjdid;

    /**
     * a
     */
    private String xjdzt;//询价单状态: 0.待报价 1.已报价待确认 2.已成交 3.已取消 4.未报价自动取消

    public String getXjdid() {
        return xjdid;
    }

    public void setXjdid(String xjdid) {
        this.xjdid = xjdid;
    }

    public String getQxyy() {
        return qxyy;
    }

    public void setQxyy(String qxyy) {
        this.qxyy = qxyy;
    }

    public String getBjdid() {
        return bjdid;
    }

    public void setBjdid(String bjdid) {
        this.bjdid = bjdid;
    }

    public String getXjdzt() {
        return xjdzt;
    }

    public void setXjdzt(String xjdzt) {
        this.xjdzt = xjdzt;
    }
}
