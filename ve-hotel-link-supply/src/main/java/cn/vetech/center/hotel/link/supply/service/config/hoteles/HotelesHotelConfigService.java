package cn.vetech.center.hotel.link.supply.service.config.hoteles;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.client.gys.hoteles.HotelesLinkHotelConfigDTO;
import cn.vetech.center.hotel.link.client.gys.hoteles.HotelesLinkHotelConfigVO;
import cn.vetech.center.hotel.link.client.gys.hoteles.IHotelesConfigServiceClient;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaotengyu
 * @since 2022-06-08 17:00
 */
@Service
public class HotelesHotelConfigService implements HotelConfigService{

    /**
     * 获取 hoteles 配置client
     */
    @Autowired
    private IHotelesConfigServiceClient iHotelesConfigServiceClient;

    /**
     * 获取所有配置
     * @param dto 1 请求参数
     * @return list
     */
    @Override
    public List<HotelConfig> getConfigs(LinkHotelDTO dto) {
        HotelesLinkHotelConfigDTO configDTO = new HotelesLinkHotelConfigDTO();
        RestResponse<List<HotelesLinkHotelConfigVO>> configsResponse = iHotelesConfigServiceClient.getConfigs(configDTO);
        if(!configsResponse.isSuccess() || ListUtil.isEmpty(configsResponse.getResult())){
            return null;
        }
        return configsResponse.getResult().stream().map(this::convert).collect(Collectors.toList());
    }

    /**
     * 获取配置
     * @param dto 1
     * @return
     */
    @Override
    public HotelConfig getConfig(LinkHotelDTO dto) {
        HotelesLinkHotelConfigDTO configDTO = new HotelesLinkHotelConfigDTO();
   configDTO.setFybh(dto.getFybh());
        configDTO.setZhmc(dto.getZhmc());
        RestResponse<HotelesLinkHotelConfigVO> configResponse = iHotelesConfigServiceClient.getConfig(configDTO);
        boolean success = configResponse.isSuccess();
        if(!success || Objects.isNull(configResponse.getResult())){
            return null;
        }
        return convert(configResponse.getResult());
    }

    /**
     *
     * @param configVO 1
     * @return 1
     */
    private HotelConfig convert(HotelesLinkHotelConfigVO configVO){
        if(configVO==null){
            return null;
        }
        HotelConfig config=new HotelConfig();
        config.setFybh(configVO.getFybh());
        config.setQybh(configVO.getShbh());
        config.setZhmc(configVO.getFyjc());
        FyEnum fyEnum=FyEnum.instanceByFybh(config.getFybh());
        if(fyEnum!=null){
            config.setFyen(fyEnum.getFyen());
            config.setGyspt(fyEnum.getGyspt());
        }
        config.setFymc(configVO.getFymc());
        config.setFyxsmc(configVO.getFymc());
        config.setConfigMap(configVO.getConfigMap());
        return config;
    }
}
