package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2022/4/6 11:44
 */
public enum SearchNightlyRateStatusEnum {
    /**
     * 可用
     */
    YES("true", "可用"),
    /**
     * 不可用
     */
    NO("false", "不可用"),
    ;

    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    private SearchNightlyRateStatusEnum(String code, String name) {
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
