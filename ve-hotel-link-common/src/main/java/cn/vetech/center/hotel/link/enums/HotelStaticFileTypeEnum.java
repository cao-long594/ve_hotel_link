package cn.vetech.center.hotel.link.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 酒店静态文件类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelStaticFileTypeEnum {
    /**
     * hotelInfo：酒店信息
     */
    HOTEL_ID("hotelId", "酒店ID文件"),

    /**
     * hotelInfo：酒店信息
     */
    HOTEL_INFO("hotelInfo", "酒店信息"),
    /**
     * hotelImg：酒店图片信息
     */
    HOTEL_IMG("hotelImg", "酒店图片信息"),
    /**
     * hotelFacility：酒店设施信息
     */
    HOTEL_FACILITY("hotelFacility", "酒店设施信息"),
    /**
     * hotelRoom：酒店房型信息
     */
    HOTEL_ROOM("hotelRoom", "酒店房型信息"),
    /**
     * hotelPolicy：酒店政策信息
     */
    HOTEL_POLICY("hotelPolicy", "酒店政策信息"),
    /**
     * hotelProduct：酒店产品信息
     */
    HOTEL_PRODUCT("hotelProduct", "酒店产品信息"),
    /**
     * hotelProductDetail：酒店产品每日库存价格房态等信息
     */
    HOTEL_PRODUCT_DETAIL("hotelProductDetail", "酒店产品每日库存价格房态"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelStaticFileTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     *
     */
    private static final Map<String, HotelStaticFileTypeEnum> CODEMAP = new HashMap<>();

    static {
        HotelStaticFileTypeEnum[] enums = HotelStaticFileTypeEnum.values();
        Arrays.asList(enums).forEach(h -> {
            CODEMAP.put(h.getCode(), h);
        });
    }

    public static Optional<HotelStaticFileTypeEnum> instanceOptByCode(String code) {
        return Optional.ofNullable(CODEMAP.get(code));
    }
}
