package cn.vetech.center.hotel.link.api;


import cn.vetech.center.hotel.link.api.checkstatus.dto.LinkHotelCheckStatusDTO;
import cn.vetech.center.hotel.link.api.checkstatus.vo.LinkHotelCheckStatusVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供应商检查接口,只传入房房源商编号和商户账号,获取配置后,调用供应商需要授权的接口如酒店详情或价格计划接口
 * 用来检查配置是否正确
 *
 * @author vetech
 */
@RequestMapping("/api/vehotellink/checkStatus")
public interface IHotelLinkCheckStatusService {

    /**
     * 同步协议酒店
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "房源商检查接口")
    @PostMapping(value = "/checkStatus")
    @OpenFeignOperation(value = "checkStatus", title = "房源商检查接口", functionRemark = "房源商检查接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "房源商检查接口")
    RestResponse<LinkHotelCheckStatusVO> checkStatus(@RequestBody LinkHotelCheckStatusDTO dto) throws SystemException;
}
