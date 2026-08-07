package cn.vetech.center.hotel.link.util.validate;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.enums.RoomStatusEnum;
import cn.vetech.center.hotel.link.api.orderbook.dto.BookNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.api.validate.vo.LinkHotelValidateVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.CurrencyEnum;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.enums.ValidateResultEnum;
import cn.vetech.center.hotel.link.enums.orderbook.HotelOrderBookErrorCodeEnum;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.center.hotel.link.util.validate.pojo.DailyRate;
import cn.vetech.charge.cloud.modules.utils.number.BigDecimalUtil;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chengwanshan
 * @since 2022/3/23 15:21
 */
public class ValidateCommonUtils {

    private ValidateCommonUtils() {
    }

    /**
     * 是否校验成功
     *
     * @param validateVO validateVO
     * @return boolean
     */
    public static boolean validateSuccess(LinkHotelValidateVO validateVO) {
        return Objects.nonNull(validateVO) && validateVO.getStatus() != null && validateVO.getStatus() == LinkHotelVO.SUCCESS && ValidateResultEnum.YES.getCode().equals(validateVO.getResult());
    }

    /**
     * 校验下单前验价VO是否成功
     *
     * @param validateVO validateVO
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkValidateVO(LinkHotelValidateVO validateVO) {
        if (Objects.isNull(validateVO)) {
            return ImmutablePair.of(false, "验价失败");
        }
        if (Objects.nonNull(validateVO.getStatus()) && validateVO.getStatus() == LinkHotelVO.SUCCESS && ValidateResultEnum.YES.getCode().equals(validateVO.getResult())) {
            return ImmutablePair.of(true, "验价成功");
        }
        return ImmutablePair.of(false, StringUtils.isNotBlank(validateVO.getErrorMsg()) ? validateVO.getErrorMsg() : "验价失败");
    }

    /**
     * 校验总价（默认允许1元以内误差）
     *
     * @param totalPrice    预定总价
     * @param gysTotalPrice 供应商返回总价
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(String totalPrice, String gysTotalPrice) {
        return checkTotalPrice(totalPrice, gysTotalPrice, 1);
    }

    /**
     * 校验总价
     *
     * @param totalPrice      预定总价
     * @param gysTotalPrice   供应商返回总价
     * @param differencePrice 校验总价，允许的差价
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(String totalPrice, String gysTotalPrice, double differencePrice) {
        if (NumberUtils.toDouble(totalPrice) <= NumConstant.NUM_0 || NumberUtils.toDouble(gysTotalPrice) <= NumConstant.NUM_0) {
            return ImmutablePair.of(false, String.format("未通过，总房价节点异常，预定总价：【%s】，供应商返回总价：【%s】", totalPrice, gysTotalPrice));
        }
        boolean priceForDifference = valPriceForDifference(gysTotalPrice, totalPrice, differencePrice);
        if (!priceForDifference) {
            return ImmutablePair.of(false, String.format("未通过，总房价变动，预定总房价：【%s】，供应商返回总房价：【%s】", totalPrice, gysTotalPrice));
        }
        return ImmutablePair.of(true, "总房价校验通过");
    }

    /**
     * @param dto           dto
     * @param gysTotalPrice 供应商验价接口返回的总房价
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(LinkHotelValidateDTO dto, String gysTotalPrice) {
        String gngj = dto.getGngj();
        String pt = dto.getPt();
        String totalPrice = dto.getTotalPrice();
        String currencyCode = dto.getCurrencyCode();
        Map<String, String> supplier = dto.getSupplier();
        String priceToleranceType = supplier.get("priceToleranceType");

        // cps和asms默认是允许容错的
        if (PtEnum.CPS.getValue().equals(pt) || PtEnum.ASMS.getValue().equals(pt)) {
            // 验价返回价格和查询价格必须相等
            if (StringUtils.equals(priceToleranceType, "0")) {
                return checkTotalPrice(totalPrice, gysTotalPrice, 0);
            }

            // 没有配置容错类型的，按默认规则进行处理
            if (StringUtils.isBlank(priceToleranceType)) {
                // 如果币种为空或人民币，默认国内容错1元，国际容错5元
                if (sfCNY(currencyCode)) {
                    return checkTotalPriceSub(totalPrice, gysTotalPrice, gngj, NumConstant.NUM_1, NumConstant.NUM_5);
                }
                // 币种非人民币，容错金额取变更前的成本价的1%
                double differencePrice = BigDecimalUtil.toBigDecimal(totalPrice).multiply(BigDecimal.valueOf(NumConstant.D001)).doubleValue();
                return checkTotalPriceSub(totalPrice, gysTotalPrice, gngj, differencePrice, differencePrice);
            }

            // 验价返回价格和查询价格可以相差正负*元，示例：ABS_5，表示可以允许总价误差正负5元
            if (StringUtils.startsWith(priceToleranceType, "ABS_")) {
                String substringAfter = StringUtils.substringAfter(priceToleranceType, "ABS_");
                double differencePrice = NumberUtils.toDouble(substringAfter);
                return checkTotalPrice(totalPrice, gysTotalPrice, gngj, differencePrice, differencePrice);
            }

            // 验价返回价格-查询报价小于*元，示例：SUB_5，表示可以允许验价返回总价-查询报价总价小于5元
            if (StringUtils.startsWith(priceToleranceType, "SUB_")) {
                String substringAfter = StringUtils.substringAfter(priceToleranceType, "SUB_");
                double differencePrice = NumberUtils.toDouble(substringAfter);
                return checkTotalPriceSub(totalPrice, gysTotalPrice, gngj, differencePrice, differencePrice);
            }
        }
        // 其他的不允许容错，只要供应商验价接口返回的房价跟查询报价的房价不一致，拦截下单
        return ValidateCommonUtils.checkTotalPrice(totalPrice, gysTotalPrice, 0);
    }

    /**
     * 币种是否为人民币，默认币种为人民币
     *
     * @param currencyCode currencyCode
     * @return boolean
     */
    private static boolean sfCNY(String currencyCode) {
        return StringUtils.isBlank(currencyCode) || StringUtils.equalsAnyIgnoreCase(currencyCode, CurrencyEnum.CNY.getCurrency(), CurrencyEnum.RMB.getCurrency());
    }

