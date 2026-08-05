package cn.vetech.center.hotel.link.supply.service.config.asms.response;

/**
 * 供应商参数
 *
 * @author luqs
 * @version v1.0
 **/
public class SupplyParam {
    /**
     * 参数名
     */
    private String name;
    /**
     * 参数值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
