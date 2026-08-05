package cn.vetech.center.hotel.link.supply.service.exchangerate;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.AdditionalChargeEnum;
import cn.vetech.center.hotel.link.api.enums.ChargeFrequenceEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.enums.RoomStatusEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchAdditional;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.client.exchangerate.charge.ChargeExchangeRateServiceClient;
import cn.vetech.center.hotel.link.client.exchangerate.cps.CpsExchangeRateServiceClient;
import cn.vetech.center.hotel.link.client.exchangerate.cps.vo.VeZgyhhlVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.CurrencyEnum;
import cn.vetech.center.hotel.link.supply.cps.exchangerate.CpsExchangeRateService;
import cn.vetech.center.hotel.link.supply.cps.exchangerate.response.ExchangeRateResponse;
import cn.vetech.center.hotel.link.util.CollectUtils;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.base.feign.currencyrate.dto.GetCurrencyRateCalcDTO;
import cn.vetech.charge.base.feign.currencyrate.vo.GetCurrencyRateCalcVO;
import cn.vetech.charge.cloud.modules.utils.collection.MapUtil;
import cn.vetech.charge.cloud.modules.utils.localcache.LocalCacheUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.number.BigDecimalUtil;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import com.google.common.cache.Cache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * @author xiaotengyu
 * @since 2024-01-03 11:43
 */
