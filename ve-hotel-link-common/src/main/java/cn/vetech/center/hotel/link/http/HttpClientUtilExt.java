package cn.vetech.center.hotel.link.http;

import cn.vetech.charge.cloud.modules.utils.http.HttpClientUtil;
import cn.vetech.charge.cloud.modules.utils.http.HttpTransValue;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cpfl.CpbhEnum;
import cn.vetech.charge.fcnet.connect.FcnetConnectService;
import cn.vetech.charge.fcnet.connect.request.FcnetRequest;
import cn.vetech.charge.fcnet.connect.response.FcnetResponse;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author lipeng Created by vetech on 2019/6/20.
 */
@Component
public class HttpClientUtilExt {

    private static final Set<String> STATUS_CODE_SET = Sets.newHashSet("200", "201", "202", "203");
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtilExt.class);
    /**
     *
     */
    private static final String CHARSET = "UTF-8";
    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 4000;
    /**
     * 查询价格超时时间 10秒
     */
    public static final int SOCKET_TIMEOUT_SEARCH = 1000 * 10;
    /**
     * 读取超时时间 600秒 3分钟
     */
    private static final int SOCKET_TIMEOUT = 1000 * 60*3;
    /**
     *
     */
    private static String fcnet;

    /**
     *
     */
    private static FcnetConnectService fcnetConnectService;

    @Autowired
    public void setFcnetConnectService(FcnetConnectService fcnetConnectService) {
        HttpClientUtilExt.fcnetConnectService = fcnetConnectService;
    }

    @Value("${fcnet_fcproxymode:}")
    public void setFcnet(String fcnet) {
        HttpClientUtilExt.fcnet = fcnet;
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGet(String url, Map<String, String> params) throws SupplyConnectException {
        return doGet(url, params, null);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return doGet(url, params, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url            1
     * @param params         1
     * @param headmap        1
     * @param connectTimeout 1
     * @param socketTimeout  1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        return doGet(url, params, headmap, connectTimeout, socketTimeout, null);
    }

    public static String doGet(String url, Map<String, String> params, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return doGet(url, params, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT, transValue);
    }

    /**
     * @param url 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGetStreamToBase64(String url) throws SupplyConnectException {
        return doGetStreamToBase64(url, null);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGetStreamToBase64(String url, Map<String, String> headmap) throws SupplyConnectException {
        return doGetStreamToBase64(url, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url            1
     * @param headmap        1
     * @param connectTimeout 1
     * @param socketTimeout  1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGetStreamToBase64(String url, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                byte[] bytes = HttpClientUtil.doGetStream(url, headmap, connectTimeout, socketTimeout);
                return Base64Utils.encodeToString(bytes);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求url:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            return null;
//            FcnetRequest fcnetRequest = new FcnetRequest();
//            fcnetRequest.setCharset(CHARSET);
//            fcnetRequest.setConnectTimeout(connectTimeout);
//            fcnetRequest.setSoTimeout(socketTimeout);
//            fcnetRequest.setMethodName("GETSTREAM");
//            fcnetRequest.setHeadmap(headmap);
//            fcnetRequest.setUrl(url);
//            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
//            if (StringUtils.equals("200", restResponse.getStatus())) {
//                return restResponse.getResult().getContent();
//            } else {
//                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
//                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
//            }
        }
    }

    /**
     * @param url            1
     * @param params         1
     * @param headmap        1
     * @param connectTimeout 1
     * @param socketTimeout  1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout, HttpTransValue transValue) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doGet(url, params, headmap, CHARSET, connectTimeout, socketTimeout, transValue);
            } catch (RuntimeException e) {
                return getResultOnExceptionByStatusCode(transValue, e);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求url:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(connectTimeout);
            fcnetRequest.setSoTimeout(socketTimeout);
            fcnetRequest.setMethodName("GET");
            fcnetRequest.setParam(params);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setUrl(url);
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            setHttpTransValue(transValue, restResponse);
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }

    private static String getResultOnExceptionByStatusCode(HttpTransValue transValue, RuntimeException e) throws SupplyConnectException {
        if (Objects.isNull(transValue)) {
            throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
        }

        Optional<String> statusCodeOpt = transValue.getVeHeaders().stream().map(veHeader -> veHeader.getName().equals("statusCode") ? veHeader.getValue() : null).filter(StringUtils::isNotBlank).findFirst();
        if (!statusCodeOpt.isPresent()) {
            throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
        }
        String statusCode = statusCodeOpt.get();
        if (!STATUS_CODE_SET.contains(statusCode)) {
            throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
        }
        String message = e.getMessage();
        String suffix = "HttpClient,error status code :" + statusCode + ",";
        String result = StringUtils.removeStart(message, suffix);
        return result;
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPost(String url, Map<String, String> params) throws SupplyConnectException {
        return doPost(url, params, null);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return doPost(url, params, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url            1
     * @param params         1
     * @param headmap        1
     * @param connectTimeout 1
     * @param socketTimeout  1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doPost(url, params, headmap, CHARSET, connectTimeout, socketTimeout);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求url:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(connectTimeout);
            fcnetRequest.setSoTimeout(socketTimeout);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setMethodName("POST");
            fcnetRequest.setParam(params);
            fcnetRequest.setUrl(url);
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }

    /**
     * @return 1
     */
    public static Map<String, String> headMapJson() {
        Map<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        return head;
    }

    /**
     * @return 1
     */
    public static Map<String, String> headMapXml() {
        Map<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/xml");
        return head;
    }

    /**
     * @return 1
     */
    public static Map<String, String> headMapForm() {
        Map<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/x-www-form-urlencoded");
        return head;
    }

    /**
     * @param url  1
     * @param body 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPostBody(String url, String body) throws SupplyConnectException {
        return doPostBody(url, body, null);
    }

    /**
     * @param url     1
     * @param body    1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPostBody(String url, String body, Map<String, String> headmap) throws SupplyConnectException {
        return doPostBody(url, body, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url     1
     * @param body    1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPostBody(String url, String body, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return doPostBody(url, body, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT, transValue);
    }


    public static String doPostBody(String url, String body, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        return doPostBody(url, body, headmap, connectTimeout, socketTimeout, null);
    }

    /**
     * @param url            1
     * @param body           1
     * @param headmap        1
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  操作超时时间
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPostBody(String url, String body, Map<String, String> headmap, int connectTimeout, int socketTimeout, HttpTransValue transValue) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doPostStr(url, body, headmap, CHARSET, connectTimeout, socketTimeout, transValue);
            } catch (RuntimeException e) {
                return getResultOnExceptionByStatusCode(transValue, e);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求url:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(connectTimeout);
            fcnetRequest.setSoTimeout(socketTimeout);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setMethodName("POSTSTR");
            fcnetRequest.setPostStr(body);
            fcnetRequest.setUrl(url);
            if (MapUtils.isNotEmpty(headmap)) {
                String apiId = headmap.remove("apiId");
                fcnetRequest.setApiid(apiId);
            }
            fcnetRequest.setCpbh(CpbhEnum.FL0300.getCode());
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            setHttpTransValue(transValue, restResponse);
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }


    private static void setHttpTransValue(HttpTransValue transValue, RestResponse<FcnetResponse> restResponse) {
        Optional<HttpTransValue> httpTransValueOpt = Optional.ofNullable(restResponse).map(RestResponse::getResult).map(FcnetResponse::getTransValue);
        if (httpTransValueOpt.isPresent()) {
            transValue = httpTransValueOpt.get();
        }
    }

    /**
     * @param url  1
     * @param body 1
     * @return 1
     * @throws Exception 1
     */
    public static String doPostStream(String url, String body) throws Exception {
        return doPostStream(url, body, null);
    }

    /**
     * @param url     1
     * @param body    1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPostStream(String url, String body, Map<String, String> headmap) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                byte[] bytes = HttpClientUtil.doPostStream(url, body.getBytes(CHARSET), headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
                return new String(bytes, CHARSET);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求地址:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(CONNECT_TIMEOUT);
            fcnetRequest.setSoTimeout(SOCKET_TIMEOUT);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setMethodName("POSTSTREAM");
            try {
                fcnetRequest.setStream(body.getBytes(CHARSET));
            } catch (Exception e) {
            }
            fcnetRequest.setUrl(url);
            fcnetRequest.setCpbh(CpbhEnum.FL0300.getCode());
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求地址:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doDelete(String url, Map<String, String> headmap) throws SupplyConnectException {
        return doDelete(url, null, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doDelete(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return doDelete(url, params, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }


    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doDelete(String url, Map<String, String> params, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return doDelete(url, null, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT, transValue);
    }


    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doDelete(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int soTimeout) throws SupplyConnectException {
        return doDelete(url, params, headmap, connectTimeout, soTimeout, null);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doDelete(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int soTimeout, HttpTransValue transValue) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doDelete(url, params, headmap, CHARSET, connectTimeout, soTimeout, transValue);
            } catch (RuntimeException e) {
                return getResultOnExceptionByStatusCode(transValue, e);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求地址:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(CONNECT_TIMEOUT);
            fcnetRequest.setSoTimeout(SOCKET_TIMEOUT);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setMethodName("DELETE");
            fcnetRequest.setParam(params);
            fcnetRequest.setUrl(url);
            fcnetRequest.setCpbh(CpbhEnum.FL0300.getCode());
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            setHttpTransValue(transValue, restResponse);
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求地址:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }

    }

    public static String doPutStr(String url, String str, Map<String, String> headmap) throws SupplyConnectException {
        return doPutStr(url, str, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    public static String doPutStr(String url, String str, Map<String, String> headmap, int connectTimeout, int soTimeout) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doPutStr(url, str, headmap, CHARSET, connectTimeout, soTimeout);
            } catch (Exception e) {
                LOGGER.error("请求供应商异常,请求地址:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(connectTimeout);
            fcnetRequest.setSoTimeout(soTimeout);
            fcnetRequest.setMethodName("PUTSTR");
            fcnetRequest.setPostStr(str);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setUrl(url);
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }

    public static String doFormDataPost(String url, Map<String, String> params) throws SupplyConnectException {
        return doFormDataPost(url, params, null);
    }

    public static String doFormDataPost(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return doFormDataPost(url, params, headmap, CONNECT_TIMEOUT, SOCKET_TIMEOUT);
    }


    public static String doFormDataPost(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        return doFormDataPost(url, params, headmap, connectTimeout, socketTimeout, null);
    }

    public static String doFormDataPost(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout, HttpTransValue transValue) throws SupplyConnectException {
        if (fcnetConnectService == null || StringUtils.isBlank(fcnet)) {
            try {
                return HttpClientUtil.doFormDataPost(url, params, headmap, connectTimeout, socketTimeout, CHARSET, transValue);
            } catch (RuntimeException e) {
                return getResultOnExceptionByStatusCode(transValue, e);
            }
             catch (Exception e) {
                LOGGER.error("请求供应商异常,请求url:{}", url, e);
                throw new SupplyConnectException(String.format("请求供应商异常%s", e.getMessage()));
            }
        } else {
            FcnetRequest fcnetRequest = new FcnetRequest();
            fcnetRequest.setCharset(CHARSET);
            fcnetRequest.setConnectTimeout(connectTimeout);
            fcnetRequest.setSoTimeout(socketTimeout);
            fcnetRequest.setHeadmap(headmap);
            fcnetRequest.setMethodName("POST");
            fcnetRequest.setParam(params);
            fcnetRequest.setUrl(url);
            RestResponse<FcnetResponse> restResponse = fcnetConnectService.connect(fcnetRequest, "酒店外部供应商");
            setHttpTransValue(transValue, restResponse);
            if (StringUtils.equals("200", restResponse.getStatus())) {
                return restResponse.getResult().getContent();
            } else {
                LOGGER.error("请求供应商异常,请求url:{},{},{}", url, restResponse.getStatus(), restResponse.getMessage());
                throw new SupplyConnectException(String.format("请求供应商异常%s%s", restResponse.getStatus(), restResponse.getMessage()));
            }
        }
    }

}