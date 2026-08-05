package cn.vetech.center.hotel.link.elong.validatebooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author chengwanshan
 * @since 2025/9/29 17:54
 */
public class ElongValidateBookingRatePlan {
    /**
     * 产品编号
     */
    @JsonProperty("RatePlanId")
    private String ratePlanId;
    /**
     * 产品名称
     */
    @JsonProperty("RatePlanName")
    private String ratePlanName;
    /**
     * 适用人群
     */
    @JsonProperty("guestType")
    private String guestType;
    /**
     * 其他
     */
    @JsonProperty("guestTypeExtendCh")
    private String guestTypeExtendCh;
    /**
     * 对应的酒店编码
     */
    @JsonProperty("HotelCode")
    private String hotelCode;
    /**
     * 付款类型
     */
    @JsonProperty("PaymentType")
    private String paymentType;
    /**
     * 关联的房型编码
     */
    @JsonProperty("RoomTypeIds")
    private String roomTypeIds;
    /**
     * 产品特性类型
     */
    @JsonProperty("ProductTypes")
    private String productTypes;
    /**
     * 是否需要提供身份证号
     */
    @JsonProperty("NeedIdNo")
    private String needIdNo;
    /**
     * 身份信息验证类型
     */
    @JsonProperty("Identification")
    private String identification;
    /**
     * 分销渠道
     */
    @JsonProperty("sellChannels")
    private String sellChannels;
    /**
     * 是否是今日特价(尾房)
     */
    @JsonProperty("IsLimitTimeSale")
    private String isLimitTimeSale;
    /**
     * 尾房每天预订开始时间
     */
    @JsonProperty("StartTime")
    private String startTime;
    /**
     * 尾房每天预订结束时间
     */
    @JsonProperty("EndTime")
    private String endTime;
    /**
     * 预订最少数量
     */
    @JsonProperty("MinAmount")
    private String minAmount;
    /**
     * 最少入住天数
     */
    @JsonProperty("MinDays")
    private String minDays;
    /**
     * 最多入住天数
     */
    @JsonProperty("MaxDays")
    private String maxDays;
    /**
     * 最多预订间数
     */
@JsonProperty("MaxCheckinRooms")
    private String maxCheckinRooms;
    /**
     * 最少提前预订小时数
     */
    @JsonProperty("MinAdvHours")
    private String minAdvHours;
    /**
     * 最多提前预订小时数
     */
    @JsonProperty("MaxAdvHours")
    private String maxAdvHours;
    /**
     * 担保规则
     */
    @JsonProperty("GuaranteeRules")
    private List<ElongGuaranteeRule> guaranteeRules;
    /**
     * 预付规则
     */
    @JsonProperty("PrepayRules")
    private List<ElongPrepayRule> prepayRules;
    /**
     * 新预付规则
     */
    @JsonProperty("PrepayRuleExtends")
    private List<ElongPrepayRuleExtend> prepayRuleExtends;
    /**
     * 新担保规则
     */
    @JsonProperty("GuaranteeRuleExtends")
    private List<ElongGuaranteeRuleExtend> guaranteeRuleExtends;
    /**
     * 增值服务
     */
    @JsonProperty("ValueAdds")
    private List<ElongValueAdd> valueAdds;
    /**
     * 新餐食节点
     */
    @JsonProperty("Meals")
    private List<ElongMeal> meals;
    /**
     * 产品提供服务的时间
     */
    @JsonProperty("serviceTimePolicyInfo")
    private ElongServiceTimePolicyInfo serviceTimePolicyInfo;
    /**
     * 产品可以展示销售的渠道
     */
    @JsonProperty("BookingChannels")
    private String bookingChannels;
    /**
     * 是否为限价产品
     */
    @JsonProperty("IsPriceLimitProduct")
    private String isPriceLimitProduct;
    /**
     * 限价类型
     */
    @JsonProperty("PriceLimitedType")
    private String priceLimitedType;
    /**
     * 可售会员等级
     */
    @JsonProperty("CustomerLevel")
    private List<Integer> customerLevel;
    /**
     * 预付产品发票模式
     */
    @JsonProperty("InvoiceMode")
    private String invoiceMode;
    /**
     * 酒店签约类型
     */
    @JsonProperty("CooperationType")
    private String cooperationType;
    /**
     * 可住开始时间
     */
    @JsonProperty("earliestToliveTime")
    private String earliestToliveTime;
    /**
     * 可住结束时间
     */
    @JsonProperty("latestToliveTime")
private String latestToliveTime;
    /**
     * 可住时长
     */
    @JsonProperty("stayTime")
    private String stayTime;
    /**
     * 限时抢规则
     */
    @JsonProperty("timeRushRuleList")
    private List<ElongTimeRushRule> timeRushRuleList;
    /**
     * 可入住人数
     */
    @JsonProperty("xStayPeopleNum")
    private String xStayPeopleNum;
    /**
     * 可入住性别
     */
    @JsonProperty("xStaySex")
    private String xStaySex;
    /**
     * 床型
     */
    @JsonProperty("xBedType")
    private String xBedType;
    /**
     * 楼层
     */
    @JsonProperty("xFloor")
    private String xFloor;
    /**
     * 朝向
     */
    @JsonProperty("xOrientation")
    private String xOrientation;
    /**
     * 自定义说明
     */
    @JsonProperty("xUserDefined")
    private String xUserDefined;
    /**
     * 是否支持专票
     */
    @JsonProperty("SupportSpecialInvoice")
    private String supportSpecialInvoice;

    public String getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(String ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public String getGuestTypeExtendCh() {
        return guestTypeExtendCh;
    }

    public void setGuestTypeExtendCh(String guestTypeExtendCh) {
        this.guestTypeExtendCh = guestTypeExtendCh;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        thi