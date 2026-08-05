package cn.vetech.center.hotel.link.elong.ordercancel.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongOrderCancelResponse extends ElongResponse {

    /**
     * result
     */
    @JsonProperty("Result")
    private ElongOrderCancel result;

    public ElongOrderCancel getResult() {
        return result;
    }

    public void setResult(ElongOrderCancel result) {
        this.result = result;
    }
}
