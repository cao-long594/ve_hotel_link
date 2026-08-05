package cn.vetech.center.hotel.link.supply.ylfx.ratesearch;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.RoomStatusEnum;
import cn.vetech.center.hotel.link.api.enums.SuffixTypeEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.*;
import cn.vetech.center.hotel.link.enums.InvoiceModeEnum;
import cn.vetech.center.hotel.link.enums.PaymentEnum;
import cn.vetech.center.hotel.link.enums.SearchNightlyRateStatusEnum;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxConfig;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxGysxdbj;
import cn.vetech.center.hotel.link.supply.ylfx.common.YlfxUtilsService;
import cn.vetech.center.hotel.link.supply.ylfx.enums.YlfxMethodEnum;
import cn.vetech.center.hotel.link.supply.ylfx.ratesearch.request.GetProductListRequest;
import cn.vetech.center.hotel.link.supply.ylfx.ratesearch.request.GetProductPriceDailyRequest;
import cn.vetech.center.hotel.link.supply.ylfx.ratesearch.request.GetRoomStatusDailyRequest;
import cn.vetech.center.hotel.link.supply.ylfx.ratesearch.response.*;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchCommonUtils;
import cn.vetech.charge.base.CommonMagicNumber;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 6161
 * @date 2024/07/18
 */
