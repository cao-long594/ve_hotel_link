package cn.vetech.center.hotel.link.enums;

/**
 * @author xiaotengyu
 * @since 2021/12/3 11:12
 */
public enum LanguageCodeEnum {

    /**
     * zhcn: 简体中文
     */
    ZHCN("zhcn", "简体中文",""),
    /**
     * zhtw: 繁体中文
     */
    ZHTW("zhtw", "繁体中文","zh"),
    /**
     * enus: 英文
     */
    ENUS("enus", "英文","en"),
    /**
     * dede: 德文
     */
    DEDE("dede", "德文","de"),
    /**
     * frfr: 法文
     */
    FRFR("frfr", "法文","fr"),
    /**
     * jajp: 日文
     */
    JAJP("jajp", "日文","jp"),
    /**
     * kokr: 韩文
     */
    KOKR("kokr", "韩文","kr"),
    /**
     * ruru: 俄文
     */
    RURU("ruru", "俄文","ru"),
    /**
     * enmy: 马来语（英）
     */
    ENMY("enmy", "马来语（英）","my"),
    /**
     * 西班牙语
     */
    ESES("eses", "西班牙语","es"),
    /**
     * 越南语
     */
    THTH("thth", "泰语","th"),
    /**
     * 越南语
     */
    VIVN("vivn", "越南语","vn"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    /**
     * 携程code
     */
    private final String xcwCode;

    private LanguageCodeEnum(String code, String name,String xcwCode) {
        this.code = code;
        this.name = name;
        this.xcwCode = xcwCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }





    public String getXcwCode() {
        return xcwCode;
    }


}
