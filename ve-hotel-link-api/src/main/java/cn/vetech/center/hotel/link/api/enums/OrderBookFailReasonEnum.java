package cn.vetech.center.hotel.link.api.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengwanshan
 * @since 2022/3/16 16:20
 */
public enum OrderBookFailReasonEnum {
//    下单失败原因，1:变价失败 2:满房失败
    /**
     * 未知
     */
    UNKNOWN("", "unknown", "未知"),
    /**
     * 1:变价失败
     */
    PRICE_CHANGE("1", "H0010004", "变价失败"),
    /**
     * 2:满房失败
     */
    FULL_ROOM("2", "H0010009", "满房失败"),
    /**
     * 3:产品无效或关房
     */
    INVALID_PRODUCT("3", "H0010010", "产品无效或关房"),
    /**
     * 4:无酒店信息
     */
    NO_HOTEL_INFORMATION("4", "H0010001", "无酒店信息"),
    /**
     * 5:无房型信息
     */
    NO_ROOM_INFORMATION("5", "H0010002", "无房型信息"),
    /**
     * 6:无价格信息
     */
    NO_PRICE_INFORMATION("6", "H0010003", "无价格信息"),

    ;
    /**
     * code
     */
    private final String code;
    /**
     * ErrorCode
     */
    private final String errorCode;
    /**
     * name
     */
    private final String name;

    private static Map<String, OrderBookFailReasonEnum> map = new HashMap<>();

    static {
        for (OrderBookFailReasonEnum c : OrderBookFailReasonEnum.values()) {
            map.put(c.errorCode, c);
        }
    }

    private OrderBookFailReasonEnum(String code, String errorCode, String name) {
        this.code = code;
        this.errorCode = errorCode;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param errorCode errorCode
     * @return enum
     */
    public static OrderBookFailReasonEnum instance(String errorCode) {
        if (StringUtils.isBlank(errorCode)) {
            return OrderBookFailReasonEnum.UNKNOWN;
        }
        OrderBookFailReasonEnum e = map.get(errorCode);
        if (e == null) {
            return OrderBookFailReasonEnum.UNKNOWN;
        } else {
            return e;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}