    /**
     * 国际供应 校验总价
     *
     * @param totalPrice        cps总价
     * @param gysTotalPrice     供应商总价
     * @param gngj              国内国际
     * @param differencePrice   国内允许差额
     * @param gjDifferencePrice 国际允许差额
     * @return true成功
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(String totalPrice, String gysTotalPrice, String gngj, double differencePrice, double gjDifferencePrice) {
        if (NumberUtils.toDouble(totalPrice) <= NumConstant.NUM_0 || NumberUtils.toDouble(gysTotalPrice) <= NumConstant.NUM_0) {
            return ImmutablePair.of(false, String.format("未通过，总房价节点异常，预定总价：【%s】，供应商返回总价：【%s】", totalPrice, gysTotalPrice));
        }
        boolean priceForDifference;
        if (StringUtils.equals(gngj, GnGjTypeEnum.GJ.getCode())) {
            priceForDifference = valPriceForDifference(gysTotalPrice, totalPrice, gjDifferencePrice);
        } else {
            priceForDifference = valPriceForDifference(gysTotalPrice, totalPrice, differencePrice);
        }
        if (!priceForDifference) {
            return ImmutablePair.of(false, String.format("未通过，总房价变动，预定总房价：【%s】，供应商返回总房价：【%s】", totalPrice, gysTotalPrice));
        }
        return ImmutablePair.of(true, "总房价校验通过");
    }

    /**
     * 校验总价
     *
     * @param totalPrice        cps总价
     * @param gysTotalPrice     供应商总价
     * @param gngj              国内国际
     * @param differencePrice   国内允许差额
     * @param gjDifferencePrice 国际允许差额
     * @return true成功
     */
    public static ImmutablePair<Boolean, String> checkTotalPriceSub(String totalPrice, String gysTotalPrice, String gngj, double differencePrice, double gjDifferencePrice) {
        if (NumberUtils.toDouble(totalPrice) <= NumConstant.NUM_0 || NumberUtils.toDouble(gysTotalPrice) <= NumConstant.NUM_0) {
            return ImmutablePair.of(false, String.format("未通过，总房价节点异常，预定总价：【%s】，供应商返回总价：【%s】", totalPrice, gysTotalPrice));
        }
        boolean priceForDifference;
        if (StringUtils.equals(gngj, GnGjTypeEnum.GJ.getCode())) {
            priceForDifference = valPriceForInterval(gysTotalPrice, totalPrice, gjDifferencePrice);
        } else {
            priceForDifference = valPriceForInterval(gysTotalPrice, totalPrice, differencePrice);
        }
        if (!priceForDifference) {
            return ImmutablePair.of(false, String.format("未通过，总房价变动，预定总房价：【%s】，供应商返回总房价：【%s】", totalPrice, gysTotalPrice));
        }
        return ImmutablePair.of(true, "总房价校验通过");
    }

