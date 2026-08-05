package cn.vetech.center.hotel.link.supply.service.aspect;

import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.UserVipExtInfo;
import cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamParseDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.supply.service.distribute.vip.VipParseDistributeService;
import cn.vetech.center.hotel.link.supply.service.pricecollect.RatePlanCollectService;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.link.util.ObjectUtils;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.cloud.modules.utils.localcache.LocalCacheUtil;
import cn.vetech.charge.cloud.modules.utils.security.MD5Tool;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import com.google.common.base.Stopwatch;
import com.google.common.cache.Cache;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author vetech
 * @since 2023/10/6
 */
@Component
@Aspect
@Order(11)
public class RateSearchAspect extends RateBaseAspect {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RateSearchAspect.class);
    /**
     * 会员解析
     */
    @Autowired
    private VipParseDistributeService vipParseDistributeService;
    /**
     * 本地缓存
     */
    private Cache<String, Long> keyExpireAtTimeCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_10_000, NumConstant.NUM_2, TimeUnit.HOURS);
    /**
     * 存储缓存时间
     */
    private static final long TIME_OUT_SECOND = NumConstant.NUM_2 * NumConstant.NUM_60 * NumConstant.NUM_60;
    /**
     * 查询报价缓存有效时间
     */
    private static final long RATE_SEARCH_VAILD_TIME_SECOND = NumConstant.NUM_5 * NumConstant.NUM_60;
    /**
     * vip
     */
    private static final String VIP_FLAG_KEY = "vip:";
    /**
     * 列表价格不同于第二屏报价
     */
    private static final Set<String> LIST_NOT_RATE_SUPPLY_CODE_SET = Sets.newHashSet("201701", "201702", "31201000");

    /**
     * 价格收集
     */
    @Autowired
    private RatePlanCollectService ratePlanCollectService;

    /**
     * 公共线程池
     */
    @Resource(name = "commonPool")
    private Executor commonPool;

    /**
     * 切面表达式
     *
     * @param dto dto
     */
    @Pointcut("execution(* cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService.rateSearch(..))&&args(dto)")
    public void pointCut(LinkHotelRateSearchDTO dto) {

    }

    /**
     * 切面
     *
     * @param joinPoint joinPoint
     * @param dto       dto
     * @return java.lang.Object
     */
    @Around(value = "pointCut(dto)")
    public Object around(ProceedingJoinPoint joinPoint, LinkHotelRateSearchDTO dto) throws Throwable {
        AtomicBoolean collectFlag = new AtomicBoolean(Boolean.TRUE);
        Object result = excute(joinPoint, dto, collectFlag);
        if (Boolean.TRUE.equals(collectFlag.get())) {
            ratePlanCollectService.asyncCollectPrice(result, dto);
        }
        return result;
    }
     private Object excute(ProceedingJoinPoint joinPoint, LinkHotelRateSearchDTO dto, AtomicBoolean collectFlag) throws Throwable {
        Map<String, String> supplier = dto.getSupplier();
        String kqhc = supplier.getOrDefault("kqhc", "0");
        //开启了缓存
        boolean openPriceCache = StringUtils.equalsAny(kqhc, "1", "2");
        //是否酒店列表查询
        boolean isListSearch = dto.getHotelListSearch() == 1;
        //只读缓存,kqhc配置为2 同时是酒店列表查询
        boolean onlyReadCache = StringUtils.equals(kqhc, "2") && isListSearch;
        if (isListSearch) {
            CommonLogContext.addCommonLogifPresent("实时计算酒店列表最低价查询，是否开启了缓存:" + openPriceCache + "是否只读缓存:" + onlyReadCache);
            CommonLogContext.getCommonLog().ifPresent(commLog -> {
                commLog.setJkzh("查询报价(列表)");
                commLog.setJkmc("RateSearchList");
            });
        }
        //没有开启缓存
        if (!openPriceCache) {
            collectFlag.set(Boolean.FALSE);
            return joinPoint.proceed();
        }
        String checkOutDate = dto.getCheckOutDate();
        long days = VeDate.getDays(checkOutDate, VeDate.getStringDate());
        if (Math.abs(days) > NumConstant.NUM_30) {
            collectFlag.set(Boolean.FALSE);
            return joinPoint.proceed();
        }
        //开启了缓存 或者者 是查询报价
        Map<String, String> dtoSupplier = dto.getSupplier();
        if (MapUtils.isEmpty(dtoSupplier)) {
            return joinPoint.proceed();
        }
        String fyen = MapUtils.getString(dtoSupplier, "fyen");
        String cacheKey = getCacheKey(dto);
        if (StringUtils.isBlank(cacheKey)) {
            return joinPoint.proceed();
        }
          String cacheType = dto.getCacheType();
        //是否为列表计算最低价查询 100秒
        long expire = Optional.ofNullable(getExpire(cacheKey, isListSearch)).orElse((long) -2);
        String logContent = String.format("缓存中获取价格,采购商:%s,查询房源:%s,酒店id:%s,是否酒店列表查询:%s,价格剩余过期时间:%s min,缓存key:%s", dto.getBusinessNo(), dto.getFybh(), dto.getLocalHotelId(), isListSearch, expire / 60, cacheKey);
        //则如果缓存没有则直接返回空，如果有则从缓存中取
        if (onlyReadCache) {
            if (expire == -2) {
                CommonLogContext.addCommonLogifPresent("只读缓存,酒店列表查询缓存中暂无数据,返回空");
                return new LinkHotelRateSearchVO();
            }
            CommonLogContext.addCommonLogifPresent(logContent);
            collectFlag.set(Boolean.FALSE);
            return getLinkHotelRateSearchVOFromCache(cacheKey);
        }
        //是否清除缓存 清除价格缓存则不走缓存||如果缓存中没有 ||或者该酒店最近验价失败同时不是酒店列表查询
        if (StringUtils.equals("0", cacheType) || expire == -2) {
            return excuteAndCache(joinPoint, cacheKey, logContent);
        }
        //根据查询参数获取缓存的key,如果当前的酒店最近验价失败了,则查询报价不走缓存.如果是asms供应商查询报价不能走缓存，因为asms的价格是在查询时放入换缓存的
        String validateFailHotelKey = getValidateFailHotelKey(dto.getLocalHotelId(), dto.getCheckInDate(), dto.getCheckOutDate());
        if (!isListSearch && (StringUtils.isBlank(validateFailHotelKey) || hasKey(validateFailHotelKey) || StringUtils.equalsAnyIgnoreCase(fyen, "asms"))) {
            return excuteAndCache(joinPoint, cacheKey, logContent);
        }
             long expireSecondVaild = TIME_OUT_SECOND - RATE_SEARCH_VAILD_TIME_SECOND;
        //如果是酒店列表实时查询最低价   或者价格过期时间在有效期内 则直接从缓存中获取
        if (isListSearch || expire >= expireSecondVaild) {
            CommonLogContext.addCommonLogifPresent(logContent);
            //判断时间可能是有效的,
            try {
                collectFlag.set(Boolean.FALSE);
                return getLinkHotelRateSearchVOFromCache(cacheKey);
            } catch (Exception e) {
                logger.error("从redis中解析数据出现异常:{},e", logContent);
                return excuteAndCache(joinPoint, cacheKey, logContent);
            }
        }
        return excuteAndCache(joinPoint, cacheKey, logContent);
    }

    /**
     * 从缓存中获取结果
     *
     * @param cacheKey cacheKey
     * @return cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO
     */
    private LinkHotelRateSearchVO getLinkHotelRateSearchVOFromCache(String cacheKey) {
        Stopwatch started = Stopwatch.createStarted();
        String cacheJsonStr = redisTemplate.opsForValue().get(cacheKey);
        long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
        if (elapsed > NumConstant.NUM_60) {
            logger.warn("从缓存中获取缓存结果超过60ms,耗时{}ms,key:{}", elapsed, cacheKey);
        }
        LinkHotelRateSearchVO vo = JsonMapperUtil.fromJson(cacheJsonStr, LinkHotelRateSearchVO.class);
        ObjectUtils.handleIfNotNull(vo, obj -> obj.setFromCacheFlag(true));
        return vo;
    }

    /**
     * 获取缓存的key
     *
     * @param dto dto
     * @return java.lang.String
     */
    private String getCacheKey(LinkHotelRateSearchDTO dto) {
        try {
            Map<String, String> dtoSupplier = dto.getSupplier();
            ImmutableTriple<Boolean, String, VipParamParseDTO> vipVaildResult = vipParseDistributeService.vailAndGetVipParam(dto);
            StringBuilder supplierStr = new StringBuilder(JsonMapperUtil.toJsonStr(dtoSupplier));
            String key = "";
            if (Boolean.TRUE.equals(vipVaildResult.getLeft()) && Objects.nonNull(vipVaildResult.getRight())) {
                String vipUserPhone = Optional.ofNullable(vipVaildResult.getRight().getUserVipExtInfo()).map(UserVipExtInfo::getPhoneNumber).orElse(vipVaildResult.getRight().getYdrPhoneNumber());
                if (StringUtils.isBlank(vipUserPhone)) {
                    logger.error("vip解析后手机号码为空,请求参数:{}", JsonMapperUtil.toJsonStr(dto));
                }
                supplierStr.append(vipUserPhone);
                key = VIP_FLAG_KEY;
            }
            // asms/cps酒店列表最低价（调用price-hotel应用,存在部分供应商报价且报价信息简化）与第二屏实时报价不完全一致，缓存区须分开【若开启列表最低价只读可共用第二屏实时报价的缓存！】
            String kqhc = MapUtils.getString(dtoSupplier, "kqhc");
            if (1 == dto.getHotelListSearch() && LIST_NOT_RATE_SUPPLY_CODE_SET.contains(dto.getFybh()) && !"2".equals(kqhc)) {
                supplierStr.append("hotelListLowPrice");
            }
            key = StringUtils.join(key, dto.getCheckInDate(), dto.getCheckOutDate(), dto.getHotelId(), dto.getLocalHotelId(), dto.getFjs(), dto.getRzrgj(), dto.getAdult(), MD5Tool.MD5Encode(supplierStr.toString()));
            return veRedisUtilService.genKey(HotelLinkConstant.APPLICATION_NAME, key);
        } catch (Exception e) {
            logger.error("生成缓存key异常", e);
        }
        return StringUtils.EMPTY;

    }

    /**
     * 执行切面方法，并缓存结果
     *
     * @param joinPoint  joinPoint
     * @param cacheKey   cacheKey
     * @param logContent logContent
     * @return java.lang.Object
     */
     if (Boolean.TRUE.equals(vipVaildResult.getLeft()) && Objects.nonNull(vipVaildResult.getRight())) {
                String vipUserPhone = Optional.ofNullable(vipVaildResult.getRight().getUserVipExtInfo()).map(UserVipExtInfo::getPhoneNumber).orElse(vipVaildResult.getRight().getYdrPhoneNumber());
                if (StringUtils.isBlank(vipUserPhone)) {
                    logger.error("vip解析后手机号码为空,请求参数:{}", JsonMapperUtil.toJsonStr(dto));
                }
                supplierStr.append(vipUserPhone);
                key = VIP_FLAG_KEY;
            }
            // asms/cps酒店列表最低价（调用price-hotel应用,存在部分供应商报价且报价信息简化）与第二屏实时报价不完全一致，缓存区须分开【若开启列表最低价只读可共用第二屏实时报价的缓存！】
            String kqhc = MapUtils.getString(dtoSupplier, "kqhc");
            if (1 == dto.getHotelListSearch() && LIST_NOT_RATE_SUPPLY_CODE_SET.contains(dto.getFybh()) && !"2".equals(kqhc)) {
                supplierStr.append("hotelListLowPrice");
            }
            key = StringUtils.join(key, dto.getCheckInDate(), dto.getCheckOutDate(), dto.getHotelId(), dto.getLocalHotelId(), dto.getFjs(), dto.getRzrgj(), dto.getAdult(), MD5Tool.MD5Encode(supplierStr.toString()));
            return veRedisUtilService.genKey(HotelLinkConstant.APPLICATION_NAME, key);
        } catch (Exception e) {
            logger.error("生成缓存key异常", e);
        }
        return StringUtils.EMPTY;

    }

    /**
     * 执行切面方法，并缓存结果
     *
     * @param joinPoint  joinPoint
     * @param cacheKey   cacheKey
     * @param logContent logContent
     * @return java.lang.Object
     */
      private Object excuteAndCache(ProceedingJoinPoint joinPoint, String cacheKey, String logContent) throws Throwable {
        long startMills = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTimeMills = System.currentTimeMillis() - startMills;
        if (Objects.isNull(result)) {
            return result;
        }
        if (!(result instanceof LinkHotelRateSearchVO)) {
            return result;
        }
        LinkHotelRateSearchVO resultVo = (LinkHotelRateSearchVO) result;
        if (Objects.isNull(resultVo.getFirReqCostMillis())) {
            resultVo.setFirReqCostMillis(costTimeMills);
        }
        String resultVoJsonStr = JsonMapperUtil.toJsonStr(result);
        if (CollectionUtils.isEmpty(resultVo.getRooms())) {
            doPutToCache(cacheKey, logContent, resultVoJsonStr);
            return result;
        }
        boolean anyMatch = resultVo.getRooms().stream().filter(searchRoom -> CollectionUtils.isNotEmpty(searchRoom.getRatePlans())).flatMap(searchRoom -> searchRoom.getRatePlans().stream()).anyMatch(searchRatePlan -> StringUtils.equals(searchRatePlan.getSfhyj(), "1"));
        if (anyMatch && !StringUtils.contains(cacheKey, VIP_FLAG_KEY)) {
            logger.warn("返回的价格包含vip价格,key无vip标识,{}", logContent);
            return result;
        }
        doPutToCache(cacheKey, logContent, resultVoJsonStr);
        return result;
    }

 /**
     * 放入缓存
     *
     * @param cacheKey        cacheKey
     * @param logContent      logContent
     * @param resultVoJsonStr resultVoJsonStr
     */
    private void doPutToCache(String cacheKey, String logContent, String resultVoJsonStr) {
        try {
            CompletableFuture.runAsync(() -> {
                Stopwatch started = Stopwatch.createStarted();
                redisTemplate.opsForValue().set(cacheKey, resultVoJsonStr, TIME_OUT_SECOND, TimeUnit.SECONDS);
                keyExpireAtTimeCache.put(cacheKey, System.currentTimeMillis() / 1000 + TIME_OUT_SECOND);
                long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
                if (elapsed > NumConstant.NUM_60) {
                    logger.warn("查询报价将结果放入缓存时间超过60ms,耗时:{}ms,{}", elapsed, logContent);
                }
            }, commonPool).exceptionally(throwable -> {
                logger.error("将结果放入缓存执行异常", throwable);
                return null;
            });
        } catch (Exception e) {
            logger.error("将结果放入缓存执行异常", e);
        }
    }

    private Long getExpire(String cacheKey, boolean isListSearch) {
        Stopwatch started = Stopwatch.createStarted();
        //优先从本地缓存获取key的过期时间点，如果本地缓存的过期时间点大于0 同时是酒店列表查询直接返回，如果在查询报价缓存有效期内则直接返回
        Long keyExpireAtTime = keyExpireAtTimeCache.getIfPresent(cacheKey);
        if (Objects.nonNull(keyExpireAtTime)) {
            long lessSecond = keyExpireAtTime - System.currentTimeMillis() / 1000;
            if (lessSecond > 0 && isListSearch) {
                return lessSecond;
            }
            if (lessSecond > (TIME_OUT_SECOND - RATE_SEARCH_VAILD_TIME_SECOND)) {
                return lessSecond;
            }
        }
         //从redis中获取过期时间
        Long expire = redisTemplate.getExpire(cacheKey);
        long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
        if (elapsed > NumConstant.NUM_60) {
            logger.warn("从缓存中获取过期时间超过60ms,耗时:{}ms,key:{}", elapsed, cacheKey);
        }
        if (expire > NumConstant.NUM_6) {
            keyExpireAtTimeCache.put(cacheKey, System.currentTimeMillis() / 1000 + expire);
        }
        return expire;
    }


}
