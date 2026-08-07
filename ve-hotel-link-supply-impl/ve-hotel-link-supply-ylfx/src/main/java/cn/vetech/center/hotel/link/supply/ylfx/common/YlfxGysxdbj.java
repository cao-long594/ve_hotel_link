package cn.vetech.center.hotel.link.supply.ylfx.common;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class YlfxGysxdbj {
    /**
     * 发票类型：0. 平台开票，  1. 酒店开票
     */
    private Integer invoiceMode;
    /**
     * 酒店id
     */
    private String hotelId;
    /**
     * 接口版本
     */
    private String apiVersion;
    /**
     * V2 房型编码
     */
    private String roomCode;

    public Integer getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(Integer invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}
