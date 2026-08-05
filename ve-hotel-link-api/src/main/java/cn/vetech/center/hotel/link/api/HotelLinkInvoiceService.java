package cn.vetech.center.hotel.link.api;


import cn.vetech.center.hotel.link.api.invoice.dto.HotelLinkInvoiceFpsqDTO;
import cn.vetech.center.hotel.link.api.invoice.invoicedetail.dto.HotelLinkInvoiceDetailDTO;
import cn.vetech.center.hotel.link.api.invoice.invoicedetail.vo.HotelLinkInvoiceDetailVO;
import cn.vetech.center.hotel.link.api.invoice.vo.HotelLinkInvoiceFpsqVO;
import cn.vetech.center.hotel.link.api.invoicecancel.dto.HotelLinkInvoiceCancelDTO;
import cn.vetech.center.hotel.link.api.invoicecancel.vo.HotelLinkInvoiceCancelVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/vehotellink/invoice")
public interface IHotelLinkInvoiceService {

    /**
     * 订单详情
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "开票申请")
    @PostMapping(value = "/kpsq")
    @OpenFeignOperation(value = "kpsq", title = "开票申请", functionRemark = "开票申请", catalogId = InterfaceCatalogEnum.HOTEL_FP_KP, catalog1 = "开票")
    RestResponse<HotelLinkInvoiceFpsqVO> kpsq(@RequestBody HotelLinkInvoiceFpsqDTO dto) throws SystemException;

    /**
     * 开票作废
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "开票作废")
    @PostMapping(value = "/invoiceCancel")
    @OpenFeignOperation(value = "invoiceCancel", title = "开票作废", functionRemark = "开票作废", catalogId = InterfaceCatalogEnum.HOTEL_FP_HC, catalog1 = "开票")
    RestResponse<HotelLinkInvoiceCancelVO> invoiceCancel(@RequestBody HotelLinkInvoiceCancelDTO dto) throws SystemException;

    /**
     * 开票详情
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "开票详情")
    @PostMapping(value = "/invoiceDetail")
    @OpenFeignOperation(value = "invoiceDetail", title = "开票详情", functionRemark = "开票详情", catalogId = InterfaceCatalogEnum.HOTEL_FP, catalog1 = "开票详情")
    RestResponse<HotelLinkInvoiceDetailVO> invoiceDetail(@RequestBody HotelLinkInvoiceDetailDTO dto) throws SystemException;
}
