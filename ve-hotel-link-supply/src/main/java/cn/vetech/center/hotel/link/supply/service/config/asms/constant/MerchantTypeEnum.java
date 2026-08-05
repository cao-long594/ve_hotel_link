package cn.vetech.center.hotel.link.supply.service.config.asms.constant;

/**
 * 商户类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum MerchantTypeEnum {
    /**
     * 1：服务商
     */
    PROVIDER("1", "服务商"),
    /**
     * 2：供应商
     */
    SUPPLIER("2", "供应商"),
    /**
     * 3：供服一体
     */
    SUP_PRO("3", "供服一体"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private MerchantTypeEnum(String code, String name) {
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
