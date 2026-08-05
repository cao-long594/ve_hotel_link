package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2023-03-13 15:41
 */
public enum HotelJdpzEnum {

    /**
     * 金牌
     */
    JP("5","金牌"),
    /**
     * 特牌
     */
    TP("6","特牌"),
    ;

    private final String val;

    private final String desc;

    public String getVal() {
        return val;
    }



    public String getDesc() {
        return desc;
    }



    private HotelJdpzEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
