package cn.vetech.center.hotel.link.elong.common;

import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.feign.FeignApi;
import cn.vetech.center.hotel.link.feign.FeignClient;
import feign.Retryer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import cn.vetech.charge.cloud.modules.utils.security.MD5Tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaotengyu
 * @date 2021/2/8 10:51
 */
@Service
public class ElongFeignHttp extends FeignClient {

    /**
     * @param data   业务参数(json)，发送时使用的url编码
     * @param method 需要调用的接口名称
     * @param config 供应商的接口配置
     * @return 请求参数
     */
    public static Map<String, String> getReqMap(String method, String data, ElongConfig config) {
        if (StringUtils.isBlank(method) || StringUtils.isBlank(data) || config == null) {
            return null;
        }
        long epoch = System.currentTimeMillis() / Long.valueOf("1000");
        data = "{\"Version\":\"" + config.getVersion() + "\",\"Local\":\"" + config.getLocal() + "\",\"Request\":" + data + "}";
        String sig = MD5Tool.MD5Encode(epoch + MD5Tool.MD5Encode(data + config.getAppKey()) + config.getSecretKey());
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("format", "json");
        reqMap.put("method", method);
        reqMap.put("user", config.getUser());
        reqMap.put("timestamp", String.valueOf(epoch));
        reqMap.put("signature", sig);
        reqMap.put("data", data);
        return reqMap;
    }


    /***
     * doget request请求
     * @param config 配置
     * @param method 方法名
     * @param data 请求参数
     * @return response string
     */
   public String doGet(ElongConfig config, String method, String data) {
        FeignApi feignHttp = getClient("https://" + config.getUrl(), FeignApi.class,new Retryer.Default(NumConstant.NUM_3000,NumConstant.NUM_3000, NumConstant.NUM_4));
        Map<String, String> hashMap = getReqMap(method, data, config);
        String result = feignHttp.get(hashMap);
        return result;
    }



}
