package cn.vetech.center.hotel.link.api.enums;

/**
 * 加床
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelBedAddEnum {
    /**
     * 0：不可加床
     */
    CAN_NOT("0", "不可加床"),

    /**
     * 1：可加床
     */
    CAN("1", "可加床"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelBedAddEnum(String code, String name) {
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