@Service
public class ExchangeRateService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 使用本地缓存 cps
     */
    private final Cache<String, Optional<VeZgyhhlVO>> cpsCurrencyCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_10, NumConstant.NUM_10, TimeUnit.MINUTES);

    /**
     * 使用本地缓存 charge
     */
    private final Cache<String, Optional<GetCurrencyRateCalcVO>> chargeCurrencyCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_10, NumConstant.NUM_10, TimeUnit.MINUTES);

    /**
     * 费控汇率
     */
    @Autowired
    private ChargeExchangeRateServiceClient exchangeRateServiceClient;
    /**
     * cps 汇率
     */
    @Autowired
    private CpsExchangeRateServiceClient cpsExchangeRateServiceClient;
    /**
     * 获取cps 汇率
     */
    @Autowired
    private CpsExchangeRateService cpsExchangeRateService;

    /**
     * 转换币种为人民币
     */
    public void dealCurrencyExchangeRate(LinkHotelRateSearchDTO dto, LinkHotelRateSearchVO vo) {
        // 平台为空时不处理
        if (StringUtils.isBlank(dto.getPt())) {
            return;
        }
        List<SearchRoom> rooms = vo.getRooms();
        if (CollectionUtils.isEmpty(rooms)) {
            return;
        }
        // 转换房费币种
        convertCurrencyExchangeRate(dto, rooms);
        // 处理附加费币种转换
        convertCurrencyExchangeAdditional(dto, rooms);
    }
  /**
     * @param dto   dto
     * @param rooms rooms
     */
    public void convertCurrencyExchangeRate(LinkHotelDTO dto, List<SearchRoom> rooms) {

        Set<String> currencySet = rooms.stream()
                .filter(item -> CollectionUtils.isNotEmpty(item.getRatePlans()))
                .flatMap(roomItem -> roomItem.getRatePlans().stream()).map(SearchRatePlan::getCurrencyCode)
                .filter(StringUtils::isNotBlank)
                .filter(item -> !StringUtils.equalsAnyIgnoreCase(item, CurrencyEnum.RMB.getCurrency(), CurrencyEnum.CNY.getCurrency()))
                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(currencySet)) {
            return;
        }
        HashMap<String, BigDecimal> currencyMap = new HashMap<>();
        for (String currency : currencySet) {
            BigDecimal currentExchangeRate = getCurrentExchangeRate(dto, currency);
            if (Objects.nonNull(currentExchangeRate) && currentExchangeRate.compareTo(BigDecimal.ZERO) != NumConstant.NUM_0) {
                currencyMap.put(currency, currentExchangeRate);
            }
        }
            //币种字段为空，或者为人民币则不处理
        for (SearchRoom room : rooms) {
            List<SearchRatePlan> ratePlans = room.getRatePlans();
            if (CollectionUtils.isEmpty(ratePlans)) {
                continue;
            }
            for (SearchRatePlan ratePlan : ratePlans) {
                if (StringUtils.isBlank(ratePlan.getCurrencyCode())
                        || StringUtils.equalsIgnoreCase(ratePlan.getCurrencyCode(), CurrencyEnum.RMB.getCurrency()) ||
                        StringUtils.equalsIgnoreCase(ratePlan.getCurrencyCode(), CurrencyEnum.CNY.getCurrency())) {
                    continue;
                }
                BigDecimal currentExchangeRate = currencyMap.get(ratePlan.getCurrencyCode());
                if (Objects.isNull(currentExchangeRate)) {
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    continue;
                }
                transExchangeRate(ratePlan, currentExchangeRate);
            }
        }
    }
      /**
     * @param dto   dto
     * @param rooms rooms
     */
    public void convertCurrencyExchangeAdditional(LinkHotelRateSearchDTO dto, List<SearchRoom> rooms) {
        Map<String, String> config = dto.getSupplier();
        if (MapUtil.isEmpty(config)) {
            return;
        }
        // 是否查询汇率， 0不查，1查，默认查
        String sfcxhl = config.get("sfcxhl");
        Map<String, BigDecimal> currencyAndExchangeRateMap = new HashMap<>();
        if (!"0".equals(sfcxhl)) {
            // 根据币种获取汇率
            currencyAndExchangeRateMap = getCurrencyAndExchange(dto, rooms);
        }
        for (SearchRoom room : rooms) {
            if (CollectionUtils.isEmpty(room.getRatePlans())) {
                continue;
            }
            for (SearchRatePlan ratePlan : room.getRatePlans()) {
                // 处理到店付税费
                convertEwsf(ratePlan, dto, currencyAndExchangeRateMap, sfcxhl);
                // 处理预付附加费
                convertSf(ratePlan, dto, currencyAndExchangeRateMap, sfcxhl);
            }
        }
    }

      /**
     * @param dto   dto
     * @param rooms rooms
     */
    public void convertCurrencyExchangeAdditional(LinkHotelRateSearchDTO dto, List<SearchRoom> rooms) {
        Map<String, String> config = dto.getSupplier();
        if (MapUtil.isEmpty(config)) {
            return;
        }
        // 是否查询汇率， 0不查，1查，默认查
        String sfcxhl = config.get("sfcxhl");
        Map<String, BigDecimal> currencyAndExchangeRateMap = new HashMap<>();
        if (!"0".equals(sfcxhl)) {
            // 根据币种获取汇率
            currencyAndExchangeRateMap = getCurrencyAndExchange(dto, rooms);
        }
        for (SearchRoom room : rooms) {
            if (CollectionUtils.isEmpty(room.getRatePlans())) {
                continue;
            }
            for (SearchRatePlan ratePlan : room.getRatePlans()) {
                // 处理到店付税费
                convertEwsf(ratePlan, dto, currencyAndExchangeRateMap, sfcxhl);
                // 处理预付附加费
                convertSf(ratePlan, dto, currencyAndExchangeRateMap, sfcxhl);
            }
        }
    }

       /**
     * @param ratePlan                   ratePlan
     * @param dto                        dto
     * @param currencyAndExchangeRateMap currencyAndExchangeRateMap
     * @param additionalList             additionalList
     */
    private void convertSfhj(SearchRatePlan ratePlan, LinkHotelRateSearchDTO dto, Map<String, BigDecimal> currencyAndExchangeRateMap, List<SearchAdditional> additionalList) {
        if (Objects.nonNull(ratePlan.getSfhj())) {
            return;
        }
        // 根据汇率转人民币
        BigDecimal ewsfhj = BigDecimal.ZERO;
        // 优先取附加费合计
        if (StringUtils.isNotBlank(ratePlan.getGysyssfhj()) && StringUtils.isNotBlank(ratePlan.getGysyssfbz())) {
            String fee = ratePlan.getGysyssfhj();
            String currency = ratePlan.getGysyssfbz();
            if (StringUtils.equalsIgnoreCase(currency, CurrencyEnum.CNY.getCurrency())) {
                ewsfhj = ewsfhj.add(BigDecimalUtil.toBigDecimal(fee));
            } else {
                // 获取汇率
                BigDecimal currentExchangeRate = currencyAndExchangeRateMap.get(currency);
                if (Objects.isNull(currentExchangeRate)) {
                    return;
                }
                String transPrice = transPrice(fee, currentExchangeRate);
                if (StringUtils.isBlank(transPrice)) {
                    return;
                }
                ewsfhj = ewsfhj.add(BigDecimalUtil.toBigDecimal(transPrice));
            }
        } else if (CollectionUtils.isNotEmpty(additionalList)) {
            for (SearchAdditional additional : additionalList) {
                String currency = additional.getCurrency();
                if (StringUtils.isBlank(currency)) {
                    continue;
                }
                // 根据收费频率计算税费合计
                BigDecimal additionalAmount = convertAdditionalAmount(additional, dto);
                if (Objects.isNull(additionalAmount)) {
                    continue;
                }
                if (StringUtils.equalsIgnoreCase(currency, CurrencyEnum.CNY.getCurrency())) {
                    ewsfhj = ewsfhj.add(additionalAmount);
                    continue;
                }
                // 获取汇率
                BigDecimal currentExchangeRate = currencyAndExchangeRateMap.get(currency);
                if (Objects.isNull(currentExchangeRate)) {
                    continue;
                }
                String transPrice = transPrice(additionalAmount.toString(), currentExchangeRate);
                if (StringUtils.isBlank(transPrice)) {
                    continue;
                }
                ewsfhj = ewsfhj.add(BigDecimalUtil.toBigDecimal(transPrice));
            }
        }
        if (ewsfhj.compareTo(BigDecimal.ZERO) > 0) {
            ratePlan.setSfhj(BigDecimalUtil.toPlainString(ewsfhj));
        }
    }

   /**
     * @param ratePlan                   ratePlan
     * @param dto                        dto
     * @param currencyAndExchangeRateMap currencyAndExchangeRateMap
     */
    private void convertEwsf(SearchRatePlan ratePlan, LinkHotelRateSearchDTO dto, Map<String, BigDecimal> currencyAndExchangeRateMap, String sfcxhl) {
        // 到店付税费
        List<SearchAdditional> additionalList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ratePlan.getAdditionals())) {
            additionalList = ratePlan.getAdditionals().stream()
                    .filter(a -> AdditionalChargeEnum.EXCLUDED.getCode().equals(a.getCharge()))
                    .collect(Collectors.toList());
        }
        // 处理供应商原始附加费合计
        convertTotalAdditionalFee(ratePlan, dto, additionalList);
        if (!"0".equals(sfcxhl)) {
            // 处理附加费合计，币种人名币，单位元
            convertEwsfhj(ratePlan, currencyAndExchangeRateMap, dto, additionalList);
        }
        // 税费说明
        if (StringUtils.isBlank(ratePlan.getEwsfsm()) && CollectionUtils.isNotEmpty(additionalList)) {
            List<String> ewsfsmList = additionalList.stream().map(SearchAdditional::getComments).collect(Collectors.toList());
            ratePlan.setEwsfsm(VeStringUtil.joinIfNotBlank(SymbolConstant.FULL_COMMA, ewsfsmList));
        }
        // cps展示的税费说明
        if (StringUtils.isBlank(ratePlan.getCpsewsfsm()) && CollectionUtils.isNotEmpty(additionalList)) {
            List<String> cpsewsfsmList = additionalList.stream().map(SearchAdditional::getCpsComments).collect(Collectors.toList());
            ratePlan.setCpsewsfsm(VeStringUtil.joinIfNotBlank(SymbolConstant.FULL_COMMA, cpsewsfsmList));
        }
    }

      /**
     * @param ratePlan       ratePlan
     * @param dto            dto
     * @param additionalList additionalList
     */
    private void convertGysyssfhj(SearchRatePlan ratePlan, LinkHotelRateSearchDTO dto, List<SearchAdditional> additionalList) {
        if (StringUtils.isNotBlank(ratePlan.getGysyssfhj()) || CollectionUtils.isEmpty(additionalList)) {
            return;
        }
        List<FeeInfo> additionalFeeInfoList = new ArrayList<>();
        for (SearchAdditional additional : additionalList) {
            String currency = additional.getCurrency();
            if (StringUtils.isBlank(currency)) {
                continue;
            }
            // 根据收费频率计算税费合计
            BigDecimal additionalAmount = convertAdditionalAmount(additional, dto);
            if (Objects.isNull(additionalAmount)) {
                continue;
            }
            FeeInfo feeInfo = new FeeInfo();
            feeInfo.setFee(additionalAmount);
            feeInfo.setCurrency(currency);
            feeInfo.setCurrencyName(CurrencyEnum.getCurrencyName(currency));
            additionalFeeInfoList.add(feeInfo);
        }
         if (CollectionUtils.isEmpty(additionalFeeInfoList)) {
            return;
        }
        Map<String, BigDecimal> additionalFeeInfoMap = additionalFeeInfoList.stream()
                .collect(Collectors.groupingBy(FeeInfo::getCurrency, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
                        .map(FeeInfo::getFee)
                        .reduce(BigDecimal::add).orElse(null))));

        List<FeeInfo> totalAdditionalFeeList = new ArrayList<>();
        additionalFeeInfoMap.forEach((key, value) -> {
            FeeInfo feeInfo = new FeeInfo();
            feeInfo.setCurrency(key);
            feeInfo.setFee(value);
            totalAdditionalFeeList.add(feeInfo);
        });

        // 预付税费必须跟查询报价入参的币种保持一致
        if (CollectionUtils.size(totalAdditionalFeeList) != NumConstant.NUM_1) {
            return;
        }
        FeeInfo feeInfo = totalAdditionalFeeList.get(0);
        ratePlan.setGysyssfhj(BigDecimalUtil.toPlainString(feeInfo.getFee()));
        ratePlan.setGysyssfbz(feeInfo.getCurrency());
    }
