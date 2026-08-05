package cn.vetech.center.hotel.link.api.enums;

/**
 * @author vetech
 * @since 2023/10/17
 */
public enum PriceStatusEnum {
    /**
     * 1正常  2 价格未开启缓存 3 超时未返回 4执行异常
     **/
    OK("1", "正常"),

    /**
     *
     */
    NOT_OPEN("2", "未开实时查询最低价"),

    /**
     *
     */
    TIME_OUT("3", "超时"),


    /**
     *
     */
    ERROR("4", "异常"),




    ;


    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;


    private PriceStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
