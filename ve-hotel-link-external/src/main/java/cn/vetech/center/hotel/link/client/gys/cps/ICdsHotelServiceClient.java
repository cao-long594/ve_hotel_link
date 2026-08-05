package cn.vetech.center.hotel.link.client.gys.cps;

import cn.vetech.center.hotel.link.client.gys.cps.dto.VeCdsHotelDetailDTO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeCdsHotelDetailVO;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@FeignClient(name = "cds-hotel-es")
public interface ICdsHotelServiceClient {

    /**
     * 酒店详情
     *
     * @param dto dto
     * @return response
     */
    @PostMapping("/api/cds/hotel/detail")
    RestResponse<VeCdsHotelDetailVO> hotelDetail(@RequestBody VeCdsHotelDetailDTO dto);

}
