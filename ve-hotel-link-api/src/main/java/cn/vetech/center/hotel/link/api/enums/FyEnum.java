package cn.vetech.center.hotel.link.api.enums;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum FyEnum {
    /**
     * 自签
     */
    ZQ("201700", "201700", "zq", "自签", false),
    /**
     * asms
     */
    ASMS("201701", "201701", "asms", "asms", false),
    /**
     * cps
     */
    CPS("201702", "201702", "cps", "cps", false),
    /**
     * 标准供应商 新增的所有标准供应商都使用这个房源编号
     */
    STANDARD("201815", "31222222", "standard", "标准供应商-通用", true),
    /**
     * 艺龙
     */
    ELONG("201710", "31200801", "elong", "艺龙", false),
    /**
     * 亚朵
     */
    YADUO("201711", "31200845", "yaduo", "亚朵", false),
    /**
     * 去哪儿
     */
    QUNAR("201712", "31200815", "qunar", "去哪儿", false),
    /**
     * 维也纳
     */
    WYN("201713", "32100866", "wyn", "维也纳", false),
    /**
     * 速8
     */
    SUB("201714", "31200867", "sub", "速8", false),
    /**
     * 铂涛
     */
    BTW("201716", "31200824", "btw", "铂涛", false),
    /**
     * 华住
     */
    HZW("201717", "31200855", "hzw", "华住", false),
    /**
     * 尚美
     */
    SMW("201721", "31200870", "smw", "尚美", false),
    /**
     * 格林豪泰
     */
    GLHT("201720", "31200835", "mnglht", "格林豪泰", false),
    /**
     * 如家
     */
    RJW("201715", "31200852", "rjw", "如家", false),
    /**
     * 锦江
     */
    JINJIANG("201723", "31200871", "jinjiang", "锦江", false),
    /**
     * 港捷旅
     */
    GJL("201722", "31200837", "gjl", "港捷旅", false),
    /**
     * 同程
     */
    TC("201724", "31200875", "tc", "同程", false),
    /**
     * 梓如
     */
    ZIRU("201728", "31209003", "ziru", "梓如", true),
    /**
     * 美亚
     */
    MEIYA("201730", "31209005", "meiya", "美亚", true),
    /**
     * 凯撒
     */
    KAISA("201732", "31209001", "kaisa", "凯撒", true),
    /**
     * 行旅国际
     */
    XLGJ("201760", "32000001", "xlgj", "行旅国际", true),
    /**
     * 差旅天下
     */
    CLTX("201761", "32000002", "cltx", "差旅天下", true),
    /**
     * 飞鹤
     */
    FEIHE("201762", "32000003", "feihe", "飞鹤", true),
    /**
     * 宿舍
     */
    SUSHE("201763", "32000004", "sushe", "宿舍", false),
    /**
     * 携程
     */
    XCW("31200823", "31200823", "xcw", "携程", false),
    /**
     * 泰坦云
     */
    TTY("201764", "31200853", "tty", "泰坦云", false),
    /**
     * 大都市
     */
    DADUSHI("", "31200892", "dadushi", "大都市", false),
    /**
     * 中烟
     */
    ZHONGYAN("201726", "31200888", "zhongyan", "中烟", false),
    /**
     * 中维
     */
    ZHONGWEI("201727", "31200841", "zhongwei", "中维", false),
    /**
     * 美团国内
     */
    MTW("201766", "31200830", "mtw", "美团", false),
    /**
     * 美团国际
     */
    MTWI("", "31200831", "mtwi", "美团国际", false),
    /**
     * 千淘
     */
    QIANTAO("201765", "31200838", "qiantao", "千淘", false),
    /**
     * 东呈
     */
    DONGCHENG("201733", "31200885", "dongcheng", "东呈", false),
    /**
     * 深捷旅
     */
    JLTOUR("", "31200805", "jltour", "深捷旅", false),
    /**
     * 华闵
     */
    HMC("201767", "31200818", "hmc", "华闵", false),
    /**
     * 鸥游
     */
    OYO("", "31200893", "oyo", "鸥游", false),
    /**
     * hotelbeds
     */
    HOTELBEDS("201770", "31200881", "hotelbeds", "hotelbeds", false),
    /**
     * 住友
     */
    ZYW("201771", "31200856", "zyw", "住友", false),
    /**
     * 喜玩
     */
    XWW("", "31200817", "xww", "喜玩", false),
    /**
     * 道旅
     */
    SZDL("", "31200832", "szdl", "道旅", false),
    /**
     * 好巧
     */
    HQW("", "31200834", "hqw", "好巧", false),
    /**
     * 星宿
     */
    XSXX("", "31200803", "xsxx", "星宿", false),
    /**
     * 斯维登
     */
    SWD("201769", "31200868", "swd", "斯维登", false),
    /**
     * 如家高星
     */
    RJGX("201779", "31200862", "rjgx", "如家高星", false),
    /**
     * 旅悦花筑
     */
    LYHZ("201822", "31200866", "lyhz", "旅悦花筑", false),
    /**
     * 在路上
     */
    ZLS("201726", "31200877", "zls", "在路上", true),
    /**
     * 游由平台(郑州优途)
     */
    YYPT("", "31200874", "yypt", "游由平台", false),
    /**
     * 一起飞
     */
    YQF("201774", "31200878", "yqf", "一起飞", true),
    /**
     * 优行商旅
     */
    YXSL("201781", "31200879", "yxsl", "优行商旅", true),
    /**
     * 丽呈
     */
    LC("201784", "31200876", "lc", "丽呈", false),
    /**
     * 小猪酒店
     */
    XZJD("", "31200883", "xzjd", "小猪酒店", false),
    /**
     * 文鸟酒店
     */
    WNJD("", "31200873", "wnjd", "文鸟酒店", false),
    /***
     * 安可达
     */
    AGODA("", "31200903", "agoda", "安可达", false),
    /***
     * 付迅
     */
    FUXUN("201821", "31200904", "fuxun", "付迅", false),
    /**
     * 蓝海协议托管（畅联）
     */
    LHCL("", "31200905", "lhcl", "蓝海畅联", false),
    /***
     * 绿云
     */
    LVYUN("", "31200906", "lvyun", "绿云", false),
    /***
     * 住哲
     */
    ZHUZHE("", "31200907", "zhuzhe", "住哲", false),
    /***
     * HRS
     */
    HRS("", "31200908", "hrs", "HRS", false),
    /***
     * 途灵
     */
    TULING("", "31200909", "tuling", "途灵", false),
    /***
     * 维景
     */
    WEIJING("", "31200910", "weijing", "维景", false),
    /***
     * 轻住
     */
    QINGZHU("31200911", "31200911", "qingzhu", "轻住", false),
    /**
     * hotelhub
     */
    HOTELHUB("", "31200912", "hotelhub", "HotelHub", false),
    /**
     * 众客
     */
    ZHONGKE("", "31200913", "zhongke", "众客", false),
    /**
     * TBO
     */
    TBO("201778", "31200914", "tbo", "TBO", false),
    /***
     * 深白云
     */
    SBY("201775", "32000020", "sby", "深白云", true),
    /***
     * 在途商旅
     */
    ZTSL("201776", "32000010", "ztsl", "在途商旅", true),
    /***
     * 空港嘉华
     */
    KGJH("201777", "32000021", "kgjh", "空港嘉华", true),
    /***
     * Restel 房源
     */
    RESTEL("", "31200915", "restel", "RESTEL", false),
    /***
     * 畅帆商旅
     */
    CFSL("201787", "32000916", "cfsl", "畅帆商旅", true),
    /**
     * 飞巴
     */
    FEIBA("201788", "31200916", "feiba", "飞巴", true),
    /**
     * 北京萤旅科技有限公司
     */
    YINGLV("", "31200917", "yinglv", "萤旅", false),
    /**
     * 携程商旅
     */
    XCSL("201789", "31200918", "xcsl", "携程商旅", true),
    /**
     * 同程商旅
     */
    TCSL("201793", "31200919", "tcsl", "同程商旅", true),
    /**
     * 秋果
     */
    QIUGUO("201817", "31200920", "qiuguo", "秋果", false),
    /**
     * 多极
     */
    DOOOOJ("", "31200921", "dooooj", "多极", false),
    /**
     * 阿里商旅
     */
    ALITRIP("201801", "31200922", "alitrip", "阿里商旅", false),
    /**
     * 雅斯特
     */
    YST("201818", "31200923", "yst", "雅斯特", false),
    /**
     * 飞天（联调cps对接的标准供应商）
     */
    FTSL("", "31200924", "ftsl", "飞天", true),
    /**
     * 中青旅
     */
    CYTS("201795", "31200925", "cyts", "中青旅", true),
    /**
     * 山东腾邦
     */
    TENGBANG("201796", "31200926", "tengbang", "山东腾邦", true),
    /**
     * 游由商旅
     */
    YYTRIP("", "31200927", "yytrip", "游由商旅", false),
    /**
     * 汇智
     */
    HUIZHI("201819", "31200928", "huizhi", "汇智", false),
    /**
     * 上海航阳
     */
    HANGYANG("", "31200929", "hangyang", "上海航阳", true),
    /**
     * 广州票亿
     */
    PIAOYI("", "31200930", "piaoyi", "广州票亿", true),
    /**
     * webbeds
     */
    WEBBEDS("", "31200931", "webbeds", "webbeds", false),
    /**
     * 差旅管家
     */
    TRIPWISE("", "31200932", "tripwise", "差旅管家", false),
    /**
     * 中旅商旅TMC
     */
    ZLSL("201797", "31200934", "zlsl", "中旅商旅", true),
    /**
     * 珠海飞跃
     */
    ZHFY("201813", "31200935", "zhfy", "珠海飞跃", false),
    /**
     * 中航服
     */
    ZHF("201798", "31200936", "zhf", "中航服", true),
    /**
     * 山东文旅
     */
    SDWL("2018000", "31200937", "sdwl", "山东文旅", false),
    /**
     * 嘉宝商旅
     */
    JBSL("201799", "31200938", "jbsl", "嘉宝商旅", true),
    /**
     * 广州市美瀛国际旅行社有限公司
     */
    MYSL("201802", "31200939", "mysl", "美瀛商旅", true),
    /**
     * 开元集团
     */
    KYJT("201805", "31200940", "kyjt", "开元集团", false),
    /**
     * 广之旅
     */
    GZL("", "31200941", "gzl", "广之旅", true),
    /**
     * 石基畅联
     */
    SJCL("", "31200942", "sjcl", "石基畅联", false),
    /**
     * 恒顺商旅
     */
    HSSL("201806", "31200943", "hssl", "恒顺商旅", true),
    /**
     * 香宿
     */
    XIANGSU("", "31200944", "xiangsu", "香宿", false),
    /**
     * 泰坦云商旅
     */
    TTYSL("201808", "31200945", "ttysl", "泰坦云商旅", true),
    /**
     * 安徽捷琛商旅
     */
    AHJCSL("", "31200946", "ahjcsl", "安徽捷琛商旅", true),
    /**
     * 凤悦商旅
     */
    FENGYUE("201807", "31200947", "fengyue", "凤悦商旅", true),
    /**
     * 好梦想
     */
    HMXSL("", "31200948", "hmxsl", "好梦想", true),
    /**
     * 联通鲲鹏
     */
    KUNPENG("", "31200949", "kunpeng", "鲲鹏", false),
    /**
     * Booking
     */
    BOOKING("", "31200902", "booking", "Booking", false),
    /**
     * Expedia
     */
    EXPEDIA("201820", "31200901", "expedia", "Expedia", false),
    /**
     * 畅联乐宿（BNP平台）
     */
    BNP("", "31200950", "bnp", "畅联乐宿", false),
    /**
     * JEGO
     */
    JEGO("201809", "31200951", "jego", "JEGO", true),
    /**
     * EXTEND，拓展房型商，标准供应商有自己的酒店，可以统一用这个枚举
     * 如：三一集团的智慧宿舍
     */
    EXTEND("201719", "31200952", "extend", "标准供应商", false),
    /**
     * Amerilink
     */
    AMERILINK("", "31200953", "amerilink", "Amerilink", false),
    /**
     * 美团商旅
     */
    MTSL("201810", "31200954", "mtsl", "美团商旅", true),
    /**
     * ve，胜意cps
     */
    VE("", "31201000", "ve", "胜意", false),
    /**
     * 千淘商旅
     */
    QTSL("201811", "31200955", "qtsl", "千淘商旅", true),
    /**
     * 阿里商旅-标准
     */
    ALSL("201812", "31200956", "alsl", "阿里商旅-标准", true),
    /**
     * 艾玛迪斯
     */
    AMADEUS("", "31200957", "amadeus", "艾玛迪斯", false),
    /**
     * 轻松出发
     */
    QSCF("", "31200958", "qscf", "轻松出发", true),
    /**
     * 遇订商旅
     */
    YDSL("", "31200959", "ydsl", "遇订商旅", true),
    /**
     * 生生国旅
     */
    SSGL("", "31200960", "ssgl", "生生国旅", true),
    /**
     * 喜玩商旅
     */
    HEYTRIP("201816", "31200961", "heytrip", "喜玩商旅", false),
    /**
     * 深圳雏虎
     */
    SZCH("", "31200962", "szch", "深圳雏虎", true),
    /**
     * 艾玛迪斯Booking
     */
    AMADEUSBOOKING("", "31200963", "amadeusbooking", "艾玛迪斯Booking", false),
    /**
     * 万顺通商旅
     */
    WSTSL("201814", "31200964", "wstsl", "万顺通商旅", true),
    /**
     * 洛阳趣浪酒店
     */
    LYQLJD("", "31200965", "lyqljd", "洛阳趣浪酒店", true),
    /**
     * 上海迈骐
     */
    SHMQSL("", "31200966", "shmqsl", "上海迈骐", true),
    /**
     * 深圳春秋
     */
    SZCQ("", "31200967", "szcq", "深圳春秋", false),
    /**
     * ratehawk
     */
    RATEHAWK("", "31200968", "ratehawk", "ratehawk", false),
    /**
     * 空港国际
     */
    KGGJ("", "31200969", "kggj", "空港国际", false),
    /**
     * 洛阳趣浪 （珠海飞跃一套接口）
     */
    LYQL("", "31200970", "lyql", "洛阳趣浪", false),
    /**
     * 重庆浩北  （珠海飞跃一套接口）
     * 重庆浩北不合作了，目前是广州流萤在使用这个枚举
     */
    CQHB("", "31200971", "cqhb", "重庆浩北", false),
    /**
     * 奈斯国际  (游由商旅一套接口)
     */
    NSGJ("", "31200972", "nsgj", "奈斯国际", false),
    /**
     * 善辉医疗
     */
    SHYL("", "31200973", "shyl", "善辉医疗", true),
    /**
     * 易旅分销
     */
    YLFX("", "31200974", "ylfx", "易旅分销", false),
    /**
     * 直客通
     */
    ZHIKETONG("", "31200975", "zhiketong", "直客通", false),
    /**
     * 珠海飞跃众衍
     */
    FYZY("", "31200976", "fyzy", "飞跃众衍", false),
    /**
     * 盟广酒店
     */
    MGJD("", "31200977", "mgjd", "盟广酒店", false),
    /**
     * 天源嘉诚
     */
    TYJC("", "31200978", "tyjc", "天源嘉诚", false),
    /**
     * W2M
     */
    W2M("", "31200979", "w2m", "W2M", false),
    /**
     * 珠海飞跃赣游通
     */
    GYT("", "31200980", "gyt", "赣游通", false),
    /**
     * 万达酒店
     */
    WANDA("", "31200981", "wanda", "万达酒店", false),
    /**
     * 住哲-艺龙
     */
    ZHUZEELONG("", "31200982", "zzyl", "住哲-艺龙", false),
    /**
     * 长沙发现者
     */
    CSFXZ("", "31200983", "csfxz", "长沙发现者", false),
    /**
     * mgbedbank
     */
    MGBEDBANK("", "31200984", "mgbedbank", "mgbedbank", false),
    /**
     * 中铁商旅
     */
    ZHONGTIE("", "31200985", "zhongtie", "中铁商旅", false),
    /**
     * 飞猪
     */
    FEIZHU("", "31200986", "feizhu", "飞猪", false),
    /**
     * 康旅科技
     */
    TOJOY("", "31200987", "tojoy", "康旅科技", false),
    /**
     * 美团民宿
     */
    MTMS("", "31200988", "mtms", "美团民宿", false),
    /**
     * 逍遥 出行
     */
    XYCX("", "31200989", "xycx", "逍遥出行", false),
    /**
     * 住哲-足印
     */
    ZZZY("", "31200990", "zzzy", "住哲-足印", false),
    /**
     * 住哲-足印托管
     */
    ZZZYTG("", "31200991", "zzzytg", "住哲-足印托管", false),
    /**
     * 别样红
     */
    BYH("", "31200992", "byh", "别样红", false),
    /**
     * 住哲-飞天下
     */
    ZZFTX("", "31200993", "zzftx", "住哲-飞天下", false),
    /**
     * 直客通国际
     */
    ZKTGJ("", "31200994", "zktgj", "直客通国际", false),

    ;

    /**
     * 供应商平台
     */
    private final String gyspt;
    /**
     * 房源编号
     */
    private final String fybh;
    /**
     * 房源英文缩写
     */
    private final String fyen;
    /**
     * 房源名称
     */
    private final String fymc;
    /**
     * 是否走tcext（通用标准房源）
     */
    private final boolean tcext;

    FyEnum(String gyspt, String fybh, String fyen, String fymc, boolean tcext) {
        this.gyspt = gyspt;
        this.fybh = fybh;
        this.fyen = fyen;
        this.fymc = fymc;
        this.tcext = tcext;
    }

    public String getGyspt() {
        return gyspt;
    }


    public String getFybh() {
        return fybh;
    }


    public String getFyen() {
        return fyen;
    }


    public String getFymc() {
        return fymc;
    }


    public boolean isTcext() {
        return tcext;
    }


    /**
     *
     */
    private static final Map<String, FyEnum> FYBHMAP = new HashMap<>();
    /**
     *
     */
    private static final Map<String, FyEnum> GYSPTMAP = new HashMap<>();
    /**
     *
     */
    private static final Map<String, FyEnum> FYENMAP = new HashMap<>();

    static {
        FyEnum[] fyEnums = FyEnum.values();
        Arrays.asList(fyEnums).forEach(f -> {
            FYBHMAP.put(f.getFybh(), f);
            GYSPTMAP.put(f.getGyspt(), f);
            FYENMAP.put(f.getFyen(), f);
        });
    }

    /**
     * @param fybh 1
     * @return 1
     * 使用 instanceOptByFybh
     */
    @Deprecated
    public static FyEnum instanceByFybh(String fybh) {
        return FYBHMAP.get(fybh);
    }

    /**
     * @param fybh 1
     * @return 1
     */
    public static Optional<FyEnum> instanceOptByFybh(String fybh) {
        return Optional.ofNullable(instanceByFybh(fybh));
    }

    /**
     * @param gyspt 1
     * @return 1
     */
    public static FyEnum instanceByGyspt(String gyspt) {
        return GYSPTMAP.get(gyspt);
    }

    public static Optional<FyEnum> instanceOptByGyspt(String gyspt) {
        return Optional.ofNullable(instanceByGyspt(gyspt));
    }

    /**
     * @param fyen 1
     * @return 1
     */
    public static FyEnum instanceByFyen(String fyen) {
        return FYENMAP.get(fyen);
    }

    public static Optional<FyEnum> instanceOptByFyen(String fyen) {
        return Optional.ofNullable(instanceByFyen(fyen));
    }

    /**
     * 判断房源编号是否存在
     *
     * @param fybh 房源商编号
     * @return boolean
     */
    public static boolean isExistByFybh(String fybh) {
        return Stream.of(FyEnum.values()).anyMatch(fyEnum -> fyEnum.getFybh().equals(fybh));
    }
}