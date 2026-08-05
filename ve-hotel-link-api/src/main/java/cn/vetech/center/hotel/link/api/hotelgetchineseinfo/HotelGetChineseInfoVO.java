package cn.vetech.center.hotel.link.api.hotelgetchineseinfo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author xiaotengyu
 * @since 2023-05-31 14:01
 */
@XmlRootElement(name = "response")
public class HotelGetChineseInfoVO {

    /**
     * 酒店信息
     */
    private List<HotelChineseVO> hotelChineseVOS;

    public List<HotelChineseVO> getHotelChineseVOS() {
        return hotelChineseVOS;
    }

    public void setHotelChineseVOS(List<HotelChineseVO> hotelChineseVOS) {
        this.hotelChineseVOS = hotelChineseVOS;
    }
}
