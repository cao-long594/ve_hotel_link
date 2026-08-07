package cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.response;

import java.util.List;

/**
 * 易旅分销 V2 酒店静态信息
 *
 * @author 6161
 * @date 2026/08/07
 */
public class YlfxV2HotelInfosHotel {
    /**
     * 酒店编码
     */
    private String hotelCode;
    /**
     * 酒店中文名称
     */
    private String hotelNameCn;
    /**
     * 酒店英文名称
     */
    private String hotelNameEn;
    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 酒店电话
     */
    private String telephone;
    /**
     * 酒店地址
     */
    private String address;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 销售状态：1 启用，2 停用
     */
    private Integer saleStatus;
    /**
     * 房型列表
     */
    private List<YlfxV2HotelInfosRoom> rooms;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelNameCn() {
        return hotelNameCn;
    }

    public void setHotelNameCn(String hotelNameCn) {
        this.hotelNameCn = hotelNameCn;
    }

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public List<YlfxV2HotelInfosRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<YlfxV2HotelInfosRoom> rooms) {
        this.rooms = rooms;
    }
}
