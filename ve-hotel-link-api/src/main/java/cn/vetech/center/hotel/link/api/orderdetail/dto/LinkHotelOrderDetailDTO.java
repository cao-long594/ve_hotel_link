package cn.vetech.center.hotel.link.api.orderdetail.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookOrderRoom;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 订单详
 *
 * @author gaojin
 */
public class LinkHotelOrderDetailDTO extends LinkHotelDTO {
    /**
     * cps订单状态
     */
    @ApiModelProperty(value = "cps订单状态", dataType = "string")
    private String cpsOrderStatus;
    /**
     * 现付，预付
     */
    @ApiModelProperty(value = "现付，预付", dataType = "string")
    private String payment;
    /**
     * cps下单时间
     */
    @ApiModelProperty(value = "cps下单时间", dataType = "string")
    private String cpsCreateTime;
    /**
     * 联系人邮箱
     */
    private String bookContactEmail;
    /**
     * 客人信息
     */
    @ApiModelProperty(value = "客人信息", dataType = "string")
    private List<BookOrderRoom> orderRooms;
    /**
     * fcb登录用户编号
     */
    private String dpyyhbh;
    /**
     * 订单类型，  1、线上订单，2：线下订单
     */
    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDpyyhbh() {
        return dpyyhbh;
    }

    public void setDpyyhbh(String dpyyhbh) {
        this.dpyyhbh = dpyyhbh;
    }

    public List<BookOrderRoom> getOrderRooms() {
        return orderRooms;
    }

    public void setOrderRooms(List<BookOrderRoom> orderRooms) {
        this.orderRooms = orderRooms;
    }

    public String getBookContactEmail() {
        return bookContactEmail;
    }

    public void setBookContactEmail(String bookContactEmail) {
        this.bookContactEmail = bookContactEmail;
    }

    public String getCpsOrderStatus() {
        return cpsOrderStatus;
    }

    public void setCpsOrderStatus(String cpsOrderStatus) {
        this.cpsOrderSta