package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.vetech.center.hotel.link.api.LinkHotelVO.FAIL;

/**
 * @author lipeng
 */
@Service
public class OrderCancelDistributeService {
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
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto) throws SupplyConnectException {
        dto.setServiceType("1");
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        LinkHotelOrderCancelVO orderCancelVO = supplyService.orderCancel(dto);
        int successs = orderCancelVO.getSuccesss();
        Integer status = orderCancelVO.getStatus();
        if (FAIL == successs || FAIL == status) {
            orderCancelVO.setSuccesss(FAIL);
            orderCancelVO.setStatus(FAIL);
        }
        return orderCancelVO;
    }

}
