package cn.vetech.center.hotel.link.supply.ylfx.ordercancel;

import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.ordercancel.request.YlfxOrderCancelRequest;
import cn.vetech.center.hotel.link.supply.ylfx.ordercancel.response.YlfxOrderCancelData;
import cn.vetech.center.hotel.link.supply.ylfx.ordercancel.response.YlfxOrderCancelResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ordercancel.OrderCancelApiRes;
import cn.vetech.charge.base.CommonMagicNumber;
import org.apache.commons.lang3.StringUtils;
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
public class YlfxOrderCancelService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxOrderCancelService.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 取消订单
     *
     * @param dto    dto
     * @param config config
     * @return LinkHotelOrderCancelVO
     */
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto, YlfxConfig config) {
        try {
            YlfxOrderCancelRequest request = new YlfxOrderCancelRequest();
            YlfxGysxdbj gysxdbj = JacksonUtils.parseNonEmpty(dto.getGysxdbj(), YlfxGysxdbj.class);
        request.setHotelId(gysxdbj.getHotelId());
            request.setOrderId(dto.getOrderId());
            request.setCusOrderId(dto.getLocalOrderId());
            request.setReason(dto.getReason());
            //取消接口只是申请取消 没有最终状态  都算取消中
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.CANCEL);
            YlfxOrderCancelResponse response = JacksonUtils.parseNonEmpty(res, YlfxOrderCancelResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())){
                return OrderCancelApiRes.fail(immutablePair.getRight());
            }
            YlfxOrderCancelData data = response.getData();
            if (Objects.isNull(data)){
                return OrderCancelApiRes.fail("取消数据为空");
            }
            //结果编码：0. 成功 1. 失败
            if (StringUtils.equals(data.getCode(),CommonMagicNumber.STRING1)){
                return OrderCancelApiRes.fail(data.getDesc());
            }
            LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
            vo.setCanceling(CommonMagicNumber.STRING1);
            return OrderCancelApiRes.fail("取消中，请求稍后同步");
        } catch (Exception e) {
            logger.warn("接口异常【{}】", e.getMessage(), e);
            return OrderCancelApiRes.fail("接口异常");
        }
    }
}
