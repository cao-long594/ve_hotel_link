package cn.vetech.center.hotel.link.supply.service.distribute.config;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.center.hotel.link.supply.base.exception.HotelConfigExcetion;
import cn.vetech.center.hotel.log.annotation.Log;
import cn.vetech.charge.cloud.cache.api.IVeCacheManage;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.collection.MapUtil;
import cn.vetech.charge.cloud.modules.utils.localcache.LocalCacheUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import com.google.common.cache.Cache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author lipeng
 */
@Service
public class HotelConfigDistributeService {
    /**
     *
     */
    @Autowired
    private Map<String, HotelConfigService> configServiceMap;
    /**
     *
     */
    @Autowired
    private IVeCacheManage cacheManage;
    /**
     *
     */
    private Cache<String, Map<String, String>> configLocalCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_700, CACHE_TIME, TimeUnit.SECONDS);

    /**
     * 所有供应商配置
     */
   private Cache<String, List<Map<String, String>>> allConfigLocalCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_1000, CACHE_TIME, TimeUnit.SECONDS);



    /**
     *
     */
    private static final int CACHE_TIME = 600;

    /**
     * 获取cps平台供应商配置
     * 优先从cgetCpsPlatformSuppler中获取如果获取不到再调用getConfig(dto, FyEnum.CPS.getGyspt());
     * 如果再获取不到则从defaultCpsPlatformSuppler获取
     * 这个方法其实是在getConfig(dto, FyEnum.CPS.getGyspt())封装一层
     *
     * 如果使用异步调用该方法，同时后续还使用到ygys字段可能会造成问题，这里如果是因私先设置为了因公，查到配置再次设置为因私，其目的在于获取cps供应商账号
     */
      @Log(name = "获取cps平台供应商配置", logParam = false, logReturn = true)
    public Map<String, String> getCpsPlatformSuppler(LinkHotelDTO dto) {
        //优先取cps平台供应商配置
        Map<String, String> cpsSupplierFromCall = dto.getCpsPlatformSuppler();
        if (MapUtils.isNotEmpty(cpsSupplierFromCall)) {
            return cpsSupplierFromCall;
        }
        //这里处理因公因私,其处理目的在于 客户配置了供应商 服务设置未配置因私，因此查询报价需要根据因公因私来匹配供应商。因此缓存的key也拼接了因公因私
        //但是一部分接口需要调cps，比如查询酒店列表接口，很多客户在cps账号服务设置上没有配置因私，因此可能会导致因此查不到cps账号因此这里做了一个兼容，
        // 当然如果费控调用时传入了。cps平台账号，那么就不会存在该问题，该功能同时做的，但是不能保证程序的更新顺序。
        //出行类型：1因公出行 2因私出行
        String ygys = dto.getYgys();
        if (StringUtils.equals(ygys, "2")) {
            dto.setYgys("1");
        }
        //再调用服务获取cps平台账号（供应商配置里面的）
        Optional<Map<String, String>> cpsConfigOpt = getCpsConfig(dto);
        dto.setYgys(ygys);
        if (cpsConfigOpt.isPresent()) {
            return cpsConfigOpt.get();
        }
        //最后取cps平台配置
        Map<String, String> platformSuppler = dto.getDefaultCpsPlatformSuppler();
        if (MapUtils.isNotEmpty(platformSuppler)) {
            return platformSuppler;
        }
        //查询平台时
        throw new HotelConfigExcetion(dto.getFybh(), dto.getCompid(), dto.getZhmc());
    }
  /**
     * @param dto 1
     * @return 1
     */
    @Log(name = "获取所有供应商配置", logParam = false, logReturn = true)
    public List<Map<String, String>> getConfigs(LinkHotelDTO dto) {
        LinkHotelDTO configDto = BeanMapper.map(dto, LinkHotelDTO.class);
        configDto.setFybh(null);
        configDto.setZhmc(null);
        if (CollectionUtils.isNotEmpty(dto.getMerchantCodeList())) {
            return getConfigsBySuppler(dto);
        }
        String cacheKey = getCacheKey(dto, null);
        List<Map<String, String>> cacheConfig = allConfigLocalCache.getIfPresent(cacheKey);
        if (CollectionUtils.isNotEmpty(cacheConfig)){
            return cacheConfig;
        }
        List<HotelConfig> configs = getService(dto).getConfigs(configDto);
        if (ListUtil.isEmpty(configs)) {
            return null;
        }
        cacheConfig = configs.stream().map(this::convertConfig).filter(Objects::nonNull).collect(Collectors.toList());
        if (ListUtil.isEmpty(cacheConfig)) {
            return cacheConfig;
        }
        allConfigLocalCache.put(cacheKey, cacheConfig);
        return cacheConfig;
    }

     /**
     * 根据费控入参进行查询指定供应商配置
     * @param dto dto
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    private List<Map<String, String>> getConfigsBySuppler(LinkHotelDTO dto) {
        String compid = dto.getCompid();
        String hyid = dto.getHyid();
        String ydrfrgs = dto.getYdrfrgs();
        String merchantCodeStr = String.join("", dto.getMerchantCodeList());
        String cacheKey = String.join("_", "CONFIG_BY_MERCHAN", compid, hyid, ydrfrgs, merchantCodeStr);
        List<Map<String, String>> configByMerchantList = allConfigLocalCache.getIfPresent(cacheKey);
        if (CollectionUtils.isNotEmpty(configByMerchantList)) {
            return configByMerchantList;
        }
        List<HotelConfig> configs = getService(dto).getConfigByMerchantList(dto);
         configByMerchantList = configs.stream().map(this::convertConfig).filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configByMerchantList)) {
            return Collections.emptyList();
        }
        allConfigLocalCache.put(cacheKey, configByMerchantList);
        return configByMerchantList;
    }

    /**
     * @param dto 1
     * @return 1
     */
    @Log(name = "设置单个供应商配置", logParam = false, logReturn = true)
    public Map<String, String> setConfig(LinkHotelDTO dto) {
        Map<String, String> config = getConfig(dto);
        dto.setSupplier(config);
        return config;
    }

    /**
     * @param dto 1
     * @return 1
     */
    @Log(name = "获取单个供应商配置", logParam = false, logReturn = true, time = true)
    public Map<String, String> getConfig(LinkHotelDTO dto) {
        String cacheKey = getCacheKey(dto, null);
        Map<String, String> cacheConfig = configLocalCache.getIfPresent(cacheKey);
        if (cacheConfig != null) {
            return new HashMap<>(cacheConfig);
        }
        HotelConfig config = getService(dto).getConfig(dto);
        cacheConfig = convertConfig(config);
        if (MapUtil.isEmpty(cacheConfig)) {
            throw new HotelConfigExcetion(dto.getFybh(), dto.getCompid(), dto.getZhmc());
        }
        configLocalCache.put(cacheKey, cacheConfig);
        return new HashMap<>(cacheConfig);
    }

