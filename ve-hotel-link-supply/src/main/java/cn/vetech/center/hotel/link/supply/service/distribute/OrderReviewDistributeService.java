package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.orderreview.dto.LinkHotelOrderReviewDTO;
import cn.vetech.center.hotel.link.api.orderreview.vo.LinkHotelOrderReviewVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengwanshan
 * @since 2026/7/28 11:33
 */
@Service
public class OrderReviewDistributeService {
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
    public LinkHotelOrderReviewVO orderReview(LinkHotelOrderReviewDTO dto) throws SupplyConnectException {
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.orderReview(dto);
    }
}
