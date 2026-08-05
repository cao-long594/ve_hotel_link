package cn.vetech.center.hotel.link.enums;

/**
 * 酒店重要通知类型
 * 重要提示分类:City,Hotel,PPRooms,FGRooms
 **/
public enum HotelImportantNoticeTypeEnum {
    CITY("City", "城市"),
    HOTEL("Hotel", "酒店"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelImportantNoticeTypeEnum(String code, String name) {
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
