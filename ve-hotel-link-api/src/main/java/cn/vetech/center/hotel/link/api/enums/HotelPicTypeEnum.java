package cn.vetech.center.hotel.link.api.enums;

/**
 * 酒店图片类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelPicTypeEnum {
    /**
     * 0：展示图
     */
    DISPLAY_PIC("0", "展示图"),
    /**
     * 1：餐厅
     */
    HALL("1", "餐厅"),
    /**
     * 2：休闲室
     */
    LOUNGE("2", "休闲室"),
    /**
     * 3：会议室
     */
    MEETING_ROOM("3", "会议室"),
    /**
     * 4：服务
     */
    SERVICE("4", "服务"),
    /**
     * 5：酒店外观
     */
    HOTEL_EXTERIOR("5", "酒店外观"),
    /**
     * 6：大堂/接待台
     */
    LOBBY("6", "大堂/接待台"),
    /**
     * 7：酒店介绍
     */
    HOTEL_INTRODUCTION("7", "酒店介绍"),
    /**
     * 8：房型
     */
    ROOM_TYPE("8", "房型"),
    /**
     * 9：背景图
     */
    BACKGROUND_IMG("9", "背景图"),
    /***
     * 11 公共区域
     */
    PUBLIC_AREA("11", "公共区域"),
    /***
     *  12 周边景点
     */
    NEARBY_ATTRACTIONS("12", "周边景点"),
    /**
     * 10：其他
     */
    OTHER("10", "其他"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelPicTypeEnum(String code, String name) {
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
