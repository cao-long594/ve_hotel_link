package cn.vetech.center.hotel.link.enums;

/**
 * 图片尺寸(传1或者2)  1：大图350*350以上；2:小图350*350及以下如70*70
 *
 * @author xiaotengyu
 * @since 2021/4/1 15:33
 */
public enum TpccTypeEnum {

    /***
     * 大图350*350以上
     */
    C1("1", "大图"),
    /***
     * 小图350*350及以下如70*70
     */
    C2("2", "小图"),
    ;

    private TpccTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private final String code;
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }


}