@Service
public class YlfxRateSearchService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(YlfxRateSearchService.class);
    /**
     * 工具类
     */
    @Autowired
    private YlfxUtilsService utilsService;

    /**
     * 查询报价
     *
     * @param dto    dto
     * @param config config
     * @return LinkHotelRateSearchVO
     */
    public LinkHotelRateSearchVO rateSearch(LinkHotelRateSearchDTO dto, YlfxConfig config) {
        List<ProductData> productDataList = getProductDataList(dto.getHotelId(), config);
        if (CollectionUtils.isEmpty(productDataList)) {
            return RateSearchApiRes.fail("产品列表为空");
        }
        List<RoomStatusDailyData> roomStatusDailyDataList = getRoomStatusDailyDataList(dto, config);
        if (CollectionUtils.isEmpty(roomStatusDailyDataList)) {
            return RateSearchApiRes.fail("每日房态为空");
        }
        List<ProductPriceDailyData> productPriceDailyDataList = getProductPriceDailyDataList(dto, config);
        if (CollectionUtils.isEmpty(productPriceDailyDataList)) {
            return RateSearchApiRes.fail("每日房价为空");
        }
        try {
            roomStatusDailyDataList.forEach(status -> status.setNight(VeDateUtils.dateNumToDateStr(status.getNight())));
            productPriceDailyDataList.forEach(price -> price.setNight(VeDateUtils.dateNumToDateStr(price.getNight())));
            return convertRateSearchVO(dto, productDataList, roomStatusDailyDataList, productPriceDailyDataList);
        } catch (Exception e) {
            logger.error("查询报价接口参数转换异常【{}】", e.getMessage(), e);
            return RateSearchApiRes.fail("参数转换异常");
        }
    }

    /**
     * 解析标准报价
     *
     * @param dto                       dto
     * @param productDataList           productDataList
* @param roomStatusDailyDataList   roomStatusDailyDataList
     * @param productPriceDailyDataList productPriceDailyDataList
     * @return LinkHotelRateSearchVO
     */
    private LinkHotelRateSearchVO convertRateSearchVO(LinkHotelRateSearchDTO dto,
                                                      List<ProductData> productDataList,
                                                      List<RoomStatusDailyData> roomStatusDailyDataList,
                                                      List<ProductPriceDailyData> productPriceDailyDataList) {
        LinkHotelRateSearchVO vo = new LinkHotelRateSearchVO();
        vo.setCheckInDate(dto.getCheckInDate());
        vo.setCheckOutDate(dto.getCheckOutDate());
        //产品Map 房型ID分组
        Map<String, List<ProductData>> productDataByRoomIdMap = productDataList.stream().collect(Collectors.groupingBy(ProductData::getRoomtypeId));
        //房态Map 房型ID + 日期 分组
        Map<String, List<RoomStatusDailyData>> roomStatusMap = roomStatusDailyDataList.stream().collect(Collectors.groupingBy(status -> getRoomStatusKey(status.getRoomtypeId(), status.getNight())));
        //房价Map 房型ID+产品ID + 日期 分组
        Map<String, List<ProductPriceDailyData>> priceMap = productPriceDailyDataList.stream().collect(Collectors.groupingBy(price -> getPriceKey(price.getRoomtypeId(), price.getProductId(), price.getNight())));
        List<SearchRoom> rooms = new ArrayList<>();
        List<String> msgList = new ArrayList<>();
        for (Map.Entry<String, List<ProductData>> productDataMap : productDataByRoomIdMap.entrySet()) {
            List<ProductData> productList = productDataMap.getValue();
            SearchRoom room = buildSearchRoom(productList);
            List<SearchRatePlan> ratePlans = new ArrayList<>();
            productList.forEach(product -> {
                if (!CommonMagicNumber.INT1.equals(product.getActive())) {
                    String format = String.format("酒店ID%s产品ID%sString format = String.format("酒店ID%s产品ID%s产品名称%s无效过滤", product.getHotelId(), product.getProductId(), product.getProductName());
                    msgList.add(format);
                    return;
                }
                SearchRatePlan plan = RateSearchCommonUtils.initSearchRatePlan(FyEnum.YLFX);
                plan.setHotelId(product.getHotelId());
                plan.setRoomId(product.getRoomtypeId());
                plan.setFyfxmc(room.getRoomName());
                plan.setRatePlanId(product.getProductId());
                plan.setRatePlanName(product.getProductName());
                plan.setPayment(PaymentEnum.PREPAID.getCode());
                RateSearchCommonUtils.convertRoomStatus(plan, RoomStatusEnum.GOOD);
                if (CommonMagicNumber.INT0.equals(product.getInvoiceMode())) {//0. 平台开票，  1. 酒店开票
                    plan.setInvoiceMode(FyEnum.YLFX.getFyen());
                } else if (CommonMagicNumber.INT1.equals(product.getInvoiceMode())) {
                    plan.setInvoiceMode(InvoiceModeEnum.HOTEL.getCode());
                }
                YlfxGysxdbj gysxdbj = new YlfxGysxdbj();
                gysxdbj.setHotelId(product.getHotelId());
                gysxdbj.setInvoiceMode(product.getInvoiceMode());
                plan.setGysxdbj(JacksonUtils.toJsonWithNonEmpty(gysxdbj));
                RateSearchCommonUtils.convertFreeMealByMealNum(plan, product.getBreakfastNumber());//早餐
                buildCancelRule(plan, product, dto.getCheckInDate());//取消规则
                buildBookingRule(plan, product, dto.getCheckInDate());//预定规则
                buildDailyPrice(plan, roomStatusMap, priceMap, dto.getCheckInDate(), dto.getCheckOutDate());//间夜价格
                if (CollectionUtils.isEmpty(plan.getNightlyRates())) {
                    String format = String.format("房型ID%s产品ID%s无间夜价格过滤", plan.getRoomId(), plan.getRatePlanId());
                    msgList.add(format);
                    return;
        }
                ratePlans.add(plan);
            });
            if (CollectionUtils.isEmpty(ratePlans)) {
                String format = String.format("房型ID%s无有效产品列表过滤", productDataMap.getKey());
                msgList.add(format);
                continue;
            }
            room.setRatePlans(ratePlans);
            rooms.add(room);
        }
        if (CollectionUtils.isNotEmpty(msgList)){
            logger.warn("过滤价格【{}】",JacksonUtils.toJsonWithNonEmpty(msgList));
        }
        vo.setRooms(rooms);
        return RateSearchApiRes.success(vo);
    }
    /**
     * 房态分组key
     *
     * @param roomId 房型id
     * @param date   日期
     * @return String
     */
    private String getRoomStatusKey(String roomId, String date) {
        return roomId + SymbolConstant.UNDER_LINE + date;
    }

    /**
     * 价格分组key
     *
     * @param roomId    房型id
     * @param productId 产品id
     * @param date      日期
     * @return String
     */
    private String getPriceKey(String roomId, String productId, String date) {
        return roomId + SymbolConstant.UNDER_LINE + productId + SymbolConstant.UNDER_LINE + date;
    }

    /**
     * 间夜价格
     *
     * @param plan          plan
     * @param roomStatusMap roomStatusMap
     * @param priceMap      priceMap
     * @param checkIn       checkIn
     * @param checkOut      checkOut
     */
     private void buildDailyPrice(SearchRatePlan plan, Map<String, List<RoomStatusDailyData>> roomStatusMap, Map<String, List<ProductPriceDailyData>> priceMap, String checkIn, String checkOut) {
        String roomId = plan.getRoomId();
        String ratePlanId = plan.getRatePlanId();
        List<String> dateList = VeDateUtils.getDateList(checkOut, checkIn);
        List<SearchNightlyRate> nightlyRates = new ArrayList<>();
        dateList.forEach(date -> {
            List<ProductPriceDailyData> priceDailyDataList = priceMap.get(getPriceKey(roomId, ratePlanId, date));
            if (CollectionUtils.isEmpty(priceDailyDataList)) {
                logger.warn("房型ID【{}】产品ID【{}】日期【{}】价格为空", roomId, ratePlanId, date);
                return;
            }
            ProductPriceDailyData price = priceDailyDataList.get(0);
            SearchNightlyRate nightlyRate = new SearchNightlyRate();
            nightlyRate.setDate(date);
            nightlyRate.setPriceAfterTax(price.getPrice());
            nightlyRate.setPriceBeforTax(price.getPrice());
            nightlyRate.setStatus(SearchNightlyRateStatusEnum.YES.getCode());
            List<RoomStatusDailyData> roomStatusDailyDataList = roomStatusMap.get(getRoomStatusKey(roomId, date));
            if (CollectionUtils.isEmpty(roomStatusDailyDataList)) {
                nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
                RateSearchCommonUtils.convertRoomStatus(plan, RoomStatusEnum.FULL);
                nightlyRates.add(nightlyRate);
                return;
            }
            RoomStatusDailyData statusDailyData = roomStatusDailyDataList.get(0);
            Integer roomStatus = statusDailyData.getRoomStatus();//房态： 0. 关房 1. 开房
            Integer roomCount = statusDailyData.getRoomCount();
            Integer overBooking = statusDailyData.getOverBooking();
            if (!CommonMagicNumber.INT1.equals(roomStatus)) {
                nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
                RateSearchCommonUtils.convertRoomStatus(plan, RoomStatusEnum.FULL);
            } else {
                if (!CommonMagicNumber.INT1.equals(overBooking) && roomCount < CommonMagicNumber.INT1) {//是否可超预订：0. 不可超 1. 可超
                    nightlyRate.setStatus(SearchNightlyRateStatusEnum.NO.getCode());
                    RateSearchCommonUtils.convertRoomStatus(plan, RoomStatusEnum.FULL);
                }
            }
            nightlyRates.add(nightlyRate);
        });
        plan.setNightlyRates(nightlyRates);
    }
    /**
     * 处理房型数据
     *
     * @param productList productList
     * @return SearchRoom
     */
    private SearchRoom buildSearchRoom(List<ProductData> productList) {
        SearchRoom room = RateSearchCommonUtils.initSearchRoom(FyEnum.YLFX);
        ProductData product = productList.get(0);
        room.setHotelId(product.getHotelId());
        room.setRoomId(product.getRoomtypeId());
        room.setRoomName(product.getRoomtypeName());
        return room;
    }
    /**
     * 取消规则
     *
     * @param plan    plan
     * @param product product
     * @param checkIn checkIn
     */
    private void buildCancelRule(SearchRatePlan plan, ProductData product, String checkIn) {
        Integer cancelable = product.getCancelable();//0. 不可取消 1. 可取消
        Integer cancelAdvancedDays = product.getCancelAdvancedDays();
        String cancelAdvancedTime = product.getCancelAdvancedTime();
        if (CommonMagicNumber.INT1.equals(cancelable) && Objects.nonNull(cancelAdvancedDays) && StringUtils.isNotBlank(cancelAdvancedTime)) {
            String preDay = VeDate.getPreDay(checkIn, CommonMagicNumber.INT0 - cancelAdvancedDays);
            String cancelTime = preDay + " " + cancelAdvancedTime + ":00";
            RateSearchCommonUtils.convertSearchPrepayRule(plan, SuffixTypeEnum.TIME_CANCEL, null, cancelTime);
            return;
        }
        RateSearchCommonUtils.convertSearchPrepayRule(plan, SuffixTypeEnum.NOT_CANCEL, null, null);
    }

    /**
     * 预定规则
     *
     * @param plan    plan
     * @param product product
     * @param checkIn checkIn
     */
    private void buildBookingRule(SearchRatePlan plan, ProductData product, String checkIn) {
        if (Objects.isNull(product.getBookAdvancedDays()) || product.getBookAdvancedDays() < CommonMagicNumber.INT1) {
            return;
        }
        List<SearchBookingRule> bookingRules = new ArrayList<>();
        SearchBookingRule bookingRule = new SearchBookingRule();
        String ydsj = VeDate.getStringDateShort();
        int twoDay = VeDate.getTwoDay(checkIn, ydsj);
        Integer bookAdvancedDays = product.getBookAdvancedDays();
        bookingRule.setAdvancedays(String.valueOf(bookAdvancedDays));
        if (bookAdvancedDays > twoDay) {
            logger.warn("产品ID【{}】需提前【{}】天预定，规则不满足设置满房", product.getProductId(), bookAdvancedDays);
            RateSearchCommonUtils.convertRoomStatus(plan, RoomStatusEnum.FULL);
        }
        bookingRules.add(bookingRule);
        plan.setBookingRules(bookingRules);
    }
    /**
     * 根据酒店ID查询酒店下所有产品信息
     *
     * @param hotelId hotelId
     * @param config  config
     * @return List<ProductData>
     */
    private List<ProductData> getProductDataList(String hotelId, YlfxConfig config) {
        try {
            GetProductListRequest request = new GetProductListRequest();
            request.setHotelId(hotelId);
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.LIST_PRODUCT);
            GetProductListResponse response = JacksonUtils.parseNonEmpty(res, GetProductListResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                logger.warn("【{}】查询酒店ID【{}】产品列表接口失败【{}】", config.getZhmc(), hotelId, immutablePair.getRight());
                return null;
            }
            return response.getProductDataList();
        } catch (Exception e) {
            logger.warn("【{}】查询酒店ID【{}】产品列表接口异常【{}】", config.getZhmc(), hotelId, e.getMessage(), e);
            return null;
        }
    }
    /**
     * 获取每日房态
     *
     * @param dto    dto
     * @param config config
     * @return List<RoomStatusDailyData>
     */
    private List<RoomStatusDailyData> getRoomStatusDailyDataList(LinkHotelRateSearchDTO dto, YlfxConfig config) {
        try {
            GetRoomStatusDailyRequest request = new GetRoomStatusDailyRequest();
            request.setHotelId(dto.getHotelId());
            request.setBeginDate(VeDateUtils.dateStrToDateNum(dto.getCheckInDate()));
            request.setEndDate(VeDateUtils.dateStrToDateNum(VeDate.getPreDay(dto.getCheckOutDate(), -1)));
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.LIST_ROOM_TYPE_DAILY);
            GetRoomStatusDailyResponse response = JacksonUtils.parseNonEmpty(res, GetRoomStatusDailyResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                logger.warn("【{}】查询酒店ID【{}】日期【{}】——【{}】每日房态接口失败【{}】", config.getZhmc(), dto.getHotelId(),
                        dto.getCheckInDate(), dto.getCheckOutDate(), immutablePair.getRight());
                return null;
            }
            return response.getRoomStatusDailyDataList();
        } catch (Exception e) {
            logger.warn("【{}】查询酒店ID【{}】日期【{}】——【{}】每日房态接口异常【{}】", config.getZhmc(), dto.getHotelId(),
                    dto.getCheckInDate(), dto.getCheckOutDate(), e.getMessage(), e);
            return null;
        }
    }
    /**
     * 获取每日房价
     *
     * @param dto    dto
     * @param config config
     * @return List<ProductPriceDailyData>
     */
    private List<ProductPriceDailyData> getProductPriceDailyDataList(LinkHotelRateSearchDTO dto, YlfxConfig config) {
        try {
            GetProductPriceDailyRequest request = new GetProductPriceDailyRequest();
            request.setHotelId(dto.getHotelId());
            request.setBeginDate(VeDateUtils.dateStrToDateNum(dto.getCheckInDate()));
            request.setEndDate(VeDateUtils.dateStrToDateNum(VeDate.getPreDay(dto.getCheckOutDate(), -1)));
            String res = utilsService.sendPost(request, config, YlfxMethodEnum.LIST_PRODUCT_DAILY);
            GetProductPriceDailyResponse response = JacksonUtils.parseNonEmpty(res, GetProductPriceDailyResponse.class);
            ImmutablePair<Boolean, String> immutablePair = utilsService.checkResponse(response);
            if (Boolean.FALSE.equals(immutablePair.getLeft())) {
                logger.warn("【{}】查询酒店ID【{}】日期【{}】——【{}】每日房价接口失败【{}】", config.getZhmc(), dto.getHotelId(),
                        dto.getCheckInDate(), dto.getCheckOutDate(), immutablePair.getRight());
                return null;
            }
            return response.getProductPriceDailyDataList();
        } catch (Exception e) {
            logger.warn("【{}】查询酒店ID【{}】日期【{}】——【{}】每日房价接口异常【{}】", config.getZhmc(), dto.getHotelId(),
                    dto.getCheckInDate(), dto.getCheckOutDate(), e.getMessage(), e);
            return null;
        }
    }

}