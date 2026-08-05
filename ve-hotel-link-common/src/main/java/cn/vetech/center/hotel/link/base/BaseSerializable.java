package cn.vetech.center.hotel.link.base;


import cn.vetech.center.hotel.link.util.JacksonUtils;

import java.io.Serializable;

/**
 * base序列化
 *
 * @author luqs
 * @version v1.0
 **/
public class BaseSerializable implements Serializable {

    private static final long serialVersionUID = 1901935853991140760L;

    /**
     * 转成json
     *
     * @return String
     */
    public String toJson() {
        return JacksonUtils.toJsonWithNonEmpty(this);
    }
}
