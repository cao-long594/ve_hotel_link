package cn.vetech.center.hotel.link.elong.notice;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.notice.HotelNoticeCheckVO;
import cn.vetech.center.hotel.link.api.notice.HotelNoticeVO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelNoticeDTO;
import cn.vetech.center.hotel.link.api.notice.LinkHotelOrderDetailNoticeDTO;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHotelGeneralEnum;
import cn.vetech.center.hotel.link.elong.common.ElongSecurityUtil;
import cn.vetech.center.hotel.link.elong.notice.request.ElongNoticeOrder;
import cn.vetech.center.hotel.link.elong.notice.response.ElongNoticeOrderDetailResponse;
import cn.vetech.center.hotel.link.enums.HotelGysOrderStatusEnum;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.orderdetail.OrderDetailHandler;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

;

/**
 * @author chengwanshan
 * @since 2021/7/14 16:48
 */
@Service
public class ElongNoticeOrderDetailService {
    /**
     * 日志工具
     */
    private Logger logger = LoggerFactory.getLogger(ElongNoticeOrderDetailService.class);

    /**
     * @param notice 通知参数
     * @return HotelNoticeVO
     * @throws SystemException e
     */
    public HotelNoticeVO getOrderId(LinkHotelNoticeDTO notice) throws SystemException {
        logger.info("getOrderId，艺龙推送通知入参：{}", notice.toJson());
        HotelNoticeVO noticeVO = new HotelNoticeVO();
        //设置返回值
        ElongNoticeOrderDetailResponse noticeResponse = new ElongNoticeOrderDetailResponse();
        noticeResponse.setCode(0);
        noticeResponse.setErrorMsg("成功");
        noticeVO.setRtnStr(JacksonUtils.toJsonWithNonEmpty(noticeResponse));

        try {
            //解析通知参数
            List<ElongNoticeOrder> noticeOrderList = convertNoticeRequest(notice);
            if (CollectionUtils.isEmpty(noticeOrderList)) {
                return noticeVO;
            }
            List<String> orderList = noticeOrderList.stream().map(ElongNoticeOrder::getOrderId).collect(Collectors.toList());
            noticeVO.setOrderIdList(orderList);
        } catch (Exception e) {
            logger.error("通知转换订单号异常");
            noticeVO.setStatus(LinkHotelVO.FAIL);
            noticeVO.setErrorMsg(e.getMessage());
        }
        logger.info("出参：{}", JacksonUtils.toJsonWithNonEmpty(noticeVO));
        return noticeVO;
    }

    /**
     * @param detailNoticeDTO 请求参数（包括供应商推送信息和本地订单信息）
     * @return HotelNoticeCheckVO
     * @throws SystemException e
     */
    public HotelNoticeCheckVO getOrderDetail(LinkHotelOrderDetailNoticeDTO detailNoticeDTO) throws SystemException {
        logger.info("getOrderDetail，艺龙推送通知入参：{}", detailNoticeDTO.toJson());
        HotelNoticeCheckVO noticeCheckVO = new HotelNoticeCheckVO();
        ElongNoticeOrderDetailResponse noticeResponse = new ElongNoticeOrderDetailResponse();
        noticeResponse.setCode(0);
        noticeResponse.setErrorMsg("成功");
        noticeCheckVO.setRtnStr(JacksonUtils.toJsonWithNonEmpty(noticeResponse));
        try {
            //解析通知参数
            ElongNoticeOrder noticeOrder = convertGetOrderDetailRequest(detailNoticeDTO);
            if (noticeOrder == null) {
                noticeCheckVO.setStatus(LinkHotelVO.FAIL);
                return noticeCheckVO;
            }
            logger.info("艺龙订单增量推送，订单编号：【{}】，订单状态：【{}】", noticeOrder.getOrderId(), noticeOrder.getStatus());
            LinkHotelOrderDetailVO orderDetailVO = new LinkHotelOrderDetailVO();
            String orderStatus = noticeOrder.getStatus();
            LinkHotelOrderDetailDTO detailDTO = detailNoticeDTO.getDetailDTO();
            //转换订单状态
            if (detailDTO != null && StringUtils.isNotBlank(detailDTO.getCpsOrderStatus())) {
                convertOrderStatus(detailDTO, orderStatus, orderDetailVO, noticeCheckVO);
            } else {
                logger.error("本地订单状态为空：{}", JacksonUtils.toJsonWithNonEmpty(detailNoticeDTO));
                noticeCheckVO.setStatus(LinkHotelVO.FAIL);
                noticeCheckVO.setErrorMsg("本地订单状态为空：" + JacksonUtils.toJsonWithNonEmpty(detailNoticeDTO));
            }
            noticeCheckVO.setDetailVO(orderDetailVO);
        } catch (Exception e) {
            logger.error("通知转换订单详情异常：{}", e.getMessage());
            noticeCheckVO.setStatus(LinkHotelVO.FAIL);
            noticeCheckVO.setErrorMsg(e.getMessage());
        }
        logger.info("出参：{}", JacksonUtils.toJsonWithNonEmpty(noticeCheckVO));
        return noticeCheckVO;
    }

