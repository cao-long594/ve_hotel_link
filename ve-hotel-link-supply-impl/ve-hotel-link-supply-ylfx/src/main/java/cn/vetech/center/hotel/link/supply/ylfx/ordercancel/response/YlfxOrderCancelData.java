package cn.vetech.center.hotel.link.supply.ylfx.ordercancel.response;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class YlfxOrderCancelData {
    /**
     * 结果编码：0. 成功 1. 失败
     */
    private String code;
    /**
     * 结果描叙
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
