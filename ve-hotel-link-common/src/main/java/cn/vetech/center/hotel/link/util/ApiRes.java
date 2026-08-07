package cn.vetech.center.hotel.link.util;

import cn.vetech.charge.cloud.api.Code;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;

import static cn.vetech.center.hotel.link.enums.DataResponseEnum.FAIL;


/**
 * <p>
 * 返回对像封装
 * </p>
 *
 * @author wangkai
 * @since  2020/10/20
 */
public class ApiRes {


    /**
     * 返回
     * @param data 参数
     * @param <T> 参数
     * @return RestResponse
     */
    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(data);
    }

    /**
     * 返回
     * @param <T> 参数
     * @return RestResponse
     * */
    public static <T> RestResponse<T> success() {
        return success(null);
    }

    /**
     * 返回
     * @param <T> 参数
     * @return RestResponse
     * */
    public static <T> RestResponse<T> fail() {
        return new RestResponse<>(FAIL);
    }

    /**
     * 返回
     * @param msg 参数
     * @param <T> 参数
     * @return RestResponse
     */
    public static <T> RestResponse<T> fail(String msg) {
        return fail(null, msg);
    }

    /**
     * 返回
     * @param t 参数
     * @param msg 参数
     * @param <T> 参数
     * @return RestResponse
     */
    public static <T> RestResponse<T> fail(T t, String msg) {
        RestResponse<T> restResponse = new RestResponse<>(FAIL, msg);
        restResponse.setResult(t);
        restResponse.setMessage(msg);
        return restResponse;
    }

    /**
     * 返回
     * @param code 参数
     * @param <T> 参数
     * @return RestResponse
     */
    public static <T> RestResponse<T> response(Code code) {
        return response(code, null);
    }

    /**
     * 返回
     * @param code 参数
     * @param data 参数
     * @param <T> 参数
     * @return RestResponse
     */
    public static <T> RestResponse<T> response(Code code, T data) {
        RestResponse<T> response = new RestResponse<>(code);
        response.setResult(data);
        return response;
    }
    /**
     * 自定义返回信息 error会替换 枚举中message中%s内容
     * @param code 参数
     * @param <T> 参数
     * @param error 自定义错误信息
     * @return RestResponse
     */
    public static <T> RestResponse<T> response(Code code,String error) {
        String msg = String.format(code.getMessage(), error);
        RestResponse<T> restResponse = new RestResponse<>(code);
        restResponse.setMessage(msg);
        return restResponse;
    }

}
