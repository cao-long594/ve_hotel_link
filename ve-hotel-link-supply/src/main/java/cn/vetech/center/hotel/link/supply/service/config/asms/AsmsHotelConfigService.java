package cn.vetech.center.hotel.link.supply.service.config.asms;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.center.hotel.link.supply.service.config.asms.constant.MerchantStatusEnum;
import cn.vetech.center.hotel.link.supply.service.config.asms.constant.MerchantTypeEnum;
import cn.vetech.center.hotel.link.supply.service.config.asms.constant.SupplierStatusEnum;
import cn.vetech.center.hotel.link.supply.service.config.asms.request.SupplierConfigQryRequest;
import cn.vetech.center.hotel.link.supply.service.config.asms.response.SupplierConfig;
import cn.vetech.center.hotel.link.supply.service.config.asms.response.SupplierConfigQryResponse;
import cn.vetech.center.hotel.link.supply.service.config.asms.response.SupplyMerchantInfo;
import cn.vetech.center.hotel.link.supply.service.config.asms.response.SupplyParam;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.service.asms.AsmsApiComponent;
import cn.vetech.center.hotel.log.service.asms.constant.AsmsApiEnum;
import cn.vetech.charge.cpfl.CpbhEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * asms供应商配置service
 *
 * @author luqs
 * @version v1.0
 **/
@Service
public class AsmsHotelConfigService implements HotelConfigService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AsmsHotelConfigService.class);
    /**
     * asms api组件
     */
    @Autowired
    private AsmsApiComponent asmsApiComponent;

    @Override
    public List<HotelConfig> getConfigs(LinkHotelDTO dto) {
        SupplierConfigQryRequest request = convert2SupplierConfigQryRequest(dto, true);
        List<SupplyMerchantInfo> merchantInfoList = getHotelConfigList(dto.getFollowReqUrl(), request);
        if (CollectionUtils.isEmpty(merchantInfoList)) {
            logger.error("获取全部供应商配置信息为空，asms请求：【{}】", JacksonUtils.toJsonWithNonEmpty(request));
            return null;
        }
        return merchantInfoList.stream()
                .flatMap(merchantInfo -> merchantInfo.getConfigs().stream().map(config -> convert2HotelConfig(merchantInfo, config)))
                .collect(Collectors.toList());
    }

     @Override
    public HotelConfig getConfig(LinkHotelDTO dto) {
        if (StringUtils.isBlank(dto.getZhmc())) {
            logger.error("获取指定供应商配置信息的指定供应商为空，asms请求：【{}】", JacksonUtils.toJsonWithNonEmpty(dto));
            return null;
        }

        SupplierConfigQryRequest request = convert2SupplierConfigQryRequest(dto, false);
        List<SupplyMerchantInfo> merchantInfoList = getHotelConfigList(dto.getFollowReqUrl(), request);


        if (CollectionUtils.isEmpty(merchantInfoList)) {
            logger.error("获取指定供应商【{}】配置信息为空，asms请求：【{}】", dto.getZhmc(), JacksonUtils.toJsonWithNonEmpty(request));
            return null;
        }
        List<HotelConfig> configList = merchantInfoList.stream()
                .flatMap(merchantInfo -> merchantInfo.getConfigs().stream().map(config -> convert2HotelConfig(merchantInfo, config)))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configList)) {
            logger.error("获取指定供应商【{}】配置信息为空，asms请求：【{}】", dto.getZhmc(), JacksonUtils.toJsonWithNonEmpty(request));
            return null;
        }
        return configList.get(0);
    }
