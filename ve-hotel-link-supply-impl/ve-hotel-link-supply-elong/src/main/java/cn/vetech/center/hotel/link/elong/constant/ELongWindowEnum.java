package cn.vetech.center.hotel.link.elong.constant;

import cn.vetech.center.hotel.link.api.enums.HotelWinTypeEnum;
import cn.vetech.center.hotel.link.enums.HotelWindowTypeEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 艺龙窗户枚举
 * 84	无窗	No Window
 * 85	部分无窗	No window in part rooms
 * 677	有窗	Window
 * 897	内窗	Inner window
 * 898	天窗	skylight
 * 899	封闭窗	Closed window
 * 900	飘窗	None
 * 2268	落地窗	French Window
 * 2269	装饰性假窗	Decorative Window
 * 2270	窗户较小	Small Window
 * 2271	窗外有墙体或遮挡	obstructions outside the window
 * 2272	部分内窗	Part Inner window
 * 2273	部分天窗	Part skylight
 * 2274	部分封闭窗	Part Closed window
 * 2275	部分窗户较小	Part Small window
 * 2276	部分窗外有墙体或遮挡	Part obstructions outside the window
 * 2277	部分装饰性假窗	Part Decorative Window
 * 2278	部分飘窗	Part Bay Window
 * 2279	部分落地窗	Part French Window
 * https://open.elong.com/doc/info/cn-main-const-bed_list
 * 艺龙告知除了 无窗、部分无窗，都可归到有窗里
 *
 * @author luqs
 * @version v1.0
 **/
public enum ELongWindowEnum {
    /**
     * 84：无窗
     */
    NO_WINDOW("84", "无窗", HotelWinTypeEnum.NO_WINDOW.getCode(), ""),
    /**
     * 85：部分窗
     */
    PART_WINDOW("85", "部分无窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), ""),
    /**
     * 677：有窗
     */
    HAS_WINDOW("677", "有窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), ""),
    /**
     * 897：内窗
     */
    INNER_WINDOW("897", "内窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.CXZL.getBh()),
    /**
     * 898：天窗
     */
    SKY_LIGHT("898", "天窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.TC.getBh()),
    /**
     * 899：封闭窗
     */
    CLOSED_WINDOW("899", "封闭窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.CLOSED_WIN.getBh()),
    /**
     * 900：飘窗
     */
    NONE("900", "飘窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), ""),
    /**
     * 2268：落地窗
     */
    FRENCH_WINDOW("2268", "落地窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), ""),
    /**
     * 2269：装饰性假窗
     */
    DECORATIVE_WINDOW("2269", "装饰性假窗", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.ZSXJC.getBh()),
    /**
     * 2270：窗户较小
     */
    SMALL_WINDOW("2270", "窗户较小", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.CHJX.getBh()),
    /**
     * 2271：窗外有墙体或遮挡
     */
    OBSTRUCTIONS_OUTSIDE_THE_WINDOW("2271", "窗外有墙体或遮挡", HotelWinTypeEnum.HAVE_WINDOW.getCode(), HotelWindowTypeEnum.CWSQT.getBh()),
    /**
     * 2272：部分内窗
     */
    PART_INNER_WINDOW("2272", "部分有窗且位于走廊或过道", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.CXZL.getBh()),
    /**
     * 2273：部分天窗
     */
    PART_SKYLIGHT("2273", "部分有窗且为天窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.TC.getBh()),
    /**
     * 2274：部分封闭窗
     */
    PART_CLOSED_WINDOW("2274", "部分有窗且为封闭窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.CLOSED_WIN.getBh()),
    /**
     * 2275：部分窗户较小
     */
    PART_SMALL_WINDOW("2275", "部分有窗且窗户较小", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.CHJX.getBh()),
    /**
     * 2276：部分窗外有墙体或遮挡
     */
    PART_OBSTRUCTIONS_OUTSIDE_THE_WINDOW("2276", "部分有窗且窗外有墙体或遮挡", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.CWSQT.getBh()),
    /**
     * 2277：部分装饰性假窗
     */
    PART_DECORATIVE_WINDOW("2277", "部分有窗且为装饰性假窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), HotelWindowTypeEnum.ZSXJC.getBh()),
    /**
     * 2278：部分飘窗
     */
    PART_BAY_WINDOW("2278", "部分有窗且为飘窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), ""),
    /**
     * 2279：部分落地窗
     */
    PART_FRENCH_WINDOW("2279", "部分有窗且为落地窗", HotelWinTypeEnum.PARTIAL_WINDOW.getCode(), "");

    /**
     * 根据编号获取对应窗户枚举
     *
     * @param code 供应商窗户编号
     * @return ELongWindowEnum
     */
    public static ELongWindowEnum getELongWindowEnumByCode(String code) {
        for (ELongWindowEnum windowEnum : ELongWindowEnum.values()) {
            if (windowEnum.getCode().equals(code)) {
                return windowEnum;
            }
        }
        return ELongWindowEnum.NO_WINDOW;
    }

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    /**
     * 标准数据窗户 HotelWinTypeEnum
     */
    private final String ch;
    /**
     * 标准数据窗户类型 HotelWindowTypeEnum
     */
    private final String chlx;

    ELongWindowEnum(String code, String name, String ch, String chlx) {
        this.code = code;
        this.name = name;
        this.ch = ch;
        this.chlx = chlx;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCh() {
        return ch;
    }

    public String getChlx() {
        return chlx;
    }

    /**
     * 包含
     *
     * @param eLongWindowEnums 窗户类型
     * @param code             code
     * @return boolean
     */
    public static boolean contain(ELongWindowEnum[] eLongWindowEnums, String code) {
        if (ArrayUtils.isEmpty(eLongWindowEnums) || StringUtils.isBlank(code)) {
            return false;
        }
        for (ELongWindowEnum eLongWindowEnum : eLongWindowEnums) {
            if (StringUtils.equals(eLongWindowEnum.getCode(), code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据code获取名称
     *
     * @param code code
     * @return name
     */
    public static String getNameByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return StringUtils.EMPTY;
        }
        return Arrays.stream(ELongWindowEnum.values())
                .filter(win -> StringUtils.equalsIgnoreCase(win.getCode(), code))
                .findFirst().map(win -> win.getName())
                .orElse(StringUtils.EMPTY);
    }
}