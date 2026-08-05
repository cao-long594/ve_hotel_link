package cn.vetech.center.hotel.link.api.enums;

/**
 * @author xingyanyan on 2018/6/4.
 */
public enum HotelErrorCodeEnum {

    /**
     * 成功
     */
    Success("0", "成功"),
    /**
     * 接口请求对象为空
     */
    ReqNull("VE0000", "接口请求对象为空"),
    /**
     * 已预订，未确认
     */
    ResNull("VE0001", "接口返回对象为空"),
    /**
     * 转换对象为空
     */
    ConResNull("VE00002", "转换对象为空"),
    /**
     * 接口返回错误
     */
    ResError("VE00003", "接口返回错误"),
    /**
     * 获取对象节点为空
     */
    ObjError("VE00004", "获取对象节点为空"),
    /**
     * 获取token失败
     */
    TokenError("VE00005", "获取token失败"),
    /**
     * 请求超频
     */
    ReqOverclock("VE00006","请求接口超频")
    ;

    /**
     *
     */
    private final String code;
    /**
     *
     */
    private final String errorms;

    private HotelErrorCodeEnum(String code, String errorms) {
        this.code = code;
        this.errorms = errorms;
    }

    public String getCode() {
        return code;
    }



    public String getErrorms() {
        return errorms;
    }


}
