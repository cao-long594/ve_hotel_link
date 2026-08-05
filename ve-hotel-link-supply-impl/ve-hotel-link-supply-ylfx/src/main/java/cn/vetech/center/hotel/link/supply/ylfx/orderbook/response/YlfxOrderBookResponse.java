package cn.vetech.center.hotel.link.supply.ylfx.orderbook.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxOrderBookResponse extends YlfxBaseResponse {
    /**
     * 下单数据
     */
    private YlfxOrderBookData data;

    public YlfxOrderBookData getData() {
        return data;
    }

    public void setData(YlfxOrderBookData data) {
        this.data = data;
    }
}
