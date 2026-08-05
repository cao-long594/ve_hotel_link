package cn.vetech.center.hotel.link.supply.service.config.cloud;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.center.hotel.link.supply.service.config.charge.ChargeHotelConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vetech
 */
@Service
public class CloudHotelConfigService implements HotelConfigService {
    @Override
    public List<HotelConfig> getConfigs(LinkHotelDTO dto) {
        return null;
    }

    @Override
    public HotelConfig getConfig(LinkHotelDTO dto) {
        return null;
    }
}
