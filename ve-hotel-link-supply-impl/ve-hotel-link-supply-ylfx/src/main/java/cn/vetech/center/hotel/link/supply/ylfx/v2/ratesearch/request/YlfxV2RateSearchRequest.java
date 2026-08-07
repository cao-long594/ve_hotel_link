package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.request;

import java.util.List;

/**
 * 易旅分销 V2 可订产品查询请求
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchRequest {
    /**
     * 客户编码
     */
    private String customerCode;
    /**
     * 酒店编码
     */
    private String hotelCode;
    /**
     * 房型编码，产品可订校验必填
     */
    private String roomCode;
    /**
     * 报价编码，产品可订校验必填
     */
    private String rateCode;
    /**
     * 入住日期
     */
    private String checkIn;
    /**
     * 离店日期
     */
    private String checkOut;
    /**
     * 房间数
     */
    private Integer roomCount;
    /**
     * 入住人房间信息
     */
    private List<YlfxV2RateSearchPaxRoom> paxRooms;
    /**
     * 入住客人国籍
     */
    private String country;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public List<YlfxV2RateSearchPaxRoom> getPaxRooms() {
        return paxRooms;
    }

    public void setPaxRooms(List<YlfxV2RateSearchPaxRoom> paxRooms) {
        this.paxRooms = paxRooms;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
