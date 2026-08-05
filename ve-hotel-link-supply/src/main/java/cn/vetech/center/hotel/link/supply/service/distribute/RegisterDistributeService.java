package cn.vetech.center.hotel.link.supply.service.distribute;

import cn.vetech.center.hotel.link.api.LinkHotelDTO;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.enums.PtEnum;
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
import cn.vetech.center.hotel.link.supply.base.IHotelLinkRegisterSupplyService;
import cn.vetech.center.hotel.link.supply.base.exception.HotelConfigExcetion;
import cn.vetech.center.hotel.link.supply.base.exception.SupplyBusinessException;
import cn.vetech.center.hotel.link.supply.base.exception.SupplyServiceException;
import cn.vetech.center.hotel.link.supply.service.distribute.config.HotelConfigDistributeService;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.charge.cloud.exception.SystemException;
import cn.vetech.charge.cloud.modules.utils.collection.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 企业在线注册服务
 *
 * @author pengyefei
 * @version 1.0
 */
@Service
public class RegisterDistributeService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterDistributeService.class);


    /**
     * 注入酒店接口实现
     */
    @Autowired(required = false)
    private Map<String, IHotelLinkRegisterSupplyService> registerSupplyServiceMap;

    /**
     * 配置
     */
    @Autowired
    private HotelConfigDistributeService configService;

    /**
     * 创建企业卡
     *
     * @param dto dto
     */
    public RegisterBaseVO<CreateCompanyOnlineVO> create(CreateCompanyOnlineDTO dto) throws SystemException {
        setConfig(dto);
        return getService(dto).create(dto);
    }

    /**
     * 企业信息补全
     *
     * @param dto dto
     */
    public RegisterBaseVO completion(CompletionCompanyOnlineDTO dto) throws SystemException {
        setConfig(dto);
        return getService(dto).completion(dto);
    }

     /**
     * 企业信息查询
     *
     * @param dto dto
     */
    public RegisterBaseVO<ExternalCompanySearchVO> companySearch(ExternalCompanySearchDTO dto) throws SystemException {
        setConfig(dto);
        return getService(dto).companySearch(dto);
    }

    /**
     * 员工注册下挂
     *
     * @param dto dto
     */
    public RegisterBaseVO<ExternalMemberBindingCompanyVO> memberBinding(ExternalMemberBindingCompanyDTO dto) throws SystemException {
        setConfig(dto);
        return getService(dto).memberBinding(dto);
    }

    /**
     * 查询会员下挂状态
     *
     * @param dto dto
     */
    public RegisterBaseVO<ExternalMemberBindStatusVO> memberBindingStatus(ExternalMemberBindStatusDTO dto) throws SystemException {
        setConfig(dto);
        return getService(dto).memberBindingStatus(dto);
    }
    /**
     * 设置配置
     *
     * @param dto 酒店基础vo
     * @throws HotelConfigExcetion     酒店配置异常
     * @throws SupplyBusinessException 酒店配置异常
     */
    private void setConfig(LinkHotelDTO dto) throws HotelConfigExcetion, SupplyBusinessException {
        String pt = dto.getPt();
        String fybh = dto.getFybh();
        String zhmc = dto.getZhmc();

        if (!PtEnum.isExits(pt)) {
            throw new SupplyBusinessException("平台编号不存在");
        }
        if (!FyEnum.isExistByFybh(fybh)) {
            throw new SupplyBusinessException("房源商编号不存在");
        }
        if (StringUtils.isBlank(zhmc)) {
            throw new SupplyBusinessException("账号名称为空");
        }
        //手动处理
        Map<String, String> config = configService.getConfig(dto);
        if (MapUtil.isEmpty(config)) {
            LOGGER.error("获取供应商配置失败，供应商编号:[{}]", fybh);
            throw new HotelConfigExcetion(fybh, null, null);
        }
        dto.setSupplier(config);
    }

  /**
     * 获取服务名称
     *
     * @param dto dto
     * @return cn.vetech.center.hotel.link.supply.base.IHotelLinkRegisterSupplyService
     */
    public IHotelLinkRegisterSupplyService getService(LinkHotelDTO dto) {
        String fybh = StringUtils.defaultString(dto.getFybh(), dto.getSupplier().get("fybh"));
        String fyen = dto.getSupplier().get("fyen");
        String tcext = dto.getSupplier().get("tcext");
        if (StringUtils.isNotBlank(tcext)) {
            fyen = "tcext";
        }
        if (StringUtils.isBlank(fyen)) {
            LOGGER.error("fyen不可为空【{}】", fybh);
            throw new SupplyServiceException(fybh, null);
        }
        String serviceName = fyen.concat(IHotelLinkRegisterSupplyService.NAME);
        IHotelLinkRegisterSupplyService supplyService = registerSupplyServiceMap.get(serviceName);
        if (supplyService == null) {
            LOGGER.error("未找到供应商对应实现，供应商编号:[{}],供应商bean名称:[{}]", fybh, serviceName);
            throw new SupplyServiceException(fybh, serviceName);
        }
        return supplyService;
    }

    /**
     * 查询员工是否开卡接口
     *
     * @param dto dto
     */
    public LinkHotelRegistrationVO registration(LinkHotelRegistrationDTO dto) throws SystemException {
        return getService(dto).registration(dto);
    }
     /**
     * 人事信息更新接口
     *
     * @param dto dto
     */
    public HotelSaveCorpCustInfoVO saveCorpCustInfo(HotelSaveCorpCustInfoDTO dto) throws SystemException {
        convertConfig(dto);
        return getService(dto).saveCorpCustInfo(dto);
    }

    /**
     * 发送短信接口
     *
     * @param dto dto
     */
    public HotelSendVerifyCodeVO sendVerifyCode(HotelSendVerifyCodeDTO dto) throws SystemException {
        return getService(dto).sendVerifyCode(dto);
    }

    /**
     * 绑定个人账户接口
     *
     * @param dto dto
     */
    public HotelBindAccountVO bindAccount(HotelBindAccountDTO dto) throws SystemException {
        return getService(dto).bindAccount(dto);
    }

    /**
     * 解除绑定个人账户接口
     *
     * @param dto dto
     */
    public HotelUnBindAccountVO unBindAccount(HotelUnBindAccountDTO dto) throws SystemException {
        return getService(dto).unBindAccount(dto);
    }
     /**
     * 处理配置参数
     *
     * @param dto dto
     */
    private void convertConfig(LinkHotelDTO dto) {
        Map<String, String> supplier = dto.getSupplier();
        // 配置参数获取方式，1：优化取link供应商配置参数
        String pzcshqfs = supplier.get("pzcshqfs");
        if ("1".equals(pzcshqfs)) {
            try {
                // 处理携程商旅配置参数
                Map<String, String> configMap = configService.getConfig(dto, FyEnum.XCSL.getGyspt());
                String subAccountName = configMap.get("subAccountName");
                if (StringUtils.isNotBlank(subAccountName)) {
                    supplier.put("subAccountName", subAccountName);
                }
            } catch (Exception e) {
                LOGGER.error("获取携程商旅配置参数异常，请求参数：【{}】", JacksonUtils.toJsonWithNonEmpty(dto), e);
            }
        }
    }
}
