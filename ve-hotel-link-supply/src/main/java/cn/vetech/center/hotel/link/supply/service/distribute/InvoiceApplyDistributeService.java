package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.invoice.dto.HotelLinkInvoiceFpsqDTO;
import cn.vetech.center.hotel.link.api.invoice.invoicedetail.dto.HotelLinkInvoiceDetailDTO;
import cn.vetech.center.hotel.link.api.invoice.invoicedetail.vo.HotelLinkInvoiceDetailVO;
import cn.vetech.center.hotel.link.api.invoice.vo.HotelLinkInvoiceFpsqVO;
import cn.vetech.center.hotel.link.api.invoicecancel.dto.HotelLinkInvoiceCancelDTO;
import cn.vetech.center.hotel.link.api.invoicecancel.vo.HotelLinkInvoiceCancelVO;
import cn.vetech.center.hotel.link.supply.base.kpsq.IHotelLinkInvoiceApplySupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chengwanshan
 * @since 2020/11/6 14:59
 */
@Service
public class InvoiceApplyDistributeService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceApplyDistributeService.class);
    /**
     *
     */
    @Autowired
    private Map<String, IHotelLinkInvoiceApplySupplyService> invoiceApplySupplyServiceMap;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;

    /**
     * @param dto 1
     * @return 1
     */
    public HotelLinkInvoiceFpsqVO invoiceApply(HotelLinkInvoiceFpsqDTO dto) {
        LOGGER.info("开票申请参数：{}", JacksonUtils.toJsonWithNonEmpty(dto));
        HotelLinkInvoiceFpsqVO vo = new HotelLinkInvoiceFpsqVO();
        //获取配置信息
        configService.setConfig(dto);
        String fyen = dto.getSupplier().get("fyen");
        String tcexString tcext = dto.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkInvoiceApplySupplyService.NAME;
        IHotelLinkInvoiceApplySupplyService detailService = invoiceApplySupplyServiceMap.get(serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        return detailService.invoiceApply(dto);
    }

    /**
     * @param dto 1
     * @return 1
     */
    public HotelLinkInvoiceCancelVO invoiceCancel(HotelLinkInvoiceCancelDTO dto) {
        LOGGER.info("发票作废请求参数：{}", JacksonUtils.toJsonWithNonEmpty(dto));
        HotelLinkInvoiceCancelVO vo = new HotelLinkInvoiceCancelVO();
        //获取配置信息
        configService.setConfig(dto);
        String fyen = dto.getSupplier().get("fyen");
        String tcext = dto.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkInvoiceApplySupplyService.NAME;
        IHotelLinkInvoiceApplySupplyService detailService = invoiceApplySupplyServiceMap.get(serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        return detailService.invoiceCancel(dto);
    }
     /**
     * @param dto 1
     * @return 1
     */
    public HotelLinkInvoiceDetailVO invoiceDetail(HotelLinkInvoiceDetailDTO dto) {
        LOGGER.info("发票详情请求参数：{}", JacksonUtils.toJsonWithNonEmpty(dto));
        HotelLinkInvoiceDetailVO vo = new HotelLinkInvoiceDetailVO();
        //获取配置信息
        configService.setConfig(dto);
        String fyen = dto.getSupplier().get("fyen");
        String tcext = dto.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        String serviceName = fyen + IHotelLinkInvoiceApplySupplyService.NAME;
        IHotelLinkInvoiceApplySupplyService detailService = invoiceApplySupplyServiceMap.get(serviceName);
        if (detailService == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到服务信息" + serviceName);
            return vo;
        }
        return detailService.invoiceDetail(dto);
    }
}
