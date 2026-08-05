package cn.vetech.center.hotel.link.api.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;


/**
 * 酒店窗户类型
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelWinTypeEnum {
    /**
     * 0：无窗
     */
    NO_WINDOW("0", "无窗"),

    /**
     * 1：有窗
     */
    HAVE_WINDOW("1", "有窗"),

    /**
     * 2：部分有窗
     */
    PARTIAL_WINDOW("2", "部分有窗"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelWinTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据名称获取对应的code
     * @param name name
     * @return code
     */
    public static String getCodeByName(String name){
        String code = "";
        if (StringUtils.isBlank(name)){
            return code;
        }
        HotelWinTypeEnum[] values = HotelWinTypeEnum.values();
        for (HotelWinTypeEnum value : values) {
            if (StringUtils.equals(name,value.name)){
                code = value.code;
                break;
            }
        }
        return code;
    }

    /**
     * 根据code 获取name
     *
     * @param code code
     * @return name
     */
    public static String getNameByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return StringUtils.EMPTY;
        }
        return Arrays.stream(HotelWinTypeEnum.values())
                .filter(win -> StringUtils.equalsIgnoreCase(win.getCode(), code))
                .findFirst().map(win -> win.getName())
                .orElse(StringUtils.EMPTY);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
