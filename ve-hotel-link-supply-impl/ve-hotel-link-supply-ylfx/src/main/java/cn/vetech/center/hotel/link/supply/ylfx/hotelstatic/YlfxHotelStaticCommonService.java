package cn.vetech.center.hotel.link.supply.ylfx.hotelstatic;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.request.GetRoomListByHotelIdRequest;
import cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.response.GetRoomListByHotelIdData;
import cn.vetech.center.hotel.link.supply.ylfx.hotelstatic.response.GetRoomListByHotelIdResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 静态接口公共服务
 *
 * @author 6161
 * @date 2024/07/19
 */
@Service
public class YlfxHotelStaticCommonSercive {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxHotelStaticCommonSercive.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 根据酒店ID获取房型列表数据
     *
     * @param hotelId 酒店id
     * @param config  配置信息
     * @return GetRoomListByHotelIdResponse
     */
    public List<GetRoomListByHotelIdData> getRoomListByHotelId(String hotelId, YlfxConfig config) {
        try {
            GetRoomListByHotelIdRequest request = new GetRoomListByHotelIdRequest();
            request.setHotelId(hotelId);
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.LIST_ROOM_TYPE_BY_HOTEL_ID);
            if (StringUtils.isBlank(res)) {
                logger.warn("酒店ID：【{}】获取房型列表失败：响应RES为空", hotelId);
                return null;
            }
            GetRoomListByHotelIdResponse response = JacksonUtils.parseNonEmpty(res, GetRoomListByHotelIdResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                logger.warn("酒店ID：【{}】获取房型列表失败：【{}】", hotelId, immutablePair.getRight());
                return null;
            }
            if (CollectionUtils.isEmpty(response.getRoomList())) {
                logger.warn("酒店ID：【{}】获取房型列表失败：房型列表数据为空", hotelId);
                return null;
            }
            return response.getRoomList();
        } catch (Exception e) {
            logger.error("【{}】酒店ID【{}】获取房型列表异常【{}】", config.getZhmc(), hotelId, e.getMessage(), e);
            return null;
        }
    }
}