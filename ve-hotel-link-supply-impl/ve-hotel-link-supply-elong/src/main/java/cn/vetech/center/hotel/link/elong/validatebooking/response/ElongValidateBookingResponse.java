package cn.vetech.center.hotel.link.elong.validatebooking.response;

import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.validate.response.ElongValidate;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chengwanshan
 * @since 2025/9/29 17:27
 */
public class ElongValidateBookingResponse  extends ElongResponse {
    /**
     *
     */
    @JsonProperty("Result")
    private ElongValidateBooking result;

    public ElongValidateBooking getResult() {
        return result;
    }

    public void setResult(ElongValidateBooking result) {
        this.result = result;
    }
}
