package cn.vetech.center.hotel.link.elong.ordercancel;

import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.ordercancel.request.ElongOrderIncrRequest;
import cn.vetech.center.hotel.link.elong.ordercancel.response.ElongOrderIncrResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xiaotengyu
 * @since 2022-07-27 11:11
 */
@Service
public class ElongOrderIncrService  extends ElongHttp implements ElongService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 艺龙订单取消接口名
     */
    private final String method = "hotel.order.cancel";
    /**
     * 艺龙订单取消使用https
     */
    private final String http = "https";

    @Override
    public ElongResponse execute(ElongRequest req) {
        ElongOrderIncrResponse response = new ElongOrderIncrResponse();
        String result = StringUtils.EMPTY;
        try{
            result = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
            if (StringUtils.isBlank(result)) {
                response.setCode(HotelErrorCodeEnum.ReqNull.getCode());
                response.setErrorMsg("请求结果为空");
                return response;
            }
            response = JacksonUtils.parseNonEmpty(result, ElongOrderIncrResponse.class);
        }catch (Exception ex){
            logger.error("艺龙增量接口:异常;request:{};response:{}",req.toJson(),result,ex);
            response.setCode(HotelErrorCodeEnum.ResError.getCode());
            response.setErrorMsg("异常");
            return response;
        }
        return response;
    }

    /**
     * 构建请求对象
     * @param lastId 最后的更新ID
     * @param count 抓取的数量
     * @return request
     */
    public ElongOrderIncrRequest buildRequest(Long lastId,Integer count){
        ElongOrderIncrRequest request = new ElongOrderIncrRequest();
        request.setCount(count);
        request.setLastId(lastId);
        return request;
    }
}
