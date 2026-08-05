package cn.vetech.center.hotel.link.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengwanshan
 * @since 2024/2/27 14:49
 */
public enum ChargeFrequenceEnum {
    /**
     * Per Booking
     */
    PB("1", "按单收"),
    /**
     * Per Day
     */
    PD("2", "每天"),
    /**
     * Per Night
     */
    PN("3", "每晚"),
    /**
     * Per Guest
     */
    PG("4", "每人"),
    /**
     * Per Guest Per Night
     */
    PGPN("5", "每人每晚")
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private static Map<String, ChargeFrequenceEnum> map = new HashMap<>();

    static {
        for (ChargeFrequenceEnum c : ChargeFrequenceEnum.values()) {
            map.put(c.code, c);
        }
    }

    private ChargeFrequenceEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param code code
     * @return enum
     */
    public static ChargeFrequenceEnum instance(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }


    public static Map<String, ChargeFrequenceEnum> getMap() {
        return map;
    }
}
