package cn.vetech.center.hotel.link.enums;

import cn.vetech.charge.cloud.api.Code;

/**
 * <p>
 * 响应枚举定义
 * </p>
 *
 * @author wangkai
 * @since 2020/10/20
 */
public enum DataResponseEnum implements Code {

    /**
     * 处理失败
     */
    FAIL("-1", "处理失败"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("500", "系统异常"),
    /**
     * 系统异常，查询报价
     */
    SYSTEM_ERROR_RATE_SEARCH("500", "系统异常:%s"),
    /**
     * 请求参数为空
     */
    PARAMETER_IS_EMPTY("4001", "请求参数为空"),


    /**
     * 供应商接口请求频率限制
     */
    SUPPLIER_FREQUENCY_LIMIT("5001", "接口请求频率限制"),

    /**
     * 供应商配置获取失败
     */
    SUPPLIER_CONFIG_GET_FAILED("5002", "供应商配置获取失败"),

    /**
     * 供应商未实现该接口
     */
    SUPPLIER_NOT_IMPLEMENTED("5003", "供应商未实现该接口"),

    /**
     * 供应商未实现该接口
     */
    SUPPLIER_NOT_SUPPORT("5004", "供应商不支持该接口"),

    /**
     * 供应商接口异常
     * %s ApiRes会替换为对应传入的供应商异常信息
     */
    SUPPLIER_EXCEPTION("6000", "供应商接口异常:%s"),

    /**
     * 供应商初始化基础数据失败
     */
    SUPPLIER_INIT_FAIL("6001", "供应商初始化基础数据失败"),

    ;
    /**
     * 响应码
     */
    private final String code;

    /**
     * 提示信息
     */
    private final String message;

    private DataResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }
}
