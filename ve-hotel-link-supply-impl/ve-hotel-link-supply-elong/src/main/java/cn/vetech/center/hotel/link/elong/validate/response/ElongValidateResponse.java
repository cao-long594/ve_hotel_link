package cn.vetech.center.hotel.link.elong.validate.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gaojin
 */

public class ElongValidateResponse extends ElongResponse {
    /**
     *
     */
    @JsonProperty("Result")
    private ElongValidate date;

    public ElongValidate getDate() {
        return date;
    }

    public void setDate(ElongValidate date) {
        this.date = date;
    }
}
