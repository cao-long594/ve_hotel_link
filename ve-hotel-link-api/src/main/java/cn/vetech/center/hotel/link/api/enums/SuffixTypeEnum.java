package cn.vetech.center.hotel.link.api.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 担保规则和取消规则枚举
 *
 * @author gaojin
 */
public enum SuffixTypeEnum {
    /**
     * 11必须担保
     */
    MUST_GUARAN("11", "必须担保"),
    /**
     * 12条件触发担保
     */
    CONDITION_GUARAN("12", "条件触发担保"),
    /**
     * 13无需担保
     */
    NO_GUARAN("13", "无需担保"),
    /**
     * 21不可取消
     */
    NOT_CANCEL("21", "此订单一经确认不得取消修改，若未入住或取消修改订单将收取您全部房费。"),
    /**
     * 22限时取消
     */
    TIME_CANCEL("22", "限时取消"),
    /**
     * 23免费取消
     */
    FREE_CANCEL("23", "免费取消"),
    /**
     * 24无取消规则
     */
    NO_CANCEL("24", "无取消规则"),
    /**
     * 25收费取消
     */
    FEE_CANCEL("25", "收费取消")
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;
    /**
     *
     */
    private static Map<String, SuffixTypeEnum> map = new HashMap<String, SuffixTypeEnum>();

    static {
        for (SuffixTypeEnum c : SuffixTypeEnum.values()) {
            map.put(c.code, c);
        }
    }

    private SuffixTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param code code
     * @return enum
     */
    public static SuffixTypeEnum instance(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }



    public static Map<String, SuffixTypeEnum> getMap() {
        return map;
    }
}
