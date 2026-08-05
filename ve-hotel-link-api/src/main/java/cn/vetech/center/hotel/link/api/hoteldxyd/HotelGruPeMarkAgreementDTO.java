package cn.vetech.center.hotel.link.api.hoteldtxyjd;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vetech
 * @since 25/06/23
 */

@XmlRootElement(name = "request")
public class HotelGrupReMarkAgreementDTO extends LinkHotelDTO{

    /**
     * 根据传入的集团协议的房源编号
     */
    private List<String> hotelGroupList;


    public List<String> getHotelGroupList() {
        return hotelGroupList;
    }

    public void setHotelGroupList(List<String> hotelGroupList) {
        this.hotelGroupList = hotelGroupList;
    }
}
