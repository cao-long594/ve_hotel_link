package cn.vetech.center.hotel.link.enums;

/**
 * 预付订单，NOSHOW情况下，是否收费枚举
 *
 * @author chengwanshan
 * @since 2021/7/9 12:04
 */
public enum ChargeForNoShowOfPrepaidEnum {
    /**
     * 0：不收费
     */
    NO_CHARGE("0", "不收费"),
    /**
     * 1：收费
     */
    CHARGE("1", "收费")
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private ChargeForNoShowOfPrepaidEnum(String code, String name) {
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
