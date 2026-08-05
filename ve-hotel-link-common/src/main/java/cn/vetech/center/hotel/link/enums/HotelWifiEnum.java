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
public enum  HotelWifiEnum {
    /**
     */
    WFSW("0","无法上网"),
    /**
     */
    WXWIFI("1","无线WIFI"),
    /**
     */
    YXKD("2","有线宽带"),
    /**
     */
    WXWFANDYXKD("3","无线WIFI和有线宽带"),

    ;
    /**
     *编号
     */
    private final String bh;
    /**
     * 名称
     */
    private final String mc;
    /**
     */
    private static Map<String,HotelWifiEnum> map = new HashMap<>();

    static {
        for (HotelWifiEnum hotelWifiEnum : HotelWifiEnum.values()) {
            map.put(hotelWifiEnum.getBh(),hotelWifiEnum);
        }
    }

    private HotelWifiEnum(String bh, String mc) {
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
     * @return HotelWifiEnum
     */
    public static HotelWifiEnum instance(String bh){
        return map.get(bh);
    }
}
