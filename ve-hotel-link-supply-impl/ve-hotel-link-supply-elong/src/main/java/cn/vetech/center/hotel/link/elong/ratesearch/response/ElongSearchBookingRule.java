package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchBookingRule {
    /**
     * 规则类型
     * NeedNationality、务必提供客人国籍
     * PerRoomPerName、您预订了N间房，请您提供不少于N的入住客人姓名
     * ForeignerNeedEnName、此酒店要求外宾务必留英文拼写
     * RejectCheckinTime、几点到几点酒店不接受预订 , 此处校验的是下单时的预订时间
     * NeedPhoneNo、务必提供客人手机号(请加在联系人结点Contact上)
     */
    @JsonProperty("TypeCode")
    private String typeCode;
    /**
     * 预订规则编号。RatePlan.BookingRuleIds将与此关联
     */
    @JsonProperty("BookingRuleId")
    private String bookingRuleId;
    /**
     * 描述
     */
    @JsonProperty("Description")
    private String description;
    /**
     * BookDay –预订日期（订单的创建日期）
     */
    @JsonProperty("DateType")
    private String dateType;
    /**
     * 开始日期
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束日期
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 每天开始时间
     */
    @JsonProperty("StartHour")
    private String startHour;
    /**
     * 每天结束日期。针对日期段内每天生效, 当TypeCode
     * 为4时表示StartHour到EndHour 酒店不接受预订
     */
    @JsonProperty("EndHour")
    private String endHour;
    /**
     *
     */
    @JsonProperty("roomTypeIds")
    private String roomTypeIds;
    /**
     *
     */
    private String hotelCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getBookingRuleId() {
        return bookingRuleId;
    }

    public void setBookingRuleId(String bookingRuleId) {
        this.bookingRuleId = bookingRuleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateType() 