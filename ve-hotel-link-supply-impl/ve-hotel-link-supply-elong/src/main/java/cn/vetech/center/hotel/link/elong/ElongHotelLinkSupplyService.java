package cn.vetech.center.hotel.link.elong;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.member.dto.LinkHotelRegistrationDTO;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.orderbook.vo.LinkHotelOrderBookVO;
import cn.vetech.center.hotel.link.api.ordercancel.dto.LinkHotelOrderCancelDTO;
import cn.vetech.center.hotel.link.api.ordercancel.vo.LinkHotelOrderCancelVO;
import cn.vetech.center.hotel.link.api.orderdetail.dto.LinkHotelOrderDetailDTO;
import cn.vetech.center.hotel.link.api.orderdetail.vo.LinkHotelOrderDetailVO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.UserVipExtInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchUserVipExtInfo;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongResponseCodeEnum;
import cn.vetech.center.hotel.link.elong.orderbook.ElongOrderBookService;
import cn.vetech.center.hotel.link.elong.orderbook.request.ElongOrderBookRequest;
import cn.vetech.center.hotel.link.elong.orderbook.response.ElongOrderBookResponse;
import cn.vetech.center.hotel.link.elong.ordercancel.ElongOrderCancelService;
import cn.vetech.center.hotel.link.elong.ordercancel.request.ElongOrderCancelRequest;
import cn.vetech.center.hotel.link.elong.ordercancel.response.ElongOrderCancelResponse;
import cn.vetech.center.hotel.link.elong.orderdetail.ElongOrderDetailService;
import cn.vetech.center.hotel.link.elong.orderdetail.request.ElongOrderDetailRequest;
import cn.vetech.center.hotel.link.elong.orderdetail.response.ElongOrderDetailResponse;
import cn.vetech.center.hotel.link.elong.ratesearch.ElongInterRateSearchService;
import cn.vetech.center.hotel.link.elong.ratesearch.request.ElongRateSearchRequest;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongRateSearchResponse;
import cn.vetech.center.hotel.link.elong.register.ElongRegisterService;
import cn.vetech.center.hotel.link.elong.validate.ElongValidateService;
import cn.vetech.center.hotel.link.elong.validatebooking.ElongValidateBookingService;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.base.util.SupplierConfigUtils;
import cn.vetech.center.hotel.link.util.VipUtils;
import cn.vetech.center.hotel.link.util.orderbook.OrderBookApiRes;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.center.hotel.link.util.validate.ValidateApiRes;
import cn.vetech.center.hotel.link.util.validate.ValidateCommonUtils;
import cn.vetech.center.hotel.log.annotation.CommonLog;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cpfl.DdlxEnum;
import cn.vetech.charge.cpfl.JklxEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wanggongchuang
 * @version 1.0
 */

@Service
public class ElongHotelLinkSupplyService implements IHotelLinkSupplyService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ElongHotelLinkSupplyService.class);
    /**
     *
     */
    @Autowired
    private ElongValidateService validateService;
    /**
     *
     */
    @Autowired
    private ElongOrderBookService bookService;
    /**
     *
     */
    @Autowired
    private ElongOrderDetailService detailService;
    /**
     *
     */
    @Autowired
    private ElongOrderCancelService cancelService;
    /**
     * service
     */
    @Autowired
    private ElongInterRateSearchService interRateSearchService;
    /**
     *
     */
    @Autowired
    private ElongRegisterService registration;
    /**
     *
     */
    @Autowired
    private ElongValidateBookingService validateBookingService;

