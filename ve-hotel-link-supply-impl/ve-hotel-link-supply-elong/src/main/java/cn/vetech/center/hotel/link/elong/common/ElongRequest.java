package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.util.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 艺龙所有的请求类的基类，艺龙的请求类应该全部继承该类
 *
 * @author gaojin
 */
public class ElongRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @JsonIgnore
    private ElongConfig config;

    public ElongConfig getConfig() {
        return config;
    }

    public void setConfig(ElongConfig config) {
        this.config = config;
    }

    /**
     * @return json
     */
    public String toJson() {
        return JacksonUtils.toJsonWithNonEmpty(this);
    }
}
