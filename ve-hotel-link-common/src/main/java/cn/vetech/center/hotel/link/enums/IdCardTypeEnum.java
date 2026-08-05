package cn.vetech.center.hotel.link.enums;

/**
 * 客人证件类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum IdCardTypeEnum {
    /**
     * IdentityCard：身份证
     */
    ID_CARD("IdentityCard", "身份证"),
    /**
     * Passport：护照
     */
    PASSPORT("Passport", "护照"),
    /**
     * Other：其他
     */
    OTHER("Other", "其他"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private IdCardTypeEnum(String code, String name) {
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
