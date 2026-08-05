package cn.vetech.center.hotel.link.vcc.ejiehui;

import cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.EjiehuiVCCCardOpenInfoService;
import cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.dto.EjiehuiVCCCardOpenInfoRequest;
import cn.vetech.center.hotel.link.vcc.ejiehui.cardopeninfo.vo.EjiehuiVCCCardOpenInfoResponse;
import cn.vetech.center.hotel.link.vcc.ejiehui.common.EjiehuiVccConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author chengwanshan
 * @since 2025/4/16 18:03
 */
@Service
public class EjiehuiVCCBaseService {
    /**
     *
     */
    @Autowired
    private EjiehuiVCCCardOpenInfoService ejiehuiVCCCardOpenInfoService;

    public Optional<EjiehuiVCCCardOpenInfoResponse> cardOpenInfo(EjiehuiVCCCardOpenInfoRequest request, EjiehuiVccConfig vccConfig) {
        return ejiehuiVCCCardOpenInfoService.cardOpenInfo(request, vccConfig);
    }

}
