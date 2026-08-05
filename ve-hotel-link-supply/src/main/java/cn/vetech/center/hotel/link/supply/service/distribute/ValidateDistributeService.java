package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.enums.ValidateResultEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.exchangerate.ExchangeRateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lilpeng
 */
@Service
public class ValidateDistributeService {
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
     */
    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelValidateVO validate(LinkHotelValidateDTO dto) throws SupplyConnectException {
        configService.setConfig(dto);
        IHotelLinkSupplyService supplyService = distributeService.getSupplyService(dto);
        LinkHotelValidateVO validate = supplyService.validate(dto);
        //处理公共result
        dealCommonResult(validate);
        //处理多币种
        exchangeRateService.dealCurrencyExchangeValidate(dto, validate);
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
 