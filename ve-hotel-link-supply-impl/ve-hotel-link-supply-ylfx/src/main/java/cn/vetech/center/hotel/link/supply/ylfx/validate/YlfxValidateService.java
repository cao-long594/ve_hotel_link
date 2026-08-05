package cn.vetech.center.hotel.link.supply.ylfx.validate;

import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.validate.request.DailyPrice;
import cn.vetech.center.hotel.link.supply.ylfx.validate.request.YlfxValidateRequest;
import cn.vetech.center.hotel.link.supply.ylfx.validate.response.YlfxValidateData;
import cn.vetech.center.hotel.link.supply.ylfx.validate.response.YlfxValidateResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.validate.ValidateApiRes;
import cn.vetech.charge.base.CommonMagicNumber;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxValidateService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxValidateService.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 下单前验价
     *
     * @param dto    dto
     * @param config config
     * @return LinkHotelValidateVO
     */
    public LinkHotelValidateVO validate(LinkHotelValidateDTO dto, YlfxConfig config) {
       try {
            YlfxValidateRequest request = convertValidateReq(dto);
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.VALIDATE);
            YlfxValidateResponse response = JacksonUtils.parseNonEmpty(res, YlfxValidateResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                return ValidateApiRes.fail(immutablePair.getRight());
            }
            YlfxValidateData data = response.getData();
            if (Objects.isNull(data)) {
                return ValidateApiRes.fail("数据结果为空");
            }
            //结果编码：0. 成功 1. 满房 2. 价格错误
            if (!StringUtils.equals(data.getCode(), CommonMagicNumber.STRING0)) {
                return ValidateApiRes.fail(data.getDesc());
            }
            return ValidateApiRes.success();
        } catch (Exception e) {
            logger.warn("接口异常【{}】",e.getMessage(),e);
            return ValidateApiRes.fail("接口异常") ;
        }
    }

    /**
     * 验价接口请求
     *
     * @param dto dto
     * @return YlfxValidateRequest
     */
    private YlfxValidateRequest convertValidateReq(LinkHotelValidateDTO dto) {
        YlfxValidateRequest request = new YlfxValidateRequest();
        request.setHotelId(dto.getHotelId());
        request.setProductId(dto.getRatePlanId());
        request.setCheckinDate(VeDateUtils.dateStrToDateNum(dto.getCheckInDate()));
        request.setCheckoutDate(VeDateUtils.dateStrToDateNum(dto.getCheckOutDate()));
        request.setRoomCount(NumberUtils.toInt(dto.getNumberOfRooms()));
        request.setTotalPrice(dto.getTotalPrice());
        List<DailyPrice> dailyList = dto.getNightlyRates().stream().map(bookNightlyRate -> {
            DailyPrice dailyPrice = new DailyPrice();
            dailyPrice.setNight(VeDateUtils.dateStrToDateNum(bookNightlyRate.getDate()));
            dailyPrice.setPrice(bookNightlyRate.getPriceAfterTax());
            return dailyPrice;
        }).collect(Collectors.toList());
        request.setDailyList(dailyList);
        return request;
    }
}