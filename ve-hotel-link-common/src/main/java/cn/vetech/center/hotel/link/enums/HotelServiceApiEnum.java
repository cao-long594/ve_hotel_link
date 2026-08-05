package cn.vetech.center.hotel.link.enums;

/**
 * 酒店openapi
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelServiceApiEnum {
    /**
     * Hotel_Getjdxxxx：酒店详情
     */
    HOTEL_DETAIL("Hotel_Getjdxxxx", "酒店详情"),
    /**
     * ihotel_cds_detail：国际酒店详情
     */
    INTER_HOTEL_DETAIL("ihotel_cds_detail", "国际酒店详情"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelServiceApiEnum(String code, String name) {
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
