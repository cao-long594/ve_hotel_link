package cn.vetech.center.hotel.link.enums;

/**
 * 供应商原始订单状态转换成标准的订单状态枚举
 *
 * @author chengwanshan
 * @since 2021/7/8 11:37
 */
public enum HotelGysOrderStatusEnum {
    /**
     * 待确认（未支付/处理中）
     */
    BEFORE_CONFIRM("1", "待确认"),
    /**
     * 已确认（可以直接办理入住）
     */
    AFTER_CONFIRM("2", "已确认"),
    /**
     * 取消中
     * 供应商状态（取消中），公共方法中暂不修改订单状态，如果有取消中相关场景需要处理，请使用LinkHotelOrderDetailVO的isCanceling字段
     */
    CANCELING("3", "取消中"),
    /**
     * 已取消
     */
    CANCEL("4", "已取消"),
    /**
     * 已入住
     */
    LIVE("5", "已入住"),
    /**
     * NOSHOW（过了预定时间未到）
     * 注意：预付订单需要向供应商确认NOSHOW是否收取费用，公共方法中处理逻辑：如果供应商不收取费用，则对应"已取消"，收取费用，则对应"已确认"
     */
    NOSHOW("6", "NOSHOW"),
    /**
     * 已离店
     */
    OUT("7", "已离店"),
    /**
     * 异常，可选用情况（1、供应商订单状态有异常状态需要人工确认的；    2、同步订单状态异常的）
     */
    ERROR("-1", "异常"),
    ;

    /**
     * 代码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    


    /**
     * @param code 代码
     * @param name 名称
     */
    private HotelGysOrderStatusEnum(String code, String name) {
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
