package cn.vetech.center.hotel.link.supply.ylfx.v2.orderbook.request;

import java.util.List;

/**
 * 易旅分销 V2 创建订单请求
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2OrderBookRequest {
    /**
     * 客户编码
     */
    private String customerCode;
    /**
     * 渠道端订单号，幂等键
     */
    private String cusOrderNo;
    /**
     * 酒店编码
     */
    private String hotelCode;
    /**
     * 房型编码
     */
    private String roomCode;
    /**
     * 报价编码
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
    private String roomCount;
    /**
     * 总价
     */
    private String totalPrice;
    /**
     * 币种
     */
    private String currencyCode;
    /**
     * 联系人姓
     */
    private String contactLastName;
    /**
     * 联系人名
     */
    private String contactFirstName;
    /**
     * 给酒店备注
     */
    private String remark;
    /**
     * 入住人房间信息
     */
    private List<YlfxV2OrderBookPaxRoom> paxNameRooms;
    /**
     * 每日价格列表
     */
    private List<YlfxV2OrderBookDailyPrice> dailyPriceList;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCusOrderNo() {
        return cusOrderNo;
    }

    public void setCusOrderNo(String cusOrderNo) {
        this.cusOrderNo = cusOrderNo;
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

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<YlfxV2OrderBookPaxRoom> getPaxNameRooms() {
        return paxNameRooms;
    }

    public void setPaxNameRooms(List<YlfxV2OrderBookPaxRoom> paxNameRooms) {
        this.paxNameRooms = paxNameRooms;
    }

    public List<YlfxV2OrderBookDailyPrice> getDailyPriceList() {
        return dailyPriceList;
    }

    public void setDailyPriceList(List<YlfxV2OrderBookDailyPrice> dailyPriceList) {
        this.dailyPriceList = dailyPriceList;
    }
}
