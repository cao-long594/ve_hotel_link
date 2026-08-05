package cn.vetech.center.hotel.link.api.enums;

import java.util.stream.Stream;

/**
 * @author lipeng
 */
public enum  PtEnum {
    /**
     *
     */
    CPS("cps"),
    /**
     *费控
     */
    CHARGE("charge"),
    /**
     *差旅云
     */
    CLOUD("cloud"),
    /**
     *差旅云
     */
    ASMS("asms"),

    /**
     *独立ES
     */
    HOTELES("hoteles");

    private final String value;

    private PtEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * 校验平台是否存在
     * @param pt 平台名称
     * @return boolean
     */
    public static boolean isExits(String pt){
        return Stream.of(PtEnum.values()).anyMatch(ptEnum -> ptEnum.getValue().equals(pt));
    }
}
