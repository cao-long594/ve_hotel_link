package cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch;

import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.HotelDeductionTypeEnum;
import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.FeeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelLadderDeductionInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelTimeInfo;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.enums.InstantConfirmationEnum;
import cn.vetech.center.hotel.link.enums.SearchNightlyRateStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.v2.common.YlfxV2UtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.v2.ratesearch.request.YlfxV2RateSearchRequest;
import cn.vetech.center.hotel.link.supply.ylfx.v2.response.YlfxV2RateSearchResponse;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 易旅分销 V2 查询报价服务
 *
 * @author 6161
 * @date 2026/08/05
 */
@Service
public class YlfxV2RateSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(YlfxV2RateSearchService.class);
    private static final String HOTEL_SEARCH_URI = "/open/avail/hotelsearch";
    @Autowired
    private YlfxV2UtilsService utilsService;

    public LinkHotelRateSearchVO rateSearch(LinkHotelRateSearchDTO dto, YlfxConfig config) {
        try {
            YlfxV2RateSearchResponse response = JacksonUtils.parseNonEmpty(utilsService.sendPost(convertRequest(dto, config), config, HOTEL_SEARCH_URI), YlfxV2RateSearchResponse.class);
            if (response == null || !StringUtils.equals("200", response.getCode())) {
                return RateSearchApiRes.fail(response == null ? "响应结果为空" : response.getMessage());
            }
            return RateSearchApiRes.success(convertResponse(dto, response.getData()));
        } catch (Exception e) {
            LOGGER.error("易旅分销 V2 查询报价接口异常", e);
            return RateSearchApiRes.fail("接口异常");
        }
    }

    private YlfxV2RateSearchRequest convertRequest(LinkHotelRateSearchDTO dto, YlfxConfig config) {
        YlfxV2RateSearchRequest request = new YlfxV2RateSearchRequest();
        request.setCustomerCode(config.getCustomerCode());
        request.setHotelCode(dto.getHotelId());
        request.setCheckIn(dto.getCheckInDate());
        request.setCheckOut(dto.getCheckOutDate());
        request.setRoomCount(dto.getFjs());
        request.setCountry(dto.getRzrgj());
        request.setPaxRooms(buildPaxRooms(dto.getFjs(), dto.getAdult(), dto.getChild(), dto.getAge()));
        return request;
    }

    private List<YlfxV2RateSearchRequest.PaxRoom> buildPaxRooms(Integer roomCount, String adult, String child, String age) {
        List<Integer> childrenAges = StringUtils.isBlank(age) ? null : java.util.Arrays.stream(age.split(",")).map(NumberUtils::toInt).collect(Collectors.toList());
        List<YlfxV2RateSearchRequest.PaxRoom> paxRooms = new ArrayList<>();
        int count = roomCount == null ? 0 : roomCount;
        for (int index = 1; index <= count; index++) {
            YlfxV2RateSearchRequest.PaxRoom paxRoom = new YlfxV2RateSearchRequest.PaxRoom();
            paxRoom.setRoomIndex(index);
            paxRoom.setAdults(NumberUtils.toInt(adult, 2));
            paxRoom.setChildren(NumberUtils.toInt(child));
            paxRoom.setChildrenAges(childrenAges);
            paxRooms.add(paxRoom);
        }
        return paxRooms;
    }

    private LinkHotelRateSearchVO convertResponse(LinkHotelRateSearchDTO dto, YlfxV2RateSearchResponse.Data data) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setCheckInDate(dto.getCheckInDate());
        vo.setCheckOutDate(dto.getCheckOutDate());
        if (data == null || CollectionUtils.isEmpty(data.getRooms())) { return vo; }
        List<SearchRoom> rooms = new ArrayList<>();
        for (YlfxV2RateSearchResponse.Room sourceRoom : data.getRooms()) {
            SearchRoom room = RateSearchCommonUtils.initSearchRoom(FyEnum.YLFX);
            room.setHotelId(data.getHotelCode());
            room.setRoomId(sourceRoom.getRoomCode());
            room.setRoomName(sourceRoom.getRoomNameCn());
            room.setCapacity(String.valueOf(sourceRoom.getMaxOccupancy()));
            room.setRatePlans(convertRatePlans(data.getHotelCode(), sourceRoom));
            rooms.add(room);
        }
        vo.setRooms(rooms);
        return vo;
    }

    private List<SearchRatePlan> convertRatePlans(String hotelCode, YlfxV2RateSearchResponse.Room room) {
        if (CollectionUtils.isEmpty(room.getRates())) { return new ArrayList<>(); }
        return room.getRates().stream().map(rate -> {
            SearchRatePlan plan = RateSearchCommonUtils.initSearchRatePlan(FyEnum.YLFX);
            plan.setHotelId(hotelCode);
            plan.setRoomId(room.getRoomCode());
            plan.setRatePlanId(rate.getRateCode());
            plan.setRatePlanName(rate.getRateNameCn());
            plan.setCurrencyCode(rate.getCurrencyCode());
            plan.setTotalRate(rate.getTotalPrice());
            plan.setTotalZqjgdj(rate.getTotalPrice());
            plan.setZfj(NumberUtils.toDouble(rate.getTotalPrice()));
            plan.setGysyssfhj(rate.getTotalTaxAndFee());
            plan.setGysyssfbz(rate.getCurrencyCode());
            YlfxGysxdbj gysxdbj = new YlfxGysxdbj();
            gysxdbj.setHotelId(hotelCode);
            gysxdbj.setApiVersion("v2");
            plan.setGysxdbj(JacksonUtils.toJsonWithNonEmpty(gysxdbj));
            plan.setInstantConfirmation(InstantConfirmationEnum.instantConfirm(Integer.valueOf(1).equals(rate.getInstantConfirm())));
            if (rate.getMeal() != null) {
                RateSearchCommonUtils.convertFreeMealByMealNum(plan, rate.getMeal().getBreakfastCount());
            }
            plan.setLadderDeductionInfoList(convertCancelPolicies(rate));
            if (CollectionUtils.isEmpty(rate.getCancelPolicies())) {
                RateSearchCommonUtils.convertSearchPrepayRule(plan, SuffixTypeEnum.NOT_CANCEL, null, null);
            } else {
                RateSearchCommonUtils.convertSearchPrepayRule(plan, SuffixTypeEnum.TIME_CANCEL, null, rate.getCancelPolicies().get(0).getFrom());
            }
            plan.setNightlyRates((rate.getDailyPriceList() == null ? new ArrayList<YlfxV2RateSearchResponse.DailyPrice>() : rate.getDailyPriceList()).stream().map(price -> {
                SearchNightlyRate nightlyRate = new SearchNightlyRate();
                nightlyRate.setDate(price.getDate());
                nightlyRate.setPriceAfterTax(price.getPrice());
                nightlyRate.setPriceBeforTax(price.getPrice());
                nightlyRate.setZqjgdj(new BigDecimal(price.getPrice()));
                nightlyRate.setOriginPriceAfterTax(buildFeeInfo(price.getPrice(), price.getCurrencyCode(), rate.getCurrencyCode()));
                nightlyRate.setOriginPriceBeforTax(buildFeeInfo(price.getPrice(), price.getCurrencyCode(), rate.getCurrencyCode()));
                nightlyRate.setStatus(SearchNightlyRateStatusEnum.YES.getCode());
                return nightlyRate;
            }).collect(Collectors.toList()));
            return plan;
        }).collect(Collectors.toList());
    }

    /**
     * 转换取消规则明细
     *
     * @param rate 易旅报价
     * @return 标准取消规则明细
     */
    private List<HotelLadderDeductionInfo> convertCancelPolicies(YlfxV2RateSearchResponse.Rate rate) {
        List<HotelLadderDeductionInfo> deductions = new ArrayList<>();
        if (CollectionUtils.isEmpty(rate.getCancelPolicies())) {
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
            deduction.setOriginPrice(buildFeeInfo(policy.getAmount(), rate.getCurrencyCode(), rate.getCurrencyCode()));
            deductions.add(deduction);
        }
        return deductions;
    }

    /**
     * 构造供应商原始币种费用
     *
     * @param amount 金额
     * @param currency 币种
     * @param defaultCurrency 默认币种
     * @return 费用信息
     */
    private FeeInfo buildFeeInfo(String amount, String currency, String defaultCurrency) {
        FeeInfo feeInfo = new FeeInfo();
        feeInfo.setFee(new BigDecimal(StringUtils.defaultIfBlank(amount, "0")));
        feeInfo.setCurrency(StringUtils.defaultIfBlank(currency, defaultCurrency));
        return feeInfo;
    }
}
