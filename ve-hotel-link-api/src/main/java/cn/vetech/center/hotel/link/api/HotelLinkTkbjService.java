package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.tfxbj.cgscxjd.HotelLinkCgscxjdDTO;
import cn.vetech.center.hotel.link.api.tfxbj.cgscxjd.HotelLinkCgscxjdVO;
import cn.vetech.center.hotel.link.api.tfxbj.cgxjdztxg.HotelLinkCgxjdztxgDTO;
import cn.vetech.center.hotel.link.api.tfxbj.cgxjdztxg.HotelLinkCgxjdztxgVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vetech
 */
@RequestMapping("/api/vehotellink/tfxbj")
public interface IHotelLinkTfxbjService {

    /**
     * 采购询价单状态修改，对应CPS接口 HOTEL_CGxjdztxg
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "采购询价单状态修改，对应CPS接口 HOTEL_CGxjdztxg")
    @PostMapping(value = "/cgxjdztxg")
    @OpenFeignOperation(value = "cgxjdztxg", title = "采购询价单状态修改", functionRemark = "采购询价单状态修改", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "采购询价单状态修改")
    RestResponse<HotelLinkCgxjdztxgVO> cgxjdztxg(@RequestBody HotelLinkCgxjdztxgDTO dto) throws SystemException;
    /**
     * 询价单生成，对应CPS接口 HOTEL_CGscxjd
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "询价单生成接口，对应CPS接口 HOTEL_CGscxjd")
    @PostMapping(value = "/createXjd")
    @OpenFeignOperation(value = "createXjd", title = "询价单生成接口", functionRemark = "询价单生成接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "询价单生成接口")
    RestResponse<HotelLinkCgscxjdVO> createXjd(@RequestBody HotelLinkCgscxjdDTO dto) throws SystemException;
}
