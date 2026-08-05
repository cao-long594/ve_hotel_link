package cn.vetech.center.hotel.link.elong.validate;

import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.constant.ElongCodeEnum;
import cn.vetech.center.hotel.link.elong.constant.ElongValidateResultCodeEnum;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongExtend;
import cn.vetech.center.hotel.link.elong.ratesearch.response.NightPriceExtend;
import cn.vetech.center.hotel.link.elong.register.ElongRegisterService;
import cn.vetech.center.hotel.link.elong.validate.request.ElongValidateRequest;
import cn.vetech.center.hotel.link.elong.validate.response.ElongValidate;
import cn.vetech.center.hotel.link.elong.validate.response.ElongValidateResponse;
import cn.vetech.center.hotel.link.elong.validatebooking.ElongValidateBookingService;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.SalePriceControlTypeEnum;
import cn.vetech.center.hotel.link.supply.base.util.SupplierConfigUtils;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.validate.ValidateApiRes;
import cn.vetech.center.hotel.link.util.validate.ValidateCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.collection.CollectionUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author gaojin
 */
@Service
public class ElongValidateService extends ElongHttp implements ElongService {
    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ElongValidateService.class);
    /**
     * 艺龙数据验证接口名
     */
    private final String method = "hotel.data.validate";
    /**
     * 艺龙数据验证使用http
     */
    private final String http = "http";

    /**
     * 注册服务
     */
    @Autowired
    private ElongRegisterService elongRegisterService;
    /**
     *
     */
    @Autowired
    private ElongValidateBookingService validateBookingService;

    @Override
    public ElongResponse execute(ElongRequest req) {
        ElongValidateResponse res = new ElongValidateResponse();
        ElongValidate date = new ElongValidate();
        if (req == null) {
            res.setCode(HotelErrorCodeEnum.ReqNull.getCode());
            date.setErrorMessage(HotelErrorCodeEnum.ReqNull.getErrorms());
            res.setDate(date);
            return res;
        }
        String src = null;
        try {
            src = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
        } catch (Exception e) {
            logger.error("艺龙下单前验证异常，请求参数:{}", req.toJson(), e);
            date.setErrorMessage("艺龙数据验证，接口请求异常，请求参数：" + req.toJson() + "异常信息：" + e.getMessage());
        }
        if (src == null) {
            res.setCode(HotelErrorCodeEnum.ResNull.getCode());
            res.setDate(date);
            return res;
        }
        res = JacksonUtils.parseNonEmpty(src, ElongValidateResponse.class);
    return res;
    }

    /**
     * 校验结果
     *
     * @param response response
     * @return vo
     */
    public LinkHotelValidateVO convertResponse(ElongValidateResponse response) {
        if (Objects.isNull(response)) {
            return ValidateApiRes.fail("响应数据为空");
        }
        if (!StringUtils.equals(CommonMagicNumber.STRING0, response.getCode())) {
            return ValidateApiRes.fail(ElongCodeEnum.ValidateEnum.getErrorCodeEnumByCode(response.getCode()), response.getCode());
        }
        ElongValidate date = response.getDate();
        if (Objects.isNull(date)) {
            return ValidateApiRes.fail("响应数据Result为空");
        }
        if (!StringUtils.equalsIgnoreCase(ElongValidateResultCodeEnum.OK.getCode(), date.getResultCode())) {
            return ValidateApiRes.fail(date.getErrorMessage());
        }
        LinkHotelValidateVO vo = new LinkHotelValidateVO();
        String guaranteeRate = date.getGuaranteeRate();
        if (StringUtils.isNotBlank(guaranteeRate) && Double.parseDouble(guaranteeRate) > 0) {
            vo.setIsVouch("1");
            vo.setAmount(guaranteeRate);
            vo.setCurrencyCode(date.getCurrencyCode());
        } else {
            vo.setIsVouch("0");
        }
        vo.setCancelTime(date.getCancelTime());
        return ValidateApiRes.success(vo);
    }

    public LinkHotelValidateVO priceCheck(LinkHotelValidateDTO dto) {
        ElongValidateRequest validateRequest = convertRequest(dto);
        ElongValidateResponse validateResponse = (ElongValidateResponse) execute(validateRequest);
        LinkHotelValidateVO validateVO = convertResponse(validateResponse);
        ElongConfig config = SupplierConfigUtils.parse(dto.getSupplier(), ElongConfig.class);
        if (ValidateCommonUtils.validateSuccess(validateVO) && "1".equals(config.getValidateType())) {
            // velidate接口只能校验价格，booking接口可以用来校验取消规则、早餐信息
return validateBookingService.priceCheck(dto);
        }
        return validateVO;
    }

    /**
     * @param dto dto
     * @return req
     */
     public ElongValidateRequest convertRequest(LinkHotelValidateDTO dto) {
        if (dto == null) {
            logger.error("艺龙数据验证，CPS请求参数对象为null");
            return null;
        }
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        ElongValidateRequest req = new ElongValidateRequest();
        req.setConfig(config);
        req.setArrivalDate(StringUtils.substring(dto.getCheckInDate(), CommonMagicNumber.INT0, CommonMagicNumber.INT10));
        req.setDepartureDate(StringUtils.substring(dto.getCheckOutDate(), CommonMagicNumber.INT0, CommonMagicNumber.INT10));
        String earliestArrivalTime = dto.getEarliestArrivalTime();
        if(StringUtils.isNotBlank(earliestArrivalTime)) {
            String[] split = earliestArrivalTime.replaceAll("\\(|\\)|（|）|[\\u4e00-\\u9fa5]", "").split(" ");
            String hour = StringUtils.substring(split[1], CommonMagicNumber.INT1, CommonMagicNumber.INT2);
            String hh = StringUtils.substring(split[1], 0, CommonMagicNumber.INT1);
            if (Integer.parseInt(hh) == 0 && (Integer.parseInt(hour) >= 0 || Integer.parseInt(hour) <= CommonMagicNumber.INT6)) {
                req.setEarliestArrivalTime(VeDate.getNextDay(split[0], "-1") + " 23:59:00");
            } else {
                req.setEarliestArrivalTime(earliestArrivalTime);
            }
        }
        req.setLatestArrivalTime(dto.getLatestArrivalTime());
        req.setHotelId(dto.getHotelId());
        req.setRoomTypeID(dto.getRoomId());
        String rateplanid = dto.getRatePlanId();
        if (StringUtils.isNotEmpty(rateplanid)) {
            if (rateplanid.contains("_")) {
                req.setRatePlanId(rateplanid.split("_")[0]);
            } else {
                req.setRatePlanId(dto.getRatePlanId());
            }

            if (rateplanid.contains("@") && rateplanid.split("@").length >= CommonMagicNumber.INT2) {
                //req.setLittleMajiaId(rateplanid.split("@")[CommonMagicNumber.INT1]);
                req.setGoodsUniqId(rateplanid.split("@")[CommonMagicNumber.INT1]);
                if (rateplanid.split("@")[CommonMagicNumber.INT0].contains("_")) {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0].split("_")[0]);
                } else {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0]);
                }
            }
        }
        req.setTotalPrice(dto.getTotalPrice());
        req.setNumberOfRooms(dto.getNumberOfRooms());
        dealValidExtend(req, dto, config);
        //处理会员价
        buildOpenId(config, req, dto);
        return req;
    }
    /**
     * 构建请求的 options
     *
     * @param dto request
     */
    private void buildOpenId(ElongConfig config, ElongValidateRequest request, LinkHotelValidateDTO dto) {
        String openId = elongRegisterService.getOpenId(config, dto);
        if (StringUtils.isBlank(openId)) {
            return;
        }
        request.setOpenId(openId);
    }

    /**
     * 处理扩展字段
     *
     * @param req req
     * @param dto dto
     */
    private void dealValidExtend(ElongValidateRequest req, LinkHotelValidateDTO dto, ElongConfig config) {
        String gysxdbj = dto.getGysxdbj();
        if (StringUtils.isBlank(gysxdbj)) {
            return;
        }
        ElongExtend extend = JacksonUtils.parseNonNull(gysxdbj, ElongExtend.class);
        if (Objects.isNull(extend)) {
            return;
        }
        String littlemajiaid = extend.getLittlemajiaid();
        if (StringUtils.isNotBlank(littlemajiaid)) {
            String s = littlemajiaid.replaceAll("\\{ve\\}", "&");
            req.setLittleMajiaId(s);
        }
        //限制销售价产品
        List<NightPriceExtend> priceExtends = extend.getPriceExtends();
        if (StringUtils.equalsIgnoreCase(config.getLpKzxsjlx(), SalePriceControlTypeEnum.LTPRICE.getCode())
                && CollectionUtil.isNotEmpty(priceExtends)) {
            int size = dto.getNightlyRates().size();
            int priceSize = priceExtends.size();
            if (size == priceSize) {
                double sum = priceExtends.stream().mapToDouble(p -> NumberUtils.toDouble(p.getCost())).sum();
                logger.info("艺龙下单限制销售价产品验价，成本价：{}", sum);
                req.setTotalPrice(String.valueOf(sum));
            } else {
                logger.error("elong:限价的每日价格和价格计划每日价格天数不同");
            }
        }
        if (checkGngj(GnGjTypeEnum.judgeGjHotel(dto.getLocalHotelId()).getCode(), extend.getSfgat(), dto.getSfgat())) {
            if (StringUtils.isNotBlank(extend.getSupplierId())) {
                req.setSupplierId(extend.getSupplierId());
            }
            if (StringUtils.isNotBlank(extend.getHotelCode())) {
                req.setHotelCode(extend.getHotelCode());
            }
            if (StringUtils.isNotBlank(extend.getShopperProductId())) {
                req.setShopperProductId(extend.getShopperProductId());
            }
            if (StringUtils.isNotBlank(extend.getSubSupplierId())) {
                req.setSubSupplierId(extend.getSubSupplierId());
            }
            req.setCurrencyCode(StringUtils.defaultIfBlank(extend.getCurrencyCode(), "RMB"));
            if((StringUtils.isBlank(dto.getAdult()) || StringUtils.equalsIgnoreCase(dto.getAdult(),"0"))){
                if(CollectionUtils.isNotEmpty(dto.getOrderRooms())){
                    int sum = dto.getOrderRooms().stream().mapToInt(room -> CollectionUtils.size(room.getCustomers())).sum();
                    req.setNumberOfAdults(sum);
                }else{
                    req.setNumberOfAdults(NumConstant.NUM_1);
                }
                return;
            }
            req.setNumberOfAdults(NumberUtils.toInt(dto.getAdult(), NumConstant.NUM_1));
            // 港澳台酒店处理
            if (StringUtils.equalsAny("1", extend.getSfgat(), dto.getSfgat())) {
                req.setNumberOfAdults(convertGatExt(req.getNumberOfAdults(), extend));
            }
        }
    }
}
