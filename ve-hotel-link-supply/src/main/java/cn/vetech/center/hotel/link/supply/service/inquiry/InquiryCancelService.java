package cn.vetech.center.hotel.link.supply.service.inquiry;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelDTO;
import cn.vetech.center.hotel.link.api.inquiry.cancel.HotelLinkInquiryOrderCancelVO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.supply.service.distribute.InquiryDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import cn.vetech.charge.cloud.modules.utils.mapper.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author xiaotengyu
 * @since 2022-04-27 18:02
 */
@Service
public class InquiryCancelService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(InquiryCancelService.class);

    /**
     * 配置服务号
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     * distribute 服务
     */
    @Autowired
    private InquiryDistributeService inquiryDistributeService;

    /**
     * 异步调用服务
     */
    @Autowired
    private InquiryAsyncService inquiryAsyncService;

    /**
     * 取消询价单
     *
     * @param dto 请求对象
     * @return 返回对象
 */
    public HotelLinkInquiryOrderCancelVO cancelInquiry(HotelLinkInquiryOrderCancelDTO dto) {
        HotelLinkInquiryOrderCancelVO vo = new HotelLinkInquiryOrderCancelVO();
        //1.通过构建请求对象
        List<HotelLinkInquiryOrderCancelDTO> hotelLinkInquiryOrderCancelDTOS = buildInquiryRequestByConfigs(dto);
        if (ListUtil.isEmpty(hotelLinkInquiryOrderCancelDTOS)) {
            logger.warn("询价单：取消询价单；供应商配置未空;dto:{}", JacksonUtils.toJsonWithNonEmpty(dto));
            vo.setStatus(LinkHotelVO.FAIL);
            return vo;
        }
        //
        List<Future<HotelLinkInquiryOrderCancelVO>> futureLinks = hotelLinkInquiryOrderCancelDTOS.stream().map(cancelDTO -> {
            return inquiryAsyncService.cancelInquiryAsync(cancelDTO);
        }).collect(Collectors.toList());
        List<HotelLinkInquiryOrderCancelVO> cancelVOS = new ArrayList<>();
        futureLinks.forEach(inquiryFuture -> {
            try {
                cancelVOS.add(inquiryFuture.get());
            } catch (InterruptedException ex) {
                logger.error("询价单:异步取消询价异常", ex);
                HotelLinkInquiryOrderCancelVO cancelVO = new HotelLinkInquiryOrderCancelVO();
                cancelVO.setStatus(LinkHotelVO.FAIL);
                cancelVO.setErrorMsg("异步取消询价异常");
                cancelVOS.add(cancelVO);
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex) {
                logger.error("询价单:异步取消询价异常", ex);
                HotelLinkInquiryOrderCancelVO cancelVO = new HotelLinkInquiryOrderCancelVO();
                cancelVO.setStatus(LinkHotelVO.FAIL);
                cancelVO.setErrorMsg("异步取消询价异常");
                cancelVOS.add(cancelVO);
            }
        });
       //判断状态；有一个供应商返回取消失败，link就返回取消询价单失败
        int status = cancelVOS.stream()
                .filter(cancelVo -> cancelVo.getStatus().intValue() == LinkHotelVO.FAIL)
                .count() > NumConstant.NUM_0 ? LinkHotelVO.FAIL : LinkHotelVO.SUCCESS;
        vo.setStatus(status);
        //失败消息
        if (status == LinkHotelVO.FAIL) {
            String errorMsg = cancelVOS.stream()
                    .filter(cancelVo -> cancelVo.getStatus() != LinkHotelVO.SUCCESS)
                    .map(cancelVo -> cancelVo.getErrorMsg())
                    .collect(Collectors.joining(SymbolConstant.SEMICOLON));
            vo.setErrorMsg(errorMsg);
        }
        return vo;
    }

     /**
     * 根据配置构建请求对象
     *
     * @param dto 费控请求对象
     * @return dto
     */
    private List<HotelLinkInquiryOrderCancelDTO> buildInquiryRequestByConfigs(HotelLinkInquiryOrderCancelDTO dto) {
        List<Map<String, String>> configs = new ArrayList<>();
        if (PtEnum.CPS.getValue().equals(dto.getPt())) {
            configs = getConfigByJkxjgysId(dto);
        } else if (PtEnum.CHARGE.getValue().equals(dto.getPt()) && StringUtils.isNotBlank(dto.getJkxjgysId())) {
            configs = getConfigByJkxjgysId(dto);
        } else {
            configs = configService.getConfigs(dto);
        }
        if (ListUtil.isEmpty(configs)) {
            return ListUtil.emptyList();
        }
        return configs.stream()
                .map(config -> {
                    HotelLinkInquiryOrderCancelDTO cgscxjdDTO = BeanMapper.map(dto, HotelLinkInquiryOrderCancelDTO.class);
                    String fybh = config.get("fybh");
                    String zhmc = config.get("zhmc");
                    cgscxjdDTO.setFybh(fybh);
                    cgscxjdDTO.setZhmc(zhmc);
                    cgscxjdDTO.setSupplier(config);
                    return cgscxjdDTO;
                }).filter(distributeDto -> inquiryDistributeService.exists(distributeDto)).collect(Collectors.toList());
    }

     /**
     * @param dto dto
     * @return List
     */
    private List<Map<String, String>> getConfigByJkxjgysId(HotelLinkInquiryOrderCancelDTO dto) {
        if (StringUtils.isBlank(dto.getJkxjgysId())) {
            return Collections.emptyList();
        }
        List<Map<String, String>> configs = new ArrayList<>();
        String[] split = dto.getJkxjgysId().split(SymbolConstant.COMMA);
        for (String fyjc : split) {
            LinkHotelDTO hotelDTO = BeanMapper.map(dto, LinkHotelDTO.class);
            // cps的fyjc唯一
            hotelDTO.setZhmc(fyjc);
            Map<String, String> config = configService.getConfig(hotelDTO);
            if (Objects.nonNull(config)) {
                configs.add(config);
            }
        }
        return configs;
    }
}
