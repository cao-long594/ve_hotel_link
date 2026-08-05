package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.conferencehotel.list.dto.ConferenceHotelListDTO;
import cn.vetech.center.hotel.link.api.conferencehotel.list.vo.ConferenceHotelListVO;
import cn.vetech.center.hotel.link.api.hotelgetcdsdata.HotelLinkGetCdsDataDTO;
import cn.vetech.center.hotel.link.api.hotelgetcdsdata.HotelLinkGetCdsDataVO;
import cn.vetech.center.hotel.link.api.hotelgetchineseinfo.HotelGetChineseInfoDTO;
import cn.vetech.center.hotel.link.api.hotelgetchineseinfo.HotelGetChineseInfoVO;
import cn.vetech.center.hotel.link.api.hotelgetcitycircle.HotelLinkGetCityCircleDTO;
import cn.vetech.center.hotel.link.api.hotelgetcitycircle.HotelLinkGetCityCircleVO;
import cn.vetech.center.hotel.link.api.hotelgetjdfx.HotelLinkGetJdfxDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdfx.HotelLinkGetJdfxVO;
import cn.vetech.center.hotel.link.api.hotelgetjdgjhxx.HotelLinkGetJdgjhxxDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdgjhxx.HotelLinkGetJdgjhxxVO;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.HotelLinkGetJdlbDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdlb.HotelLinkGetJdlbVO;
import cn.vetech.center.hotel.link.api.hotelgetjdmc.HotelLinkGetJdmcDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdmc.HotelLinkGetJdmcVO;
import cn.vetech.center.hotel.link.api.hotelgetjdsjzd.HotelLinkGetJdsjzdDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdsjzd.HotelLinkGetJdsjzdVO;
import cn.vetech.center.hotel.link.api.hotelgetjdtp.HotelLinkGetJdtpDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdtp.HotelLinkGetJdtpVO;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.HotelLinkGetJdxxxxDTO;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.HotelLinkGetJdxxxxVO;
import cn.vetech.center.hotel.link.api.hotelgetjdxxxx.IHotelLinkGetJdxxxxVO;
import cn.vetech.center.hotel.link.api.hotelgetpbjgjh.HotelLinkGetShieldRatePlanVO;
import cn.vetech.center.hotel.link.api.hotelgetpbjgjh.HotelLinkGetShieldRateplanDTO;
import cn.vetech.center.hotel.link.api.hotelgetpriceinfo.GetPriceInfoDTO;
import cn.vetech.center.hotel.link.api.hotelgetpriceinfo.GetPriceInfoVO;
import cn.vetech.center.hotel.link.api.hotelgetrmcs.HotelLinkGetJdrmcsDTO;
import cn.vetech.center.hotel.link.api.hotelgetrmcs.HotelLinkGetJdrmcsVO;
import cn.vetech.center.hotel.link.api.hotelgetstandardtravelexpense.HotelGetStandardTravelExpenseDTO;
import cn.vetech.center.hotel.link.api.hotelgetstandardtravelexpense.HotelGetStandardTravelExpenseVO;
import cn.vetech.center.hotel.link.api.hotelhyslist.HotelGetHysListDTO;
import cn.vetech.center.hotel.link.api.hotelhyslist.HotelGetHysListVO;
import cn.vetech.center.hotel.link.api.hotelmap.vo.HotelMapDistributionGetVO;
import cn.vetech.center.hotel.link.api.hotelpriceclendar.dto.HotelPriceCalendarDTO;
import cn.vetech.center.hotel.link.api.hotelpriceclendar.vo.HotelPriceCalendarVO;
import cn.vetech.center.hotel.link.api.ihotelgetjdlb.IHotelLinkGetJdlbDTO;
import cn.vetech.center.hotel.link.api.ihotelgetjdlb.IHotelLinkGetJdlbVO;
import cn.vetech.center.hotel.link.api.ihotelmap.vo.IHotelMapDistributionGetVO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.RealTimePriceAsyncDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.dto.RealTimePriceDTO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.RealTimePriceAsyncVO;
import cn.vetech.center.hotel.link.api.realtimeprice.vo.RealTimePriceVO;
import cn.vetech.center.hotel.link.api.roommergedata.dto.RoomMergeDataDTO;
import cn.vetech.center.hotel.link.api.roommergedata.vo.RoomMergeDataVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/vehotellink/search")
public interface IHotelLinkSearchService {

