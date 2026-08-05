package cn.vetech.center.hotel.link.supply.service.config.asms.constant;

/**
 * 供应商商状态
 *
 * @author luqs
 * @version v1.0
 **/
public enum SupplierStatusEnum {
    /**
     * 0：停用
     */
    OFF("0", "停用"),
    /**
     * 1：启用
     */
    ON("1", "启用"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private SupplierStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
