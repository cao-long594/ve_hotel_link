package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.ddtk.dto.LinkHotelDdtkDTO;
import cn.vetech.center.hotel.link.api.ddtk.vo.LinkHotelDdtkVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class DdtkDistributeService {
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

    /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelDdtkVO ddtk(LinkHotelDdtkDTO dto) {
        dto.setServiceType("1");
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.hotelDdtk(dto);
    }
}
