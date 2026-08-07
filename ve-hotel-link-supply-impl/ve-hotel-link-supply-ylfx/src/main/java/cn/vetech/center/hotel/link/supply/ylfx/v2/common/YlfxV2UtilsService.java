package cn.vetech.center.hotel.link.supply.ylfx.v2.common;

import cn.vetech.center.hotel.link.http.HttpClientUtilExt;
import cn.vetech.center.hotel.link.http.HttpService;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.util.UrlUtils;
import cn.vetech.charge.cloud.modules.utils.security.MD5Tool;
import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 易旅分销 V2 公共通信服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2UtilsService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2UtilsService.class);
    /**
     * HTTP 请求服务
     */
    @Autowired
    private HttpService httpService;

    /**
     * 发送 V2 POST 请求
     *
     * @param request 请求参数
     * @param config 供应商配置
     * @param uri 接口地址
     * @return 原始响应
     * @throws Exception 请求异常
     */
    public String sendPost(Object request, YlfxConfig config, String uri) {
        String appid = StringUtils.defaultIfBlank(config.getAppid(), config.getAppId());
        String timestamp = String.valueOf(System.currentTimeMillis());
        Map<String, String> headers = HttpClientUtilExt.headMapJson();
        headers.put("appid", appid);
        headers.put("timestamp", timestamp);
        headers.put("signature", MD5Tool.MD5Encode(MD5Tool.MD5Encode(appid + config.getSecret()) + timestamp));
        String baseUrl = StringUtils.defaultIfBlank(config.getNewUrl(), config.getUrl());
        String url = UrlUtils.completeUrl(baseUrl, uri);
        String params = JsonMapper.nonEmptyMapper().toJson(request);
        try {
            return httpService.doPostBody(url, params, headers);
        } catch (Exception e) {
            LOGGER.error("【易旅分销 V2】请求异常【{}】", e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }
}
