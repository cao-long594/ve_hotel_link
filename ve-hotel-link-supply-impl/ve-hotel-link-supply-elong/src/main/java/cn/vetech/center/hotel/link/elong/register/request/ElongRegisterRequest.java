package cn.vetech.center.hotel.link.elong.register.request;

import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaotengyu
 * @since 2023-06-15 10:27
 */
public class ElongRegisterRequest extends ElongRequest {

    @JsonProperty("Mobile")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
