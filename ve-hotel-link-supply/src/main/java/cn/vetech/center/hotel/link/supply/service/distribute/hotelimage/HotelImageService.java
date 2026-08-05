package cn.vetech.center.hotel.link.supply.service.distribute.hotelimage;

import cn.vetech.center.hotel.link.api.LinkHotelVO;
import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.hotelimage.dto.HotelImageConvertDTO;
import cn.vetech.center.hotel.link.api.hotelimage.dto.HotelImageInfo;
import cn.vetech.center.hotel.link.api.hotelimage.vo.HotelImageConvertVO;
import cn.vetech.center.hotel.link.api.hotelimage.vo.ImageInfo;
import cn.vetech.center.hotel.link.api.ratesearch.dto.Mapper;
import cn.vetech.center.hotel.link.enums.HotelFaceCodeEnum;
import cn.vetech.center.hotel.link.supply.base.hotel.dto.HotelDTO;
import cn.vetech.center.hotel.link.supply.base.hotel.vo.HotelVO;
import cn.vetech.center.hotel.link.supply.service.distribute.HotelImageDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.supply.service.distribute.hotel.HotelDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.log.bean.CommonLogBean;
import cn.vetech.center.hotel.log.util.CommonLogContext;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author chengwanshan
 * @since 2023/10/7 9:33
 */
@Service
public class HotelImageService {
    /**
     * 日志
     */
private final Logger LOGGER = LoggerFactory.getLogger(HotelImageService.class);
    /**
     * 配置服务
     */
    @Autowired
    private HotelConfigDistributeService configService;
    /**
     * distribute 服务
     */
    @Autowired
    private HotelImageDistributeService hotelImageDistributeService;
    /**
     *
     */
    @Autowired
    private HotelDistributeService hotelService;
    /**
     *
     */
    @Autowired
    private HotelImageAsyncService hotelImageAsyncService;

