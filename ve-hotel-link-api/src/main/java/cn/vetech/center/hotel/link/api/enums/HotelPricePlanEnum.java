package cn.vetech.center.hotel.link.api.enums;

/**
 * 酒店价格查询来源
 *
 * @author luqs
 * @version v1.0
 */
public enum HotelPriceSearchSourceEnum {

    /**
     * 酒店列表最低价
     */
    LIST_LOWEST_PRICE("LIST_LOWEST_PRICE", "酒店列表最低价"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelPriceSearchSourceEnum(String code, String name) {
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
