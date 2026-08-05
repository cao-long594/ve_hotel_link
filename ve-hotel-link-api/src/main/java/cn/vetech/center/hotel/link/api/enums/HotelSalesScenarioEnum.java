package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/12/11 9:35
 */
public enum HotelSalesScenarioEnum {
    GUEST_HOTEL_ONLY("1", "旅客登陆胜意自己开发的网站直接自助发起单独选购酒店的查询报价"),
    CUSTOMER_HOTEL_ONLY("2", "内部客服登陆贵司自己开发的网站发起单独选购酒店的查询报价"),
    CUSTOMER_HOTEL_PACKAGE("3", "内部客服登陆贵司自己开发的网站为持有站外机票订单的旅客捆绑销售酒店"),

    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelSalesScenarioEnum(String code, String name) {
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
