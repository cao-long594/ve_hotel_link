package cn.vetech.center.hotel.link.enums;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * 酒店房源商编码对应映射字段名枚举
 *
 * @author gaojin
 */
public enum HotelFaceCodeEnum {
    /**
     * 本地
     */
    LOCAL("31200800", "自有房源", "local", "fy6", "mc6", "1", "2"),
    /**
     * 艺龙
     */
    ELONG("31200801", "艺龙", "elong", "fy1", "mc1", "1", "2"),
    /**
     * 爱满
     */
    AIM("31200802", "爱满", "aim", "fy2", "mc2", "1", "2"),
    /**
     * 去哪儿
     */
    QUNAR("31200815", "去哪儿", "qunar", "fy3", "mc3", "1", "2"),
    /**
     * 星宿
     */
    XSXX("31200803", "星宿", "xsxx", "fy4", "mc4", "1", "2"),
    /**
     * 深捷旅
     */
    JLTOUR("31200805", "深捷旅", "jltour", "fy5", "mc5", "1", "2"),
    /**
     * 华闵
     */
    HMC("31200818", "华闵", "hmc", "fy7", "mc7", "1", "2"),
    /**
     * 号百
     */
    SHHBSL("31200807", "号百", "shhbsl", "fy8", "mc8", "1", "2"),
    /**
     * 喜玩网
     */
    XWW("31200817", "喜玩", "xww", "fy9", "mc9", "1", "2"),
    /**
     * 小议网
     */
    XYW("31200827", "小议", "xyw", "fy10", "mc10", "1", "2"),
    /**
     * 携程
     */
    XCW("31200823", "携程", "xcw", "fy11", "mc11", "1", "2"),
    /**
     * 携程分销
     */
    XCFX("31200872", "携程分销", "xcfx", "fy00", "mc11", "3", "2"),
    /**
     * 美团
     */
    MTW("31200830", "美团", "mtw", "fy12", "mc12", "1", "2"),


    /**
     * 美团国际
     */
    MTWI("31200831", "美团国际", "mtwi", "", "", "1", "2"),

    /**
     * 好巧
     */
    HQW("31200834", "好巧", "hqw", "fy13", "mc13", "1", "2"),
    /**
     * 道旅
     */
    SZDL("31200832", "道旅", "szdl", "fy14", "mc14", "1", "2"),
    /**
     * 亚朵
     */
    YADUO("31200845", "亚朵", "yaduo", "fy15", "mc15", "1", "1"),
    /**
     * 如家网
     */
    RJW("31200852", "如家网", "rjw", "fy16", "mc16", "1", "1"),
    /**
     * 维也纳
     */
    WYN("32100866", "维也纳", "wyn", "fy17", "mc17", "1", "1"),
    /**
     * 速8
     */
    SUB("31200867", "速8", "sub", "fy20", "mc20", "1", "1"),
    /**
     * 泰坦云
     */
    TTY("31200853", "泰坦云", "tty", "fy18", "mc18", "1", "2"),
    /**
     * 安图泰坦云
     */
    ATTTY("31200854", "安图泰坦云", "attty", "fy19", "mc19", "1", "2"),
    /**
     * 华住
     */
    HZW("31200855", "华住", "hzw", "fy21", "mc21", "1", "1"),
    /**
     * 铂涛
     */
    BTW("31200824", "铂涛", "btw", "fy22", "mc22", "1", "2"),
    /**
     * 住友布丁
     */
    ZYW("31200856", "住友布丁", "zyw", "fy23", "mc23", "1", "1"),

    /**
     * 斯维登
     */
    SWD("31200868", "斯维登", "swd", "fy24", "mc24", "1", "1"),

    /**
     * 格林豪泰
     */
    GLHT("31200835", "格林豪泰", "mnglht", "fy25", "mc25", "1", "1"),
    /**
     * 尚美
     */
    SM("31200870", "尚美", "smw", "fy26", "mc26", "1", "2"),
    /**
     * 途家
     */
    TUJIA("31200836", "途家", "tujia", "fy27", "mc27", "2", "2"),
    /**
     * 港捷旅
     */
    GJL("31200837", "港捷旅", "gjl", "fy28", "mc28", "1", "2"),

    /**
     * 同程
     */
    TC("31200875", "同程", "tc", "fy29", "mc29", "1", "2"),

