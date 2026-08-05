package cn.vetech.center.hotel.link.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaojin
 */
public enum RoomStatusEnum {
    /**
     *
     */
    GOOD("0", "充足"),
    /**
     *
     */
    TENSION("1", "紧张"),
    /**
     *
     */
    FULL("2", "满房"),
    /**
     *
     */
    UNKNOW("3", "需等待确认"),
    /**
     *
     */
    KJSQR("4", "可及时确认");

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
    private static Map<String, RoomStatusEnum> map = new HashMap<String, RoomStatusEnum>();

    static {
        for (RoomStatusEnum c : RoomStatusEnum.values()) {
            map.put(c.code, c);
        }
    }

    private RoomStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param code code
     * @return enum
     */
    public static RoomStatusEnum instance(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    public static Map<String, RoomStatusEnum> getMap() {
        return map;
    }
}