    /**
     * 校验总价允许差额（仅针对币种是人民币）
     *
     * @param totalPrice        cps总价
     * @param gysTotalPrice     供应商总价
     * @param gngj              国内国际
     * @param pt                平台
     * @param differencePrice   国内允许差额
     * @param gjDifferencePrice 国际允许差额
     * @return true成功
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(String totalPrice, String gysTotalPrice, String gngj, String pt, double differencePrice, double gjDifferencePrice) {
        if (PtEnum.CPS.getValue().equals(pt)) {
            // CPS预订，币种为人民币，国内允许误差1元，国际允许误差5元
            return ValidateCommonUtils.checkTotalPrice(totalPrice, gysTotalPrice, gngj, differencePrice, gjDifferencePrice);
        } else {
            return ValidateCommonUtils.checkTotalPrice(totalPrice, gysTotalPrice, 0);
        }
    }

    /**
     * 校验总价允许差额（根据币种）
     * 币种为人民币，国内酒店预订总价允许容错1元，国际酒店预订总价允许容错5元
     * 币种为外币，国内酒店、国际酒店预订总价允许容错1%
     *
     * @param totalPrice    预订总价
     * @param gysTotalPrice 供应商总价
     * @param gngj          国内国际
     * @param pt            平台
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkTotalPrice(String totalPrice, String gysTotalPrice, String gngj, String pt, String currencyCode) {
        if (StringUtils.isBlank(currencyCode) || StringUtils.equalsAnyIgnoreCase(currencyCode, CurrencyEnum.CNY.getCurrency(), CurrencyEnum.RMB.getCurrency())) {
            return checkTotalPrice(totalPrice, gysTotalPrice, gngj, pt, NumConstant.NUM_1, NumConstant.NUM_5);
        }
        // 变更前的成本价的1%
        double differencePrice = BigDecimalUtil.toBigDecimal(totalPrice).multiply(BigDecimal.valueOf(NumConstant.D001)).doubleValue();
        return checkTotalPrice(totalPrice, gysTotalPrice, gngj, pt, differencePrice, differencePrice);
    }

    /**
     * 差额方式比较价格（可允许供应商验价接口返回的房价 - 查询报价的房价 < 某个差值）
     *
     * @param gysPrice   供应商验价接口返回的房价
     * @param price      查询报价的房价，即验价接口dto中的总房价
     * @param difference 差值
     * @return boolean
     */
    private static boolean valPriceForInterval(String gysPrice, String price, double difference) {
        return new BigDecimal(gysPrice).subtract(new BigDecimal(price)).compareTo(BigDecimal.valueOf(difference)) < NumConstant.NUM_0;
    }

    /**
     * 差值方式比较价格（可允许供应商验价接口返回的房价跟查询报价的房价上下误差某个数值）
     *
     * @param gysPrice   供应商实时返回的价格
     * @param price      下单预定价格
     * @param difference 允许的差额
     * @return boolean
     */
    private static boolean valPriceForDifference(String gysPrice, String price, double difference) {
        double differencePrice = NumberUtils.toDouble(new BigDecimal(price).subtract(new BigDecimal(gysPrice)).abs().toString());
        return differencePrice <= difference;
    }


    /**
     * 差额方式比较价格（可允许的差额在**以内）
     *
     * @param gysPrice   供应商实时返回的价格
     * @param price      下单预定价格
     * @param difference 允许的差额
     * @return boolean
     */
    public static boolean valPriceForDifference(double gysPrice, double price, double difference) {
        double differencePrice = NumberUtils.toDouble(BigDecimal.valueOf(price).subtract(BigDecimal.valueOf(gysPrice)).abs().toString());
        return differencePrice <= difference;
    }


