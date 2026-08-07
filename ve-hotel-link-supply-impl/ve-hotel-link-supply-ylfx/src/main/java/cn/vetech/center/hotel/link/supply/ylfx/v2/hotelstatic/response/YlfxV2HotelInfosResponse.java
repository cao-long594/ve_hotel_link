package cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.response;

import java.util.List;

/**
 * 易旅分销 V2 酒店静态信息响应。
 *
 * @author 6161
 * @date 2026/08/07
 */
public class YlfxV2HotelInfosResponse {
    /** 响应编码 */
    private String code;
    /** 响应消息 */
    private String message;
    /** 酒店静态信息 */
    private List<HotelInfo> data;

    /** 酒店信息 */
    public static class HotelInfo {
        private String hotelCode;
        private String hotelNameCn;
        private String hotelNameEn;
        private String countryName;
        private String provinceCode;
        private String provinceName;
        private String cityCode;
        private String cityName;
        private String telephone;
        private String address;
        private String longitude;
        private String latitude;
        private Integer saleStatus;
        private List<RoomInfo> rooms;

        public String getHotelCode() { return hotelCode; }
        public void setHotelCode(String hotelCode) { this.hotelCode = hotelCode; }
        public String getHotelNameCn() { return hotelNameCn; }
        public void setHotelNameCn(String hotelNameCn) { this.hotelNameCn = hotelNameCn; }
        public String getHotelNameEn() { return hotelNameEn; }
        public void setHotelNameEn(String hotelNameEn) { this.hotelNameEn = hotelNameEn; }
        public String getCountryName() { return countryName; }
        public void setCountryName(String countryName) { this.countryName = countryName; }
        public String getProvinceCode() { return provinceCode; }
        public void setProvinceCode(String provinceCode) { this.provinceCode = provinceCode; }
        public String getProvinceName() { return provinceName; }
        public void setProvinceName(String provinceName) { this.provinceName = provinceName; }
        public String getCityCode() { return cityCode; }
        public void setCityCode(String cityCode) { this.cityCode = cityCode; }
        public String getCityName() { return cityName; }
        public void setCityName(String cityName) { this.cityName = cityName; }
        public String getTelephone() { return telephone; }
        public void setTelephone(String telephone) { this.telephone = telephone; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getLongitude() { return longitude; }
        public void setLongitude(String longitude) { this.longitude = longitude; }
        public String getLatitude() { return latitude; }
        public void setLatitude(String latitude) { this.latitude = latitude; }
        public Integer getSaleStatus() { return saleStatus; }
        public void setSaleStatus(Integer saleStatus) { this.saleStatus = saleStatus; }
        public List<RoomInfo> getRooms() { return rooms; }
        public void setRooms(List<RoomInfo> rooms) { this.rooms = rooms; }
    }

    /** 房型信息 */
    public static class RoomInfo {
        private String roomCode;
        private String roomNameCn;
        private String roomNameEn;

        public String getRoomCode() { return roomCode; }
        public void setRoomCode(String roomCode) { this.roomCode = roomCode; }
        public String getRoomNameCn() { return roomNameCn; }
        public void setRoomNameCn(String roomNameCn) { this.roomNameCn = roomNameCn; }
        public String getRoomNameEn() { return roomNameEn; }
        public void setRoomNameEn(String roomNameEn) { this.roomNameEn = roomNameEn; }
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<HotelInfo> getData() { return data; }
    public void setData(List<HotelInfo> data) { this.data = data; }
}
