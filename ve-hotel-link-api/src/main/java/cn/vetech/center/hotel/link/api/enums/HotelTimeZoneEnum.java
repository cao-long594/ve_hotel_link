package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/12/11 15:27
 */
public enum HotelTimeZoneEnum {
    UTC_800("UTC+8:00" ,"东八区"),
    UTC_700("UTC+7:00" ,""),
    UTC_MINUS_800("UTC-8:00" ,""),


    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelTimeZoneEnum(String code, String name) {
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
