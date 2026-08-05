package cn.vetech.center.hotel.link.enums.orderreminder;

/**
 * 酒店订单催单状态码
 *
 * @author chengwanshan
 * @since 2024/6/5 15:31
 */
public enum HotelOrderReminderErrorCodeEnum {
    /**
     * 0 催单成功
     */
    OR_0("OR_0", "催单成功"),
    /**
     * 1 系统繁忙，请稍后重试
     */
    OR_1("OR_1", "系统繁忙，请稍后重试"),
    /**
     * 2 催单失败，订单号不存在
     */
    OR_2("OR_2", "催单失败，订单号不存在"),
    /**
     * 3 催单失败，订单非预定中状态
     */
    OR_3("OR_3", "催单失败，订单非预定中状态"),
    /**
     * 4 催单时限内无法催单
     */
    OR_4("OR_4", "催单时限内无法催单"),
    /**
     * 5 已催单，无需重复催单
     */
    OR_5("OR_5", "已催单，无需重复催单"),
    /**
     * 20 其它
     */
    OR_20("OR_20", "其它"),
    /**
     * 30 供应商未实现此接口
     */
    OR_30("OR_30", "供应商未实现此接口"),
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private HotelOrderReminderErrorCodeEnum(String code, String name) {
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
