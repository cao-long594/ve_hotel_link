package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2022-08-22 19:05
 */
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelPolicyEnum {
    /**
     * 入住离店
     */
    CheckInCheckOut("CheckInCheckOut", "入离时间"),
    /**
     * 儿童
     */
    Child("Child", "儿童政策"),
    /**
     * 早餐
     */
    Meal("Meal", "早餐信息"),
    /**
     * 宠物
     */
    Pet("Pet", "宠物"),
    /**
     * 费用，押金等费用信息
     */
    FEE_DESCRIPTION("FeeDescription", "押金"),
    /**
     * 入住须知
     */
    INSTRUCTIONS("Instructions", "入住须知"),
    /**
     * 语言
     */
    LANGUAGE("Language", "语言"),
    /**
     * 进入方式
     */
    ENTRYTYPE("EntryType", "进入方式"),
    /**
     * 强制收费，押金、税费等
     */
    MANDATORY_FEES("MandatoryFees", "强制收费"),
    /**
     * 费用，停车费、餐食费等可选择性的费用信息
     */
    OPTIONAL_FEES("OptionalFees", "费用信息");

    private final String code;
    private final String title;

    /**
     * 静态映射：code -> 枚举对象（类加载时初始化一次）
     */
    private static final Map<String, HotelPolicyEnum> CODE_ENUM_MAP;

    // 静态代码块：初始化code与枚举的映射关系
    static {
        CODE_ENUM_MAP = Arrays.stream(HotelPolicyEnum.values())
                .collect(Collectors.toMap(
                        HotelPolicyEnum::getCode,
                        Function.identity()
                ));
    }

    HotelPolicyEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    // ===================== 核心静态工具方法 =====================
    /**
     * 根据code获取对应的title
     * @param code 策略编码
     * @return 标题，找不到返回null
     */
    public static String getTitleByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return StringUtils.EMPTY;
        }
        HotelPolicyEnum policyEnum = CODE_ENUM_MAP.get(code);
        return policyEnum == null ? null : policyEnum.getTitle();
    }

    /**
     * 根据code获取枚举对象
     */
    public static HotelPolicyEnum getByCode(String code) {
        return CODE_ENUM_MAP.get(code);
    }
    // ==========================================================

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}