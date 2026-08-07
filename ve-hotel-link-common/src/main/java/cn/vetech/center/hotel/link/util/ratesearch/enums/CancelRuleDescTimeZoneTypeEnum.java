package cn.vetech.center.hotel.link.util.ratesearch.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengwanshan
 * @since 2024/9/27 11:02
 */
public enum CancelRuleDescTimeZoneTypeEnum {
    /**
     * 酒店当地时间
     */
    LOCAL("1", "酒店当地时间"),
    /**
     * 北京时间
     */
    BEIJING("2", "北京时间"),
    /**
     * 指定时区时间，取消规则描述中有明确的时区
     */
    SPECIFY("3", ""),

    ;
    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private CancelRuleDescTimeZoneTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, String> codeNameMap = new HashMap<>();
    static {
        for (CancelRuleDescTimeZoneTypeEnum typeEnum : CancelRuleDescTimeZoneTypeEnum.values()) {
            codeNameMap.put(typeEnum.code, typeEnum.name);
        }
    }

    public static String getNameByCode(String code) {
        return codeNameMap.get(code);
    }
}
