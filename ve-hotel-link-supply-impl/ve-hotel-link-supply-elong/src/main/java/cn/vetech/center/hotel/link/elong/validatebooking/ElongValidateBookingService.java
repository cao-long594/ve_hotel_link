package cn.vetech.center.hotel.link.elong.validatebooking;

import cn.vetech.center.hotel.link.api.enums.FreeMealEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHotelGeneralEnum;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.common.ElongRequest;
import cn.vetech.center.hotel.link.elong.common.ElongResponse;
import cn.vetech.center.hotel.link.elong.common.ElongService;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongExtend;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchDayMeal;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchLadderParse;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchMeals;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchPrepayResult;
import cn.vetech.center.hotel.link.elong.register.ElongRegisterService;
import cn.vetech.center.hotel.link.elong.validate.ElongValidateService;
import cn.vetech.center.hotel.link.elong.validatebooking.request.ElongValidateBookingRequest;
import cn.vetech.center.hotel.link.elong.validatebooking.response.ElongValidateBooking;
import cn.vetech.center.hotel.link.elong.validatebooking.response.ElongValidateBookingResponse;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.validate.ValidateApiRes;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2025/9/29 17:21
 */
@Service
public class ElongValidateBookingService extends ElongHttp implements ElongService {

    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(ElongValidateService.class);
    /**
     * 艺龙数据验证接口名
     */
    private static final String method = "hotel.data.booking";
    /**
     * 艺龙数据验证使用http
     */
    private static final String http = "http";
    /**
     * 注册服务
     */
    @Autowired
    private ElongRegisterService elongRegisterService;

    @Override
    public ElongResponse execute(ElongRequest req) {
        try {
            String src = sendInvoke(method, req.toJson(), req.getConfig(), http, 0L);
            return JacksonUtils.parseNonEmpty(src, ElongValidateBookingResponse.class);
        } catch (Exception e) {
            logger.error("艺龙下单前验证异常，请求参数:{}", req.toJson(), e);
            return null;
        }
    }

    /**
     * @param dto dto
     * @return LinkHotelValidateVO
     */
    public LinkHotelValidateVO priceCheck(LinkHotelValidateDTO dto) {
        // 转换请求参数
        ImmutableTriple<Boolean, String, ElongValidateBookingRequest> triple = convertRequest(dto);
        if (Boolean.FALSE.equals(triple.getLeft())) {
            return ValidateApiRes.fail(triple.getMiddle());
        }
        // 调用供应商接口
        ElongResponse response = execute(triple.getRight());
        return convertResponse(response, dto);
    }

