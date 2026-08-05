package cn.vetech.center.hotel.link.enums;

/**
 * <p>
 * 缓存常量信息定义
 * 前缀定义规则 项目名称_版本_业务_房源商编号
 * </p>
 *
 * @author wangkai
 * @since 2020/10/22
 */
public enum CachePrefix {

    /**
     * 初始化方法的缓存时间
     */
    INIT_CACHE_PREFIX("ve-hotel-link_v1020_init_", "初始化方法的缓存时间,防止并发初始化"),

    /**
     * 酒店国家基础信息缓存
     */
    COUNTRY_CACHE_PREFIX("ve-hotel-link_v1020_country_", "酒店国家数据缓存前缀"),

    /**
     * 酒店城市基础信息缓存
     */
    CITY_CACHE_PREFIX("ve-hotel-link_v1020_city_", "酒店城市数据缓存前缀"),
    /**
     * 酒店地区数据缓存前缀
     */
    AREA_CACHE_PREFIX("ve-hotel-link_v1020_area_", "酒店地区数据缓存前缀"),
    /**
     * 国际酒店地区数据缓存前缀
     */
    AREA_GJ_CACHE_PREFIX("ve-hotel-link_v1020_area_gj_", "国际酒店地区数据缓存前缀"),
    /**
     * 酒店行政区数据缓存前缀
     */
    ADMINISTRATIVE_CACHE_PREFIX("ve-hotel-link_v1020_ADMINISTRATIVE_", "酒店行政区数据缓存前缀"),

    /**
     * 酒店商业圈数据缓存
     */
    BUSINESS_CACHE_PREFIX("ve-hotel-link_v1020_BUSINESS_", "酒店商业圈数据缓存"),

    /**
     * 酒店品牌数据缓存前缀
     */
    BRAND_CACHE_PREFIX("ve-hotel-link_v1020_brand_", "酒店品牌数据缓存前缀"),

    /**
     * 酒店设施数据缓存前缀
     */
    FACILITIE_CACHE_PREFIX("ve-hotel-link_v1020_facilitie_", "酒店设施数据缓存前缀"),

    /**
     * 酒店省份数据缓存前缀
     */
    PROVINCE_CACHE_PREFIX("ve-hotel-link_v1020_province_", "酒店省份数据缓存前缀"),
    /**
     * 国际酒店国家基础信息缓存
     */
    COUNTRY_GJ_CACHE_PREFIX("ve-hotel-link_v1020_country_gj_", "酒店国家国际数据缓存前缀"),
    /**
     * 国际酒店城市基础信息缓存
     */
    CITY_GJ_CACHE_PREFIX("ve-hotel-link_v1020_city_gj_", "酒店城市国际数据缓存前缀"),

    /**
     * 酒店评级缓存前缀
     */
    HOTEL_LEVEL_CACHE_PREFIX("ve-hotel-link_hotel_level_", "酒店评级缓存前缀"),
    /**
     * 酒店限流
     */
    RATE_LIMIT("ve-hotel-link_rate_limit:", "限流前缀"),
    /***
     * 国家编码
     */
    LANGUAGE_CODE("ve-hotel-link_code_language_", "国际酒店本地国家代码"),
    /***
     * 城市的所有酒店ID
     */
    CITY_HOTELID("ve-hotel-link_hotelid_city_", "城市的所有酒店ID"),

    /***
     * 城市的所有酒店ID
     */
    CITY_HOTELID_JG("ve-hotel-link_hotelid_city_gj_", "国际城市的所有酒店ID"),
    /**
     * 房型信息缓存前缀
     */
    HOTEL_ROOM_CACHE_PREFIX("ve-hotel-link_hotel_room_", "酒店房型信息缓存前缀"),
    /**
     * 酒店会员前缀
     */
    HOTEL_MEMBER_CACHE_PREFIX("hotel_member_cache_prefix","酒店会员前缀"),
    /***
     * 城市的所有酒店ID
     */
    CITY_HOTELLIST("ve-hotel-link_city_hotellist", "城市的所有酒店ID"),
    /***
     * 别样红酒店ID集合
     */
    BYH_HOTEL_ID_SET("ve-hotel-link_byh_hotel_ids_", "别样红酒店ID集合"),
    ;

    /**
     * key前缀
     */
    private String keyPrefix;

    /**
     * 描述
     */
    private String desc;

    CachePrefix(String keyPrefix, java.lang.String desc) {
        this.keyPrefix = keyPrefix;
        this.desc = desc;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public String getDesc() {
        return desc;
    }

}
