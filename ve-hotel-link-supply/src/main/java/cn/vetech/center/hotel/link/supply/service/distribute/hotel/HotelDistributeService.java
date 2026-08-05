package cn.vetech.center.hotel.link.supply.service.distribute.hotel;

import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.hotel.IHotelService;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.log.annotation.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class HotelDistributeService {

    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(HotelDistributeService.class);
    /**
     *
     */
    @Autowired
    private Map<String, IHotelService> hotelServiceMap;

    /**
     * 获取酒店详情
     *
     * @param dto 1
     * @return 1
     */
    @Log(name = "查询酒店详情")
    public HotelVO getHotel(HotelDTO dto) throws SupplyConnectException {
        String serviceName = dto.getPt() + IHotelService.NAME;
        IHotelService service = hotelServiceMap.get(serviceName);
        HotelVO hotelVO= null;
        if (StringUtils.equals("0", dto.getGngj())) {
            hotelVO= service.getIHotel(dto);
        }else {
            hotelVO = service.getHotel(dto);
        }
        String zt = Optional.ofNullable(hotelVO).map(HotelVO::getZt).orElse(StringUtils.EMPTY);
        if (StringUtils.equals(zt,"4")){
            LOGGER.warn("酒店ID:{}为下架状态",dto.getHotelId());
            return null;
        }
        return hotelVO;
    }
}
