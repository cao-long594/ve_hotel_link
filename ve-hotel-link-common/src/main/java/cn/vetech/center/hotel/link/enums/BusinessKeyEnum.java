package cn.vetech.center.hotel.link.enums;

import cn.vetech.center.hotel.link.entity.IBusinessKey;

/**
 * @author wangkai
 * @since 2021/3/18
 */
public enum BusinessKeyEnum implements IBusinessKey {
    /**
     *
     */
    ELONG_RATE_ONE("elong:rate", "艺龙查询报价"),

    /**
     * 喜玩查询报价接口（最低价用）
     */
    XWW_RATE("xww:zdj", "喜玩查询报价接口（最低价用）限流"),
    /**
     * 东呈
     */
    DONGCHENG_RATE("dongcheng:zdj", "东呈限流"),
    /****
     * 艺龙最低价
     */
    ELONG_RATE("elong:zdj", "艺龙最低价限流"),
    /**
     * 付讯酒店列表
     */
    FUXUN_HOTEL("fuxun:hotel", "付讯酒店列表限流"),
    /**
     * 付讯房型列表
     */
    FUXUN_ROOM("fuxun:room", "付讯房型列表限流"),
    /**
     * 付讯报价
     */
    FUXUN_RATE("fuxun:rate", "付讯报价限流"),
    /**
     * 众客报价
     */
    ZHONGKE_RATE("zhongke:rate", "众客报价限流"),
    /**
     * 携程查询酒店详情限流
     */
    XCW_HOTELDETAIL("xcw:hoteldetail", "携程查询酒店详情限流"),
    /**
     * 喜玩查询酒店详情限流
     */
    XWW_HOTEL("xww:hoteldetail", "喜玩查询酒店详情限流"),
    /**
     * 游由迎航查询酒店列表限流
     */
    YYTRIP_HOTELList("yytrip:hotellist", "游由迎航查询酒店列表限流"),
    /**
     * 游由迎航查询酒店详情列表限流
     */
    YYTRIP_HOTELINFOR("yytrip:hotelinfo", "游由迎航查询酒店详情列表限流"),
    /**
     * 阿里商旅查询报价接口（最低价用）
     */
    ALITRIP_RATE("alitrip:zdj", "阿里商旅查询报价接口（最低价用）限流"),
    /**
     * 携程查询报价接口（最低价用）限流
     */
    XCW_LOW_PRICE("xcw:zdj", "携程查询报价接口（最低价用）限流"),
    /**
     * 差旅管家查询报价接口（最低价用）
     */
    TRIPWISE_RATE("tripwise:zdj", "差旅管家查询报价接口（最低价用）限流"),
    /**
     * 萤旅查询报价接口（最低价用）
     */
    YINGLV_RATE("yinglv:zdj", "萤旅查询报价接口（最低价用）限流"),
    /**
     * 携程商旅查询酒店详情接口限流
     */
    XCSL_HOTEL_DETAIL("xcsl:hoteldetail", "携程商旅查询酒店详情接口限流"),
    /**
     * 胜意CPS查询酒店列表接口限流
     */
    VE_HOTEL_LIST("ve:hotellist", "胜意CPS查询酒店列表接口限流"),
    /**
     * 胜意CPS查询酒店房型信息接口限流
     */
    VE_ROOM_INFO("ve:roominfo", "胜意CPS查询酒店房型信息接口限流"),
    /**
     * tcext获取最低价接口限流
     */
    TCEXT_LOW_PRICE("tcext:lowPrice", "tcext获取最低价接口限流"),
    /**
    胜意CPS获取最低价接口限流
     */
    VE_LOW_PRICE("ve:lowPrice", "胜意CPS获取最低价接口限流"),
    /**
     * webbeds查询报价接口（最低价用）限流
     */
    WEBBEDS_RATE("webbeds:zdj", "webbeds查询报价接口（最低价用）限流"),

    /**
     * 深圳春秋酒店ID列表限流
     */
    SZCQ_HOTEL_ID_LIST("szcq:hotelidlist", "深圳春秋酒店ID列表限流"),

    /**
     * 深圳春秋酒店详情列表限流
     */
    SZCQ_HOTEL_INFO_LIST("szcq:hotelinfolist", "深圳春秋酒店详情列表限流"),
    /**
     * zhiketong最低价限流
     */
    ZHIKETONG_ZDJ("zhiketong:zdj", "zhiketong最低价限流"),
    /**
     * ratehawk最低价限流
     */
    RATEHAWK_ZDJ("ratehawk:zdj", "ratehawk最低价限流"),

    /**
     * heytrip酒店ID列表限流
     */
    HEYTRIP_HOTEL_ID_LIST("heytrip:hotelidlist", "heytrip酒店ID列表限流"),
    /**
     * heytrip城市ID列表限流
     */
    HEYTRIP_CITY_ID_LIST("heytrip:cityidlist", "heytrip城市ID列表限流"),
    /**
     * heytrip酒店详情限流
     */
    HEYTRIP_HOTEL_INFO_LIST("heytrip:hotelinfo", "heytrip酒店详情限流"),
    /**
     * heytrip酒店价格限流
     */
    HEYTRIP_HOTEL_RATE_LIST("heytrip:hotelrate", "heytrip酒店价格限流"),
    /**
     * expedia最低价限流
     */
    EXPEDIA_ZDJ("expedia:zdj", "expedia最低价限流"),
    /**
     * expedia酒店静态数据限流
     */
    EXPEDIA_HOTELINFO("expedia:hotelinfo", "expedia酒店静态数据限流"),

    /**
     * 游由平台查询酒店列表限流
     */
    YYPT_HOTELList("yypt:hotellist", "游由平台查询酒店列表限流"),
    /**
     * 游由平台查询酒店详情列表限流
     */
    YYPT_HOTELINFOR("yypt:hotelinfo", "游由平台查询酒店详情列表限流"),

    /**
     * 推送酒店静态数据
     */
    PUSH_HOTELINFO("push:hotelinfo", "推送酒店静态数据"),

    /**
     * 推送酒店最低价
     */
    PUSH_HOTEPRICE("push:hotelprice", "推送酒店最低价"),


    /**
     * 酒店映射推送
     */
    PUSH_MAPPING("push:hotelmapping", "酒店映射推送"),
    ;
    /**
     * key
     */
    private final String bKey;

    /**
     * desc
     */
    private final String desc;

    private BusinessKeyEnum(String bKey, String desc) {
        this.bKey = bKey;
        this.desc = desc;
    }

    /**
     * e
     * @return s
     */
        public String getbKey() {
        return bKey;
    }

    /***
     * s
     * @param bKey k
     */


    public String getDesc() {
        return desc;
    }


}
    