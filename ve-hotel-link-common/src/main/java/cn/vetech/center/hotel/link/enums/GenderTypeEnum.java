package cn.vetech.center.hotel.link.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * LinkHotelOrderBookDTO 中 Gender 枚举
 * 性别。Female 女，Maile 男, Unknown 保密
 *
 * @author pengyefei
 * @version 1.0
 * @since 2023/7/6 14:28
 */
public enum GenderTypeEunm {
    MAILE("Maile", "男"),
    FEMALE("Female", "女"),
    UNKNOWN("Unknown", "保密"),
    ;
    private String code;
    private String name;

    private static Map<String, GenderTypeEunm> map = new HashMap<>();

    static {
        for (GenderTypeEunm genderTypeEunm : GenderTypeEunm.values()) {
            map.put(genderTypeEunm.getCode(), genderTypeEunm);
        }
    }

    public static GenderTypeEunm instance(String bh) {
        return map.get(bh);
    }


    public static String getName(String bh){
        if (StringUtils.isBlank(bh) || StringUtils.equals(GenderTypeEunm.UNKNOWN.code,bh)){
            return StringUtils.EMPTY;
        }
        return map.get(bh).getName();
    }

   private GenderTypeEunm(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
