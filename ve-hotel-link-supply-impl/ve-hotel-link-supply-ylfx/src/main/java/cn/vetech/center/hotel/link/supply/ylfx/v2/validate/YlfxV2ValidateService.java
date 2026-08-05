package cn.vetech.center.hotel.link.supply.ylfx.v2.validate;

import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.HotelDeductionTypeEnum;
import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelLadderDeductionInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelTimeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.request.YlfxV2RateSearchRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.response.YlfxV2RateSearchResponse;
import cn.vetech.center.hotel.link.supply.ylfx.v2.validate.response.YlfxV2ValidateResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import cn.vetech.center.hotel.link.util.validate.ValidateApiRes;
import cn.vetech.center.hotel.link.enums.SearchNightlyRateStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * 易旅分销 V2 产品可订校验服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2ValidateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2ValidateService.class);
    private static final String PRECHECK_URI = "/open/avail/precheck";
    @Autowired
    private YlfxV2UtilsService utilsService;

    public LinkHotelValidateVO validate(LinkHotelValidateDTO dto, YlfxConfig config) {
        try {
            YlfxV2ValidateResponse response = JacksonUtils.parseNonEmpty(utilsService.sendPost(convertRequest(dto, config), config, PRECHECK_URI), YlfxV2ValidateResponse.class);
            if (response == null || !StringUtils.equals("200", response.getCode()) || response.getData() == null || response.getData().getRoom() == null || response.getData().getRoom().getRate() == null) {
                return ValidateApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            YlfxV2RateSearchResponse.Rate rate = response.getData().getRoom().getRate();
            LinkHotelValidateVO vo = ValidateApiRes.success();
            vo.setTotalprice(rate.getTotalPrice());
            vo.setCurrencyCode(rate.getCurrencyCode());
            vo.setRooms(convertRooms(dto, response.getData().getRoom()));
            return vo;
        } catch (Exception e) {
            LOGGER.error("易旅分销 V2 产品可订校验接口异常", e);
            return ValidateApiRes.fail("接口异常");
        }
    }

    private YlfxV2RateSearchRequest convertRequest(LinkHotelValidateDTO dto, YlfxConfig config) {
        YlfxV2RateSearchRequest request = new YlfxV2RateSearchRequest();
        request.setCustomerCode(config.getCustomerCode());
        request.setHotelCode(dto.getHotelId());
        request.setRoomCode(dto.getRoomId());
        request.setRateCode(dto.getRatePlanId());
        request.setCheckIn(dto.getCheckInDate());
        request.setCheckOut(dto.getCheckOutDate());
        request.setRoomCount(NumberUtils.toInt(dto.getNumberOfRooms()));
        request.setCountry(dto.getRzrgj());
        request.setPaxRooms(buildPaxRooms(dto));
        return request;
    }

    /**
     * 构造校验接口入住人房间信息
     *
     * @param dto 标准验价请求
     * @return 入住人房间信息
     */
    private List<YlfxV2RateSearchRequest.PaxRoom> buildPaxRooms(LinkHotelValidateDTO dto) {
        int roomCount = NumberUtils.toInt(dto.getNumberOfRooms());
        List<YlfxV2RateSearchRequest.PaxRoom> paxRooms = new ArrayList<>();
        for (int index = 1; index <= roomCount; index++) {
            YlfxV2RateSearchRequest.PaxRoom paxRoom = new YlfxV2RateSearchRequest.PaxRoom();
            paxRoom.setRoomIndex(index);
            paxRoom.setAdults(NumberUtils.toInt(dto.getAdult(), 2));
            paxRoom.setChildren(NumberUtils.toInt(dto.getChild()));
            paxRooms.add(paxRoom);
        }
        return paxRooms;
    }

    /**
     * 转换校验后的房型与报价
     *
     * @param dto 标准验价请求
     * @param sourceRoom 易旅校验房型
     * @return 标准房型节点
     */
    private List<SearchRoom> convertRooms(LinkHotelValidateDTO dto, YlfxV2ValidateResponse.Room sourceRoom) {
        YlfxV2RateSearchResponse.Rate sourceRate = sourceRoom.getRate();
        SearchRoom room = RateSearchCommonUtils.initSearchRoom(FyEnum.YLFX);
        room.setHotelId(dto.getHotelId());
        room.setRoomId(sourceRoom.getRoomCode());
        SearchRatePlan ratePlan = RateSearchCommonUtils.initSearchRatePlan(FyEnum.YLFX);
        ratePlan.setHotelId(dto.getHotelId());
        ratePlan.setRoomId(sourceRoom.getRoomCode());
        ratePlan.setRatePlanId(sourceRate.getRateCode());
        ratePlan.setRatePlanName(sourceRate.getRateNameCn());
        ratePlan.setCurrencyCode(sourceRate.getCurrencyCode());
        ratePlan.setTotalRate(sourceRate.getTotalPrice());
        ratePlan.setTotalZqjgdj(sourceRate.getTotalPrice());
        ratePlan.setGysyssfhj(sourceRate.getTotalTaxAndFee());
        ratePlan.setGysyssfbz(sourceRate.getCurrencyCode());
        ratePlan.setLadderDeductionInfoList(convertCancelPolicies(sourceRate));
        YlfxGysxdbj gysxdbj = new YlfxGysxdbj();
        gysxdbj.setHotelId(dto.getHotelId());
        gysxdbj.setApiVersion("v2");
        ratePlan.setGysxdbj(JacksonUtils.toJsonWithNonEmpty(gysxdbj));
        if (sourceRate.getMeal() != null) {
            RateSearchCommonUtils.convertFreeMealByMealNum(ratePlan, sourceRate.getMeal().getBreakfastCount());
        }
        if (sourceRate.getCancelPolicies() == null || sourceRate.getCancelPolicies().isEmpty()) {
            RateSearchCommonUtils.convertSearchPrepayRule(ratePlan, SuffixTypeEnum.NOT_CANCEL, null, null);
        } else {
            RateSearchCommonUtils.convertSearchPrepayRule(ratePlan, SuffixTypeEnum.TIME_CANCEL, null,
                    sourceRate.getCancelPolicies().get(0).getFrom());
        }
        ratePlan.setNightlyRates((sourceRate.getDailyPriceList() == null ? new ArrayList<YlfxV2RateSearchResponse.DailyPrice>()
                : sourceRate.getDailyPriceList()).stream().map(sourcePrice -> {
            SearchNightlyRate nightlyRate = new SearchNightlyRate();
            nightlyRate.setDate(sourcePrice.getDate());
            nightlyRate.setPriceAfterTax(sourcePrice.getPrice());
            nightlyRate.setPriceBeforTax(sourcePrice.getPrice());
            nightlyRate.setZqjgdj(new BigDecimal(sourcePrice.getPrice()));
            nightlyRate.setStatus(SearchNightlyRateStatusEnum.YES.getCode());
            return nightlyRate;
        }).collect(Collectors.toList()));
        room.setRatePlans(java.util.Collections.singletonList(ratePlan));
        return java.util.Collections.singletonList(room);
    }

    /**
     * 转换取消规则明细
     *
     * @param rate 易旅报价
     * @return 标准取消规则明细
     */
    private List<HotelLadderDeductionInfo> convertCancelPolicies(YlfxV2RateSearchResponse.Rate rate) {
        List<HotelLadderDeductionInfo> deductions = new ArrayList<>();
        if (rate.getCancelPolicies() == null || rate.getCancelPolicies().isEmpty()) {
            HotelLadderDeductionInfo deduction = new HotelLadderDeductionInfo();
            deduction.setDeductionType(HotelDeductionTypeEnum.CANNOT_CANCEL.getCode());
            deductions.add(deduction);
            return deductions;
        }
        for (YlfxV2RateSearchResponse.CancelPolicy policy : rate.getCancelPolicies()) {
            HotelLadderDeductionInfo deduction = new HotelLadderDeductionInfo();
            deduction.setDeductionType(NumberUtils.toDouble(policy.getAmount()) == 0
                    ? HotelDeductionTypeEnum.FREE.getCode() : HotelDeductionTypeEnum.LADDER.getCode());
            HotelTimeInfo startTime = new HotelTimeInfo();
            startTime.setTime(policy.getFrom());
            deduction.setOriginalStartDeductTime(startTime);
            FeeInfo feeInfo = new FeeInfo();
            feeInfo.setFee(new BigDecimal(StringUtils.defaultIfBlank(policy.getAmount(), "0")));
            feeInfo.setCurrency(rate.getCurrencyCode());
            deduction.setOriginPrice(feeInfo);
            deductions.add(deduction);
        }
        return deductions;
    }
}
