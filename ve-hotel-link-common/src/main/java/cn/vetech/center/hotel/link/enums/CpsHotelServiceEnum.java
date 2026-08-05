package cn.vetech.center.hotel.link.enums;

/**
 * cps服务名
 *
 * @author wangkai
 * @since 2022/5/11
 */
public enum CpsHotelServiceEnum {

    /**
     * 酒店地图分布接口
     */
    HOTEL_GET_MAP_DISTRIBUTION("Hotel_GetMapDistribution", "hotelMap/distribution/search", "酒店地图分布接口", 1, true),
    /**
     * 酒店价格日历
     */
    HOTEL_GET_PRICE_CALENDAR("Hotel_GetPriceCalendar", "hotelPrice/calendar/search", "酒店价格日历", 1, true),
    /**
     * 酒店列表接口
     */
    HOTEL_GETJDLB("Hotel_Getjdlb", "searchList", "酒店列表接口", 1, false),
    /**
     * 酒店数据字典接口
     */
    HOTEL_GETJDSJZD("Hotel_Getjdsjzd", "hotelDataDict/search", "酒店数据字典", 2, true),

    /**
     * 获取酒店基本信息
     */
    HOTEL_GETJDXXXX("Hotel_Getjdxxxx", "hotelDetail/search", "获取酒店基本信息", 2, true),

    /**
     * 酒店房型 暂时不转
     */
    HOTEL_GETJDFX_NEW("Hotel_Getjdfx_new", "", "获取房型信息", 2, true),

    /**
     * 获取单个酒店房型合并相关数据
     */
    HOTEL_ROOM_MERGE_DATA_SEARCH("Hotel_RoomMergeDataSearch", "", "获取单个酒店房型合并相关数据", 2, true),

    /**
     * cps实时价格查询，注：此报价经过冻结过滤、采购/供应过滤、控润等处理，超过设定的等待时长会直接返回（即未超时的价格均会返回）
     */
    HOTEL_CPS_PRICE_SEARCH("Hotel_CpsPriceSearch", "", "cps价格查询", 2, true),

    /**
     * 获取酒店图片
     */
    HOTEL_GETJDTP("Hotel_Getjdtp", "hotelImg/listImg", "获取酒店图片", 2, true),

    /**
     * 获取酒店热门城市
     */
    HOTEL_GETRMCS("Hotel_Getrmcs", "", "获取酒店热门城市", 2, true),

    /**
     * 查询酒店品牌
     */
    HOTEL_GETCDSDATA("Hotel_Getcdsdata", "cdsData/listCdsData", "查询酒店品牌", 2, true),

    /**
     * 查询酒店名称
     */
    HOTEL_GETJDMC("Hotel_Getjdmc", "hotelName/listHotelName", "查询酒店名称", 2, true),

    /**
     * 酒店id转换
     */
    HOTEL_ID_CONVERT("HOTEL_HotelIdConvert", "", "酒店id转换", 2, true),

    /**
     * 查询城市商圈
     */
    GET_CITY_CIRCLE("getCityCircle", "businessCircle/search", "查询城市商圈", 2, true),
    /**
     * 国际酒店地图分布接口
     */
    IHOTEL_GET_MAP_DISTRIBUTION("IHotel_GetMapDistribution", "ihotelMap/distribution/search", "国际酒店地图分布接口", 1, true),
    /**
     * 查询国际酒店列表
     */
    IHOTEL_CDS_SEARCH("ihotel_cds_search", "hotelsearch/searchihotelList", "查询国际酒店列表", 2, true),

    /**
     * 查询国际酒店详情
     */
    IHOTEL_CDS_DETAIL("ihotel_cds_detail", "hotelsearch/searchihotelDetail", "查询国际酒店详情", 2, true),

    /**
     * 查询国际酒店图片
     */
    IHOTEL_CDS_IMAGE("ihotel_cds_image", "hotelsearch/searchihotelImage", "查询国际酒店详情", 2, true),

    /**
     * 查询酒店国际化信息
     */
    HOTEL_GETJDGJHXX("Hotel_Getjdgjhxx", "hotelsearch/searchGlobalInfo", "查询酒店国际化信息", 2, true),

