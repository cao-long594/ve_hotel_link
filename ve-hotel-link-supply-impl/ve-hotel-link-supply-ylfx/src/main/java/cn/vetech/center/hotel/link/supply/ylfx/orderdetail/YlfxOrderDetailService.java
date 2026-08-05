package cn.vetech.center.hotel.link.supply.ylfx.orderdetail;

import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxOrderStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.orderdetail.request.YlfxOrderDetailRequest;
import cn.vetech.center.hotel.link.supply.ylfx.orderdetail.response.YlfxOrderDetailData;
import cn.vetech.center.hotel.link.supply.ylfx.orderdetail.response.YlfxOrderDetailResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailApiRes;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailHandler;
import cn.vetech.charge.base.CommonMagicNumber;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxOrderDetailService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxOrderDetailService.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 订单详情
     *
     * @param dto    dto
     * @param config config
     * @return LinkHotelOrderDetailVO
     */
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto, YlfxConfig config) {
        try {
            YlfxOrderDetailRequest request = new YlfxOrderDetailRequest();
            request.setCusOrderId(dto.getLocalOrderId());
            request.setOrderId(dto.getOrderId());
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.QUERY_STATUS);
            YlfxOrderDetailResponse response = JacksonUtils.parseNonEmpty(res, YlfxOrderDetailResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                return OrderDetailApiRes.fail(immutablePair.getRight());
            }
            YlfxOrderDetailData data = response.getData();
            if (Objects.isNull(data)) {
                return OrderDetailApiRes.fail("订单详情数据为空");
            }
            Integer code = data.getCode();//结果编码：0. 查询成功 1. 查询失败
            if (!CommonMagicNumber.INT0.equals(code)) {
                return OrderDetailApiRes.fail(data.getDesc());
            }
            LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
            Integer status = data.getStatus();
            String showStatus = OrderDetailHandler.getShowStatus(dto.getCpsOrderStatus(), dto.getPayment(), dto.getPt(), YlfxOrderStatusEnum.getHotelGysOrderStatusEnum(status));
            vo.setShowStatus(showStatus);
            vo.setTrueStatus(String.valueOf(status));
            vo.setTrueStatusMs(OrderDetailHandler.convertTrueStatusMs(String.valueOf(status), YlfxOrderStatusEnum.getGysName(status)));
            vo.setOrderId(data.getOrderId());
            return OrderDetailApiRes.success(vo);
        } catch (Exception e) {
            logger.warn("接口异常【{}】", e.getMessage(), e);
            return OrderDetailApiRes.fail("接口异常");
        }
    }
}
