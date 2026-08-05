package cn.vetech.center.hotel.link.elong.orderbook.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */
public class ElongOrderBookResponse extends ElongResponse {
    /**
     *
     */
    @JsonProperty("Result")
    private ElongOrderBook result;

    public ElongOrderBook getResult() {
        return result;
    }

    public void setResult(ElongOrderBook result) {
        this.result = result;
    }
}
