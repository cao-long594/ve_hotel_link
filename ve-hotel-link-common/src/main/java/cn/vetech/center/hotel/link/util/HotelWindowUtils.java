package cn.vetech.center.hotel.link.util;

import cn.vetech.center.hotel.link.base.HotelWindowEnumInterface;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 酒店窗户工具类
 *
 * @author luqs
 * @version v1.0
 **/
public class HotelWindowUtils {

    /**
     * 是否包含
     *
     * @param enums 窗户类型
     * @param code  code
     * @return boolean
     */
    public static boolean contain(HotelWindowEnumInterface[] enums, String code) {
        if (ArrayUtils.isEmpty(enums) || StringUtils.isBlank(code)) {
            return false;
        }
        for (HotelWindowEnumInterface window : enums) {
            if (StringUtils.equals(window.getCode(), code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否相等
     *
     * @param window 窗户类型
     * @param code   code
     * @return boolean
     */
    public static boolean equalWindow(HotelWindowEnumInterface window, String code) {
        return window.getCode().equals(code);
    }
}
