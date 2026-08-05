package cn.vetech.center.hotel.link.enums.orderbook;

/**
 * @author chengwanshan
 * @since 2024/6/5 16:39
 */
public enum HotelOrderBookInverseQueryCodeEnum {
    /**
     * 支持反查
     */
    YES("1", "支持反查"),
    /**
     * 不支持反查
     */
    NO("0", "不支持反查"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelOrderBookInverseQueryCodeEnum(String code, String name) {
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