    /**
     * 转换请求参数
     *
     * @param dto dto
     * @return ImmutableTriple
     */
    public ImmutableTriple<Boolean, String, ElongValidateBookingRequest> convertRequest(LinkHotelValidateDTO dto) {
        ElongConfig config = BeanMapper.map(dto.getSupplier(), ElongConfig.class);
        ElongValidateBookingRequest req = new ElongValidateBookingRequest();
        req.setConfig(config);
        req.setArrivalDate(VeDateUtils.convertDate(dto.getCheckInDate()));
        req.setDepartureDate(VeDateUtils.convertDate(dto.getCheckOutDate()));
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
                req.setGoodsUniqId(rateplanid.split("@")[CommonMagicNumber.INT1]);
                if (rateplanid.split("@")[CommonMagicNumber.INT0].contains("_")) {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0].split("_")[0]);
                } else {
                    req.setRatePlanId(rateplanid.split("@")[CommonMagicNumber.INT0]);
                }
            }
        }
        String payment = dto.getPayment();
        if ("0".equals(payment)) {
            req.setPaymentType("SelfPay");
        } else if ("1".equals(payment)) {
            req.setPaymentType("Prepay");
        } else {
            return ImmutableTriple.of(false, "付款类型错误！", null);
        }
        req.setRatesWithDRR(true);
        req.setNumberOfRooms(dto.getNumberOfRooms());
        String earliestArrivalTime = dto.getEarliestArrivalTime();
        if (StringUtils.isNotBlank(earliestArrivalTime)) {
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
        dealValidExtend(req, dto, config);
        //处理会员价
        buildOpenId(config, req, dto);
        return ImmutableTriple.of(true, "", req);
    }
    /**
     * 构建请求的 options
     *
     * @param dto request
     */
    private void buildOpenId(ElongConfig config, ElongValidateBookingRequest request, LinkHotelValidateDTO dto) {
        String openId = elongRegisterService.getOpenId(config, dto);
        if (StringUtils.isBlank(openId)) {
            return;
        }
        request.setOpenId(openId);
    }
    /**
     * 处理拓展信息
     *
     * @param req    req
     * @param dto    dto
     * @param config config
     */
    private void dealValidExtend(ElongValidateBookingRequest req, LinkHotelValidateDTO dto, ElongConfig config) {
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
        if (StringUtils.isNotBlank(extend.getHotelCode())) {
            req.setHotelCode(extend.getHotelCode());
        }
    }
    /**
     * 校验结果
     *
     * @param elongResponse elongResponse
     * @return vo
     */
    public LinkHotelValidateVO convertResponse(ElongResponse elongResponse, LinkHotelValidateDTO dto) {
        if (Objects.isNull(elongResponse)) {
            return ValidateApiRes.fail("响应数据为空");
        }
        ElongValidateBookingResponse response = (ElongValidateBookingResponse) elongResponse;
        if (!StringUtils.equals(CommonMagicNumber.STRING0, response.getCode())) {
            return ValidateApiRes.fail(response.getErrorMsg());
        }
        ElongValidateBooking result = response.getResult();
        if (Objects.isNull(result)) {
            return ValidateApiRes.fail("响应数据Result为空");
        }
        // 校验总价
//        ImmutablePair<Boolean, String> totalPricePair = ValidateCommonUtils.checkTotalPrice(dto, result.getTotalRate());
//        if (Boolean.FALSE.equals(totalPricePair.getLeft())) {
//            return ValidateApiRes.fail(totalPricePair.getRight());
//        }

        // 校验早餐
        ImmutablePair<Boolean, String> mealsPair = checkMeals(dto, result.getMeals());
        if (Boolean.FALSE.equals(mealsPair.getLeft())) {
            return ValidateApiRes.fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20004, mealsPair.getRight());
        }
        // 校验取消规则
        ImmutablePair<Boolean, String> prepayRulePair = checkPrepayRule(dto, result.getPrepayResult());
        if (Boolean.FALSE.equals(prepayRulePair.getLeft())) {
            return ValidateApiRes.fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20004, prepayRulePair.getRight());
        }
        return ValidateApiRes.success();
    }
    private ImmutablePair<Boolean, String> checkMeals(LinkHotelValidateDTO dto, ElongSearchMeals meals) {
        // 验价接口返回早餐
        FreeMealEnum freeMealEnum = getMeals(meals, dto.getCheckInDate());
        // 查询报价返回早餐
        ElongExtend elongExtend = JacksonUtils.parseNonEmpty(dto.getGysxdbj(), ElongExtend.class);
        if (!StringUtils.equals(elongExtend.getFreeMeal(), freeMealEnum.getCode())) {
            return ImmutablePair.of(false, "早餐信息变更，验价接口返回早餐【" + freeMealEnum.getCode() + "】，查询报价返回早餐【" + elongExtend + "】");
        }
        return ImmutablePair.of(true, "");
    }

    private FreeMealEnum getMeals(ElongSearchMeals meals, String checkInDate) {
        // 默认无早
        FreeMealEnum freeMealEnum = FreeMealEnum.NO;
        if (Objects.isNull(meals)) {
            return freeMealEnum;
        }
        Boolean hasMealTable = meals.getHasMealTable();
        if (Objects.nonNull(hasMealTable) && hasMealTable) {
            List<ElongSearchDayMeal> dayMealTable = meals.getDayMealTable();
            ImmutableTriple<FreeMealEnum, String, String> mealNum = dealMealNum(dayMealTable, VeDateUtils.convertDate(checkInDate));
            return mealNum.getLeft();
        }
        return freeMealEnum;
    }

    private ImmutablePair<Boolean, String> checkPrepayRule(LinkHotelValidateDTO dto, ElongSearchPrepayResult prepayResult) {
        // 验价接口返回取消规则
        String zwqxsj = dealPrepayRule(prepayResult, dto.getGngj());
        // 查询报价返回取消规则
        ElongExtend elongExtend = JacksonUtils.parseNonEmpty(dto.getGysxdbj(), ElongExtend.class);
        String queryZwqxsj = StringUtils.isNotBlank(elongExtend.getZwqxsj()) ? elongExtend.getZwqxsj() : VeDate.getStringDate();
        int twoMin = VeDate.getTwoMin(queryZwqxsj, zwqxsj);
        if (twoMin > 0) {
            return ImmutablePair.of(false, "取消规则信息变更，验价接口返回最晚取消时间【" + zwqxsj + "】，查询报价返回最晚取消时间【" + queryZwqxsj + "】");
        }
        return ImmutablePair.of(true, "");
    }
     /**
     * 取消规则
     *
     * @param prepayResult prepayResult
     * @param gngj         gngj
     * @return String
     */
    protected String dealPrepayRule(ElongSearchPrepayResult prepayResult, String gngj) {
        try {
            SearchRatePlan plancps = new SearchRatePlan();
            String cancelType = prepayResult.getCancelType();
            if (StringUtils.equals(cancelType, ElongHotelGeneralEnum.ElongCancelTypeEnum.C2.getVal())
                    || StringUtils.equals(cancelType, ElongHotelGeneralEnum.ElongCancelTypeEnum.C4.getVal())) {
                // 不可取消、收费取消，按不可取消处理，返回当前时间
                return VeDate.getStringDate();
            }
            // 最晚取消时间
            List<ElongSearchLadderParse> ladderParseList = prepayResult.getLadderParseList();
            dealZwqxsj(plancps, ladderParseList, gngj, null);
            return plancps.getZwqxsj();
        } catch (Exception ex) {
            logger.error("艺龙解析取消规则异常", ex);
        }
        return VeDate.getStringDate();
    }
}
