package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.PageVO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.hotelguestreviews.dto.HotelGuestReviewsDTO;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsExpandInfo;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsInfo;
import cn.vetech.center.hotel.link.api.hotelguestreviews.vo.HotelGuestReviewsVO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.GnGjTypeEnum;
import cn.vetech.center.hotel.link.http.SupplyConnectException;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.hotel.HotelDistributeService;
import cn.vetech.center.hotel.link.supply.service.hotelguestreviews.HotelGuestReviewsAsyncService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.bean.CommonLogBean;
import cn.vetech.center.hotel.log.util.CommonLogContext;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import cn.vetech.commlog.api.vo.CommLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author chengwanshan
 * @since 2024/12/24 15:38
 */
@Service
public class HotelGuestReviewsDistributeService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(HotelGuestReviewsDistributeService.class);

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
    private HotelGuestReviewsAsyncService guestReviewsAsyncService;
    /**
     *
     */
    @Autowired
    private HotelDistributeService hotelService;
   /**
     * @param dto 1
     * @return 1
     */
    public HotelGuestReviewsVO hotelGuestReviews(HotelGuestReviewsDTO dto) throws SupplyConnectException {
        CommonLogBean commonLogBean = CommonLogContext.get();
        CommLog commLog = null;
        if (commonLogBean != null) {
            commLog = commonLogBean.getCommonLog();
        }
        if (commLog == null) {
            commLog = new CommLog();
        }
        HotelGuestReviewsVO vo = new HotelGuestReviewsVO();
        HotelDTO detailDto = BeanMapper.map(dto, HotelDTO.class);
        if (!GnGjTypeEnum.GN.getCode().equals(detailDto.getGngj())) {
            detailDto.setGngj(GnGjTypeEnum.GJ.getCode());
        }
        //查询酒店祥
        HotelVO hotel = hotelService.getHotel(detailDto);
        if (hotel == null) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未查询到酒店信息");
            commLog.add("没有查询到酒店信息" + JacksonUtils.toJsonWithNonEmpty(detailDto));
            return vo;
        }
        //判断酒店映射
        List<Mapper> mappers = hotel.getMappers();
        dto.setMappers(mappers);

        List<Map<String, String>> configs = configService.getConfigs(dto);
        if (CollectionUtils.isEmpty(configs)) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到供应商配置信息");
            return vo;
        }
        // 目前只有CPS一家供应商有这个接口，先通过配置参数控制是否分发
        configs = configs.stream()
                .filter(config -> {
                    if (StringUtils.equals(dto.getPt(), PtEnum.CPS.getValue())) {
                        return StringUtils.equals(config.get("sfcxjddp"), "1");
                    } else if (StringUtils.equals(dto.getPt(), PtEnum.CHARGE.getValue()) || StringUtils.equals(dto.getPt(), PtEnum.ASMS.getValue())) {
                        // 费控、1w默认查cps
                        return StringUtils.equals(config.get("sfcxjddp"), "1") || FyEnum.CPS.getFybh().equals(config.get("fybh"));
                    }
                    return false;
                })
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configs)) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到需要查询酒店点评的供应商配置信息");
            return vo;
        }
        List<HotelGuestReviewsDTO> hotelGuestReviewsDTOS = buildRequestByConfigs(dto, configs);
        if (ListUtil.isEmpty(hotelGuestReviewsDTOS)) {
            vo.setStatus(LinkHotelVO.FAIL);
            logger.warn("没有获取到需要查询的供应商配置，dto:{}", JacksonUtils.toJsonWithDefault(dto));
            return vo;
        }
          logger.info("酒店客人评价接口，dtos：{}", JacksonUtils.toJsonWithNonEmpty(hotelGuestReviewsDTOS));
        List<Future<HotelGuestReviewsVO>> futureLinks = hotelGuestReviewsDTOS.stream().map(guestReviewsDTO -> {
            return guestReviewsAsyncService.hotelGuestReviewsAsync(guestReviewsDTO);
        }).collect(Collectors.toList());
        List<HotelGuestReviewsVO> guestReviewsVOS = new ArrayList<>();
        futureLinks.forEach(guestReviewsVOFuture -> {
            try {
                guestReviewsVOS.add(guestReviewsVOFuture.get());
            } catch (InterruptedException ex) {
                logger.error("酒店客人评价:异步调用异常", ex);
                HotelGuestReviewsVO guestReviewsVO = new HotelGuestReviewsVO();
                guestReviewsVO.setStatus(LinkHotelVO.FAIL);
                guestReviewsVO.setErrorMsg("异步调用异常");
                guestReviewsVOS.add(guestReviewsVO);
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex) {
                logger.error("酒店客人评价:异步调用异常", ex);
                HotelGuestReviewsVO guestReviewsVO = new HotelGuestReviewsVO();
                guestReviewsVO.setStatus(LinkHotelVO.FAIL);
                guestReviewsVO.setErrorMsg("异步调用异常");
                guestReviewsVOS.add(guestReviewsVO);
            }
        });
          List<HotelGuestReviewsInfo> guestReviewsList = new ArrayList<>();
        if (CollectionUtils.isEmpty(guestReviewsVOS)) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("异步调用异常");
            return vo;
        }
        //判断所有供应商返回的状态，有一个状态为成功，则返回成功，否则返回失败
        int status = guestReviewsVOS.stream()
                .anyMatch(reviewsVO -> Objects.nonNull(reviewsVO.getStatus()) && reviewsVO.getStatus() == LinkHotelVO.SUCCESS) ? LinkHotelVO.SUCCESS : LinkHotelVO.FAIL;
        vo.setStatus(status);

        boolean last = true;
        Integer tatal = NumConstant.NUM_0;
        Map<String, String> fybhAndConditionMap = new HashMap<>();
        for (HotelGuestReviewsVO guestReviewsVO : guestReviewsVOS) {
            // 过滤掉失败 和 点评信息列表为空的
            if (guestReviewsVO.getStatus() == LinkHotelVO.FAIL || CollectionUtils.isEmpty(guestReviewsVO.getGuestReviewsList())) {
                continue;
            }
            guestReviewsList.addAll(guestReviewsVO.getGuestReviewsList());
            PageVO reviewsVOPageVO = guestReviewsVO.getPageVO();
            // 有一个供应商不是最后一页
            if (Objects.nonNull(reviewsVOPageVO) && Objects.nonNull(reviewsVOPageVO.getLast()) && !reviewsVOPageVO.getLast()) {
                last = false;
            }
            // 只要有一个供应商返回了点评信息，但没有返回总数，那总数就不返回
            if (Objects.isNull(reviewsVOPageVO) || Objects.isNull(reviewsVOPageVO.getTotal())) {
                tatal = null;
            }
            if (Objects.nonNull(tatal)) {
                tatal = tatal + reviewsVOPageVO.getTotal();
            }
              // 组装各房源商的拓展信息
            String fybh = StringUtils.EMPTY;
            if (Objects.nonNull(guestReviewsVO.getGuestReviewsList().get(0))) {
                fybh = guestReviewsVO.getGuestReviewsList().get(0).getFybh();
            }
            if (StringUtils.isNotBlank(fybh) && Objects.nonNull(reviewsVOPageVO) && StringUtils.isNotBlank(reviewsVOPageVO.getCondition())) {
                fybhAndConditionMap.put(fybh, reviewsVOPageVO.getCondition());
            }
        }
        PageVO pageVO = new PageVO();
        pageVO.setLast(last);
        pageVO.setTotal(tatal);
        HotelGuestReviewsExpandInfo expandInfo = new HotelGuestReviewsExpandInfo();
        expandInfo.setFybhAndConditionMap(fybhAndConditionMap);
        pageVO.setCondition(JacksonUtils.toJsonWithNonEmpty(expandInfo));
        vo.setPageVO(pageVO);
        vo.setGuestReviewsList(guestReviewsList);
        return vo;
    }

     /**
     * 根据配置构建请求对象
     *
     * @param dto 费控请求对象
     * @return dto
     */
    private List<HotelGuestReviewsDTO> buildRequestByConfigs(HotelGuestReviewsDTO dto, List<Map<String, String>> configs) {
        if (!StringUtils.equals("1", dto.getSfpbcpsjg())) {
            Mapper cpsM = new Mapper();
            cpsM.setFybh(FyEnum.CPS.getFybh());
            dto.getMappers().add(cpsM);
        }
        Mapper asmsM = new Mapper();
        asmsM.setFybh(FyEnum.ASMS.getFybh());
        dto.getMappers().add(asmsM);

        Map<String, List<Mapper>> mapperMap = dto.getMappers().stream().collect(Collectors.groupingBy(Mapper::getFybh));
        return configs.stream()
                .filter(config -> {
                    String fybh = config.get("fybh");
                    //1.cps平台请求 不用走cps 也不走asms
                    //2.差旅云平台请求 不用走cps(差旅云自己调用的cps)
                    //3.费控平台请求 都得走
                    if (StringUtils.equals(dto.getPt(), PtEnum.CPS.getValue())) {
                        if (StringUtils.equals(fybh, FyEnum.CPS.getFybh()) || StringUtils.equals(fybh, FyEnum.ASMS.getFybh())) {
                            return false;
                        }
                    }
                    //2
                    if (StringUtils.equals(dto.getPt(), PtEnum.CLOUD.getValue())) {
                        if (StringUtils.equals(fybh, FyEnum.CPS.getFybh())) {
                            return false;
                        }
                    }
                     //3
                    //映射中有 就需要请求
                    if (mapperMap.containsKey(fybh)) {
                        // 拓展标准供应商
                        if (FyEnum.EXTEND.getFybh().equals(fybh)) {
                            String tcext = config.get("tcext");
                            if (StringUtils.isBlank(tcext)) {
                                return false;
                            }
                            return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                                    .anyMatch(mapperMap::containsKey);
                        }
                        return true;
                    }
                    //映射没有 需要判断是否为tcext房源  同时tcext房源使用的映射是否在映射中
                    String tcext = config.get("tcext");
                    if (StringUtils.isBlank(tcext)) {
                        return false;
                    }
                    return Arrays.stream(tcext.replaceAll("，", ",").split(","))
                            .anyMatch(mapperMap::containsKey);
                })
                    .map(config -> {
                    HotelGuestReviewsDTO guestReviewsDTO = BeanMapper.map(dto, HotelGuestReviewsDTO.class);
                    String fybh = config.get("fybh");
                    String zhmc = config.get("zhmc");
                    guestReviewsDTO.setFybh(fybh);
                    guestReviewsDTO.setZhmc(zhmc);
                    guestReviewsDTO.setSupplier(config);
                    if (mapperMap.containsKey(fybh)) {
                        guestReviewsDTO.setHotelId(mapperMap.get(fybh).get(0).getHotelid());
                    }
                    return guestReviewsDTO;
                    //以后房源迁移齐了 可以吧这个exists判断去掉 让系统抛出异常 现在不行
                    //原因：cps有很多房源映射 和房源配置 但是目前房源实现很少 测试中会大量抛出异常
                    //当然一直加着也可以 不过不好判断是不是没有房源实现导致的错误
                }).filter(guestReviewsDTO -> distributeService.exists(guestReviewsDTO)).collect(Collectors.toList());
    }

}
