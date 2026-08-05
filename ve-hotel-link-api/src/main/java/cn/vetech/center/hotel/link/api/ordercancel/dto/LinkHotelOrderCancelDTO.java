package cn.vetech.center.hotel.link.api.ordercancel.dto;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.HotelOrderRoomInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 订单取消
 *
 * @author gaojin
 */
public class LinkHotelOrderCancelDTO extends LinkHotelDTO {
    /**
     * 扣款金额
     */
    @ApiModelProperty(value = "扣款金额", dataType = "string")
    private String amount;
    /**
     * 现付，预付
     */
    @ApiModelProperty(value = "现付，预付", dataType = "string")
    private String payment;
    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因", dataType = "string")
    private String reason;
    /**
     * 1B:平台拒单；1A：采购取消
     */
    @ApiModelProperty(value = "1B:平台拒单；1A：采购取消", dataType = "string")
    private String type;
    /**
     * 整单取消退房 1：费控假取消   2：CPS订单走取消接口
     */
    private String zdqxtf;
    /**
     * 房间信息集合
     */
    private List<HotelOrderRoomInfo> orderRoomInfoList;
    /**
     * 联系人邮箱
     */
    private String bookContactEmail;

    public List<HotelOrderRoomInfo> getOrderRoomInfoList() {
        return orderRoomInfoList;
    }

    public void setOrderRoomInfoList(List<HotelOrderRoomInfo> orderRoomInfoList) {
        this.orderRoomInfoList = orderRoomInfoList;
    }

    public String getBookContactEmail() {
        return bookContactEmail;
    }

    public void setBookContactEmail(String bookContactEmail) {
        this.bookContactEmail = bookContactEmail;
    }

    public String getZdqxtf() {
        return zdqxtf;
    }

    public void setZdqxtf(String zdqxtf) {
        this.zdqxtf = zdqxtf;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.r