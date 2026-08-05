package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 预定规则
 *
 * @author SongJun 8963
 */
public class SearchBookingRule implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 规则类型
     */
    @ApiModelProperty(value = "规则类型", dataType = "string")
    private String typeCode;
    /**
     * 预订规则编号
     */
    @ApiModelProperty(value = "预订规则编号", dataType = "string")
    private String bookingRuleId;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;
    /**
     * BookDay–预订日期（订单的创建日期）
     */
    @ApiModelProperty(value = "BookDay–预订日期（订单的创建日期）", dataType = "string")
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
     * 每天开始时间
     */
    @ApiModelProperty(value = "每天开始时间", dataType = "string")
    private String startHour;
    /**
     * 每天结束日期。针对日期段内每天生效, 当TypeCode为4时表示StartHour到EndHour 酒店不接受预订
     */
    @ApiModelProperty(value = "每天结束日期", dataType = "string")
    private String endHour;
    /**
     * 提前天数
     */
    @ApiModelProperty(value = "提前天数", dataType = "string")
    private String advancedays;
    /**
     * 提前小时
     */
    @ApiModelProperty(value = "提前小时", dataType = "string")
    private String advancetime;
    /**
     * 指定日期
     */
    @ApiModelProperty(value = "指定日期", dataType = "string")
    private String appointeddate;
    /**
     * 连住晚数
     */
    @ApiModelProperty(value = "连住晚数", dataType = "string")
    private String continuousdays;
    /**
     * 最少预定间数
     */
    @ApiModelProperty(value = "最少预定间数", dataType = "string")
    private String continuousfjs;
    /**
     * 最多入住人数
     */
    @ApiModelProperty(value = "最多入住人数", dataType = "string")
    private String zdrzrs;