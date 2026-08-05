package cn.vetech.center.hotel.link.api.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 早餐份数转中文
 */
public enum FreeMealEnum {
    /**
     *
     */
    NO("0", "无", "B", "B_0", "无早"),
    /**
     *
     */
    ONE("1", "单", "B", "B_1", "单早"),
    /**
     *
     */
    TWO("2", "双", "B", "B_2", "双早"),
    /**
     *
     */
    THREE("3", "三", "B", "B_3", "三早"),
    /**
     *
     */
    FOUR("4", "四", "B", "B_4", "四早"),
    /**
     *
     */
    FIVE("5", "五", "B", "B_5", "五早"),
    /**
     *
     */
    SIX("6", "六", "B", "B_6", "六早"),
    /**
     *
     */
    SEVEN("7", "七", "B", "B_7", "七早"),
    /**
     *
     */
    EIGHT("8", "八", "B", "B_8", "八早"),
    /**
     *
     */
    NINE("9", "九", "B", "B_9", "九早"),
    /**
     *
     */
    TEN("10", "十", "B", "B_10", "十早"),
    /**
     *
     */
    Q("11", "其他", "B", "Q", "其他"),
    /**
     *
     */
    H("12", "含", "B", "H", "含早"),

    ;
    /**
     * 份数
     */
    private final String amount;
    /**
     * 中文简称
     */
    private final String name;
    /**
     * 类型，早B中L晚D其他Q，默认为早
     */
    private final String type;
    /**
     * 早餐code
     */
    private final String code;
    /**
     * 早餐描述
     */
    private final String desc;

    /**
     *
     */
    private static Map<String, FreeMealEnum> map = new HashMap<>();

    static {
        for (FreeMealEnum c : FreeMealEnum.values()) {
            map.put(c.amount, c);
        }
    }

    private FreeMealEnum(String amount, String name, String type, String code, String desc) {
        this.amount = amount;
        this.name = name;
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取指定value值的枚举实例
     *
     * @param amount amount
     * @return enum
     */
    public static FreeMealEnum instance(String amount) {
        if (StringUtils.isBlank(amount)) {
            return FreeMealEnum.NO;
        }
        FreeMealEnum e = map.get(amount);
        if (e == null) {
            return FreeMealEnum.NO;
        } else {
            return e;
        }
    }

    /**
     * 通过无早或单早等获取对应早餐枚举
     * @param desc 供应商早餐只返回无早或有早等描述
     * @return FreeMealEnum
     */
    public static FreeMealEnum getFreeMealByDesc(String desc){
        FreeMealEnum mealEnum = FreeMealEnum.Q;
        if (StringUtils.isBlank(desc)){
            return mealEnum;
        }
        FreeMealEnum[] values = FreeMealEnum.values();
        for (FreeMealEnum value : values) {
            if (StringUtils.equals(desc,value.desc)){
                mealEnum = value;
                break;
            }
        }
        return mealEnum;
    }

    /**
     * 通过无早或单早等获取对应早餐枚举
     *
     * @param desc 供应商早餐只返回无早或有早等描述
     * @return FreeMealEnum
     */
    public static FreeMealEnum freeMealByDesc(String desc) {
        FreeMealEnum mealEnum = null;
        if (StringUtils.isBlank(desc)) {
            return mealEnum;
        }
        FreeMealEnum[] values = FreeMealEnum.values();
        for (FreeMealEnum value : values) {
            if (StringUtils.equals(desc, value.desc)) {
                mealEnum = value;
                break;
            }
        }
        return mealEnum;
    }

    /**
     * 通过B_0获取当前枚举
     * @param code
     * @return
     */
    public static FreeMealEnum getFreeMealByCode(String code) {
        FreeMealEnum mealEnum = FreeMealEnum.Q;
        if (StringUtils.isBlank(code)) {
            return mealEnum;
        }
        FreeMealEnum[] values = FreeMealEnum.values();
        for (FreeMealEnum value : values) {
            if (StringUtils.equals(code, value.code)) {
                mealEnum = value;
                break;
            }
        }
           return mealEnum;
    }

    public String getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, FreeMealEnum> getMap() {
        return map;
    }
}
