package cn.vetech.center.hotel.link.elong.ordercancel;

import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHotelGeneralEnum;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongResponseCodeEnum;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.ordercancel.request.ElongOrderCancelRequest;
import cn.vetech.center.hotel.link.elong.ordercancel.response.ElongOrderCancel;
import cn.vetech.center.hotel.link.elong.ordercancel.response.ElongOrderCancelResponse;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongCreditCard;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetail;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetailResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ordercancel.OrderCancelApiRes;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author gaojin
 */
@Service
public class ElongOrderCancelService extends ElongHttp implements ElongService {
    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ElongOrderCancelService.class);
    /**
     * 艺龙订单取消接口名
     */
    private final String method = "hotel.order.cancel";
    /**
     * 艺龙订单取消使用https
     */
    private final String http = "https";

    /**
     *
     */
    @Autowired
    private ElongOrderIncrService elongOrderIncrService;

    @Override
    public ElongResponse execute(ElongRequest req) {
        ElongOrderCancelResponse res = new ElongOrderCancelResponse();
        ElongOrderCancel cancel = new ElongOrderCancel();
        if (req == null) {
            res.setCode(HotelErrorCodeEnum.ReqNull.getCode());
            cancel.setErrorMsg(HotelErrorCodeEnum.ReqNull.getErrorms());
            res.setResult(cancel);
            return res;
        }
        String src = null;
        String errorMessage = StringUtils.EMPTY;
        try {
            src = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
        } catch (Exception e) {
            logger.error("艺龙订单取消异常,请求参数：{}", req.toJson(), e);
            errorMessage = e.getMessage();
        }
        if (src == null) {
            res.setCode(HotelErrorCodeEnum.ReqNull.getCode());
            cancel.setErrorMsg("艺龙订单取消，接口响应数据为空，请求参数" + req.toJson() + "异常信息：" + errorMessage);
            res.setResult(cancel);
            return res;
        }
        res = JacksonUtils.parseNonEmpty(src, ElongOrderCancelResponse.class);
        String code = res.getCode();
        ElongOrderCancel result = res.getResult();
        if (!StringUtils.equalsIgnoreCase(code, "0")) {
            result.setErrorMsg(code);
            res.setResult(result);
        }
        return res;
    }

    /**
     * @param res            res
     * @param detailResponse response
     * @return vo
     */
    public LinkHotelOrderCancelVO convertResponse(ElongOrderCancelResponse res, ElongOrderDetailResponse detailResponse) {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        if (Objects.isNull(res)) {
            return OrderCancelApiRes.fail("艺龙接口请求异常");
 }
        ElongOrderCancel result = res.getResult();
        String successs = result.getSuccesss();
        if (!StringUtils.equalsIgnoreCase(successs, "true") || Objects.isNull(detailResponse)) {
            return OrderCancelApiRes.fail(result.getErrorMsg());
        }
        if (!StringUtils.equalsIgnoreCase(ElongResponseCodeEnum.E0.getCode(), detailResponse.getCode())
                || Objects.isNull(detailResponse.getResult()) || Objects.isNull(detailResponse.getResult().getCreditCard())) {
            return OrderCancelApiRes.failCanceling("订单正在取消中");
        }
        ElongOrderDetail orderDetail = detailResponse.getResult();
        ElongCreditCard creditCard = orderDetail.getCreditCard();
        //交易类型不是 退款
        if (StringUtils.equalsIgnoreCase(creditCard.getProcessType(), ElongHotelGeneralEnum.CreditCardProcessTypeEnum.Refund.getCode())
                && StringUtils.equalsIgnoreCase(creditCard.getStatus(), ElongHotelGeneralEnum.CreditCardStatusEnum.Succeed.getCode())) {
            //处理取消罚金
            BigDecimal penaltyAmount = dealPenaltyAmount(orderDetail);
            if(penaltyAmount!=BigDecimal.ZERO){
                vo.setPenaltyAmount(penaltyAmount.toString());
            }
            return OrderCancelApiRes.success(vo);
        }
        return OrderCancelApiRes.failCanceling("订单正在取消中");
    }
    /**
     * 请求参数
     *
     * @param dto dto
     * @return req
     */
    public ElongOrderCancelRequest convertRequest(LinkHotelOrderCancelDTO dto) {
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        ElongOrderCancelRequest req = new ElongOrderCancelRequest();
        req.setConfig(config);
        req.setOrderId(dto.getOrderId());
        req.setCancelCode("行程变更");
        req.setReason(dto.getReason());
        return req;
    }
