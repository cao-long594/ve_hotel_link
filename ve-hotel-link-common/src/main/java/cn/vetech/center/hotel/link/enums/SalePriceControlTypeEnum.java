package cn.vetech.center.hotel.link.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 预付-酒店开票控制销售价类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum SalePriceControlTypeEnum {
    /**
     * 0：无控制
     */
    NO_CONTROL("0", "无控制"),
    /**
     * 1：大于等于
     */
    GREATER_EQUAL("1", "大于等于"),
    /**
     * 2：等于
     */
    EQUAL("2", "等于"),
    /**
     * 3：不可销售
     */
    NOT_SALE("3", "不可销售"),
    /**
     * 限制销售价特殊处理
     */
    LTPRICE("4","限制价格销售"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private SalePriceControlTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 是否无控制
     *
     * @param code 编码
     * @return boolean
     */
    public static boolean isNoControl(String code) {
        return StringUtils.isBlank(code) || NO_CONTROL.getCode().equals(code);
    }

    /**
     * 是否大于等于
     *
     * @param code 编码
     * @return boolean
     */
    public static boolean isGreaterEqual(String code) {
        return GREATER_EQUAL.getCode().equals(code);
    }

    /**
     * 是否等于
     *
     * @param code 编码
     * @return boolean
     */
    public static boolean isEqual(String code) {
        return EQUAL.getCode().equals(code);
    }

    /**
     * 是否不可销售
     *
     * @param code 编码
     * @return boolean
     */
    public static boolean isNotSale(String code) {
        return NOT_SALE.getCode().equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
