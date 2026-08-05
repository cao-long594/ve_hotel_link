package cn.vetech.center.hotel.link.supply.service.ratesearch;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.HotelPriceSearchSourceEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.HotelItemDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.RealTimePriceAsyncDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.HotelRealTimePriceAsyncVO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.RealTimePriceAsyncVO;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.HotelPriceCalcEnum;
import cn.vetech.center.hotel.link.supply.base.async.AsyncService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.CollectUtils;
import cn.vetech.center.hotel.link.util.ImmutablePairUtils;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.link.util.LogUtils;
import cn.vetech.center.hotel.link.util.ThreadPoolUtils;
import cn.vetech.center.hotel.link.util.VeStringUtil;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.cloud.cache.api.IVeCacheManage;
import cn.vetech.charge.cloud.cache.redis.config.VeRedisLockManage;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.charge.cloud.modules.utils.security.MD5Tool;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.redisson.api.RLock;
import org.slf4
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 列表实时最低价查询
 *
 * @author vetech
 * @since 2023/10/30
 */
@Service
public class RealTimeRateSearchAsyncService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RealTimeRateSearchAsyncService.class);
    /**
     * 结果缓存前缀
     */
    private static final String RESULT_CACHE_PREFIX = "listPrice_";
    /**
     * 结果缓存后缀
     */
    private static final String RESULT_CACHE_SUFFIX = "_result";
    /**
     * 线程池
     */
    private static ExecutorService executorService;
    /**
     * 跟踪号
     */
    @Autowired
    private Tracer tracer;
    /**
     * lock
     */
    @Autowired
    private VeRedisLockManage lockManage;

     /**
     * 缓存
     */
    @Autowired
    private IVeCacheManage cacheManage;
    /**
     * 报价异步查询
     */
    @Autowired
    private RateSearchAsyncService rateSearchAsyncService;
    /**
     * 房源配置分发
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     * 实时报价
     */
    @Autowired
    private RealTimeRateSearchService realTimeRateSearchService;
    /**
     * 异步
     */
    @Autowired
    private AsyncService asyncService;
    /**
     * 实时计算最低价异步线程
     */
    @Resource(name = "asyncRealTimePrice")
    private Executor asyncRealTimePrice;

     /**
     * 初始化
     */
    @PostConstruct
    private void init() {
        final int corePoolSize = 64;
        final int maxPoolSize = 150;
        final int keepAliveTime = 5;
        final int dequeCap = 10;
        executorService = ThreadPoolUtils.initTraceableExecutorService(corePoolSize, maxPoolSize, keepAliveTime, dequeCap, "realTimeRateAsync", tracer, "realTimeRate");
    }

    /**
     * 异步实时计算最低价
     *
     * @param dto RealTimePriceDTO
     * @return RealTimePriceVO
     */
    public RealTimePriceAsyncVO getRealTimeRatePriceAsync(RealTimePriceAsyncDTO dto) {
        // 校验参数
        ImmutablePair<Boolean, String> pair = checkBeforeSearch(dto);
        if (Boolean.FALSE.equals(pair.getLeft())) {
            return BeanMapper.map(RateSearchApiRes.fail(pair.getRight()), RealTimePriceAsyncVO.class);
        }

        // 预处理
        preHandle(dto);

        LinkHotelRateSearchDTO rateSearchDTO = dto.getLinkHotelRateSearchDTO();
        final String cacheKey = rateSearchDTO.getCacheKey();
        CommonLogContext.getCommonLog().ifPresent(commLog -> {
            commLog.setYwdh(String.join(SymbolConstant.UNDER_LINE, rateSearchDTO.getCheckInDate(), rateSearchDTO.getCheckOutDate()));
            commLog.setDdbh(cacheKey);
        });

          // 首次查询
        if (dto.isFirstSearchFlag()) {
            RealTimePriceAsyncVO vo = new RealTimePriceAsyncVO();
            vo.setCacheKey(cacheKey);
            vo.setPriceCompletedHotelIdList(Collections.emptySet());
            vo.setPriceNotCompletedHotelIdList(dto.getItemDTOList().stream().map(HotelItemDTO::getJdid).collect(Collectors.toSet()));
            vo.setHotelIdList(dto.getItemDTOList().stream().map(HotelItemDTO::getJdid).collect(Collectors.toSet()));
            vo.setHotelPriceAsyncList(Collections.emptyList());
            // 先放入缓存，以免第二次查不到缓存
            putRealTimePriceAsyncResult(cacheKey, vo, NumConstant.NUM_1 * NumConstant.NUM_60);

            // 异步先分发，后续调用直接取缓存
            CompletableFuture.runAsync(() -> {
                RealTimePriceAsyncVO asyncVO = new RealTimePriceAsyncVO();
                asyncVO.setCacheKey(cacheKey);
                asyncVO.setHotelIdList(dto.getItemDTOList().stream().map(HotelItemDTO::getJdid).collect(Collectors.toSet()));

                // 获取供应商信息
                List<Map<String, String>> configs = CollectUtils.applyIfNotEmpty(dto.getSupplierConfigs(), list -> list, () -> configService.getConfigs(rateSearchDTO));
                if (CollectionUtils.isEmpty(configs)) {
                    logger.warn("供应商信息为空，查询参数：【{}】", JsonMapperUtil.toJsonStr(rateSearchDTO));
                    asyncVO.setErrorMsg("未获取到供应商信息");
                    // 放入缓存
                    putRealTimePriceAsyncResult(cacheKey, asyncVO, NumConstant.NUM_2 * NumConstant.NUM_60);
                    return;
                }
   // 构造报价查询参数
                Set<String> notCompletedHotelIdList = new HashSet<>();
                for (HotelItemDTO item : dto.getItemDTOList()) {
                    if (CollectionUtils.isEmpty(item.getGyslist())) {
                        logger.warn("酒店【{}】映射关系为空", item.getJdid());
                        continue;
                    }

                    LinkHotelRateSearchDTO itemRateSearchDTO = BeanMapper.map(rateSearchDTO, LinkHotelRateSearchDTO.class);
                    itemRateSearchDTO.setLocalHotelId(item.getJdid());
                    itemRateSearchDTO.setCacheKey(genHotelPriceResultCacheKey(rateSearchDTO.getCacheKey(), item.getJdid()));
                    List<LinkHotelRateSearchDTO> searchDTOList = realTimeRateSearchService.getHotelRateSearchDTOS(itemRateSearchDTO, configs, item, NumConstant.NUM_30 * NumConstant.NUM_1000, HotelPriceCalcEnum.ListPriceGetWay.SINGLE_ASYNC_GET);
                    if (CollectionUtils.isEmpty(searchDTOList)) {
                        logger.warn("酒店【{}】无供应商可查询", item.getJdid());
                        continue;
                    }
                    // 单个酒店异步查询
                    notCompletedHotelIdList.add(item.getJdid());
                    searchRealTimePriceAsync(itemRateSearchDTO, item, searchDTOList);
                }

                // 放入缓存
                asyncVO.setPriceNotCompletedHotelIdList(notCompletedHotelIdList);
                putRealTimePriceAsyncResult(cacheKey, asyncVO, NumConstant.NUM_2 * NumConstant.NUM_60);
            }, executorService);

            return vo;
        }
  RealTimePriceAsyncVO cacheVO = getRealTimePriceAsyncResult(cacheKey);
        Set<String> oriNotCompletedHotelIdList = CollectUtils.applyIfNotEmpty(cacheVO.getPriceNotCompletedHotelIdList(), list -> list, HashSet::new);
        Set<String> oriCompletedHotelIdList = CollectUtils.applyIfNotEmpty(cacheVO.getPriceCompletedHotelIdList(), list -> list, HashSet::new);
        if (CollectionUtils.isEmpty(oriNotCompletedHotelIdList)) {
            logger.warn("【{}】本次查询无价格加载未完成的酒店，已完成酒店：【{}】，错误信息：【{}】", cacheKey, VeStringUtil.joinIfNotBlank(SymbolConstant.COMMA, oriCompletedHotelIdList), cacheVO.getErrorMsg());
            return cacheVO;
        }

        List<HotelRealTimePriceAsyncVO> priceResultList;
        boolean hasChangeFlag;
        boolean hasPriceChangeFlag = false;
        boolean allCompletedFlag = false;
        int pollCount = 0;
        final int pollMaxLimit = 30;
        Set<String> nowCompletedHotelIdList = new HashSet<>();
        long startTime = System.currentTimeMillis();
        do {
            pollCount++;
            priceResultList = new ArrayList<>();
            for (String hotelId : oriNotCompletedHotelIdList) {
                HotelRealTimePriceAsyncVO asyncVO = getHotelPriceResult(cacheKey, hotelId, pollCount == 1 || pollCount == pollMaxLimit);
                if (Objects.nonNull(asyncVO) && asyncVO.isPriceLoadCompletedFlag()) {
                    priceResultList.add(asyncVO);
                }
            }
             nowCompletedHotelIdList = priceResultList.stream().map(HotelRealTimePriceAsyncVO::getLocalHotelId).collect(Collectors.toSet());
            allCompletedFlag = oriNotCompletedHotelIdList.containsAll(nowCompletedHotelIdList) && nowCompletedHotelIdList.containsAll(oriNotCompletedHotelIdList);
            hasPriceChangeFlag = priceResultList.stream().anyMatch(item -> CollectionUtils.isNotEmpty(item.getSearchRoomList()));
            hasChangeFlag = (allCompletedFlag || hasPriceChangeFlag);
            if (!hasChangeFlag) {
                try {
                    Thread.sleep(NumConstant.NUM_500);
                } catch (InterruptedException e) {
                    logger.error("线程sleep异常", e);
                    Thread.currentThread().interrupt();
                }
            }
        } while (!hasChangeFlag && pollCount < pollMaxLimit);
        logger.info("【{}】本次查询共轮询【{}】次，耗时【{}】ms，存在有价格酒店：【{}】，全部加载完成：【{}】，原始未完成酒店：【{}】，本次加载完成酒店：【{}】", cacheKey, pollCount, System.currentTimeMillis() - startTime, hasPriceChangeFlag, allCompletedFlag, VeStringUtil.joinIfNotBlank(SymbolConstant.COMMA, oriNotCompletedHotelIdList), VeStringUtil.joinIfNotBlank(SymbolConstant.COMMA, nowCompletedHotelIdList));

        oriCompletedHotelIdList.addAll(nowCompletedHotelIdList);
        oriNotCompletedHotelIdList.removeAll(nowCompletedHotelIdList);

        cacheVO.setPriceCompletedHotelIdList(oriCompletedHotelIdList);
        cacheVO.setPriceNotCompletedHotelIdList(oriNotCompletedHotelIdList);
        cacheVO.setHotelPriceAsyncList(priceResultList);
        int loadCount = cacheVO.getLoadCount().incrementAndGet();
        // 若加载最低价次数>20,则默认完成！
        if (loadCount >= 20 && CollectionUtils.isNotEmpty(oriNotCompletedHotelIdList)) {
            cacheVO.setPriceNotCompletedHotelIdList(Collections.emptySet());
            cacheVO.setErrorMessage("最低价加载次数超过限制，默认完成，当前未完成酒店ID：【" + VeStringUtil.joinIfNotBlank(SymbolConstant.COMMA, oriNotCompletedHotelIdList) + "】");
        }
        // 放入缓存
        putRealTimePriceAsyncResult(cacheKey, cacheVO, NumConstant.NUM_2 * NumConstant.NUM_60);
        return cacheVO;
    }

     /**
     * 校验
     *
     * @param dto 入参
     * @return ImmutablePair<Boolean, String>
     */
    private ImmutablePair<Boolean, String> checkBeforeSearch(RealTimePriceAsyncDTO dto) {
        LinkHotelRateSearchDTO rateSearchDTO = dto.getLinkHotelRateSearchDTO();
        if (dto.isFirstSearchFlag()) {
            if (CollectionUtils.isEmpty(dto.getItemDTOList())) {
                logger.warn("酒店信息为空，入参：【{}】", JsonMapperUtil.toJsonStr(dto));
                return ImmutablePairUtils.build(false, "酒店信息为空");
            }
        } else {
            if (StringUtils.isBlank(rateSearchDTO.getCacheKey())) {
                logger.warn("非首次查询，缓存key不可为空，入参：【{}】", JsonMapperUtil.toJsonStr(dto));
                return ImmutablePairUtils.build(false, "非首次查询，缓存key不可为空");
            }
        }
        return ImmutablePairUtils.build(true, null);
    }

    /**
     * 预处理
     *
     * @param dto 入参
     */
    private void preHandle(RealTimePriceAsyncDTO dto) {
        LinkHotelRateSearchDTO searchDTO = dto.getLinkHotelRateSearchDTO();
        String cacheKey = searchDTO.getCacheKey();
        if (StringUtils.isBlank(cacheKey)) {
            cacheKey = RESULT_CACHE_PREFIX + genUniKey(dto) + RESULT_CACHE_SUFFIX;
        } else {
            if (!cacheKey.startsWith(RESULT_CACHE_PREFIX)) {
                cacheKey = RESULT_CACHE_PREFIX + cacheKey;
            }
            if (!cacheKey.endsWith(RESULT_CACHE_SUFFIX)) {
                cacheKey = cacheKey + RESULT_CACHE_SUFFIX;
            }
        }
        searchDTO.setCacheKey(cacheKey);
        // 酒店最低价报价查询
        if (1 == searchDTO.getHotelListSearch()) {
            searchDTO.setSearchSource(StringUtils.defaultIfBlank(searchDTO.getSearchSource(), HotelPriceSearchSourceEnum.LIST_LOWEST_PRICE.getCode()));
        }
    }

     /**
     * 供应查询参数分类
     *
     * @param dtoList 供应查询参数
     * @return ImmutablePair<List < LinkHotelRateSearchDTO>, List<LinkHotelRateSearchDTO>>
     */
    private ImmutablePair<List<LinkHotelRateSearchDTO>, List<LinkHotelRateSearchDTO>> classifyLinkHotelRateSearchDTO(List<LinkHotelRateSearchDTO> dtoList) {
        Map<Boolean, List<LinkHotelRateSearchDTO>> map = dtoList.stream().filter(dto -> MapUtils.isNotEmpty(dto.getSupplier())).collect(Collectors.groupingBy(realTimeRateSearchService::canPartInRealTimePriceQry));
        return ImmutablePair.of(map.getOrDefault(Boolean.TRUE, Collections.emptyList()), map.getOrDefault(Boolean.FALSE, Collections.emptyList()));
    }

    /**
     * 异步查询实时最低价
     *
     * @param dto           查询参数
     * @param hotelItem     酒店信息
     * @param searchDTOList 供应查询参数
     * @return Future<Void>
     */
    private Future<Void> searchRealTimePriceAsync(LinkHotelRateSearchDTO dto, HotelItemDTO hotelItem, List<LinkHotelRateSearchDTO> searchDTOList) {
        return CompletableFuture.runAsync(() -> {
            String resultKey = dto.getCacheKey();
            Function<LinkHotelRateSearchDTO, String> supplyFunc = item -> item.getFybh() + SymbolConstant.UNDER_LINE + item.getZhmc();
            // 查询参数分类
            ImmutablePair<List<LinkHotelRateSearchDTO>, List<LinkHotelRateSearchDTO>> dtoPair = classifyLinkHotelRateSearchDTO(searchDTOList);
            List<LinkHotelRateSearchDTO> partInSearchDtoList = dtoPair.getLeft();
            List<LinkHotelRateSearchDTO> notPartInSearchDtoList = dtoPair.getRight();
  HotelRealTimePriceAsyncVO vo = new HotelRealTimePriceAsyncVO();
            vo.setLocalHotelId(hotelItem.getJdid());
            vo.setPartInSupplyMerchList(Collections.emptyList());
            vo.setNotPartInSupplyMerchList(CollectUtils.applyEachIfNotEmpty(notPartInSearchDtoList, supplyFunc));
            vo.setAllSupplyMerchList(CollectUtils.applyEachIfNotEmpty(searchDTOList, supplyFunc));
            vo.setSearchRoomList(Collections.emptyList());

            if (CollectionUtils.isEmpty(partInSearchDtoList)) {
                vo.setPriceLoadCompletedFlag(true);
                vo.setVersionMd5("no_supplier");
                // 放入缓存
                putHotelPriceResult(resultKey, vo, NumConstant.NUM_5 * NumConstant.NUM_60);
                return;
            }

            // 异步查询
            String lockKey = dto.getCacheKey() + "_lowPrice";
            RLock lock = lockManage.getLock(HotelLinkConstant.APPLICATION_NAME, lockKey);
            //根据酒店映射和供应商配置 生成各个供应商请求
            try {
                boolean tryLock = lock.tryLock(NumConstant.NUM_60, TimeUnit.SECONDS);
                if (!tryLock) {
                    logger.warn("无法获取到lock【{}】，可能酒店【{}】报价正在查询中", lockKey, hotelItem.getJdid());
                    return;
                }
            List<Future<LinkHotelRateSearchVO>> futureList = new ArrayList<>();
                for (LinkHotelRateSearchDTO searchDTO : partInSearchDtoList) {
                    futureList.add(rateSearchAsyncService.rateSearch(searchDTO));
                }

                for (Future<LinkHotelRateSearchVO> future : futureList) {
                    LinkHotelRateSearchDTO searchDTO = partInSearchDtoList.get(futureList.indexOf(future));
                    try {
                        future.get();
                    } catch (InterruptedException e) {
                        logger.error("查询供应【{}_{}】酒店【{}_{}】报价InterruptedException异常", searchDTO.getFybh(), searchDTO.getZhmc(), searchDTO.getLocalHotelId(), searchDTO.getHotelId(), e);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        logger.error("查询供应【{}_{}】酒店【{}_{}】报价ExecutionException异常", searchDTO.getFybh(), searchDTO.getZhmc(), searchDTO.getLocalHotelId(), searchDTO.getHotelId(), e);
                    }
                }

                // 取缓存
                LinkHotelRateSearchVO rateSearchVO = asyncService.getCache(dto);
                if (Objects.isNull(rateSearchVO)) {
                    logger.warn("酒店【{}】价格缓存为空，缓存key：【{}】", dto.getLocalHotelId(), dto.getCacheKey());
                    vo.setPriceLoadCompletedFlag(true);
                    vo.setVersionMd5("no_cache_data");
                    // 放入缓存
                    putHotelPriceResult(resultKey, vo, NumConstant.NUM_5 * NumConstant.NUM_60);
                    return;
                }
                List<Future<LinkHotelRateSearchVO>> futureList = new ArrayList<>();
                for (LinkHotelRateSearchDTO searchDTO : partInSearchDtoList) {
                    futureList.add(rateSearchAsyncService.rateSearch(searchDTO));
                }

                for (Future<LinkHotelRateSearchVO> future : futureList) {
                    LinkHotelRateSearchDTO searchDTO = partInSearchDtoList.get(futureList.indexOf(future));
                    try {
                        future.get();
                    } catch (InterruptedException e) {
                        logger.error("查询供应【{}_{}】酒店【{}_{}】报价InterruptedException异常", searchDTO.getFybh(), searchDTO.getZhmc(), searchDTO.getLocalHotelId(), searchDTO.getHotelId(), e);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        logger.error("查询供应【{}_{}】酒店【{}_{}】报价ExecutionException异常", searchDTO.getFybh(), searchDTO.getZhmc(), searchDTO.getLocalHotelId(), searchDTO.getHotelId(), e);
                    }
                }

                // 取缓存
                LinkHotelRateSearchVO rateSearchVO = asyncService.getCache(dto);
                if (Objects.isNull(rateSearchVO)) {
                    logger.warn("酒店【{}】价格缓存为空，缓存key：【{}】", dto.getLocalHotelId(), dto.getCacheKey());
                    vo.setPriceLoadCompletedFlag(true);
                    vo.setVersionMd5("no_cache_data");
                    // 放入缓存
                    putHotelPriceResult(resultKey, vo, NumConstant.NUM_5 * NumConstant.NUM_60);
                    return;
                }
 /**
     * 生产缓存key-单个酒店
     *
     * @param cacheKey     缓存key
     * @param localHotelId 本地酒店id
     * @return String
     */
    private String genHotelPriceResultCacheKey(String cacheKey, String localHotelId) {
        return cacheKey + SymbolConstant.UNDER_LINE + localHotelId;
    }

    /**
     * 获取缓存-单个酒店价格
     *
     * @param cacheKey      缓存key
     * @param localHotelId  本地酒店id
     * @param recordLogFlag 是否记录日志
     * @return HotelRealTimePriceAsyncVO
     */
      private HotelRealTimePriceAsyncVO getHotelPriceResult(String cacheKey, String localHotelId, boolean recordLogFlag) {
        String key = genHotelPriceResultCacheKey(cacheKey, localHotelId);
        try {
            return LogUtils.applyAndRecord(() -> {
                Object o = cacheManage.get(HotelLinkConstant.APPLICATION_NAME, key);
                if (Objects.nonNull(o)) {
                    return (HotelRealTimePriceAsyncVO) o;
                }
                if (recordLogFlag) {
                    logger.warn("酒店价格缓存【{}】数据为空", key);
                }
                return null;
            }, NumConstant.NUM_100, time -> logger.info("获取酒店价格缓存【{}】数据耗时【{}】ms", key, time));
        } catch (Exception e) {
            logger.error("获取酒店价格缓存【{}】数据异常：", key, e);
            return null;
        }
    }
 private HotelRealTimePriceAsyncVO getHotelPriceResult(String cacheKey, String localHotelId, boolean recordLogFlag) {
        String key = genHotelPriceResultCacheKey(cacheKey, localHotelId);
        try {
            return LogUtils.applyAndRecord(() -> {
                Object o = cacheManage.get(HotelLinkConstant.APPLICATION_NAME, key);
                if (Objects.nonNull(o)) {
                    return (HotelRealTimePriceAsyncVO) o;
                }
                if (recordLogFlag) {
                    logger.warn("酒店价格缓存【{}】数据为空", key);
                }
                return null;
            }, NumConstant.NUM_100, time -> logger.info("获取酒店价格缓存【{}】数据耗时【{}】ms", key, time));
        } catch (Exception e) {
            logger.error("获取酒店价格缓存【{}】数据异常：", key, e);
            return null;
        }
    }

    /**
     * 放入缓存-酒店最低价结果
     *
     * @param resultKey 缓存key
     * @param vo        缓存结果
     * @param cacheTime 缓存时长（s）
     */
    private void putRealTimePriceAsyncResult(String resultKey, RealTimePriceAsyncVO vo, int cacheTime) {
        RealTimePriceAsyncVO asyncVO = BeanMapper.map(vo, RealTimePriceAsyncVO.class);
        // 不记录数据
        asyncVO.setHotelPriceAsyncList(null);
        try {
            LogUtils.runAndRecord(() -> cacheManage.put(HotelLinkConstant.APPLICATION_NAME, resultKey, asyncVO, cacheTime), NumConstant.NUM_100, time -> logger.info("缓存数据【{}】耗时【{}】ms，缓存时长：【{}】s", resultKey, time, cacheTime));
        } catch (Exception e) {
            logger.error("缓存【{}】异常：", resultKey, e);
        }
    }

    /**
     * 生成唯一key
     *
     * @param dto 入参
     * @return String
     */
    private String genUniKey(RealTimePriceAsyncDTO dto) {
        RealTimePriceAsyncDTO asyncDTO = BeanMapper.map(dto, RealTimePriceAsyncDTO.class);
        // 置空影响md5结果值
        asyncDTO.setUuidKey(null);
        asyncDTO.setYdrIp(null);
        asyncDTO.setVeLanguage(null);
        LinkHotelRateSearchDTO rateSearchDTO = asyncDTO.getLinkHotelRateSearchDTO();
        rateSearchDTO.setUuidKey(null);
        rateSearchDTO.setYdrIp(null);
        rateSearchDTO.setYdrYdzd(null);
        rateSearchDTO.setVeLanguage(null);
        return MD5Tool.MD5Encode(JsonMapperUtil.toJsonStr(asyncDTO));
    }

}

                
                
    
        
