package cn.vetech.center.hotel.link.vcc.tranhub;

import cn.vetech.center.hotel.link.entity.vcc.VccBaseRequest;
import cn.vetech.center.hotel.link.entity.vcc.VccConfig;
import cn.vetech.center.hotel.link.entity.vcc.vccapply.dto.VccApplyRequest;
import cn.vetech.center.hotel.link.entity.vcc.vccapply.vo.VccApplyResponse;
import cn.vetech.center.hotel.link.entity.vcc.vccquery.dto.VccQueryRequest;
import cn.vetech.center.hotel.link.entity.vcc.vccquery.vo.VccQueryResponse;
import cn.vetech.center.hotel.link.enums.VccGeneralEnum;
import cn.vetech.center.hotel.link.http.HttpClientUtilExt;
import cn.vetech.center.hotel.link.http.HttpService;
import cn.vetech.center.hotel.link.util.AES;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.TranHubAPIUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xiaotengyu
 * @since 2021/9/27 15:00
 */
@Service
public class HttpServiceVcc {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(HttpServiceVcc.class);

    /**
     * httpservice
     */
    @Autowired
    private HttpService httpService = new HttpService();

    /***
     * 参数加密、签名
     * @param orgRequest 请求参数
     * @param config 配置信息
     * @return string
     */
    protected String encodeAndSign(VccBaseRequest orgRequest, VccConfig config) {
        //转换为jsonMap,会把空串删除
        Map map = (Map) JSON.toJSON(orgRequest);
        logger.info("map:{}", JacksonUtils.toJsonWithDefault(map));
        //签名
        String sign = TranHubAPIUtils.sign(map, config.getSha256Key());
        //全报文加密
        String aesValue = AES.encryptToBase64(map.toString(), config.getAesKey());
        //构建请求
        VccBaseRequest baseRequest = new VccBaseRequest();
        baseRequest.setMerchantNo(orgRequest.getMerchantNo());
        baseRequest.setRequestNo(orgRequest.getRequestNo());
        baseRequest.setSign(sign);
        baseRequest.setContent(aesValue);
        baseRequest.setProductCode(orgRequest.getProductCode());
        String jsonRequest = JacksonUtils.toJsonWithNonNull(baseRequest);
        logger.info("vccpay:baseRequest:{}", jsonRequest);
        return jsonRequest;
    }
    /**
     * 请求轻住api
     *
     * @param responseT   返回对象
     * @param baseRequest 请求对象
     * @param apiSiteEnum 方法枚举
     * @param config      配置信息
     * @param <T>         t
     * @return t
     */
    public <T> Optional<T> sendInvokeApi(Class<T> responseT
            , VccBaseRequest baseRequest
            , VccConfig config
            , VccGeneralEnum.ApiSiteEnum apiSiteEnum) {
        String result = StringUtils.EMPTY;
        String requestJson = JacksonUtils.toJsonWithDefault(baseRequest);
        try {
            String requestData = encodeAndSign(baseRequest, config);
            Map<String, String> headMapJson = HttpClientUtilExt.headMapJson();
            result = httpService.doPostBody(config.getBaseUrl() + apiSiteEnum.getUrl(), requestData, headMapJson);
            if (StringUtils.isEmpty(result)) {
                logger.warn("vccpay：{}请求供应商返回为空；request:{}", apiSiteEnum.getName(), requestJson);
                return Optional.empty();
            }
            VccBaseRequest baseResponse = JacksonUtils.parseNonEmpty(result, VccBaseRequest.class);
            String jsonMap = AES.decryptFromBase64(baseResponse.getContent(), config.getAesKey());
            T response = JacksonUtils.parseNonEmpty(jsonMap, responseT);
            if (Objects.isNull(response)) {
                logger.warn("vccpay：{}请求供应商返回格式为空；request:{}；response：{}", apiSiteEnum.getName(), requestJson, result);
                return Optional.empty();
            }
            logger.info("vccpay:response:{}", JacksonUtils.toJsonWithDefault(response));
            return Optional.of(response);
        } catch (Exception ex) {
            logger.error("vccpay：{}异常；requestData:{};response:{}", apiSiteEnum.getName(), requestJson, result, ex);
        }
        return Optional.empty();
    }

    /**
     * 申请卡接口
     * 申请虚拟卡
     *
     * @param applyRequest applyRequest
     * @param vccConfig    vccConfig
     * @return Optional<VccApplyResponse>
     */
    public Optional<VccApplyResponse> vccApply(VccApplyRequest applyRequest, VccConfig vccConfig) {
        return sendInvokeApi(VccApplyResponse.class, applyRequest, vccConfig, VccGeneralEnum.ApiSiteEnum.APPLY);
    }

    public Optional<VccQueryResponse> vccSettlebill(VccQueryRequest applyRequest, VccConfig vccConfig) {
        return sendInvokeApi(VccQueryResponse.class, applyRequest, vccConfig, VccGeneralEnum.ApiSiteEnum.SETTLEBILL);
    }

}