package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.tdxx.dto.LinkHotelCgtdxxDTO;
import cn.vetech.center.hotel.link.api.tdxx.vo.LinkHotelCgtdxxVO;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.log.bean.CommonLogBean;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.commlog.api.vo.CommLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lipeng
 */
@Service
public class TdxxDistributeService {
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     *
     */
    @Autowired
    private SupplyDistributeService distributeService;

    /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelCgtdxxVO tdxx(LinkHotelCgtdxxDTO dto) throws SupplyConnectException {
        CommonLogBean commonLogBean = CommonLogContext.get();
        if (commonLogBean != null) {
            CommLog commLog = commonLogBean.getCommonLog();
            if (commLog != null) {
                commLog.setDdbh(dto.getCpstdbh());
            }
        }
        dto.setServiceType("1");
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        return supplyService.hotelTdxx(dto);
    }
}
