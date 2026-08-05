package cn.vetech.center.hotel.link.api.ordercancel.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 取消订单
 *
 * @author gaojin
 */
public class LinkHotelOrderCancelVO extends LinkHotelVO {
    /**
     * 是否成功 1表示成功 -1表示失败
     */
    @ApiModelProperty(value = "是否成功 1表示成功 -1表示失败", dataType = "string")
    private int successs = SUCCESS;
    /**
     * 取消罚金
     */
    @ApiModelProperty(value = "取消罚金", dataType = "string")
    private String penaltyAmount;
    //途家民宿
    /**
     *
     */
    @ApiModelProperty(value = "货币类型:CNY,USD,JPY,THB,KRW...", dataType = "string")
    private String currency;
    /**
     *
     */
    @ApiModelProperty(value = "可退款金额", dataType = "string")
    private String amount;
    /**
     *
     */
    @ApiModelProperty(value = "退款说明", dataType = "string")
    private String desc;
    /**
     * 取消中标记，是否取消中 返回1代表取消中，但是只有在false的情况才可以返回1
     */
    @ApiModelProperty(value = "取消中标记", dataType = "string")
    private String canceling;


    public int getSuccesss() {
        return successs;
    }

    public void setSuccesss(int successs) {
        this.successs = successs;
    }

    public String getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(String penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCanceling() {
        return canceling;
    }

    public void setCanceling(String canceling