package cn.vetech.center.hotel.link.supply.service.ratesearch;

import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.async.AsyncService;
import cn.vetech.center.hotel.link.supply.service.distribute.RateSearchDistributeService;
import cn.vetech.center.hotel.link.supply.service.exchangerate.ExchangeRateService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;

/**
 *
 */
@Service
public class RateSearchAsyncService {
    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateSearchAsyncService.class);
    /**
     *
     */
    @Autowired
    private RateSearchDistributeService rateSearchDistributeService;
    /**
     *
     */
    @Autowired
    private AsyncService asyncService;
    /**
     *
     */
    @Autowired
    private ExchangeRateService exchangeRateService;


    //    @Log(name="异步查询报价",logParam = true,logReturn = true,time = true)
  @Async("asyncJgjhServiceExecutor")
    public Future<LinkHotelRateSearchVO> rateSearch(LinkHotelRateSearchDTO dto) {
        LinkHotelRateSearchVO vo = null;
        try {
            vo = rateSearchDistributeService.rateSearch(dto);
        } catch (Exception e) {
            LOGGER.error("单个供应商查询报价异常,请求参数:{}", JacksonUtils.toJsonWithNonEmpty(dto), e);
        }
        //过滤空的集合节点
        filterEmptyRemoveOverMaxRpId(vo);
        //处理多币种
        exchangeRateService.dealCurrencyExchangeRate(dto, vo);

        boolean cpsQryFlag = StringUtils.equals(dto.getFybh(), FyEnum.CPS.getFybh());
        boolean asmsQryFlag = StringUtils.equals(dto.getFybh(), FyEnum.ASMS.getFybh());

        // 处理缓存 注：cps、asms属于异步查询，查询内部已做同等处理并放入大缓存，此处则跳过该处理！！！
        if (!cpsQryFlag && !asmsQryFlag) {
            //处理一些公共值字段
            asyncService.executeCommon(dto, vo);
            //处理配置信息
            asyncService.executeConfig(dto, vo);
            //状态处理
            asyncService.handleRoomStatus(dto, vo);
            //处理缓存
            asyncService.executeCache(dto, vo, false);
        }

        // 若酒店列表实时最低价查询 且 asms时，重新放一次大缓存！（注：避免多次查询Asms时，后续切面拦截直接取缓存，并未将价格信息放入大缓存，导致实时最低价时取大缓存时取不到该价格！！！）
        if (dto.getHotelListSearch() == 1 && asmsQryFlag && Objects.nonNull(vo) && vo.isFromCacheFlag()) {
            // 放入大缓存
            asyncService.executeCache(dto, vo, true);
        }

        //异步返回
        return new AsyncResult<>(vo);
    }

/**
     * cps、asms查询报价
     *
     * @param dto dto
     * @return LinkHotelRateSearchVO
     * @throws SupplyConnectException e
     */
    public LinkHotelRateSearchVO rateSearchForAsmsAndCps(LinkHotelRateSearchDTO dto) throws SupplyConnectException {
        LinkHotelRateSearchVO vo = rateSearchDistributeService.rateSearch(dto);
        //过滤空价格 移除超长价格计划id
        filterEmptyRemoveOverMaxRpId(vo);
        asyncService.handleRoomStatus(dto, vo);
        //处理多币种
        exchangeRateService.dealCurrencyExchangeRate(dto, vo);
        // asms处理公共配置，cps在link-hotel上处理
        if (PtEnum.ASMS.getValue().equals(dto.getPt())) {
            // 处理配置信息
            asyncService.executeConfig(dto, vo);
        }
        return vo;
    }

    /**
     * @param vo 1
     */
    private void filterEmptyRemoveOverMaxRpId(LinkHotelRateSearchVO vo) {
        if (vo == null || ListUtil.isEmpty(vo.getRooms())) {
            return;
        }
        //删除priceAfterTax为0的nightlyRates节点
        //删除nightlyRates为空的rateplan节点
        //删除rateplan为空的room节点
        vo.getRooms().removeIf(room -> {
            List<SearchRatePlan> ratePlans = room.getRatePlans();
            if (ListUtil.isEmpty(ratePlans)) {
                return true;
            }
            ratePlans.removeIf(ratePlan -> {
                String ratePlanId = ratePlan.getRatePlanId();
                if (StringUtils.length(ratePlanId) > NumConstant.NUM_450) {
                    LOGGER.warn("价格计划id:{},超450字符过滤", ratePlanId);
                    return true;
                }
                List<SearchNightlyRate> nightlyRates = ratePlan.getNightlyRates();
                if (ListUtil.isEmpty(nightlyRates)) {
                    return true;
                }
                //增加排序
                nightlyRates.sort((o1, o2) -> StringUtils.compare(o1.getDate(), o2.getDate()));
                return nightlyRates.stream().anyMatch(item -> new BigDecimal(item.getPriceAfterTax()).compareTo(BigDecimal.ZERO) == NumConstant.NUM_0);
            });
            return ListUtil.isEmpty(ratePlans);
        });
    }


}
