package cn.vetech.center.hotel.link.supply.service.distribute.notice;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.inquiryorder.vo.LinkHotelInquiryOrderFareNoticeVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkNoticeSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.charge.cloud.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chengwanshan
 * @since 2023/7/4 13:36
 */
@Service
public class NoticeInquiryOrderFareDistributeService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeInquiryOrderFareDistributeService.class);
    /**
     *
     */
    @Autowired
    private Map<String, IHotelLinkNoticeSupplyService> noticeSupplyServiceMap;
    /**
     * 安全校验
     */
    @Autowired
    private NoticeValidateDistributeService validateDistributeService;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configDistributeService;

    /**
     * @param notice notice
     * @return LinkHotelInquiryOrderFareNoticeVO
     * @throws SystemException e
     */
    public LinkHotelInquiryOrderFareNoticeVO getInquiryOrderFare(LinkHotelNoticeDTO notice) throws SystemException {
        LinkHotelInquiryOrderFareNoticeVO vo = new LinkHotelInquiryOrderFareNoticeVO();
        //通过uri后缀判断哪个供应商, 如：hzw
        String gysptEn = notice.getGysptEnForInquiryOrderFare();
        if (StringUtils.isBlank(gysptEn)) {
          LOGGER.error("请检查给供应商登记的通知地址是否符合;/fcnotice/hotel/hotelSaveOtherOfferOrderNoticeDispose/*** 其中***代表在我们平台的供应商简称 如hzw,当前通知地址为{}", notice.getUri());
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("当前通知地址为" + notice.getUri() + "不符合/fcnotice/hotel/hotelSaveOtherOfferOrderNoticeDispose/***要求,其中***代表在我们平台的供应商简称 如hzw");
            return vo;
        }
        FyEnum fyEnum = FyEnum.instanceByFyen(gysptEn);
        if (fyEnum == null) {
            LOGGER.error("通知地址获取到的供应商英文编号是{},在系统中没有找到,,当前通知地址为{} ", gysptEn, notice.getUri());
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到房源枚举信息");
            return vo;
        }
        String fyen = fyEnum.getFyen();
        if (fyEnum.isTcext()) {
            fyen = "tcext";
        }

        String serviceName = fyen + IHotelLinkNoticeSupplyService.NAME;
        IHotelLinkNoticeSupplyService detailService = noticeSupplyServiceMap.get(serviceName);
        LOGGER.info("服务信息：{}", serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        LinkHotelInquiryOrderFareNoticeVO noticeVO = detailService.getInquiryOrderFare(notice);
        return noticeVO;
    }
}
