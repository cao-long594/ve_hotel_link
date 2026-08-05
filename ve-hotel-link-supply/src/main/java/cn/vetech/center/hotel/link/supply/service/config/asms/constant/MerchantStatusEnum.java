package cn.vetech.center.hotel.link.supply.service.config.asms.constant;

/**
 * 商户状态
 *
 * @author luqs
 * @version v1.0
 **/
public enum MerchantStatusEnum {
    /**
     * 0：未审核
     */
    NOT_REVIEW("0", "未审核"),
    /**
     * 1：启用
     */
    ON("1", "启用"),
    /**
     * 2：停用
     */
    OFF("2", "停用"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private MerchantStatusEnum(String code, String name) {
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