    /**
     * 转换通知信息
     *
     * @param notice 推送订单信息
     * @return List<ElongNoticeOrder>
     * @throws RuntimeException e
     */
    public List<ElongNoticeOrder> convertNoticeRequest(LinkHotelNoticeDTO notice) throws RuntimeException {
        if (notice == null) {
            logger.error("艺龙订单增量推送, 通知參數为空");
            throw new RuntimeException("艺龙订单增量推送, 通知參數为空");
        }
        String json = "";
        if (StringUtils.isNotBlank(notice.getStream())) {
            json = notice.stream2Str("UTF-8");
        } else {
            Map<String, String> data = notice.getData();
            json = data != null ? data.get("data") : "";
//            guid = data != null ? data.get("guid") : "";
//            type = data != null ? data.get("type") : "";
        }
        logger.info("艺龙订单增量推送，订单信息（加密串）：{}", json);
        if (StringUtils.isBlank(json)) {
            logger.error("艺龙订单增量推送，订单信息无法获取，入参：{}", notice.toJson());
            throw new RuntimeException("艺龙订单增量推送，订单信息无法获取");
        }

        String decrypt;
        // 处理配置信息，获取appKey的后8位用于解密
        ElongConfig config = BeanMapper.map(notice.getSupplier(), ElongConfig.class);
        if (Objects.isNull(config)) {
            logger.error("艺龙订单增量推送，获取配置信息失败");
            throw new RuntimeException("艺龙订单增量推送，获取配置信息失败");
        }
        String appKey = config.getAppKey();
        if (StringUtils.isBlank(appKey) || appKey.length() < CommonMagicNumber.INT8) {
            logger.error("艺龙订单增量推送，配置信息appKey不存在或不合法");
            throw new RuntimeException("艺龙订单增量推送，配置信息appKey不存在或不合法");
        }
        String key = appKey.substring(appKey.length() - CommonMagicNumber.INT8);
        try {
            //解密
            decrypt = ElongSecurityUtil.decrypt(json, key);
        } catch (Exception e) {
            logger.error("艺龙订单增量推送，data解密操作异常，data：{}", json);
            throw new RuntimeException("艺龙订单增量推送，data解密操作异常");
        }
        logger.info("艺龙订单增量推送，订单信息（解密）：{}", decrypt);
        List<ElongNoticeOrder> noticeOrderList = JacksonUtils.parseNonEmpty(decrypt, new TypeReference<List<ElongNoticeOrder>>() {
        });
        if (CollectionUtils.isEmpty(noticeOrderList)) {
            logger.error("艺龙订单增量推送，data转换参数出错，data：{}", decrypt);
            throw new RuntimeException("艺龙订单增量推送，data转换参数出错");
        }
        return noticeOrderList;
    }

    /**
     * 转换通知信息
     *
     * @param noticeCheckDTO 1
     * @return 1
     */
    public ElongNoticeOrder convertGetOrderDetailRequest(LinkHotelOrderDetailNoticeDTO noticeCheckDTO) {
        // 推送订单信息
        LinkHotelNoticeDTO noticeDTO = noticeCheckDTO.getNoticeDTO();
        // 解析通知参数
        List<ElongNoticeOrder> noticeOrderList = convertNoticeRequest(noticeDTO);
        // 按订单编号分组
        Map<String, List<ElongNoticeOrder>> noticeOrderListMap = noticeOrderList.stream()
                .filter(order -> StringUtils.isNotBlank(order.getOrderId()))
                .collect(Collectors.groupingBy(ElongNoticeOrder::getOrderId));
        if (MapUtils.isEmpty(noticeOrderListMap)) {
            logger.error("艺龙订单增量推送, 通知參數按订单编号分组异常");
            return null;
        }

        // 获取供应订单编号
        String orderId = noticeCheckDTO.getOrderId();
        List<ElongNoticeOrder> orders = noticeOrderListMap.get(orderId);
        if (CollectionUtils.isEmpty(orders)) {
            logger.error("艺龙订单增量推送, 订单编号【{}】无对应信息", orderId);
            return null;
        }
        // 按时间排序，保留最新鲜的那条数据，且该时间在当前时间半小时内
        ElongNoticeOrder noticeOrder = orders.get(0);
//        noticeOrder.getTime();
        return noticeOrder;
    }

