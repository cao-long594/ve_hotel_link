package cn.vetech.center.hotel.link.elong.ordercancel.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2022-07-27 11:16
 */
public class ElongOrderIncrRequest extends ElongRequest {

    /**
     * 最后的更新ID
     */
    @JsonProperty("LastId")
    private Long lastId;

    /**
     * 抓取的数量
     */
    @JsonProperty("Count")
    private Integer count;

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