    /**
     * 锦江
     */
    JINJIANG("31200871", "锦江", "jinjiang", "fy30", "mc30", "1", "1"),
    /**
     * 千淘
     */
    QIANTAO("31200838", "千淘", "qiantao", "fy31", "mc31", "1", "2"),
    /**
     * 东呈
     */
    DONGCHENG("31200885", "东呈", "dongcheng", "fy33", "mc33", "1", "2"),
    /**
     * 中维
     */
    ZHONGWEI("31200841", "中维", "zhongwei", "fy34", "mc34", "1", "2"),
    /**
     * 千淘
     */
    JD("31200880", "京东", "jdw", "fy32", "mc32", "1", "2"),
    /**
     * hotelbeds
     */
    HOTELBEDS("31200881", "hotelbeds", "hotelbeds", "fy35", "mc35", "1", "2"),
    /**
     *
     */
    ZHONGYAN("31200888", "中烟", "zhongyan", "fy36", "mc36", "1", "2"),
    /**
     * 途牛
     */
    TUNIU("31200894", "途牛", "tuniu", "fy38", "mc38", "1", "2"),
    /**
     * 大都市
     */
    DADUSHI("31200892", "大都市", "dadushi", "fy37", "mc37", "1", "2"),
    /**
     * 小猪
     */
    XIAOZHU("31200891", "小猪", "xiaozhu", "fy27", "mc27", "2", "2"),
    /**
     * OYO
     */
    OYO("31200893", "鸥游", "oyo", "fy39", "mc39", "1", "2"),
    /**
     * YOYO
     */
    YOYO("31200895", "游由", "yoyo", "fy40", "mc40", "1", "2"),
    /**
     * 如家高星
     */
    RJGX("31200862", "如家高星", "rjgx", "fy41", "mc41", "1", "1"),

    /**
     * 旅悦通 花筑集团商
     */
    LYHZ("31200866", "旅悦花筑", "lyhz", "fy42", "mc42", "1", "1"),

    /**
     * 文鸟酒店
     */
    WNJD("31200873", "文鸟酒店", "wnjd", "fy43", "mc43", "1", "1"),
    /**
     * 游由平台
     */
    YYPT("31200874", "游由平台", "yypt", "fy44", "mc44", "1", "2"),
    /**
     * 丽呈
     */
    LC("31200876", "丽呈", "lc", "fy45", "mc45", "1", "1"),
    /**
     * 游由特牌
     */
    YYTP("31200882", "游由特牌", "yytp", "", "", "1", "2"),
    /**
     * 小猪酒店
     */
    XZJD("31200883", "小猪酒店", "xzjd", "", "", "1", "2"),
    /***
     *安可达
     */
    AGODA("31200903", "安可达", "agoda", "", "", "1", "2"),
    /**
     * 付迅
     */
    FUXUN("31200904", "付迅", "fuxun", "", "", "1", "2"),
    /**
     * 蓝海畅联
     */
    LHCL("31200905", "蓝海畅联", "lhcl", "", "", "1", "2"),
    /**
     * 绿云
     */
    LVYUN("31200906", "绿云", "lvyun", "", "", "1", "2"),
    /**
     * 住哲
     */
    ZHUZHE("31200907", "住哲", "zhuzhe", "", "", "1", "1"),
    /***
     *HRS
     */
    HRS("31200908", "HRS", "hrs", "", "", "1", "2"),
    /**
     * 途灵
     */
    TULING("31200909", "途灵", "tuling", "", "", "1", "2"),
    /**
     * 途灵
     */
    WEIJING("31200910", "维景", "weijing", "", "", "1", "2"),
    /**
     * 轻住
     */
    QINGZHU("31200911", "轻住", "qingzhu", "", "", "1", "2"),
    /**
     * hotelhub
     */
    HOTELHUB("31200912", "HotelHub", "hotelhub", "", "", "1", "2"),
    /**
     * 众客
     */
    ZHONGKE("31200913", "众客", "zhongke", "", "", "1", "2"),

    /**
     *深白云
     */
    SBY("32000020", "深白云", "sby","","","1","2"),
    /**
     * tbo
     */
    TBO("31200914", "TBO",  "tbo","","","1","2"),
    /**
     *在途商旅
     */
    ZTSL("32000010", "在途商旅", "ztsl","","","1","2"),
    /**
     *空港嘉华
     */
    KGJH("32000021", "空港嘉华", "kgjh","","","1","2"),
    /**
     *RESTEL
     */
    RESTEL("31200915", "RESTEL", "restel","","","1","2"),
    /**
     * 畅帆商旅
     */
    CFSL("32000916", "畅帆商旅", "cfsl","","","1","2"),
    /**
     * 飞巴
     */
    FEIBA("31200916", "飞巴", "feiba","","","1","2"),
    /**
     * 萤旅
     */
    YINGLV("31200917", "萤旅", "yinglv","","","1","2"),
    /**
     * 携程商旅
     */
    XCSL("31200918","携程商旅", "xcsl","","","1","2"),
    /**
     * 同程商旅
     */
    TCSL("31200919","同程商旅", "tcsl","","","1","2"),
    /**
     * 秋果
     */
    QIUGUO("31200920", "秋果", "qiuguo", "", "", "1", "2"),
    /**
     * 多极
     */
    DOOOOJ("31200921", "多极", "dooooj", "", "", "1", "2"),
    /**
     * 阿里商旅
     */
    ALITRIP("31200922", "阿里商旅", "alitrip", "", "", "1", "2"),
    /**
     * 飞天
     */
    FTSL("31200924", "飞天", "ftsl", "", "", "1", "2"),
    /**
     * 中青旅
     */
    CYTS("31200925","中青旅", "cyts","","","1","2"),
    /**
     * 雅斯特
     */
    YST("31200923", "雅斯特", "yst","","","1","2"),
    /**
     * 山东腾邦
     */
    TENGBANG("31200926","山东腾邦", "tengbang","","","1","2"),
    /**
     * 游由商旅
     */
    YYTRIP("31200927","游由商旅", "yytrip","","","1","2"),
    /**
     * 汇智
     */
    HUIZHI("31200928","汇智", "huizhi","","","1","2"),
    /**
     * 上海航阳
     */
    HANGYANG("31200929","上海航阳", "hangyang","","","1","2"),
    /**
     * 广州票亿
     */
    PIAOYI("31200930","广州票亿", "piaoyi","","","1","2"),
//    /**
//     * webbeds
//     */
//    WEBBEDS("31200931","webbeds", "webbeds","","","1","2"),
    /**
     * 差旅管家
     */
    TRIPWISE("31200932","差旅管家", "tripwise","","","1","2"),
    /**
     * 中旅商旅TMC
     */
    ZLSL("31200934","中旅商旅", "zlsl","","","1","2"),
    /**
     * 珠海飞跃
     */
    ZHFY("31200935", "珠海飞跃", "zhfy", "","","1","2"),
    /**
     * 中航服
     */
    ZHF("31200936","中航服", "zhf","","","1","2"),
    /**
     * Booking
     */
    BOOKING("31200902", "缤客","booking", "","","1" , "2"),
    /**
     * Expedia
     */
    EXPEDIA("31200901", "亿客行", "expedia", "", "","1","2"),
    /**
     * ve，胜意cps
     */
    VE("31201000", "胜意", "ve", "", "", "1", "2"),
    /**
     * 艾玛迪斯
     */
    AMADEUS("31200957", "艾玛迪斯", "amadeus", "", "","1","2"),