    /**
     * 根据本地订单状态和供应商推送订单状态返回订单状态
     *
     * @param detailDTO     1
     * @param orderStatus   1
     * @param orderDetailVO 1
     * @param noticeCheckVO 1
     * @return 1
     */
    public HotelNoticeCheckVO convertOrderStatus(LinkHotelOrderDetailDTO detailDTO, String orderStatus, LinkHotelOrderDetailVO orderDetailVO, HotelNoticeCheckVO noticeCheckVO) {
        //本地订单状态
        String ptzt = detailDTO.getCpsOrderStatus();
        String payment = detailDTO.getPayment();
        String pt = detailDTO.getPt();
        if (StringUtils.isNotBlank(orderStatus) && StringUtils.isNotBlank(ptzt)) {
            orderDetailVO.setTrueStatus(orderStatus);//供应商返回原始的订单状态
            orderDetailVO.setShowStatus(convertOrderStatus(ptzt, orderStatus, payment, pt));
        } else {
            logger.error("订单状态为空, 通知參數orderStatus：{}，本地订单状态cpsOrderStatus：", orderStatus, ptzt);
            noticeCheckVO.setStatus(LinkHotelVO.FAIL);
            noticeCheckVO.setErrorMsg("订单状态为空, 通知參數orderStatus：" + orderStatus + "，本地订单状态cpsOrderStatus：" + ptzt);
        }
        return noticeCheckVO;
    }


    /**
     * 处理订单状态
     *
     * @param cpszt   本地订单状态
     * @param gyszt   供应商订单状态
     * @param payment 支付方式
     * @param pt      平台
     * @return String
     */
    private String convertOrderStatus(String cpszt, String gyszt, String payment, String pt) {
        HotelGysOrderStatusEnum hotelGysOrderStatusEnum = convert2HotelGysOrderStatus(gyszt);
        return OrderDetailHandler.getShowStatus(cpszt, gyszt, payment, pt, hotelGysOrderStatusEnum);
    }

    /**
     * 供应商原始订单状态转换成标准的订单状态
     *
     * @param orderStatus 供应商订单状态
     * @return HotelGysOrderStatusEnum
     */
    private HotelGysOrderStatusEnum convert2HotelGysOrderStatus(String orderStatus) {
        if (StringUtils.isBlank(orderStatus)) {
            return null;
        }
        //订单状态  A-已确认   B-NO SHOW   B1-有预定未查到   B2-待查   B3-暂不确定  C-已结帐   D-删除   E-取消    F-已入住   G-变价
        //H-变更  N-新单    O-满房    S-特殊    U-特殊满房  V-已审    Z-删除,另换酒店
//        if (StringUtils.equals(ElongOrderStatusEnum.A.getCode(), orderStatus)) {
//            return HotelGysOrderStatusEnum.AFTER_CONFIRM;
//        }
        if (StringUtils.equals(ElongHotelGeneralEnum.ElongOrderStatusEnum.F.getCode(), orderStatus)) {
            return HotelGysOrderStatusEnum.LIVE;
        }
        if (StringUtils.equals(ElongHotelGeneralEnum.ElongOrderStatusEnum.E.getCode(), orderStatus)) {
            return HotelGysOrderStatusEnum.CANCELING;
        }
        if (StringUtils.equals(ElongHotelGeneralEnum.ElongOrderStatusEnum.D.getCode(), orderStatus)) {
            return HotelGysOrderStatusEnum.CANCEL;
        }
        if (StringUtils.equals(ElongHotelGeneralEnum.ElongOrderStatusEnum.C.getCode(), orderStatus)) {
            return HotelGysOrderStatusEnum.OUT;
        }
        return null;
    }
}