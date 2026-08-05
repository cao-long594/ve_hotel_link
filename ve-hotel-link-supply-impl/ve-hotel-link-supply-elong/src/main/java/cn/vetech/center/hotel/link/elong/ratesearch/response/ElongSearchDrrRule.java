package cn.vetech.center.hotel.link.elong.ratesearch.response;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchDrrRule {
    /**
     * 促销规则编号
     */
    @JsonProperty("DrrRuleId")
    private String drrRuleId;
    /**
     * 产品促销规则类型代码
     */
    @JsonProperty("TypeCode")
    private String typeCode;
    /**
     * 描述
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 日期类型。CheckInDay:入住日期 StayDay:在店日期 BookDay:预订日期
     */
    @JsonProperty("DateType")
    private String dateType;
    /**
     * 促销生效开始日期
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 促销生效结束日期
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 提前几天
     */
    @JsonProperty("DayNum")
    private String dayNum;
    /**
     * 连住几天
     */
    @JsonProperty("CheckInNum")
    private String checkInNum;
    /**
     * 每连住几晚
     */
    @JsonProperty("EveryCheckInNum")
    private String everyCheckInNum;
    /**
     * 最后几天
     */
    @JsonProperty("LastDayNum")
    private String lastDayNum;
    /**
     * 第几晚及以后优惠
     */
    @JsonProperty("WhichDayNum")
    private String whichDayNum;
    /**
     * 按金额或按比例来优惠。Cash-金额 Money-金额 Percent-比例 Scale-比例
     */
    @JsonProperty("CashScale")
    private String cashScale;
    /**
     * 按金额或比例优惠的数值。当CashScale为Percent时，该值保存的为百分数，例如30%
     */
    @JsonProperty("DeductNum")
    private String deductNum;
    /**
     * 星期有效设置。日期符合Weekset中的周设置，才享受 feetype所对应的价格
     * 仅DRRStayWeekDay和DRRCheckInWeekDay的时候使用
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 价格类型。WeekendFee-周末价格 WeekdayFee-平日价格
     */
    @JsonProperty("FeeType")
    private String feeType;

    public String getDrrRuleId() {
        return drrRuleId;
    }

    public void setDrrRuleId(String drrRuleId) {
        this