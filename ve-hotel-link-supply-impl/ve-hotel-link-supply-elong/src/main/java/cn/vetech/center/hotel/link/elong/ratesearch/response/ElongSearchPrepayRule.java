package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchPrepayRule {
    /**
     * 规则编码
     */
    @JsonProperty("PrepayRuleId")
    private String prepayRuleId;
    /**
     * 描述
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 日期类型
     * CheckInDay：入住日期
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
     * 周有效设置
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 变更规则
     * PrepayNoChange：不允许变更取消
     * PrepayNeedSomeDay：在到店当日24点前Hour小时前按规则看是否可以免费变更取消(一般是不收罚金)，
     * 在Hour和Hour2之间按规则存在罚金，Hour2之后不能变更取消
     * PrepayNeedOneTime：在约定日期时间点(DateNum + Time)前可以免费变更取消
     */
    @JsonProperty("ChangeRule")
    private String changeRule;
    /**
     * 具体取消时间日期部分，用于 PrepayNeedOneTime
     */
    @JsonProperty("DateNum")
    private String dateNum;
    /**
     * 具体取消时间小时部分，用于 PrepayNeedOneTime
     */
    @JsonProperty("Time")
    private String time;
    /**
     * 在变更时间点前是否扣费，用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)
     */
    @JsonProperty("DeductFeesBefore")
    private String deductFeesBefore;
    /**
     * 时间点前扣费的金额或比例，用于PrepayNeedSomeDay的Hour前扣款类型(一般不收罚金)
     */
    @JsonProperty("DeductNumBefore")
    private String deductNumBefore;
    /**
     * 时间点前扣款类型，Money：金额 Percent：比例 ​FristNight：首晚
     */
    @JsonProperty("CashScaleFirstBefore")
    private String cashScaleFirstBefore;
    /**
     * 时间点后扣款类型，Money：金额 Percent：比例 FristNight：首晚
     */
    @JsonProperty("CashScaleFirstAfter")
    private String cashScaleFirstAfter;
    /**
     * 在变更时间点后是否扣费，用于 PrepayNeedSomeDay的Hour到Hour2之间的扣款类型
     */
    @JsonProperty("DeductFeesAfter")
    private String deductFeesAfter;
    /**
     * 时间点后扣费的金额或比例，用于 PrepayNeedSomeDay的Hour到Hour2之间的扣款类型
     */
    @JsonProperty("DeductNumAfter")
    private String deductNumAfter;
    /**
     * 第一阶段提前几小时，用于 PrepayNeedSomeDay
     */
    @JsonProperty("Hour")
    private String hour;
    /**
     * 第二阶段提前几小时，用于 PrepayNeedSomeDay
     */
    @JsonProperty("Hour2")
    private String hour2;

    public String getPrepayRuleId() {
        return prepayRuleId;
    }

    public void setPrepayRuleId(String prepayRuleId) {
        this.prepayRuleId = prepayRuleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWeekSet() {
        return weekSet;
    }

    public void setWeekSet(String weekSet) {
        this.weekSet = weekSet;
    }

    public String getChangeRule() {
        return changeRule;
    }

    public void setChangeRule(String changeRule) {
        this.changeRule = changeRule;
    }

    public String getDateNum() {
        return dateNum;
    }

    public void setDateNum(String dateNum) {
        this.dateNum = dateNum;
    }

    pub