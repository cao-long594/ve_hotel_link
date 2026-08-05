package cn.vetech.center.hotel.link.elong.common;

/**
 * @author xiaotengyu
 * @since 2021/12/6 16:11
 */
public enum ElongResponseCodeEnum {

    /**
     * 成功
     */
    E0("0","成功"),
    /**
     * 504
     */
    E504("504","请求接口异常");


    private ElongResponseCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
    /**
     * code
     */
    private final String code;
    /**
     * name
     */
    private final String name;

    public String getCode() {
        return code;
    }



    public String getName() {
        return name;
    }


}
