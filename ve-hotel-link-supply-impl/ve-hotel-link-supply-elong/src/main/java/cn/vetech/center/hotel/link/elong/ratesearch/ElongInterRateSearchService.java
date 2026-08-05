package cn.vetech.center.hotel.link.elong.ratesearch;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FilterProductTypeEnum;
import cn.vetech.center.hotel.link.api.enums.FreeMealEnum;
import cn.vetech.center.hotel.link.api.enums.HotelErrorCodeEnum;
import cn.vetech.center.hotel.link.api.enums.RoomStatusEnum;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchBookingRule;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongConstant;
import cn.vetech.center.hotel.link.elong.ratesearch.request.ElongRateSearchRequest;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongAdditionalTax;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongAdditionalTaxItem;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongInterBedGroup;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongRateSearch;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongRateSearchResponse;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchBookingRule;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchGift;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchHotel;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchMeals;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchNightlyRate;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchPrepayRule;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchRatePlan;
import cn.vetech.center.hotel.link.elong.ratesearch.response.ElongSearchRoom;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.HotelFplxEnum;
import cn.vetech.center.hotel.link.enums.HotelGysFplxEnum;
import cn.vetech.center.hotel.link.enums.HotelWindowEnum;
import cn.vetech.center.hotel.link.enums.VipEnum;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaotengyu
 * @since 2024-05-10 16:30
 */
@Service
public class ElongInterRateSearchService extends ElongRateSearchService {


    /**
     * @param config config
     * @param res    res
     * @param rzrq   rzrq  @return cps searchvo
     * @return vo
     */
    //        Map<String, ElongSearchHAvailPolicy> hvailPolicys = listToMap(ElongSearchHAvailPolicy.class, hotel.getHAvailPolicys(), "id");
    public LinkHotelRateSearchVO convertGjRooms(ElongConfig config, ElongRateSearchResponse res, String rzrq, boolean sfhyj, String sfgat
            , ElongRateSearchRequest req, String hotelLocalTimezone) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        if (config == null || res == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("艺龙接口请求异常");
            return vo;
        }
        String code = res.getCode();
if (StringUtils.isNotBlank(code) && code.startsWith("-1")) {
            vo.setStatus(LinkHotelVO.FAIL);
            String[] split = code.split("\\|");
            if (split.length > 1) {
                vo.setErrorMsg(split[1]);
            }
            return vo;
        }
        //处理超频
        if (StringUtils.contains(code, ElongConstant.OVER_LOCK_FLAG)) {
            return RateSearchApiRes.failOverclock(HotelErrorCodeEnum.ReqOverclock);
        }

        ElongRateSearch result = res.getResult();
        if (result == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg(JSONObject.toJSONString(res));
            return vo;
        }
        List<ElongSearchHotel> hotels = result.getHotels();
        if (hotels == null || hotels.isEmpty()) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("艺龙接口没有返回酒店信息");
            return vo;
        }
        ElongSearchHotel hotel = hotels.get(0);
        List<ElongSearchRoom> roomsel = hotel.getRooms();
        if (roomsel == null || roomsel.isEmpty()) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("艺龙接口没有返回房间信息");
            return vo;
        }
