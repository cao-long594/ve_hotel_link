package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2022/6/9 11:06
 */
public enum HotelFplxEnum {
    /**
     * 专票
     */
    SPECIAL_INVOICE("0", "专票"),
    /**
     * 普票
     */
    GENERAL_INVOICE("1", "普票"),
    /**
     * 普票或专票
     */
    SPECIAL_OR_GENERAL_INVOICE("2", "普票或专票"),
    ;

    private HotelFplxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }


}
