package cn.vetech.center.hotel.link.enums;

/**
 * 订单退单申请类型
 *
 * @author 6161
 * @date 2024/12/24
 */
public enum OrderApplyTypeEnum {
    CANCEL("1", "超时取消"),
    EARLY_DEPARTURE("2", "提前离店");
    private String code;
    private String name;

    OrderApplyTypeEnum(String code, String name) {
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
