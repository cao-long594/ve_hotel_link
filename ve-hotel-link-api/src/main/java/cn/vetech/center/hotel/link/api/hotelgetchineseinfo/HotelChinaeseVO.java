package cn.vetech.center.hotel.link.api.hotelgetchineseinfo;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2023-05-31 14:01
 */
public class HotelChineseVO {

    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 酒店地址
     */
    private String address;

    /**
     * 国际化信息
     */
    private List<HotelGlobalVO> globalVOS;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HotelGlobalVO> getGlobalVOS() {
        return globalVOS;
    }

    public void setGlobalVOS(List<HotelGlobalVO> globalVOS) {
        this.globalVOS = globalVOS;
    }
}
