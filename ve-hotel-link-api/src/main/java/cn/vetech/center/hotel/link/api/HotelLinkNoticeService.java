package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.notice.HotelNoticeCheckVO;
import cn.vetech.center.hotel.link.api.notice.HotelNoticeVO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelOrderDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.chargeback.vo.LinkHotelChargebackDetailNoticeVO;
import cn.vetech.center.hotel.link.api.notice.inquiryorder.vo.LinkHotelInquiryOrderFareNoticeVO;
import cn.vetech.center.hotel.link.api.notice.refund.dto.LinkHotelRefundDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.refund.vo.HotelRefundNoticeCheckVO;
import cn.vetech.center.hotel.link.api.notice.refund.vo.HotelRefundNoticeVO;
import cn.vetech.center.hotel.link.api.noticeinvoice.dto.LinkHotelInvoiceDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.noticeinvoice.vo.HotelInvoiceNoticeCheckVO;
import cn.vetech.center.hotel.link.api.noticeinvoice.vo.HotelInvoiceNoticeVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 外部通知接口
 *
 * @author vetech
 */
@RequestMapping("/api/vehotellink/notice")
public interface IHotelLinkNoticeService {
    /**
     * 根据供应商发票通知解析出订单编号，  提供给hotel查询本地订单信息
     *
     * @param notice 1
     * @return 1
     * @throws SystemException 1
     */
    @ApiOperation(value = "订单详通知-getOrderId")
    @PostMapping(value = "/getOrderId")
     @OpenFeignOperation(value = "getOrderId", title = "订单详通知", functionRemark = "订单详通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "订单详通知")
    RestResponse<HotelNoticeVO> getOrderId(@RequestBody LinkHotelNoticeDTO notice) throws SystemException;

    /**
     * 根据通知数据 以及 本地订单数据  转换订单信息（主要是订单状态）
     *
     * @param detailNoticeDTO 1
     * @return 1
     * @throws SystemException 1
     */
    @ApiOperation(value = "订单详通知-getOrderDetail")
    @PostMapping(value = "/getOrderDetail")
     @OpenFeignOperation(value = "getOrderDetail", title = "订单详通知", functionRemark = "订单详通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "订单详通知")
    RestResponse<HotelNoticeCheckVO> getOrderDetail(@RequestBody LinkHotelOrderDetailNoticeDTO detailNoticeDTO) throws SystemException;

    /**
     * 根据供应商发票通知解析出订单编号，  提供给hotel查询本地订单信息
     *
     * @param notice 1
     * @return 1
     * @throws SystemException 1
     */
    @ApiOperation(value = "发票通知-getOrderIdInvoice")
    @PostMapping(value = "/getOrderIdInvoice")
     @OpenFeignOperation(value = "getOrderIdInvoice", title = "发票通知", functionRemark = "发票通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "发票通知")
    RestResponse<HotelInvoiceNoticeVO> getOrderIdInvoice(@RequestBody LinkHotelNoticeDTO notice) throws SystemException;

    /**
     * 根据供应商发票通知解析出订单编号，  提供给hotel查询本地订单信息
     *
     * @param invoiceDetailNoticeDTO 1
     * @return 1
     * @throws SystemException 1
     */
    @ApiOperation(value = "发票通知-getInvoiceDetail")
    @PostMapping(value = "/getInvoiceDetail")
     @OpenFeignOperation(value = "getInvoiceDetail", title = "发票通知", functionRemark = "发票通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "发票通知")
    RestResponse<HotelInvoiceNoticeCheckVO> getInvoiceDetail(@RequestBody LinkHotelInvoiceDetailNoticeDTO invoiceDetailNoticeDTO) throws SystemException;

    /**
     * 根据供应商退单通知解析出订单编号，  提供给hotel查询本地订单信息
     *
     * @param notice notice
     * @return RestResponse
     * @throws SystemException e
     */
    @ApiOperation(value = "退单详通知-getRefundId")
    @PostMapping(value = "/getRefundId")
     @OpenFeignOperation(value = "getRefundId", title = "退单详通知", functionRemark = "退单详通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "退单详通知")
    RestResponse<HotelRefundNoticeVO> getRefundId(@RequestBody LinkHotelNoticeDTO notice) throws SystemException;

    /**
     * 根据通知数据 以及 本地订单数据  转换退单信息
     *
     * @param detailNoticeDTO detailNoticeDTO
     * @return RestResponse
     * @throws SystemException e
     */
    @ApiOperation(value = "退单详通知-getRefundDetail")
    @PostMapping(value = "/getRefundDetail")
     @OpenFeignOperation(value = "getRefundDetail", title = "退单详通知", functionRemark = "退单详通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "退单详通知")
    RestResponse<HotelRefundNoticeCheckVO> getRefundDetail(@RequestBody LinkHotelRefundDetailNoticeDTO detailNoticeDTO) throws SystemException;

    /**
     * @param notice notice
     * @return RestResponse
     * @throws SystemException e
     */
    @ApiOperation(value = "酒店退单补退通知-getChargebackDetail")
    @PostMapping(value = "/getChargebackDetail")
     @OpenFeignOperation(value = "getChargebackDetail", title = "酒店退单补退通知", functionRemark = "酒店退单补退通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "酒店退单补退通知")
    RestResponse<LinkHotelChargebackDetailNoticeVO> getChargebackDetail(@RequestBody LinkHotelNoticeDTO notice) throws SystemException;

    /**
     * @param notice notice
     * @return RestResponse
     * @throws SystemException e
     */
    @ApiOperation(value = "酒店报价单通知-getInquiryOrderFare")
    @PostMapping(value = "/getInquiryOrderFare")
     @OpenFeignOperation(value = "getInquiryOrderFare", title = "酒店报价单通知", functionRemark = "酒店报价单通知", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "酒店报价单通知")
    RestResponse<LinkHotelInquiryOrderFareNoticeVO> getInquiryOrderFare(@RequestBody LinkHotelNoticeDTO notice) throws SystemException;
}