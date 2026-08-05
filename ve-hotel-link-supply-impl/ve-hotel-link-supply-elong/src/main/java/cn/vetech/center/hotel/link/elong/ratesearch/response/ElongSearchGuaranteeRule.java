package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchGuaranteeRule {
    /**
     * 担保规则编号
     */
    @JsonProperty("GuranteeRuleId")
    private String guranteeRuleId;
    /**
     * 描述
     */
    @JsonProperty("Description")
    private String description;
    /**
     * 日期类型
     * CheckInDay-入住日期
     * StayDay-在店日期
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
     * 周有效天数，一般为周一到周日都有效，判断日期符合日期段同时也要满足周设置的有效
     * 周一对应为1，周二对应为2，依次类推;逗号分隔
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 是否到店时间担保。False:为不校验到店时间 True:为需要校验到店时间
     */
    @JsonProperty("IsTimeGuarantee")
    private String isTimeGuarantee;
    /**
     * 到店担保开始时间
     */
    @JsonProperty("StartTime")
    private String startTime;
    /**
     * 到店担保结束时间。用于IsTimeGuarantee==true进行检查。
     * [补充]当EndTime小于StartTime的时候，默认从StartTime到次日6点都需要担保。
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 到店担保的结束时间是否为第二天 ; 0为当天，1为次日
     */
    @JsonProperty("IsTomorrow")
    private String isTomorrow;
    /**
     * 是否房量担保。False:为不校验房量条件 True:为校验房量条件
     */
    @JsonProperty("IsAmountGuarantee")
    private String isAmountGuarantee;
    /**
     * 担保的房间数,预定几间房以上要担保。用于IsAmountGuarantee ==true进行检查
     */
    @JsonProperty("Amount")
    private String amount;
    /**
     * 担保类型。FirstNightCost为首晚房费担保
     * FullNightCost为全额房费担保
     */
    @JsonProperty("GuaranteeType")
    private String guaranteeType;
    /**
     * 变更规则。担保规则取消变更规则：
     * NoChange、不允许变更取消
     * NeedSomeDay、允许变更/取消,需在XX日YY时之前通知
     * NeedCheckinTime、允许变更/取消,需在最早到店时间之前几小时通知
     * NeedCheckin24hour、允许变更/取消,需在到店日期的24点之前几小时通知
     */
    @JsonProperty("ChangeRule")
    private String changeRule;
    /**
     * 日期参数。ChangeRule= NeedSomeDay时，对应规则2描述中
     * “允许变更/取消,需在XX日YY时之前通知” 中的XX日，YY时
     */
    @JsonProperty("Day")
    private String day;
    /**
     * 时间参数。ChangeRule= NeedSomeDay时，对应规则2描述中
     * “允许变更/取消,需在XX日YY时之前通知” 中的XX日，YY时
     */
    @JsonProperty("Time")
    private String time;
    /**
     * 小时参数。ChangeRule= NeedCheckinTime时，对应规则3描述中 “
     * 允许变更/取消,需在最早到店时间之前几小时通知” 中的几小时 ChangeRule=
     * NeedCheckin24hour时，对应规则4描述中“
     * 允许变更/取消,需在到店日期的24点之前几小时通知” 中的几小时
     */
    @JsonProperty("Hour")
    private String hour;
    /**
     *
     */
    private String hotelCode;

    public String getGuranteeRuleId() {
        return guranteeRuleId;
    }

    public void setGuranteeRuleId(String guranteeRuleId) {
        this.guranteeRuleId = guranteeRuleId;
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

    p