    /**
     * @param dto dto
     * @return HotelImageConvertVO
     */
    public HotelImageConvertVO hotelImageConvert(HotelImageConvertDTO dto) {
        CommonLogBean commonLogBean = CommonLogContext.get();
        CommLog commLog = null;
        if (commonLogBean != null) {
            commLog = commonLogBean.getCommonLog();
        }
        if (commLog == null) {
            commLog = new CommLog();
        }
         HotelImageConvertVO vo = new HotelImageConvertVO();
        if (CollectionUtils.isEmpty(dto.getHotelImageInfoList())) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("请求参数图片信息为空");
            return vo;
        }
        // 处理酒店映射
        convertMapper(dto);
        List<Map<String, String>> configs = null;
        // 获取供应商配置
        try {
            configs = configService.getConfigs(dto);
        } catch (Exception e) {
            LOGGER.error("获取供应商配置异常", e);
        }
        if (CollectionUtils.isEmpty(configs)) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到供应商配置信息");
            return vo;
        }
        // 对接了酒店图片转换的供应商
        List<Map<String, String>> configMapList = configs.stream().filter(c -> StringUtils.isNotBlank(c.get("imageUrl"))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configMapList)) {
            LOGGER.warn("未获取到可转换酒店图片的供应商配置信息");
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("未获取到可转换酒店图片的供应商配置信息");
            return vo;
        }
        // 根据供应商配置 生成各个供应商请求
        List<HotelImageConvertDTO> dtos = getDtos(dto, configMapList);
        commLog.add("获取的配置:" + JacksonUtils.toJsonWithNonEmpty(dtos));
        if (CollectionUtils.isEmpty(dtos)) {
            vo.setStatus(LinkHotelVO.FAIL);
            vo.setErrorMsg("需要查询的供应商为空");
            return vo;
        }
         List<HotelImageConvertVO> voList = new ArrayList<>();
        dtos.stream().map(hotelImageConvertDTO ->
                        // 查询单个供应商
                        hotelImageAsyncService.hotelImageAsync(hotelImageConvertDTO))
                .collect(Collectors.toList())
                .forEach(f -> {
                    try {
                        voList.add(f.get());
                    } catch (InterruptedException e) {
                        LOGGER.error("查询单个房源异常", e);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        LOGGER.error("查询单个房源异常", e);
                    }
                });

        List<ImageInfo> imageInfoList = voList.stream()
                .filter(v -> CollectionUtils.isNotEmpty(v.getImageList()))
                .flatMap(v -> v.getImageList().stream().filter(imageInfo -> StringUtils.isNotBlank(imageInfo.getUrlBase64())))
                .collect(Collectors.toList());
        vo.setImageList(imageInfoList);
        return vo;
    }

 /**
     * @param dto dto
     */
    private void convertMapper(HotelImageConvertDTO dto) {
        if (StringUtils.isBlank(dto.getLocalHotelId())) {
            return;
        }
        try {
            HotelDTO detailDto = BeanMapper.map(dto, HotelDTO.class);
            // 查询酒店祥
            HotelVO hotel = hotelService.getHotel(detailDto);
            if (hotel == null) {
                return;
            }
            // 判断酒店映射
            List<Mapper> mappers = hotel.getMappers();
            if (CollectionUtils.isEmpty(mappers)) {
                return;
            }
            // 转换mapper
            convertAndSetMappers(dto, mappers);
        } catch (Exception e) {
            LOGGER.warn("获取酒店映射关系异常", e);
        }
    }

    /**
     * 转换并设置mapper，如果入参指定了房源商，这按照指定的进行查询
     * 如果房源编号里面有艺龙，则添加至同程的映射上
     *
     * @param dto     dto
     * @param mappers mappers
     */
    private static void convertAndSetMappers(HotelImageConvertDTO dto, List<Mapper> mappers) {
        dto.setMappers(mappers);
        if (StringUtils.isNotBlank(dto.getFybh()) && StringUtils.isNotBlank(dto.getHotelId())) {
            mappers.clear();
            Mapper mapper = new Mapper();
            mapper.setFybh(dto.getFybh());
            mapper.setHotelid(dto.getHotelId());
            mappers.add(mapper);
            dto.setMappers(mappers);
            return;
        }

         //如果供应商中 有艺龙 则将该映射也添加至艺龙中
        String elongFybh = HotelFaceCodeEnum.ELONG.getJkbh();
        Optional<Mapper> elongMapperOpt = mappers.stream().filter(mapper -> elongFybh.equals(mapper.getFybh())).findAny();
        if (elongMapperOpt.isPresent()) {
            Mapper elongMapper = elongMapperOpt.get();
            String tcFybh = HotelFaceCodeEnum.TC.getJkbh();
            //排除同程的
            List<Mapper> notContainsTcMapperList = mappers.stream().filter(mapper -> !StringUtils.equals(mapper.getFybh(), tcFybh)).collect(Collectors.toList());

            //拷贝艺龙的房源映射改成同程的 并放进去
            Mapper tcMapper = BeanMapper.map(elongMapper, Mapper.class);
            tcMapper.setFybh(tcFybh);
            notContainsTcMapperList.add(tcMapper);
            dto.setMappers(notContainsTcMapperList);
        }
    }

  /**
     * @param dto     dto
     * @param configs configs
     * @return List<HotelImageConvertDTO>
     */
    private List<HotelImageConvertDTO> getDtos(HotelImageConvertDTO dto, List<Map<String, String>> configs) {
        return configs.stream()
                .map(config -> {
                    HotelImageConvertDTO hotelImageConvertDTO = BeanMapper.map(dto, HotelImageConvertDTO.class);
                    String fybh = config.get("fybh");
                    String zhmc = config.get("zhmc");
                    hotelImageConvertDTO.setFybh(fybh);
                    hotelImageConvertDTO.setZhmc(zhmc);
                    hotelImageConvertDTO.setSupplier(config);
                    // 配置all，可转换所有url
                    if (!"all".equals(config.get("imageUrl"))) {
                        // 处理请求参数图片地址
                        List<HotelImageInfo> hotelImageInfos = convertHotelImageInfoList(hotelImageConvertDTO.getHotelImageInfoList(), config.get("imageUrl"));
                        if (CollectionUtils.isEmpty(hotelImageInfos)) {
                            return null;
                        }
                        hotelImageConvertDTO.setHotelImageInfoList(hotelImageInfos);
                    }
                    return hotelImageConvertDTO;
                })
                .filter(hotelImageConvertDTO -> Objects.nonNull(hotelImageConvertDTO) && hotelImageDistributeService.exists(hotelImageConvertDTO))
                .collect(Collectors.toList());
    }

    /**
     * @param hotelImageInfoList hotelImageInfoList
     * @param imageUrl           imageUrl
     * @return List<HotelImageInfo>
     */
    private List<HotelImageInfo> convertHotelImageInfoList(List<HotelImageInfo> hotelImageInfoList, String imageUrl) {
        if (CollectionUtils.isEmpty(hotelImageInfoList)) {
            return Collections.emptyList();
        }
      // 配置的图片前缀
        List<String> imageUrlList = Arrays.asList(imageUrl.split(SymbolConstant.COMMA));
        // 对图片地址进行分组
        List<HotelImageInfo> hotelImageInfoListNew = new ArrayList<>();
        for (HotelImageInfo hotelImageInfo : hotelImageInfoList) {
            boolean match = imageUrlList.stream().anyMatch(i -> StringUtils.contains(hotelImageInfo.getUrl(), i));
            if (match) {
                hotelImageInfoListNew.add(hotelImageInfo);
            }
        }
        return hotelImageInfoListNew;
    }
}
