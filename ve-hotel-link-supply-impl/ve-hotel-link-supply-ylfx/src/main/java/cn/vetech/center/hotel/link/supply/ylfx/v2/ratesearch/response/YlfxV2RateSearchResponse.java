package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response;

import java.util.List;

/**
 * 易旅分销 V2 可订产品查询响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2RateSearchResponse {
    /** 响应编码 */
    private String code;
    /** 响应消息 */
    private String message;
    /** 可订产品数据 */
    private Data data;
    public static class Data {
        private String hotelCode;
        private String currencyCode;
        private List<Room> rooms;
        public String getHotelCode() { return hotelCode; }
        public void setHotelCode(String hotelCode) { this.hotelCode = hotelCode; }
        public String getCurrencyCode() { return currencyCode; }
        public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
        public List<Room> getRooms() { return rooms; }
        public void setRooms(List<Room> rooms) { this.rooms = rooms; }
    }
    public static class Room {
        /** 酒店编码 */
        private String hotelCode;
        private String roomCode;
        private String roomNameCn;
        private String roomNameEn;
        private Integer maxOccupancy;
        private Integer maxAdultOccupancy;
        private List<Rate> rates;
        public String getHotelCode() { return hotelCode; }
        public void setHotelCode(String hotelCode) { this.hotelCode = hotelCode; }
        public String getRoomCode() { return roomCode; }
        public void setRoomCode(String roomCode) { this.roomCode = roomCode; }
        public String getRoomNameCn() { return roomNameCn; }
        public void setRoomNameCn(String roomNameCn) { this.roomNameCn = roomNameCn; }
        public String getRoomNameEn() { return roomNameEn; }
        public void setRoomNameEn(String roomNameEn) { this.roomNameEn = roomNameEn; }
        public Integer getMaxOccupancy() { return maxOccupancy; }
        public void setMaxOccupancy(Integer maxOccupancy) { this.maxOccupancy = maxOccupancy; }
        public Integer getMaxAdultOccupancy() { return maxAdultOccupancy; }
        public void setMaxAdultOccupancy(Integer maxAdultOccupancy) { this.maxAdultOccupancy = maxAdultOccupancy; }
        public List<Rate> getRates() { return rates; }
        public void setRates(List<Rate> rates) { this.rates = rates; }
    }
    public static class Rate {
        private String rateCode;
        private String rateNameCn;
        private String rateNameEn;
        private Integer instantConfirm;
        private String currencyCode;
        private String totalPrice;
        private String totalTaxAndFee;
        private List<DailyPrice> dailyPriceList;
        private List<CancelPolicy> cancelPolicies;
        private Meal meal;
        private List<HotelFee> hotelFees;
        public String getRateCode() { return rateCode; }
        public void setRateCode(String rateCode) { this.rateCode = rateCode; }
        public String getRateNameCn() { return rateNameCn; }
        public void setRateNameCn(String rateNameCn) { this.rateNameCn = rateNameCn; }
        public String getRateNameEn() { return rateNameEn; }
        public void setRateNameEn(String rateNameEn) { this.rateNameEn = rateNameEn; }
        public Integer getInstantConfirm() { return instantConfirm; }
        public void setInstantConfirm(Integer instantConfirm) { this.instantConfirm = instantConfirm; }
        public String getCurrencyCode() { return currencyCode; }
        public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
        public String getTotalPrice() { return totalPrice; }
        public void setTotalPrice(String totalPrice) { this.totalPrice = totalPrice; }
        public String getTotalTaxAndFee() { return totalTaxAndFee; }
        public void setTotalTaxAndFee(String totalTaxAndFee) { this.totalTaxAndFee = totalTaxAndFee; }
        public List<DailyPrice> getDailyPriceList() { return dailyPriceList; }
        public void setDailyPriceList(List<DailyPrice> dailyPriceList) { this.dailyPriceList = dailyPriceList; }
        public List<CancelPolicy> getCancelPolicies() { return cancelPolicies; }
        public void setCancelPolicies(List<CancelPolicy> cancelPolicies) { this.cancelPolicies = cancelPolicies; }
        public Meal getMeal() { return meal; }
        public void setMeal(Meal meal) { this.meal = meal; }
        public List<HotelFee> getHotelFees() { return hotelFees; }
        public void setHotelFees(List<HotelFee> hotelFees) { this.hotelFees = hotelFees; }
    }
    public static class DailyPrice {
        private String date;
        private String price;
        private String currencyCode;
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
        public String getCurrencyCode() { return currencyCode; }
        public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    }
    public static class CancelPolicy {
        private String from;
        private String amount;
        public String getFrom() { return from; }
        public void setFrom(String from) { this.from = from; }
        public String getAmount() { return amount; }
        public void setAmount(String amount) { this.amount = amount; }
    }
    public static class Meal {
        private Integer breakfastCount;
        private Integer lunchCount;
        private Integer dinnerCount;
        public Integer getBreakfastCount() { return breakfastCount; }
        public void setBreakfastCount(Integer breakfastCount) { this.breakfastCount = breakfastCount; }
        public Integer getLunchCount() { return lunchCount; }
        public void setLunchCount(Integer lunchCount) { this.lunchCount = lunchCount; }
        public Integer getDinnerCount() { return dinnerCount; }
        public void setDinnerCount(Integer dinnerCount) { this.dinnerCount = dinnerCount; }
    }
    public static class HotelFee {
        private String type;
        private String amount;
        private String currencyCode;
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getAmount() { return amount; }
        public void setAmount(String amount) { this.amount = amount; }
        public String getCurrencyCode() { return currencyCode; }
        public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}
