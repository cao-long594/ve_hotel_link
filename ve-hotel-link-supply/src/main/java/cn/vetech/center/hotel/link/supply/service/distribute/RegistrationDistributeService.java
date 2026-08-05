package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.member.dto.LinkHotelRegistrationDTO;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author pengyefei
 * @version 1.0
 * @since 2023/4/12 15:25
 */
@Service
public class RegistrationDistributeService {

    /**
     * 接口分发服务
     */
    @Autowired
    private SupplyDistributeService distributeService;

    /**
     * 员工注册绑定
     *
     * @param dto dto
     * @return LinkHotelRegistrationVO
     * @throws SupplyConnectException SupplyConnectException
     */
    public LinkHotelRegistrationVO registration(LinkHotelRegistrationDTO dto) throws SupplyConnectException {
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.registration(dto);
    }

}
