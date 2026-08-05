package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.ddtk.dto.LinkHotelDdtkDTO;
import cn.vetech.center.hotel.link.api.ddtk.vo.LinkHotelDdtkVO;
import cn.vetech.center.hotel.link.api.ddvalidate.dto.LinkHotelDdValidateDTO;
import cn.vetech.center.hotel.link.api.ddvalidate.vo.LinkHotelDdValidateVO;
import cn.vetech.center.hotel.link.api.guarantee.dto.LinkHotelGuaranteeDTO;
import cn.vetech.center.hotel.link.api.guarantee.vo.LinkHotelGuaranteeVO;
import cn.vetech.center.hotel.link.api.hotelbuyerbillpush.HotelBuyerBillPushDTO;
import cn.vetech.center.hotel.link.api.hotelbuyerbillpush.HotelBuyerBillPushVO;
import cn.vetech.center.hotel.link.api.hotelguestreviews.dto.HotelGuestReviewsDTO;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsVO;
import cn.vetech.center.hotel.link.api.hotelimage.dto.HotelImageConvertDTO;
import cn.vetech.center.hotel.link.api.hotelimage.vo.HotelImageConvertVO;
import cn.vetech.center.hotel.link.api.member.dto.LinkHotelRegistrationDTO;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.api.orderlist.dto.LinkHotelOrderListDTO;
import cn.vetech.center.hotel.link.api.orderlist.vo.LinkHotelOrderListVO;
import cn.vetech.center.hotel.link.api.orderpay.dto.LinkHotelOrderPayDTO;
import cn.vetech.center.hotel.link.api.orderpay.vo.LinkHotelOrderPayVO;
import cn.vetech.center.hotel.link.api.orderreminder.dto.LinkHotelOrderReminderDTO;
import cn.vetech.center.hotel.link.api.orderreminder.vo.LinkHotelOrderReminderVO;
import cn.vetech.center.hotel.link.api.orderreview.dto.LinkHotelOrderReviewDTO;
import cn.vetech.center.hotel.link.api.orderreview.vo.LinkHotelOrderReviewVO;
import cn.vetech.center.hotel.link.api.paydk.dto.LinkHotelPayDkDTO;
import cn.vetech.center.hotel.link.api.paydk.vo.LinkHotelPayDkVO;
import cn.vetech.center.hotel.link.api.paydkmix.dto.LinkHotelPayWithholdingMixDTO;
import cn.vetech.center.hotel.link.api.paydkmix.vo.LinkHotelPayWithholdingMixVO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.tdsq.dto.LinkHotelCgtdsqDTO;
import cn.vetech.center.hotel.link.api.tdsq.vo.LinkHotelCgtdsqVO;
import cn.vetech.center.hotel.link.api.tdxx.dto.LinkHotelCgtdxxDTO;
import cn.vetech.center.hotel.link.api.tdxx.vo.LinkHotelCgtdxxVO;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.api.validategn.dto.LinkHotelValidateGnDTO;
import cn.vetech.center.hotel.link.api.validategn.vo.LinkHotelValidateGnVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 2020-07-27
 */
