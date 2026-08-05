package cn.vetech.center.hotel.link.enums;

/**
 * @author pengyefei
 * @version 1.0
 * @since 2022/10/31 14:43
 */
public enum OrderCancelingEnum {
    /**
     * 是否取消中   空或者0表示非取消中，1表示取消中
     */
    ISCANCELING_YES("1","取消中")
    ;
    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String name;

    private OrderCancelingEnum(String code, String name) {
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
