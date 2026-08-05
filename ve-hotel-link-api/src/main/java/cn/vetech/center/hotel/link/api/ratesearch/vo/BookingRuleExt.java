package cn.vetech.center.hotel.link.api.ratesearch.vo;

/**
 * 预定规则 用于报价统一处理 校验是否满足预定规则
 * 可将供应预定规则字段对应解析
 *
 * @author 6161
 * @date 2024/10/31
 */
public class BookingRuleExt {
    /**
     * 预订条款编号
     */
    private String bookingRuleId;
    /**
     * 开始日期
     * 报价生效时间yyyy-MM-dd HH:mm:ss 为空表示无限制
     */
    private String startDate;
    /**
     * 结束日期
     * 报价结束时间yyyy-MM-dd HH:mm:ss 为空表示无限制
     */
    private String endDate;
    /**
     * 最少预定房间数量（大于0）
     */
    private Integer minAmount;
    /**
     * 最大预定房间数量（大于0）
     */
    private Integer maxAmount;
    /**
     * 最少连住入住天数（大于0）
     */
    private Integer minDays;
    /**
     * 最多连住入住天数（大于0）
     */
    private Integer maxDays;
    /**
     * 最小提前天数（大于0）
     */
    private Integer minBookUnit;
    /**
     * 最大提前天数  0表示只能预定当天
     */
    private Integer maxBookUnit;
    /**
     * 预订说明
     */
    private String bookingNotices;

    public String getBookingRuleId() {
        return bookingRuleId;
    }

    public void setBookingRuleId(String bookingRuleId) {
        this.bookingRuleId = bookingRuleId;
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

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMinDays() {
        return minDays;
    }

    public void setMinDays(Integer minDays) {
        this.minDays = minDays;
    }

    public Integer g