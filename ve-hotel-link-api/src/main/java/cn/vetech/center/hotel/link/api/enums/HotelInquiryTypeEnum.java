package cn.vetech.center.hotel.link.api.enums;

/**
 * @author xiaotengyu
 * @since 2022-05-09 10:48
 */
public enum HotelInquiryTypeEnum {

    /**
     * 酒店询价
     */
    T0("0","酒店询价"),
    /**
     * 会务询价
     */
    T1("1","会务询价"),
    /**
     * 线下备案 只推cps，不推其他平台
     */
    T2("2","线下备案"),

    ;

    private HotelInquiryTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 类型code
     */
    private final String code;
    /**
     * 类型名称
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }


}
