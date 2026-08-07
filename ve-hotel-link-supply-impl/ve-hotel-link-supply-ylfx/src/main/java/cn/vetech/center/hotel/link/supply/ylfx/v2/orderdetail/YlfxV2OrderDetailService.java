package cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail;

import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.enums.YlfxV2MethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.request.YlfxV2OrderDetailRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.response.YlfxV2OrderDetailData;
import cn.vetech.center.hotel.link.supply.ylfx.v2.orderdetail.response.YlfxV2OrderDetailResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailApiRes;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 易旅分销 V2 查询订单服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2OrderDetailService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2OrderDetailService.class);
    /**
     * V2 公共通信服务
     */
    @Autowired
    private YlfxV2UtilsService utilsService;

    /**
     * 查询订单
     *
     * @param dto 标准查单请求
     * @param config 易旅分销配置
     * @return 标准查单结果
     */
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto, YlfxConfig config) {
        try {
            YlfxV2OrderDetailRequest request = new YlfxV2OrderDetailRequest();
            request.setCustomerCode(config.getCustomerCode());
            request.setCusOrderNo(dto.getLocalOrderId());
            String responseBody = utilsService.sendPost(request, config, YlfxV2MethodEnum.QUERY_STATUS);
            YlfxV2OrderDetailResponse response = JacksonUtils.parseNonEmpty(responseBody, YlfxV2OrderDetailResponse.class);
            if (response == null || !StringUtils.equals("200", response.getCode()) || response.getData() == null) {
                return OrderDetailApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            String status = response.getData().getOrderStatus();
            if ("NO_ORDER_FOUND".equals(status)) {
                return OrderDetailApiRes.fail(response.getMessage());
            }
            LinkHotelOrderDetailVO vo = convertResponse(response.getData(), dto);
            return OrderDetailApiRes.success(vo);
        } catch (Exception e) {
            LOGGER.warn("易旅分销 V2 查询订单接口异常【{}】", e.getMessage(), e);
            return OrderDetailApiRes.fail("接口异常");
        }
    }

    /**
     * 转换查询订单响应
     *
     * @param data V2 订单数据
     * @param dto 标准查单请求
     * @return 标准查单结果
     */
    private LinkHotelOrderDetailVO convertResponse(YlfxV2OrderDetailData data, LinkHotelOrderDetailDTO dto) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        vo.setOrderId(data.getOrderId());
        vo.setTrueStatus(data.getOrderStatus());
        vo.setTrueStatusMs(data.getOrderStatus());
        vo.setShowStatus(OrderDetailHandler.getShowStatus(dto.getCpsOrderStatus(), dto.getPayment(), dto.getPt(),
                convertStatus(data.getOrderStatus())));
        return vo;
    }

    /**
     * 转换供应商订单状态
     *
     * @param status 供应商订单状态
     * @return 标准供应商状态枚举
     */
    private HotelGysOrderStatusEnum convertStatus(String status) {
        if ("CONFIRMED".equals(status)) {
            return HotelGysOrderStatusEnum.AFTER_CONFIRM;
        }
        if ("CONFIRM_PENDING".equals(status)) {
            return HotelGysOrderStatusEnum.BEFORE_CONFIRM;
        }
        if ("CANCELLED".equals(status) || "REFUSED".equals(status)) {
            return HotelGysOrderStatusEnum.CANCEL;
        }
        return HotelGysOrderStatusEnum.ERROR;
    }
}
