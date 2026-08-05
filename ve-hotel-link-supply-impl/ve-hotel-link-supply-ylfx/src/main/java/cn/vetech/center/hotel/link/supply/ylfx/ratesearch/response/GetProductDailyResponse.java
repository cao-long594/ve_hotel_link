package cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 6161
 * @date 2024/07/23
 */
public class GetProductListResponse extends YlfxBaseResponse {
    /**
     * 产品列表
     */
    @JsonProperty("data")
    private List<ProductData> productDataList;

    public List<ProductData> getProductDataList() {
        return productDataList;
    }

    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }
}
