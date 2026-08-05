package cn.vetech.center.hotel.link.supply.ylfx.common;

import cn.vetech.center.hotel.link.http.HttpClientUtilExt;
import cn.vetech.center.hotel.link.http.HttpService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.util.UrlUtils;
import cn.vetech.charge.cloud.modules.utils.mapper.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxUtilsService {
    /**
     * http请求服务
     */
    @Autowired
    private HttpService httpService;
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxUtilsService.class);

    /**
     * POST请求公共方法
     *
     * @param request    请求参数
     * @param config     配置信息
     * @param methodEnum 供应请求方法枚举
     * @return 原始响应
     */
    public String sendPost(YlfxBaseRequest request, YlfxConfig config, YlfxMethodEnum methodEnum) {
        request.setAppId(config.getAppId());
        request.setAppKey(config.getAppKey());
        String url = UrlUtils.completeUrl(config.getUrl(), methodEnum.getUri());
        String params = JsonMapper.nonEmptyMapper().toJson(request);
        try {
            return httpService.doPostBody(url, params, HttpClientUtilExt.headMapJson());
        } catch (Exception e) {
            LOGGER.error("【{}】请求异常【{}】", methodEnum.getDesc(), e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 校验响应状态
     *
     * @param response response
     * @return ImmutablePair<Boolean, String>
     */
    public ImmutablePair<Boolean, String> checkResponse(YlfxBaseResponse response) {
        if (Objects.isNull(response)) {
            return ImmutablePair.of(false, "RESPONSE为空");
        }
        if (!StringUtils.equals("200", response.getCode())) {
            return ImmutablePair.of(false, response.getMessage());
        }
        return ImmutablePair.of(true, response.getMessage());
    }
}
