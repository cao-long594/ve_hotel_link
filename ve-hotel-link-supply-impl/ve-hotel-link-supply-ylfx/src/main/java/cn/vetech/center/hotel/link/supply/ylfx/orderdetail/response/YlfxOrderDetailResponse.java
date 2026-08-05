package cn.vetech.center.hotel.link.supply.ylfx.orderdetail.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderDetailResponse extends YlfxBaseResponse {
    /**
     * 订单数据
     */
    private YlfxOrderDetailData data;

    public YlfxOrderDetailData getData() {
        return data;
    }

    public void setData(YlfxOrderDetailData data) {
        this.data = data;
    }
}
