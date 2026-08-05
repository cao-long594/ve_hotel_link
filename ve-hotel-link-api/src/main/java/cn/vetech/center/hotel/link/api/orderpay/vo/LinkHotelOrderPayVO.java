package cn.vetech.center.hotel.link.api.orderpay.vo;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 支付订单
 *
 * @author gaojin
 */
public class LinkHotelOrderPayVO extends LinkHotelVO {
    /**
     * 是否成功 1表示成功 -1表示失败
     */
    @ApiModelProperty(value = "是否成功 1表示成功 -1表示失败", dataType = "string")
    private int successs = SUCCESS;

    public int getSuccesss() {
        return successs;
    }

    public void setSuccesss(int successs) {
        this.successs = successs;
    }
}