    /**
     * 校验每日房价（默认保留两位小数，四舍五入进行比价）
     *
     * @param nightlyRates 标准请求参数每日房价信息
     * @param dailyRates   供应商返回每日房价信息
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkEveryDayDetails(List<BookNightlyRate> nightlyRates, List<DailyRate> dailyRates) {
        return checkEveryDayDetails(nightlyRates, dailyRates, NumConstant.NUM_2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 校验每日房价
     *
     * @param nightlyRates 标准请求参数每日房价信息
     * @param dailyRates   供应商返回每日房价信息
     * @param newScale     小数位数
     * @param roundingMode 舍入模式
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkEveryDayDetails(List<BookNightlyRate> nightlyRates, List<DailyRate> dailyRates, int newScale, int roundingMode) {
        if (CollectionUtils.isEmpty(nightlyRates) || CollectionUtils.isEmpty(dailyRates)) {
            return ImmutablePair.of(false, "每日房价节点为空");
        }
        // 供应商返回每日房价信息按照日期分组
        Map<String, List<DailyRate>> dailyRatesMap = dailyRates.stream()
                .map(n -> {
                    if (StringUtils.isBlank(n.getDate())) {
                        return null;
                    }
                    n.setDate(VeDateUtils.convertDate(n.getDate()));
                    return n;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(DailyRate::getDate));

        for (BookNightlyRate bookNightlyRate : nightlyRates) {
            String dateStr = VeDateUtils.convertDate(bookNightlyRate.getDate());
            List<DailyRate> dailyRateList = dailyRatesMap.get(dateStr);
            if (CollectionUtils.size(dailyRateList) != NumConstant.NUM_1) {
                return ImmutablePair.of(false, String.format("%s房价信息有误，无价格或存在多条价格", dateStr));
            }
            DailyRate orderPrice = dailyRateList.get(0);
            // 供应商返回价格
            String realPrice = orderPrice.getPrice();
            // 预定价格
            String priceAfterTax = bookNightlyRate.getPriceAfterTax();
            // 校验每日房价
            boolean halfDown = valPriceForRound(realPrice, priceAfterTax, newScale, roundingMode);
            if (!halfDown) {
                return ImmutablePair.of(false, String.format("房价变动，%s预定每日房价：%s，供应商返回每日新价格：%s", dateStr, priceAfterTax, realPrice));
            }
        }
        return ImmutablePair.of(true, "每日房价校验通过");
    }

    /**
     * 舍入方式比较价格
     *
     * @param gysPrice     供应商实时返回的价格
     * @param price        下单预定价格
     * @param newScale     要返回的值的小数位数
     * @param roundingMode 要应用的舍入模式
     * @return boolean
     */
    private static boolean valPriceForRound(String gysPrice, String price, int newScale, int roundingMode) {
        if (NumberUtils.toDouble(gysPrice) <= NumConstant.NUM_0 || NumberUtils.toDouble(price) <= NumConstant.NUM_0) {
            return false;
        }
        BigDecimal gysPriceBigDecimal = new BigDecimal(gysPrice).setScale(newScale, roundingMode);
        BigDecimal priceDecimal = new BigDecimal(price).setScale(newScale, roundingMode);
        return gysPriceBigDecimal.compareTo(priceDecimal) == 0;
    }

    /**
     * 校验每日房量
     *
     * @param nightlyRates  标准请求参数每日房价信息
     * @param numberOfRooms 标准请求参数每日预订房间数
     * @param dailyRates    供应商返回每日房价信息
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkRoomQuantity(List<BookNightlyRate> nightlyRates, String numberOfRooms, List<DailyRate> dailyRates) {
        if (CollectionUtils.isEmpty(nightlyRates) || NumberUtils.toInt(numberOfRooms) <= 0 || CollectionUtils.isEmpty(dailyRates)) {
            return ImmutablePair.of(false, "每日预订房间数为空");
        }
        // 供应商返回每日房价信息按照日期分组
        Map<String, List<DailyRate>> dailyRatesMap = dailyRates.stream()
                .map(n -> {
                    if (StringUtils.isBlank(n.getDate())) {
                        return null;
                    }
                    n.setDate(VeDateUtils.convertDate(n.getDate()));
                    return n;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(DailyRate::getDate));

        for (BookNightlyRate bookNightlyRate : nightlyRates) {
            String dateStr = VeDateUtils.convertDate(bookNightlyRate.getDate());
            List<DailyRate> dailyRateList = dailyRatesMap.get(dateStr);
            if (CollectionUtils.size(dailyRateList) != NumConstant.NUM_1) {
                return ImmutablePair.of(false, String.format("%s房量信息有误，房量为空或存在多条", dateStr));
            }
            DailyRate orderPrice = dailyRateList.get(0);
            // 供应商返回房量
            String roomQuantity = orderPrice.getRoomQuantity();
            // 校验每日房量
            if (NumberUtils.toInt(numberOfRooms) > NumberUtils.toInt(roomQuantity)) {
                return ImmutablePair.of(false, String.format("%s房量不足，预订房量：%s，供应商可订房量为：%s", dateStr, numberOfRooms, roomQuantity));
            }
        }
        return ImmutablePair.of(true, "每日房量校验通过");
    }

    /**
     * 验价接口赋值间夜价格
     *
     * @param vo           vo
     * @param nightlyRates nightlyRates
     */
    public static void convertValidateRooms(LinkHotelValidateVO vo, List<SearchNightlyRate> nightlyRates) {
        if (CollectionUtils.isEmpty(nightlyRates)) {
            return;
        }
        List<SearchRoom> rooms = new ArrayList<>();
        SearchRoom room = new SearchRoom();
        List<SearchRatePlan> ratePlans = new ArrayList<>();
        SearchRatePlan plan = new SearchRatePlan();
        plan.setNightlyRates(nightlyRates);
        ratePlans.add(plan);
        room.setRatePlans(ratePlans);
        rooms.add(room);
        vo.setRooms(rooms);
    }

