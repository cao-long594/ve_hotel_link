package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vetech
 * @since 25/06/23
 */
@XmlRootElement(name = "request")
public class MarkAgreementDTO extends LinkHotelDTO {
    /**
     * 单体协议酒店字符串
     * HotelMarkAgreementItemDTO  转换后
     */
    private String jsonString;

    /**
     * 房源名称
     */
    private String fymc;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }
}
