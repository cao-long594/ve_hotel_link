package cn.vetech.center.hotel.link.enums;

/**
 * @author pengyefei
 * @version 1.0
 * @since 2022/11/30 15:03
 */
public enum PayModeEnum {
    PAY("1","付款"),
    REFUND("2","退款")
    ;
    /**
     * 编码
     */
    private final String code;
    /**
     * 描述
     */
    private final String name;

    private PayModeEnum(String code, String name) {
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
