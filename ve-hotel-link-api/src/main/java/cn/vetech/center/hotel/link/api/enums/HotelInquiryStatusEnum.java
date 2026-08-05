package cn.vetech.center.hotel.link.api.enums;

/**
 * @author xiaotengyu
 * @since 2022-04-29 17:15
 */
public enum HotelInquiryStatusEnum {

    /**
     * 待报价
     */
    ST0("0","待报价"),
    /**
     * 已报价待确认
     */
    ST1("1","已报价待确认"),
    /**
     * 已成交
     */
    ST2("2","已成交"),
    /**
     * 已取消
     */
    ST3("3","已取消"),
    /**
     * 未报价自动取消
     */
    ST4("4","未报价自动取消"),
    ;

    private HotelInquiryStatusEnum(String code, String name) {
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
