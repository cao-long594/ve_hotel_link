package cn.vetech.center.hotel.link.elong.orderdetail.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/2/19 16:25
 */
public class ElongOrderHotel {
    /**
     * 酒店ID
     */
    @JsonProperty(value = "HotelId")
    private String hotelId;
    /**
     * 酒店名称
     */
    @JsonProperty(value = "Name")
    private String name;
    /**
     * 酒店地址
     */
    @JsonProperty(value = "Address")
    private String address;
    /**
     * 酒店电话
     */
    @JsonProperty(value = "Phone")
    private String phone;
    /**
     * 城市名称
     */
    @JsonProperty(value = "CityName")
    private String cityName;
    /**
     * 酒店英文名    国际特有字段
     */
    @JsonProperty(value = "HotelEnglishName")
    private String hotelEnglishName;
    /**
     * 国家名称     国际特有字段
     */
    @JsonProperty(value = "HotelCountryName")
    private String hotelCountryName;
    /**
     * 维度	   国际特有字段
     */
    @JsonProperty(value = "HotelLatitude")
    private String hotelLatitude;
    /**
     * 经度	   国际特有字段
     */
    @JsonProperty(value = "HotelLongitude")
    private String hotelLongitude;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHotelEnglishName() {
       