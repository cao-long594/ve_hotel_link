package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.HotelDeductionTypeEnum;
import cn.vetech.center.hotel.link.api.enums.HotelTimeZoneEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.HotelLadderDeductionInfo;
import cn.vetech.center.hotel.link.api.ratesearch.vo.LinkHotelRateSearchVO;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchNightlyRate;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRatePlan;
import cn.vetech.center.hotel.link.api.ratesearch.vo.SearchRoom;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.IHotelLinkSupplyService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.HotelRoomUtils;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.charge.cloud.modules.utils.http.VeHttpRequestRetryHandler;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import cn.vetech.charge.hotel.HotelGngjEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vetech
 */
@Service
public class RateSearchDistributeService {

    /**
     *
     */
    @Autowired
    private SupplyDistributeService supplyDistributeService;
    /**
     *
     */
    @Autowired
    private HotelConfigDistributeService configDistributeService;
    /**
     * 国内
     */
    public static final String CONFIG_GN = "GN";
    /**
     * 港澳台
     */
    public static final String CONFIG_GAT = "GAT";
    /**
     * 国际
     */
    public static final String CONFIG_GJ = "GJ";

    /**
     * @param dto 1
     * @return 1
     */
    public LinkHotelRateSearchVO rateSearch(LinkHotelRateSearchDTO dto) throws SupplyConnectException {
        configDistributeService.setConfig(dto);
        IHotelLinkSupplyService supplyService = supplyDistributeService.getSupplyService(dto);
        //初始化默认值
        initDto(dto);
        String requestTime = VeDate.getStringDate();
        long millisStart = System.currentTimeMillis();
        // 设置为不需要重试
        VeHttpRequestRetryHandler.setShouldRetry(Boolean.FALSE);
        try {
            LinkHotelRateSearchVO vo = supplyService.rateSearch(dto);
            Optional.ofNullable(vo).filter(item -> Objects.isNull(item.getFirReqCostMillis())).ifPresent(item -> item.setFirReqCostMillis(System.currentTimeMillis() - millisStart));
            doCommonHandle(vo, requestTime, dto);
            return vo;
        } finally {
            VeHttpRequestRetryHandler.clearShouldRetry();
        }
    }

    /**
     * 公共处理
     *
     * @param vo          响应结果
     * @param requestTime 请求时间
     */
    private void doCommonHandle(LinkHotelRateSearchVO vo, String requestTime, LinkHotelRateSearchDTO dto) {
        if (vo == null || CollectionUtils.isEmpty(vo.getRooms())) {
            return;
        }

        String now = VeDate.getStringDate();
        Iterator<SearchRoom> roomIterator = vo.getRooms().iterator();
        while (roomIterator.hasNext()) {
            SearchRoom room = roomIterator.next();
            // 格式化面积
            room.setRoomArea(HotelRoomUtils.fmtArea(room.getRoomArea()));
            if (CollectionUtils.isEmpty(room.getRatePlans())) {
                roomIterator.remove();
                continue;
            }

            Iterator<SearchRatePlan> ratePlanIterator = room.getRatePlans().iterator();
            while (ratePlanIterator.hasNext()) {
                SearchRatePlan ratePlan = ratePlanIterator.next();
                if (Objects.isNull(ratePlan)) {
                    ratePlanIterator.remove();
                    continue;
                }

                // 请求/返回时间
                if (StringUtils.isBlank(ratePlan.getReturnTime())) {
                    ratePlan.setRequestTime(requestTime);
                    ratePlan.setReturnTime(now);
                }

                // 价格计划的原始房型名称
                ratePlan.setFyfxmc(StringUtils.defaultIfBlank(ratePlan.getFyfxmc(), room.getRoomName()));
                // 格式化面积
                ratePlan.setFxRoomArea(HotelRoomUtils.fmtArea(ratePlan.getFxRoomArea()));

                // 处理取消规则列表
                handleLadderDeductionInfoList(ratePlan);

                // 每日价格信息跟查询日期不相符的，过滤
                if (!convertRoomStaus(ratePlan, dto)) {
                    ratePlanIterator.remove();
                }
                // 最晚取消时间为空的，统一设置为当前时间
                if (StringUtils.isBlank(ratePlan.getZwqxsj())) {
                    ratePlan.setZwqxsj(VeDate.getStringDate());
                }
            }

            if (CollectionUtils.isEmpty(room.getRatePlans())) {
                roomIterator.remove();
            }
        }
    }

      private boolean convertRoomStaus(SearchRatePlan ratePlan, LinkHotelRateSearchDTO dto) {
        if (CollectionUtils.isEmpty(ratePlan.getNightlyRates())) {
            return false;
        }
        List<SearchNightlyRate> dailyRoomRates = ratePlan.getNightlyRates();
        for (SearchNightlyRate nightlyRate : dailyRoomRates) {
            // 先校验每日房价信息
            if (StringUtils.isBlank(nightlyRate.getDate()) || StringUtils.isBlank(nightlyRate.getPriceAfterTax())) {
                return false;
            }
        }
        List<String> searchNightlyRateDateList = dailyRoomRates.stream().map(SearchNightlyRate::getDate).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(searchNightlyRateDateList)) {
            return false;
        }

