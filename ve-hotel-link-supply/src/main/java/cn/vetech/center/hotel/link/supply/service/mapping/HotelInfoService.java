package cn.vetech.center.hotel.link.supply.service.mapping;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.client.gys.mapping.IHotelInfoServiceClient;
import cn.vetech.center.hotel.link.client.gys.mapping.dto.VeRoomSearchDTO;
import cn.vetech.center.hotel.link.client.gys.mapping.vo.VeHotelRoomVO;
import cn.vetech.center.hotel.link.client.gys.mapping.vo.VeRoomBaseInfoVO;
import cn.vetech.center.hotel.link.supply.base.config.BaseConfig;
import cn.vetech.center.hotel.link.supply.service.mapping.model.HotelBaseInfo;
import cn.vetech.center.hotel.link.supply.service.mapping.model.RoomBaseInfo;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 酒店信息
 *
 * @author luqs
 * @version v1.0
 **/
@Service
public class HotelInfoService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(HotelInfoService.class);

    /**
     * 酒店基本信息client
     */
    @Autowired
    private IHotelInfoServiceClient hotelInfoServiceClient;

    /**
     * 获取酒店信息
     *
     * @param config      供应商配置
     * @param hotelIdList 酒店id
     * @return Map<String, HotelRoomVO>
     */
    public Map<String, HotelBaseInfo> getHotelMap(BaseConfig config, List<String> hotelIdList) {
        String supplyKey = config.getFyen() + SymbolConstant.UNDER_LINE + config.getFybh();
        VeRoomSearchDTO searchDTO = new VeRoomSearchDTO();
     searchDTO.setSupplyCode(config.getFybh());
        searchDTO.setSupplyAbbr(config.getFyen());
        searchDTO.setHotelIdList(hotelIdList);
        RestResponse<List<VeHotelRoomVO>> response = hotelInfoServiceClient.getRoomList(searchDTO);
        if (response == null || !response.isSuccess()) {
            logger.warn("【{}】获取酒店房型信息失败，请求：【{}】，响应：【{}】", supplyKey, JacksonUtils.toJsonWithNonEmpty(searchDTO), JacksonUtils.toJsonWithNonEmpty(response));
            return new HashMap<>(0);
        }
        if (CollectionUtils.isEmpty(response.getResult())) {
            logger.warn("【{}】获取酒店房型信息为空，请求：【{}】，响应：【{}】", supplyKey, JacksonUtils.toJsonWithNonEmpty(searchDTO), JacksonUtils.toJsonWithNonEmpty(response));
            return new HashMap<>(0);
        }
        return response.getResult().stream().collect(Collectors.toMap(VeHotelRoomVO::getSupplyHotelId, this::convert2HotelBaseInfo));
    }

    /**
     * 获取房型信息
     *
     * @param hotelBaseInfo 酒店信息
     * @return Map<String, RoomBaseInfo>
     */
    public Map<String, RoomBaseInfo> getRoomMap(HotelBaseInfo hotelBaseInfo) {
        if (Objects.isNull(hotelBaseInfo) || CollectionUtils.isEmpty(hotelBaseInfo.getRoomList())) {
            return new HashMap<>(0);
        }
        return hotelBaseInfo.getRoomList().stream().collect(Collectors.toMap(RoomBaseInfo::getRoomId, roomBaseInfo -> roomBaseInfo));
    }

    /**
     * 酒店基础信息
     *
     * @param hotelRoomVO 酒店信息
     * @return HotelBaseInfo
     */
    private HotelBaseInfo convert2HotelBaseInfo(VeHotelRoomVO hotelRoomVO) {
        HotelBaseInfo hotelBaseInfo = new HotelBaseInfo();
        hotelBaseInfo.setHotelId(hotelRoomVO.getSupplyHotelId());
        hotelBaseInfo.setCityId(hotelRoomVO.getCityId());
        hotelBaseInfo.setRoomList(convert2RoomBaseInfo(hotelRoomVO.getRoomList()));
        return hotelBaseInfo;
    }

    /**
     * 转换成RoomBaseInfo
     *
     * @param roomBaseInfoVOList 房型信息
     * @return List<RoomBaseInfo>
     */
    private List<RoomBaseInfo> convert2RoomBaseInfo(List<VeRoomBaseInfoVO> roomBaseInfoVOList) {
        if (CollectionUtils.isEmpty(roomBaseInfoVOList)) {
            return Collections.emptyList();
        }
        return roomBaseInfoVOList.stream()
                .map(roomBaseInfoVO -> Objects.isNull(roomBaseInfoVO) ? null : convert2RoomBaseInfo(roomBaseInfoVO))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 转换成RoomBaseInfo
     *
     * @param roomBaseInfoVO 房型信息
     * @return RoomBaseInfo
     */
    private RoomBaseInfo convert2RoomBaseInfo(VeRoomBaseInfoVO roomBaseInfoVO) {
        if (Objects.isNull(roomBaseInfoVO)) {
            return null;
        }
        RoomBaseInfo baseInfo = new RoomBaseInfo();
        baseInfo.setRoomId(roomBaseInfoVO.getRoomId());
        baseInfo.setRoomName(roomBaseInfoVO.getRoomName());
        baseInfo.setRoomNum(StringUtils.isNumeric(roomBaseInfoVO.getRoomNum()) ? Integer.parseInt(roomBaseInfoVO.getRoomNum()) : null);
        baseInfo.setBedType(roomBaseInfoVO.getBedType());
        baseInfo.setBedNum(StringUtils.isNumeric(roomBaseInfoVO.getBedNum()) ? Integer.parseInt(roomBaseInfoVO.getBedNum()) : null);
        return baseInfo;
    }
}


