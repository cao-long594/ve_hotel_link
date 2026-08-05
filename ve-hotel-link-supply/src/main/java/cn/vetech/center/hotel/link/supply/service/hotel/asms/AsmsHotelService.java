package cn.vetech.center.hotel.link.supply.service.hotel.asms;

import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelGysdx;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.IHotelLinkGetJdxxxxVO;
import cn.vetech.center.hotel.link.api.ihotelgetjdlb.vo.IHotelJdb;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.enums.HotelServiceApiEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.hotel.IHotelService;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.link.supply.cps.base.CpsConfig;
import cn.vetech.center.hotel.link.supply.cps.base.CpsHttpService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.dto.HotelGetJdxxxxRequest;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelGetJdxxxxResponse;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelGysdxVO;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelJdb;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * asms酒店信息
 *
 * @author luqs
 * @version v1.0
 **/
@Service
public class AsmsHotelService implements IHotelService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AsmsHotelService.class);
    /**
     * 酒店配置分发service
     */
    @Autowired
 private HotelConfigDistributeService configDistributeService;
    /**
     * http service
     */
    @Autowired
    private CpsHttpService httpService;

    @Override
    public HotelVO getHotel(HotelDTO dto) {
        dto.setSupplier(configDistributeService.getCpsPlatformSuppler(dto));
        CpsConfig config = JacksonUtils.parseNonEmpty(JacksonUtils.toJsonWithNonEmpty(dto.getSupplier()), CpsConfig.class);
        HotelGetJdxxxxRequest request = new HotelGetJdxxxxRequest();
        request.setCompid(config.getCompid());
        request.setService(HotelServiceApiEnum.HOTEL_DETAIL.getCode());
        request.setJdid(dto.getLocalHotelId());
        try {
            HotelGetJdxxxxResponse response = httpService.execute(request, config, HotelGetJdxxxxResponse.class);
            if (Objects.isNull(response)) {
                logger.warn("[asms]获取酒店【{}】信息响应为空，请求：【{}】", request.getJdid(), JacksonUtils.toJsonWithNonEmpty(request));
                return null;
            }
            HotelJdb hotel = response.getHotel();
            if (hotel == null) {
                logger.warn("[asms]获取酒店【{}】信息为空，请求：【{}】，响应：【{}】", request.getJdid(), JacksonUtils.toJsonWithNonEmpty(request), JacksonUtils.toJsonWithNonEmpty(response));
                return null;
            }

            HotelVO hotelVO = new HotelVO();
            hotelVO.setJdid(hotel.getJdid());
            hotelVO.setZt(hotel.getZt());
            List<HotelGysdxVO> mappingList = hotel.getGyslist();
            if (CollectionUtils.isEmpty(mappingList)) {
                return hotelVO;
            }
              hotelVO.setMappers(mappingList.stream().map(mapping -> {
                Mapper mapper = new Mapper();
                mapper.setFybh(mapping.getGysbh());
                mapper.setHotelid(mapping.getHotelid());
                return mapper;
            }).collect(Collectors.toList()));
            return hotelVO;
        } catch (Exception e) {
            logger.error("[asms]获取酒店【{}】信息异常，请求：【{}】", request.getJdid(), JacksonUtils.toJsonWithNonEmpty(request), e);
        }
        return null;
    }

    @Override
    public HotelVO getIHotel(HotelDTO dto) throws SupplyConnectException {
        dto.setSupplier(configDistributeService.getCpsPlatformSuppler(dto));
        CpsConfig config = JacksonUtils.parseNonEmpty(JacksonUtils.toJsonWithNonEmpty(dto.getSupplier()), CpsConfig.class);
        HotelGetJdxxxxRequest request = new HotelGetJdxxxxRequest();
        request.setCompid(config.getCompid());
        request.setService(HotelServiceApiEnum.INTER_HOTEL_DETAIL.getCode());
        request.setJdid(dto.getLocalHotelId());
        IHotelLinkGetJdxxxxVO response = httpService.execute(request, config, IHotelLinkGetJdxxxxVO.class);
        if (response == null) {
            logger.warn("[asms]获取国际酒店【{}】信息响应为空，请求：【{}】", request.getJdid(), JacksonUtils.toJsonWithNonEmpty(request));
            return null;
        }
          IHotelJdb hotel = response.getHotel();
        if (hotel == null) {
            logger.warn("[asms]获取国际酒店【{}】信息为空，请求：【{}】，响应：【{}】", request.getJdid(), JacksonUtils.toJsonWithNonEmpty(request), JacksonUtils.toJsonWithNonEmpty(response));
            return null;
        }

        HotelVO hotelVO = new HotelVO();
        hotelVO.setZt(hotel.getZt());
        hotelVO.setJdid(hotel.getJdid());
        List<HotelGysdx> mappingList = hotel.getGyslist();
        if (ListUtil.isEmpty(mappingList)) {
            return hotelVO;
        }
        hotelVO.setMappers(mappingList.stream().map(gysdxVO -> {
            Mapper mapper = new Mapper();
            mapper.setFybh(gysdxVO.getGysbh());
            mapper.setHotelid(gysdxVO.getHotelid());
            return mapper;
        }).collect(Collectors.toList()));
        return hotelVO;

    }
}