    /**
     * @param vo           vo
     * @param nightlyRates nightlyRates
     * @param dto          dto
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> convertValidateRooms(LinkHotelValidateVO vo, List<SearchNightlyRate> nightlyRates, LinkHotelValidateDTO dto) {
        if (CollectionUtils.isEmpty(nightlyRates)) {
            return ImmutablePair.of(true, null);
        }
        String checkInDate = VeDateUtils.convertDate(dto.getCheckInDate());
        String checkOutDate = VeDateUtils.convertDate(dto.getCheckOutDate());
        // 查询天数
        int queryDays = VeDate.getTwoDay(checkOutDate, checkInDate);
        // 构建查询预订起止日期集合
        List<String> queryDayList = new ArrayList<>();
        for (int i = 0; i < queryDays; i++) {
            queryDayList.add(VeDate.getNextDay(checkInDate, String.valueOf(i)));
        }
        if (CollectionUtils.isEmpty(queryDayList)) {
            return ImmutablePair.of(false, "验价接口，入离日期有误");
        }

        // 供应商每日房价按日期分组
        Map<String, List<SearchNightlyRate>> priceGroupByDateMap = nightlyRates.stream()
                .peek(p -> p.setDate(VeDateUtils.convertDate(p.getDate())))
                .filter(priceAmount -> StringUtils.isNotBlank(priceAmount.getDate()))
                .collect(Collectors.groupingBy(SearchNightlyRate::getDate));
        if (MapUtils.isEmpty(priceGroupByDateMap)) {
            return ImmutablePair.of(false, "验价接口，供应商返回每日价格信息有误");
        }
        for (String day : queryDayList) {
            List<SearchNightlyRate> dailyRates = priceGroupByDateMap.get(day);
            // 异常情况，该日期无价格数据 或 存在多条价格数据
            if (CollectionUtils.size(dailyRates) != NumConstant.NUM_1) {
                return ImmutablePair.of(false, "验价接口，供应商返回【" + day + "】房价信息有误，房价数量【" + CollectionUtils.size(dailyRates) + "】");
            }
            SearchNightlyRate rate = dailyRates.get(0);
            String priceAfterTax = rate.getPriceAfterTax();
            if (StringUtils.isBlank(priceAfterTax) || new BigDecimal(priceAfterTax).compareTo(BigDecimal.ZERO) <= NumConstant.NUM_0) {
                return ImmutablePair.of(false, "验价接口，供应商返回【" + day + "】房价信息有误，房价【" + priceAfterTax + "】");
            }
        }
        convertValidateRooms(vo, nightlyRates);
        return ImmutablePair.of(true, null);
    }

    /**
     * 当供应验价接口响应与查询报价一致 可用此方法
     *
     * @param dto         验价请求参数
     * @param searchRooms 标准房型集合
     * @return LinkHotelValidateVO
     */
    public static LinkHotelValidateVO getLinkHotelValidateVO(LinkHotelValidateDTO dto, List<SearchRoom> searchRooms) {
        if (CollectionUtils.isEmpty(searchRooms)) {
            return ValidateApiRes.fail("searchRooms数据为空");
        }
        SearchRoom room = searchRooms.get(0);
        List<SearchRatePlan> ratePlans = room.getRatePlans();
        if (CollectionUtils.isEmpty(ratePlans)) {
            return ValidateApiRes.fail("ratePlans数据为空");
        }
        List<SearchRatePlan> ratePlanList = ratePlans.stream().filter(ratePlan ->
                        dto.getRatePlanId().equals(ratePlan.getRatePlanId()) && dto.getPayment().equals(ratePlan.getPayment()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ratePlanList)) {
            return ValidateApiRes.fail("ratePlanList为空");
        }
        SearchRatePlan searchRatePlan = ratePlanList.get(0);
        if (CollectionUtils.isEmpty(searchRatePlan.getNightlyRates())) {
            return ValidateApiRes.fail("间夜价格为空");
        }
        //校验总价
        BigDecimal sum = searchRatePlan.getNightlyRates().stream().map(rate -> BigDecimalUtil.toBigDecimal(rate.getPriceAfterTax())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal numberOfRooms = new BigDecimal(dto.getNumberOfRooms());
        BigDecimal gysTotalPrice = sum.multiply(numberOfRooms);
        ImmutablePair<Boolean, String> immutablePair = ValidateCommonUtils.checkTotalPrice(dto.getTotalPrice(), String.valueOf(gysTotalPrice), dto.getGngj(), Double.parseDouble(dto.getNumberOfRooms()), 5);
        if (!immutablePair.getLeft()) {
            return ValidateApiRes.fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20004, immutablePair.getRight());
        }
        for (SearchRatePlan ratePlan : ratePlanList) {
            if (StringUtils.equals(RoomStatusEnum.FULL.getCode(), ratePlan.getFirstStatus())) {
                return ValidateApiRes.fail(HotelOrderBookErrorCodeEnum.GYSE_CFMD_20002, "房量不足");
            }
        }
        LinkHotelValidateVO vo = new LinkHotelValidateVO();
        vo.setRooms(searchRooms);
        return ValidateApiRes.success(vo);
    }

