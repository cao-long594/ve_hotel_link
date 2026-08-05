package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class ProductData {
    /**
     * 酒店ID
     */
    private String hotelId;
    /**
     * 房型ID
     */
    private String roomtypeId;
    /**
     * 房型名称
     */
    private String roomtypeName;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 早餐数量
     */
    private Integer breakfastNumber;
    /**
     * 是否可取消： 0. 不可取消 1. 可取消
     */
    private Integer cancelable;
    /**
     * 提前取消天数
     */
    private Integer cancelAdvancedDays;
    /**
     * 提前取消时间，如"18:00"
     */
    private String cancelAdvancedTime;
    /**
     * 提前预订天数，如：0表示可当天预订，1表示最少提前1天预订
     */
    private Integer bookAdvancedDays;
    /**
     * 每天可预订开始时间，如：“9:00"，为空，则没有限制
     */
    private String bookStartTime;
    /**
     * 每天可预订结束时间，如：“20:00"，为空则没有限制
     */
    private String bookEndTime;
    /**
     * 发票类型：0. 平台开票，  1. 酒店开票
     */
    private Integer invoiceMode;
    /**
     * 是否有效： 0：无效 1. 有效
     */
    private Integer active;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(String roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String produc