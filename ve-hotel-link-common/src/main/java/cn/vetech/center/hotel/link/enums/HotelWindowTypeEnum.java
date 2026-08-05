package cn.vetech.center.hotel.link.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author zoujiming
 * @since 2019/1/30
 */
public enum HotelWindowTypeEnum {
    /**
     * 0：朝向走廊
     */
    CXZL("0", "朝向走廊"),
    /**
     * 1：装饰性假窗
     */
    ZSXJC("1", "装饰性假窗"),
    /**
     * 2：天窗
     */
    TC("2", "天窗"),
    /**
     * 3：窗户较小
     */
    CHJX("3", "窗户较小"),
    /**
     * 4：窗外是墙体
     */
    CWSQT("4", "窗外是墙体"),
    /**
     * 5：封闭窗
     */
    CLOSED_WIN("5", "封闭窗"),
    /**
     * 6：飘窗
     */
    BAY_WINDOW("6", "飘窗"),
    /**
     * 7：落地窗
     */
    FRENCH_WINDOW("7", "落地窗"),
    /**
     * 8：内窗
     */
    INNER_WINDOW("8", "内窗"),
    ;
    /**
     * 编号
     */
    private final String bh;
    /**
     * 名称
     */
    private final String mc;
    /**
     *
     */
    private static Map<String, HotelWindowTypeEnum> map = new HashMap<>();

    static {
        for (HotelWindowTypeEnum hotelWindowTypeEnum : HotelWindowTypeEnum.values()) {
            map.put(hotelWindowTypeEnum.getBh(), hotelWindowTypeEnum);
        }
    }

    private HotelWindowTypeEnum(String bh, String mc) {
        this.bh = bh;
        this.mc = mc;
    }

    public String getBh() {
        return bh;
    }


    public String getMc() {
        return mc;
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
        return Arrays.stream(HotelWindowTypeEnum.values())
                .filter(win -> StringUtils.equalsIgnoreCase(win.getBh(), code))
                .findFirst().map(HotelWindowTypeEnum::getMc)
                .orElse(StringUtils.EMPTY);
    }

    /**
     * @param bh 编号
     * @return HotelWindowTypeEnum
     */
    public static HotelWindowTypeEnum instance(String bh) {
        return map.get(bh);
    }
}