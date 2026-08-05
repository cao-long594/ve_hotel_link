package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/10/15 14:32
 */
public class ElongPrepayRule {
    /**
     * 描述	        String(255)	N
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 日期类型	    Enum	N	CheckInDay：入住日期（该字段后期下线，可以不用判断）
     */
    @JsonProperty("DateType")
    private String dateType;
    /**
     * 开始日期	    Date	Y	使用离线数据模式需要判断
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束日期	    Date	Y
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * 周有效设置	String(20)	Y
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 变更规则	    Enum	N	PrepayNoChange：不允许变更取消；PrepayNeedSomeDay：在到店当日24点前Hour小时前按规则看是否可以免费变更取消（一般是不收罚金），在Hour和Hour2之间按规则存在罚金，Hour2之后不能变更取消；PrepayNeedOneTime：在约定日期时间点(DateNum + Time)前可以免费变更取消
     */
    @JsonProperty("ChangeRule")
    private String changeRule;
    /**
     * 第一阶段提前的几小时	Int	Y	用于PrepayNeedSomeDay
     */
    @JsonProperty("Hour")
    private String hour;
    /**
     * 第二阶段提前的几小时	Int	Y
     */
    @JsonProperty("Hour2")
    private String hour2;
    /**
     * 具体取消时间日期部分	date	Y	用于PrepayNeedOneTime
     */
    @JsonProperty("DateNum")
    private String dateNum;
    /**
     * 具体取消时间小时部分	time	Y
     */
    @JsonProperty("Time")
    private String time;
    /**
     * 在变更时间点前是否扣费	Int	Y	用于 PrepayNeedSomeDay的Hour前扣款类型（一般不收罚金）。DeductFeesBefore为1表示扣款，0表示不扣款。
     */
    @JsonProperty("DeductFeesBefore")
    private String deductFeesBefore;
    /**
     * 时间点前扣费的金额或比例	Decimal	Y
     */
    @JsonProperty("DeductNumBefore")
    private String deductNumBefore;
    /**
     * 时间点后扣款类型	Enum	Y	Money：金额    Percent：比例   FristNight：首晚
     */
    @JsonProperty("CashScaleFirstAfter")
    private String cashScaleFirstAfter;
    /**
    * 在变更时间点后是否扣费	Int	Y	用于 PrepayNeedSomeDay的Hour到Hour2之间的扣款类型。DeductFeesAfter为1表示扣款，0表示不扣款。如果CashScaleFirstAfter为FristNight，则返回-1，没有意义
     */
    @JsonProperty("DeductFeesAfter")
    private String deductFeesAfter;
    /**
     * 时间点后扣费的金额或比例	Decimal	Y
     */
    @JsonProperty("DeductNumAfter")
    private String deductNumAfter;
    /**
     * 时间点前扣款类型	Enum	Y	Money：金额        Percent：比例      FristNight：首晚
     */
    @JsonProperty("CashScaleFirstBefore")
    private String cashScaleFirstBefore;
