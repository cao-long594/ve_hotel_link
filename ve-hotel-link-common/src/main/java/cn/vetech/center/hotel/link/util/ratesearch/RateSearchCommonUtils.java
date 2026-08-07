package cn.vetech.center.hotel.link.util.ratesearch;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FreeMealEnum;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.HotelDeductionTypeEnum;
import cn.vetech.center.hotel.link.api.enums.HotelTimeZoneEnum;
import cn.vetech.center.hotel.link.api.enums.RoomStatusEnum;
import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.BookingRuleExt;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelLadderDeductionInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelTimeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchBookingRule;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchPrepayRule;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.SearchNightlyRateStatusEnum;
import cn.vetech.center.hotel.link.util.HotelUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import cn.vetech.center.hotel.link.util.ratesearch.enums.CancelRuleDescTimeZoneTypeEnum;
import cn.vetech.center.hotel.link.util.ratesearch.pojo.RateSearchDailyRoomRate;
import cn.vetech.center.hotel.link.util.ratesearch.pojo.SearchLadderDeductionInfo;
import cn.vetech.center.hotel.link.util.ratesearch.pojo.SearchPrepayRuleInfo;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengwanshan
 * @since 2022/3/22 21:31
 */
public class RateSearchCommonUtils {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RateSearchCommonUtils.class);
    /**
     * 限时取消通用规则描述
     */
    public static final String DESCRIPTION_TIME = "%s前可免费取消修改，若未入住或过时取消修改将收取您全部房费。";
    /**
     * 00:00:00
     */
    public static final String TIME_STR_000000 = "00:00:00";
    /**
     * 18:00:00
     */
    public static final String TIME_STR_180000 = "18:00:00";
    /**
     * 23:59:00
     */
    public static final String TIME_STR_235900 = "23:59:00";
    /**
     * 23:59:59
     */
    public static final String TIME_STR_235959 = "23:59:59";
    /**
     * 24:00:00
     */
    public static final String TIME_STR_240000 = "24:00:00";

    /**
     * 处理房态
     *
     * @param ratePlan       标准价格计划
     * @param roomStatusEnum 房态枚举
     */
    public static void convertRoomStatus(SearchRatePlan ratePlan, RoomStatusEnum roomStatusEnum) {
        ratePlan.setFirstStatus(roomStatusEnum.getCode());
        ratePlan.setAllStatus(roomStatusEnum.getCode());
        ratePlan.setAllStatusDesc(roomStatusEnum.getName());
    }

    /**
     * 处理早餐
     *
     * @param ratePlan     标准价格计划
     * @param freeMealEnum 早餐枚举
     */
    public static void convertBreakfast(SearchRatePlan ratePlan, FreeMealEnum freeMealEnum) {
        if (Objects.isNull(freeMealEnum)) {
            freeMealEnum = FreeMealEnum.NO;
        }
        ratePlan.setFreeMeal(freeMealEnum.getCode());
        ratePlan.setFreeMealMs(freeMealEnum.getDesc());
        ratePlan.setZcsm(freeMealEnum.getDesc());
        ratePlan.setFreeMealDesc(freeMealEnum.getDesc());
    }

    /**
     * 处理早餐
     *
     * @param ratePlan       标准价格计划
     * @param breakfastCount 早餐数量
     * @param freeMealDesc   早餐描述
     */
    public static void convertBreakfast(SearchRatePlan ratePlan, String breakfastCount, String freeMealDesc) {
        convertFreeMealByMealNum(ratePlan, NumberUtils.toInt(breakfastCount));
        if (StringUtils.isNotBlank(freeMealDesc) && FreeMealEnum.H.getCode().equals(ratePlan.getFreeMeal())) {
            ratePlan.setFreeMealMs(freeMealDesc);
            ratePlan.setZcsm(freeMealDesc);
            ratePlan.setFreeMealDesc(freeMealDesc);
        }
    }

    /**
     * 处理公共参数
     *
     * @param ratePlan 标准价格计划
     */
    public static void convertCommonParameters(SearchRatePlan ratePlan) {
        ratePlan.setCurrencyCode("RMB");
    }

    /**
     * 校验查询报价公共请求参数
     *
     * @param config 配置信息
     * @param dto    标准请求DTO
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> valData(Object config, LinkHotelRateSearchDTO dto) {
        // 公共参数校验
        ImmutablePair<Boolean, String> valCommonDataPair = HotelUtils.valCommonData(config, dto);
        if (Boolean.FALSE.equals(valCommonDataPair.getLeft())) {
            return ImmutablePair.of(false, valCommonDataPair.getRight());
        }

        if (StringUtils.isBlank(dto.getHotelId())) {
            return ImmutablePair.of(false, "请求参数hotelId(酒店id)为空");
        }
        if (StringUtils.isBlank(dto.getCheckInDate())) {
            return ImmutablePair.of(false, "请求参数checkInDate(入住日期)为空");
        }
        if (StringUtils.isBlank(dto.getCheckOutDate())) {
            return ImmutablePair.of(false, "请求参数checkOutDate(离店日期)为空");
        }
        return ImmutablePair.of(true, "请求参数校验通过");
    }


    /**
     * 处理房价房态
     *
     * @param dto            标准请求参数
     * @param ratePlan       标准价格计划
     * @param dailyRoomRates 标准房价房态信息
     * @return boolean
     */
    public static boolean convertNightlyRates(LinkHotelRateSearchDTO dto, SearchRatePlan ratePlan, List<RateSearchDailyRoomRate> dailyRoomRates) {
        if (CollectionUtils.isEmpty(dailyRoomRates)) {
            return false;
        }
        // 每日价格明细
        List<SearchNightlyRate> nightlyRates = new ArrayList<>();
        String checkInDate = VeDateUtils.convertDate(dto.getCheckInDate());
        String checkOutDate = VeDateUtils.convertDate(dto.getCheckOutDate());
        // 查询天数
        int queryDays = VeDate.getTwoDay(checkOutDate, checkInDate);
        // 构建查询预订起止日期集合
        List<String> queryDayList = new ArrayList<>();
        for (int i = 0; i < queryDays; i++) {
            queryDayList.add(VeDate.getNextDay(checkInDate, String.valueOf(i)));
        }
        if (CollectionUtils.isEmpty(queryDayList)) {
            return false;
        }

        // 供应商每日房价按日期分组
        Map<String, List<RateSearchDailyRoomRate>> priceGroupByDateMap = dailyRoomRates.stream()
                .peek(p -> p.setDate(VeDateUtils.convertDate(p.getDate())))
                .filter(priceAmount -> StringUtils.isNotBlank(priceAmount.getDate()))
                .collect(Collectors.groupingBy(RateSearchDailyRoomRate::getDate));

        if (MapUtils.isEmpty(priceGroupByDateMap)) {
            return false;
        }

        for (String day : queryDayList) {
            SearchNightlyRate nightlyRate = new SearchNightlyRate();
            List<RateSearchDailyRoomRate> dailyRates = priceGroupByDateMap.get(day);
            // 异常情况，该日期无价格数据 或 存在多条价格数据
            if (CollectionUtils.size(dailyRates) != NumConstant.NUM_1) {
                break;
            }
            RateSearchDailyRoomRate dailyRate = dailyRates.get(0);
            nightlyRate.setDate(dailyRate.getDate());
            nightlyRate.setPriceBeforTax(dailyRate.getPrice());
            nightlyRate.setPriceAfterTax(dailyRate.getPrice());
            // 每日房态默认可用
            nightlyRate.setStatus(SearchNightlyRateStatusEnum.YES.getCode());

            // 处理房量，房量不为空，则需要校验房量
            if (SearchNightlyRateStatusEnum.YES.getCode().equals(nightlyRate.getStatus())
                    && StringUtils.isNotBlank(dailyRate.getRoomNum())
                    && NumberUtils.toInt(dailyRate.getRoomNum()) < NumConstant.NUM_1) {
                nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
            }
            // 处理房态，房态不为空，则需要校验房态
            if (SearchNightlyRateStatusEnum.YES.getCode().equals(nightlyRate.getStatus())
                    && StringUtils.isNotBlank(dailyRate.getStatus())
                    && !StringUtils.equals(SearchNightlyRateStatusEnum.YES.getCode(), dailyRate.getStatus())) {
                nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
            }

            nightlyRates.add(nightlyRate);
        }
        // 比较每日房价条数是否等于查询天数
        if (CollectionUtils.size(nightlyRates) != queryDays) {
            return false;
        }
        ratePlan.setNightlyRates(nightlyRates);

        // 价格计划房态默认充足
        RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.GOOD);
        // 根据剩余房间数量处理房态，大于0小于5处理为紧张
        if (NumberUtils.toInt(ratePlan.getSyfjsl()) > NumConstant.NUM_0 && NumberUtils.toInt(ratePlan.getSyfjsl()) < NumConstant.NUM_5) {
            RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.TENSION);
        }
        // 每日房态只要有一天为不可用，则价格计划房态为满房
        boolean anyStatus = nightlyRates.stream().anyMatch(n -> SearchNightlyRateStatusEnum.NO.getCode().equals(n.getStatus()));
        if (anyStatus) {
            RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
        }

        // 首日房费
        Optional<SearchNightlyRate> firstNightlyRate = nightlyRates.stream()
                .filter(n -> StringUtils.equals(n.getDate(), checkInDate))
                .collect(Collectors.toList()).stream().findFirst();
        firstNightlyRate.ifPresent(searchNightlyRate -> ratePlan.setFirstPrice(searchNightlyRate.getPriceAfterTax()));

        // 总房费
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (SearchNightlyRate rate : nightlyRates) {
            totalPrice = totalPrice.add(new BigDecimal(rate.getPriceAfterTax()));
        }
        if (Objects.nonNull(dto.getFjs()) && dto.getFjs() > 1) {
            ratePlan.setTotalRate(totalPrice.multiply(BigDecimal.valueOf(dto.getFjs())).toString());
        } else {
            ratePlan.setTotalRate(totalPrice.toString());
        }
        return true;
    }

    /**
     * 处理取消规则
     *
     * @param ratePlan         价格计划
     * @param suffixTypeEnum   取消规则枚举
     * @param desc             取消规则描述
     * @param latestCancelTime 最晚取消时间，yyyy-MM-dd HH:mm:ss
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, SuffixTypeEnum suffixTypeEnum, String desc, String latestCancelTime) {
        SearchPrepayRuleInfo ruleInfo = new SearchPrepayRuleInfo();
        ruleInfo.setSuffixTypeEnum(suffixTypeEnum);
        ruleInfo.setDesc(desc);
        ruleInfo.setLatestCancelTime(latestCancelTime);
        convertSearchPrepayRule(ratePlan, ruleInfo);
    }

    /**
     * 国内酒店供应商，转换取消规则
     *
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, GnGjTypeEnum.GN.getCode(), false, null, null);
    }

    /**
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     * @param showFeeCancel           showFeeCancel
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, boolean showFeeCancel) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, GnGjTypeEnum.GN.getCode(), showFeeCancel, null, null);
    }

    /**
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     * @param showFeeCancel           showFeeCancel
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, String gngj, boolean showFeeCancel) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, gngj, showFeeCancel, null, null);
    }

    /**
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     * @param showFeeCancel           showFeeCancel
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, boolean showFeeCancel, String desc, CancelRuleDescTimeZoneTypeEnum descTimeZoneTypeEnum) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, GnGjTypeEnum.GN.getCode(), showFeeCancel, desc, descTimeZoneTypeEnum);
    }

    /**
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     * @param gngj                    gngj
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, String gngj) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, gngj, false, null, null);
    }

    /**
     * @param ratePlan                ratePlan
     * @param ladderDeductionInfoList ladderDeductionInfoList
     * @param gngj                    gngj
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, String gngj, String desc, CancelRuleDescTimeZoneTypeEnum descTimeZoneTypeEnum) {
        convertSearchPrepayRule(ratePlan, ladderDeductionInfoList, gngj, false, desc, descTimeZoneTypeEnum);
    }

    /**
     * 国际酒店供应商，转换取消规则
     *
     * @param ratePlan                价格计划
     * @param ladderDeductionInfoList 取消规则列表
     * @param gngj                    国内国际
     * @param showFeeCancel           是否展示收费的取消规则
     * @param desc                    取消规则描述
     * @param descTimeZoneTypeEnum    取消规则描述中的时间时区，如果传desc，必传descTimeZoneTypeEnum
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, List<HotelLadderDeductionInfo> ladderDeductionInfoList, String gngj, boolean showFeeCancel, String desc, CancelRuleDescTimeZoneTypeEnum descTimeZoneTypeEnum) {
        // 默认不可取消
        RateSearchCommonUtils.convertSearchPrepayRule(ratePlan, SuffixTypeEnum.NOT_CANCEL, null, null);
        if (CollectionUtils.isEmpty(ladderDeductionInfoList)) {
            return;
        }
        // 免费取消
        List<HotelLadderDeductionInfo> ladderDeductionInfoArrayList = ladderDeductionInfoList.stream()
                .filter(l -> HotelDeductionTypeEnum.FREE.getCode().equals(l.getDeductionType()))
                .collect(Collectors.toList());
        // 收费取消
        List<HotelLadderDeductionInfo> ladderList = ladderDeductionInfoList.stream()
                .filter(l -> HotelDeductionTypeEnum.LADDER.getCode().equals(l.getDeductionType())
                        && Objects.nonNull(l.getBeijingEndDeductTime())
                        && StringUtils.isNotBlank(l.getBeijingEndDeductTime().getTime()))
                .sorted(Comparator.comparing(l -> l.getBeijingEndDeductTime().getTime()))
                .collect(Collectors.toList());
        ladderDeductionInfoArrayList.addAll(ladderList);
        // 不可取消
        List<HotelLadderDeductionInfo> cannotCancelList = ladderDeductionInfoList.stream()
                .filter(l -> HotelDeductionTypeEnum.CANNOT_CANCEL.getCode().equals(l.getDeductionType()))
                .collect(Collectors.toList());
        ladderDeductionInfoArrayList.addAll(cannotCancelList);

        Set<String> deductionTypeSet = ladderDeductionInfoArrayList.stream().map(HotelLadderDeductionInfo::getDeductionType).collect(Collectors.toSet());
        // 不包含免费取消，按不可取消解析
        if (!CollectionUtils.containsAny(deductionTypeSet, HotelDeductionTypeEnum.FREE.getCode())) {
            return;
        }
        // 如果不展示收费取消，过滤收费取消规则
        if (!showFeeCancel && CollectionUtils.containsAny(deductionTypeSet, HotelDeductionTypeEnum.LADDER.getCode())) {
            List<HotelLadderDeductionInfo> ladderDeductionInfoNewList = new ArrayList<>();
            // 免费取消
            HotelLadderDeductionInfo deductionInfoFirst = ladderDeductionInfoArrayList.stream()
                    .filter(l -> StringUtils.equals(l.getDeductionType(), HotelDeductionTypeEnum.FREE.getCode()))
                    .findFirst().orElse(null);
            if (Objects.isNull(deductionInfoFirst)) {
                return;
            }
            ladderDeductionInfoNewList.add(deductionInfoFirst);
            // 根据免费取消规则补充一条不可取消规则
            HotelLadderDeductionInfo deductionInfoLast = new HotelLadderDeductionInfo();
            deductionInfoLast.setDeductionType(HotelDeductionTypeEnum.CANNOT_CANCEL.getCode());
            deductionInfoLast.setOriginalStartDeductTime(deductionInfoFirst.getOriginalEndDeductTime());
            deductionInfoLast.setBeijingStartDeductTime(deductionInfoFirst.getBeijingEndDeductTime());
            deductionInfoLast.setLocalStartDeductTime(deductionInfoFirst.getLocalEndDeductTime());
            ladderDeductionInfoNewList.add(deductionInfoLast);

            // 替换取消规则列表
            ladderDeductionInfoArrayList = ladderDeductionInfoNewList;
        }

        // 最晚取消时间只能返回
        String latestCancelTime = StringUtils.EMPTY;
        String latestCancelTimeZone = StringUtils.EMPTY;
        // 取消规则描述
        List<String> descList = new ArrayList<>();
        // 处理最晚取消时间和取消规则描述
        for (HotelLadderDeductionInfo deductionInfo : ladderDeductionInfoArrayList) {
            if (HotelDeductionTypeEnum.FREE.getCode().equals(deductionInfo.getDeductionType())) {
                // 最晚免费取消时间
                String endTime = deductionInfo.getBeijingEndDeductTime().getTime();
                if (StringUtils.isNotBlank(endTime)) {
                    descList.add(endTime + "前可免费取消");
                    latestCancelTime = endTime;
                    // latestCancelTimeZone取决于latestCancelTime
                    latestCancelTimeZone = HotelTimeZoneEnum.UTC_800.getCode();
                }
            } else if (HotelDeductionTypeEnum.LADDER.getCode().equals(deductionInfo.getDeductionType())) {
                HotelTimeInfo beijingStartDeductTime = deductionInfo.getBeijingStartDeductTime();
                HotelTimeInfo beijingEndDeductTime = deductionInfo.getBeijingEndDeductTime();
                String descStr = null;
                if (Objects.nonNull(beijingStartDeductTime) && Objects.nonNull(beijingEndDeductTime)) {
                    descStr = VeStringUtil.joinWithEmptyIsNotBlank(beijingStartDeductTime.getTime(), "到", beijingEndDeductTime.getTime(), "之间取消将扣除");
                }
                FeeInfo originPrice = deductionInfo.getOriginPrice();
                String nights = deductionInfo.getNights();
                String percent = deductionInfo.getPercent();
                if (Objects.nonNull(originPrice)) {
                    descStr = VeStringUtil.joinWithEmptyIsNotBlank(descStr, originPrice.getFee().toString(), "房费");
                } else if (NumberUtils.toInt(nights) > NumConstant.NUM_0) {
                    descStr = descStr + (NumberUtils.toInt(nights) == NumConstant.NUM_1 ? "首晚" : "前" + nights + "晚") + "房费";
                } else if (StringUtils.isNotBlank(percent)) {
                    descStr = descStr + percent + "房费";
                }
                if (StringUtils.isNotBlank(descStr)) {
                    descList.add(descStr);
                }
            } else if (HotelDeductionTypeEnum.CANNOT_CANCEL.getCode().equals(deductionInfo.getDeductionType())) {
                // 不可取消的开始时间
                String startTime = deductionInfo.getBeijingStartDeductTime().getTime();
                if (StringUtils.isNotBlank(startTime)) {
                    descList.add(startTime + "后不可取消");
                }
            }
        }
        if (StringUtils.isBlank(latestCancelTime)) {
            // 最晚免费取消时间为空，按不可取消解析，目前我们系统是根据最晚免费取消时间来控制取消按钮的展示，即最晚免费取消时间前可以调用取消接口，之后不可调用取消接口
            return;
        }
        // 拼接取消规则描述
        desc = StringUtils.defaultIfBlank(desc, VeStringUtil.joinIfNotBlank(SymbolConstant.FULL_COMMA, descList));
        String descTimeZoneType = Objects.nonNull(descTimeZoneTypeEnum) ? descTimeZoneTypeEnum.getCode() : CancelRuleDescTimeZoneTypeEnum.BEIJING.getCode();
        // 取消规则
        SearchPrepayRuleInfo ruleInfo = new SearchPrepayRuleInfo();
        ruleInfo.setSuffixTypeEnum(SuffixTypeEnum.TIME_CANCEL);
        ruleInfo.setLatestCancelTime(latestCancelTime);
        ruleInfo.setLatestCancelTimeZone(latestCancelTimeZone);
        ruleInfo.setGngj(gngj);
        ruleInfo.setDesc(desc);
        ruleInfo.setDescTimeZoneType(descTimeZoneType);
        // 处理取消规则
        convertSearchPrepayRule(ratePlan, ruleInfo, false);
        if (StringUtils.equalsAny(ratePlan.getSuffixName(), SuffixTypeEnum.TIME_CANCEL.getCode(), SuffixTypeEnum.FEE_CANCEL.getCode())) {
            ratePlan.setLadderDeductionInfoList(ladderDeductionInfoArrayList);
        }
    }

    /**
     * 处理取消规则
     *
     * @param ratePlan 价格计划
     * @param ruleInfo 取消规则信息
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, SearchPrepayRuleInfo ruleInfo) {
        convertSearchPrepayRule(ratePlan, ruleInfo, true);
    }

    /**
     * 处理取消规则
     *
     * @param ratePlan 价格计划
     * @param ruleInfo 取消规则信息
     */
    public static void convertSearchPrepayRule(SearchRatePlan ratePlan, SearchPrepayRuleInfo ruleInfo, boolean convertPrepayRuleList) {
        ImmutableTriple<SuffixTypeEnum, String, String> cancelRule = convertCancelRule(ruleInfo);
        List<SearchPrepayRule> prepayRules = new ArrayList<>();
        SearchPrepayRule searchPrepayRule = new SearchPrepayRule();
        searchPrepayRule.setDescription(cancelRule.getRight());
        prepayRules.add(searchPrepayRule);
        ratePlan.setPrepayRules(prepayRules);
        ratePlan.setSuffixName(cancelRule.getLeft().getCode());
        ratePlan.setZwqxsj(cancelRule.getMiddle());

        // 处理取消规则列表，有传取消规则描述的暂时先不处理，避免描述跟列表不一致造成歧义
        if (convertPrepayRuleList && StringUtils.isBlank(ruleInfo.getDesc())) {
            List<HotelLadderDeductionInfo> ladderDeductionInfoList = new ArrayList<>();
            // 免费取消
            SearchLadderDeductionInfo deductionInfoFirst = new SearchLadderDeductionInfo();
            deductionInfoFirst.setHotelLocalTimeZone(ruleInfo.getHotelLocalTimeZone());
            deductionInfoFirst.setDeductionType(HotelDeductionTypeEnum.FREE);
            deductionInfoFirst.setEndDateStr(ruleInfo.getLatestCancelTime());
            deductionInfoFirst.setEndTimeZoneStr(ruleInfo.getLatestCancelTimeZone());
            ladderDeductionInfoList.add(RateSearchCommonUtils.convertDeductTime(deductionInfoFirst));
            // 不可取消
            SearchLadderDeductionInfo deductionInfoLast = new SearchLadderDeductionInfo();
            deductionInfoLast.setDeductionType(HotelDeductionTypeEnum.CANNOT_CANCEL);
            deductionInfoLast.setHotelLocalTimeZone(ruleInfo.getHotelLocalTimeZone());
            deductionInfoLast.setStartDateStr(ruleInfo.getLatestCancelTime());
            deductionInfoLast.setStartTimeZoneStr(ruleInfo.getLatestCancelTimeZone());
            ladderDeductionInfoList.add(RateSearchCommonUtils.convertDeductTime(deductionInfoLast));
            ratePlan.setLadderDeductionInfoList(ladderDeductionInfoList);
        }
    }

    /**
     * @param ruleInfo 取消规则信息
     * @return ImmutableTriple<规则枚举, 最晚取消时间, 规则描述>
     */
    private static ImmutableTriple<SuffixTypeEnum, String, String> convertCancelRule(SearchPrepayRuleInfo ruleInfo) {
        String nowDate = VeDate.getStringDate();
        if (Objects.isNull(ruleInfo) || Objects.isNull(ruleInfo.getSuffixTypeEnum())) {
            return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
        }
        // 国内酒店设置默认时区
        if (!GnGjTypeEnum.GJ.getCode().equals(ruleInfo.getGngj())) {
            if (StringUtils.isBlank(ruleInfo.getLatestCancelTimeZone())) {
                ruleInfo.setLatestCancelTimeZone(HotelTimeZoneEnum.UTC_800.getCode());
            }
            if (StringUtils.isBlank(ruleInfo.getHotelLocalTimeZone())) {
                ruleInfo.setHotelLocalTimeZone(HotelTimeZoneEnum.UTC_800.getCode());
            }
        }

        SuffixTypeEnum suffixTypeEnum = ruleInfo.getSuffixTypeEnum();
        String desc = ruleInfo.getDesc();
        String descTimeZoneType = ruleInfo.getDescTimeZoneType();
        String latestCancelTime = ruleInfo.getLatestCancelTime();
        String latestCancelTimeZone = ruleInfo.getLatestCancelTimeZone();
        String gngj = ruleInfo.getGngj();
        try {
            // 免费取消
            if (SuffixTypeEnum.FREE_CANCEL.equals(suffixTypeEnum)) {
                // 免费取消没有最晚取消时间，只有现付有免费取消，预付没有免费取消
                return ImmutableTriple.of(suffixTypeEnum, null, StringUtils.defaultIfBlank(desc, suffixTypeEnum.getName()));
            } else if (SuffixTypeEnum.TIME_CANCEL.equals(suffixTypeEnum) || SuffixTypeEnum.FEE_CANCEL.equals(suffixTypeEnum)) {
                // 最晚取消时间为空，解析为不可取消
                if (StringUtils.isEmpty(latestCancelTime)) {
                    return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
                }
                // 国内不处理
                if (!GnGjTypeEnum.GJ.getCode().equals(gngj)) {
                    int twoSec = VeDate.compareDate(latestCancelTime, nowDate);
                    // 最晚取消时间在当前时间之前，解析为不可取消
                    if (twoSec <= NumConstant.NUM_0) {
                        return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
                    }
                    return ImmutableTriple.of(suffixTypeEnum, latestCancelTime, StringUtils.defaultIfBlank(desc, String.format(DESCRIPTION_TIME, latestCancelTime)));
                }

                // 国际酒店最晚取消时间转成 北京时间
                String beijingTime = VeDateUtils.sourceTimeZone2BeijingTimeZoneForTimeZoneValue(latestCancelTime, latestCancelTimeZone);
                if (StringUtils.isBlank(beijingTime)) {
                    // 时间没有转换成功，设置为不可取消
                    return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
                }
                int twoSec = VeDate.compareDate(beijingTime, nowDate);
                // 最晚取消时间在当前时间之前，解析为不可取消
                if (twoSec <= NumConstant.NUM_0) {
                    return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
                }
                String name = "";
                if (StringUtils.isNotBlank(desc)) {
                    // 国际酒店取消规则描述处理
                    name = CancelRuleDescTimeZoneTypeEnum.getNameByCode(descTimeZoneType);
                    if (Objects.isNull(name)) {
                        // 无法确定取消规则描述中时间的时区，设置为不可取消
                        return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
                    }
                    return ImmutableTriple.of(suffixTypeEnum, beijingTime, VeStringUtil.joinWithEmptyIsNotBlank(name, desc));
                }
                desc = VeStringUtil.joinWithEmptyIsNotBlank(CancelRuleDescTimeZoneTypeEnum.BEIJING.getName(), String.format(DESCRIPTION_TIME, beijingTime));
                return ImmutableTriple.of(suffixTypeEnum, beijingTime, desc);
            } else {
                //不可取消
                return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
            }
        } catch (Exception e) {
            logger.error("处理取消规则异常:", e);
            //不可取消
            return ImmutableTriple.of(SuffixTypeEnum.NOT_CANCEL, nowDate, SuffixTypeEnum.NOT_CANCEL.getName());
        }
    }

    /**
     * 根据房量设置房态信息
     * 暂时先定义剩余房量大于0小于等于5的房态为 紧张，大于5的房态为充足
     *
     * @param ratePlan  价格计划对象
     * @param roomCount 房间数量
     */
    public static void convertRoomStatusByRoomCount(SearchRatePlan ratePlan, int roomCount) {
        if (roomCount > 99) {
            roomCount = 99;
        }
        ratePlan.setFl(String.valueOf(roomCount));
        ratePlan.setFjsl(String.valueOf(roomCount));
        ratePlan.setSyfjsl(String.valueOf(roomCount));
        //小于1 满房
        if (roomCount < CommonMagicNumber.INT1) {
            convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
            //1-5  紧张
        } else if (roomCount > CommonMagicNumber.INT0 && roomCount < CommonMagicNumber.INT6) {
            convertRoomStatus(ratePlan, RoomStatusEnum.TENSION);
            //大于5 充足
        } else if (roomCount > CommonMagicNumber.INT5) {
            convertRoomStatus(ratePlan, RoomStatusEnum.GOOD);
        }
    }

    /**
     * 根据房量设置间夜价格房态
     *
     * @param nightlyRate 间夜价格对象
     * @param roomCount   房间数量
     */
    public static void convertNightlyRateStatusByRoomCount(SearchNightlyRate nightlyRate, int roomCount) {
        if (roomCount > CommonMagicNumber.INT0) {
            nightlyRate.setStatus(SearchNightlyRateStatusEnum.YES.getCode());
        } else {
            nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
        }
    }

    /**
     * 根据早餐数量设置早餐
     *
     * @param ratePlan 设置早餐
     * @param mealNum  早餐数量
     */
    public static void convertFreeMealByMealNum(SearchRatePlan ratePlan, Integer mealNum) {
        if (Objects.isNull(mealNum)) {
            RateSearchCommonUtils.convertBreakfast(ratePlan, FreeMealEnum.NO);
        }
        if (mealNum < CommonMagicNumber.INT0) {
            RateSearchCommonUtils.convertBreakfast(ratePlan, FreeMealEnum.NO);
        } else if (mealNum <= CommonMagicNumber.INT10) {
            FreeMealEnum mealEnum = FreeMealEnum.instance(String.valueOf(mealNum));
            convertBreakfast(ratePlan, mealEnum);
        } else {
            //大于10显示含早
            convertBreakfast(ratePlan, FreeMealEnum.H);
        }
    }

    /**
     * 处理最大入住人数
     *
     * @param maxOccupancy maxOccupancy
     * @return String
     */
    public static String convertFxCapacity(Integer maxOccupancy) {
        if (Objects.isNull(maxOccupancy) || maxOccupancy <= 0) {
            return null;
        }
        return String.valueOf(maxOccupancy);
    }

    /**
     * 处理最大入住人数
     *
     * @param maxOccupancy maxOccupancy
     * @return String
     */
    public static String convertFxCapacity(String maxOccupancy) {
        if (NumberUtils.toInt(maxOccupancy) <= 0) {
            return null;
        }
        return maxOccupancy;
    }

    /**
     * 初始化SearchRatePlan
     *
     * @param fyEnum 房源枚举
     */
    public static SearchRatePlan initSearchRatePlan(FyEnum fyEnum) {
        SearchRatePlan ratePlan = new SearchRatePlan();
        ratePlan.setBh(fyEnum.getFybh());
        ratePlan.setFymc(fyEnum.getFymc());
        return ratePlan;
    }

    /**
     * 初始化SearchRoom
     *
     * @param fyEnum 房源枚举
     */
    public static SearchRoom initSearchRoom(FyEnum fyEnum) {
        SearchRoom room = new SearchRoom();
        room.setBh(fyEnum.getFybh());
        room.setFymc(fyEnum.getFymc());
        return room;
    }

    /**
     * 判断是否凌晨房
     * 当请求的入住日期＜当前日期
     *
     * @param checkInDate 入住日期
     * @return true 凌晨预订 false 不是凌晨预订
     */
    public static boolean isEarlyMorningRoom(String checkInDate) {
        int twoDay = VeDate.getTwoDay(checkInDate, VeDate.getStringDateShort());
        return twoDay < CommonMagicNumber.INT0;
    }

    /**
     * 处理取消规则
     *
     * @param ratePlan             价格计划
     * @param gngj                 国内国际
     * @param latestCancelTimeZone 取消时间时区
     * @param desc                 取消规则描述
     * @param hotelLocalTimezone   酒店当地时区
     */
    public static void dealTimezonePrepayRule(SearchRatePlan ratePlan, String gngj, String latestCancelTimeZone, String desc, String hotelLocalTimezone) {
        if (!StringUtils.equalsIgnoreCase(gngj, GnGjTypeEnum.GJ.getCode())) {
            return;
        }
        if (StringUtils.isBlank(latestCancelTimeZone)) {
            RateSearchCommonUtils.convertSearchPrepayRule(ratePlan, SuffixTypeEnum.NOT_CANCEL, null, null);
            return;
        }
        String zwqxsj = ratePlan.getZwqxsj();
        zwqxsj = StringUtils.defaultIfBlank(zwqxsj, VeDate.getStringDate());

        SearchPrepayRuleInfo ruleInfo = new SearchPrepayRuleInfo();
        ruleInfo.setSuffixTypeEnum(SuffixTypeEnum.TIME_CANCEL);
        ruleInfo.setLatestCancelTime(zwqxsj);
        ruleInfo.setLatestCancelTimeZone(latestCancelTimeZone);
        ruleInfo.setGngj(gngj);
        ruleInfo.setDescTimeZoneType(desc);
        ruleInfo.setHotelLocalTimeZone(hotelLocalTimezone);
        //时区
        convertSearchPrepayRule(ratePlan, ruleInfo);
    }

    /**
     * 校验预定规则是否可定
     *
     * @param checkIn  入住日期
     * @param checkOut 离店日期
     * @param ruleExt  标准预定规则
     * @param ratePlan 价格计划
     * @return ImmutablePair<是否可定true-可订 false-不可订, 不可订原因>
     */
    public static ImmutablePair<Boolean, String> checkBookingRule(String checkIn, String checkOut, BookingRuleExt ruleExt, SearchRatePlan ratePlan) {
        if (Objects.isNull(ruleExt) || Objects.isNull(ratePlan) || StringUtils.isAnyBlank(checkIn, checkOut)) {
            return ImmutablePair.of(true, null);
        }
        List<SearchBookingRule> bookingRules = new ArrayList<>();
        SearchBookingRule bookingRule = new SearchBookingRule();
        List<String> bookingNoticesList = new ArrayList<>();
        String nowTime = VeDate.getStringDate();//查询时间 yyyy-MM-dd HH:mm:ss
        //1、开始日期 报价生效时间 yyyy-MM-dd HH:mm:ss 为空表示无限制  (下单日期 >= 报价生效时间 可订)
        if (StringUtils.isNotBlank(ruleExt.getStartDate())) {
            String startDate = ruleExt.getStartDate();
            int sDay = VeDate.getTwoSec(nowTime, startDate);
            if (sDay < CommonMagicNumber.INT0) {
                return ImmutablePair.of(false, "报价生效时间校验失败");
            }
            bookingNoticesList.add(String.format("报价生效时间：%s", startDate));
            ratePlan.setStartTime(startDate);
            bookingRule.setStartDate(startDate);
        }
        //2、结束日期 报价结束时间 yyyy-MM-dd HH:mm:ss 为空表示无限制 (下单日期<=报价结束时间 可订)
        if (StringUtils.isNotBlank(ruleExt.getEndDate())) {
            String endDate = ruleExt.getEndDate();
            int sDay = VeDate.getTwoSec(nowTime, endDate);
            if (sDay > CommonMagicNumber.INT0) {
                return ImmutablePair.of(false, "报价结束时间校验失败");
            }
            bookingNoticesList.add(String.format("报价结束时间：%s", endDate));
            ratePlan.setEndTime(endDate);
            bookingRule.setEndDate(endDate);
        }
        //连住天数 = 离店日期-入住日期
        int lzDay = VeDate.getTwoDay(checkOut, checkIn);
        //3、最小连住天数 为空或不大于0 表示无限制  (连住天数>=最小连续入住 可订)
        if (Objects.nonNull(ruleExt.getMinDays()) && ruleExt.getMinDays() > CommonMagicNumber.INT0) {
            if (lzDay < ruleExt.getMinDays()) {
                return ImmutablePair.of(false, "最小连住天数校验失败");
            }
            bookingNoticesList.add(String.format("最小连住%s天", ruleExt.getMinDays()));
            ratePlan.setMinDays(String.valueOf(ruleExt.getMinDays()));
            bookingRule.setContinuousdays(String.valueOf(ruleExt.getMinDays()));
        }
        //4、最大连住天数 为空或不大于0 表示无限制  (连住天数<=最大连住天数 可订)
        if (Objects.nonNull(ruleExt.getMaxDays()) && ruleExt.getMaxDays() > CommonMagicNumber.INT0) {
            if (lzDay > ruleExt.getMaxDays()) {
                return ImmutablePair.of(false, "最大连住天数校验失败");
            }
            bookingNoticesList.add(String.format("最大连住%s天", ruleExt.getMaxDays()));
            ratePlan.setMaxDays(String.valueOf(ruleExt.getMaxDays()));
        }
        //提前天数 = 入住日期 - 下单日期
        int tqDay = VeDate.getTwoDay(checkIn, nowTime);
        //5、最小提前天数   (提前天数 >= 最小提前预订 可订)
        if (Objects.nonNull(ruleExt.getMinBookUnit()) && ruleExt.getMinBookUnit() > CommonMagicNumber.INT0) {
            if (tqDay < ruleExt.getMinBookUnit()) {
                return ImmutablePair.of(false, "最小提前天数校验失败");
            }
            bookingNoticesList.add(String.format("最小提前%s天预订", ruleExt.getMinBookUnit()));
            bookingRule.setAdvancedays(String.valueOf(ruleExt.getMinBookUnit()));
        }
        //6、最大提前天数 0表示只能当天   (提前天数 <= 最大提前预订 可订)
        if (Objects.nonNull(ruleExt.getMaxBookUnit()) && ruleExt.getMaxBookUnit() >= CommonMagicNumber.INT0) {
            if (tqDay > ruleExt.getMaxBookUnit()) {
                return ImmutablePair.of(false, "最大提前天数校验失败");
            }
            bookingNoticesList.add(String.format("最大提前%s天预订", ruleExt.getMaxBookUnit()));
        }
        //7、最小预定房间数
        if (Objects.nonNull(ruleExt.getMinAmount()) && ruleExt.getMinAmount() > CommonMagicNumber.INT0) {
            ratePlan.setMinAmount(ruleExt.getMinAmount().toString());
            bookingRule.setContinuousfjs(ruleExt.getMinAmount().toString());
            bookingNoticesList.add(String.format("最少预定%s间", ruleExt.getMinAmount()));
        }
        //8、最大预定房间数
        if (Objects.nonNull(ruleExt.getMaxAmount()) && ruleExt.getMaxAmount() > CommonMagicNumber.INT0) {
            ratePlan.setMaxAmount(ruleExt.getMaxAmount().toString());
            bookingNoticesList.add(String.format("最多预定%s间", ruleExt.getMaxAmount()));
        }
        //预定规则描述 优先传值 其次按照预定规则解析
        if (StringUtils.isNotBlank(ruleExt.getBookingNotices())) {
            bookingRule.setDescription(ruleExt.getBookingNotices());
        } else {
            if (CollectionUtils.isNotEmpty(bookingNoticesList)) {
                bookingRule.setDescription(StringUtils.join(bookingNoticesList, SymbolConstant.SEMICOLON));
            }
        }
        bookingRules.add(bookingRule);
        ratePlan.setBookingRules(bookingRules);
        return ImmutablePair.of(true, null);
    }

    public static HotelLadderDeductionInfo convertDeductTime(SearchLadderDeductionInfo ladderDeductionInfo) {
        if (Objects.isNull(ladderDeductionInfo) || Objects.isNull(ladderDeductionInfo.getDeductionType())) {
            return null;
        }
        HotelDeductionTypeEnum deductionType = ladderDeductionInfo.getDeductionType();
        HotelLadderDeductionInfo info = new HotelLadderDeductionInfo();
        info.setDeductionType(deductionType.getCode());
        String startDateStr = ladderDeductionInfo.getStartDateStr();
        String startTimeZoneStr = ladderDeductionInfo.getStartTimeZoneStr();
        String endDateStr = ladderDeductionInfo.getEndDateStr();
        String endTimeZoneStr = ladderDeductionInfo.getEndTimeZoneStr();
        FeeInfo originPrice = ladderDeductionInfo.getOriginPrice();
        String nights = ladderDeductionInfo.getNights();
        String percent = ladderDeductionInfo.getPercent();
//        if (HotelDeductionTypeEnum.FREE.equals(deductionType) && StringUtils.isBlank(startDateStr)) {
//            startDateStr = "1970-01-01 08:00:00";
//            startTimeZoneStr = endTimeZoneStr;
//        }
//        if (HotelDeductionTypeEnum.CANNOT_CANCEL.equals(deductionType) && StringUtils.isBlank(endDateStr)) {
//            endDateStr = "9999-12-31 23:59:59";
//            endTimeZoneStr = startTimeZoneStr;
//        }
        // 原始时间
        info.setOriginalStartDeductTime(convertHotelTimeInfo(startDateStr, startTimeZoneStr));
        info.setOriginalEndDeductTime(convertHotelTimeInfo(endDateStr, endTimeZoneStr));
        // 转 北京时间
        String beijingStartTime = VeDateUtils.sourceTimeZone2BeijingTimeZoneForTimeZoneValue(startDateStr, startTimeZoneStr);
        String beijingEndTime = VeDateUtils.sourceTimeZone2BeijingTimeZoneForTimeZoneValue(endDateStr, endTimeZoneStr);
        info.setBeijingStartDeductTime(convertHotelTimeInfo(beijingStartTime, HotelTimeZoneEnum.UTC_800.getCode()));
        info.setBeijingEndDeductTime(convertHotelTimeInfo(beijingEndTime, HotelTimeZoneEnum.UTC_800.getCode()));
        // 转 酒店当地时间
        if (StringUtils.isNotBlank(ladderDeductionInfo.getHotelLocalTimeZone())) {
            String hotelLocalTimeZone = ladderDeductionInfo.getHotelLocalTimeZone();
            String LocalStartTime = VeDateUtils.sourceTimeZone2TargetTimeZoneForTimeZoneValue(beijingStartTime, "yyyy-MM-dd HH:mm:ss", HotelTimeZoneEnum.UTC_800.getCode(), hotelLocalTimeZone);
            String LocalEndTime = VeDateUtils.sourceTimeZone2TargetTimeZoneForTimeZoneValue(beijingEndTime, "yyyy-MM-dd HH:mm:ss", HotelTimeZoneEnum.UTC_800.getCode(), hotelLocalTimeZone);
            info.setLocalStartDeductTime(convertHotelTimeInfo(LocalStartTime, hotelLocalTimeZone));
            info.setLocalEndDeductTime(convertHotelTimeInfo(LocalEndTime, hotelLocalTimeZone));
        }
        if (Objects.nonNull(originPrice)) {
            info.setOriginPrice(originPrice);
        } else if (StringUtils.isNotBlank(nights)) {
            info.setNights(nights);
        } else if (StringUtils.isNotBlank(percent)) {
            info.setPercent(percent);
        }
        if (Objects.isNull(info.getOriginalStartDeductTime()) && Objects.isNull(info.getOriginalEndDeductTime())) {
            return null;
        }
        return info;
    }

    private static HotelTimeInfo convertHotelTimeInfo(String time, String timeZone) {
        if (StringUtils.isAnyBlank(time, timeZone)) {
            return null;
        }
        HotelTimeInfo hotelTimeInfo = new HotelTimeInfo();
        hotelTimeInfo.setTime(time);
        hotelTimeInfo.setTimeZone(timeZone);
        return hotelTimeInfo;
    }

    /**
     * 设置国际酒店推荐入住人数为请求的成人数
     *
     * @param vo  vo
     * @param dto dto
     */
    public static void setIntlRatePlanCapacity(LinkHotelRateSearchVO vo, LinkHotelRateSearchDTO dto) {
        String adult = dto.getAdult();
        if (NumberUtils.toInt(adult, NumConstant.NUM_0) == NumConstant.NUM_0) {
            return;
        }
        GnGjTypeEnum gngjEnum = GnGjTypeEnum.getGngjEnum(dto.getGngj(), dto.getLocalHotelId());
        if (Objects.isNull(gngjEnum) || !StringUtils.equalsIgnoreCase(gngjEnum.getCode(), GnGjTypeEnum.GJ.getCode())) {
            return;
        }
        if (Objects.isNull(vo) || CollectionUtils.isEmpty(vo.getRooms())) {
            return;
        }
        List<SearchRoom> rooms = vo.getRooms();
        for (SearchRoom room : rooms) {
            if (Objects.isNull(room) || CollectionUtils.isEmpty(room.getRatePlans())) {
                continue;
            }
            List<SearchRatePlan> ratePlans = room.getRatePlans();
            for (SearchRatePlan ratePlan : ratePlans) {
                if (StringUtils.isNotBlank(ratePlan.getFxCapacity())) {
                    continue;
                }
                ratePlan.setFxCapacity(adult);
            }
        }
    }

    /**
     * 设置价格最大入住人数
     *
     * @param ratePlan 价格
     * @param capacity 最大入住人数
     */
    public static void setRatePlanCapacity(SearchRatePlan ratePlan, Integer capacity) {
        String capacityValue = convertFxCapacity(capacity);
        if (StringUtils.isBlank(capacityValue)) {
            return;
        }
        ratePlan.setFxCapacity(capacityValue);
    }

    /**
     * 处理价格计划id，使用#替换$，#为供应商提供
     *
     * @param ratePlanId ratePlanId
     * @return String
     */
    public static String replaceRatePlanId(String ratePlanId) {
        if (StringUtils.contains(ratePlanId, SymbolConstant.DOLLAR_SIGN)) {
            ratePlanId = StringUtils.replace(ratePlanId, SymbolConstant.DOLLAR_SIGN, SymbolConstant.UNION);
        }
        return ratePlanId;
    }

    /**
     * 还原价格计划id
     *
     * @param ratePlanId ratePlanId
     * @return String
     */
    public static String restoreRatePlanId(String ratePlanId) {
        if (StringUtils.contains(ratePlanId, SymbolConstant.UNION)) {
            ratePlanId = StringUtils.replace(ratePlanId, SymbolConstant.UNION, SymbolConstant.DOLLAR_SIGN);
        }
        return ratePlanId;
    }

}