@CommonLog(jkzh = "查询报价", jkmc = "RateSearch", ddlx = DdlxEnum.DDLX0300_0, jklx = JklxEnum.CX_1001)
    @Log(name = "查询报价")
    @Override
    public LinkHotelRateSearchVO rateSearch(LinkHotelRateSearchDTO dto) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        //处理国内国际
        dealGngj(dto);

        ElongRateSearchRequest req = interRateSearchService.convertRequest(dto);
        boolean sfhyj = StringUtils.isNotBlank(req.getOpenId());
        //验证请求参数
        String msg = interRateSearchService.valRateRequest(req);
        if (StringUtils.isNotBlank(msg)) {
            return RateSearchApiRes.fail(msg);
        }
        ElongRateSearchResponse res = (ElongRateSearchResponse) interRateSearchService.execute(req);
        if (res == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("响应数据为空");
            return vo;
        }
        if (StringUtils.equalsIgnoreCase(res.getCode(), ElongResponseCodeEnum.E504.getCode())) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg(res.getErrorMsg());
            return vo;
        }
        try {
            if (StringUtils.equalsIgnoreCase(dto.getGngj(), GnGjTypeEnum.GJ.getCode())) {
                vo = interRateSearchService.convertGjRooms(req.getConfig(), res, req.getArrivalDate(), sfhyj, dto.getSfgat(), req, dto.getHotelLocalTimeZone());
            } else {
                vo = interRateSearchService.convertRooms(req, res, sfhyj, dto.getSfgat());
            }
        } catch (Exception e) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("请求或返回结果转换JSON出错");
        }
        ElongConfig config = SupplierConfigUtils.parse(dto.getSupplier(), ElongConfig.class);
        // 处理会员价参数
        if (sfhyj && "1".equals(config.getShowVipExtInfo())) {
            convertVip(vo.getRooms(), dto.getYdrPhoneNumber(), dto.getUserVipExtInfoList(), req.getOpenId());
        }
        return vo;
    }
    private void convertVip(List<SearchRoom> rooms, String ydrPhoneNumber, List<UserVipExtInfo> userVipExtInfoList, String openId) {
        String phoneNumber = ydrPhoneNumber;
        if (StringUtils.isBlank(phoneNumber)) {
            UserVipExtInfo userVipExtInfo = VipUtils.isOpenVipForUser(userVipExtInfoList, FyEnum.ELONG.getFybh());
            if (Objects.nonNull(userVipExtInfo) && StringUtils.isNotBlank(userVipExtInfo.getPhoneNumber())) {
                phoneNumber = userVipExtInfo.getPhoneNumber();
            }
        }
        if (CollectionUtils.isEmpty(rooms)) {
            return;
        }
        SearchUserVipExtInfo extInfo = new SearchUserVipExtInfo();
        extInfo.setFybh(FyEnum.ELONG.getFybh());
        extInfo.setPhoneNumber(phoneNumber);
        extInfo.setYdrUid(openId);
        for (SearchRoom searchRoom : rooms) {
            List<SearchRatePlan> ratePlans = searchRoom.getRatePlans();
            if (CollectionUtils.isEmpty(ratePlans)) {
                continue;
            }
            for (SearchRatePlan searchRatePlan : ratePlans) {
                searchRatePlan.setSearchUserVipExtInfo(extInfo);
            }
        }
    }
    @Log(name = "下单验证")
    @Override
    public LinkHotelValidateVO validate(LinkHotelValidateDTO dto) {
        if (StringUtils.isNotBlank(dto.getRatePlanId())) {
            // 20200804,处理'&'在无线端xml无法解析成对象的情况
            dto.setRatePlanId(dto.getRatePlanId().replaceAll("\\{ve\\}", "&"));
        }
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        if ("1".equals(config.getValidateType())) {
            return validateService.priceCheck(dto);
        }
        return ValidateApiRes.success();
    }

    @Log(name = "下单")
    @Override
    public LinkHotelOrderBookVO orderBook(LinkHotelOrderBookDTO dto) {
        ElongOrderBookRequest req = null;
        try {
            if (StringUtils.isNotBlank(dto.getRatePlanId())) {
                // 20200804,处理'&'在无线端xml无法解析成对象的情况
                dto.setRatePlanId(dto.getRatePlanId().replaceAll("\\{ve\\}", "&"));
            }
            LinkHotelValidateVO validateVO = new LinkHotelValidateVO();
            LinkHotelValidateDTO validateDTO = BeanMapper.map(dto, LinkHotelValidateDTO.class);
            validateVO = validateService.priceCheck(validateDTO);
            if (!ValidateCommonUtils.validateSuccess(validateVO)) {
                return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, Objects.nonNull(validateVO) ? validateVO.getErrorMsg() : "验价失败");
            }
            req = bookService.convertRequest(dto);
        } catch (Exception e) {
            LOGGER.error("艺龙预定异常", e);
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, "验价失败");
        }
        if (Objects.isNull(req)) {
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20001, "预订请求参数转换失败");
        }
        try {
            ElongOrderBookResponse res = (ElongOrderBookResponse) bookService.execute(req);
            return bookService.converResponse(res);
        } catch (Exception e) {
            LOGGER.error("艺龙预定异常", e);
            return OrderBookApiRes.failSupportInverseQuery(HotelOrderBookErrorCodeEnum.GYSE_UN_10001);
        }
    }

    @Log(name = "订单详情")
    @Override
    public LinkHotelOrderDetailVO orderDetail(LinkHotelOrderDetailDTO dto) {
        LinkHotelOrderDetailVO vo = new LinkHotelOrderDetailVO();
        ElongOrderDetailRequest req = detailService.convertRequest(dto);
        ElongOrderDetailResponse res = (ElongOrderDetailResponse) detailService.execute(req);
        if (res == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("接口响应数据为空");
            return vo;
        }
        vo = detailService.convertResponse(res, dto);
        vo.setHotelRequest(req.toJson());
        vo.setHotelResponse(res.toJson());
        return vo;
    }

    @Log(name = "订单取消")
    @Override
    public LinkHotelOrderCancelVO orderCancel(LinkHotelOrderCancelDTO dto) {
        LinkHotelOrderCancelVO vo = new LinkHotelOrderCancelVO();
        ElongOrderCancelRequest req = cancelService.convertRequest(dto);
        ElongOrderCancelResponse res = (ElongOrderCancelResponse) cancelService.execute(req);
        if (res == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("接口响应数据为空");
            return vo;
        }
        ElongOrderDetailRequest elongOrderDetailRequest = convertRequest(req.getConfig(), dto.getOrderId(), dto.getLocalOrderId());
        //调用查询订单详
        ElongOrderDetailResponse detailResponse = (ElongOrderDetailResponse) detailService.execute(elongOrderDetailRequest);
        vo = cancelService.convertResponse(res, detailResponse);
        vo.setHotelRequest(req.toJson());
        vo.setHotelResponse(res.toJson());
        return vo;
    }
    /**
     * request
     *
     * @param config       config
     * @param orderId      orderId
     * @param localOrderId orderId
     * @return elongrequest
     */
    public ElongOrderDetailRequest convertRequest(ElongConfig config, String orderId, String localOrderId) {
        ElongOrderDetailRequest req = new ElongOrderDetailRequest();
        req.setConfig(config);
        req.setOrderId(orderId);
        if (StringUtils.isBlank(orderId)) {
            req.setOrderId("0");
        }
        req.setAffiliateConfirmationId(localOrderId);
        return req;
    }

    /**
     * 处理国内国际
     *
     * @param dto 请求dto
     */
    private void dealGngj(LinkHotelRateSearchDTO dto) {
        if (StringUtils.isNotBlank(dto.getGngj())) {
            return;
        }
        String localHotelId = dto.getLocalHotelId();
        if (StringUtils.containsAny(localHotelId, "IVE", "IJD")) {
            dto.setGngj(GnGjTypeEnum.GJ.getCode());
            return;
        }
        dto.setGngj(GnGjTypeEnum.GN.getCode());
    }

    @Log(name = "员工注册绑定")
    @Override
    public LinkHotelRegistrationVO registration(LinkHotelRegistrationDTO dto) {
        return registration.registration(dto);
    }
}
