package cn.vetech.center.hotel.link.supply.service.hotel.charge.vo;

import cn.vetech.center.hotel.link.supply.cps.base.OpenApiResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author gaojin
 */
@XmlRootElement(name = "response")
public class HotelGetJdxxxxResponse extends OpenApiResponse {
    /**
     *
     */
    private HotelJdb hotel;

    /**
     *
     */
    private List<HotelJdb> hotelList;

    @XmlElement(name = "hotel")
    public HotelJdb getHotel() {
        return this.hotel;
    }

    public void setHotel(HotelJdb hotel) {
        this.hotel = hotel;
    }

    @XmlElementWrapper(name = "hotelList")
    @XmlElement(name = "hotel")
    public List<HotelJdb> getHotelList() {
        return this.hotelList;
    }

    public void setHotelList(List<HotelJdb> hotelList) {
        this.hotelList = hotelList;
    }
}
