package cn.vetech.center.hotel.link.elong.ratesearch.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongSearchGift {
    /**
     * 关联RatePlan.GiftId
     */
    @JsonProperty("GiftId")
    private String giftId;
    /**
     * 开始时间
     */
    @JsonProperty("StartDate")
    private String startDate;
    /**
     * 结束时间
     */
    @JsonProperty("EndDate")
    private String endDate;
    /**
     * CheckinDate:入住日 BookingDate:预订日 StayDate:在店日
     */
    @JsonProperty("DateType")
    private String dateType;
    /**
     * 星期设置
     */
    @JsonProperty("WeekSet")
    private String weekSet;
    /**
     * 活动内容
     */
    @JsonProperty("GiftContent")
    private String description;
    /**
     * 1 送餐相关 2 延迟退房 3 送礼品 4 设施服务（如免费健身、送洗衣等） 5
     * 免费接站/接机 6 送折扣/抵扣券 7 送旅游/门票 8 其他
     */
    @JsonProperty("GiftTypes")
    private String giftTypes;
    /**
     * 小时数
     */
    @JsonProperty("HourNumber")
    private String hourNumber;
    /**
     * 几点之前参加 或者几点之后参加 或者 24点都参加
     */
    @JsonProperty("HourType")
    private String hourType;
    /**
     * 送礼方式:直送一次、每晚都送、其它
     */
    @JsonProperty("WayOfGiving")
    private String wayOfGiving;
    /**
     * 送礼方式为其他的时候，送礼活动的名称
     */
    @JsonProperty("WayOfGivingOther")
    private String wayOfGivingOther;

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
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

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
      