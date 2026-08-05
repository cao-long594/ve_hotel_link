package cn.vetech.center.hotel.link.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Map;

/**
 * <p>
 * 基础权限拦截
 * </p>
 *
 * @author wangkai
 * @since 2021/1/23
 */
public class BaseAuthInterceptor implements RequestInterceptor {

    /**
     *对象头
     */
    private Map<String, String> headerMap;


    /**
     * 构造
     *
     * @param headerMap headerMap
     */
    public BaseAuthInterceptor(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }


    @Override
    public void apply(RequestTemplate template) {
        headerMap.forEach(template::header);
    }
}
