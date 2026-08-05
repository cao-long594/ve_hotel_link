package cn.vetech.center.hotel.link.supply.service.hotelguestreviews;

import cn.vetech.center.hotel.link.api.hotelguestreviews.dto.HotelGuestReviewsDTO;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.SupplyDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author xiaotengyu
 * @since 2022-04-29 13:42
 */
@Service
public class HotelGuestReviewsAsyncService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(HotelGuestReviewsAsyncService.class);

    /**
     * 分发服务
     */
    @Autowired
    private SupplyDistributeService supplyDistributeService;

    /**
     * 异步创建询价单
     *
     * @param dto 请求对象
     * @return 异步返回
     */
    @Async("asyncJgjhServiceExecutor")
    public Future<HotelGuestReviewsVO> hotelGuestReviewsAsync(HotelGuestReviewsDTO dto) {
        HotelGuestReviewsVO vo = null;
        try {
            IHotelLinkSupplyService supplyService = supplyDistributeService.getSupplyService(dto);
            vo = supplyService.hotelGuestReviews(dto);
        } catch (Exception ex) {
            logger.error("调用酒店客人评价接口异常:dto：{}", JacksonUtils.toJsonWithDefault(dto), ex);
        }
        return new AsyncResult<>(vo);
    }


}
