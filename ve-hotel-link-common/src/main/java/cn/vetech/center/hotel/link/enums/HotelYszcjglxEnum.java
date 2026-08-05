package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * //因私支持的价格类型yszcjglx：0因私支持现付价格，1因私支持预付价格，2或者空代表因私现付预付都支持，3现付预付全部屏蔽不支持因私查询
 * @since 2023-01-12 11:03
 */
public enum HotelYszcjglxEnum {

    /**
     * 因私支持现付价格
     */
    YSZCXF("0"),
    /**
     *因私支持预付价格
     */
    YSZCYF("1"),
    /**
     * 或者空代表因私现付预付都支持
     */
    YSZCXFYF("2"),
    /**
     *现付预付全部屏蔽不支持因私查询
     */
    YSPB("3"),
    ;

    private final String val;

    private HotelYszcjglxEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }


}