//        List<ExchangeRate> exchanges = result.getExchanges();
//        if (exchanges != null) {
//            exchanges.forEach(ex -> rate.put(ex.getCurrencyCode(), ex.getRate()));
//        }

        // 处理房型价格计划
        vo.setRooms(convertGjRoom(hotel, config, rzrq, sfhyj, sfgat, req, hotelLocalTimezone));
        vo.setStatus(LinkHotelVO.SUCCESS);
        return vo;
    }

    /**
     * 处理房型列表
     *
     * @param hotel  产品房态信息
     * @param config 配置信息
     * @param rzrq   入住日期
     * @return List<SearchRoom>
     */
    private List<SearchRoom> convertGjRoom(ElongSearchHotel hotel, ElongConfig config, String rzrq, boolean sfhyj, String sfgat
            , ElongRateSearchRequest req, String hotelLocalTimezone) {
        List<ElongSearchRoom> roomsel = hotel.getRooms();

        Map<String, ElongSearchPrepayRule> prepayRules = listToMap(ElongSearchPrepayRule.class, hotel.getPrepayRules(), "prepayRuleId");

        // 预订规则解析
        Map<String, ElongSearchBookingRule> bookingRules = listToMap(ElongSearchBookingRule.class, hotel.getBookingRules(), "bookingRuleId");
        Map<String, ElongSearchGift> gifts = listToMap(ElongSearchGift.class, hotel.getGifts(), "giftId");

        List<SearchRoom> roomscps = new ArrayList<>();
        //转换房型
        for (ElongSearchRoom roomel : roomsel) {
            SearchRoom roomcps = convertRoom(roomel);
            roomcps.setBh(config.getFybh());
            roomcps.setFymc(config.getFymc());
            roomcps.setHotelId(hotel.getHotelId());
            //转换价格计划
            List<ElongSearchRatePlan> plansel = roomel.getRatePlans();
            if (plansel == null || plansel.isEmpty()) {
                continue;
            }
            List<SearchRatePlan> planscps = new ArrayList<>();
            for (ElongSearchRatePlan planel : plansel) {
                //转换间夜
                List<ElongSearchNightlyRate> nights = planel.getNightlyRates();
                if (nights == null || nights.isEmpty() || "4".equals(planel.getProductTypes())) {
                    continue;
                }
                //config.getSupplyId() 需要过滤的供应商id
                if (StringUtils.equals(planel.getSupplyId(), config.getSupplyId())) {
                    continue;
                }
                //转换价格
                SearchRatePlan plancps = convertRatePlan(roomel, planel);
                String customerType = plancps.getCustomerType();
                //不支持中国大陆预订的价格直接过滤掉
                if (StringUtils.isNoneBlank(customerType) && !(StringUtils.equalsIgnoreCase("All", customerType) || StringUtils.equalsIgnoreCase("Chinese", customerType)
|| StringUtils.equalsIgnoreCase("ChinaGuest", customerType))) {
                    continue;
                }

                for (ElongSearchNightlyRate elongSearchNightlyRate : nights) {
                    if (StringUtils.equalsIgnoreCase("false", elongSearchNightlyRate.getStatus())) {
                        plancps.setAllStatus(RoomStatusEnum.FULL.getCode());
                        plancps.setFirstStatus(RoomStatusEnum.FULL.getCode());
                        plancps.setAllStatusDesc(RoomStatusEnum.FULL.getName());
                    }
                }

                String type = planel.getPaymentType();
                // 现付报价
                if ("SelfPay".equals(type)) {
                    continue;
                }
                plancps.setYffd(NumberUtils.toDouble(config.getYffd(), 0D));
                //取消规则
                dealPrepayRule(plancps, planel, GnGjTypeEnum.GJ.getCode(), hotelLocalTimezone);

                if ("Hotel".equals(planel.getInvoiceMode())) {
                    plancps.setInvoiceMode("Hotel");
                    // 1代表过滤预付酒店开票产品
                    if (FilterProductTypeEnum.PREPAY_HOTEL_INVOICE.getCode().equals(config.getFilterProductType())) {
                        continue;
                    }
                } else {
                    plancps.setInvoiceMode("Elong");
                    // 2代表过滤预付供应商开票产品
                    if (FilterProductTypeEnum.PREPAY_SUPPLIER_INVOICE.getCode().equals(config.getFilterProductType())) {
                        continue;
                    }
                }
                //支持发票类型  1 专票  2 普票
                if (StringUtils.equals(config.getSupportSpecialInvoice(), CommonMagicNumber.STRING1)
                        && !planel.getSupportSpecialInvoice()
                        || StringUtils.equals(config.getSupportSpecialInvoice(), CommonMagicNumber.STRING2)
                        && planel.getSupportSpecialInvoice()) {
          continue;
                }
                //专票
                if (Boolean.TRUE.equals(planel.getSupportSpecialInvoice())) {
                    plancps.setFplx(HotelFplxEnum.SPECIAL_INVOICE.getCode());
                    plancps.setGysfplx(HotelGysFplxEnum.SPECIAL_INVOICE.getCode());
                } else {
                    plancps.setFplx(HotelFplxEnum.GENERAL_INVOICE.getCode());
                    plancps.setGysfplx(HotelGysFplxEnum.GENERAL_INVOICE.getCode());
                }
                //预定规则
                bookingRule(plancps, planel, bookingRules);
                //间夜价
                List<SearchNightlyRate> nightscps = new ArrayList<>();
                for (ElongSearchNightlyRate night : nights) {
                    SearchNightlyRate nightlyRate = convertNightly(night);
                    nightscps.add(nightlyRate);//处理间夜
                }
                plancps.setNightlyRates(nightscps);
                plancps.setBh(config.getFybh());
                plancps.setFymc(config.getFymc());
                plancps.setHotelId(hotel.getHotelId());
                //币种
                String currencyCode = planel.getCurrencyCode();
                plancps.setCurrencyCode(currencyCode);

                //早餐（使用 meals 餐食节点，不再使用 board）
                ElongSearchMeals meals = planel.getMeals();
                setFreeMealNew(plancps, meals, rzrq);
                //添加扩展字段
                //是否是限价产品isPriceLimittedProduct 字段判断 添加扩展字段处理
                dealExtendField(plancps, planel, sfgat, req);
                //其他字段赋值
                buildRatePlanOhterInfo(plancps, planel);
                //附加税信息
                buildAdditionTax(plancps, planel);
                //房型描述
                buildFxDescription(plancps, planel);
                plancps.setSfhyj(sfhyj ? VipEnum.YES.getCode() : null);
                if (StringUtils.isNotBlank(plancps.getCurrencyCode())) {
                    planscps.add(plancps);
}
            }
            roomcps.setRatePlans(planscps);
            roomscps.add(roomcps);
        }
        return roomscps;
    }

    /**
     * 房型描述
     *
     * @param plancps cps
     * @param planel  艺龙
     */
    private void buildFxDescription(SearchRatePlan plancps, ElongSearchRatePlan planel) {
        StringBuilder fxBuild = new StringBuilder(NumConstant.NUM_50);
        if (StringUtils.isNotBlank(planel.getCheckInInstructions())) {
            fxBuild.append(planel.getCheckInInstructions());
            fxBuild.append("；");
        }
        if (StringUtils.isNotBlank(planel.getRoomSquareMetres()) && StringUtils.equalsIgnoreCase(planel.getRoomSquareMetres(), "0")) {
            fxBuild.append(String.format("房间面积:%s；", planel.getRoomSquareMetres()));
        }
        if (StringUtils.equalsIgnoreCase(planel.getHasWindow(), Boolean.TRUE.toString())) {
            fxBuild.append("有窗；");
        } else if (StringUtils.equalsIgnoreCase(planel.getHasWindow(), Boolean.FALSE.toString())) {
            fxBuild.append("无窗；");
        }
        if (CollectionUtils.isNotEmpty(planel.getBedGroups())) {
            List<ElongInterBedGroup> bedGroups = planel.getBedGroups();
            String bedDesc = bedGroups.stream().filter(bed -> StringUtils.isNotBlank(bed.getBedGroupDesc())).map(bed -> bed.getBedGroupDesc()).collect(Collectors.joining(SymbolConstant.COMMA));
            fxBuild.append(String.format("床型:%s；", bedDesc));
        }
        if (StringUtils.isNotBlank(planel.getSmokingDesc())) {
            fxBuild.append(planel.getSmokingDesc());
            fxBuild.append("；");
        }
        if (StringUtils.isNotBlank(planel.getRoomChildAge()) && !StringUtils.equalsIgnoreCase(planel.getRoomChildAge(), "0")) {
            fxBuild.append(String.format("房间可住儿童年龄:%s；", planel.getRoomChildAge()));
        }
if (StringUtils.isNotBlank(planel.getRoomMaxPax()) && !StringUtils.equalsIgnoreCase(planel.getRoomMaxPax(), "0")) {
            fxBuild.append(String.format("房间房间最大入住人数:%s；", planel.getRoomMaxPax()));
        }
        if (StringUtils.isNotBlank(planel.getAdultOccupancyPerRoom()) && !StringUtils.equalsIgnoreCase(planel.getAdultOccupancyPerRoom(), "0")) {
            fxBuild.append(String.format("房间最大可住成人数:%s；", planel.getAdultOccupancyPerRoom()));
        }
        if (StringUtils.isNotBlank(planel.getChildrenOccupancyPerRoom()) && !StringUtils.equalsIgnoreCase(planel.getChildrenOccupancyPerRoom(), "0")) {
            fxBuild.append(String.format("房间最大可住儿童数:%s；", planel.getAdultOccupancyPerRoom()));
        }
        if (StringUtils.isNotBlank(planel.getInternetDesc())) {
            fxBuild.append(planel.getInternetDesc());
        }
        plancps.setFxDescription(fxBuild.toString());
    }

    /**
     * 附加税信息
     *
     * @param plancps cps 价格
     * @param planel  艺龙价格
     */
     private void buildAdditionTax(SearchRatePlan plancps, ElongSearchRatePlan planel) {
        ElongAdditionalTax additionalTax = planel.getAdditionalTax();
        if (Objects.isNull(additionalTax)) {
            return;
        }
        List<ElongAdditionalTaxItem> additionalTaxItems = additionalTax.getAdditionalTaxItems();
        if (CollectionUtils.isEmpty(additionalTaxItems)) {
            return;
        }
        String totalAmountRmb = additionalTax.getTotalAmountRmb();
        String text = String.format("需要另外支付附加税和服务费：%s;", totalAmountRmb);
        String addtext = additionalTaxItems.stream().map(item -> item.getDescription()).collect(Collectors.joining(SymbolConstant.COMMA));
        String textAdd = String.format("其中包括:%s", addtext);
        List<SearchBookingRule> bookingRules = CollectionUtils.isEmpty(plancps.getBookingRules()) ? new ArrayList<>() : plancps.getBookingRules();
        SearchBookingRule rule = new SearchBookingRule();
        rule.setDescription(text + textAdd);
        bookingRules.add(rule);
    }
    /**
     * 构建其他信息
     *
     * @param plancps cps 价格
     * @param planel  艺龙价格
     */
    private void buildRatePlanOhterInfo(SearchRatePlan plancps, ElongSearchRatePlan planel) {
        plancps.setFxRoomArea(planel.getRoomSquareMetres());
        plancps.setFxBedType(planel.getBedTypeAssociationalFilter());
        plancps.setFxBedDesc(planel.getBedDescription());
        if (StringUtils.equalsIgnoreCase(planel.getHasWindow(), Boolean.TRUE.toString())) {
            plancps.setCh(HotelWindowEnum.YC.getBh());
        } else {
            plancps.setCh(HotelWindowEnum.WC.getBh());
        }
        if (StringUtils.isNotBlank(planel.getRoomChildAge()) && !StringUtils.equalsIgnoreCase(planel.getRoomChildAge(), "0")) {
            plancps.setEtzxnl(planel.getRoomChildAge());
        }
        if (StringUtils.isNotBlank(planel.getRoomMaxPax()) && !StringUtils.equalsIgnoreCase(planel.getRoomMaxPax(), "0")) {
            plancps.setFxCapacity(planel.getRoomMaxPax());
        }
        if (StringUtils.isNotBlank(planel.getAdultOccupancyPerRoom()) && !StringUtils.equalsIgnoreCase(planel.getAdultOccupancyPerRoom(), "0")) {
            plancps.setZdadult(NumberUtils.toInt(planel.getAdultOccupancyPerRoom()));
        }
        if (StringUtils.isNotBlank(planel.getChildrenOccupancyPerRoom()) && !StringUtils.equalsIgnoreCase(planel.getChildrenOccupancyPerRoom(), "0")) {
            plancps.setZdchild(NumberUtils.toInt(planel.getChildrenOccupancyPerRoom()));
        }
        List<ElongInterBedGroup> bedGroups = planel.getBedGroups();
        if (CollectionUtils.isNotEmpty(bedGroups)) {
            String bedDesc = bedGroups.stream().map(group -> group.getBedGroupDesc()).collect(Collectors.joining(SymbolConstant.COMMA));
            plancps.setFxBedDesc(bedDesc);
        }
    }
    /**
     * @param night night
     * @return night
     */
    private SearchNightlyRate convertNightly(ElongSearchNightlyRate night) {
        SearchNightlyRate nightcps = new SearchNightlyRate();
        String date = night.getDate();
        if (date.contains("T")) {
            date = date.substring(0, date.indexOf("T"));
        }
        String price = night.getRate();
        nightcps.setDate(date);
        nightcps.setStatus(night.getStatus());
        nightcps.setPriceBeforTax(price);
        nightcps.setPriceAfterTax(price);
        return nightcps;
    }

}