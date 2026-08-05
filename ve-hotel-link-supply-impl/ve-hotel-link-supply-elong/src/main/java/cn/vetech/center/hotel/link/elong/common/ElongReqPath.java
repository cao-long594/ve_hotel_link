package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.http.ReqPath;

public enum ElongReqPath implements ReqPath {
    /**
     * 获取酒店ID地址
     */
    HOTEL_GET_ID("/xml/v2.0/hotel/hotellist.xml", "获取酒店ID"),
    /**
     * 酒店品牌
     */
    HOTEL_BRAND("/xml/v2.0/hotel/brand_%s.xml", "酒店品牌"),
    /**
     * 酒店设施
     */
    HOTEL_FACILITIE("/open/FacilitiesV2.xlsx", "酒店设施"),

    /**
     * 酒店城市、行政区、商圈
     */
    HOTEL_GEO("/xml/v2.0/hotel/geo_%s.xml", "酒店城市、行政区、商圈"),
    ;
    /**
     *请求地址
     */
    private String path;
    /**
     *描述
     */
    private String desc;

    ElongReqPath(String path, String desc) {
        this.path = path;
        this.desc = desc;
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}