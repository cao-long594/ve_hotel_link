package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2022/3/23 15:53
 */
public enum ValidateResultEnum {
    // 验证结果 1可预订 0不可预订
    /**
     * 0不可预订
     */
    NO("0", "不可预订"),
    /**
     * 1可预订
     */
    YES("1", "可预订"),
    ;
    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;


    private ValidateResultEnum(String code, String name) {
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
