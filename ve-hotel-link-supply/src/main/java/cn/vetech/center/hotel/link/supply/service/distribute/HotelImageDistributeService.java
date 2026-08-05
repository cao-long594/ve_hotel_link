package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chengwanshan
 * @since 2023/9/28 14:59
 */
@Service
public class HotelImageDistributeService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelImageDistributeService.class);
    /**
     *
     */
    @Autowired
    private Map<String, IHotelLinkSupplyService> supplyServiceMap;

    /**
     * @param dto 1
     * @return 1
     */
    public boolean exists(LinkHotelDTO dto) {
        IHotelLinkSupplyService supplyService = get(dto);
        if (supplyService == null) {
            LOGGER.error("fybh:{},serviceName:{}无房源实现", dto.getFybh(), getServiceName(dto));
            return false;
        }
        return true;
    }

    /**
     * @param dto 1
     * @return 1
     */
    private IHotelLinkSupplyService get(LinkHotelDTO dto) {
        String serviceName = getServiceName(dto);
        IHotelLinkSupplyService supplyService = supplyServiceMap.get(serviceName);
        return supplyService;
    }

    /**
     * @param dto 1
     * @return 1
     */
    private String getServiceName(LinkHotelDTO dto) {
        String fyen = dto.getSupplier().get("fyen");
        String tcext = dto.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkSupplyService.NAME;
        return serviceName;
    }

}
