package cn.vetech.center.hotel.link.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付类型枚举
 * @author chengwanshan
 * @since 2021/3/5 13:51
 */
public enum PaymentEnum {
    /**
     * 0: 现付
     */
    CASH("0", "现付"),
    /**
     * 1：预付
     */
    PREPAID("1", "预付"),
    /**
     * 2：现付/预付
     */
    ALL("2", "现付/预付"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;
    /**
     *
     */
    private static Map<String, PaymentEnum> map = new HashMap<>();

    static {
        for (PaymentEnum p : PaymentEnum.values()) {
            map.put(p.code, p);
        }
    }

    private PaymentEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param code code
     * @return enum
     */
    public static PaymentEnum instance(String code) {
        if (StringUtils.isBlank(code)) {
            return PaymentEnum.PREPAID;
        }
        PaymentEnum e = map.get(code);
        if (e == null) {
            return PaymentEnum.PREPAID;
        } else {
            return e;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Map<String, PaymentEnum> getMap() {
        return map;
    }
}
