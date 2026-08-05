package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.util.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 艺龙所有的响应类的父类
 *
 * @author gaojin
 */
public class ElongResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 0表示请求成功返回；非0表示存在业务异常
     */
    @JsonProperty("Code")
    private String code;

    /**
     * 错误消息
     */
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return json
     */
    public String toJson() {
        return JacksonUtils.toJsonWithNonEmpty(this);
    }
}
