package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.guarantee.dto.LinkHotelGuaranteeDTO;
import cn.vetech.center.hotel.link.api.guarantee.vo.LinkHotelGuaranteeVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class GuaranteeDistributeService {
    /**
     *
     */
    @Autowired
    private SupplyDistributeService distributeService;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;

    public LinkHotelGuaranteeVO guarantee(LinkHotelGuaranteeDTO dto) throws SupplyConnectException {
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService=distributeService.getSupplyService(dto);
        return supplyService.guarantee(dto);
    }
}
