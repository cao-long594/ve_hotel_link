package cn.vetech.center.hotel.link.supply.service.distribute.vip;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamParseDTO;
import cn.vetech.center.hotel.link.supply.base.vip.VipParseService;
import cn.vetech.center.hotel.link.supply.base.exception.SupplyBusinessException;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author vetech
 * @since 2023/11/10
 */
@Service
public class VipParseDistributeService {


    /**
     * vip解析
     */
    @Autowired
    private Map<String, VipParseService> vipParseServiceMap;

    /**
     * 校验并获取vip信息 不同的平台有不同的校验方式以及数据解析方式
     *
     * @param searchDTO searchDTO
     * @return org.apache.commons.lang3.tuple.ImmutableTriple<java.lang.Boolean, java.lang.String, cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamParseDTO>
     */
    public ImmutableTriple<Boolean, String, VipParamParseDTO> vailAndGetVipParam(LinkHotelRateSearchDTO searchDTO) {
        return vailAndGetVipParam(searchDTO, Boolean.TRUE);
    }

    /**
     * 校验并获取vip信息
     *
     * @param searchDTO           searchDTO
     * @param checkUserVipExtInfo 是否强制校验vip
     * @return org.apache.commons.lang3.tuple.ImmutableTriple<java.lang.Boolean, java.lang.String, cn.vetech.center.hotel.link.api.ratesearch.dto.VipParamParseDTO>
     */
    public ImmutableTriple<Boolean, String, VipParamParseDTO> vailAndGetVipParam(LinkHotelRateSearchDTO searchDTO, Boolean checkUserVipExtInfo) {
        return getVipParaseService(searchDTO).getVipParam(searchDTO, checkUserVipExtInfo);
    }


    private VipParseService getVipParaseService(LinkHotelDTO dto) {
      VipParseService vipParseService = vipParseServiceMap.get(dto.getPt().concat(VipParseService.NAME));
        if (Objects.isNull(vipParseService)) {
            throw new SupplyBusinessException(String.format("平台：%s未找到vip解析实现类", dto.getPt()));
        }
        return vipParseService;
    }


}
