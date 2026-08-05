package cn.vetech.center.hotel.link.api.orderbook.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单入住人优惠分摊
 *
 * @author luqs
 * @date 2026-04-07
 */
public class HotelOrderDiscountShareVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 房间序号，和房间表的房间序号对应hotel_dd_fjb_new
     */
    private String roomNo;
    /**
     * 入住日期，yyyy-MM-dd
     */
    private String checkIn;
    /**
     * 入住人序号，和房间表的入住人序号对应hotel_dd_fjb_new
     */
    private Integer checkInNo;
    /**
     * 入住人姓名
     */
    private String name;
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
    private String atyType;
    /**
     * 活动类型名称
     */
    private String atyTypeName;
    /**
     * 分摊抵扣金额（元），保留2位小数
     */
    private BigDecimal deductAmount;
    /**
     * 分摊抵扣积分，如：30
     */
    private Integer deductScore;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public Integer getCheckInNo() {
        return checkInNo;
    }

    public void setCheckInNo(Integer checkInNo) {
        this.checkInNo = checkInNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getWelfareAtyId() {
        return welfareAtyId;
    }

    p