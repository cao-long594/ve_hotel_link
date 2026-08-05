package cn.vetech.center.hotel.link.enums;

/**
 * @author chengwanshan
 * @since 2025/4/3 16:09
 */
public enum OrderTypeEnum {
    /**
     * 2：线上订单
     */
    ONLINE("1", "线上订单"),
    /**
     * 2：线下订单
     */
    OFFLINE("2", "线下订单"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private OrderTypeEnum(String code, String name) {
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
