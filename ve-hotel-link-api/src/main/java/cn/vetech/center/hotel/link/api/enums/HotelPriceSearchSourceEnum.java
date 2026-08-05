package cn.vetech.center.hotel.link.api.enums;

/**
 * @author chengwanshan
 * @since 2024/12/12 9:19
 */
public enum HotelPriceTagEnum {
    // 这里可能是部分枚举，做差异化标签展示使用

    MOBILE_PROMOTION("mobile_promotion", "移动专享"),
    /**
     * 线下交通产品订单搭售价格
     */
    PACKAGE_PRODUCTS("package_products", "打包价专享"),
    /**
     * 免费取消场景
     */
    EXTEND_FREE_CANCEL_SCENE("extend_free_cancel_scene", "酒店订单确认后X分钟内免费取消"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelPriceTagEnum(String code, String name) {
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
