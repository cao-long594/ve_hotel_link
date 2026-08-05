package cn.vetech.center.hotel.link.api.enums;

/**
 * T0 待审核;T1 已审核待退款;T2 已退款;T3 取消
 * 退单状态枚举
 *
 * @author 6161
 * @date 2024/12/18
 */
public enum HotelRefundOrderStatusEnum {
    WAIT_REVIEW("T0", "待审核"),
    WAIT_REFUND("T1", "已审核待退款"),
    REFUNDED("T2", "已退款"),
    CANCEL("T3", "取消"),
    ;
    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    HotelRefundOrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
