package cn.vetech.center.hotel.link.http;

import cn.vetech.center.hotel.log.annotation.HttpLog;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.cloud.modules.utils.http.HttpTransValue;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lipeng
 */
@Service
public class HttpService {


    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException SupplyConnectException
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params);
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException SupplyConnectException
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params, headmap, transValue);
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException SupplyConnectException
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params, Map<String, String> headmap, int socketTimeout, HttpTransValue transValue) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params, headmap, HttpClientUtilExt.CONNECT_TIMEOUT, socketTimeout, transValue);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params, headmap);
    }

    /**
     * @param url           1
     * @param params        1
     * @param headmap       1
     * @param socketTimeout 读取超时时间  单位毫秒
     * @return 1
     * @throws SupplyConnectException e
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params, Map<String, String> headmap, int socketTimeout) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params, headmap, HttpClientUtilExt.CONNECT_TIMEOUT, socketTimeout);
    }

    /**
     * @param url            1
     * @param params         1
     * @param headmap        1
     * @param connectTimeOut 连接超时时间
     * @param sockTimeOut    操作超时时间
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGet(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeOut, int sockTimeOut) throws SupplyConnectException {
        return HttpClientUtilExt.doGet(url, params, headmap, connectTimeOut, sockTimeOut);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException SupplyConnectException
     */
    @Log(name = "GET请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doGetStreamToBase64(String url, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doGetStreamToBase64(url, headmap);
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostForCps(String url, Map<String, String> params, String service) throws SupplyConnectException {
        return HttpClientUtilExt.doPost(url, params);
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPost(String url, Map<String, String> params) throws SupplyConnectException {
        return HttpClientUtilExt.doPost(url, params);
    }

    /**
     * @param url           1
     * @param params        1
     * @param headmap       1
     * @param socketTimeout 读取超时时间  单位毫秒
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPost(String url, Map<String, String> params, Map<String, String> headmap, int socketTimeout) throws SupplyConnectException {
        return HttpClientUtilExt.doPost(url, params, headmap, HttpClientUtilExt.CONNECT_TIMEOUT, socketTimeout);
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
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPost(String url, Map<String, String> params, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        return HttpClientUtilExt.doPost(url, params, headmap, connectTimeout, socketTimeout);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPost(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doPost(url, params, headmap);
    }

    /**
     * @param url    1
     * @param params 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params, headmap);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params, headmap, transValue);
    }

    /**
     * @param url           1
     * @param params        1
     * @param headmap       1
     * @param socketTimeout 操作超时时间
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params, Map<String, String> headmap, int socketTimeout) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params, headmap, HttpClientUtilExt.CONNECT_TIMEOUT, socketTimeout);
    }

    /**
     * @param url           1
     * @param params        1
     * @param headmap       1
     * @param socketTimeout 操作超时时间
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params, Map<String, String> headmap, int socketTimeout, HttpTransValue transValue) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params, headmap, HttpClientUtilExt.CONNECT_TIMEOUT, socketTimeout, transValue);
    }

    /**
     * @param url            1
     * @param params         1
     * @param headmap        1
     * @param connectTimeout 连接超时时间
     * @param socketTimeout  操作超时时间
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params, Map<String, String> headmap, int connectTimeout, int socketTimeout) throws SupplyConnectException {
        return HttpClientUtilExt.doPostBody(url, params, headmap, connectTimeout, socketTimeout);
    }

    /**
     * @param url     1
     * @param params  1
     * @param headmap 1
     * @return 1
     * @throws Exception 1
     */
    @Log(name = "Stream请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostStream(String url, String params, Map<String, String> headmap) throws Exception {
        return HttpClientUtilExt.doPostStream(url, params, headmap);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doDelete(String url, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doDelete(url, headmap);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doDelete(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doDelete(url, params, headmap);
    }


    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doDelete(String url, Map<String, String> params, Map<String, String> headmap, HttpTransValue transValue) throws SupplyConnectException {
        return HttpClientUtilExt.doDelete(url, params, headmap, transValue);
    }

    /**
     * @param url     1
     * @param headmap 1
     * @return 1
     * @throws SupplyConnectException 1
     */
    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPutBody(String url, String body, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doPutStr(url, body, headmap);
    }

    @Log(name = "POST请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doFormDataPost(String url, Map<String, String> params, Map<String, String> headmap) throws SupplyConnectException {
        return HttpClientUtilExt.doFormDataPost(url, params, headmap);
    }

}