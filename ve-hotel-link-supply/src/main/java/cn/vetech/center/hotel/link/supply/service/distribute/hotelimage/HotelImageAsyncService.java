package cn.vetech.center.hotel.link.supply.service.distribute.hotelimage;

import cn.vetech.center.hotel.link.api.hotelimage.dto.HotelImageConvertDTO;
import cn.vetech.center.hotel.link.api.hotelimage.vo.HotelImageConvertVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.SupplyDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author chengwanshan
 * @since 2023/10/7 10:37
 */
@Service
public class HotelImageAsyncService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelImageAsyncService.class);
    /**
     *
     */
    @Autowired
    private SupplyDistributeService supplyDistributeService;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configDistributeService;

    @Async("asyncJgjhServiceExecutor")
    public Future<HotelImageConvertVO> hotelImageAsync(HotelImageConvertDTO dto) {
//        configDistributeService.setConfig(dto);
        IHotelLinkSupplyService supplyService = supplyDistributeService.getSupplyService(dto);
        HotelImageConvertVO vo = supplyService.hotelImageConvert(dto);
        //异步返回
        return new AsyncResult<>(vo);
    }
}
