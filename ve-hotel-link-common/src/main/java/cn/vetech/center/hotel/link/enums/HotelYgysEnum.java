package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2022-03-24 14:27
 */
public enum HotelYgysEnum {
    /**
     * 因公
     */
    YG("1", "因公"),
    /**
     * 因私
     */
    YS("2", "因私"),
    ;

    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    private HotelYgysEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
