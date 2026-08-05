package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * 是否及时确认
 * @since 2021/9/14 19:22
 */
public enum InstantConfirmationEnum {

    /**
     * 及时确认
     */
    INSTANT("true", "及时确认"),
    /**
     * 延时确认
     */
    DELAY("false", "延时确认"),
    ;

    private InstantConfirmationEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    /**
     * 是否立即确认
     *
     * @param instantConfirmFlag 是否立即确认
     * @return String
     */
    public static String instantConfirm(Boolean instantConfirmFlag) {
        return Boolean.TRUE.equals(instantConfirmFlag) ? InstantConfirmationEnum.INSTANT.getCode() : InstantConfirmationEnum.DELAY.getCode();
    }

    /**
     * 立即确认
     *
     * @return String
     */
    public static String instantConfirm() {
        return InstantConfirmationEnum.INSTANT.getCode();
    }

    /**
     * 延迟确认
     *
     * @return String
     */
    public static String delayConfirm() {
        return InstantConfirmationEnum.DELAY.getCode();
    }

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



}
