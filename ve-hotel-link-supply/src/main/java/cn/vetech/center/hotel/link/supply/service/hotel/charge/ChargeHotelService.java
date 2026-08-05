package cn.vetech.center.hotel.link.supply.service.hotel.charge;

import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelGysdx;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.HotelLinkGetJdxxxxDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.IHotelLinkGetJdxxxxVO;
import cn.vetech.center.hotel.link.api.ihotelgetjdlb.vo.IHotelJdb;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.enums.CpsHotelServiceEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.hotel.IHotelService;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.link.supply.cps.base.CpsConfig;
import cn.vetech.center.hotel.link.supply.cps.cds.CpsSignleEsInvokeRequestService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelGetJdxxxxResponse;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelGysdxVO;
import cn.vetech.center.hotel.link.supply.service.hotel.charge.vo.HotelJdb;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lipeng
 */
@Service
public class ChargeHotelService implements IHotelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeHotelService.class);
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configDistributeService;
    /**
     * cps请求服务
     */
    @Autowired
   private CpsSignleEsInvokeRequestService cpsInvokeRequestService;

    @Override
    public HotelVO getHotel(HotelDTO dto) {
        dto.setSupplier(configDistributeService.getCpsPlatformSuppler(dto));
        CpsConfig config = JacksonUtils.parseNonEmpty(JacksonUtils.toJsonWithNonEmpty(dto.getSupplier()), CpsConfig.class);
        HotelLinkGetJdxxxxDTO request = new HotelLinkGetJdxxxxDTO();
        request.setCompid(config.getCompid());
        request.setJdid(dto.getLocalHotelId());
        request.setSupplier(dto.getSupplier());
        try {
            HotelGetJdxxxxResponse response = cpsInvokeRequestService.invokeRequestByConfig(request, CpsHotelServiceEnum.HOTEL_GETJDXXXX, HotelGetJdxxxxResponse.class);
            if (response == null) {
                return null;
            }
            HotelJdb hotel = response.getHotel();
            if (hotel == null) {
                return null;
            }
            HotelVO vo = new HotelVO();
            vo.setZt(hotel.getZt());
            vo.setJdid(hotel.getJdid());
            List<HotelGysdxVO> gysdxVOList = hotel.getGyslist();
            if (ListUtil.isEmpty(gysdxVOList)) {
                return vo;
            }
            List<Mapper> mappers = gysdxVOList.stream().map(gysdxVO -> {
                Mapper mapper = new Mapper();
                mapper.setFybh(gysdxVO.getGysbh());
                mapper.setHotelid(gysdxVO.getHotelid());
                return mapper;
            }).collect(Collectors.toList());
            vo.setMappers(mappers);
            return vo;
        } catch (Exception e) {
            LOGGER.error("查询酒店详情异常:", e);
        }
        return null;
    }

    @Override
    public HotelVO getIHotel(HotelDTO dto) throws SupplyConnectException {
        dto.setSupplier(configDistributeService.getCpsPlatformSuppler(dto));
       CpsConfig config = JacksonUtils.parseNonEmpty(JacksonUtils.toJsonWithNonEmpty(dto.getSupplier()), CpsConfig.class);
        HotelLinkGetJdxxxxDTO request = new HotelLinkGetJdxxxxDTO();
        request.setCompid(config.getCompid());
        request.setJdid(dto.getLocalHotelId());
        request.setSupplier(dto.getSupplier());
        IHotelLinkGetJdxxxxVO response = cpsInvokeRequestService.invokeRequestByConfig(request, CpsHotelServiceEnum.IHOTEL_CDS_DETAIL, IHotelLinkGetJdxxxxVO.class);
        if (response == null) {
            return null;
        }
        IHotelJdb hotel = response.getHotel();
        if (hotel == null) {
            return null;
        }
        HotelVO vo = new HotelVO();
        vo.setZt(hotel.getZt());
        vo.setJdid(hotel.getJdid());
        List<HotelGysdx> gysdxVOList = hotel.getGyslist();
        if (ListUtil.isEmpty(gysdxVOList)) {
            return vo;
        }
        List<Mapper> mappers = gysdxVOList.stream().map(gysdxVO -> {
            Mapper mapper = new Mapper();
            mapper.setFybh(gysdxVO.getGysbh());
            mapper.setHotelid(gysdxVO.getHotelid());
            return mapper;
        }).collect(Collectors.toList());
        vo.setMappers(mappers);
        return vo;

    }
}
