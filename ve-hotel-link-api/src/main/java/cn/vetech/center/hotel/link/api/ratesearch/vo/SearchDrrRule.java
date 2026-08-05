package cn.vetech.center.hotel.link.api.ratesearch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 促销类
 *
 * @author Songjun 8963
 */
public class SearchDrrRule implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 促销规则编号
     */
    @ApiModelProperty(value = "促销规则编号", dataType = "string")
    private String drrRuleId;
    /**
     * 产品促销规则类型代码
     */
    @ApiModelProperty(value = "产品促销规则类型代码", dataType = "string")
    private String typeCode;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", dataType = "string")
    private String description;
    /**
     * 日期类型,CheckInDay:入住日期 StayDay:在店日期 BookDay:预订日期
     */
    @ApiModelProperty(value = "日期类型,CheckInDay:入住日期 StayDay:在店日期 BookDay:预订日期", dataType = "string")
    private String dateType;
    /**
     * 促销生效开始日期
     */
    @ApiModelProperty(value = "促销生效开始日期", dataType = "string")
    private String startDate;
    /**
     * 促销生效结束日期
     */
    @ApiModelProperty(value = "促销生效结束日期", dataType = "string")
    private String endDate;
    /**
     * 提前几天
     */
    @ApiModelProperty(value = "提前几天", dataType = "string")
    private String dayNum;
    /**
     * 连住几天
     */
    @ApiModelProperty(value = "连住几天", dataType = "string")
    private String checkInNum;
    /**
     * 每连住几晚
     */
    @ApiModelProperty(value = "每连住几晚", dataType = "string")
    private String everyCheckInNum;
    /**
     * 最后几天
     */
    @ApiModelProperty(value = "最后几天", dataType = "string")
    private String lastDayNum;
    /**
     * 第几晚及以后优惠
     */
    @ApiModelProperty(value = "第几晚及以后优惠", dataType = "string")
    private String whichDayNum;
    /**
     * 按金额或按比例来优惠,Cash-金额 Money-金额 Percent-比例 Scale-比例
     */
    @ApiModelProperty(value = "按金额或按比例来优惠,Cash-金额 Money-金额 Percent-比例 Scale-比例", dataType = "string")
    private String cashScale;
    /**
     * 按金额或比例优惠的数值,当CashScale为Percent时,该值保存的为百分数,例如30%
     */
    @ApiModelProperty(value = "按金额或比例优惠的数值,当CashScale为Percent时,该值保存的为百分数,例如30%", dataType = "string")
    private String deductNum;
    /**
     * 星期有效设置,日期符合Weekset中的周设置,才享受feetype所对应的价格 仅DRRStayWeekDay和DRRCheckInWeekDay的时候使用
     */
    @ApiModelProperty(value = "星期有效设置,日期符合Weekset中的周设置,才享受feetype所对应的价格 仅DRRStayWeekDay和DRRCheckInWeekDay的时候使用", dataType = "string")
    private String weekSet;
    /**
     * 价格类型,WeekendFee-周末价格 WeekdayFee-平日价格
     */
    @ApiModelProperty(value = "价格类型,WeekendFee-周末价格 WeekdayFee-平日价格", dataType = "string")
    private String feeType;