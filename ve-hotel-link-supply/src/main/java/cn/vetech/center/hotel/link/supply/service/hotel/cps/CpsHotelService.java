package cn.vetech.center.hotel.link.supply.service.hotel.cps;

import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.client.gys.cps.ICdsHotelServiceClient;
import cn.vetech.center.hotel.link.client.gys.cps.ICdsIHotelServiceClient;
import cn.vetech.center.hotel.link.client.gys.cps.dto.VeCdsIHotelDetailDTO;
import cn.vetech.center.hotel.link.client.gys.cps.dto.VeCdsHotelDetailDTO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeCdsHotelDetailVO;
import cn.vetech.center.hotel.link.client.gys.cps.vo.VeCdsIHotelDetailVO;
import cn.vetech.center.hotel.link.supply.base.hotel.IHotelService;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lipeng
 */
@Service
public class CpsHotelService implements IHotelService {
    /**
     *
     */
    @Autowired
    private ICdsHotelServiceClient hotelServiceClient;
    /**
     *
     */
    @Autowired
    private ICdsIHotelServiceClient iHotelServiceClient;
    @Override
    public HotelVO getHotel(HotelDTO dto) {
        VeCdsHotelDetailDTO detailDTO=new VeCdsHotelDetailDTO();
        detailDTO.setHotelId(dto.getLocalHotelId());
        detailDTO.setInfoType("0");
        RestResponse<VeCdsHotelDetailVO> response=hotelServiceClient.hotelDetail(detailDTO);
        if(response==null||response.getResult()==null){
            return null;
        }
        VeCdsHotelDetailVO detailVO=response.getResult();
        HotelVO vo=new HotelVO();
        vo.setZt(detailVO.getZt());
        vo.setJdid(detailVO.getJdid());
       if(ListUtil.isEmpty(detailVO.getMaps())){
            return vo;
        }
        List<Mapper> mappers=detailVO.getMaps().stream().map(mapVo->{
            Mapper mapper=new Mapper();
            mapper.setFybh(mapVo.getFybh());
            mapper.setHotelid(mapVo.getHotelid());
            return mapper;
        }).collect(Collectors.toList());
        vo.setMappers(mappers);
        vo.setBd(detailVO.getBd());
        return vo;
    }

    @Override
    public HotelVO getIHotel(HotelDTO dto) {
        VeCdsIHotelDetailDTO detailDTO=new VeCdsIHotelDetailDTO();
        detailDTO.setHotelId(dto.getLocalHotelId());
        detailDTO.setInfoType("0");

        RestResponse<VeCdsIHotelDetailVO> response=iHotelServiceClient.hotelDetail(detailDTO);
        if(response==null||response.getResult()==null){
            return null;
        }
        VeCdsIHotelDetailVO detailVO=response.getResult();
        HotelVO vo=new HotelVO();
        vo.setZt(detailVO.getZt());
        vo.setJdid(detailVO.getJdid());
        if(ListUtil.isEmpty(detailVO.getMaps())){
            return vo;
        }
        List<Mapper> mappers=detailVO.getMaps().stream().map(mapVo->{
            Mapper mapper=new Mapper();
            mapper.setFybh(mapVo.getFybh());
            mapper.setHotelid(mapVo.getHotelid());
            return mapper;
        }).collect(Collectors.toList());
        vo.setMappers(mappers);
        return vo;
    }
}
