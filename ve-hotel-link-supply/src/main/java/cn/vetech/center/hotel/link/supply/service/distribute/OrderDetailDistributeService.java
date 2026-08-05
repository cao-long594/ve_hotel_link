package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class OrderDetailDistributeService {
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
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto) throws SupplyConnectException {
        dto.setServiceType("1");
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.orderDetail(dto);
    }
}
