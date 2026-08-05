package cn.vetech.center.hotel.link.api.hotelgetjdgjhxx;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class HotelLinkGetJdgjhxxVO {

    /**
     * 酒店国际化信息
     */
    private List<HotelGjhVO> globals;

    public List<HotelGjhVO> getGlobals() {
        return globals;
    }

    public void setGlobals(List<HotelGjhVO> globals) {
        this.globals = globals;
    }

}
