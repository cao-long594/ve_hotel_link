package cn.vetech.center.hotel.link.supply.ylfx.v2.ordercancel;

import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.enums.YlfxV2MethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ordercancel.request.YlfxV2OrderCancelRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ordercancel.response.YlfxV2OrderCancelResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ordercancel.OrderCancelApiRes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 易旅分销 V2 取消订单服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2OrderCancelService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2OrderCancelService.class);
    /**
     * V2 公共通信服务
     */
    @Autowired
    private YlfxV2UtilsService utilsService;

    /**
     * 取消订单
     *
     * @param dto 标准取消请求
     * @param config 易旅分销配置
     * @return 标准取消结果
     */
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto, YlfxConfig config) {
        try {
            YlfxV2OrderCancelRequest request = new YlfxV2OrderCancelRequest();
            request.setCustomerCode(config.getCustomerCode());
            request.setCusOrderNo(dto.getLocalOrderId());
            String responseBody = utilsService.sendPost(request, config, YlfxV2MethodEnum.CANCEL);
            YlfxV2OrderCancelResponse response = JacksonUtils.parseNonEmpty(responseBody, YlfxV2OrderCancelResponse.class);
            if (response == null || !StringUtils.equals("200", response.getCode()) || response.getData() == null) {
                return OrderCancelApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            String status = response.getData().getOrderStatus();
            if ("CANCELLED".equals(status)) {
                return OrderCancelApiRes.success(new LinkHotelOrderCancelVO());
            }
            if ("CONFIRM_PENDING".equals(status) || "CONFIRMED".equals(status)) {
                return OrderCancelApiRes.failCanceling("订单正在取消中");
            }
            return OrderCancelApiRes.fail(response.getMessage());
        } catch (Exception e) {
            LOGGER.warn("易旅分销 V2 取消订单接口异常【{}】", e.getMessage(), e);
            return OrderCancelApiRes.fail("接口异常");
        }
    }
}
