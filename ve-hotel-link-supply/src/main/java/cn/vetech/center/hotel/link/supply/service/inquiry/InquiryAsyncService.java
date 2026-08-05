package cn.vetech.center.hotel.link.supply.service.inquiry;

import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelDTO;
import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelVO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateDTO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkInquirySupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.InquiryDistributeService;
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
public class InquiryAsyncService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(InquiryAsyncService.class);

    /**
     * 分发服务
     */
    @Autowired
    private InquiryDistributeService inquiryDistributeService;

    /**
     * 异步创建询价单
     * @param dto 请求对象
     * @return 异步返回
     */
    @Async("asyncJgjhServiceExecutor")
    public Future<HotelLinkInquiryOrderCreateVO> createInquiryAsync(HotelLinkInquiryOrderCreateDTO dto) {
        HotelLinkInquiryOrderCreateVO vo = null;
        try {
            IHotelLinkInquirySupplyService supplyService = inquiryDistributeService.getSupplyService(dto);
            vo = supplyService.createInquiry(dto);
        } catch (Exception ex) {
            logger.error("询价单:创建询价单异常:dto：{}", JacksonUtils.toJsonWithDefault(dto), ex);
        }
        return new AsyncResult<>(vo);
    }

    /**
     * 异步取消询价单
     * @param dto 取消询价单请求实体
     * @return 异步返回  105816    86135   529080
     */
    public Future<HotelLinkInquiryOrderCancelVO> cancelInquiryAsync(HotelLinkInquiryOrderCancelDTO dto){
        HotelLinkInquiryOrderCancelVO vo = null;
        try {
            IHotelLinkInquirySupplyService supplyService = inquiryDistributeService.getSupplyService(dto);
            vo = supplyService.cancelInquiry(dto);
        } catch (Exception ex) {
            logger.error("询价单:创建询价单异常:dto：{}", JacksonUtils.toJsonWithDefault(dto), ex);
        }
        return new AsyncResult<>(vo);
    }


}
