package cn.vetech.center.hotel.link.api;

import cn.vetech.center.hotel.link.api.member.dto.LinkHotelRegistrationDTO;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.api.register.dto.CompletionCompanyOnlineDTO;
import cn.vetech.center.hotel.link.api.register.dto.CreateCompanyOnlineDTO;
import cn.vetech.center.hotel.link.api.register.dto.ExternalCompanySearchDTO;
import cn.vetech.center.hotel.link.api.register.dto.ExternalMemberBindStatusDTO;
import cn.vetech.center.hotel.link.api.register.dto.ExternalMemberBindingCompanyDTO;
import cn.vetech.center.hotel.link.api.register.memberbinding.bindaccount.dto.HotelBindAccountDTO;
import cn.vetech.center.hotel.link.api.register.memberbinding.bindaccount.vo.HotelBindAccountVO;
import cn.vetech.center.hotel.link.api.register.memberbinding.savecorpcustinfo.dto.HotelSaveCorpCustInfoDTO;
import cn.vetech.center.hotel.link.api.register.memberbinding.savecorpcustinfo.vo.HotelSaveCorpCustInfoVO;
import cn.vetech.center.hotel.link.api.register.memberbinding.sendverifycode.dto.HotelSendVerifyCodeDTO;
import cn.vetech.center.hotel.link.api.register.memberbinding.sendverifycode.vo.HotelSendVerifyCodeVO;
import cn.vetech.center.hotel.link.api.register.memberbinding.unbindaccount.dto.HotelUnBindAccountDTO;
import cn.vetech.center.hotel.link.api.register.memberbinding.unbindaccount.vo.HotelUnBindAccountVO;
import cn.vetech.center.hotel.link.api.register.vo.CreateCompanyOnlineVO;
import cn.vetech.center.hotel.link.api.register.vo.ExternalCompanySearchVO;
import cn.vetech.center.hotel.link.api.register.vo.ExternalMemberBindStatusVO;
import cn.vetech.center.hotel.link.api.register.vo.ExternalMemberBindingCompanyVO;
import cn.vetech.center.hotel.link.api.register.vo.RegisterBaseVO;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.springcloud.api.RestResponse;
import cn.vetech.charge.cloud.springcloud.config.module.OpenFeignOperation;
import cn.vetech.charge.common.InterfaceCatalogEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业在线注册接口(华住2.0)
 *
 * @author pengyefei
 * @version 1.0
 * @date 2021/11/23 14:16
 */
@RequestMapping("/api/vehotellink/register")
public interface IHotelLinkRegisterService {
    /**
     * 创建企业卡
     *
     * @param dto 请求参数
     * @return CreateCompanyOnlineVO
     */
    @ApiOperation(value = "创建企业卡")
    @PostMapping(value = "/create")
    @OpenFeignOperation(value = "create", title = "创建企业卡", functionRemark = "创建企业卡", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "创建企业卡")
    RestResponse<RegisterBaseVO<CreateCompanyOnlineVO>> create(@RequestBody CreateCompanyOnlineDTO dto) throws SystemException;

    /**
     * 企业信息补全
     *
     * @param dto dto
     * @return CompletionCompanyOnlineVO
     */
    @ApiOperation(value = "企业信息补全")
    @PostMapping(value = "/completion")
    @OpenFeignOperation(value = "completion", title = "企业信息补全", functionRemark = "企业信息补全", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "企业信息补全")
    RestResponse<RegisterBaseVO> completion(@RequestBody CompletionCompanyOnlineDTO dto) throws SystemException;

    /**
     * 企业信息查询
     *
     * @param dto dto
     * @return ExternalCompanySearchVO
     */
    @ApiOperation(value = "企业信息查询")
    @PostMapping(value = "/companySearch")
    @OpenFeignOperation(value = "companySearch", title = "企业信息查询", functionRemark = "企业信息查询", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "企业信息查询")
    RestResponse<RegisterBaseVO<ExternalCompanySearchVO>> companySearch(@RequestBody ExternalCompanySearchDTO dto) throws SystemException;

