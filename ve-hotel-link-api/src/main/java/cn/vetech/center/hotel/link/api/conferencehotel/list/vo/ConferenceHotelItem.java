package cn.vetech.center.hotel.link.api.conferencehotel.list.vo;

import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelJdb;

import java.math.BigDecimal;

/**
 * 会议酒店信息
 *
 * @author luqs
 * @version v1.0
 */
public class ConferenceHotelItem extends HotelJdb {

    /**
     * 会场最大容纳人数
     */
    private String venueMaxCapacity;
    /**
     * 会场最大面积，如：100㎡
     */
    private String venueMaxArea;
    /**
     * 会场数量
     */
    private Integer venueNum;
    /**
     * 会场全天参考价
     */
    private BigDecimal venuePrice;

    public String getVenueMaxCapacity() {
        return venueMaxCapacity;
    }

    public void setVenueMaxCapacity(String venueMaxCapacity) {
        this.venueMaxCapacity = venueMaxCapacity;
    }

    public String getVenueMaxArea() {
        return venueMaxArea;
    }

    public void setVenueMaxArea(String venueMaxArea) {
        this.venueMaxArea = venueMaxArea;
    }

    public Integer getVenueNum() {
        return venueNum;
    }

    public void setVenueNum(Integer venueNum) {
        this.venueNum = venueNum;
    }

    public BigDecimal getVenuePrice() {
        return venuePrice;
    }

    public void setVenuePrice(BigDecimal venuePrice) {
        this.venuePrice = venuePrice;
    }
}
