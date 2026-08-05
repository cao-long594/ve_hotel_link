package cn.vetech.center.hotel.link.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengwanshan
 * @since 2024/3/11 15:10
 */
public enum AdditionalChargeEnum {
    /**
     * 强制性–在预订创建时会收取房费之外的附加费用
     */
    MANDATORY("Mandatory", "已计算到房费总价中", "Surcharges will be charged in addition to room price at time of booking creation"),
    /**
     * 可选–如果在预订请求中提交，则将收取附加费用
     */
    OPTIONAL("Optional", "可选附加费(默认不选)", "Surcharges will be charged if submitted in booking request"),
    /**
     * 已排除-在创建预订时不收取附加费；客户将直接在酒店支付附加费
     */
    EXCLUDED("Excluded", "客人需要到酒店支付附加费", "Surcharges are not charged at time of booking creation; customers will pay the surcharges at a hotel directly"),
    ;

    private AdditionalChargeEnum(String code, String name, String enName) {
        this.code = code;
        this.name = name;
        this.enName = enName;
    }

    /***
     * code
     */
    private final String code;
    /***
     * name
     */
    private final String name;
    /***
     * enName
     */
    private final String enName;
    /**
     * MAP
     */
    private static Map<String, AdditionalChargeEnum> map = new HashMap<>();

    static {
        for (AdditionalChargeEnum c : AdditionalChargeEnum.values()) {
            map.put(c.code, c);
        }
    }

    /**
     * 获取指定code值的枚举实例
     *
     * @param code 编码
     * @return enum
     */
    public static AdditionalChargeEnum instance(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEnName() {
        return enName;
    }
}
