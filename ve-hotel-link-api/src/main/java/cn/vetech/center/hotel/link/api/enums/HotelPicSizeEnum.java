package cn.vetech.center.hotel.link.api.enums;

/**
 * 酒店图片尺寸
 *
 * @author luqs
 * @version v1.0
 **/
public enum HotelPicSizeEnum {
    /**
     * 1：大图350*350以上
     */
    BIG("1", "大图350*350以上"),

    /**
     * 2：小图350*350及以下如70*70
     */
    SMALL("2", "小图350*350及以下如70*70"),
    ;

    /**
     * 编码
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    private HotelPicSizeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
