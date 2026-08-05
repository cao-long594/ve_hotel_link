package cn.vetech.center.hotel.link.supply.service.inquiry;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.inquiry.confirm.HotelLinkInquiryOrderConfirmDTO;
import cn.vetech.center.hotel.link.api.inquiry.confirm.HotelLinkInquiryOrderConfirmVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkInquirySupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.InquiryDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaotengyu
 * @since 2022-04-27 18:03
 */
@Service
public class InquiryConfirmService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(InquiryConfirmService.class);

    /**
     * 分发服务
     */
    @Autowired
    private InquiryDistributeService inquiryDistributeService;

    /**
     * 配置服务
     */
    @Autowired
    private HotelConfigDistributeService configService;


    /**
     * 确认询价单
     *
     * @param dto 请求对象
     * @return 返回对象
     */
    public HotelLinkInquiryOrderConfirmVO confirmInquiry(HotelLinkInquiryOrderConfirmDTO dto) {
        configService.setConfig(dto);
        HotelLinkInquiryOrderConfirmVO vo = null;
        try {
            IHotelLinkInquirySupplyService supplyService = inquiryDistributeService.getSupplyService(dto);
            vo = supplyService.confirmInquiry(dto);
        } catch (Exception ex) {
            logger.error("询价单:确认询价单异常:dto：{}", JacksonUtils.toJsonWithDefault(dto), ex);
            vo = new HotelLinkInquiryOrderConfirmVO();
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("异常");
        }
        return vo;
    }

}
