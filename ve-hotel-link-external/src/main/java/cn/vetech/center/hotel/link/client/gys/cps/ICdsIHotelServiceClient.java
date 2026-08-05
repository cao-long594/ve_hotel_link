package cn.vetech.center.hotel.link.client.gys.cps;

import cn.vetech.center.hotel.link.client.gys.cps.dto.VeCdsIHotelDetailDTO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeCdsIHotelDetailVO;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@FeignClient(name = "cds-hotel-es")
public interface ICdsIHotelServiceClient {


    /**
     * 酒店详情
     *
     * @param dto dto
     * @return response
     */
    @PostMapping("/api/cds/ihotel/detail")
    RestResponse<VeCdsIHotelDetailVO> hotelDetail(@RequestBody VeCdsIHotelDetailDTO dto);
}
