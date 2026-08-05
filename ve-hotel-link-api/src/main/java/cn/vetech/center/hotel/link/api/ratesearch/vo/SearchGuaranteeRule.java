package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 担保
 *
 * @author SongJun 8963
 */
public class SearchGuaranteeRule implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 担保规则编号
     */
    @ApiModelProperty(value = "担保规则编号", dataType = "string")
    private String guranteeRuleId;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;
    /**
     * 日期类型
     */
    @ApiModelProperty(value = "日期类型", dataType = "string")
    private String dateType;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期", dataType = "string")
    private String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期", dataType = "string")
    private String endDate;
    /**
     * 周有效天数,一般为周一到周日都有效,判断日期符合日期段同时也要满足周设置的有效,周一对应为1,周二对应为2,依次类推
     */
    @ApiModelProperty(value = "周有效天数,一般为周一到周日都有效,判断日期符合日期段同时也要满足周设置的有效,周一对应为1,周二对应为2,依次类推", dataType = "string")
    private String weekSet;
    /**
     * 是否到店时间担保。False:为不校验到店时间 True:为需要校验到店时间
     */
    @ApiModelProperty(value = "是否到店时间担保,False:为不校验到店时间 True:为需要校验到店时间", dataType = "string")
    private String isTimeGuarantee;
    /**
     * 到店担保开始时间
     */
    @ApiModelProperty(value = "到店担保开始时间", dataType = "string")
    private String startTime;
    /**
     * 到店担保结束时间,用于IsTimeGuarantee ==true进行检查,[补充]当EndTime小于StartTime的时候,默认从StartTime到次日6点都需要担保
     */
    @ApiModelProperty(value = "到店担保结束时间,用于IsTimeGuarantee==true进行检查,[补充]当EndTime小于StartTime的时候,默认从StartTime到次日6点都需要担保", dataType = "string")
    private String endTime;
    /**
     * 到店担保的结束时间是否为第二天
     */
    @ApiModelProperty(value = "到店担保的结束时间是否为第二天", dataType = "string")
    private String isTomorrow;
    /**
     * 是否房量担保,False:为不校验房量条件 True:为校验房量条件
     */
    @ApiModelProperty(value = "是否房量担保,False:为不校验房量条件 True:为校验房量条件", dataType = "string")
    private String isAmountGuarantee;
    /**
     * 担保的房间数,预定几间房以上要担保,用于IsAmountGuarantee==true进行检查
     */
    @ApiModelProperty(value = "担保的房间数,预定几间房以上要担保,用于IsAmountGuarantee==true进行检查", dataType = "string")
    private String amount;
    /**
     * 担保类型,FirstNightCost为首晚房费担保, FullNightCost为全额房费担保
     */
    @ApiModelProperty(value = "担保类型,FirstNightCost为首晚房费担保,FullNightCost为全额房费担保", dataType = "string")
    private String guaranteeType;
    /**
     * 变更规则,担保规则取消变更规则:NoChange不允许变更取消,NeedSomeDay允许变更/取消,需在XX日YY时之前通知,NeedCheckinTime允许变更/取消,需在最早到店时间之前几小时通知,NeedCheckin24hour允许变更/取消,需在到店日期的24点之前几小时通知
     */
    @ApiModelProperty(value = "变更规则,担保规则取消变更规则:NoChange不允许变更取消,NeedSomeDay允许变更/取消,需在XX日YY时之前通知,NeedCheckinTime允许变更/取消,需在最早到店时间之前几小时通知,NeedCheckin24hour允许变更/取消,需在到店日期的24点之前几小时通知", dataType = "string")
    private String changeRule;
    /**
     * 日期参数,ChangeRule=NeedSomeDay时,对应规则2描述中,'允许变更/取消,需在XX日YY时之前通知'中的XX日,YY时
     */
    @ApiModelProperty(value = "日期参数,ChangeRule=NeedSomeDay时,对应规则2描述中,'允许变更/取消,需在XX日YY时之前通知'中的XX日,YY时", dataType = "string")
    private String day;
    /**
     * 时间参数,ChangeRule= NeedSomeDay时,对应规则2描述中, “允许变更/取消,需在XX日YY时之前通知” 中的XX日,YY时
     */
    @ApiModelProperty(value = "时间参数,ChangeRule=NeedSomeDay时,对应规则2描述中,允许变更/取消,需在XX日YY时之前通知中的XX日,YY时", dataType = "string")
    private String time;
    /**
     * 小时参数,ChangeRule=NeedCheckinTime时,对应规则3描述中'允许变更/取消,需在最早到店时间之前几小时通知'中的几小时ChangeRule=NeedCheckin24hour时,对应规则4描述中'允许变更/取消,需在到店日期的24点之前几小时通知'中的几小时
     */
    @ApiModelProperty(value = "小时参数,ChangeRule=NeedCheckinTime时,对应规则3描述中'允许变更/取消,需在最早到店时间之前几小时通知'中的几小时ChangeRule=NeedCheckin24hour时,对应规则4描述中'允许变更/取消,需在到店日期的24点之前几小时通知'中的几小时", dataType = "string")
    private String hour;