package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2022/1/18 16:55
 */
public enum VipEnum {
    /**
     * 是会员
     */
    YES("1", "是会员价"),
    /**
     * 不是会员
     */
    NO("0", "非会员价"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private VipEnum(String code, String name) {
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