    /**
     * 查询酒店屏蔽价格计划 不用处理
     */
    HOTEL_GETSHIELDRATEPLAN("Hotel_GetshieldRateplan", "", "查询酒店屏蔽价格计划", 2, true),

    /**
     * 获取缓存价格接口
     */
    HOTEL_GETPRICEINFO("Hotel_getPriceInfo", "lowerprice/getPriceInfo", "酒店获取缓存价格接口", 2, true),

    /**
     * 获取酒店中文信息
     */
    HOTEL_GETCHINESEINFO("Hotel_GetChineseInfo", "hotelsearch/searchHotelChineseInfo", "获取酒店中文信息", 2, true),


    /**
     * 重置集团协议
     */
    HOTEL_GROUP_REMARK("Hotel_GroupReMark", "hotelreceive/hotel/sync/hotelGroupReMark", "重置集团协议", 2, true),
    /**
     * 同步协议酒店
     */
    HOTEL_MARK_AGREEMENT("Hotel_markAgreement", "hotelreceive/hotel/sync/hotelMarkAgreement", "标记协议酒店", 2, true),

    /**
     * 同步协议酒店
     */
    HOTEL_AGREEMENT_QUERY("Hotel_agreementQuery", "hotelreceive/hotel/sync/hotelAgreementQuery", "查询协议酒店", 2, true),

    /**
     * 同步协议酒店
     */
    HOTELDTXYJD("HotelDtxyJd", "hotelreceive/hotel/sync/dtPrice", "同步协议酒店", 2, true),
    /**
     * 同步商户推荐度
     */
    HOTEL_CGTBJDTJD("Hotel_CGtbjdtjd", "", "同步商户推荐度", 2, true),
    /**
     * 同步酒店客户评分
     */
    HOTEL_SYNC_CUSTOMER_SCORE("", "hotelreceive/hotel/customerScore/sync", "同步酒店客户评分", 1, true),
    /**
     * 价格计划收集
     */
    HOTEL_JGJHSJ("Hotel_JGjhsj", "lowerprice/collectRatePlan", "价格计划收集", 2, true),
    /**
     * 会议室酒店列表
     */
    HOTEL_CONFERENCE_HOTEL_LIST("Hotel_ConferenceHotelList", "", "查询会议酒店列表", 2, true),
    /**
     * 获取酒店会议室列表
     */
    HOTEL_GET_HYS_LIST("Hotel_GetHysList", "", "获取酒店会议室列表", 2, true),
    /**
     * 查询酒店动态差标
     */
    HOTEL_GET_STANDARD_TRAVELEXPENSE("Hotel_GetStandardTravelExpense","","查询酒店动态差标",2,true)
    ;


    /**
     * 服务名
     */
    private final String serviceName;


    /**
     * 独立es服务后缀
     */
    private final String singleEsSuffixUrl;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 版本
     * 版本越高那么需要的 sfzdles 这个字段值必须大于或等于version这个字段
     */
    private final Integer version;


    /**
     * 由于费控配置的请求地址是
     * /hoteles/open/hotelsearch/
     * 所以部分接口需要去除 hotelsearch/ 再拼接后缀
     * 如果改属性为true 则最后拼接的地址为:  域名/上下文路径/hoteles/open/singleEsSuffixUrl
     * 如果改属性为false 则最后拼接的地址为:  域名/上下文路径/hoteles/open/hotelsearch/singleEsSuffixUrl
     */
    private final boolean trimHotelSearchPath;


    private CpsHotelServiceEnum(String serviceName, String singleEsSuffixUrl, String desc, Integer version, Boolean trimHotelSearchPath) {
        this.serviceName = serviceName;
        this.singleEsSuffixUrl = singleEsSuffixUrl;
        this.desc = desc;
        this.version = version;
        this.trimHotelSearchPath = trimHotelSearchPath;
    }

    public String getServiceName() {
        return serviceName;
    }


    public String getSingleEsSuffixUrl() {
        return singleEsSuffixUrl;
    }


    public String getDesc() {
        return desc;
    }


    public Integer getVersion() {
        return version;
    }


    public boolean getTrimHotelSearchPath() {
        return trimHotelSearchPath;
    }


}