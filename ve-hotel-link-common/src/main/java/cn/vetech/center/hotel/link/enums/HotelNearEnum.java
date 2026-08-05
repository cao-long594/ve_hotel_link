package cn.vetech.center.hotel.link.enums;

/**
 * 设施类型
 *
 * @author pengyefei
 * @since 2022-08-22 19:05
 */
public enum HotelNearEnum {
    /**
     * 餐饮
     */
    MEAL("1", "餐饮"),
    /**
     * 购物
     */
    SHOP("2", "购物"),
    /**
     * 娱乐
     */
    RECREATION("3", "娱乐"),
    /**
     * 地铁站
     */
    SUBWAY_STATION("201", "地铁站"),
    /**
     * 景点
     */
    SCENIC_SPOT("102", "景点"),
    /**
     * 医院
     */
    HOSPITAL("104", "医院"),
    ;

    private final String code;

    private final String name;

    HotelNearEnum(String code, String name) {
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
