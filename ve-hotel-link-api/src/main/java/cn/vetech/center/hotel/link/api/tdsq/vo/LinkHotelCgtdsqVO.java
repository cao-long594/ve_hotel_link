package cn.vetech.center.hotel.link.api.tdsq.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author xingyanyan
 */
@XmlRootElement(name = "response")
public class LinkHotelCgtdsqVO extends LinkHotelVO {
    private String tdbh; //退单编号

    public String getTdbh() {
        return tdbh;
    }

    public void setTdbh(String tdbh) {
        this.tdbh = tdbh;
    }
}
