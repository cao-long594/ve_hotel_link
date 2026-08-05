package cn.vetech.center.hotel.link.supply.service.ratesearch;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.HotelPriceSearchSourceEnum;
import cn.vetech.center.hotel.link.api.enums.PriceStatusEnum;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.vo.HotelGysdx;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.HotelItemDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.RealTimePriceDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.RealTimePriceExtDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.RealTimePriceInfoVO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.RealTimePriceVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.HotelPriceCalcEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.service.distribute.RateSearchDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.exchangerate.ExchangeRateService;
import cn.vetech.center.hotel.link.util.CollectUtils;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.link.util.LogUtils;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.cloud.modules.utils.IdWorker;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * @author vetech
 * @since 2023/10/30
 */
@Service
public class RealTimeRateSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealTimeRateSearchService.class);

    /**
     * 实时计算最低价异步线程
     */
    @Resource(name = "asyncRealTimePrice")
    private Executor asyncRealTimePrice;
    /**
     * 价格查询
     */
    @Autowired
    private RateSearchService rateSearchService;
    /**
     * 房源配置分发
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     * 价格计划分发
     */
    @Autowired
    private RateSearchDistributeService rateSearchDistributeService;
    /**
     * 汇率转换
     */
    @Autowired
    private ExchangeRateService exchangeRateService;
 /**
     * 实时计算最低价
     *
     * @param dto RealTimePriceDTO
     * @return RealTimePriceVO
     */
    public RealTimePriceVO getRealTimeRatePrice(RealTimePriceDTO dto) {
        Integer waitTime = dto.getWaitTime();
        // 预处理
        preHandle(dto);

        LinkHotelRateSearchDTO rateSearchDTO = dto.getLinkHotelRateSearchDTO();

        List<Map<String, String>> configs = CollectUtils.applyIfNotEmpty(dto.getSupplierConfigs(), list -> list, () -> configService.getConfigs(rateSearchDTO));
        CommonLogContext.getCommonLog().ifPresent(commLog -> {
            commLog.setYwdh(String.join(SymbolConstant.UNDER_LINE, rateSearchDTO.getCheckInDate(), rateSearchDTO.getCheckOutDate()));
        });

        List<HotelItemDTO> hotelList = dto.getItemDTOList();
        Map<HotelItemDTO, List<LinkHotelRateSearchDTO>> hotelIdRateSearchDtoListMap = hotelList.stream().filter(item -> CollectionUtils.isNotEmpty(item.getGyslist())).collect(Collectors.toMap(Function.identity(), hotelItem -> {
            return getHotelRateSearchDTOS(rateSearchDTO, configs, hotelItem, dto.getWaitTime(), HotelPriceCalcEnum.ListPriceGetWay.BATCH_SYNC_GET).stream()
                    .peek(i -> i.setCacheKey(i.getCacheKey() + SymbolConstant.UNDER_LINE + hotelItem.getJdid())).collect(Collectors.toList());
        }));

        //key为酒店id，value为该酒店所有价格信息 生成线程安全的map以及list
        ConcurrentMap<String, List<RealTimePriceInfoVO>> jdidAllPriceInfoMap = hotelList.stream().map(HotelItemDTO::getJdid).collect(Collectors.toConcurrentMap(Function.identity(), o -> Collections.synchronizedList(new LinkedList<>())));

        List<CompletableFuture<Void>> otaResultListFuture = hotelIdRateSearchDtoListMap.entrySet().stream().map(entry -> CompletableFuture.runAsync(() -> executeSingleOta(jdidAllPriceInfoMap.get(entry.getKey().getJdid()), entry.getValue(), waitTime), asyncRealTimePrice)).collect(Collectors.toList());
       try {
            int allWaitTime = waitTime + NumConstant.NUM_1000;
            CompletableFuture.allOf(otaResultListFuture.toArray(new CompletableFuture[]{})).get(allWaitTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.warn("等待cps供应商和所有直连供应商超时", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        List<RealTimePriceInfoVO> priceInfoVOList = jdidAllPriceInfoMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        RealTimePriceVO timePriceVO = new RealTimePriceVO();
        timePriceVO.setRealTimePriceInfoVOList(priceInfoVOList);
        return timePriceVO;
    }

    /**
     * 获取供应商查询参数
     *
     * @param dto         查询参数
     * @param configs     供应商配置
     * @param hotelItem   酒店信息
     * @param waitTime    等待时长(ms)
     * @param priceGetWay 列表价格获取方式
     * @return List<LinkHotelRateSearchDTO>
     */
    public List<LinkHotelRateSearchDTO> getHotelRateSearchDTOS(LinkHotelRateSearchDTO dto, List<Map<String, String>> configs,
                                                               HotelItemDTO hotelItem, Integer waitTime, HotelPriceCalcEnum.ListPriceGetWay priceGetWay) {
        LinkHotelRateSearchDTO searchDTO = copyRateSearchDTO(dto);
        List<HotelGysdx> gyslist = hotelItem.getGyslist();
        // 该酒店的mapper映射信息
        List<Mapper> mapperList = gyslist.stream().map(item -> {
            Mapper mapper = new Mapper();
            mapper.setFybh(item.getGysbh());
            mapper.setHotelid(item.getHotelid());
            return mapper;
        }).collect(Collectors.toList());
        RateSearchService.convertAndSetMappers(searchDTO, mapperList);
        searchDTO.setMappers(mapperList);
        searchDTO.setLocalHotelId(hotelItem.getJdid());
        return CollectionUtils.emptyIfNull(rateSearchService.getDtosAndFilter(searchDTO, configs)).stream().peek(item -> {
            // cps
             if (FyEnum.CPS.getFybh().equals(item.getFybh())) {
                RealTimePriceExtDTO extDTO = new RealTimePriceExtDTO();
                extDTO.setListPriceGetWay(priceGetWay.getCode());
                extDTO.setWaitTime(waitTime);
                extDTO.setHotelItem(hotelItem);
                extDTO.setRequestSource(HotelPriceCalcEnum.RequestSource.CHARGE.getCode());
                item.setExtendedInfo(JsonMapperUtil.toJsonStr(extDTO));
            }
            // asms
            if (FyEnum.ASMS.getFybh().equals(item.getFybh())) {
                RealTimePriceExtDTO extDTO = new RealTimePriceExtDTO();
                extDTO.setListPriceGetWay(priceGetWay.getCode());
                extDTO.setWaitTime(waitTime);
                extDTO.setRequestSource(HotelPriceCalcEnum.RequestSource.CHARGE.getCode());
                item.setExtendedInfo(JsonMapperUtil.toJsonStr(extDTO));
            }
        }).collect(Collectors.toList());
    }

      /**
     * 是否可实时最低价查询
     *
     * @param dto 查询参数
     * @return boolean
     */
    public boolean canPartInRealTimePriceQry(LinkHotelRateSearchDTO dto) {
        // cps默认参与
        if (FyEnum.CPS.getFybh().equals(dto.getFybh())) {
            return true;
        }
        return StringUtils.equals(MapUtils.getString(dto.getSupplier(), "kqhc"), "1");
    }

    /**
     * 预处理
     *
     * @param dto 入参
     */
    private void preHandle(RealTimePriceDTO dto) {
        LinkHotelRateSearchDTO searchDTO = dto.getLinkHotelRateSearchDTO();
        if (StringUtils.isBlank(searchDTO.getCacheKey())) {
            searchDTO.setCacheKey("realTimePrice-" + IdWorker.get32UUID());
        }
        // 酒店最低价报价查询
        if (1 == searchDTO.getHotelListSearch()) {
            searchDTO.setSearchSource(StringUtils.defaultIfBlank(searchDTO.getSearchSource(), HotelPriceSearchSourceEnum.LIST_LOWEST_PRICE.getCode()));
        }
    }

     /**
     * 复制查询参数
     *
     * @param dto 查询参数
     * @return LinkHotelRateSearchDTO
     */
    private LinkHotelRateSearchDTO copyRateSearchDTO(LinkHotelRateSearchDTO dto) {
        LinkHotelRateSearchDTO searchDTO = BeanMapper.map(dto, LinkHotelRateSearchDTO.class);
        searchDTO.setHotelListSearch(NumConstant.NUM_1);
        return searchDTO;
    }

    /**
     * 查询单个供应商
     *
     * @param resultList 报价结果
     * @param searchDTOS 供应报价查询参数
     * @param waitTime   等待时长
     */
    private void executeSingleOta(List<RealTimePriceInfoVO> resultList, List<LinkHotelRateSearchDTO> searchDTOS, Integer waitTime) {
        Map<Boolean, List<LinkHotelRateSearchDTO>> map = searchDTOS.stream().filter(dto -> MapUtils.isNotEmpty(dto.getSupplier())).collect(Collectors.groupingBy(this::canPartInRealTimePriceQry));
        List<LinkHotelRateSearchDTO> openCacheSearchDtoList = map.getOrDefault(Boolean.TRUE, Collections.emptyList());
        if (CollectionUtils.isEmpty(openCacheSearchDtoList)) {
            return;
        }
        List<CompletableFuture<Void>> futureList = openCacheSearchDtoList.stream().map(searchDTO -> executeWithAsync(searchDTO, resultList)).collect(Collectors.toList());
        try {
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).get(waitTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            LOGGER.warn("等待所有直连供应商报价异常", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

     /**
     * 异步执行
     *
     * @param dto                     dto
     * @param realTimePriceInfoVOList realTimePriceInfoVOList
     * @return java.util.concurrent.CompletableFuture<java.lang.Void>
     */
    private CompletableFuture<Void> executeWithAsync(LinkHotelRateSearchDTO dto, List<RealTimePriceInfoVO> realTimePriceInfoVOList) {
        String fybh = dto.getFybh();
        String zhmc = dto.getZhmc();
        return CompletableFuture.runAsync(() -> {
            RealTimePriceInfoVO timePriceInfoVO = new RealTimePriceInfoVO(fybh, zhmc, StringUtils.defaultString(dto.getHotelId(), dto.getLocalHotelId()), dto.getLocalHotelId(), PriceStatusEnum.TIME_OUT);
            realTimePriceInfoVOList.add(timePriceInfoVO);
            try {
                LinkHotelRateSearchVO rateSearchVO = rateSearchDistributeService.rateSearch(dto);
                List<SearchRoom> searchRoomList = Optional.ofNullable(rateSearchVO).map(LinkHotelRateSearchVO::getRooms).orElse(Collections.emptyList());
                searchRoomList.forEach(searchRoom -> {
                    if (CollectionUtils.isEmpty(searchRoom.getRatePlans())) {
                        return;
                    }
                      searchRoom.getRatePlans().forEach(searchRatePlan -> {
                        if (Objects.isNull(searchRatePlan)) {
                            return;
                        }
                        searchRatePlan.setBh(StringUtils.defaultString(searchRatePlan.getBh(), fybh));
                        searchRatePlan.setFyjc(zhmc);
                        searchRatePlan.setGyspt(dto.getSupplier().get("gyspt"));
                        if (CollectionUtils.isNotEmpty(searchRatePlan.getNightlyRates())) {
                            searchRatePlan.setFirstPrice(StringUtils.defaultIfBlank(searchRatePlan.getFirstPrice(), searchRatePlan.getNightlyRates().stream().min(Comparator.comparing(SearchNightlyRate::getDate)).map(SearchNightlyRate::getPriceAfterTax).get()));
                        }
                    });
                });

                // 汇率转换，注：实时计算最低价跟税费无关，暂不转换税费币种和汇率！！！
                if (CollectionUtils.isNotEmpty(searchRoomList)) {
                    LogUtils.runAndRecord(() -> exchangeRateService.convertCurrencyExchangeRate(dto, searchRoomList), NumConstant.NUM_100, time -> LOGGER.info("汇率转换耗时【{}】ms", time));
                }

                timePriceInfoVO.setSearchRoomList(searchRoomList);
                timePriceInfoVO.setPriceStatus(PriceStatusEnum.OK.getCode());
            } catch (SupplyConnectException e) {
                timePriceInfoVO.setPriceStatus(PriceStatusEnum.ERROR.getCode());
            }
        }, asyncRealTimePrice);
    }

}

    