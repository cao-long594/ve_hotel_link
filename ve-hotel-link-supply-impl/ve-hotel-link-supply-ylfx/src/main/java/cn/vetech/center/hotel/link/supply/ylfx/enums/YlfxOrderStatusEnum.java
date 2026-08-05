package cn.vetech.center.hotel.link.supply.ylfx.enums;

import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 订单状态
 * 1. 预订处理中 2. 预订已经确认  3. 预订已经拒绝    4. 取消处理中 5. 取消已经确认 6. 取消已经拒绝
 *
 * @author pengyefei
 * @version 1.0
 * @since 2022/10/26 9:51
 */
public enum YlfxOrderStatusEnum {
    WAIT_CONFIRM(1, "预订处理中", HotelGysOrderStatusEnum.BEFORE_CONFIRM),
    AFTER_CONFIRM(2, "预订已经确认", HotelGysOrderStatusEnum.AFTER_CONFIRM),
    REFUSE(3, "预订已经拒绝", HotelGysOrderStatusEnum.CANCEL),
    CANCELING(4, "取消处理中", HotelGysOrderStatusEnum.CANCELING),
    CANCELLED(5, "取消已经确认", HotelGysOrderStatusEnum.CANCEL),
    ERROR(6, "取消已经拒绝", HotelGysOrderStatusEnum.ERROR);
    /**
     * 供应商订单状态code
     */
    private final Integer gysCode;
    /**
     * 供应商订单状态名称
     */
    private final String gysName;
    /**
     * cps对应状态枚举
     */
    private final HotelGysOrderStatusEnum gysOrderStatusEnum;

    private static final Map<Integer, YlfxOrderStatusEnum> map = new HashMap<>();

    static {
        for (YlfxOrderStatusEnum statusEnum : YlfxOrderStatusEnum.values()) {
            map.put(statusEnum.gysCode, statusEnum);
        }
    }

    /**
     * 根据供应商code获取对应cps状态枚举
     *
     * @param gysCode gysCode
     * @return HotelGysOrderStatusEnum
     */
    public static HotelGysOrderStatusEnum getHotelGysOrderStatusEnum(int gysCode) {
        YlfxOrderStatusEnum statusEnum = map.get(gysCode);
        if (Objects.isNull(statusEnum)){
            return HotelGysOrderStatusEnum.ERROR;
        }
        return statusEnum.getGysOrderStatusEnum();
    }

    /**
     * 根据供应商code获取供应商描述
     *
     * @param gysCode gysCode
     * @return String
     */
    public static String getGysName(int gysCode) {
        YlfxOrderStatusEnum statusEnum = map.get(gysCode);
        if (Objects.isNull(statusEnum)){
            return null;
            }
        return statusEnum.getGysName();
    }

    YlfxOrderStatusEnum(Integer gysCode, String gysName, HotelGysOrderStatusEnum gysOrderStatusEnum) {
        this.gysCode = gysCode;
        this.gysName = gysName;
        this.gysOrderStatusEnum = gysOrderStatusEnum;
    }

    public Integer getGysCode() {
        return gysCode;
    }

    public String getGysName() {
        return gysName;
    }

    public HotelGysOrderStatusEnum getGysOrderStatusEnum() {
        return gysOrderStatusEnum;
    }
}

    