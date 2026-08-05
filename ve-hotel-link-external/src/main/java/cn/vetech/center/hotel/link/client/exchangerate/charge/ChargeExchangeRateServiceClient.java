package cn.vetech.center.hotel.link.client.exchangerate.charge;

import cn.vetech.charge.base.feign.currencyrate.dto.GetCurrencyRateCalcDTO;
import cn.vetech.charge.base.feign.currencyrate.vo.GetCurrencyRateCalcVO;
import cn.vetech.charge.cloud.common.ApplicationName;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author xiaotengyu
 * @since 2024-01-03 11:19
 */
@ApiIgnore
@FeignClient(value = ApplicationName.BASE)
public interface ChargeExchangeRateServiceClient{

    /**
     * 费控获取费率
     * @param var1 请求参数
     * @return 返回
     * @throws SystemException 异常
     */
    @PostMapping({"/api/base/currencyrate/getCurrencyRateCalc"})
    RestResponse<GetCurrencyRateCalcVO> getCurrencyRateCalc(@RequestBody GetCurrencyRateCalcDTO var1) throws SystemException;


}
