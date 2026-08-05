package cn.vetech.center.hotel.link.api.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 酒店类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelTypeEnum {
    /**
     * 0：经济型
     */
    ECONOMY_HOTEL("0", "经济型"),
    /**
     * 1：快捷酒店
     */
    EXPRESS_INN("1", "快捷酒店"),
    /**
     * 2：商务酒店
     */
    BUSINESS_HOTEL("2", "商务酒店"),
    /**
     * 3：主题酒店
     */
    THEME_HOTEL("3", "主题酒店"),
    /**
     * 4：情侣酒店
     */
    LOVE_HOTEL("4", "情侣酒店"),
    /**
     * 5：公寓
     */
    APARTMENT("5", "公寓"),
    /**
     * 6：客栈
     */
    TAVERN("6", "客栈"),
    /**
     * 7：民宿
     */
    HOMESTAY("7", "民宿"),
    /**
     * 8：青年旅社
     */
    YOUTH_HOTEL("8", "青年旅社"),
    /**
     * 9：农家院
     */
    FARMYARD("9", "农家院"),
    /**
     * 10：家庭旅馆
     */
    FAMILY_INN("10", "家庭旅馆"),
    /**
     * 11：招待所
     */
    GUEST_HOUSE("11", "招待所"),
    /**
     * 12：度假酒店
     */
    VACATION_HOTEL("12", "度假酒店"),
    /**
     * 13：别墅
     */
    VILLA("13", "别墅"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelTypeEnum(String code, String name) {
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
     *
     */
    private static final Map<String, String> map = new HashMap<>();

    static {
        for (HotelTypeEnum hotelTypeEnum : HotelTypeEnum.values()) {
            map.put(hotelTypeEnum.name, hotelTypeEnum.getCode());
        }
    }

    /**
     * @param name name
     * @return String
     */
    public static String getCodeByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return map.get(name);
    }

    /**
 * 默认返回
     *
     * @param name name
     * @return String
     */
    public static String getCodeByNameDefaultIfBlank(String name, HotelTypeEnum hotelTypeEnum) {
        String codeByName = getCodeByName(name);
        if (StringUtils.isBlank(codeByName) && hotelTypeEnum != null) {
            return hotelTypeEnum.code;
        }
        return codeByName;
    }
}
