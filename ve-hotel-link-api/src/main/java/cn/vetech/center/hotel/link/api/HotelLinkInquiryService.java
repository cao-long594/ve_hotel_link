package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelDTO;
import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelVO;
import cn.vetech.center.hotel.link.api.inquiry.confirm.HotelLinkInquiryOrderConfirmDTO;
import cn.vetech.center.hotel.link.api.inquiry.confirm.HotelLinkInquiryOrderConfirmVO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateDTO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateVO;
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
@RequestMapping("/api/vehotellink/inquiry")
public interface IHotelLinkInquiryService {

    /**
     * 询价单生成，对应CPS接口 HOTEL_CGscxjd
     *
     * @param dto 入参
     * @return 回参
     * @throws SystemException ex
     */
    @ApiOperation(value = "询价单生成接口")
    @PostMapping(value = "/createInquiryOrder")
    @OpenFeignOperation(value = "createInquiryOrder", title = "询价单生成接口", functionRemark = "询价单生成接口", catalogId = InterfaceCatalogEnum.HOTEL_DDLC_XJD, catalog1 = "询价单")
    RestResponse<HotelLinkInquiryOrderCreateVO> createInquiryOrder(@RequestBody HotelLinkInquiryOrderCreateDTO dto) throws SystemException;


    /**
     * 询价单生成，对应CPS接口 HOTEL_CGscxjd
     *
     * @param dto 入参
     * @return 回参
     * @throws SystemException ex
     */
    @ApiOperation(value = "询价单取消接口")
    @PostMapping(value = "/cancelInquiryOrder")
   @OpenFeignOperation(value = "cancelInquiryOrder", title = "询价单取消接口", functionRemark = "询价单取消接口", catalogId = InterfaceCatalogEnum.HOTEL_DDLC_XJD, catalog1 = "询价单")
    RestResponse<HotelLinkInquiryOrderCancelVO> cancelInquiryOrder(@RequestBody HotelLinkInquiryOrderCancelDTO dto) throws SystemException;

    /**
     * 询价单生成，对应CPS接口 HOTEL_CGscxjd
     *
     * @param dto 入参
     * @return 回参
     * @throws SystemException ex
     */
    @ApiOperation(value = "询价单确认接口")
    @PostMapping(value = "/confirmInquiryOrder")
    @OpenFeignOperation(value = "confirmInquiryOrder", title = "询价单确认接口", functionRemark = "询价单确认接口", catalogId = InterfaceCatalogEnum.HOTEL_DDLC_XJD, catalog1 = "询价单")
    RestResponse<HotelLinkInquiryOrderConfirmVO> confirmInquiryOrder(@RequestBody HotelLinkInquiryOrderConfirmDTO dto) throws SystemException;
}
