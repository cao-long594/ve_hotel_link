package cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo;

import cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.dto.EjiehuiVCCCardOpenInfoRequest;
import cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.vo.EjiehuiVCCCardOpenInfoResponse;
import cn.vetech.center.hotel.link.vcc.ejiehui.common.EjiehuiVCCHttpService;
import cn.vetech.center.hotel.link.vcc.ejiehui.common.EjiehuiVccConfig;
import cn.vetech.center.hotel.link.vcc.ejiehui.enums.EjiehuiVccGeneralEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author chengwanshan
 * @since 2025/4/16 20:15
 */
@Service
public class EjiehuiVCCCardOpenInfoService {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(EjiehuiVCCCardOpenInfoService.class);

    @Autowired
    private EjiehuiVCCHttpService ejiehuiVCCHttpService;

    public Optional<EjiehuiVCCCardOpenInfoResponse> cardOpenInfo(EjiehuiVCCCardOpenInfoRequest request, EjiehuiVccConfig vccConfig) {
        return ejiehuiVCCHttpService.sendInvokeApi(EjiehuiVCCCardOpenInfoResponse.class, request, vccConfig, EjiehuiVccGeneralEnum.ApiSiteEnum.CARD_OPEN_INFO);
    }


}
