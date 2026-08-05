package cn.vetech.center.hotel.link.client.exchangerate.cps;

import cn.vetech.center.hotel.link.client.exchangerate.cps.vo.VeZgyhhlVO;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author xiaotengyu
 * @since 2024-01-03 11:19
 */
@ApiIgnore
@FeignClient("finance")
public interface CpsExchangeRateServiceClient {


    /**
     * 获取中国银行汇率
     *
     * @param currency 币种
     * @param date     日期
     * @return 汇率集合
     */
    @RequestMapping("/api/finance/zgyhhl/getZghyhl")
    RestResponse<List<VeZgyhhlVO>> getZghyhl(@RequestParam("currency") String currency, @RequestParam("date") String date);
}
