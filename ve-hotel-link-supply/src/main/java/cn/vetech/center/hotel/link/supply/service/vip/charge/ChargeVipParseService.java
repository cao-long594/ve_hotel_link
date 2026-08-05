package cn.vetech.center.hotel.link.supply.service.vip.charge;

import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.supply.base.vip.VipParseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

/**
 * @author vetech
 * @since 2023/11/10
 */
@Service
public class ChargeVipParseService extends VipParseService {



    /**
     * 如果费控 直接返回true
     *
     * @param searchDTO searchDTO
     * @return org.apache.commons.lang3.tuple.ImmutablePair<java.lang.Boolean, java.lang.String>
     */
    protected ImmutablePair<Boolean, String> doCheckIsVipAccout(LinkHotelRateSearchDTO searchDTO) {
        return ImmutablePair.of(true, StringUtils.EMPTY);
    }

}
