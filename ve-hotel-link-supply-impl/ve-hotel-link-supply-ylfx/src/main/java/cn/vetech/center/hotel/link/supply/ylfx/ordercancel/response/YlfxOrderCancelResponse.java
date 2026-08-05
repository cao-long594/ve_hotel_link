package cn.vetech.center.hotel.link.supply.ylfx.ordercancel.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderCancelResponse extends YlfxBaseResponse {
    /**
     * 取消接口数据
     */
    private YlfxOrderCancelData data;

    public YlfxOrderCancelData getData() {
        return data;
    }

    public void setData(YlfxOrderCancelData data) {
        this.data = data;
    }
}
