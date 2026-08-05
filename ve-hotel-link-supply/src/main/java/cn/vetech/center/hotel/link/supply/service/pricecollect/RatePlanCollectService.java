package cn.vetech.center.hotel.link.supply.service.pricecollect;

import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamParseDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.client.gys.cps.ICpsRatePlanCollectClient;
import cn.vetech.center.hotel.link.client.gys.cps.dto.RatePlanCollectGzipDTO;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.supply.service.distribute.vip.VipParseDistributeService;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.charge.cloud.modules.utils.zip.ZipUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author vetech
 * @since 2024/11/13
 */
@Service
public class RatePlanCollectService {

    enum Source {
        RATE_SEARCH,REAL_TIME_PRICE, MIN_PRICE,
    }

    /**
     * 日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(RatePlanCollectService.class);
    /**
     * 价格收集线程
     */
    @Resource(name = "asyncCollectPrice")
    private Executor asyncCollectPrice;


    @Value("${cps.price.collect.flag:false}")
    private Boolean cpsPriceCollectFlag;

    /**
     *cps价格收集
     */
    @Autowired
    private ICpsRatePlanCollectClient cpsRatePlanCollectClient;
/**
     * 会员解析
     */
    @Autowired
    private VipParseDistributeService vipParseDistributeService;



    public void asyncCollectPrice(Object searchVoObj, LinkHotelRateSearchDTO dto) {
        if (!cpsPriceCollectFlag) {
            return;
        }
        if (Objects.isNull(searchVoObj) || Objects.isNull(dto)) {
            return;
        }
        if (!(searchVoObj instanceof LinkHotelRateSearchVO)) {
            return;
        }
        if (!StringUtils.equals(dto.getPt(), PtEnum.CPS.getValue())) {
            return;
        }
        try {
            LinkHotelRateSearchVO searchVO = (LinkHotelRateSearchVO) searchVoObj;
            String fybh = dto.getFybh();
            String zhmc = dto.getZhmc();
            searchVO.setCheckInDate(dto.getCheckInDate());
            searchVO.setCheckOutDate(dto.getCheckOutDate());
            String localHotelId = dto.getLocalHotelId();
            String hotelId = dto.getHotelId();
            Source soucrceEnum = dto.getHotelListSearch() == 1 ? Source.REAL_TIME_PRICE : Source.RATE_SEARCH;
            String zipStr = ZipUtil.compressStrBase64(JsonMapperUtil.toJsonStr(searchVO), StandardCharsets.UTF_8.name());
            ImmutableTriple<Boolean, String, VipParamParseDTO> vipVaildResult = vipParseDistributeService.vailAndGetVipParam(dto);
            String sfhyj  = Boolean.TRUE.equals(vipVaildResult.getLeft()) && Objects.nonNull(vipVaildResult.getRight())?"1":"0";
            Map<String, String> dtoSupplier = dto.getSupplier();
            String discount = MapUtils.getString(dtoSupplier, "discount");
            String shbh = MapUtils.getString(dtoSupplier, "qybh", MapUtils.getString(dtoSupplier, "shbh"));
            RatePlanCollectGzipDTO gzipDTO = new RatePlanCollectGzipDTO(fybh,zhmc,hotelId,soucrceEnum.name(),discount,sfhyj,localHotelId,zipStr);
            String businessNo = dto.getBusinessNo();
            gzipDTO.setShbh(shbh);
             gzipDTO.setGngj(StringUtils.defaultString(dto.getGngj(), GnGjTypeEnum.GN.getCode()));
            gzipDTO.setBusinessNo(businessNo);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> cpsRatePlanCollectClient.collect(gzipDTO), asyncCollectPrice);
        } catch (Exception e) {
            logger.error("asyncCollectPrice error", e);
        }
    }


}
