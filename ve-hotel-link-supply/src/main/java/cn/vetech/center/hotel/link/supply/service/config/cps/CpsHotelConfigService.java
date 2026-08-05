package cn.vetech.center.hotel.link.supply.service.config.cps;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.client.gys.cps.ILinkHotelConfigServiceClient;
import cn.vetech.center.hotel.link.client.gys.cps.dto.VeLinkHotelConfigDTO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeLinkHotelConfigVO;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vetech
 */
@Service
public class CpsHotelConfigService implements HotelConfigService {
    /**
     *
     */
    @Autowired
    private ILinkHotelConfigServiceClient configServiceClient;

    @Override
    public List<HotelConfig> getConfigs(LinkHotelDTO dto) {
        VeLinkHotelConfigDTO configDTO = new VeLinkHotelConfigDTO();
        configDTO.setShbh("CPS");
        List<VeLinkHotelConfigVO> vos = configServiceClient.getConfigs(configDTO);
        if (ListUtil.isEmpty(vos)) {
            return null;
        }
        return vos.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public HotelConfig getConfig(LinkHotelDTO dto) {
        VeLinkHotelConfigDTO configDTO = new VeLinkHotelConfigDTO();
        configDTO.setShbh(null);
        configDTO.setFybh(dto.getFybh());
        //对应 Hotel_Fyxx 表的 fyjc  而fyjc这个字段并不是标准的是随意定的 如: MENGNIUyaduo ZJJTyaduo  yaduo
        configDTO.setZhmc(dto.getZhmc());
        VeLinkHotelConfigVO vo = configServiceClient.getConfig(configDTO);
        return convert(vo);
    }

    /**
     * @param configVO 1
     * @return 1
     */
    private HotelConfig convert(VeLinkHotelConfigVO configVO) {
        if (configVO == null) {
            return null;
        }
        HotelConfig config = new HotelConfig();
        config.setFybh(configVO.getFybh());
        config.setQybh(configVO.getShbh());
        config.setZhmc(configVO.getFyjc());
        FyEnum fyEnum = FyEnum.instanceByFybh(config.getFybh());
        if (fyEnum != null) {
            config.setFyen(fyEnum.getFyen());
            config.setGyspt(fyEnum.getGyspt());
        }
        String kqhc = configVO.getKqhc();
        config.setKqhc(kqhc);
        config.setFymc(configVO.getFymc());
        config.setFyxsmc(configVO.getFymc());
        config.setConfigMap(configVO.getConfigMap());
        return config;
    }
}
