package cn.vetech.center.hotel.link.supply.ylfx.validate.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;

/**
 * @author 6161
 * @date 2024/07/18
 */
public class YlfxValidateResponse extends YlfxBaseResponse {
    /**
     * 验价结果
     */
    private YlfxValidateData data;

    public YlfxValidateData getData() {
        return data;
    }

    public void setData(YlfxValidateData data) {
        this.data = data;
    }
}
