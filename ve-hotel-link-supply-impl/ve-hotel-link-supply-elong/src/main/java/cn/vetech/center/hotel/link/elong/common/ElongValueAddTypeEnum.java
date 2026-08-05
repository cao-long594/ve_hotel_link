package cn.vetech.center.hotel.link.elong.common;

/**
 * @author xiaotengyu
 * @since 2022-04-19 13:48
 */
public enum  ElongValueAddTypeEnum {

    /**
     * 早餐
     */
    T01("01","早餐"),
    /**
     * 午餐
     */
    T02("02","午餐"),
    /**
     * 晚餐
     */
    T03("03","晚餐"),
    /**
     * 宽带上网
     */
    T04("04","宽带上网"),
    /**
     * 服务费
     */
    T05("05","服务费"),
    /**
     * 政府税
     */
    T06("06","政府税"),
    /**
     * 特殊早餐，有效日期内生效，优先级高于01早餐
     */
    T99("99","特殊早餐，有效日期内生效，优先级高于01早餐"),
    ;

    private ElongValueAddTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * code
     */
    private final String code;
    /**
     * 名称
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }


}
