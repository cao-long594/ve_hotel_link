package cn.vetech.center.hotel.link.enums;

/**
 * 供应商发票类型  0 普票  1专票    2 普票或专票
 *
 * @author xiaotengyu
 * @since 2021/9/1 20:04
 */
public enum HotelGysFplxEnum {

    /**
     * 普票
     */
    GENERAL_INVOICE("0", "普票"),
    /**
     * 专票
     */
    SPECIAL_INVOICE("1", "专票"),
    /**
     * 普票或专票
     */
    SPECIAL_OR_GENERAL_INVOICE("2", "普票或专票 "),
    ;

    private HotelGysFplxEnum(String code, String name) {
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
