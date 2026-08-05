package cn.vetech.center.hotel.link.elong.ratesearch.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 艺龙查询报价请求对象
 *
 * @author gaojin
 */
public class ElongRateSearchResponse extends ElongResponse {
    /**
     * 响应结果
     */
    @JsonProperty("Result")
    private ElongRateSearch result;

    public ElongRateSearch getResult() {
        return result;
    }

    public void setResult(ElongRateSearch result) {
        this.result = result;
    }
}
