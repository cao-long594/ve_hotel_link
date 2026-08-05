package cn.vetech.center.hotel.link.elong.orderdetail;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FreeMealEnum;
import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.api.enums.HotelOrderStatusEnum;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHotelGeneralEnum;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.orderdetail.request.ElongOrderDetailRequest;
import cn.vetech.center.hotel.link.elong.orderdetail.response.Customer;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongCreditCard;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetail;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetailResponse;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderHotel;
import cn.vetech.center.hotel.link.elong.orderdetail.response.OrderRoom;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchDayMeal;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailHandler;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author gaojin
 */
@Service
public class ElongOrderDetailService extends ElongHttp implements ElongService {
    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ElongOrderDetailService.class);
    /**
     * 艺龙订单详情接口名
     */
    private final String method = "hotel.order.detail";
    /**
     * 艺龙订单详情使用https
     */
    private final String http = "https";

    @Override
    public ElongResponse execute(ElongRequest req) {
        ElongOrderDetail result = new ElongOrderDetail();
        ElongOrderDetailResponse res = new ElongOrderDetailResponse();
        if (req == null) {
            logger.error("艺龙订单详情，请求参数对象为null");
            result.setErrorMsg(HotelErrorCodeEnum.ReqNull.getErrorms());
            res.setCode(HotelErrorCodeEnum.ReqNull.getCode());
            res.setResult(result);
            return res;
        }
        String src = null;
        String errorMessage = StringUtils.EMPTY;
        try {
            src = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
        } catch (Exception e) {
            logger.error("艺龙订单详情异常,请求参数：{}", req.toJson(), e);
            errorMessage = e.getMessage();
        }
        if (src == null) {
            logger.error("艺龙订单详情，接口请求返回null，请求参数：{}，接口响应，{}", req.toJson(), src);
            result.setErrorMsg("艺龙订单详情，接口请求返回null，请求参数：" + req.toJson() + "，异常消息：" + errorMessage);
            res.setCode(HotelErrorCodeEnum.ResNull.getCode());
            res.setResult(result);
            return res;
        }
        res = JacksonUtils.parseNonEmpty(src, ElongOrderDetailResponse.class);
        return res;
    }

