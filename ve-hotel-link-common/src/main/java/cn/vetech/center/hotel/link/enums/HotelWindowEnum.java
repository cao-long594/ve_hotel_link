package cn.vetech.center.hotel.link.enums;/**
 * Created by vetech on 2019/1/30.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author zoujiming
 * @since 2019/1/30
 */
public enum  HotelWindowEnum {
    /**
     */
    WC("0","无窗"),
    /**
     */
    YC("1","有窗"),
    /**
     */
    BFYC("2","部分有窗")

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
    private static Map<String,HotelWindowEnum> map = new HashMap<>();

    static {
        for (HotelWindowEnum hotelWindowEnum : HotelWindowEnum.values()) {
            map.put(hotelWindowEnum.getBh(),hotelWindowEnum);
        }
    }

    private HotelWindowEnum(String bh, String mc) {
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
     * @return HotelWindowEnum
     */
    public static HotelWindowEnum instance(String bh){
        return map.get(bh);
    }

}
