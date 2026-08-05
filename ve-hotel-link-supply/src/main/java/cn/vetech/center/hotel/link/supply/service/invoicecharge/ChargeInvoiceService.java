package cn.vetech.center.hotel.link.supply.service.invoice.charge;

import cn.vetech.center.hotel.link.client.gys.charge.IInvoiceInfoServiceClient;
import cn.vetech.center.hotel.link.supply.base.invoice.IInvoiceService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.base.feign.invoice.dto.InvoiceInfoSearchDTO;
import cn.vetech.charge.base.feign.invoice.vo.InvoiceInfoSearchVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vetech
 */
@Service
public class ChargeInvoiceService implements IInvoiceService {
    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ChargeInvoiceService.class);
    /**
     *
     */
    @Autowired
    private IInvoiceInfoServiceClient invoiceInfoServiceClient;

    @Log(name = "费控查询发票服务", logParam = true, logReturn = true)
    @Override
    public InvoiceInfoSearchVO invoiceInfoSearch(InvoiceInfoSearchDTO dto) {
        try {
            logger.info("费控查询发票服务入参：{}", JacksonUtils.toJsonWithNonEmpty(dto));
            RestResponse<InvoiceInfoSearchVO> restResponse = invoiceInfoServiceClient.invoiceInfoSearch(dto);
            logger.info("费控查询发票服务出参：{}", JacksonUtils.toJsonWithNonEmpty(restResponse));
            return restResponse.getResult();
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }
}
