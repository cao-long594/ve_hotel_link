package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.paydk.dto.LinkHotelPayDkDTO;
import cn.vetech.center.hotel.link.api.paydk.vo.LinkHotelPayDkVO;
import cn.vetech.center.hotel.link.api.paydkmix.dto.LinkHotelPayWithholdingMixDTO;
import cn.vetech.center.hotel.link.api.paydkmix.vo.LinkHotelPayWithholdingMixVO;
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
public class PayDkDistributeService {
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
     *
     * @param dto 1
     * @return 1
     */
    public LinkHotelPayDkVO payDk(LinkHotelPayDkDTO dto) throws SupplyConnectException {
        CommonLogBean commonLogBean=CommonLogContext.get();
        if(commonLogBean!=null){
            CommLog commLog = commonLogBean.getCommonLog();
            if(commLog!=null){
                commLog.setDdbh(dto.getOrderNo());
            }
        }
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService=distributeService.getSupplyService(dto);
        return supplyService.hotelPayDk(dto);
    }

    /**
     *
     * @param dto 1
     * @return 1
     */
    public LinkHotelPayWithholdingMixVO payDkMix(LinkHotelPayWithholdingMixDTO dto) throws SupplyConnectException {
        CommonLogBean commonLogBean=CommonLogContext.get();
  if(commonLogBean!=null){
            CommLog commLog = commonLogBean.getCommonLog();
            if(commLog!=null){
                commLog.setDdbh(dto.getOrderNo());
            }
        }
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService=distributeService.getSupplyService(dto);
        return supplyService.hotelPayDkMix(dto);
    }
}
