package cn.vetech.center.hotel.link.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vetech on 2019/1/30.
 */
public enum HotelWyEnum {
    /**
     */
    BKXY("0","不可吸烟"),
    /**
     */
    KYXY("1","可以吸烟"),

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
     */
    private static Map<String,HotelWyEnum> map = new HashMap<>();

    static {
        for (HotelWyEnum hotelWyEnum : HotelWyEnum.values()) {
            map.put(hotelWyEnum.getBh(),hotelWyEnum);
        }
    }

    private HotelWyEnum(String bh, String mc) {
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
     * @param bh 编号
     * @return HotelWyEnum
     */
    public static HotelWyEnum instance(String bh){
        return map.get(bh);
    }
}
