package cn.vetech.center.hotel.link.supply.ylfx.enums;

public enum YlfxMethodEnum {
    PAGE_HOTEL("/openapi/hotel/static/pageHotel", "分页获取酒店列表，每页最大1000"),
    GET_HOTEL_INFO("/openapi/hotel/static/getHotelInfo", "根据酒店ID查询酒店下所有房型信息"),
    LIST_ROOM_TYPE_BY_HOTEL_ID("/openapi/hotel/static/listRoomtypeByHotelId", "根据酒店ID查询酒店下所有房型信息"),
    GET_ROOM_TYPE_BY_ROOM_TYPE_ID("/openapi/hotel/static/getRoomtypeByRoomtypeId", "根据酒店ID查询酒店下所有房型信息"),
    LIST_PRODUCT("/openapi/hotel/static/listProduct", "根据酒店ID查询酒店下所有产品信息"),
    GET_PRODUCT_INFO_BY_PRODUCT_ID("/openapi/hotel/static/getProductInfoByProductId", "根据产品ID查询产品信息"),
    LIST_ROOM_TYPE_DAILY("/openapi/hotel/listRoomtypeDaily", "根据酒店ID,查询酒店下所有房型在指定时间范围内的房态，库存，是否可超售"),
    LIST_ROOM_TYPE_DAILY_BY_ROOM_TYPE("/openapi/hotel/listRoomtypeDailyByRoomtype", "根据房型ID查询房态库存"),
    LIST_PRODUCT_DAILY("/openapi/hotel/listProductDaily", "根据酒店ID查询产品每日价格"),
    LIST_PRODUCT_DAILY_BY_PRODUCT("/openapi/hotel/listProductDailyByProduct", "根据产品ID查询产品每日价格"),
    LIST_CHANGE("/openapi/hotel/listChange", "变更查询接口"),
    BOOK("/openapi/order/book", "预订"),
    VALIDATE("/openapi/order/validate", "下单前校验"),
    CANCEL("/openapi/order/cancel", "取消订单"),
    QUERY_STATUS("/openapi/order/queryStatus", "查询订单状态"),
    ;
    private final String uri;
    private final String desc;

    YlfxMethodEnum(String uri, String desc) {
        this.uri = uri;
        this.desc = desc;
    }

    public String getUri() {
        return uri;
    }

    public String getDesc() {
        return desc;
    }
}