/**
     * @param dto 1
     * @return 1
     */
    @Log(name = "设置单个供应商配置", logParam = false, logReturn = true)
    public Map<String, String> setConfig(LinkHotelDTO dto) {
        Map<String, String> config = getConfig(dto);
        dto.setSupplier(config);
        return config;
    }

    /**
     * @param dto 1
     * @return 1
     */
    @Log(name = "获取单个供应商配置", logParam = false, logReturn = true, time = true)
    public Map<String, String> getConfig(LinkHotelDTO dto) {
        String cacheKey = getCacheKey(dto, null);
        Map<String, String> cacheConfig = configLocalCache.getIfPresent(cacheKey);
        if (cacheConfig != null) {
            return new HashMap<>(cacheConfig);
        }
        HotelConfig config = getService(dto).getConfig(dto);
        cacheConfig = convertConfig(config);
        if (MapUtil.isEmpty(cacheConfig)) {
            throw new HotelConfigExcetion(dto.getFybh(), dto.getCompid(), dto.getZhmc());
        }
        configLocalCache.put(cacheKey, cacheConfig);
        return new HashMap<>(cacheConfig);
    }

 /**
     * 转换
     *
     * @param config 配置
     * @return Map<String, String>
     */
    private Map<String, String> convertConfig(HotelConfig config) {
        if (config == null) {
            return null;
        }
        Map<String, String> configMap = BeanMapper.map(config, HashMap.class);
        configMap.remove("configMap");
        if (MapUtil.isNotEmpty(config.getConfigMap())) {
            Map<String, String> hotelConfigMap = config.getConfigMap();
            //对所有configmap 配置属性去空格处理
            Map<String, String> trimHotelConfigMap = hotelConfigMap.entrySet().stream()
                    .collect(HashMap::new, (m, v) -> m.put(StringUtils.trim(v.getKey()), StringUtils.trim(v.getValue())), HashMap::putAll);
            configMap.putAll(trimHotelConfigMap);
        }
        return configMap;
    }

    /**
     * 获取service
     *
     * @param dto 参数
     * @return 服务
     */
    private HotelConfigService getService(LinkHotelDTO dto) {
        String serviceName = dto.getPt() + HotelConfigService.NAME;
        HotelConfigService service = configServiceMap.get(serviceName);
        if (service == null) {
            service = configServiceMap.get("default" + HotelConfigService.NAME);
        }
        return service;
    }
}
