package cn.vetech.center.hotel.link.client.gys.mapping;

import cn.vetech.center.hotel.link.client.gys.mapping.dto.VeSearchCityDTO;
import cn.vetech.center.hotel.link.client.gys.mapping.dto.VeSearchDictDTO;
import cn.vetech.center.hotel.link.client.gys.mapping.vo.VeJdCityVO;
import cn.vetech.center.hotel.link.client.gys.mapping.vo.VeJdHomeDictVO;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 初始化相关的接口、获取城市、以及各种字段
 * </p>
 *
 * @author wangkai
 * @since 2020/11/9
 */
@FeignClient(name = "mapping-hotel-data")
public interface IHotelInitServiceClient {

    /**
     * 查询供应商城市列表
     * @param searchCityDTO 城市查询DTO
     * @return 返回参数
     */
    @PostMapping("/mapping/hotel/init/listGysCity")
    RestResponse<List<VeJdCityVO>> listGysCity(@RequestBody VeSearchCityDTO searchCityDTO);

    /**
     * 查询供应商数据字典列表
     * @param searchDictDTO 数据字典查询DTO
     * @return 返回参数
     */
    @PostMapping("/mapping/hotel/init/listGysDict")
    RestResponse<List<VeJdHomeDictVO>> listGysDict(@RequestBody VeSearchDictDTO searchDictDTO);
}
