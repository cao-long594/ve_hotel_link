package cn.vetech.center.hotel.link.supply.service.distribute.notice;


import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.supply.base.notice.INoticeValidateService;
import cn.vetech.charge.cloud.exception.SystemException;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author vetech
 */
@Service
public class NoticeValidateDistributeService {
    /**
     *
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(NoticeValidateDistributeService.class);
    /**
     *
     */
    @Autowired(required = false)
    private Map<String, INoticeValidateService> validateServiceMap;

    /**
     *
     * @param dto 1
     * @return 1
     * @throws SystemException 1
     */
    public LinkHotelVO validate(LinkHotelDTO dto) throws SystemException {
        String fyen = dto.getSupplier().get("fyen");
        String serviceName = fyen + INoticeValidateService.NAME;
        INoticeValidateService validateService = MapUtils.getObject(validateServiceMap, serviceName);
        if(validateService == null){
            LOGGER.info("未获取到服务信息:{}",serviceName);
            return new LinkHotelVO();
        }
        return validateService.validate(dto);
    }
}
