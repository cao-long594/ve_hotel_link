package cn.vetech.center.hotel.link.vcc.ejiehui.common;

import cn.vetech.center.hotel.link.base.BaseSerializable;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:09
 */
public class EjiehuiVCCBaseResponse extends BaseSerializable {
    /**
     * 接口返回码（详见附件）
     */
    private String code;
    /**
     * code 详细信息
     */
    private String msg;
    /**
     * 业务响应, 不同请求返回的结构不同
     */
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
