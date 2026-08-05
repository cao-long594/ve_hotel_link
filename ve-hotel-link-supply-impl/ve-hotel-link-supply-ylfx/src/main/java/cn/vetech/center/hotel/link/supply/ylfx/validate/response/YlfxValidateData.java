package cn.vetech.center.hotel.link.supply.ylfx.validate.response;

/**
 * @author 6161
 * @date 2024/07/25
 */
public class YlfxValidateData {
    /**
     * 结果编码：0. 成功 1. 满房 2. 价格错误
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
