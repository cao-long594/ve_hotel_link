package cn.vetech.center.hotel.link.enums;

/**
 * 需要身份证枚举
 *
 * @author luqs
 * @version v1.0
 **/
public enum IDCardNeedEnum {
    /**
     * 0：不需要
     */
    NOT_NEED("0", "不需要"),
    /**
     * 1：需要
     */
    NEED("1", "需要"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;
    /**
     * 是否需要身份证
     *
     * @param flag 是否需要身份证
     * @return String
     */
    public static String needIDCardFlag(Boolean flag) {
        return Boolean.TRUE.equals(flag) ? IDCardNeedEnum.NEED.getCode() : IDCardNeedEnum.NOT_NEED.getCode();
    }

    private IDCardNeedEnum(String code, String name) {
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
