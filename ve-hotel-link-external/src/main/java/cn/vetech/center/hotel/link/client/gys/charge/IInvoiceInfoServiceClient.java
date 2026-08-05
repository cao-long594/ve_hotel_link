package cn.vetech.center.hotel.link.client.gys.charge;

import cn.vetech.charge.base.feign.invoice.IInvoiceInfoService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * base提供发票所需要的发票抬头等配置信息
 * @author tianjie
 * @since 2019-07-20
 */
@FeignClient(value = "base")
public interface IInvoiceInfoServiceClient extends IInvoiceInfoService {
}
