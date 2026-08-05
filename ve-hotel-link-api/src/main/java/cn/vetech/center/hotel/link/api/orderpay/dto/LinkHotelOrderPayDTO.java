package cn.vetech.center.hotel.link.api.orderpay.dto;

import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;
import io.swagger.annotations.ApiModelProperty;

/**
 * 担保校验
 *
 * @author gaojin
 */
public class LinkHotelOrderPayDTO extends LinkHotelOrderBookDTO {
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", dataType = "string")
    private String payAmount;
    /**
     * 支付金额（原始币种）
     */
    private FeeInfo originPayAmount;

    public FeeInfo getOriginPayAmount() {
        return originPayAmount;
    }

    public void setOriginPayAmount(FeeInfo originPayAmount) {
        this.originPayAmount = originPayAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
