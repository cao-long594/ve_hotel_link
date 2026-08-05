package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.orderreminder.dto.LinkHotelOrderReminderDTO;
import cn.vetech.center.hotel.link.api.orderreminder.vo.LinkHotelOrderReminderVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class OrderReminderDistributeService {
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
    public LinkHotelOrderReminderVO orderReminder(LinkHotelOrderReminderDTO dto) throws SupplyConnectException {
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.orderReminder(dto);
    }
}
