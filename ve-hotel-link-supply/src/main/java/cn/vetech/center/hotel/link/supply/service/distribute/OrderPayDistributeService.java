package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.orderpay.dto.LinkHotelOrderPayDTO;
import cn.vetech.center.hotel.link.api.orderpay.vo.LinkHotelOrderPayVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class OrderPayDistributeService {
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     *
     */
    @Autowired
    private SupplyDistributeService distributeService;

    /**
     *
     * @param dto 1
     * @return 1
     */
    public LinkHotelOrderPayVO orderPay(LinkHotelOrderPayDTO dto){
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService=distributeService.getSupplyService(dto);
        return supplyService.orderPay(dto);
    }
}
