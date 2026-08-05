package cn.vetech.center.hotel.link.supply.service.distribute;


import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.base.exception.SupplyServiceException;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.annotation.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SupplyDistributeService {
    /**
     *
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(SupplyDistributeService.class);

    @Autowired
    private Map<String,IHotelLinkSupplyService> supplyServiceMap;

    /**
     * 获取对应服务
     * @param dto 1
     * @return 1
     */
    @Log(name="供应商动态接口分发",logParam = false,logReturn = true)
    public IHotelLinkSupplyService getSupplyService(LinkHotelDTO dto){
        IHotelLinkSupplyService supplyService=get(dto);
        if(supplyService==null){
            LOGGER.error("配置信息:{}", JacksonUtils.toJsonWithNonEmpty(dto.getSupplier()));
            throw new SupplyServiceException(dto.getFybh(),getServiceName(dto));
        }
        return supplyService;
    }

    /**
     *
     * @param dto 1
     * @return 1
     */
    public boolean exists(LinkHotelDTO dto){
        IHotelLinkSupplyService supplyService=get(dto);
        if(supplyService==null){
            LOGGER.error("fybh:{},serviceName:{}无房源实现",dto.getFybh(),getServiceName(dto));
            return false;
        }
        return true;
    }
    /**
     *
     * @param dto 1
     * @return 1
     */
    private IHotelLinkSupplyService get(LinkHotelDTO dto){
        String serviceName=getServiceName(dto);
        IHotelLinkSupplyService supplyService=supplyServiceMap.get(serviceName);
        return supplyService;
    }

    /**
     *
     * @param dto 1
     * @return 1
     */
    private String getServiceName(LinkHotelDTO dto){
        String fyen=dto.getSupplier().get("fyen");
        String tcext=dto.getSupplier().get("tcext");
        if(StringUtils.isNotBlank(tcext)){
            fyen="tcext";
        }
        String serviceName=fyen+IHotelLinkSupplyService.NAME;
        return serviceName;
    }
}
