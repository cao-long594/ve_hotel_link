package cn.vetech.center.hotel.link.client.gys.cps;

import cn.vetech.center.hotel.link.client.gys.cps.dto.RatePlanCollectGzipDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cds-hotel-analysis")
public interface ICpsRatePlanCollectClient {



    @PostMapping("/api/cps/rateCollect")
    void collect(@RequestBody RatePlanCollectGzipDTO gzipDTO);
}
