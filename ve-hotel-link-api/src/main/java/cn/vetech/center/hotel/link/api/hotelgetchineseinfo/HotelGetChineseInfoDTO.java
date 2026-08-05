package cn.vetech.center.hotel.link.api.hotelgetchineseinfo;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xiaotengyu
 * @since 2023-05-31 13:55
 */
@XmlRootElement(name = "request")
public class HotelGetChineseInfoDTO extends LinkHotelDTO {

    /**
     * 酒店名称
     */
    private String keyword;
    /**
     * 语种
     */
    private String veLanguage;
    /**
     * 国内酒店，国际酒店；1：国内；0：国际
     */
    private String gngj;

    @Override
    public String getGngj() {
        return gngj;
    }

    @Override
    public void setGngj(String gngj) {
        this.gngj = gngj;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getVeLanguage() {
        return veLanguage;
    }

    public void setVeLanguage(String veLanguage) {
        this.veLanguage = veLanguage;
    }
}