* @param dto dto
     * @return req
     */
    public ElongOrderDetailRequest convertRequest(LinkHotelOrderDetailDTO dto) {
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        ElongOrderDetailRequest req = new ElongOrderDetailRequest();
        req.setConfig(config);
        req.setOrderId(dto.getOrderId());
        if (StringUtils.isEmpty(dto.getOrderId())) {
            req.setOrderId("0");
        }
        req.setAffiliateConfirmationId(dto.getLocalOrderId());
        return req;
    }

    /**
     * @param res res
     * @param dto do
     * @return vo
     */
    public LinkHotelOrderDetailVO convertResponse(ElongOrderDetailResponse res, LinkHotelOrderDetailDTO dto) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        ElongOrderDetail result = res.getResult();
        if (!"0".equals(res.getCode()) || result == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg(result == null ? null : result.getErrorMsg());
            return vo;
        }
        String status = result.getStatus();
        String showStatus = result.getShowStatus();
        String ptzt = dto.getCpsOrderStatus();
        vo.setTrueStatus(OrderDetailHandler.joinWith(SymbolConstant.LEFT_SLASH, showStatus, status));// 供应商返回的原始状态
        String orderStatusMs = OrderDetailHandler.convertTrueStatusMs(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.getOrderStatusMs(status));
        String orderShowStatusMs = OrderDetailHandler.convertTrueStatusMs(status, ElongHotelGeneralEnum.ElongOrderShowStatusEnum.getOrderStatusMs(showStatus));
        vo.setTrueStatusMs(OrderDetailHandler.joinWith(SymbolConstant.COMMA, orderShowStatusMs, orderStatusMs));
        vo.setShowStatus(ptzt);
        String paymentType = result.getPaymentType();
        vo.setFjbz(result.getPenaltyCurrencyCode());
        if ("SelfPay".equals(paymentType)) {
            if ("SelfPay".equals(paymentType)) {
            if (StringUtils.isNotBlank(showStatus)) {
                if ("1".equals(showStatus)) {// 担保状态
                    vo.setGuaranteeStatus("担保失败");
                } else if ("2".equals(showStatus)) {
                    vo.setGuaranteeStatus("等待担保");
                } else if ("2048".equals(showStatus)) {
                    vo.setGuaranteeStatus("处理中");
                }
                vo.setShowStatus(getShowStatus(status, showStatus, ptzt, "SelfPay", dto.getPt()));
            }
            //计算退款金额和罚金
            buildJkgystkje(vo, result);
        } else if ("Prepay".equals(paymentType)) {// 预付 1表示预付
            if (StringUtils.isNotBlank(showStatus)) {
                if ("8".equals(showStatus)) {// 担保状态
                    vo.setGuaranteeStatus("等待支付");
                } else if ("4096".equals(showStatus)) {
                    vo.setGuaranteeStatus("正在担保-处理中");
                } else if ("8192".equals(showStatus)) {
                    vo.setGuaranteeStatus("支付失败");
                }
                vo.setShowStatus(getShowStatus(status, showStatus, ptzt, "Prepay", dto.getPt()));
            }
            //同步取消状态
            buildCancelStatus(vo, status, ptzt, result, dto.getPt());
            //取消罚金
            buildJkgystkje(vo, result);
        }

        vo.setCheckInDate(result.getArrivalDate().split("T")[0]);
        vo.setCheckOutDate(result.getDepartureDate().split("T")[0]);
        vo.setTotalPrice(result.getTotalPrice());
        vo.setNumberOfRooms(result.getNumberOfRooms());
        List<OrderRoom> orderRooms = result.getOrderRooms();
        Set<String> confirmationNumberSet = new HashSet<>();
        if (orderRooms != null && !orderRooms.isEmpty()) {
            StringBuilder sbr = new StringBuilder();
            for (OrderRoom orderRoom : orderRooms) {
                List<Customer> customers = orderRoom.getCustomers();
                if (customers != null && !customers.isEmpty()) {
                    for (Customer customer : customers) {
                        sbr.append("," + customer.getName());
                        if (StringUtils.isNotBlank(customer.getConfirmationNumber())) {
                            confirmationNumberSet.add(customer.getConfirmationNumber());
                        }
                    }
                }
            }
            vo.setContact(sbr.deleteCharAt(0).toString());
        }
        vo.setOrderId(result.getOrderId());
        vo.setLocalOrderId(result.getAffiliateConfirmationId());
        vo.setGysfxmc(result.getRoomName());
        vo.setGyszc(getMealNum(result.getDayMealTable(), result.getArrivalDate()));
        vo.setJdqrh(VeStringUtil.joinIfNotBlank(SymbolConstant.COMMA, confirmationNumberSet));
        ElongOrderHotel orderHotel = result.getOrderHotel();
        if (Objects.nonNull(orderHotel)) {
            vo.setGysjdmc(orderHotel.getName());
            vo.setHotelPhone(orderHotel.getPhone());
            vo.setHotelAddress(orderHotel.getAddress());
        }
        return vo;
    }

    /**
     * @param status     订单状态
     * @param showStatus 对用户展示的订单状态
     * @param ptzt       cps订单状态
     * @param payment    支付类型
     * @param pt         请求来源平台 cps charge(费控) cloud(差旅云)
     * @return str
     */
    private String getShowStatus(String status, String showStatus, String ptzt, String payment, String pt) {
        String cpsOrder = ptzt;
        if (StringUtils.isBlank(showStatus)) {
            cpsOrder = "-1";
        }
        if ("SelfPay".equals(payment)) {//现付
            //确认前
            if ("1".equals(ptzt)) {
                // 已预订待确认
                if ("4".equals(showStatus)) {
                    cpsOrder = HotelOrderStatusEnum.BOOK_NOT_CONFIRM.getCode();// 已预订待确认状态对应CPS订单状态码
                } else if (StringUtils.equalsIgnoreCase(status, } else if (StringUtils.equalsIgnoreCase(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.D.getCode())) {// 酒店拒绝订单
                    cpsOrder = HotelOrderStatusEnum.SUPPLY_CANCEL_NOT_CONFIRM.getCode();// 酒店拒绝订单状态对应CPS订单状态码(酒店拒绝订单)
                }
            } else if ("2".equals(ptzt)) {//确认后
                if (StringUtils.equalsIgnoreCase(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.D.getCode())) {// 酒店拒绝订单
                    cpsOrder = HotelOrderStatusEnum.SUPPLY_CANCEL_CONFIRM.getCode();// 酒店拒绝订单状态对应CPS订单状态码(酒店拒绝订单)
                } else if ("64".equals(showStatus)) {// 未入住 noshow
                    cpsOrder = HotelOrderStatusEnum.NOSHOW.getCode();// 未入住状态对应CPS订单状态码
                }
            }
            if ("16".equals(showStatus) || "512".equals(showStatus) || "A".equals(status)) {// 已确认
                cpsOrder = HotelOrderStatusEnum.BOOK_CONFIRM.getCode();// 已确认状态对应CPS订单状态码
            }
        }
        if ("Prepay".equals(payment)) {//预付
            //确认前
            if ("7".equals(ptzt)) {
                // 已预订待确认
                if ("4".equals(showStatus)) {
                    cpsOrder = HotelOrderStatusEnum.PAY_BEFORE_CONFIRM.getCode();// 已预订待确认状态对应CPS订单状态码
                } else if (StringUtils.equalsIgnoreCase(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.D.getCode())) {// 酒店拒绝订单
                    // 酒店拒绝订单状态对应CPS订单状态码(酒店拒绝订单)
                    cpsOrder = OrderDetailHandler.convert2Status(pt, ptzt);
                }
            }
            if ("16".equals(showStatus) || "512".equals(showStatus) || "A".equals(status)) {// 已确认
                cpsOrder = HotelOrderStatusEnum.PAY_AFTER_CONFIRM.getCode();// 已确认状态对应CPS订单状态码
            }
        }
        if ("1024".equals(showStatus)) {// 已入住
            if ("C".equals(status)) {
                cpsOrder = HotelOrderStatusEnum.OUT.getCode();// 未入住状态对应CPS订单状态码
            } else {
                cpsOrder = HotelOrderStatusEnum.LIVE.getCode();// 已入住状态对应CPS订单状态码
            }
        } else if ("128".equals(showStatus)) {// 已离店
            cpsOrder = HotelOrderStatusEnum.OUT.getCode();// 未入住状态对应CPS订单状态码
        }
        return cpsOrder;
    }
   /**
     * 为取消订单赋值
     *
     * @param status           艺龙订单状态
     * @param orderDetailVO    查询订单详返回实体
     * @param ptzt             cps订单状态
     * @param elongOrderDetail 艺龙返回订单详情
     * @param pt               平台类型
     */
     private void buildCancelStatus(LinkHotelOrderDetailVO orderDetailVO
            , String status
            , String ptzt
            , ElongOrderDetail elongOrderDetail
            , String pt) {
        //预付
        //交易对象为空
        ElongCreditCard creditCard = elongOrderDetail.getCreditCard();
        if (creditCard == null) {
            return;
        }
        //不是预付，或者平台状态不是“已支付已确认”
//        if (!"8".equals(ptzt)) {
//            return;
//        }
        //订单状态不是 已删除和已取消
        if (!StringUtils.equalsIgnoreCase(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.E.getCode())
                && !StringUtils.equalsIgnoreCase(status, ElongHotelGeneralEnum.ElongOrderStatusEnum.D.getCode())) {
            return;
        }
        //交易类型不是 退款
        if (!StringUtils.equalsIgnoreCase(creditCard.getProcessType(), ElongHotelGeneralEnum.CreditCardProcessTypeEnum.Refund.getCode())) {
            orderDetailVO.setIsCanceling("1");
            return;
        }
        if (StringUtils.equalsIgnoreCase(creditCard.getStatus(), ElongHotelGeneralEnum.CreditCardStatusEnum.Succeed.getCode())) {
            orderDetailVO.setShowStatus(OrderDetailHandler.convert2Status(pt, ptzt));
        } else {
            orderDetailVO.setShowStatus(ptzt);
            orderDetailVO.setIsCanceling("1");
        }
    }
    /***
     * 供应商退款金额 和罚金
     * @param orderDetailVO detailVo
     * @param elongOrderDetail  orderDetail
     */
    private void buildJkgystkje(LinkHotelOrderDetailVO orderDetailVO, ElongOrderDetail elongOrderDetail) {
        //交易对象为空
        ElongCreditCard creditCard = elongOrderDetail.getCreditCard();
        if (creditCard == null) {
            return;
        }
        //订单状态不是 已删除和已取消
        if (!StringUtils.equalsIgnoreCase(creditCard.getProcessType(), ElongHotelGeneralEnum.CreditCardProcessTypeEnum.Refund.getCode())) {
            return;
        }
        if (!StringUtils.equalsIgnoreCase(creditCard.getStatus(), ElongHotelGeneralEnum.CreditCardStatusEnum.Succeed.getCode())) {
            return;
        }
        BigDecimal refundAmount = dealRefundAmount(elongOrderDetail);
        if (refundAmount != BigDecimal.ZERO) {
            orderDetailVO.setJkgystkje(refundAmount.multiply(BigDecimal.valueOf(NumConstant.NUM_100)).intValue()); //单位分
        }
        BigDecimal penaltyAmount = dealPenaltyAmount(elongOrderDetail);
        if (penaltyAmount != BigDecimal.ZERO) {
            orderDetailVO.setJkgysfj(penaltyAmount.multiply(BigDecimal.valueOf(NumConstant.NUM_100)).intValue()); //单位分
            orderDetailVO.setPenaltyToCustomer(penaltyAmount);
        }
    }

    /**
     * 解析早餐数量
     *
     * @param dayMealTable 供应餐食列表
     * @param arrivalDate  入住时间
     * @return 早餐数量
     */
    private String getMealNum(List<ElongSearchDayMeal> dayMealTable, String arrivalDate) {
        String mealNum = StringUtils.EMPTY;
        if (CollectionUtils.isEmpty(dayMealTable) || StringUtils.isBlank(arrivalDate)) {
            return mealNum;
        }
        ImmutableTriple<FreeMealEnum, String, String> mealNumPair = dealMealNum(dayMealTable, arrivalDate);
        if (StringUtils.isBlank(mealNumPair.getMiddle())) {
            return mealNum;
        }
        return mealNumPair.getMiddle();
    }
}
