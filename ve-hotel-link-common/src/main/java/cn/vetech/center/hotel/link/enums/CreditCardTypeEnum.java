package cn.vetech.center.hotel.link.enums;

/**
 * 客人证件类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum CreditCardIdTypeEnum {
    /**
     * 0：身份证
     */
    ID_CARD("0", "身份证"),
    /**
     * 1：护照
     */
    PASSPORT("1", "护照"),
    /**
     * 2：军人证
     */
    MILITARY_ID("2", "军人证"),
    /**
     * 3：其他
     */
    OTHER("3", "其他"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private CreditCardIdTypeEnum(String code, String name) {
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
