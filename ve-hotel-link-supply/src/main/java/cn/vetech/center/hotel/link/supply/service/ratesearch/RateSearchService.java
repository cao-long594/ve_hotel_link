package cn.vetech.center.hotel.link.supply.service.ratesearch;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.HotelFaceCodeEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.async.AsyncService;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.link.supply.service.distribute.SupplyDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.hotel.HotelDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.bean.CommonLogBean;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.cloud.cache.redis.config.VeRedisLockManage;
import cn.vetech.charge.cloud.modules.utils.IdWorker;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.commlog.api.vo.CommLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RateSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateSearchService.class);
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     *
     */
    @Autowired
    private HotelDistributeService hotelService;
    /**
     *
     */
    @Autowired
    private RateSearchAsyncService rateSearchAsyncService;
    /**
     *
     */
    @Autowired
    private SupplyDistributeService supplyDistributeService;

    /**
     *
     */
    @Autowired
    private AsyncService asyncService;
    /**
     *
     */
    @Autowired
    private VeRedisLockManage lockManage;
    /**
     * 国内
     */
    public static final String CONFIG_GN = "GN";
    /**
     * 港澳台
     */
    public static final String CONFIG_GAT = "GAT";
    /**
     * 国际
     */
    public static final String CONFIG_GJ = "GJ";


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RateSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateSearchService.class);
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     *
     */
    @Autowired
    private HotelDistributeService hotelService;
    /**
     *
     */
    @Autowired
    private RateSearchAsyncService rateSearchAsyncService;
    /**
     *
     */
    @Autowired
    private SupplyDistributeService supplyDistributeService;

    /**
     *
     */
    @Autowired
    private AsyncService asyncService;
    /**
     *
     */
    @Autowired
    private VeRedisLockManage lockManage;
    /**
     * 国内
     */
    public static final String CONFIG_GN = "GN";
    /**
     * 港澳台
     */
    public static final String CONFIG_GAT = "GAT";
    /**
     * 国际
     */
    public static final String CONFIG_GJ = "GJ";
 if (StringUtils.isBlank(dto.getCacheKey())) {
            dto.setCacheKey("cachekey-" + IdWorker.get32UUID());
        }
        //异步查询
        String lockKey = dto.getCacheKey() + "lock_key";
        RLock lock = lockManage.getLock("ve-link-hotel", lockKey);
        //根据酒店映射和供应商配置 生成各个供应商请求
        List<LinkHotelRateSearchDTO> dtos = getDtosAndFilter(dto, configs);
        try {
            boolean tryLock = lock.tryLock(60, TimeUnit.SECONDS);
            if (!tryLock) {
                vo.setErrorMsg("查询正在进行中cacheKey:" + dto.getCacheKey());
                return vo;
            }
            commLog.add("获取的配置" + JacksonUtils.toJsonWithNonEmpty(dtos));
            if (CollectionUtils.isEmpty(dtos)) {
                vo.setStatus(LinkHotelVO.FAIL);
                vo.setSfwc(true);
                vo.setErrorMsg("需要查询的供应商为空");
                asyncService.putCacheWithThirtySecond(dto, vo);
                return vo;
            }
            dtos.stream().map(rateSearchDto ->
                    //查询单个供应商
                    rateSearchAsyncService.rateSearch(rateSearchDto)
            ).collect(Collectors.toList()).forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error("查询单个房源异常", e);
                    Thread.currentThread().interrupt();
                }
            });//这里设置
            vo = getCacheVo(dto);
        } catch (InterruptedException e) {
            LOGGER.error("查询报价异常", e);
            Thread.currentThread().interrupt();
        } finally {
            asyncService.removeFhsd(dto);
            lockManage.unlock(lock);
        }
        return vo;
    }

     /**
     * 转换并设置mapper，如果入参指定了房源商，这按照指定的进行查询
     * 如果房源编号里面有艺龙，则添加至同程的映射上
     *
     * @param dto     dto
     * @param mappers mappers
     */
    protected static void convertAndSetMappers(LinkHotelRateSearchDTO dto, List<Mapper> mappers) {
        dto.setMappers(mappers);
        //添加本地酒店ID
        if (CollectionUtils.isNotEmpty(mappers)) {
            Mapper mapper = new Mapper();
            mapper.setFybh(HotelFaceCodeEnum.LOCAL.getJkbh());
            mapper.setHotelid(dto.getLocalHotelId());
            mappers.add(mapper);
        }
        if (StringUtils.isNotBlank(dto.getFybh()) && StringUtils.isNotBlank(dto.getHotelId())) {
            mappers.clear();
            Mapper mapper = new Mapper();
            mapper.setFybh(dto.getFybh());
            mapper.setHotelid(dto.getHotelId());
            mappers.add(mapper);
            dto.setMappers(mappers);
            return;
        }

         /**
     * 转换并设置mapper，如果入参指定了房源商，这按照指定的进行查询
     * 如果房源编号里面有艺龙，则添加至同程的映射上
     *
     * @param dto     dto
     * @param mappers mappers
     */
    protected static void convertAndSetMappers(LinkHotelRateSearchDTO dto, List<Mapper> mappers) {
        dto.setMappers(mappers);
        //添加本地酒店ID
        if (CollectionUtils.isNotEmpty(mappers)) {
            Mapper mapper = new Mapper();
            mapper.setFybh(HotelFaceCodeEnum.LOCAL.getJkbh());
            mapper.setHotelid(dto.getLocalHotelId());
            mappers.add(mapper);
        }
        if (StringUtils.isNotBlank(dto.getFybh()) && StringUtils.isNotBlank(dto.getHotelId())) {
            mappers.clear();
            Mapper mapper = new Mapper();
            mapper.setFybh(dto.getFybh());
            mapper.setHotelid(dto.getHotelId());
            mappers.add(mapper);
            dto.setMappers(mappers);
            return;
        }

        /**
     * 合并后
     *
     * @param dto     dto
     * @param configs configs
     * @return java.util.List<cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO>
     */
    protected List<LinkHotelRateSearchDTO> getDtosAndFilter(LinkHotelRateSearchDTO dto, List<Map<String, String>> configs) {
        List<LinkHotelRateSearchDTO> searchDTOS = getDtos(dto, configs);
        // 费控云增加设置直连集团的酒店不查询其它接口供应商价格(一般包含CPS和OTA接口)
        searchDTOS = convertZljtfybh(searchDTOS, dto);
        // 费控增加设置只查询指定国内国际港澳台的资源
        searchDTOS = convertZdcxgngj(searchDTOS, dto);
        return searchDTOS;
    }

    /**
     * @param searchDTOS searchDTOS
     * @param dto        dto
     */
    private List<LinkHotelRateSearchDTO> convertZdcxgngj(List<LinkHotelRateSearchDTO> searchDTOS, LinkHotelRateSearchDTO dto) {
        if (CollectionUtils.isEmpty(searchDTOS)) {
            return searchDTOS;
        }
        // gngj:1国际，其他国内
        String gngj = StringUtils.isBlank(dto.getGngj()) ? GnGjTypeEnum.GN.getCode() : dto.getGngj();
        // sfgat:1是港澳台，其他不是
        String sfgat = StringUtils.isBlank(dto.getSfgat()) ? "0" : dto.getSfgat();
        return searchDTOS.stream().filter(item -> {
            // 0：国际， 1：国内(中国大陆)，3：港澳台；多个逗号拼接
            String zdcxgngj = item.getSupplier().get("zdcxgngj");
            if (StringUtils.isBlank(zdcxgngj)) {
                return true;
            }
            List<String> zdcxgngjList = Arrays.asList(zdcxgngj.replace(SymbolConstant.FULL_COMMA, SymbolConstant.COMMA).split(SymbolConstant.COMMA));
            // 先判断是否港澳台
            if ("1".equals(sfgat)) {
                return zdcxgngjList.contains("3");
            } else if (GnGjTypeEnum.GJ.getCode().equals(gngj)) {
                return zdcxgngjList.contains("0");
            } else if (GnGjTypeEnum.GN.getCode().equals(gngj)) {
                return zdcxgngjList.contains("1");
            }
            return true;
                }).collect(Collectors.toList());
    }

/**
     * @param searchDTOS searchDTOS
     * @param dto        dto
     */
    private List<LinkHotelRateSearchDTO> convertZljtfybh(List<LinkHotelRateSearchDTO> searchDTOS, LinkHotelRateSearchDTO dto) {
        if (CollectionUtils.isEmpty(searchDTOS)) {
            return searchDTOS;
        }
        String zljtfybh = dto.getZljtfybh();
        if (StringUtils.equals("1", dto.getZljtjdcqtjkjg()) && StringUtils.isNotBlank(zljtfybh)) {
            LOGGER.info("指定供应商【{}】", zljtfybh);
            List<String> zljtfybhList = Arrays.asList(zljtfybh.replace(SymbolConstant.FULL_COMMA, SymbolConstant.COMMA).split(SymbolConstant.COMMA));
            List<LinkHotelRateSearchDTO> filterDtoList = searchDTOS.stream()
                    .filter(item -> zljtfybhList.contains(item.getFybh()))
                    .collect(Collectors.toList());
            // filterDtoList为空，mappers中存在指定的直连集团房源编号，则不查询其他供应商价格（一般是指定了供应商，但是账号未开启）
            List<Mapper> mapperList = dto.getMappers().stream()
                    .filter(item -> zljtfybhList.contains(item.getFybh()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterDtoList) || CollectionUtils.isNotEmpty(mapperList)) {
                searchDTOS = filterDtoList;
            }
        }
        return searchDTOS;
    }

    /**
     * @param dto     1
     * @param configs 1
     * @return 1
     */
    private List<LinkHotelRateSearchDTO> getDtos(LinkHotelRateSearchDTO dto, List<Map<String, String>> configs) {
        if (!StringUtils.equals("1", dto.getSfpbcpsjg())) {
            Mapper cpsM = new Mapper();
            cpsM.setFybh(FyEnum.CPS.getFybh());
            dto.getMappers().add(cpsM);
        }
        Mapper asmsM = new Mapper();
        asmsM.setFybh(FyEnum.ASMS.getFybh());
        dto.getMappers().add(asmsM);
        Map<String, List<Mapper>> mapperMap = dto.getMappers().stream().collect(Collectors.groupingBy(Mapper::getFybh));
        // 处理拓展标准供应商映射
        convertMapperMap(mapperMap);
        return configs.stream()
                .filter(config -> {
                    String fybh = config.get("fybh");
                    //1.cps平台请求 不用走cps 也不走asms
                    //2.差旅云平台请求 不用走cps(差旅云自己调用的cps)
                    //3.费控平台请求 都得走
                    if (StringUtils.equals(dto.getPt(), PtEnum.CPS.getValue())) {
                        if (StringUtils.equals(fybh, FyEnum.CPS.getFybh()) || StringUtils.equals(fybh, FyEnum.ASMS.getFybh())) {
                            return false;
                        }
                    }
                     //2
                    if (StringUtils.equals(dto.getPt(), PtEnum.CLOUD.getValue())) {
                        if (StringUtils.equals(fybh, FyEnum.CPS.getFybh())) {
                            return false;
                        }
                    }
                    //3
                    //映射中有 就需要请求
                    if (mapperMap.containsKey(fybh)) {
                        // 拓展标准供应商
                        if (FyEnum.EXTEND.getFybh().equals(fybh)) {
                            String tcext = config.get("tcext");
                            if (StringUtils.isBlank(tcext)) {
                                return false;
                            }
                            return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                                    .anyMatch(mapperMap::containsKey);
                        }
                        return true;
                    }
                    // 映射没有 需要判断是否为tcext房源  同时tcext房源使用的映射是否在映射中
                    String tcext = config.get("tcext");
                    if (StringUtils.isBlank(tcext)) {
                        return false;
                    }
                    // 处理tcext配置参数
                    tcext = convertTcext(tcext, config.get("originalTcext"), dto.getGngj(), dto.getSfgat());
                    if (StringUtils.isBlank(tcext)) {
                        LOGGER.info("符合配置的房源编号为空过滤：【{}】", fybh);
                        return false;
                    }
                    config.put("tcext", tcext);
                    return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                            .anyMatch(mapperMap::containsKey);
                })
                 //2
                    if (StringUtils.equals(dto.getPt(), PtEnum.CLOUD.getValue())) {
                        if (StringUtils.equals(fybh, FyEnum.CPS.getFybh())) {
                            return false;
                        }
                    }
                    //3
                    //映射中有 就需要请求
                    if (mapperMap.containsKey(fybh)) {
                        // 拓展标准供应商
                        if (FyEnum.EXTEND.getFybh().equals(fybh)) {
                            String tcext = config.get("tcext");
                            if (StringUtils.isBlank(tcext)) {
                                return false;
                            }
                            return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                                    .anyMatch(mapperMap::containsKey);
                        }
                        return true;
                    }
                    // 映射没有 需要判断是否为tcext房源  同时tcext房源使用的映射是否在映射中
                    String tcext = config.get("tcext");
                    if (StringUtils.isBlank(tcext)) {
                        return false;
                    }
                    // 处理tcext配置参数
                    tcext = convertTcext(tcext, config.get("originalTcext"), dto.getGngj(), dto.getSfgat());
                    if (StringUtils.isBlank(tcext)) {
                        LOGGER.info("符合配置的房源编号为空过滤：【{}】", fybh);
                        return false;
                    }
                    config.put("tcext", tcext);
                    return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                            .anyMatch(mapperMap::containsKey);
                })

                 /**
     * @param tcext tcext
     * @param gngj  gngj
     * @param sfgat sfgat
     * @return String
     */
    private String convertTcext(String tcext, String originalTcext, String gngj, String sfgat) {
        // 国际酒店查询报价，tcext没有配置，不查报价
        if (StringUtils.isBlank(originalTcext) && GnGjTypeEnum.GJ.getCode().equals(gngj)) {
            tcext = StringUtils.EMPTY;
        }
        // 国内国际分开配置，格式：GN:31200823,31200830_GJ:31200903,31200832_GAT:31200801
        // 不区分国内国际的格式：31200823,31200830,31200801
        tcext = tcext.replaceAll(SymbolConstant.FULL_COLON, SymbolConstant.HALF_COLON).replaceAll(SymbolConstant.FULL_COMMA, SymbolConstant.COMMA);
        if (!StringUtils.containsAny(tcext, SymbolConstant.HALF_COLON, SymbolConstant.UNDER_LINE)) {
            return tcext;
        }
        String tcextStr = null;
        Map<String, String> gngjTypeAndFybhMap = new HashMap<>();
        String[] gngjTypeAndFybhList = tcext.split(SymbolConstant.UNDER_LINE);
        for (String str : gngjTypeAndFybhList) {
            if (!str.contains(SymbolConstant.HALF_COLON)) {
                continue;
            }
            String[] split = str.split(SymbolConstant.HALF_COLON);
            gngjTypeAndFybhMap.put(split[0], split[1]);
        }
        if ("1".equals(sfgat)) {
            tcextStr = StringUtils.defaultString(gngjTypeAndFybhMap.get(CONFIG_GAT), gngjTypeAndFybhMap.get(CONFIG_GN));
        } else if (GnGjTypeEnum.GJ.getCode().equals(gngj)) {
            tcextStr = gngjTypeAndFybhMap.get(CONFIG_GJ);
        } else {
            tcextStr = gngjTypeAndFybhMap.get(CONFIG_GN);
        }
        return tcextStr;
    }

    /**
     * @param mapperMap mapperMap
     */
    private void convertMapperMap(Map<String, List<Mapper>> mapperMap) {
        List<Mapper> extendMapperList = mapperMap.get(FyEnum.EXTEND.getFybh());
        if (CollectionUtils.isEmpty(extendMapperList)) {
            return;
        }
        String hotelId = extendMapperList.get(0).getHotelid();
        if (StringUtils.isBlank(hotelId)) {
            return;
        }
        // fybh1=hotelId1@fybh2=hotelId2@fybh3=hotelId3
        String[] fybhAndHotelIds = hotelId.split(SymbolConstant.A_SIGN);
        for (String fybhAndHotelId : fybhAndHotelIds) {
            String[] split = fybhAndHotelId.split(SymbolConstant.EQUAL);
            if (split.length != NumConstant.NUM_2) {
                continue;
            }
            String extendFybh = split[0];
            String extendHotelId = split[1];
            Mapper mapper = new Mapper();
            mapper.setFybh(extendFybh);
            mapper.setFybh(extendHotelId);
            mapperMap.put(extendFybh, Collections.singletonList(mapper));
        }
    }

     /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelRateSearchVO getCacheVo(LinkHotelRateSearchDTO dto) {
        LinkHotelRateSearchVO cacheVo = asyncService.getCache(dto);
        if (cacheVo == null) {
            return null;
        }
        cacheVo.setSfwc(true);
        asyncService.putCache(dto, cacheVo);
        return cacheVo;
    }


}

    
                    