@RequestMapping("/api/vehotellink")
public interface IHotelLinkService {
    /**
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "查询报价 费控使用在link中并发请求")
    @PostMapping(value = "/rateSearch")
    @OpenFeignOperation(value = "rateSearch", title = "费控使用在link中并发请求", functionRemark = "费控使用在link中并发请求", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDBJ, catalog1 = "费控使用在link中并发请求")
    RestResponse<LinkHotelRateSearchVO> rateSearch(@RequestBody LinkHotelRateSearchDTO dto);

    /**
     * asms、cps使用 别的别用
     *
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "查询报价")
    @PostMapping(value = "/rateSearchSingle")
    @OpenFeignOperation(value = "rateSearchSingle", title = "查询报价", functionRemark = "查询报价", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDBJ, catalog1 = "查询报价")
    RestResponse<LinkHotelRateSearchVO> rateSearchSingle(@RequestBody LinkHotelRateSearchDTO dto);

    /**
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "担保校验")
    @PostMapping("/guarantee")
    @OpenFeignOperation(value = "guarantee", title = "担保校验", functionRemark = "担保校验", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JGJY, catalog1 = "担保校验")
    RestResponse<LinkHotelGuaranteeVO> guarantee(@RequestBody LinkHotelGuaranteeDTO dto);

    /**
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "下单前校验")
    @PostMapping("/validate")
    @OpenFeignOperation(value = "validate", title = "下单前校验", functionRemark = "下单前校验", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JGJY, catalog1 = "下单前校验")
    RestResponse<LinkHotelValidateVO> validate(@RequestBody LinkHotelValidateDTO dto);

    /**
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "下单前校验")
    @PostMapping("/validateGn")
    @OpenFeignOperation(value = "validateGn", title = "下单前校验", functionRemark = "下单前校验", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JGJY, catalog1 = "下单前校验")
    RestResponse<LinkHotelValidateGnVO> validateGn(@RequestBody LinkHotelValidateGnDTO dto);

    /**
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "下单")
    @PostMapping(value = "/orderBook")
    @OpenFeignOperation(value = "orderBook", title = "下单", functionRemark = "下单", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_TPFTJP, catalog1 = "下单")
    RestResponse<LinkHotelOrderBookVO> orderBook(@RequestBody LinkHotelOrderBookDTO dto);

    /**
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "支付")
    @PostMapping("/orderPay")
    @OpenFeignOperation(value = "orderPay", title = "支付", functionRemark = "支付", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "支付")
    RestResponse<LinkHotelOrderPayVO> orderPay(@RequestBody LinkHotelOrderPayDTO dto);

    /**
     * 订单详情
     *
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "订单详情")
    @PostMapping("/orderDetail")
    @OpenFeignOperation(value = "orderDetail", title = "同步协议酒店", functionRemark = "订单详情", catalogId = InterfaceCatalogEnum.HOTEL_DDLBXQ_ZCD, catalog1 = "订单详情")
    RestResponse<LinkHotelOrderDetailVO> orderDetail(@RequestBody LinkHotelOrderDetailDTO dto);

    /**
     * 取消订单
     *
     * @param dto dto
     * @return response
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("/orderCancel")
    @OpenFeignOperation(value = "orderCancel" , title = "取消订单", functionRemark = "取消订单", catalogId = InterfaceCatalogEnum.HOTEL_DDLC_TD, catalog1 = "取消订单")
    RestResponse<LinkHotelOrderCancelVO> orderCancel(@RequestBody LinkHotelOrderCancelDTO dto);

    /**
     * 退单申请
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "退单申请")
    @PostMapping(value = "/hotelTdsq")
    @OpenFeignOperation(value = "hotelTdsq", title = "退单申请", functionRemark = "退单申请", catalogId = InterfaceCatalogEnum.HOTEL_DDLC_TD, catalog1 = "退单申请")
    RestResponse<LinkHotelCgtdsqVO> hotelTdsq(@RequestBody LinkHotelCgtdsqDTO dto);

    /**
     * 退单详细
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "退单详细")
    @PostMapping(value = "/hotelTdxx")
    @OpenFeignOperation(value = "hotelTdxx", title = "退单详细", functionRemark = "退单详细", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "HOTEL_DDLBXQ_TD")
    RestResponse<LinkHotelCgtdxxVO> hotelTdxx(@RequestBody LinkHotelCgtdxxDTO dto);

    /**
     * cps代扣
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "cps代扣")
    @PostMapping(value = "/hotelPayDk")
    @OpenFeignOperation(value = "hotelPayDk", title = "cps代扣", functionRemark = "cps代扣", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "cps代扣")
    RestResponse<LinkHotelPayDkVO> hotelPayDk(@RequestBody LinkHotelPayDkDTO dto);

    /**
     * cps代扣混合
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "cps代扣混合")
    @PostMapping(value = "/hotelPayDkMix")
    @OpenFeignOperation(value = "hotelPayDkMix", title = "cps代扣混合", functionRemark = "cps代扣混合", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "cps代扣混合")
    RestResponse<LinkHotelPayWithholdingMixVO> hotelPayDkMix(@RequestBody LinkHotelPayWithholdingMixDTO dto);

    /**
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "酒店订单退款通知")
    @PostMapping(value = "/hotelDdtk")
    @OpenFeignOperation(value = "hotelDdtk", title = "酒店订单退款通知", functionRemark = "酒店订单退款通知", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "酒店订单退款通知")
    RestResponse<LinkHotelDdtkVO> hotelDdtk(@RequestBody LinkHotelDdtkDTO dto);

    /**
     * 对账
     *
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "对账")
    @PostMapping(value = "/orderList")
    @OpenFeignOperation(value = "orderList", title = "对账", functionRemark = "对账", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "对账")
    RestResponse<LinkHotelOrderListVO> orderList(@RequestBody LinkHotelOrderListDTO dto);

    /**
     * 订单校验
     *
     * @param dto 1
     * @return 1
     */
    @ApiOperation(value = "订单校验")
    @PostMapping(value = "/dValidate")
    @OpenFeignOperation(value = "dValidate", title = "订单校验", functionRemark = "订单校验", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "订单校验")
    RestResponse<LinkHotelDdValidateVO> ddValidate(@RequestBody LinkHotelDdValidateDTO dto);

