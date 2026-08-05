package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2024/5/9 19:59
 */
public enum ValidateGnResultEnum {
    // 验证结果 1验价成功 -1验价失败，不可继续预订 -2验价失败，可以继续预订
    /**
     * 1可预订
     */
    YES("1", "可预订"),
    /**
     * 0不可预订
     */
    NO_1("-1", "验价失败，不可继续预订"),
    /**
     * 0不可预订
     */
    NO_2("-2", "验价失败，可以继续预订"),
    ;
    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;


    private ValidateGnResultEnum(String code, String name) {
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
