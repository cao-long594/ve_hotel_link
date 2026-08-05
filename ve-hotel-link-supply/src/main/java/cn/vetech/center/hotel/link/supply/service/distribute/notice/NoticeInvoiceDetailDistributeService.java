package cn.vetech.center.hotel.link.supply.service.distribute.notice;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.noticeinvoice.dto.LinkHotelInvoiceDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.noticeinvoice.vo.HotelInvoiceNoticeCheckVO;
import cn.vetech.center.hotel.link.api.noticeinvoice.vo.HotelInvoiceNoticeVO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkNoticeSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发票信息通知
 * @author chengwanshan
 * @since 2020/11/16 11:01
 */
@Service
public class NoticeInvoiceDetailDistributeService {
    /**
     *
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(NoticeInvoiceDetailDistributeService.class);
    /**
     *
     */
    @Autowired
    private Map<String, IHotelLinkNoticeSupplyService> noticeSupplyServiceMap;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configDistributeService;

    /**
     *
     * @param notice 1
     * @return 1
     * @throws SystemException 1
     */
    public HotelInvoiceNoticeVO getOrderIdInvoice(LinkHotelNoticeDTO notice) throws SystemException{
        HotelInvoiceNoticeVO vo = new HotelInvoiceNoticeVO();
        //通过uri后缀判断哪个供应商, 如：hzw
        String gysptEn = notice.getGysptEnInvoice();
        if (StringUtils.isBlank(gysptEn)) {
 LOGGER.error("请检查给供应商登记的通知地址是否符合;fcnotice/hotel/hotelOrderInvoiceUrlPushNoticeDispose/*** 其中***代表在我们平台的供应商简称 如hzw,当前通知地址为{}", notice.getUri());
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("当前通知地址为" + notice.getUri() + "不符合fcnotice/hotel/hotelOrderInvoiceUrlPushNoticeDispose/***要求,其中***代表在我们平台的供应商简称 如hzw");
            return vo;
        }
        FyEnum fyEnum = FyEnum.instanceByFyen(gysptEn);
        if(fyEnum == null){
            LOGGER.error("通知地址获取到的供应商英文编号是{},在系统中没有找到,,当前通知地址为{} ", gysptEn, notice.getUri());
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到房源枚举信息");
            return vo;
        }
        String fyen = fyEnum.getFyen();
        if(fyEnum.isTcext()){
            fyen="tcext";
        }
        String serviceName = fyen + IHotelLinkNoticeSupplyService.NAME;
        IHotelLinkNoticeSupplyService detailService = noticeSupplyServiceMap.get(serviceName);
        LOGGER.info("服务信息：{}", serviceName);
        if(detailService==null){
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息"+serviceName);
            return vo;
        }
        HotelInvoiceNoticeVO orderIdInvoice = detailService.getOrderIdInvoice(notice);
        if (StringUtils.isBlank(orderIdInvoice.getVeDdbh())) {
            orderIdInvoice.setVeDdbh(orderIdInvoice.getOrderId());
        }
        orderIdInvoice.setFybh(fyEnum.getFybh());
        LOGGER.info("返回给hotel的出参：{}", JacksonUtils.toJsonWithNonEmpty(orderIdInvoice));
        return orderIdInvoice;
    }
 /**
     *
     * @param detailNoticeDTO 1
     * @return 1
     * @throws SystemException 1
     */
    public HotelInvoiceNoticeCheckVO getInvoiceDetail(LinkHotelInvoiceDetailNoticeDTO detailNoticeDTO) throws SystemException{
        HotelInvoiceNoticeCheckVO vo = new HotelInvoiceNoticeCheckVO();
        if (detailNoticeDTO == null || detailNoticeDTO.getNoticeDTO() == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("推送参数对象为空");
            return vo;
        }
        //获取配置信息
        configDistributeService.setConfig(detailNoticeDTO);
        detailNoticeDTO.getNoticeDTO().setSupplier(detailNoticeDTO.getSupplier());
        String fyen = detailNoticeDTO.getSupplier().get("fyen");
        String tcext = detailNoticeDTO.getSupplier().get("tcext");
        if(StringUtils.isNotBlank(tcext)){
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkNoticeSupplyService.NAME;
        IHotelLinkNoticeSupplyService detailService = noticeSupplyServiceMap.get(serviceName);
        if(detailService == null){
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息"+serviceName);
            return vo;
        }
        HotelInvoiceNoticeCheckVO checkVO = detailService.getInvoiceDetail(detailNoticeDTO);
        if (StringUtils.isBlank(checkVO.getVeDdbh())) {
            checkVO.setVeDdbh(checkVO.getOrderId());
        }
        return checkVO;
    }
}