    /**
     * 飞猪
     */
    FEIZHU("31200986", "飞猪", "feizhu", "", "","1","2"),
    ;
    /**
     * 房源商ID
     */
    @ApiModelProperty(value = "房源商ID", dataType = "string")
    private final String jkbh;
    /**
     * 房源商名称
     */
    @ApiModelProperty(value = "房源商名称", dataType = "string")
    private final String jkmc;
    /**
     * 房源商英文
     */
    @ApiModelProperty(value = "房源商英文", dataType = "string")
    private final String jken;
    /**
     * 关系映射表中ID字段
     */
    @ApiModelProperty(value = "关系映射表中ID字段", dataType = "string")
    private final String column;
    /**
     * 关系映射表中名称字段
     */
    @ApiModelProperty(value = "关系映射表中名称字段", dataType = "string")
    private final String mccolumn;
    /**
     * 房源类型 1酒店 2民宿
     */
    @ApiModelProperty(value = "1酒店 2民宿", dataType = "string")
    private final String fylx;
    /**
     * 是否是集团 1是 2不是
     */
    @ApiModelProperty(value = "1是 2不是", dataType = "string")
    private final String sfjt;
    /**
     * 房源商ID为key
     */
    @ApiModelProperty(value = "房源商ID为key", dataType = "string")
    private static final Map<String, HotelFaceCodeEnum> map = new HashMap<>();
    /**
     * FY..为key
     */
    @ApiModelProperty(value = "FY..为key", dataType = "string")
    private static final Map<String, HotelFaceCodeEnum> fymap = new HashMap<>();
    /**
     * JKEN..为key
     */
    @ApiModelProperty(value = "JKEN..为key", dataType = "string")
    private static final Map<String, HotelFaceCodeEnum> jkenmap = new HashMap<>();


    HotelFaceCodeEnum(String jkbh, String jkmc, String jken, String column, String mccolumn, String fylx, String sfjt) {
        this.jkbh = jkbh;
        this.jkmc = jkmc;
        this.jken = jken;
        this.column = column;
        this.mccolumn = mccolumn;
        this.fylx = fylx;
        this.sfjt = sfjt;
    }

    static {
        for (HotelFaceCodeEnum c : HotelFaceCodeEnum.values()) {
            map.put(c.jkbh, c);
            fymap.put(c.column.toUpperCase(), c);
            jkenmap.put(c.jken.toUpperCase(), c);
        }
    }

    /**
     * @param type 根据房源商ID获取房源商枚举
     * @return 枚举
     */
    public static HotelFaceCodeEnum instance(String type) {
        return map.get(type);
    }

    /**
     * @param type 根据FY..获取房源商枚举
     * @return 枚举
     */
    public static HotelFaceCodeEnum instanceFy(String type) {
        return fymap.get(type.toUpperCase());
    }

    /**
     * @param type 根据Jken..获取房源商枚举
     * @return 枚举
     */
    public static HotelFaceCodeEnum instanceJken(String type) {
        return jkenmap.get(type.toUpperCase());
    }

    public String getJkbh() {
        return jkbh;
    }



    public String getJkmc() {
        return jkmc;
    }



    public String getJken() {
        return jken;
    }



    public String getColumn() {
        return column;
    }



    public String getMccolumn() {
        return mccolumn;
    }



    public String getFylx() {
        return fylx;
    }



    public String getSfjt() {
        return sfjt;
    }


}