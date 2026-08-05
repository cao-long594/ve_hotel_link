package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2022-08-01 11:22
 */
public enum  AsmsFplxEnum {

    /**
     * 服务商
     */
    FP1("1","服务商"),
    /**
     * 酒店
     */
    FP0("0","酒店"),
    ;

    /**
     * code
     */
    private final String code;
    /**
     * desc
     */
    private final String desc;

    public String getCode() {
        return code;
    }



    public String getDesc() {
        return desc;
    }



    private AsmsFplxEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

