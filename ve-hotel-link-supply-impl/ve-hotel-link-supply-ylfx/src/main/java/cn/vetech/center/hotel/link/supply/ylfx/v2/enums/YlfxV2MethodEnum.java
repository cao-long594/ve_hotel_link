package cn.vetech.center.hotel.link.supply.ylfx.v2.enums;

/**
 * 易旅分销 V2 接口地址。
 */
public enum YlfxV2MethodEnum {
    HOTEL_CODES("/open/static/hotelcodes", "可同步酒店编码列表"),
    HOTEL_INFOS("/open/static/hotelinfos", "酒店静态信息"),
    HOTEL_SEARCH("/open/avail/hotelsearch", "酒店可订产品查询"),
    PRECHECK("/open/avail/precheck", "产品可订校验"),
    BOOK("/open/booking/create", "创建订单"),
    CANCEL("/open/booking/cancel", "取消订单"),
    QUERY_STATUS("/open/booking/search", "查询订单");

    private final String uri;
    private final String desc;

    /**
     * 构造接口枚举。
     *
     * @param uri 接口路径
     * @param desc 接口说明
     */
    YlfxV2MethodEnum(String uri, String desc) {
        this.uri = uri;
        this.desc = desc;
    }

    /**
     * 获取接口路径。
     *
     * @return 接口路径
     */
    public String getUri() {
        return uri;
    }

    /**
     * 获取接口说明。
     *
     * @return 接口说明
     */
    public String getDesc() {
        return desc;
    }
}
