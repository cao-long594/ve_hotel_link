package cn.vetech.center.hotel.link.api.enums;

/**
 * 酒店分类(国内、国际)
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelClassEnum {
    /**
     * 0：国际
     */
    INTERNATIONAL("0", "国际"),
    /**
     * 1：国内
     */
    DOMESTIC("1", "国内"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelClassEnum(String code, String name) {
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
