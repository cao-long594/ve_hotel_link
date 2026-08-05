package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.api.orderpay.dto.LinkHotelOrderPayDTO;
import cn.vetech.center.hotel.link.api.orderpay.vo.LinkHotelOrderPayVO;
import cn.vetech.center.hotel.link.asms.AsmsHotelLinkSupplyService;
import cn.vetech.center.hotel.link.enums.HotelFaceCodeEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookInverseQueryCodeEnum;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.cps.CpsHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.invoice.InvoiceDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ObjectFilterUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.base.feign.invoice.bean.InvoiceInfoBean;
import cn.vetech.charge.base.feign.invoice.vo.InvoiceInfoSearchVO;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
/**
 * @author lipeng
 */
@Service
public class OrderBookDistributeService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBookDistributeService.class);
    /**
     * 订单提交重试次数
     */
    private static final int ORDER_SUBMIT_RETRY_TIMES = 2;
    /**
     *
     */
    @Autowired
    private SupplyDistributeService distributeService;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     *
     */
    @Autowired
    private InvoiceDistributeService invoiceDistributeService;

    /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto) {
        LOGGER.info("下单请求参数：{}", JacksonUtils.toJsonWithDefault(dto));
        configService.setConfig(dto);
        String xdzt = dto.getSupplier().get("xdzt");
        String fybh = dto.getSupplier().get("fybh");
        String zhmc = dto.getSupplier().get("zhmc");
        String fyxsmc = dto.getSupplier().get("fyxsmc");
        String fymc = dto.getSupplier().get("fymc");
        if (StringUtils.isNotBlank(xdzt) && !StringUtils.equals("1", xdzt)) {
            LOGGER.error("下单功能已关闭,fybh:{},zhmc:{},fyxsmc:{}", fybh, zhmc, fyxsmc);
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, "该房源下单功能已关闭");
        }
        String hotelId = dto.getHotelId();
        String ratePlanId = dto.getRatePlanId();
        CommonLogContext.addCommonLogifPresent(String.format("fymc:%s,hotelId:%s,ratePlanId:%s", fymc, hotelId, ratePlanId));
        //过滤入参
        filterDto(dto);
        //设置华住纳税人编号
        setHzwNsrbh(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        LinkHotelOrderBookVO orderBookVO = null;
        try {
            orderBookVO = supplyService.orderBook(dto);
        } catch (Exception e) {
            LOGGER.error("本地订单【{}】下单供应商【{}_{}】异常，请求：【{}】", dto.getLocalOrderId(), fybh, zhmc, dto.toJson(), e);
            orderBookVO = OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10003);
        }
         // 若下单成功，则直接返回
        boolean successFlag = Objects.nonNull(orderBookVO) && StringUtils.isNotBlank(orderBookVO.getOrderId());
        if (successFlag) {
            // 提交订单
            return submitOrder(supplyService, orderBookVO, dto);
        }
        // 明确不锁单的，不进行反查
        if (Objects.nonNull(orderBookVO) && !HotelOrderBookErrorCodeEnum.getIsLockOrder(orderBookVO.getGysErrorCode())) {
            return orderBookVO;
        }
        // 明确不支持反查的，不进行反查
        if (Objects.nonNull(orderBookVO) && HotelOrderBookInverseQueryCodeEnum.NO.getCode().equals(orderBookVO.getInverseQuery())) {
            return orderBookVO;
        }
        // 若本地订单编号为空，则直接返回
        if (StringUtils.isBlank(dto.getLocalOrderId())) {
            return orderBookVO;
        }
        // 下单失败反查订单详
        orderBookVO = checkOrderDetailAfterBookFail(supplyService, dto, orderBookVO);
        String supplerOrderId = Optional.ofNullable(orderBookVO).map(LinkHotelOrderBookVO::getOrderId).orElse(StringUtils.EMPTY);
        CommonLogContext.getCommonLog().ifPresent(commLog -> commLog.setDdbh(supplerOrderId));
        return orderBookVO;
    }

    /**
     * 设置华住纳税人编号
     *
     * @param dto dto
     */
    public void setHzwNsrbh(LinkHotelOrderBookDTO dto) {
        if (!StringUtils.equals(FyEnum.HZW.getFybh(), dto.getFybh())) {
            return;
        }
        InvoiceInfoSearchVO invoiceInfoSearchVO = invoiceDistributeService.invoiceInfoSearch(dto);
        if (invoiceInfoSearchVO == null) {
            return;
        }
        if (ListUtil.isNotEmpty(invoiceInfoSearchVO.getInvoiceInfoBeanList())) {
            InvoiceInfoBean invoiceInfoBean = invoiceInfoSearchVO.getInvoiceInfoBeanList().get(0);
            dto.setNsrsbh(invoiceInfoBean.getNsrsbh());
        }
    }

    /**
     * 下单失败后，根据本地订单号查询订单详
     *
     * @param supplyService  供应商service
     * @param orderBookDTO   下单参数
     * @param oriOrderBookVO 原始下单结果
     * @return LinkHotelOrderBookVO
     */
     private LinkHotelOrderBookVO checkOrderDetailAfterBookFail(IHotelLinkSupplyService supplyService, LinkHotelOrderBookDTO orderBookDTO, LinkHotelOrderBookVO oriOrderBookVO) {
        LinkHotelOrderDetailDTO detailDTO = BeanMapper.map(orderBookDTO, LinkHotelOrderDetailDTO.class);
        if (StringUtils.isBlank(detailDTO.getBookContactEmail())) {
            detailDTO.setBookContactEmail(Objects.nonNull(orderBookDTO.getContact()) ? orderBookDTO.getContact().getEmail() : null);
        }
        String localOrderId = detailDTO.getLocalOrderId();
        LinkHotelOrderDetailVO orderDetailVO;
        try {
            orderDetailVO = supplyService.orderDetail(detailDTO);
            boolean failFlag = Objects.isNull(orderDetailVO)
                    || Objects.isNull(orderDetailVO.getStatus())
                    || orderDetailVO.getStatus() != LinkHotelVO.SUCCESS;
            if (failFlag) {
                LOGGER.warn("下单失败，通过本地订单【{}】反查订单详失败，请求：【{}】，响应：【{}】", localOrderId, detailDTO.toJson(), JacksonUtils.toJsonWithNonEmpty(orderDetailVO));
                return oriOrderBookVO;
            }
        } catch (Exception e) {
            LOGGER.error("下单失败，通过本地订单【{}】反查订单详异常，请求：【{}】", localOrderId, detailDTO.toJson(), e);
            return oriOrderBookVO;
        }

        String orderId = orderDetailVO.getOrderId();
        if (StringUtils.isBlank(orderId)) {
            LOGGER.warn("下单失败，通过本地订单【{}】反查订单详，供应商订单详中订单号为空，请求：【{}】，响应：【{}】", localOrderId, detailDTO.toJson(), orderDetailVO.toJson());
            return oriOrderBookVO;
        }
        LOGGER.info("下单失败，通过本地订单【{}】反查订单详结束，查询成功，供应商订单号：{}，响应：【{}】", localOrderId, orderId, orderDetailVO.toJson());
        if (Objects.isNull(oriOrderBookVO)) {
            oriOrderBookVO = new LinkHotelOrderBookVO();
        }
        oriOrderBookVO.setStatus(LinkHotelVO.SUCCESS);
        oriOrderBookVO.setOrderId(orderId);
          oriOrderBookVO.setGysErrorCode(null);
        oriOrderBookVO.setGysErrorMsg(null);
        oriOrderBookVO.setErrorCode(null);
        oriOrderBookVO.setErrorMsg(null);
        oriOrderBookVO.setErrorMessage(null);
        oriOrderBookVO.setTrueStatus(orderDetailVO.getTrueStatus());
        oriOrderBookVO.setShowStatus(orderDetailVO.getShowStatus());
        return oriOrderBookVO;
    }

     /**
     * 提交订单
     * <br/><strong>注：下单成功的情况下调用</strong>
     *
     * @param hotelLinkSupplyService 供应商service
     * @param orderBookVO            下单结果
     * @param orderBookDTO           下单参数
     * @return LinkHotelOrderBookVO
     */
    private LinkHotelOrderBookVO submitOrder(IHotelLinkSupplyService hotelLinkSupplyService, LinkHotelOrderBookVO orderBookVO, LinkHotelOrderBookDTO orderBookDTO) {
        // 注：目前cps平台下单业务已做相关处理，在此过滤cps平台，后续若删除cps相关处理，改由独立link统一处理，删除此平台判断！！！cps/asms分发平台对应接口内部有相同处理，直接过滤！！！
        if (PtEnum.CPS.getValue().equals(orderBookDTO.getPt())
                || hotelLinkSupplyService instanceof CpsHotelLinkSupplyService
                || hotelLinkSupplyService instanceof AsmsHotelLinkSupplyService
        ) {
            return orderBookVO;
        }

        // 注：订单供应商非携程、游由平台，不走后续处理！！！
        String supplyCode = orderBookDTO.getFybh();
        if (!HotelFaceCodeEnum.XCW.getJkbh().equals(supplyCode)
                && !HotelFaceCodeEnum.YYPT.getJkbh().equals(supplyCode)
                && (!HotelFaceCodeEnum.ALITRIP.getJkbh().equals(supplyCode) || "0".equals(orderBookDTO.getSupplier().get("yfcdms")))) {
            return orderBookVO;
        }

        LinkHotelOrderPayDTO payDto = BeanMapper.map(orderBookDTO, LinkHotelOrderPayDTO.class);
        payDto.setTotalPrice(orderBookVO.getOrderPrice());
        payDto.setOrderId(orderBookVO.getOrderId());
        // todo 日志串联功能
        payDto.setGysxdbj(orderBookVO.getGysxdbj());
        int requestNum = 0;
        try {
            do {
                requestNum++;
                // 提交订单，非真实支付接口
                LinkHotelOrderPayVO orderPayVO = hotelLinkSupplyService.orderPay(payDto);
                if (LinkHotelVO.SUCCESS == orderPayVO.getStatus()) {
                    orderBookVO.setStatus(LinkHotelVO.SUCCESS);
                    return orderBookVO;
                }
            } while (requestNum < ORDER_SUBMIT_RETRY_TIMES);
            orderBookVO.setErrorMsg("订单保存成功，订单提交失败");
        } catch (Exception e) {
            LOGGER.error("订单【{}_{}_{}_{}】提交异常，请求：【{}】", orderBookDTO.getLocalOrderId(), orderBookDTO.getFybh(),
                    orderBookDTO.getHotelId(), orderBookVO.getOrderId(), JacksonUtils.toJsonWithNonEmpty(payDto), e);
            orderBookVO.setErrorMsg("订单保存成功，订单提交异常");
        }
        return orderBookVO;
    }

      /**
     * 根据配置过滤dto中的属性值
     *
     * @param dto 1
     */
    private void filterDto(LinkHotelOrderBookDTO dto) {
        //设置的需要过滤的参数
        Optional<HashSet<String>> filterFieldsOpt = Optional.ofNullable(dto.getSupplier().get("bcgyszd")).map(item -> Sets.newHashSet(item.split("\\/")));
        filterFieldsOpt.ifPresent(item -> {
            if (CollectionUtils.isNotEmpty(item)) {
                ObjectFilterUtils.filterObj(dto, item);
            }
        });
    }
}
