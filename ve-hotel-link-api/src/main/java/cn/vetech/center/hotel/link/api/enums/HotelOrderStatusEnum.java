package cn.vetech.center.hotel.link.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 酒店订单状态枚举
 * 供应商确认前 cps订单状态，cps订单状态说明
 *
 * @author gaojin
 */
public enum HotelOrderStatusEnum {
    /**
     * 已预订，未确认
     */
    BOOK_NOT_CONFIRM("1", "已预订，未确认"),
    /**
     * 采购取消（确认前）
     */
    CUSTOMER_CANCEL_NOT_CONFIRM("1A", "采购取消（确认前）"),
    /**
     * 平台拒单（确认前）
     */
    SYSTEM_CANCEL_NOT_CONFIRM("1B", "平台拒单（确认前）"),
    /**
     * 供应拒单（确认前）
     */
    SUPPLY_CANCEL_NOT_CONFIRM("1C", "供应拒单（确认前）"),
    /**
     * 供应商确认超时，订单自动取消
     */
    TIMEOUT_CANCEL_NOT_CONFIRM("1D", "供应商确认超时，订单自动取消"),
    /**
     * 供应商确认后
     */
    BOOK_CONFIRM("2", "已确认"),
    /**
     * 采购取消（确认后）
     */
    CUSTOMER_CANCEL_CONFIRM("2A", "采购取消（确认后）"),
    /**
     * 平台拒单（确认后）
     */
    SYSTEM_CANCEL_CONFIRM("2B", "平台拒单（确认后）"),
    /**
     * 供应拒单（确认后）
     */
    SUPPLY_CANCEL_CONFIRM("2C", "供应拒单（确认后）"),


    /*****************************预付*********************************/
    /**
     * 采购支付前
     */
    BOOK_NOT_PAY("6", "已预订未支付"),
    /**
     * 采购取消（支付前）
     */
    CUSTOMER_CANCEL_NOT_PAY("6A", "采购取消（支付前）"),
    /**
     * 平台拒单（支付前）
     */
    SYSTEM_CANCEL_NOT_PAY("6B", "平台拒单（支付前）"),
    /**
     * 采购支付超时，订单自动取消
     */
    TIMEOUT_CANCEL_NOT_PAY("6C", "采购支付超时，订单自动取消"),
    /**
     * 支付后，确认前
     */
    PAY_BEFORE_CONFIRM("7", "已支付 待确认"),
    /**
     * 供应拒单（确认前）/待转单
     */
    SUPPLY_CANCEL_BEFORE_CONFIRM("7A", "供应拒单（确认前）"),
    /**
     * 已转单待确认
     */
    TURN_SUCCESS_BEFORE_CONFIRM("7B", "已转单待确认"),
    /**
     * 转单失败拒单（确认前）
     */
    TURN_FALSE_BEFORE_CONFIRM("7C", "转单失败拒单（确认前）"),
    /**
     * 7D", "平台拒单（确认前）
     */
    SYSTEM_CANCEL_BEFORE_CONFIRM("7D", "平台拒单（确认前）"),
    /**
     * 供应商确认超时，订单自动取消
     */
    TIMEOUT_CANCEL_BEFORE_CONFIRM("1D", "供应商确认超时，订单自动取消"),
    /**
     * 已支付已确认
     */
    PAY_AFTER_CONFIRM("8", "已支付已确认"),
    /**
     * 已转单已确认
 */
    TURN_SUCCESS_AFTER_CONFIRM("8A", "已转单已确认"),
    /**
     * 采购取消（确认后）
     */
    CUSTOMER_CANCEL_AFTER_CONFIRM("8B", "采购取消（确认后）"),
    /**
     * 供应拒单（确认后）
     */
    SUPPLY_CANCEL_AFTER_CONFIRM("8C", "供应拒单（确认后）"),
    /**
     * 已转单待确认
     */
    TURN_SUCCESS_NOT_CONFIRM("8D", "已转单待确认"),
    /**
     * 平台拒单（确认后）
     */
    SYSTEM_CANCEL_AFTER_CONFIRM("8E", "平台拒单（确认后）"),
    /**
     * 转单失败拒单（确认后）
     */
    TURN_FALSE_AFTER_CONFIRM("8F", "转单失败拒单（确认后）"),
    /**
     * 已入住
     */
    LIVE("3", "已入住"),
    /**
     * NOSHOW
     */
    NOSHOW("4", "NOSHOW"),
    /**
     * 已离店
     */
    OUT("5", "已离店");


    /**
     * 代码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;
    /**
     * MAP
     */
    private static Map<String, HotelOrderStatusEnum> map = new HashMap<>();

    static {
        for (HotelOrderStatusEnum c : HotelOrderStatusEnum.values()) {
            map.put(c.code, c);
        }
    }

    /**
     * @param code 代码
     * @param name 名称
     */
    private HotelOrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param code 接口编号
     * @return enum
     */
    public static HotelOrderStatusEnum instance(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    public static Map<String, HotelOrderStatusEnum> getMap() {
        return map;
    }

}