package cn.vetech.center.hotel.link.api;


import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelAgreementQueryDTO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelAgreementQueryVO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelGrupReMarkAgreementDTO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelLinkDtxyDTO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelLinkDtxyVO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.HotelMarkAgreementVO;
import cn.vetech.center.hotel.link.api.hoteldtxyjd.MarkAgreementDTO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vetech
 */
@RequestMapping("/api/vehotellink/xyjd")
public interface IHotelLinkTbXyjdService {

    /**
     * 同步协议酒店
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "同步协议酒店")
    @PostMapping(value = "/tbxyjd")
    @OpenFeignOperation(value = "tbxyjd", title = "同步协议酒店", functionRemark = "同步协议酒店", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "同步协议酒店")
    RestResponse<HotelLinkDtxyVO> tbxyjd(@RequestBody HotelLinkDtxyDTO dto) throws SystemException;

    /**
     * 同步协议酒店
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "标记协议酒店")
    @PostMapping(value = "/markAgreementHotel")
    @OpenFeignOperation(value = "markAgreementHotel", title = "标记协议酒店", functionRemark = "标记协议酒店", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "标记协议酒店")
    RestResponse<HotelMarkAgreementVO> markAgreementHotel(@RequestBody MarkAgreementDTO dto) throws SystemException;


    /**
     * 同步协议酒店
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "重置集团协议")
    @PostMapping(value = "/hotelGroupRemark")
    @OpenFeignOperation(value = "hotelGroupRemark", title = "重置集团协议", functionRemark = "重置集团协议", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "重置集团协议")
    RestResponse<HotelLinkDtxyVO> hotelGroupRemark(@RequestBody HotelGrupReMarkAgreementDTO dto) throws SystemException;

    /**
     * 查询协议酒店
     *
     * @param dto 入参
     * @return 回参
     */
    @ApiOperation(value = "查询协议酒店")
    @PostMapping(value = "/agreementHotelQuery")
    @OpenFeignOperation(value = "agreementHotelQuery", title = "查询协议酒店", functionRemark = "查询协议酒店", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "查询协议酒店")
    RestResponse<HotelAgreementQueryVO> agreementHotelQuery(@RequestBody HotelAgreementQueryDTO dto) throws SystemException;
}