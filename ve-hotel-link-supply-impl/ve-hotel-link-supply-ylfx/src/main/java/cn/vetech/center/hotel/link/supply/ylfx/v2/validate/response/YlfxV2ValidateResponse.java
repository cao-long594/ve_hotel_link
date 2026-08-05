package cn.vetech.center.hotel.link.supply.ylfx.v2.validate.response;

import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response.YlfxV2RateSearchResponse;

/**
 * 易旅分销 V2 产品可订校验响应
 *
 * @author 6161
 * @date 2026/08/05
 */
public class YlfxV2ValidateResponse {
    /** 响应编码 */
    private String code;
    /** 响应消息 */
    private String message;
    /** 校验结果 */
    private Data data;
    public static class Data {
        /** 校验后的房型与报价 */
        private Room room;
        public Room getRoom() { return room; }
        public void setRoom(Room room) { this.room = room; }
    }
    public static class Room {
        /** 房型编码 */
        private String roomCode;
        /** 校验后的报价 */
        private YlfxV2RateSearchResponse.Rate rate;
        public String getRoomCode() { return roomCode; }
        public void setRoomCode(String roomCode) { this.roomCode = roomCode; }
        public YlfxV2RateSearchResponse.Rate getRate() { return rate; }
        public void setRate(YlfxV2RateSearchResponse.Rate rate) { this.rate = rate; }
    }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Data getData() { return data; }
    public void setData(Data data) { this.data = data; }
}
