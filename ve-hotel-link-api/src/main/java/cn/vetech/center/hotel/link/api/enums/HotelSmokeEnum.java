package cn.vetech.center.hotel.link.api.enums;

/**
 * 吸烟
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelSmokeEnum {
    /**
     * 0：不可吸烟
     */
    CAN_NOT("0", "不可吸烟"),

    /**
     * 1：可吸烟
     */
    CAN("1", "可吸烟"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelSmokeEnum(String code, String name) {
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
