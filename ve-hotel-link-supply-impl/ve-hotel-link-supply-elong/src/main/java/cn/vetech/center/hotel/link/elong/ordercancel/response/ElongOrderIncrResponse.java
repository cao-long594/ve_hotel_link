package cn.vetech.center.hotel.link.elong.ordercancel.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2022-07-27 11:24
 */
public class ElongOrderIncrResponse extends ElongResponse {

    /**
     * 订单增量返回结果
     */
    @JsonProperty("Result")
    private ElongOrderIncrResult result;

    public ElongOrderIncrResult getResult() {
        return result;
    }

    public void setResult(ElongOrderIncrResult result) {
        this.result = result;
    }
}
