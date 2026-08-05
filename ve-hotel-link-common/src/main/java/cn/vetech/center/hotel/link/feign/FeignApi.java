package cn.vetech.center.hotel.link.feign;

import feign.RequestLine;

import java.util.Map;

/**
 * @author xiaotengyu
 * @since 2021/2/8 14:19
 */
public interface FeignApi {

    /**
     * get请求
     * @param map map
     * @return string
     */
    @RequestLine("GET")
    String get(Map<String, String> map);

    /**
     * post 请求
     * @param map map
     * @return string
     */
    @RequestLine("POST")
    String post(Map<String, String> map);

}
