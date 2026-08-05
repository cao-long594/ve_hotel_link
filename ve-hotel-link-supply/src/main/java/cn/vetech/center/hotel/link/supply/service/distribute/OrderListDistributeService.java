package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.orderlist.dto.LinkHotelOrderListDTO;
import cn.vetech.center.hotel.link.api.orderlist.vo.LinkHotelOrderListVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengwanshan
 * @since 2021/7/5 19:25
 */
@Service
public class OrderListDistributeService {
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
     * @param dto 1
     * @return 1
     */
    public LinkHotelOrderListVO orderList(LinkHotelOrderListDTO dto) {
        dto.setServiceType("1");
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.orderList(dto);
    }
}
