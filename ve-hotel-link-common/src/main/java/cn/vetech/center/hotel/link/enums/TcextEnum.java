package cn.vetech.center.hotel.link.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huchaochao
 * 2020/12/8 16:20
 * @Title: TcextEnum
 * @Description: tcext供应商枚举
 */
public enum TcextEnum {
    /**
     * 凯撒
     */
    KAISA("31209001", "凯撒", "kaisa"),
    /**
     * 梓如
     */
    ZIRU("31209003", "梓如", "ziru"),
    /**
     * 美亚
     */
    MEIYA("31209005", "美亚", "meiya"),
    /**
     * 行旅国际
     */
    XLGJ("32000001", "行旅国际", "xlgj"),
    /**
     * 差旅天下
     */
    CLTX("32000002", "差旅天下", "cltx"),
    /**
     * 飞鹤
     */
    FEIHE("32000003", "飞鹤", "feihe"),
    /**
     * 在途商旅
     */
    ZTSL("32000010", "在途商旅", "ztsl"),
    /**
     * 优行商旅
     */
    YXSL("32000011", "优行商旅", "yxsl"),
    /**
     * 港捷旅标准
     */
    BZGJL("32000012", "港捷旅标准", "bzgjl"),

    /**
     * 在路上
     */
    ZLS("31200877", "在路上", "zls"),
    /**
     *深白云
     */
    SBY("32000020", "深白云", "sby"),
    /**
     *空港嘉华
     */
    KGJH("32000021", "空港嘉华", "kgjh"),
    /**
     * 畅帆商旅
     */
    CFSL("32000916", "畅帆商旅", "cfsl"),
    /**
     * 飞巴
     */
    FEIBA("31200916", "飞巴", "feiba"),
    /**
     * 携程商旅
     */
    XCSL("31200918","携程商旅", "xcsl"),
    /**
     * 同程商旅
     */
    TCSL("31200919","同程商旅", "tcsl"),
    /**
     * 山东腾邦
     */
    TENGBANG("31200926","山东腾邦", "tengbang"),
    /**
     * 上海航阳
     */
    HANGYANG("31200929", "上海航阳", "hangyang"),
    /**
     * 广州票亿
     */
    PIAOYI("31200930", "广州票亿", "piaoyi"),
    /**
     * 中旅商旅TMC
     */
    ZLSL("31200934","中旅商旅", "zlsl"),
    /**
     * 中航服
     */
    ZHF("31200936","中航服", "zhf"),

    ;

    /**
     * 房源编号
     */
    private final String fybh;
    /**
     * 房源名称
     */
    private final String fymc;
    /**
     * 房源简称
     */
    private final String fyjc;
    /**
     * 房源编号map
     */
    private static Map<String, TcextEnum> fybhMap = new HashMap<>();
 