/**
     * 转换成 SupplierConfigQryRequest
     *
     * @param dto     入参
     * @param allFlag 是否全部配置
     * @return SupplierConfigQryRequest
     */
    private SupplierConfigQryRequest convert2SupplierConfigQryRequest(LinkHotelDTO dto, boolean allFlag) {
        SupplierConfigQryRequest request = new SupplierConfigQryRequest();
        request.setZgs(dto.getCompid());
        request.setQybh(dto.getHyid());
        request.setBmid(dto.getClkdeptid());
        request.setYgid(dto.getClkid());
        request.setCpbh(CpbhEnum.FL0300.getCode());
        if (!allFlag) {
            request.setGysbh(dto.getZhmc());
        }
        return request;
    }

    /**
     * 获取供应商配置
     *
     * @param apiUrl  url
     * @param request 请求
     * @return List<SupplyMerchantInfo>
     */
    private List<SupplyMerchantInfo> getHotelConfigList(String apiUrl, SupplierConfigQryRequest request) {
        try {

            String responseJson = getConfigFromHeader();
            if (StringUtils.isBlank(responseJson)) {
                responseJson = asmsApiComponent.post(apiUrl, AsmsApiEnum.MERCHANT_CONFIG, request);
            }

            SupplierConfigQryResponse response = JacksonUtils.parseNonEmpty(responseJson, SupplierConfigQryResponse.class);
            if (Objects.isNull(response) || !response.isSuccess() || CollectionUtils.isEmpty(response.getResult())) {
                logger.warn("[asms]获取供应商户配置失败，apiUrl：【{}】，请求：【{}】，响应：【{}】", apiUrl, JacksonUtils.toJsonWithNonEmpty(request), responseJson);
                return null;
            }

               List<SupplyMerchantInfo> merchantInfoList = response.getResult();
            List<SupplyMerchantInfo> handleList = new ArrayList<>();
            for (SupplyMerchantInfo merchantInfo : merchantInfoList) {
                if (!StringUtils.equals(MerchantStatusEnum.ON.getCode(), merchantInfo.getZt())) {
                    logger.warn("[asms]商户【{}_{}】状态【{}】未开启", merchantInfo.getMc(), merchantInfo.getBh(), merchantInfo.getZt());
                    continue;
                }

                if (!StringUtils.equalsAny(merchantInfo.getShlx(), MerchantTypeEnum.SUPPLIER.getCode(), MerchantTypeEnum.SUP_PRO.getCode())) {
                    logger.warn("[asms]商户【{}_{}】非供应商类型", merchantInfo.getMc(), merchantInfo.getBh());
                    continue;
                }

                List<SupplierConfig> supplierConfigList = merchantInfo.getConfigs();
                if (CollectionUtils.isEmpty(supplierConfigList)) {
                    logger.warn("[asms]商户【{}_{}】配置的酒店产品供应商信息为空", merchantInfo.getMc(), merchantInfo.getBh());
                    continue;
                }

                int validAccount = 0;
                for (SupplierConfig supplierConfig : supplierConfigList) {
                    if (!SupplierStatusEnum.ON.getCode().equals(supplierConfig.getZt())) {
                        logger.warn("[asms]供应商【{}_{}_{}_{}】状态【{}】未启用", merchantInfo.getMc(), merchantInfo.getBh(),
                                supplierConfig.getFymc(), supplierConfig.getFybh(), supplierConfig.getZt());
                        continue;
                    }
                    validAccount++;
                }

                if (validAccount > 1) {
                    logger.warn("[asms]商户【{}_{}】配置了多个启用的供应商账号，供应商信息：【{}】", merchantInfo.getMc(), merchantInfo.getBh()
                            , JacksonUtils.toJsonWithNonEmpty(supplierConfigList));
                    continue;
                }
                   handleList.add(merchantInfo);
            }
            return handleList;
        } catch (Exception e) {
            logger.error("[asms]获取供应商配置异常，请求：【{}】，请求：【{}】", apiUrl, JacksonUtils.toJsonWithNonEmpty(request), e);
        }
        return null;
    }

    /**
     * 转换成HotelConfig
     *
     * @param merchantInfo   商户信息
     * @param supplierConfig 供应商配置
     * @return HotelConfig
     */
    private HotelConfig convert2HotelConfig(SupplyMerchantInfo merchantInfo, SupplierConfig supplierConfig) {
        HotelConfig config = new HotelConfig();
        FyEnum fyEnum = FyEnum.instanceByFybh(supplierConfig.getFybh());
        fyEnum = Objects.isNull(fyEnum) ? FyEnum.instanceByGyspt(supplierConfig.getPt()) : fyEnum;
        if (Objects.nonNull(fyEnum)) {
            config.setFybh(fyEnum.getFybh());
            config.setFyen(fyEnum.getFyen());
        }
        config.setQybh(supplierConfig.getQybh());
        config.setFymc(supplierConfig.getFymc());
        config.setFyxsmc(merchantInfo.getXsmc());
        config.setZhmc(merchantInfo.getBh());
        config.setGyspt(supplierConfig.getPt());

        List<SupplyParam> supplyParamList = supplierConfig.getParamList();
        if (CollectionUtils.isEmpty(supplyParamList)) {
            return config;
        }

        Map<String, String> paramMap = supplyParamList.stream()
                .collect(Collectors.toMap(SupplyParam::getName, SupplyParam::getValue));
        // 下单状态
        putIfNotExist(paramMap, "xdzt", supplierConfig.getXdzt());
        // 房源编号
        putIfNotExist(paramMap, "fybh", config.getFybh());
        // 房源显示名称
        putIfNotExist(paramMap, "fyxsmc", StringUtils.defaultIfBlank(merchantInfo.getXsmc(), merchantInfo.getMc()));
        // 账户名称
        putIfNotExist(paramMap, "zhmc", config.getZhmc());
        // tcext
        if (fyEnum != null && fyEnum.isTcext() && StringUtils.isBlank(paramMap.get(HotelLinkConstant.SUPPLY_PARAM_NAME_TCEXT))) {
            String val = String.join(SymbolConstant.COMMA, FyEnum.ELONG.getFybh(), FyEnum.XCW.getFybh());
            paramMap.put(HotelLinkConstant.SUPPLY_PARAM_NAME_TCEXT, val);
        }
        config.setConfigMap(paramMap);
        return config;
    }

    /**
     * 转换成HotelConfig
     *
     * @param merchantInfo   商户信息
     * @param supplierConfig 供应商配置
     * @return HotelConfig
     */
    private HotelConfig convert2HotelConfig(SupplyMerchantInfo merchantInfo, SupplierConfig supplierConfig) {
        HotelConfig config = new HotelConfig();
        FyEnum fyEnum = FyEnum.instanceByFybh(supplierConfig.getFybh());
        fyEnum = Objects.isNull(fyEnum) ? FyEnum.instanceByGyspt(supplierConfig.getPt()) : fyEnum;
        if (Objects.nonNull(fyEnum)) {
            config.setFybh(fyEnum.getFybh());
            config.setFyen(fyEnum.getFyen());
        }
        config.setQybh(supplierConfig.getQybh());
        config.setFymc(supplierConfig.getFymc());
        config.setFyxsmc(merchantInfo.getXsmc());
        config.setZhmc(merchantInfo.getBh());
        config.setGyspt(supplierConfig.getPt());

        List<SupplyParam> supplyParamList = supplierConfig.getParamList();
        if (CollectionUtils.isEmpty(supplyParamList)) {
            return config;
        }

        Map<String, String> paramMap = supplyParamList.stream()
                .collect(Collectors.toMap(SupplyParam::getName, SupplyParam::getValue));
        // 下单状态
        putIfNotExist(paramMap, "xdzt", supplierConfig.getXdzt());
        // 房源编号
        putIfNotExist(paramMap, "fybh", config.getFybh());
        // 房源显示名称
        putIfNotExist(paramMap, "fyxsmc", StringUtils.defaultIfBlank(merchantInfo.getXsmc(), merchantInfo.getMc()));
        // 账户名称
        putIfNotExist(paramMap, "zhmc", config.getZhmc());
        // tcext
        if (fyEnum != null && fyEnum.isTcext() && StringUtils.isBlank(paramMap.get(HotelLinkConstant.SUPPLY_PARAM_NAME_TCEXT))) {
            String val = String.join(SymbolConstant.COMMA, FyEnum.ELONG.getFybh(), FyEnum.XCW.getFybh());
            paramMap.put(HotelLinkConstant.SUPPLY_PARAM_NAME_TCEXT, val);
        }
        config.setConfigMap(paramMap);
        return config;
    }