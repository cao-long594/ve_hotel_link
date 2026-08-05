package cn.vetech.center.hotel.link.supply.service.distribute.notice;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.refund.dto.LinkHotelRefundDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.refund.vo.HotelRefundNoticeCheckVO;
import cn.vetech.center.hotel.link.api.notice.refund.vo.HotelRefundNoticeVO;
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
 * @since 2022/7/5 21:18
 */
@Service
public class NoticeRefundDetailDistributeService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeRefundDetailDistributeService.class);
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
     * @param notice 1
     * @return 1
     * @throws SystemException 1
     */
    public HotelRefundNoticeVO getRefundId(LinkHotelNoticeDTO notice) throws SystemException {
        HotelRefundNoticeVO vo = new HotelRefundNoticeVO();
        //通过uri后缀判断哪个供应商, 如：hzw
        String gysptEn = notice.getGysptEnRefund();
        if (StringUtils.isBlank(gysptEn)) {
            LOGGER.error("请检查给供应商登记的通知地址是否符合;/fcnotice/hotel/hotelRefundOrderStatusGysNoticeDispose/*** 其中***代表在我们平台的供应商简称 如hzw,当前通知地址为{}", notice.getUri());
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("当前通知地址为" + notice.getUri() + "不符合/fcnotice/hotel/hotelRefundOrderStatusGysNoticeDispose/***要求,其中***代表在我们平台的供应商简称 如hzw");
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

        // 如果房源商是艺龙，则获取配置
//        if (StringUtils.equalsIgnoreCase(fyen, FyEnum.ELONG.getFyen())) {
//            notice.setZhmc(fyen);
//            configDistributeService.setConfig(notice);
//        }

        String serviceName = fyen + IHotelLinkNoticeSupplyService.NAME;
        IHotelLinkNoticeSupplyService detailService = noticeSupplyServiceMap.get(serviceName);
        LOGGER.info("服务信息：{}", serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        HotelRefundNoticeVO noticeVO = detailService.getRefundId(notice);
        if (StringUtils.isBlank(noticeVO.getVeDdbh())) {
            noticeVO.setVeDdbh(noticeVO.getOrderId());
        }
        noticeVO.setFybh(fyEnum.getFybh());
        return noticeVO;
    }

/**
     * @param detailNoticeDTO 1
     * @return 1
     * @throws SystemException 1
     */
    public HotelRefundNoticeCheckVO getRefundDetail(LinkHotelRefundDetailNoticeDTO detailNoticeDTO) throws SystemException {
        HotelRefundNoticeCheckVO vo = new HotelRefundNoticeCheckVO();
        //获取配置信息
        configDistributeService.setConfig(detailNoticeDTO);
        if (detailNoticeDTO.getNoticeDTO() == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("推送参数对象为空");
            return vo;
        }
        detailNoticeDTO.getNoticeDTO().setSupplier(detailNoticeDTO.getSupplier());
//        //安全校验,    不实现校验接口，就不校验
//        LinkHotelVO validateVO = validateDistributeService.validate(detailNoticeDTO);
//        if (validateVO.getStatus() == LinkHotelVO.FAIL) {
//            vo.setStatus(LinkHotelVO.FAIL);
//            vo.setErrorMsg(validateVO.getErrorMsg());
//            return vo;
//        }
        String fyen = detailNoticeDTO.getSupplier().get("fyen");
        String tcext = detailNoticeDTO.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkNoticeSupplyService.NAME;
        IHotelLinkNoticeSupplyService detailService = noticeSupplyServiceMap.get(serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        return detailService.getRefundDetail(detailNoticeDTO);
    }
}
