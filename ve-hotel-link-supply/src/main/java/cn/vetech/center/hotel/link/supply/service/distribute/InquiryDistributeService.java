package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkInquirySupplyService;
import cn.vetech.center.hotel.link.supply.base.exception.SupplyServiceException;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.collection.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xiaotengyu
 * @since 2022-04-27 21:57
 */
@Service
public class InquiryDistributeService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(InquiryDistributeService.class);

    /**
     * inquirySupplyServiceMap
     */
    @Autowired
    private List<IHotelLinkInquirySupplyService> inquirySupplyServices;

    /**
     * 获取对应服务
     *
     * @param dto 1
     * @return 1
     */
    public IHotelLinkInquirySupplyService getSupplyService(LinkHotelDTO dto) {
        IHotelLinkInquirySupplyService supplyService = get(dto);
        if (Objects.isNull(supplyService)) {
            throw new SupplyServiceException(dto.getFybh(),StringUtils.EMPTY);
        }
        return supplyService;
    }

    /**
     * @param dto 1
     * @return 1
     */
    public boolean exists(LinkHotelDTO dto) {
        IHotelLinkInquirySupplyService supplyService = get(dto);
        if (Objects.isNull(supplyService)) {
            return false;
        }
        return true;
    }

    /**
     * @param dto 1
     * @return 1
     */
    private IHotelLinkInquirySupplyService get(LinkHotelDTO dto) {
        if (MapUtil.isEmpty(dto.getSupplier())) {
             logger.warn("询价单：没有供应商信息；dto:{}", JacksonUtils.toJsonWithNonEmpty(dto));
            return null;
        }
        String fybh = dto.getSupplier().get("fybh");
        if (StringUtils.isBlank(fybh)) {
            logger.warn("询价单：没有房源编号；dto:{}", JacksonUtils.toJsonWithNonEmpty(dto));
            return null;
        }
        FyEnum fyEnum = FyEnum.instanceByFybh(fybh);
        if (Objects.isNull(fyEnum)) {
            logger.warn("询价单：没有该房源信息；dto:{}", JacksonUtils.toJsonWithNonEmpty(dto));
            return null;
        }
        if (fyEnum.isTcext()) {
            fybh = "tcext";
        }
        String finalFybh = fybh;
        Optional<IHotelLinkInquirySupplyService> first = inquirySupplyServices.stream()
                .filter(service -> StringUtils.contains(service.getFybh(), finalFybh))
                .findFirst();
        IHotelLinkInquirySupplyService supplyService = first.orElse(null);
        return supplyService;
    }


}