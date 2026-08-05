package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.api.validategn.dto.LinkHotelValidateGnDTO;
import cn.vetech.center.hotel.link.api.validategn.vo.LinkHotelValidateGnVO;
import cn.vetech.center.hotel.link.enums.ValidateGnResultEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2024/5/9 17:09
 */
@Service
public class ValidateGnDistributeService {
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
    public LinkHotelValidateGnVO validateGn(LinkHotelValidateGnDTO dto) throws SupplyConnectException {
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        LinkHotelValidateGnVO validate = supplyService.validateGn(dto);
        //处理公共result
        dealCommonResult(validate);
        return validate;
    }

    /**
     * 处理验价结果
     *
     * @param vo 返回vo
     */
    private void dealCommonResult(LinkHotelValidateVO vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        Integer status = vo.getStatus();
        String result = vo.getResult();
        if (StringUtils.isBlank(result)) {
            String strStatus = String.valueOf(status);
         vo.setResult(ValidateGnResultEnum.NO_1.getCode());
            if (StringUtils.equalsIgnoreCase(strStatus, String.valueOf(LinkHotelVO.SUCCESS))) {
                vo.setResult(ValidateGnResultEnum.YES.getCode());
            }
        }
        if (Objects.isNull(status)) {
            vo.setStatus(LinkHotelVO.FAIL);
            //status 字段失败值是-1  result字段失败值是-1、-2
            if (StringUtils.equalsIgnoreCase(ValidateGnResultEnum.YES.getCode(), vo.getResult())) {
                vo.setStatus(LinkHotelVO.SUCCESS);
            }
        }
    }
}
