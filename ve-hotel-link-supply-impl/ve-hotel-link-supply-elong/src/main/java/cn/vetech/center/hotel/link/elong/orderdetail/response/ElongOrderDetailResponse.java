package cn.vetech.center.hotel.link.elong.orderdetail.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongOrderDetailResponse extends ElongResponse {
    /**
     *
     */
    @JsonProperty("Result")
    private ElongOrderDetail result;

    public ElongOrderDetail getResult() {
        return result;
    }

    public void setResult(ElongOrderDetail result) {
        this.result = result;
    }
}
