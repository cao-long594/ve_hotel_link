package cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic;

import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.enums.YlfxV2MethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.request.YlfxV2HotelInfosRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.response.YlfxV2HotelInfosHotel;
import cn.vetech.center.hotel.link.supply.ylfx.v2.hotelstatic.response.YlfxV2HotelInfosResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * 易旅分销 V2 静态信息公共服务。
 *
 * @author 6161
 * @date 2026/08/07
 */
@Service
public class YlfxV2HotelStaticCommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2HotelStaticCommonService.class);

    @Autowired
    private YlfxV2UtilsService utilsService;

    /**
     * 批量查询酒店静态信息。
     *
     * @param hotelCodes 酒店编码，接口单次最多支持 50 个
     * @param config 供应商配置
     * @return 酒店静态信息，响应异常或无数据时返回 null
     */
    public List<YlfxV2HotelInfosHotel> getHotelInfos(List<String> hotelCodes, YlfxConfig config) {
        if (CollectionUtils.isEmpty(hotelCodes)) {
            return null;
        }
        try {
            List<YlfxV2HotelInfosHotel> hotelInfos = new ArrayList<>();
            for (int start = 0; start < hotelCodes.size(); start += 50) {
                int end = Math.min(start + 50, hotelCodes.size());
                List<String> batchCodes = hotelCodes.subList(start, end);
                List<YlfxV2HotelInfosHotel> batchInfos = getHotelInfosBatch(batchCodes, config);
                if (CollectionUtils.isNotEmpty(batchInfos)) {
                    hotelInfos.addAll(batchInfos);
                }
            }
            return CollectionUtils.isEmpty(hotelInfos) ? null : hotelInfos;
        } catch (Exception e) {
            LOGGER.error("易旅分销 V2 查询酒店静态信息异常：{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 查询一批酒店静态信息。
     *
     * @param hotelCodes 本批酒店编码
     * @param config 供应商配置
     * @return 本批酒店信息
     */
    private List<YlfxV2HotelInfosHotel> getHotelInfosBatch(List<String> hotelCodes, YlfxConfig config) {
        YlfxV2HotelInfosRequest request = convertRequest(hotelCodes, config);
        String result = utilsService.sendPost(request, config, YlfxV2MethodEnum.HOTEL_INFOS);
        if (StringUtils.isBlank(result)) {
            LOGGER.warn("易旅分销 V2 查询酒店静态信息失败：响应为空");
            return null;
        }
        YlfxV2HotelInfosResponse response = JacksonUtils.parseNonEmpty(result, YlfxV2HotelInfosResponse.class);
        if (response == null || !StringUtils.equals("200", response.getCode())) {
            LOGGER.warn("易旅分销 V2 查询酒店静态信息失败：{}", response == null ? "响应为空" : response.getMessage());
            return null;
        }
        if (CollectionUtils.isEmpty(response.getData())) {
            LOGGER.warn("易旅分销 V2 查询酒店静态信息失败：酒店数据为空");
            return null;
        }
        return response.getData();
    }

    /**
     * 转换酒店静态信息请求。
     *
     * @param hotelCodes 酒店编码
     * @param config 供应商配置
     * @return V2 请求对象
     */
    protected YlfxV2HotelInfosRequest convertRequest(List<String> hotelCodes, YlfxConfig config) {
        YlfxV2HotelInfosRequest request = new YlfxV2HotelInfosRequest();
        request.setCustomerCode(config.getCustomerCode());
        request.setHotelCodes(hotelCodes);
        return request;
    }
}
