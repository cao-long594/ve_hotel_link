package cn.vetech.center.hotel.link.api.enums;

public enum HotelJkxylxEnum {

    /**
     *接口托管单体协
     */
    DTXY("12", "单体协议"),
    /**
     * 接口托管集团协议
     */
    JTXY("13", "集团协议");

    /**
     * 编号
     */
    private final String code;
    /***
     * 中文名称
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    private HotelJkxylxEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
