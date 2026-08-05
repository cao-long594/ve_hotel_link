package cn.vetech.center.hotel.link.supply.service.config.charge;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.client.gys.charge.IMerchantSupplyServiceClient;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.base.feign.merchant.dto.MatchingMerchantSupplyDTO;
import cn.vetech.charge.base.feign.merchant.dto.ShZhxxSearchDTO;
import cn.vetech.charge.base.feign.merchant.vo.MatchingMerchantSupplyVO;
import cn.vetech.charge.base.feign.merchant.vo.MerchantInfoVO;
import cn.vetech.charge.base.feign.merchant.vo.MerchantKeyVO;
import cn.vetech.charge.base.feign.merchant.vo.ShZhxxExVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ChargeHotelConfigService implements HotelConfigService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeHotelConfigService.class);
    /**
     *
     */
    @Autowired
    private IMerchantSupplyServiceClient merchantScopeServiceClient;

    /**
     * @param dto 1
     * @return 1
     */
    @Override
    public List<HotelConfig> getConfigs(LinkHotelDTO dto) {
        MatchingMerchantSupplyDTO supplyDTO = new MatchingMerchantSupplyDTO();
        supplyDTO.setZgs(dto.getCompid());
        supplyDTO.setQybh(dto.getHyid());
        supplyDTO.setYgid(dto.getClkid());
        supplyDTO.setBmid(dto.getClkdeptid());
        supplyDTO.setFrgsid(dto.getYdrfrgs());
        supplyDTO.setCpbh("0300");
        supplyDTO.setClyy(dto.getYgys());
        supplyDTO.setCbzxid(dto.getCbzxid());
        supplyDTO.setCbzxbh(dto.getCbzxbh());

        List<MerchantInfoVO> merchantInfoVOList = getConfigFromRemote(supplyDTO);
        return merchantInfoVOList.stream()
                .filter(gys -> ListUtil.isNotEmpty(gys.getZhjh()))
                .flatMap(gys -> gys.getZhjh().stream()
                        .filter(zh -> StringUtils.equals(zh.getZt(), "1"))
                        .map(zh -> convertConfig(dto, gys, zh)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

     /**
     * @param dto 1
     * @return 1
     */
    @Override
    public List<HotelConfig> getConfigByMerchantList(LinkHotelDTO dto) {

        ShZhxxSearchDTO searchDTO = new ShZhxxSearchDTO();
        searchDTO.setZgs(dto.getCompid());
        searchDTO.setCpbh("0300");
        searchDTO.setShbh(String.join(",",dto.getMerchantCodeList()));
        RestResponse<List<MerchantKeyVO>> restResponse = CommonLogContext.addCommonLogAndRecordifPresent(StringUtils.join("根据指定供应商调用base获取供应商:",JacksonUtils.toJsonWithNonEmpty(searchDTO)), () -> {
            RestResponse<List<MerchantKeyVO>> merchantListRes = null;
            try {
                merchantListRes = merchantScopeServiceClient.searchShZhxxList(searchDTO);
                String bhStrJoin = Optional.ofNullable(merchantListRes).map(RestResponse::getResult)
                        .map(merchantInfoVOS -> merchantInfoVOS.stream().map(MerchantKeyVO::getShbh).collect(Collectors.joining(","))).orElse(StringUtils.EMPTY);
                LOGGER.info("根据指定供应商调用base获取供应商,总公司:{},请求参数:{},响应的供应商编号:{}", searchDTO.getZgs(), JsonMapperUtil.toJsonStr(searchDTO), bhStrJoin);
            } catch (SystemException e) {
                LOGGER.error("根据指定供应商调用base获取供应商,请求:{}", JacksonUtils.toJsonWithNonEmpty(searchDTO), e);
            }
            return merchantListRes;
        });
         List<MerchantKeyVO> merchantList = restResponse.getResult();
            if (CollectionUtils.isEmpty(merchantList)) {
                return Collections.emptyList();
            }
            return merchantList.stream()
                            .filter(zh -> StringUtils.equals(zh.getZt(), "1"))
                            .map(zh ->{
                                MerchantInfoVO infoVO = new MerchantInfoVO();
                                infoVO.setXsmc(zh.getShbh());
                                return convertConfig(dto, infoVO, zh);
                            })
                    .collect(Collectors.toList());
    }

    /**
     * @param dto 1
     * @return 1
     */
    @Override
    public HotelConfig getConfig(LinkHotelDTO dto) {
        if (StringUtils.isBlank(dto.getZhmc())) {
            return null;
        }
        MatchingMerchantSupplyDTO supplyDTO = new MatchingMerchantSupplyDTO();
        supplyDTO.setZgs(dto.getCompid());
        supplyDTO.setQybh(dto.getHyid());
        supplyDTO.setYgid(dto.getClkid());
        supplyDTO.setBmid(dto.getClkdeptid());
        supplyDTO.setCpbh("0300");
        supplyDTO.setClyy(dto.getYgys());
        supplyDTO.setGysbh(dto.getZhmc());
        supplyDTO.setFrgsid(dto.getYdrfrgs());
        List<MerchantInfoVO> merchantInfoVOList = getConfigFromRemote(supplyDTO);
        Optional<HotelConfig> configP = merchantInfoVOList.stream()
                .filter(gys -> ListUtil.isNotEmpty(gys.getZhjh()))
                .flatMap(gys -> gys.getZhjh().stream()
                        .filter(zh -> "1".equals(dto.getServiceType()) || StringUtils.equals(zh.getZt(), "1"))
                        .map(zh -> convertConfig(dto, gys, zh)))
                .filter(Objects::nonNull).findFirst();
        return configP.orElse(null);
    }

    /**
     * @param dto 1
     * @param gys 1
     * @param vo  1
     * @return 1
     */
    private HotelConfig convertConfig(LinkHotelDTO dto, MerchantInfoVO gys, MerchantKeyVO vo) {
        HotelConfig config = new HotelConfig();
        FyEnum fyEnum = FyEnum.instanceByGyspt(vo.getPt());
        if (fyEnum == null) {
            fyEnum = FyEnum.instanceByFybh(dto.getFybh());
        }
        if (fyEnum != null) {
            config.setFybh(fyEnum.getFybh());
            config.setFyen(fyEnum.getFyen());
        }
        config.setQybh(dto.getCompid());
        config.setFymc(gys.getXsmc());
        config.setFyxsmc(gys.getXsmc());
        config.setZhmc(vo.getShbh());
        config.setGyspt(vo.getPt());
        List<ShZhxxExVO> csjh = vo.getCsjh();
        if (ListUtil.isEmpty(csjh)) {
            config.setConfigMap(new HashMap<>());
            return config;
        }
        Map<String, String> configMap = new HashMap<>();
        csjh.forEach(cs -> configMap.put(cs.getCsmc(), cs.getCsz()));
        if (StringUtils.isBlank(configMap.get("fybh")) && fyEnum != null) {
            configMap.put("fybh", fyEnum.getFybh());
        }
        if (fyEnum != null && fyEnum.isTcext()) {
            String tcext = configMap.get("tcext");
            String tcextStr = convertTcext(tcext);
            configMap.put("tcext", tcextStr);
            configMap.put("originalTcext", tcext);
        }
        config.setConfigMap(configMap);
        return config;
    }

      /**
     * @param tcext tcext
     * @return String
     */
    private String convertTcext(String tcext) {
        if (StringUtils.isNotBlank(tcext)) {
            return tcext;
        }
        // 默认需要传 elong xcw mtw
        return String.join(SymbolConstant.COMMA, FyEnum.ELONG.getFybh(), FyEnum.XCW.getFybh(), FyEnum.MTW.getFybh());
    }

    /**
     * 从base服务获取 商户配置信息
     *
     * @param supplyDTO 请求供应商的参数
     * @return List<MerchantInfoVO>
     */
    public List<MerchantInfoVO> getConfigFromRemote(MatchingMerchantSupplyDTO supplyDTO) {
        RestResponse<MatchingMerchantSupplyVO> supplyRes = CommonLogContext.addCommonLogAndRecordifPresent(StringUtils.join("请求base获取供应商信息,请求参数:", JacksonUtils.toJsonWithNonEmpty(supplyDTO)), () -> {
            try {
                RestResponse<MatchingMerchantSupplyVO> restResponse = merchantScopeServiceClient.matchingMerchantSupply(supplyDTO);
                String bhStrJoin = Optional.ofNullable(restResponse).map(RestResponse::getResult).map(MatchingMerchantSupplyVO::getGysjh)
                        .map(merchantInfoVOS -> merchantInfoVOS.stream().map(MerchantInfoVO::getBh).collect(Collectors.joining(","))).orElse(StringUtils.EMPTY);
                LOGGER.info("调用base服务获取供应商信息,企业编号:{},请求参数:{},响应的供应商编号:{}", supplyDTO.getQybh(), JsonMapperUtil.toJsonStr(supplyDTO), bhStrJoin);
                return restResponse;
            } catch (SystemException e) {
                LOGGER.error("查询供应商账号异常,请求:{}", JacksonUtils.toJsonWithNonEmpty(supplyDTO), e);
                return null;
            }
        });
        if (supplyRes == null || !supplyRes.isSuccess() || supplyRes.getResult() == null || CollectionUtils.isEmpty(supplyRes.getResult().getGysjh())) {
            return Collections.emptyList();
        }

        return supplyRes.getResult().getGysjh();
    }

}