    public static ImmutablePair<Boolean, String> checkZwqxsj(String extendZwqxsj, String zwqxsj) {
        if (StringUtils.isAnyBlank(zwqxsj, extendZwqxsj)) {
            return ImmutablePair.of(false, "取消规则校验不通过，查询报价最晚取消时间" + extendZwqxsj + "，可订检查最晚取消时间" + zwqxsj);
        }
        if (StringUtils.equals(zwqxsj, extendZwqxsj)) {
            return ImmutablePair.of(true, "取消规则校验通过");
        }
        // 最晚取消时间变晚，按成功解析
        if (VeDate.compareDate(zwqxsj, extendZwqxsj) >= 0) {
            return ImmutablePair.of(true, "取消规则校验通过");
        }
        return ImmutablePair.of(false, "取消规则校验不通过，查询报价最晚取消时间" + extendZwqxsj + "，可订检查最晚取消时间" + zwqxsj);
    }

    /**
     * @param freeMeal       freeMeal
     * @param extendFreeMeal extendFreeMeal
     * @return ImmutablePair
     */
    public static ImmutablePair<Boolean, String> checkFreeMeal(String extendFreeMeal, String freeMeal) {
        if (StringUtils.isAnyBlank(freeMeal, extendFreeMeal)) {
            return ImmutablePair.of(false, "早餐信息校验不通过，早餐为空");
        }
        if (StringUtils.equals(freeMeal, extendFreeMeal)) {
            return ImmutablePair.of(true, "早餐信息校验通过");
        }
        // 如果是固定份数早餐，早餐变多了按成功处理
        if (StringUtils.startsWith(extendFreeMeal, "B_") && StringUtils.startsWith(freeMeal, "B_")) {
            int freeMealCount = NumberUtils.toInt(StringUtils.substringAfter(freeMeal, "B_"));
            int extendFreeMealCount = NumberUtils.toInt(StringUtils.substringAfter(extendFreeMeal, "B_"));
            if (freeMealCount > extendFreeMealCount) {
                return ImmutablePair.of(true, "早餐信息校验通过");
            }
        }
        return ImmutablePair.of(false, "早餐信息校验不通过，查询报价早餐" + extendFreeMeal + "，可订检查早餐" + freeMeal);
    }
}
 