    /**
     * 员工注册下挂
     *
     * @param dto dto
     * @return ExternalMemberBindingCompanyVO
     */
    @ApiOperation(value = "员工注册下挂")
    @PostMapping(value = "/memberBinding")
    @OpenFeignOperation(value = "memberBinding", title = "员工注册下挂", functionRemark = "员工注册下挂", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "员工注册下挂")
    RestResponse<RegisterBaseVO<ExternalMemberBindingCompanyVO>> memberBinding(@RequestBody ExternalMemberBindingCompanyDTO dto) throws SystemException;

    /**
     * 查询会员下挂状态
     *
     * @param dto dto
     * @return ExternalMemberBindStatusVO
     */
    @ApiOperation(value = "查询会员下挂状态")
    @PostMapping(value = "/memberBindingStatus")
    @OpenFeignOperation(value = "memberBindingStatus", title = "查询会员下挂状态", functionRemark = "查询会员下挂状态", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "查询会员下挂状态")
    RestResponse<RegisterBaseVO<ExternalMemberBindStatusVO>> memberBindingStatus(@RequestBody ExternalMemberBindStatusDTO dto) throws SystemException;

    /**
     * 查询员工是否开卡接口
     *
     * @param dto dto
     * @return RestResponse
     */
    @ApiOperation(value = "查询员工是否开卡接口")
    @PostMapping(value = "/registration")
    @OpenFeignOperation(value = "registration", title = "查询员工是否开卡接口", functionRemark = "查询员工是否开卡接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "查询员工是否开卡接口")
    RestResponse<LinkHotelRegistrationVO> registration(@RequestBody LinkHotelRegistrationDTO dto) throws SystemException;

    /**
     * 人事信息更新接口
     *
     * @param dto dto
     * @return RestResponse
     */
    @ApiOperation(value = "人事信息更新接口")
    @PostMapping(value = "/saveCorpCustInfo")
    @OpenFeignOperation(value = "saveCorpCustInfo", title = "人事信息更新接口", functionRemark = "人事信息更新接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "人事信息更新接口")
    RestResponse<HotelSaveCorpCustInfoVO> saveCorpCustInfo(@RequestBody HotelSaveCorpCustInfoDTO dto) throws SystemException;

    /**
     * 发送短信接口
     *
     * @param dto dto
     * @return RestResponse
     */
    @ApiOperation(value = "发送短信接口")
    @PostMapping(value = "/sendVerifyCode")
    @OpenFeignOperation(value = "sendVerifyCode", title = "发送短信接口", functionRemark = "发送短信接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "发送短信接口")
    RestResponse<HotelSendVerifyCodeVO> sendVerifyCode(@RequestBody HotelSendVerifyCodeDTO dto) throws SystemException;

    /**
     * 绑定个人账户接口
     *
     * @param dto dto
     * @return RestResponse
     */
    @ApiOperation(value = "绑定个人账户接口")
    @PostMapping(value = "/bindAccount")
    @OpenFeignOperation(value = "bindAccount", title = "绑定个人账户接口", functionRemark = "绑定个人账户接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "绑定个人账户接口")
    RestResponse<HotelBindAccountVO> bindAccount(@RequestBody HotelBindAccountDTO dto) throws SystemException;

    /**
     * 解除绑定个人账户接口
     *
     * @param dto dto
     * @return RestResponse
     */
    @ApiOperation(value = "解除绑定个人账户接口")
    @PostMapping(value = "/unBindAccount")
    @OpenFeignOperation(value = "unBindAccount", title = "解除绑定个人账户接口", functionRemark = "解除绑定个人账户接口", catalogId = InterfaceCatalogEnum.HOTEL_QT, catalog1 = "解除绑定个人账户接口")
    RestResponse<HotelUnBindAccountVO> unBindAccount(@RequestBody HotelUnBindAccountDTO dto) throws SystemException;

}