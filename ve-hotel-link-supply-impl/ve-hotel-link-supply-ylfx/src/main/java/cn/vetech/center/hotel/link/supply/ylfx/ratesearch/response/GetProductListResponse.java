package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class GetProductPriceDailyResponse extends YlfxBaseResponse {
    /**
     * 每日产品价格列表
     */
    @JsonProperty("data")
    private List<ProductPriceDailyData> productPriceDailyDataList;

    public List<ProductPriceDailyData> getProductPriceDailyDataList() {
        return productPriceDailyDataList;
    }

    public void setProductPriceDailyDataList(List<ProductPriceDailyData> productPriceDailyDataList) {
        this.productPriceDailyDataList = productPriceDailyDataList;
    }
}
