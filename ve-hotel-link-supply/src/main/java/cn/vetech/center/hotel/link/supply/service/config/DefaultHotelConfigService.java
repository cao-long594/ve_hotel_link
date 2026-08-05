package cn.vetech.center.hotel.link.supply.service.config;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.supply.base.config.HotelConfigService;
import cn.vetech.center.hotel.link.supply.base.config.bean.HotelConfig;


import java.util.List;

/**
 * lipeng
 */
//@Service
public class DefaultHotelConfigService implements HotelConfigService {
//    /**
//     *
//     */
//    @Autowired
//    private JdGyspzService jdGyspzService;
//    /**
//     *
//     */
//    @Autowired
//    private JdGyspzmxService jdGyspzmxService;
    /**
     * 获取全部配置
     * @param dto CPS CMBCHINA MENGNIU
     * @return 1
     */
    public List<HotelConfig> getConfigs(LinkHotelDTO dto){
//        String qybh=dto.getCompid();//CPS CMBCHINA MENGNIU
//        //查询启动状态的配置
//        List<JdGyspz> jdGyspzs=jdGyspzService.selectList(qybh,"1");
//        if(ListUtil.isEmpty(jdGyspzs)){
//            return null;
//        }
//        List<String> ids=jdGyspzs.stream().map(JdGyspz::getId).collect(Collectors.toList());
//        List<JdGyspzmx> jdGyspzmxes=jdGyspzmxService.selectList(ids);
//        if(ListUtil.isEmpty(jdGyspzmxes)){
//            return null;
//        }
//        Map<String,List<JdGyspzmx>> mxMap=jdGyspzmxes.stream().collect(Collectors.groupingBy(JdGyspzmx::getZbid));
//        return jdGyspzs.stream().map(jdGyspz -> {
//            List<JdGyspzmx> mxs=mxMap.get(jdGyspz.getId());
//            return convertMap(jdGyspz,mxs);
//        }).filter(config-> config!=null).collect(Collectors.toList());
        return null;
    }

    /**
     * 处理供应商配置
     * @param dto 1
     */
    public HotelConfig getConfig(LinkHotelDTO dto){
//        String fybh=dto.getFybh();
//        String qybh=dto.getCompid();
//        String zhmc=dto.getZhmc();
//        JdGyspz jdGyspz=jdGyspzService.selectOne(fybh,qybh,zhmc,"1");
//        if(jdGyspz==null){
    throw new HotelConfigExcetion(fybh,qybh,zhmc);
//        }
//        List<JdGyspzmx> mxs=jdGyspzmxService.selectOne(jdGyspz.getId());
//        HotelConfig config=convertMap(jdGyspz,mxs);
//        if(MapUtil.isEmpty(config.getConfigMap())){
//            throw new HotelConfigExcetion(fybh,qybh,zhmc);
//        }
//        return config;
        return null;
    }

    /**
     *
     * @param jdGyspz 1
     * @param mxs 1
     * @return 1
     */
//    private HotelConfig convertMap(JdGyspz jdGyspz,List<JdGyspzmx> mxs){
//        if(jdGyspz==null){
//            return null;
//        }
//        if(ListUtil.isEmpty(mxs)){
//            return null;
//        }
//        HotelConfig config=BeanMapper.map(jdGyspz,HotelConfig.class);
//        Map<String,String> configMap=new HashMap<>();
//        mxs.stream().forEach(mx->{
//            configMap.put(mx.getSxm(),mx.getSxz());
//        });
//        config.setConfigMap(configMap);
//        return config;
//    }
}
