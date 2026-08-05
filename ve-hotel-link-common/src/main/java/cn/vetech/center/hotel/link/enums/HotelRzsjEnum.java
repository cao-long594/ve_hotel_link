package cn.vetech.center.hotel.link.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vetech on 2019/1/30.
 */
public enum HotelRzrgjEnum {
    //0、仅内宾  1、外宾适用（含内宾和港澳台） 2、港澳台客人适用（含内宾，除港澳台以外的客人不适用）3、香港客人适用（含内宾，除香港以外的客人不适用） 4、台湾客人适用（含内宾，除台湾以外的客人不适用）  5、澳门客人适用（含内宾，除澳门以外的客人不适用）
    /**
     */
    JNB("0","仅内宾"),
    /**
     */
    KJDWB("1","外宾适用（含内宾和港澳台）"),
    /**
     */
    GATKR("2","港澳台客人适用（含内宾，除港澳台以外的客人不适用）"),
    /**
     */
    XGKR("3","香港客人适用（含内宾，除香港以外的客人不适用）"),
    /**
     */
    TWKR("4","台湾客人适用（含内宾，除台湾以外的客人不适用）"),
    /**
     */
    AMKR("5","澳门客人适用（含内宾，除澳门以外的客人不适用）"),

    ;
    /**
     * 编号
     */
    private final String bh;
    /***
     * 名称
     */
    private final String mc;
    /**
     */
    private static Map<String,HotelRzrgjEnum> map = new HashMap<>();

    static {
        for (HotelRzrgjEnum hotelRzrgjEnum : HotelRzrgjEnum.values()) {
            map.put(hotelRzrgjEnum.getBh(),hotelRzrgjEnum);
        }
    }

    private HotelRzrgjEnum(String bh, String mc) {
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
     * @return HotelRzrgjEnum
     */
    public static HotelRzrgjEnum instance(String bh){
        return map.get(bh);
    }
}
