package cn.vetech.center.hotel.link.client.gys.charge;

import cn.vetech.charge.base.feign.merchant.IMerchantSupplyService;
import cn.vetech.charge.cloud.common.ApplicationName;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lipeng
 */
@ApiIgnore
@FeignClient(value = ApplicationName.BASE, qualifier = "merchantSupplyClient")
@RequestMapping("/api/merchantSupplyClient/client")
public interface IMerchantSupplyServiceClient extends IMerchantSupplyService {
}
