package cn.vetech.center.hotel.link.http;

import cn.vetech.center.hotel.log.annotation.HttpLog;
import cn.vetech.center.hotel.log.annotation.Log;
import org.springframework.stereotype.Service;

/**
 * 用于推送数据 记录通用日志
 *
 * @author pengyefei
 * @version 1.0
 * @since 2023/7/24 15:30
 */
@Service
public class PushHttpService {
    /**
     * @param url   接口名
     * @param params 数据
     * @return String
     */
    @Log(name = "BODY请求", logParam = true, logReturn = false)
    @HttpLog(urlName = "url", paramName = "params")
    public String doPostBody(String url, String params) {
        return url;
    }
}
