package cn.vetech.center.hotel.link.elong.validatebooking.response;

import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchMeals;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchPrepayResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/10/15 14:24
 */
public class ElongValidateBooking {
    /**
     * 产品信息
     */
    @JsonProperty("RatePlan")
    private ElongValidateBookingRatePlan ratePlan;
    /**
     * 周末价格起始日
     * 为0表示周末设置从周一开始
     * 为0表示到周日结束，但是两个都为0表示无周末设置； 如果开始为3，结束为1，表示从周三到下周1都是周末设置
     * 1代表周一，7代表周日
     */
    @JsonProperty("WeekendStart")
    private String weekendStart;
    /**
     * 周末价格结束日
     * 为0表示周末设置从周一开始
     * 为0表示到周日结束，但是两个都为0表示无周末设置； 如果开始为3，结束为1，表示从周三到下周1都是周末设置
     * 1代表周一，7代表周日
     */
    @JsonProperty("WeekendEnd")
    private String weekendEnd;
    /**
     * 预订规则
     */
    @JsonProperty("BookingRules")
    private List<ElongBookingRule> bookingRules;
    /**
     * 库存
     */
    @JsonProperty("Inventories")
    private List<ElongInventory> inventories;
    /**
     * 价格
     */
    @JsonProperty("Rates")
    private List<ElongRate> rates;
    /**
     * 对象状态
     */
    @JsonProperty("ObjectEffectiveStatus")
    private ElongObjectEffectiveStatus objectEffectiveStatus;
    /**
     * 到天餐食结果表格
     * 注意：此为移位后餐食结果表格。根据餐食原始规则，当入住日期内全部为固定餐食或入住日期内全部为半固定餐食且固定餐食类型一样时，固定餐食中的早餐、午餐会向后移一天展示；动态餐食分别与入住日期对应，不会后移一天。hasMealTable为true，表示存在餐食表格。
     */
    @JsonProperty("meals")
    private ElongSearchMeals meals;
    /**
     * 预付规则结果
     */
    @JsonProperty("PrepayResult")
    private ElongSearchPrepayResult prepayResult;
    /**
     * 现付规则结果
     */
    @JsonProperty("GuaranteeResult")
    private ElongGuaranteeResult guaranteeResult;
    /**
     * 数据来源
     * v1.32新增，特定情况使用，一般可忽略不管
     * DB-数据库
     * DC-直连
     */
    @JsonProperty("DataSource")
    private String dataSource;
    /**
     * 总卖价
     */
    @JsonProperty("TotalRate")
    private String totalRate;