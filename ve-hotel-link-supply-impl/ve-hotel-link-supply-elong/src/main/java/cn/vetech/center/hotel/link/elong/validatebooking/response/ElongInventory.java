package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 15:00
 */
public class ElongInventory {
    /**
     * 酒店ID	String(8)	N	这几个属性是业务主键。
     */
    @JsonProperty("HotelID")
    private String hotelID;
    /**
     * 房型ID	String(10)	N	Date表示的是某天的库存。
     */
    @JsonProperty("RoomTypeId")
    private String roomTypeId;
    /**
     * 酒店编码	String(8)	N	HotelCode关联搜索接口的RatePlan.HotelCode
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 库存时间	Date	N
     */
    @JsonProperty("Date")
    private String date;
    /**
     * 库存状态	Boolean	N	False-不可用 True-可用
     */
    @JsonProperty("Status")
    private String status;
    /**
     * 库存数量	Int	N	剩余的可知库存数量
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 超售状态	Int	N	0---可超售，1—不可超售。可超售的时候即使Amount等于0也是可以继续销售的。
     */
    @JsonProperty("OverBooking")
    private String overBooking;
    /**
     * 可用开始日期	Date	N	库存可用开始日期
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 可用结束日期	Date	N	库存可用结束日期
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 可用开始时间	Time	N	预订当天库存，须校验库存可用开始时间
     */
    @JsonProperty("StartTime")
    private String startTime;
    /**
     * 可用结束时间	Time	N	预订当天库存，须校验库存可用结束时间; 若为23:59:59则为无限制；
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 当天库存是否支持即时确认	Boolean	Y	V1.22新增,具体使用请见接口使用说明。
     */
    @JsonProperty("IsInstantConfirm")
    private String isInstantConfirm;
    /**
     * 预订当天即时确认可用开始时间	Time	Y	注意：此三个字段已无效，是否即时确认请以创建订单接口的返回值或者订单详情中的即时确认相关字段为准。
     */
    @JsonProperty("IC_BeginTime")
    private String iC_BeginTime;
    /**
     * 预订当天即时确认可用结束时间	Time	Y
     */
    @JsonProperty("IC_EndTime")
    private String iC_EndTime;

    public String getHotelID() {