/**
     * 根据币种获取汇率
     *
     * @param rooms rooms
     * @return Map<String, BigDecimal>
     */
    private Map<String, BigDecimal> getCurrencyAndExchange(LinkHotelRateSearchDTO dto, List<SearchRoom> rooms) {
        // 收集附加费中除CNY外所有币种
        Set<String> additionalCurrencySet = rooms.stream()
                .filter(room -> CollectionUtils.isNotEmpty(room.getRatePlans()))
                .flatMap(room -> room.getRatePlans().stream()
                        .flatMap(plan -> {
                            Set<String> currencySet = new HashSet<>();
                            CollectUtils.handleEachIfNotEmpty(plan.getTotalAdditionalFeeList(), obj -> {
                                currencySet.add(obj.getCurrency());
                            });
                            CollectUtils.handleEachIfNotEmpty(plan.getAdditionals(), obj -> {
                                currencySet.add(obj.getCurrency());
                            });
                            return currencySet.stream();
                        }))
                .filter(c -> StringUtils.isNotBlank(c) && !StringUtils.equalsIgnoreCase(c, CurrencyEnum.CNY.getCurrency())).collect(Collectors.toSet());
        // 根据币种获取汇率
        Map<String, BigDecimal> currencyAndExchangeRateMap = new HashMap<>();
        if (CollectionUtils.isEmpty(additionalCurrencySet)) {
            return currencyAndExchangeRateMap;
        }
        for (String currency : additionalCurrencySet) {
            // 获取汇率
            BigDecimal currentExchangeRate = getCurrentExchangeRate(dto, currency);
            if (Objects.nonNull(currentExchangeRate)) {
                currencyAndExchangeRateMap.put(currency, currentExchangeRate);
            }
        }
        return currencyAndExchangeRateMap;
    }

      /**
     * 处理价格计划附加费
     *
     * @param ratePlan                   ratePlan
     * @param currencyAndExchangeRateMap currencyAndExchangeRateMap
     * @param dto                        dto
     * @param additionalList             additionalList
     */
    private void convertEwsfhj(SearchRatePlan ratePlan, Map<String, BigDecimal> currencyAndExchangeRateMap, LinkHotelRateSearchDTO dto, List<SearchAdditional> additionalList) {
        if (Objects.nonNull(ratePlan.getEwsfhj())) {
            return;
        }
        // 根据汇率转人民币
        BigDecimal ewsfhj = BigDecimal.ZERO;
        // 优先取附加费合计
        if (CollectionUtils.isNotEmpty(ratePlan.getTotalAdditionalFeeList())) {
            for (FeeInfo feeInfo : ratePlan.getTotalAdditionalFeeList()) {
                String currency = feeInfo.getCurrency();
                BigDecimal fee = feeInfo.getFee();
                if (StringUtils.isBlank(currency) || Objects.isNull(fee)) {
                    logger.warn("附加费币种或金额为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                    // 币种为空，房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                if (StringUtils.equalsIgnoreCase(currency, CurrencyEnum.CNY.getCurrency())) {
                    ewsfhj = ewsfhj.add(fee);
                    continue;
                }
                      // 获取汇率
                BigDecimal currentExchangeRate = currencyAndExchangeRateMap.get(currency);
                // 获取不到汇率，房态置为满房
                if (Objects.isNull(currentExchangeRate)) {
                    logger.warn("币种【{}】获取汇率为空，价格计划【{}】房态置为满房", currency, ratePlan.getRatePlanId());
                    // 房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                String transPrice = transPrice(fee.toString(), currentExchangeRate);
                if (StringUtils.isBlank(transPrice)) {
                    logger.warn("根据汇率转换价格为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                    // 房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                ewsfhj = ewsfhj.add(new BigDecimal(transPrice));
            }
            } else if (CollectionUtils.isNotEmpty(additionalList)) {
            for (SearchAdditional additional : additionalList) {
                String currency = additional.getCurrency();
                if (StringUtils.isBlank(currency)) {
                    logger.warn("附加费币种为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                    // 币种为空，房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                // 根据收费频率计算税费合计
                BigDecimal additionalAmount = convertAdditionalAmount(additional, dto);
                if (Objects.isNull(additionalAmount)) {
                    logger.warn("附加费合计计算为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                    // 房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                if (StringUtils.equalsIgnoreCase(currency, CurrencyEnum.CNY.getCurrency())) {
                    ewsfhj = ewsfhj.add(additionalAmount);
                    continue;
                }
                // 获取汇率
                  BigDecimal currentExchangeRate = currencyAndExchangeRateMap.get(currency);
                // 获取不到汇率，房态置为满房
                if (Objects.isNull(currentExchangeRate)) {
                    logger.warn("币种【{}】获取汇率为空，价格计划【{}】房态置为满房", currency, ratePlan.getRatePlanId());
                    // 房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                String transPrice = transPrice(additionalAmount.toString(), currentExchangeRate);
                if (StringUtils.isBlank(transPrice)) {
                    logger.warn("根据汇率转换价格为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                    // 房态置为满房
                    RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                    return;
                }
                ewsfhj = ewsfhj.add(new BigDecimal(transPrice));
            }
        }
        if (ewsfhj.compareTo(BigDecimal.ZERO) > 0) {
            ratePlan.setEwsfhj(ewsfhj);
        }
    }
    
 /**
     * 处理供应商原始附加费合计
     *
     * @param ratePlan ratePlan
     * @param dto      dto
     */
    private void convertTotalAdditionalFee(SearchRatePlan ratePlan, LinkHotelRateSearchDTO dto, List<SearchAdditional> additionalList) {
        if (CollectionUtils.isNotEmpty(ratePlan.getTotalAdditionalFeeList()) || CollectionUtils.isEmpty(additionalList)) {
            return;
        }
        List<FeeInfo> additionalFeeInfoList = new ArrayList<>();
        for (SearchAdditional additional : additionalList) {
            String currency = additional.getCurrency();
            if (StringUtils.isBlank(currency)) {
                logger.warn("附加费币种为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                // 币种为空，房态置为满房
                RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                return;
            }
            // 根据收费频率计算税费合计
            BigDecimal additionalAmount = convertAdditionalAmount(additional, dto);
            if (Objects.isNull(additionalAmount)) {
                logger.warn("附加费合计计算为空，价格计划【{}】房态置为满房", ratePlan.getRatePlanId());
                // 房态置为满房
                RateSearchCommonUtils.convertRoomStatus(ratePlan, RoomStatusEnum.FULL);
                return;
            }
               FeeInfo feeInfo = new FeeInfo();
            feeInfo.setFee(additionalAmount);
            feeInfo.setCurrency(currency);
            feeInfo.setCurrencyName(CurrencyEnum.getCurrencyName(currency));
            additionalFeeInfoList.add(feeInfo);
        }
        if (CollectionUtils.isEmpty(additionalFeeInfoList)) {
            return;
        }
        Map<String, BigDecimal> additionalFeeInfoMap = additionalFeeInfoList.stream()
                .collect(Collectors.groupingBy(FeeInfo::getCurrency, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
                        .map(FeeInfo::getFee)
                        .reduce(BigDecimal::add).orElse(null))));

        List<FeeInfo> totalAdditionalFeeList = new ArrayList<>();
        additionalFeeInfoMap.forEach((key, value) -> {
            FeeInfo feeInfo = new FeeInfo();
            feeInfo.setCurrency(key);
            feeInfo.setCurrencyName(CurrencyEnum.getCurrencyName(key));
            feeInfo.setFee(value);
            totalAdditionalFeeList.add(feeInfo);
        });
        ratePlan.setTotalAdditionalFeeList(totalAdditionalFeeList);
    }

     /**
     * @param additional additional
     * @param dto        dto
     * @return BigDecimal
     */
    private BigDecimal convertAdditionalAmount(SearchAdditional additional, LinkHotelRateSearchDTO dto) {
        if (StringUtils.isAnyBlank(additional.getAmount(), additional.getChargeFrequence())) {
            return null;
        }
        // 按单收
        if (ChargeFrequenceEnum.PB.getCode().equals(additional.getChargeFrequence())) {
            return new BigDecimal(additional.getAmount());
        }
        // 每天
        if (ChargeFrequenceEnum.PD.getCode().equals(additional.getChargeFrequence())) {
            int twoDay = VeDate.getTwoDay(dto.getCheckOutDate(), dto.getCheckInDate()) + NumConstant.NUM_1;
            return new BigDecimal(additional.getAmount()).multiply(BigDecimal.valueOf(twoDay));
        }
        // 每晚
        if (ChargeFrequenceEnum.PN.getCode().equals(additional.getChargeFrequence())) {
            int twoDay = VeDate.getTwoDay(dto.getCheckOutDate(), dto.getCheckInDate());
            return new BigDecimal(additional.getAmount()).multiply(BigDecimal.valueOf(twoDay));
        }
        // 每人
        if (ChargeFrequenceEnum.PG.getCode().equals(additional.getChargeFrequence())) {
            BigDecimal totalAdult = new BigDecimal(dto.getAdult()).multiply(BigDecimal.valueOf(dto.getFjs()));
            return new BigDecimal(additional.getAmount()).multiply(totalAdult);
        }
        // 每人每晚
        if (ChargeFrequenceEnum.PGPN.getCode().equals(additional.getChargeFrequence())) {
            BigDecimal totalAdult = new BigDecimal(dto.getAdult()).multiply(BigDecimal.valueOf(dto.getFjs()));
            int twoDay = VeDate.getTwoDay(dto.getCheckOutDate(), dto.getCheckInDate());
            return new BigDecimal(additional.getAmount()).multiply(totalAdult).multiply(BigDecimal.valueOf(twoDay));
        }
        return null;
    }

 /**
     * 转换汇率价格
     *
     * @param ratePlan     价格计划
     * @param exchangeRate 汇率
     */
    private void transExchangeRate(SearchRatePlan ratePlan, BigDecimal exchangeRate) {
        if (exchangeRate.compareTo(BigDecimal.ONE) == NumConstant.NUM_0) {
            logger.info("汇率为1");
            return;
        }
//        ratePlan.setFirstPrice(transPrice(ratePlan.getFirstPrice(), exchangeRate));
//        ratePlan.setFirstCostPrice(transPrice(ratePlan.getFirstCostPrice(), exchangeRate));
//        ratePlan.setTotalRate(transPrice(ratePlan.getTotalRate(), exchangeRate));
//        ratePlan.setAverageRate(transPrice(ratePlan.getAverageRate(), exchangeRate));
//        ratePlan.setAverageBaseRate(transPrice(ratePlan.getAverageBaseRate(), exchangeRate));
//        ratePlan.setCurrencyCode(CurrencyEnum.CNY.getCurrency());
        ratePlan.setCurrencyRate(exchangeRate.toString());
        ratePlan.setCurrencyName(CurrencyEnum.getCurrencyName(ratePlan.getCurrencyCode()));
//        ratePlan.setGpj(transPrice(ratePlan.getGpj(), exchangeRate));
//        ratePlan.setZfj(transPrice(ratePlan.getZfj(), exchangeRate));
//        ratePlan.setCgxsj(transPrice(ratePlan.getCgxsj(), exchangeRate));
//        ratePlan.setKzxsj(transPrice(ratePlan.getKzxsj(), exchangeRate));
//        List<SearchNightlyRate> nightlyRates = ratePlan.getNightlyRates();
//        for (SearchNightlyRate nightlyRate : nightlyRates) {
//            nightlyRate.setPriceBeforTax(transPrice(nightlyRate.getPriceBeforTax(), exchangeRate));
//            nightlyRate.setPriceAfterTax(transPrice(nightlyRate.getPriceAfterTax(), exchangeRate));
//            nightlyRate.setMsj(transPrice(nightlyRate.getMsj(), exchangeRate));
//        }
//        ratePlan.setNightlyRates(nightlyRates);
    }

    /**
     * 转换价格
     *
     * @param price        价格
     * @param exchangeRate 汇率
     * @return 转换后价格
     */
    public String transPrice(String price, BigDecimal exchangeRate) {
        if (StringUtils.isBlank(price) || Objects.isNull(exchangeRate)) {
            return null;
        }
        if (exchangeRate.compareTo(BigDecimal.ONE) == NumConstant.NUM_0) {
            return price;
        }
        return NumberUtils.toScaledBigDecimal(price).multiply(exchangeRate).setScale(NumConstant.NUM_2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 换行价格
     *
     * @param price        价格
     * @param exchangeRate 汇率
     * @return 转换后价格
     */
    private Double transPrice(Double price, BigDecimal exchangeRate) {
        if (Objects.isNull(price)) {
            return price;
        }
        if (Objects.isNull(exchangeRate) || exchangeRate.compareTo(BigDecimal.ONE) == NumConstant.NUM_0) {
            return price;
        }
        return NumberUtils.toScaledBigDecimal(price).multiply(exchangeRate).setScale(NumConstant.NUM_2, RoundingMode.HALF_UP).doubleValue();
    }

       /**
     * 获取当前汇率
     *
     * @param dto      dto
     * @param currency 币种
     * @return 汇率
     */
    private BigDecimal getCurrentExchangeRate(LinkHotelDTO dto, String currency) {
        String pt = dto.getPt();
        if (StringUtils.equalsIgnoreCase(pt, PtEnum.CHARGE.getValue())) {
            return getChargeExchangeRate(dto, currency);
        }
        if (StringUtils.equalsIgnoreCase(pt, PtEnum.CPS.getValue())) {
            return getCpsChargeExchangeRate(pt, currency);
        }
        return null;
    }

    /**
     * 获取费控汇率
     *
     * @param dto      dto
     * @param currency 币种
     * @return 汇率
     */
    private BigDecimal getChargeExchangeRate(LinkHotelDTO dto, String currency) {
        if (!StringUtils.equalsIgnoreCase(dto.getPt(), PtEnum.CHARGE.getValue())) {
            return null;
        }
        Optional<GetCurrencyRateCalcVO> chargeCurrencyCalcByCache = getChargeCurrencyCalcByCache(dto, currency);
        return chargeCurrencyCalcByCache.map(GetCurrencyRateCalcVO::getHl).orElse(null);
    }
  /**
     * 获取汇率
     *
     * @param dto      请求dto
     * @param currency 币种
     * @return vo
     */
    private Optional<GetCurrencyRateCalcVO> getChargeCurrencyCalcByCache(LinkHotelDTO dto, String currency) {
        if (StringUtils.isBlank(currency)) {
            return Optional.empty();
        }
        String key = VeStringUtil.joinWithIsNotBlank(SymbolConstant.MIDDLE_LINE, "ve-link-hotel:chargecurrency", currency);
        try {
            Optional<GetCurrencyRateCalcVO> ifPresent = chargeCurrencyCache.getIfPresent(key);
            if (Objects.nonNull(ifPresent) && ifPresent.isPresent()) {
                return ifPresent;
            }
            Optional<GetCurrencyRateCalcVO> opCalcVo = this.getChargeCurrencyCalc(dto, currency);
            if (opCalcVo.isPresent()) {
                chargeCurrencyCache.put(key, opCalcVo);
            }
            return opCalcVo;
        } catch (Exception ex) {
            logger.error("缓存中获取费控汇率异常;currency:{};dto:{}", currency, JacksonUtils.toJsonWithNonNull(dto), ex);
        }
        return Optional.empty();
    }

    /**
     * 获取汇率
     *
     * @param dto      请求dto
     * @param currency 币种
     * @return vo
     */
       private Optional<GetCurrencyRateCalcVO> getChargeCurrencyCalc(LinkHotelDTO dto, String currency) {
        try {
            logger.info("请求费控汇率,币种：{};dto:{}", currency, JsonMapperUtil.toJsonStr(dto));
            GetCurrencyRateCalcDTO calcDTO = new GetCurrencyRateCalcDTO();
            calcDTO.setFrombz(currency);
            calcDTO.setTobz(CurrencyEnum.CNY.getCurrency());
            calcDTO.setRq(VeDate.getStringDateShort());
            calcDTO.setQybh(dto.getCompid());
            calcDTO.setMoney(BigDecimal.ONE);
            RestResponse<GetCurrencyRateCalcVO> currencyRateCalcResponse = exchangeRateServiceClient.getCurrencyRateCalc(calcDTO);
            if (Objects.isNull(currencyRateCalcResponse) || Objects.isNull(currencyRateCalcResponse.getResult())) {
                logger.info("转换币种：charge没有查询到当前币种汇率;currency:{}", currency);
                return Optional.empty();
            }
            logger.info("获取费控费率返回:{}", JsonMapperUtil.toJsonStr(currencyRateCalcResponse));
            GetCurrencyRateCalcVO result = currencyRateCalcResponse.getResult();
            return Optional.of(result);
        } catch (Exception ex) {
            logger.error("获取费控汇率异常;currency:{};dto:{};", currency, JacksonUtils.toJsonWithNonNull(dto), ex);
        }
        return Optional.empty();
    }

    /**
     * 查询cps汇率
     *
     * @param pt       平台
     * @param currency 币种
     * @return 汇率
     */
    public BigDecimal getCpsChargeExchangeRate(String pt, String currency) {

        if (!StringUtils.equalsIgnoreCase(pt, PtEnum.CPS.getValue())) {
            return null;
        }
        Optional<VeZgyhhlVO> cpsCurrencyByCache = getCpsCurrencyByCache(currency);
        return cpsCurrencyByCache
                .map(cur -> NumberUtils.toScaledBigDecimal(cur.getZhesuan(), NumConstant.NUM_4, RoundingMode.HALF_UP).divide(new BigDecimal(NumConstant.NUM_100)))
                .orElse(null);
    }

      /**
     * 获取缓存中汇率
     *
     * @param currency 币种
     * @return op
     */
    private Optional<VeZgyhhlVO> getCpsCurrencyByCache(String currency) {
        if (StringUtils.isBlank(currency)) {
            return Optional.empty();
        }
        String key = VeStringUtil.joinWithIsNotBlank(SymbolConstant.MIDDLE_LINE, "ve-link-hotel:cpscurrency", currency);
        try {
            Optional<VeZgyhhlVO> ifPresent = cpsCurrencyCache.getIfPresent(key);
            if (Objects.nonNull(ifPresent) && ifPresent.isPresent()) {
                return ifPresent;
            }
            Optional<VeZgyhhlVO> opVeZgyVo = this.getCpsCurrencyCalc(currency);
            if (opVeZgyVo.isPresent()) {
                cpsCurrencyCache.put(key, opVeZgyVo);
            }
            return opVeZgyVo;
        } catch (Exception ex) {
            logger.error("缓存中获取cps汇率异常;currency:【{}】", currency, ex);
        }
        return Optional.empty();
    }

    /**
     * 获取汇率
     *
     * @param currency 币种
     * @return vo
     */
     private Optional<VeZgyhhlVO> getCpsCurrencyCalc(String currency) {
        try {
            logger.info("请求CPS汇率，币种：{}", currency);
            RestResponse<List<VeZgyhhlVO>> zghyhlResponse = cpsExchangeRateServiceClient.getZghyhl(currency, VeDate.getStringDateShort());
            CommonLogContext.addCommonLogifPresent("请求fiance获取汇率,币种:" + currency + "返回的币种信息：" + JacksonUtils.toJsonWithNonNull(zghyhlResponse));
            if (Objects.isNull(zghyhlResponse) || CollectionUtils.isEmpty(zghyhlResponse.getResult())) {
                logger.info("转换币种：fiance没有查询到当前币种汇率;currency:{}", currency);
                return Optional.empty();
            }
            logger.info("获取CPS币种汇率:{}", JacksonUtils.toJsonWithNonNull(zghyhlResponse));
            return zghyhlResponse.getResult().stream().filter(re -> StringUtils.equalsIgnoreCase(re.getCurrency(), currency)).findFirst();
        } catch (Exception ex) {
            logger.error("获取cps汇率异常;currency:【{}】", currency, ex);
            CommonLogContext.addCommonLogifPresent("请求fiance获取汇率,币种:" + currency + ",出现异常：" + ex.getMessage());
        }
        return Optional.empty();
    }

    /**
     * 获取汇率
     *
     * @param currency 币种
     * @return vo
     */
    private Optional<VeZgyhhlVO> getCpsCurrencyCalc(LinkHotelDTO dto, String currency) {
        try {
            ExchangeRateResponse rateResponse = cpsExchangeRateService.execute(dto, currency, VeDate.getStringDateShort());
            if (Objects.isNull(rateResponse) || StringUtils.isBlank(rateResponse.getExchangeRateAfterUp())) {
                logger.info("转换币种：cdsbase没有查询到当前币种汇率;currency:{}", currency);
                return Optional.empty();
            }
             VeZgyhhlVO vo = new VeZgyhhlVO();
            vo.setChaoIn(rateResponse.getChaoIn());
            vo.setChaoOut(rateResponse.getChaoOut());
            vo.setCurrency(rateResponse.getCurrency());
            vo.setDay(rateResponse.getDay());
            vo.setHuiIn(rateResponse.getHuiIn());
            vo.setHuiOut(rateResponse.getHuiOut());
            vo.setName(rateResponse.getName());
            vo.setZhesuan(rateResponse.getExchangeRateAfterUp());
        } catch (Exception ex) {
            logger.error("获取cps汇率异常;currency:【{}】", currency, ex);
            CommonLogContext.addCommonLogifPresent("请求cdsbase获取汇率,币种:" + currency + ",出现异常：" + ex.getMessage());
        }
        return Optional.empty();
    }

    public void dealCurrencyExchangeValidate(LinkHotelValidateDTO dto, LinkHotelValidateVO validate) {
        // 平台为空时不处理
        if (StringUtils.isBlank(dto.getPt())) {
            return;
        }
        List<SearchRoom> rooms = validate.getRooms();
        if (CollectionUtils.isEmpty(rooms)) {
            return;
        }
         try {
            // 转换房费币种
            convertCurrencyExchangeRate(dto, rooms);
            // 处理附加费币种转换
            convertCurrencyExchangeAdditional(BeanMapper.map(dto, LinkHotelRateSearchDTO.class), rooms);
        } catch (Exception ignored) {
        }
    }
}

