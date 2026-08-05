package cn.vetech.center.hotel.link.elong.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author pengyefei
 * @version 1.0
 * @since 2023/8/8 17:30
 */
public enum ElongValidateResultCodeEnum {
    /**
     * 验证结果
     * OK:  正常可预订
     * Product：产品无效或关房
     * Inventory：房量不够
     * Rate:价格不符
     */
    OK("OK", "正常可预订"),
    PRODUCT("Product", "产品无效或关房"),
    INVENTORY("Inventory", "房量不够"),
    RATE("Rate", "价格不符"),
    ERROR("", "ResultCode不正确");;
    /**
     * code
     */
    private final String code;
    /**
     * 描述
     */
    private final String name;

    private ElongValidateResultCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据code获取name
     *
     * @param code code
     * @return String
     */
    public static String getNameByCode(String code) {
        for (ElongValidateResultCodeEnum resultCodeEnum : ElongValidateResultCodeEnum.values()) {
            if (StringUtils.equalsIgnoreCase(code, resultCodeEnum.getCode())) {
                return resultCodeEnum.getName();
            }
        }
        return ElongValidateResultCodeEnum.ERROR.getName();
    }
}