        // 每日价格明细
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
            return false;
        }
        return CollectionUtils.isEqualCollection(searchNightlyRateDateList, queryDayList);
    }

     private void handleLadderDeductionInfoList(SearchRatePlan ratePlan) {
        if (CollectionUtils.isEmpty(ratePlan.getLadderDeductionInfoList())) {
            return;
        }
        List<HotelLadderDeductionInfo> ladderDeductionInfoList = ratePlan.getLadderDeductionInfoList().stream()
                .filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ladderDeductionInfoList)) {
            ratePlan.setLadderDeductionInfoList(null);
            return;
        }
        for (HotelLadderDeductionInfo info : ladderDeductionInfoList) {
            if (Objects.isNull(info)) {
                continue;
            }
            // 如果是不可取消，把结束时间置空
            if (HotelDeductionTypeEnum.CANNOT_CANCEL.getCode().equals(info.getDeductionType())) {
                info.setLocalEndDeductTime(null);
                info.setBeijingEndDeductTime(null);
                info.setOriginalEndDeductTime(null);
            }
        }
        ratePlan.setLadderDeductionInfoList(ladderDeductionInfoList);
    }

    /**
     * 初始化查询报价请求
     *
     * @param dto 请求对象
     */
     private void initDto(LinkHotelRateSearchDTO dto) {
        //默认赋值成人数
        int intAdult = NumberUtils.toInt(dto.getAdult(), NumConstant.NUM_2);
        //国际 酒店
        if (StringUtils.equalsIgnoreCase(dto.getGngj(), HotelGngjEnum.GJ.getName())
                || StringUtils.containsIgnoreCase(dto.getLocalHotelId(), "IVE")
                || StringUtils.containsIgnoreCase(dto.getLocalHotelId(), "IJD")) {
            int adult = intAdult > 0 ? intAdult : NumConstant.NUM_2;
            dto.setAdult(String.valueOf(adult));
        } else {
            //国内酒店
            int adult = intAdult > 1 ? intAdult : NumConstant.NUM_2;
            dto.setAdult(String.valueOf(adult));
        }
        // 处理tcext配置参数，目前只有费控有处理originalTcext
        if (PtEnum.CHARGE.getValue().equals(dto.getPt()) && StringUtils.isNotBlank(dto.getSupplier().get("tcext"))) {
            Map<String, String> supplier = dto.getSupplier();
            String tcext = convertTcext(supplier.get("tcext"), supplier.get("originalTcext"), dto.getGngj(), dto.getSfgat());
            supplier.put("tcext", tcext);
        }
        // 国内酒店设置默认时区
        if (!GnGjTypeEnum.GJ.getCode().equals(dto.getGngj()) && StringUtils.isBlank(dto.getHotelLocalTimeZone())) {
            dto.setHotelLocalTimeZone(HotelTimeZoneEnum.UTC_800.getCode());
        }
    }

    /**
     * @param tcext tcext
     * @param gngj  gngj
     * @param sfgat sfgat
     * @return String
     */
    private String convertTcext(String tcext, String originalTcext, String gngj, String sfgat) {
        // 国际酒店查询报价，tcext没有配置，不查报价
        if (StringUtils.isBlank(originalTcext) && GnGjTypeEnum.GJ.getCode().equals(gngj)) {
            tcext = StringUtils.EMPTY;
        }
        // 国内国际分开配置，格式：GN:31200823,31200830_GJ:31200903,31200832_GAT:31200801
        // 不区分国内国际的格式：31200823,31200830,31200801
        tcext = tcext.replaceAll(SymbolConstant.FULL_COLON, SymbolConstant.HALF_COLON).replaceAll(SymbolConstant.FULL_COMMA, SymbolConstant.COMMA);
        if (!StringUtils.containsAny(tcext, SymbolConstant.HALF_COLON, SymbolConstant.UNDER_LINE)) {
            return tcext;
        }
        String tcextStr = null;
        Map<String, String> gngjTypeAndFybhMap = new HashMap<>();
        String[] gngjTypeAndFybhList = tcext.split(SymbolConstant.UNDER_LINE);
        for (String str : gngjTypeAndFybhList) {
            if (!str.contains(SymbolConstant.HALF_COLON)) {
                continue;
            }
            String[] split = str.split(SymbolConstant.HALF_COLON);
            gngjTypeAndFybhMap.put(split[0], split[1]);
        }
        if ("1".equals(sfgat)) {
            tcextStr = StringUtils.defaultString(gngjTypeAndFybhMap.get(CONFIG_GAT), gngjTypeAndFybhMap.get(CONFIG_GN));
        } else if (GnGjTypeEnum.GJ.getCode().equals(gngj)) {
            tcextStr = gngjTypeAndFybhMap.get(CONFIG_GJ);
        } else {
            tcextStr = gngjTypeAndFybhMap.get(CONFIG_GN);
        }
        return tcextStr;
    }

}