    /**
     * 会员注册
     *
     * @param dto 请求参数
     * @return LinkHotelRegistrationVO
     */
    @ApiOperation(value = "员工注册绑定")
    @PostMapping(value = "/registration")
    @OpenFeignOperation(value = "registration", title = "员工注册绑定", functionRemark = "员工注册绑定", catalogId = InterfaceCatalogEnum.HOTEL_QT_HYBD, catalog1 = "员工注册绑定")
    RestResponse<LinkHotelRegistrationVO> registration(@RequestBody LinkHotelRegistrationDTO dto);


    /**
     * 酒店采购商账单明细推送服务
     *
     * @param dto 请求对象性
     * @return response
     * @throws SystemException exec
     */
    @ApiOperation(value = "酒店采购商账单明细推送服务")
    @PostMapping(value = "/hotelBuyerBillPush")
    @OpenFeignOperation(value = "hotelBuyerBillPush", title = "酒店采购商账单明细推送服务", functionRemark = "酒店采购商账单明细推送服务", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "酒店采购商账单明细推送服务")
    RestResponse<HotelBuyerBillPushVO> hotelBuyerBillPush(@RequestBody HotelBuyerBillPushDTO dto);

    /**
     * 酒店图片转换
     *
     * @param dto 请求参数
     * @return RestResponse
     */
    @ApiOperation(value = "酒店图片转换")
    @PostMapping(value = "/hotelImageConvert")
    @OpenFeignOperation(value = "hotelImageConvert", title = "酒店图片转换", functionRemark = "酒店图片转换", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "酒店图片转换")
    RestResponse<HotelImageConvertVO> hotelImageConvert(@RequestBody HotelImageConvertDTO dto);

    /**
     * 催单
     *
     * @param dto 请求参数
     * @return RestResponse
     */
    @ApiOperation(value = "酒店订单催单")
    @PostMapping(value = "/orderReminder")
    @OpenFeignOperation(value = "orderReminder", title = "酒店订单催单", functionRemark = "酒店订单催单", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "酒店订单催单")
    RestResponse<LinkHotelOrderReminderVO> orderReminder(@RequestBody LinkHotelOrderReminderDTO dto);

    /**
     * 酒店客人评价
     *
     * @param dto 请求参数
     * @return RestResponse
     */
    @ApiOperation(value = "酒店客人评价")
    @PostMapping(value = "/hotelGuestReviews")
    @OpenFeignOperation(value = "hotelGuestReviews", title = "酒店客人评价", functionRemark = "酒店客人评价", catalogId = InterfaceCatalogEnum.HOTEL_QT_PJ, catalog1 = "酒店客人评价")
    RestResponse<HotelGuestReviewsVO> hotelGuestReviews(@RequestBody HotelGuestReviewsDTO dto);

    /**
     * 酒店订单离店审核接口
     *
     * @param dto 请求参数
     * @return RestResponse
     */
    @ApiOperation(value = "酒店订单离店审核接口")
    @PostMapping(value = "/orderReview")
    @OpenFeignOperation(value = "orderReview", title = "酒店订单离店审核接口", functionRemark = "酒店订单离店审核接口", catalogId = InterfaceCatalogEnum.HOTEL_DDLC, catalog1 = "酒店订单催单")
    RestResponse<LinkHotelOrderReviewVO> orderReview(@RequestBody LinkHotelOrderReviewDTO dto);
}