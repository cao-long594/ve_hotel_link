package cn.vetech.center.hotel.link.client.gys.cps;

import cn.vetech.center.hotel.link.client.gys.cps.dto.VeLinkHotelConfigDTO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeLinkHotelConfigVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@FeignClient("link-hotel")
public interface ILinkHotelConfigServiceClient  {


    /**
     * @param dto 1
     * @return 1
     */
    @PostMapping("/api/link/config/getConfigs")
    List<VeLinkHotelConfigVO> getConfigs(@RequestBody VeLinkHotelConfigDTO dto);


    /**
     * @param dto 1
     * @return 1
     */
    @PostMapping("/api/link/config/getConfig")
    VeLinkHotelConfigVO getConfig(@RequestBody VeLinkHotelConfigDTO dto);

}
