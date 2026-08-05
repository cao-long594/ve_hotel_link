package cn.vetech.center.hotel.link.client.gys.charge;

import cn.vetech.charge.base.feign.merchant.IMerchantService;
import cn.vetech.charge.cloud.common.ApplicationName;
import org.springframework.cloud.netflix.feign.FeignClient;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lipeng
 */
@ApiIgnore
@FeignClient(value = ApplicationName.BASE, qualifier = "merchantServiceClient")
public interface IMerchantServiceClient extends IMerchantService {
}
