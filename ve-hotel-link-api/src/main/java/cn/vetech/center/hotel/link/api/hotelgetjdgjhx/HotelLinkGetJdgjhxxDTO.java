package cn.vetech.center.hotel.link.api.hotelgetjdgjhxx;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "request")
public class HotelLinkGetJdgjhxxDTO  extends LinkHotelDTO {

    /**
     * 酒店ID 列表
     */
    private List<String> jdids;

    /**
     * 是否查询所有的国际化信息；true：返回所有国际化信息；false：只返回酒店名称和酒店dz
     */
    private Boolean fullData;

    public List<String> getJdids() {
        return jdids;
    }

    public void setJdids(List<String> jdids) {
        this.jdids = jdids;
    }

    public Boolean getFullData() {
        return fullData;
    }

    public void setFullData(Boolean fullData) {
        this.fullData = fullData;
    }
}