    /**
     * 获取酒店地图分布信息，对应CPS接口 Hotel_GetMapDistribution
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店地图分布信息，对应CPS接口 Hotel_GetMapDistribution")
    @PostMapping(value = "/getHotelMapDistribution")
    @OpenFeignOperation(value = "getHotelMapDistribution", title = "获取酒店地图分布信息", functionRemark = "获取酒店地图分布信息", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "获取酒店地图分布信息")
    RestResponse<HotelMapDistributionGetVO> getHotelMapDistribution(@RequestBody HotelLinkGetJdlbDTO dto) throws SystemException;

    /**
     * 获取酒店列表，对应CPS接口 Hotel_Getjdlb
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店列表，对应CPS接口 Hotel_Getjdlb")
    @PostMapping(value = "/hotelGetjdlb")
    @OpenFeignOperation(value = "hotelGetjdlb", title = "获取酒店列表", functionRemark = "获取酒店列表", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDLB, catalog1 = "获取酒店列表")
    RestResponse<HotelLinkGetJdlbVO> hotelGetjdlb(@RequestBody HotelLinkGetJdlbDTO dto) throws SystemException;

    /**
     * 异步获取酒店实时报价
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "异步获取酒店实时报价")
    @PostMapping(value = "/hotelGetRealTimePriceAsync")
    @OpenFeignOperation(value = "hotelGetRealTimePriceAsync", title = "异步获取酒店实时报价", functionRemark = "异步获取酒店实时报价", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "异步获取酒店实时报价")
    RestResponse<RealTimePriceAsyncVO> hotelGetRealTimePriceAsync(@RequestBody RealTimePriceAsyncDTO dto) throws SystemException;

    /**
     * 获取酒店实时报价
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店实时报价 hotelGetRealTimePrice")
    @PostMapping(value = "/hotelGetRealTimePrice")
    @OpenFeignOperation(value = "hotelGetRealTimePrice", title = "获取酒店实时报价", functionRemark = "获取酒店实时报价", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "获取酒店实时报价")
    RestResponse<RealTimePriceVO> hotelGetRealTimePrice(@RequestBody RealTimePriceDTO dto) throws SystemException;

    /**
     * 获取酒店价格日历
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店价格日历")
    @PostMapping(value = "/getHotelPriceCalendar")
    @OpenFeignOperation(value = "getHotelPriceCalendar", title = "获取酒店价格日历", functionRemark = "获取酒店价格日历", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "获取酒店价格日历")
    RestResponse<HotelPriceCalendarVO> getHotelPriceCalendar(@RequestBody HotelPriceCalendarDTO dto) throws SystemException;

    /**
     * 获取酒店数据字典信息，对应CPS接口 Hotel_Getjdsjzd
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店数据字典信息，对应CPS接口 Hotel_Getjdsjzd")
    @PostMapping(value = "/hotelGetjdsjzd")
    @OpenFeignOperation(value = "hotelGetjdsjzd", title = "获取酒店数据字典信息", functionRemark = "获取酒店数据字典信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店数据字典信息")
    RestResponse<HotelLinkGetJdsjzdVO> hotelGetjdsjzd(@RequestBody HotelLinkGetJdsjzdDTO dto) throws SystemException;

    /**
     * 获取酒店详情信息，对应CPS接口 Hotel_Getjdxxxx
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店详情信息，对应CPS接口 Hotel_Getjdxxxx")
    @PostMapping(value = "/hotelGetjdxxxx")
    @OpenFeignOperation(value = "hotelGetjdxxxx", title = "获取酒店详情信息", functionRemark = "获取酒店详情信息", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDXQ, catalog1 = "获取酒店详情信息")
    RestResponse<HotelLinkGetJdxxxxVO> hotelGetjdxxxx(@RequestBody HotelLinkGetJdxxxxDTO dto) throws SystemException;

    /**
     * 获取房型信息，对应CPS接口 Hotel_Getjdfx
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取房型信息，对应CPS接口 Hotel_Getjdfx_new")
    @PostMapping(value = "/hotelGetjdfx")
    @OpenFeignOperation(value = "hotelGetjdfx", title = "获取房型信息", functionRemark = "获取房型信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取房型信息")
    RestResponse<HotelLinkGetJdfxVO> hotelGetjdfx(@RequestBody HotelLinkGetJdfxDTO dto) throws SystemException;

    /**
     * 获取单个酒店房型合并相关数据
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取单个酒店房型合并相关数据，对应CDS接口 Hotel_RoomMergeDataSearch")
    @PostMapping(value = "/hotelRoomMergeData")
    @OpenFeignOperation(value = "hotelRoomMergeData", title = "获取单个酒店房型合并相关数据", functionRemark = "获取单个酒店房型合并相关数据", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取单个酒店房型合并相关数据")
    RestResponse<RoomMergeDataVO> hotelRoomMergeData(@RequestBody RoomMergeDataDTO dto) throws SystemException;

    /**
     * 获取酒店所有的图片，对应CPS接口 Hotel_Getjdtp
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店所有的图片，对应CPS接口 Hotel_Getjdtp")
    @PostMapping(value = "/hotelGetjdtp")
    @OpenFeignOperation(value = "hotelGetjdtp", title = "获取酒店所有的图片", functionRemark = "获取酒店所有的图片", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店所有的图片")
    RestResponse<HotelLinkGetJdtpVO> hotelGetjdtp(@RequestBody HotelLinkGetJdtpDTO dto) throws SystemException;

    /**
     * 获取酒店热门城市，对应CPS接口 Hotel_Getrmcs
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店热门城市，对应CPS接口 Hotel_Getrmcs")
    @PostMapping(value = "/hotelGetrmcs")
    @OpenFeignOperation(value = "hotelGetrmcs", title = "获取酒店热门城市", functionRemark = "获取酒店热门城市", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店热门城市")
    RestResponse<HotelLinkGetJdrmcsVO> hotelGetrmcs(@RequestBody HotelLinkGetJdrmcsDTO dto) throws SystemException;

    /**
     * 获取酒店品牌
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店CDS数据，例如品牌等")
    @PostMapping(value = "/hotelGetpp")
    @OpenFeignOperation(value = "hotelGetpp", title = "获取酒店CDS数据", functionRemark = "获取酒店CDS数据", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店CDS数据")
    RestResponse<HotelLinkGetCdsDataVO> hotelGetpp(@RequestBody HotelLinkGetCdsDataDTO dto) throws SystemException;

    /**
     * 获取酒店名称
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店名称")
    @PostMapping(value = "/hotelGetjdmc")
    @OpenFeignOperation(value = "hotelGetjdmc", title = "获取酒店名称", functionRemark = "获取酒店名称", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店名称")
    RestResponse<HotelLinkGetJdmcVO> hotelGetjdmc(@RequestBody HotelLinkGetJdmcDTO dto) throws SystemException;

    /**
     * 获取酒店商圈等信息
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取酒店商圈等信息")
    @PostMapping(value = "/hotelGetcityCircle")
    @OpenFeignOperation(value = "hotelGetcityCircle", title = "获取酒店商圈等信息", functionRemark = "获取酒店商圈等信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店商圈等信息")
    RestResponse<HotelLinkGetCityCircleVO> hotelgetcitycircle(@RequestBody HotelLinkGetCityCircleDTO dto) throws SystemException;

    /**
     * 获取国际酒店地图分布信息，对应CPS接口 iHotel_GetMapDistribution
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取国际酒店地图分布信息，对应CPS接口 iHotel_GetMapDistribution")
    @PostMapping(value = "/getIHotelMapDistribution")
    @OpenFeignOperation(value = "getIHotelMapDistribution", title = "获取国际酒店地图分布信息", functionRemark = "获取国际酒店地图分布信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取国际酒店地图分布信息")
    RestResponse<IHotelMapDistributionGetVO> getIHotelMapDistribution(@RequestBody IHotelLinkGetJdlbDTO dto) throws SystemException;

    /**
     * 获取酒店列表，对应CPS接口 Hotel_Getjdlb
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取国际酒店列表，对应CPS接口 ihotel_cds_search")
    @PostMapping(value = "/ihotelGetjdlb")
    @OpenFeignOperation(value = "ihotelGetjdlb", title = "获取国际酒店列表", functionRemark = "获取国际酒店列表", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDLB, catalog1 = "获取国际酒店列表")
    RestResponse<IHotelLinkGetJdlbVO> ihotelGetjdlb(@RequestBody IHotelLinkGetJdlbDTO dto) throws SystemException;

    /**
     * 获取酒店详情信息，对应CPS接口 Hotel_Getjdxxxx
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "获取国际酒店详情信息，对应CPS接口 ihotel_cds_detail")
    @PostMapping(value = "/ihotelGetjdxxxx")
    @OpenFeignOperation(value = "ihotelGetjdxxxx", title = "获取国际酒店详情信息", functionRemark = "获取国际酒店详情信息", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDXQ, catalog1 = "获取国际酒店详情信息")
    RestResponse<IHotelLinkGetJdxxxxVO> ihotelGetjdxxxx(@RequestBody HotelLinkGetJdxxxxDTO dto) throws SystemException;

    /**
     * 获取酒店国际化信息
     *
     * @param dto dto
     * @return response
     * @throws SystemException exception
     */
    @ApiOperation(value = "获取酒店国际化信息")
    @PostMapping(value = "/hotelGetjdgjhxx")
    @OpenFeignOperation(value = "hotelGetjdgjhxx", title = "获取酒店国际化信息", functionRemark = "获取酒店国际化信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "获取酒店国际化信息")
    RestResponse<HotelLinkGetJdgjhxxVO> hotelGetJdgjhxx(@RequestBody HotelLinkGetJdgjhxxDTO dto) throws SystemException;

    /**
     * 获取酒店屏蔽价格内容
     *
     * @param dto 请求对象性
     * @return response
     * @throws SystemException exec
     */
    @ApiOperation(value = "获取酒店屏蔽价格")
    @PostMapping(value = "/hotelGetShieldRateplan")
    @OpenFeignOperation(value = "hotelGetShieldRateplan", title = "获取酒店屏蔽价格", functionRemark = "获取酒店屏蔽价格", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDBJ, catalog1 = "获取酒店屏蔽价格")
    RestResponse<HotelLinkGetShieldRatePlanVO> hotelGetShieldRateplan(@RequestBody HotelLinkGetShieldRateplanDTO dto) throws SystemException;

    /**
     * 查询酒店缓存价格计
     *
     * @param dto dto
     * @return cn.vetech.charge.cloud.springcloud.api.RestResponse<cn.vetech.center.hotel.link.api.hotelgetpriceinfo.GetPriceInfoVO>
     */
    @ApiOperation(value = "查询酒店缓存价格")
    @PostMapping(value = "/hotelGetPriceInfo")
    @OpenFeignOperation(value = "hotelGetPriceInfo", title = "查询酒店缓存价格", functionRemark = "查询酒店缓存价格", catalogId = InterfaceCatalogEnum.HOTEL_JDYD_JDBJ, catalog1 = "查询酒店缓存价格")
    RestResponse<GetPriceInfoVO> hotelGetPriceInfo(@RequestBody GetPriceInfoDTO dto) throws SystemException;

    /**
     * 查询查询酒店中文信息
     *
     * @param dto dto
     * @return response
     * @throws SystemException ex
     */
    @ApiOperation(value = "查询查询酒店中文信息")
    @PostMapping(value = "/hotelGetChineseInfo")
    @OpenFeignOperation(value = "hotelGetChineseInfo", title = "查询查询酒店中文信息", functionRemark = "查询查询酒店中文信息", catalogId = InterfaceCatalogEnum.HOTEL_QT_JCSJ, catalog1 = "查询查询酒店中文信息")
    RestResponse<HotelGetChineseInfoVO> hotelGetChineseInfo(@RequestBody HotelGetChineseInfoDTO dto) throws SystemException;

    /**
     * 查询会议酒店列表
     *
     * @param dto 请求对象
     * @return response
     * @throws SystemException exec
     */
    @ApiOperation(value = "查询会议酒店列表")
    @PostMapping(value = "/hotelConferenceHotelList")
    @OpenFeignOperation(value = "hotelConferenceHotelList", title = "查询会议酒店列表", functionRemark = "查询会议酒店列表", catalogId = InterfaceCatalogEnum.HOTEL_QT_HYS, catalog1 = "查询会议酒店列表")
    RestResponse<ConferenceHotelListVO> conferenceHotelList(@RequestBody ConferenceHotelListDTO dto) throws SystemException;

    /**
     * 获取酒店会议室列表
     *
     * @param dto 请求对象
     * @return response
     * @throws SystemException exec
     */
    @ApiOperation(value = "获取酒店会议室列表")
    @PostMapping(value = "/hotelGetHysList")
    @OpenFeignOperation(value = "hotelGetHysList", title = "获取酒店会议室列表", functionRemark = "获取酒店会议室列表", catalogId = InterfaceCatalogEnum.HOTEL_QT_HYS, catalog1 = "获取酒店会议室列表")
    RestResponse<HotelGetHysListVO> hotelGetHysList(@RequestBody HotelGetHysListDTO dto) throws SystemException;

    /**
     * 查询酒店动态差标
     *
     * @param dto 请求对象
     * @return response
     * @throws SystemException exec
     */
    @ApiOperation(value = "查询酒店动态差标")
    @PostMapping(value = "/hotelGetStandardTravelExpense")
    @OpenFeignOperation(value = "hotelGetStandardTravelExpense", title = "查询酒店动态差标", functionRemark = "查询酒店动态差标", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "查询酒店动态差标")
    RestResponse<HotelGetStandardTravelExpenseVO> hotelGetStandardTravelExpense(@RequestBody HotelGetStandardTravelExpenseDTO dto) throws SystemException;
}