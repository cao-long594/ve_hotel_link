package cn.vetech.center.hotel.link.supply.service.inquiry;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.HotelInquiryStatusEnum;
import cn.vetech.center.hotel.link.api.enums.HotelInquiryTypeEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateDTO;
import cn.vetech.center.hotel.link.api.inquiry.create.HotelLinkInquiryOrderCreateVO;
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
 * @since 2022-04-27 15:36
 */
@Service
public class InquiryCreateService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(InquiryCreateService.class);

    /**
     * 配置服务
     */
    @Autowired
    private HotelConfigDistributeService configService;

    /**
     * 询价单分发服务
     */
    @Autowired
    private InquiryDistributeService inquiryDistributeService;

    /**
     * 询价单创建服务
     */
    @Autowired
  private InquiryAsyncService inquiryCreateAsyncService;

    /**
     * 创建询价单
     *
     * @param dto 请求参数
     * @return vo
     */
    public HotelLinkInquiryOrderCreateVO createInquiry(HotelLinkInquiryOrderCreateDTO dto) {
        HotelLinkInquiryOrderCreateVO linkCgscxjdVO = new HotelLinkInquiryOrderCreateVO();
        List<HotelLinkInquiryOrderCreateDTO> hotelLinkCgscxjdDTOS = buildInquiryRequestByConfigs(dto);
        if (ListUtil.isEmpty(hotelLinkCgscxjdDTOS)) {
            linkCgscxjdVO.setStatus(LinkHotelVO.FAIL);
            logger.warn("询价单：新增没有供应商配置dto:{}", JacksonUtils.toJsonWithDefault(dto));
            return linkCgscxjdVO;
        }
        logger.info("询价单：询价单请求参数：{}", JacksonUtils.toJsonWithNonEmpty(hotelLinkCgscxjdDTOS));
        //分发请求 创建询价单
        List<Future<HotelLinkInquiryOrderCreateVO>> inqueryCreateFutures = hotelLinkCgscxjdDTOS.stream().map(xjd -> {
            return inquiryCreateAsyncService.createInquiryAsync(xjd);
        }).collect(Collectors.toList());
        List<HotelLinkInquiryOrderCreateVO> hotelLinkCgscxjdVOS = new ArrayList<>();
        inqueryCreateFutures.forEach(inquiry -> {
            try {
                hotelLinkCgscxjdVOS.add(inquiry.get());
            } catch (InterruptedException e) {
                logger.error("询价单：异步创建异常", e);
                HotelLinkInquiryOrderCreateVO createVO = new HotelLinkInquiryOrderCreateVO();
                createVO.setStatus(LinkHotelVO.FAIL);
                createVO.setErrorMsg("异步创建异常");
                hotelLinkCgscxjdVOS.add(createVO);
                Thread.currentThread().interrupt();
            } catch (ExecutionException ex) {
                logger.error("询价单：异步创建异常", ex);
                HotelLinkInquiryOrderCreateVO createVO = new HotelLinkInquiryOrderCreateVO();
                createVO.setStatus(LinkHotelVO.FAIL);
                createVO.setErrorMsg("异步创建异常");
                hotelLinkCgscxjdVOS.add(createVO);
            }
   //判断所有平台返回询价单的状态，有一个询价单状态为成功，则返回成功，否则返回失败
        int status = hotelLinkCgscxjdVOS.stream()
                .filter(vo -> Objects.nonNull(vo.getStatus()) && vo.getStatus().intValue() == LinkHotelVO.SUCCESS)
                .count() > 0 ? LinkHotelVO.SUCCESS : LinkHotelVO.FAIL;
        linkCgscxjdVO.setStatus(status);
        String errorMsg = hotelLinkCgscxjdVOS.stream().filter(vo -> Objects.nonNull(vo.getStatus())
                && vo.getStatus().intValue() == LinkHotelVO.FAIL
                & Objects.nonNull(vo.getErrorMsg())).map(vo -> vo.getErrorMsg()).collect(Collectors.joining(SymbolConstant.SEMICOLON));
        linkCgscxjdVO.setErrorMsg(errorMsg);
        //赋值询价单状态
        if (status == LinkHotelVO.SUCCESS) {
            linkCgscxjdVO.setXjdzt(HotelInquiryStatusEnum.ST0.getCode());
        }
        linkCgscxjdVO.setXjdid(dto.getAsmsxjdid());
        return linkCgscxjdVO;
    }

     /**
     * 根据配置构建请求对象
     *
     * @param dto 费控请求对象
     * @return dto
     */
    private List<HotelLinkInquiryOrderCreateDTO> buildInquiryRequestByConfigs(HotelLinkInquiryOrderCreateDTO dto) {
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
        //如果询价单类型是"线下备案"，则只推cps询价
        if (StringUtils.equalsIgnoreCase(dto.getXjdlb(), HotelInquiryTypeEnum.T2.getCode())) {
            return configs.stream().filter(config -> StringUtils.equalsIgnoreCase(config.get("fybh"), FyEnum.CPS.getFybh())).map(config -> {
                HotelLinkInquiryOrderCreateDTO cgscxjdDTO = BeanMapper.map(dto, HotelLinkInquiryOrderCreateDTO.class);
                String fybh = config.get("fybh");
                String zhmc = config.get("zhmc");
                cgscxjdDTO.setFybh(fybh);
                cgscxjdDTO.setZhmc(zhmc);
                cgscxjdDTO.setSupplier(config);
                return cgscxjdDTO;
            }).filter(distributeDto -> inquiryDistributeService.exists(distributeDto)).collect(Collectors.toList());
        }

        return configs.stream()
                .map(config -> {
                    HotelLinkInquiryOrderCreateDTO cgscxjdDTO = BeanMapper.map(dto, HotelLinkInquiryOrderCreateDTO.class);
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
    private List<Map<String, String>> getConfigByJkxjgysId(HotelLinkInquiryOrderCreateDTO dto) {
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


    