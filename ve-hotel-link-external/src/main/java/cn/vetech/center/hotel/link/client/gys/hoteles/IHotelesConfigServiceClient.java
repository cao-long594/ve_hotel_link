package cn.vetech.center.hotel.link.client.gys.hoteles;


import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author xiaotengyu
 * @since 2022-06-08 17:04
 */
@FeignClient(name = "price-hotel")
public interface IHotelesConfigServiceClient {

    /**
     * @param dto 1
     * @return 1
     */
    @PostMapping("/api/price/hotelesconfig/getConfig")
    RestResponse<HotelesLinkHotelConfigVO> getConfig(@RequestBody HotelesLinkHotelConfigDTO dto);

    /**
     * 获取配置list
     * @param dto  请求参数
     * @return list
     */
    @PostMapping("/api/price/hotelesconfig/getConfigs")
    RestResponse<List<HotelesLinkHotelConfigVO>> getConfigs(@RequestBody HotelesLinkHotelConfigDTO dto);
}
