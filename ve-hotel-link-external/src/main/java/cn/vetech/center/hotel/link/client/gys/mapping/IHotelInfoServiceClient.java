package cn.vetech.center.hotel.link.client.gys.mapping;

import cn.vetech.center.hotel.link.client.gys.mapping.dto.VeRoomSearchDTO;
import cn.vetech.center.hotel.link.client.gys.mapping.vo.VeHotelRoomVO;
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
public interface IHotelInfoServiceClient  {


    /**
     * 获取房型信息
     *
     * @param roomSearchDTO 查询参数
     * @return RestResponse<List < HotelRoomVO>>
     */
    @PostMapping("/mapping/hotel/info/getRoomList")
    RestResponse<List<VeHotelRoomVO>> getRoomList(@RequestBody VeRoomSearchDTO roomSearchDTO);
}
