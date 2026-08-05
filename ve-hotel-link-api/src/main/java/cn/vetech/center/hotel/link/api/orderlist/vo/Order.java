package cn.vetech.center.hotel.link.api.orderlist.vo;

import java.io.Serializable;

/**
 * @author chengwanshan
 * @since 2021/7/5 19:52
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 酒店编号
     */
    private String hotelId;
    /**
     * 酒店id房源   如：艺龙 31200801
     */
    private String hotelIdType;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 实际入住日期
     */
    private String arrivalDate;
    /**
     * 实际离店日期
     */
    private String departureDate;
    /**
     * 订单状态
     *     BEFORE_CONFIRM("1", "待确认"),
     *     AFTER_CONFIRM("2", "已确认"),
     *     CANCELING("3", "取消中"),
     *     CANCEL("4", "已取消"),
     *     LIVE("5", "已入住"),
     *     NOSHOW("6", "NOSHOW"),
     *     OUT("7", "已离店"),
     *     ERROR("-1", "异常");
     */
    private String status;
    /**
     * 供应商原始状态
     */
    private String trueStatus;
    /**
     * 实际房间数量
     */
    private String numberOfRooms;
    /**
     * 实际房费总价
     */
    private String totalPrice;
    /**
     * 服务商服务费
     */
    private String fwsfwf;
    /**
     * 间夜数
     */
    private String jys;
    /**
     * 退款金额
     */
    private String refundAmount;
    /**
     * 供应商退单号
     */
    private String refundId;
    /**
     * 订单类型，1：正常单，2：退单
     */
    private String orderType;
    /**
     * 入住人姓名，多个姓名使用英文逗号拼接
     */
    private String guestName;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTrueStatus() {
        return trueStatus;
    }

    public void se