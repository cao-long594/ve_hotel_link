package cn.vetech.center.hotel.link.api.orderbook.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 优惠信息
 *
 * @author luqs
 * @version v1.0
 */
public class BookDiscountInfo implements Serializable {
    private static final long serialVersionUID = -3380189478715559615L;
    /**
     * 优惠类型：1积分  2优惠券
     */
    private String discountType;
    /**
     * 福利活动ID，即优惠券活动ID
     */
    private String welfareAtyId;
    /**
     * 福利活动名称
     */
    private String welfareAtyName;
    /**
     * 活动类型id
     */
    private String activityType;
    /**
     * 活动类型名称
     */
    private String activityTypeName;
    /**
     * 优惠券券码
     */
    private String couponCode;
    /**
     * 抵扣金额
     */
    private BigDecimal deductAmount;
    /**
     * 抵扣积分
     */
    private Integer deductScore;
    /**
     * 积分单位，如：积分
     */
    private String scoreUnit;

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getWelfareAtyId() {
        return welfareAtyId;
    }

    public void setWelfareAtyId(String welfareAtyId) {
        this.welfareAtyId = welfareAtyId;
    }

    public String getWelfareAtyName() {
        return welfareAtyName;
    }

    public void setWelfareAtyName(String welfareAtyName) {
        this.welfareAtyName = welfareAtyName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCo