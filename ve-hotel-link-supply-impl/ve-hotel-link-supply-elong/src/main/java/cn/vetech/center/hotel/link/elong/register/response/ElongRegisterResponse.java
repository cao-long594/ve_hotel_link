package cn.vetech.center.hotel.link.elong.register.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2023-06-15 10:29
 */
public class ElongRegisterResponse extends ElongResponse {

    /**
     * 结果
     */
    @JsonProperty("Result")
    private ElongRegisterResult result;

    public ElongRegisterResult getResult() {
        return result;
    }

    public void setResult(ElongRegisterResult result) {
        this.result = result;
    }
}
