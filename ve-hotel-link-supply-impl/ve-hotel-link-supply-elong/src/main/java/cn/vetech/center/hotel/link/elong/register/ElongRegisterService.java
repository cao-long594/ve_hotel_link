package cn.vetech.center.hotel.link.elong.register;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.api.member.dto.LinkHotelRegistrationDTO;
import cn.vetech.center.hotel.link.api.member.vo.LinkHotelRegistrationVO;
import cn.vetech.center.hotel.link.api.orderbook.dto.LinkHotelOrderBookDTO;
import cn.vetech.center.hotel.link.api.ratesearch.dto.LinkHotelRateSearchDTO;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.elong.common.ElongCipherUtil;
import cn.vetech.center.hotel.link.elong.common.ElongConfig;
import cn.vetech.center.hotel.link.elong.common.ElongHttp;
import cn.vetech.center.hotel.link.elong.register.request.ElongRegisterRequest;
import cn.vetech.center.hotel.link.elong.register.response.ElongRegisterResponse;
import cn.vetech.center.hotel.link.enums.CachePrefix;
import cn.vetech.center.hotel.link.supply.base.cache.HotelBaseDataCacheService;
import cn.vetech.center.hotel.link.supply.base.util.SupplierConfigUtils;
import cn.vetech.center.hotel.link.util.JacksonUtils;
import cn.vetech.center.hotel.link.util.ratesearch.RateSearchApiRes;
import cn.vetech.charge.cloud.modules.utils.collection.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author xiaotengyu
 * @since 2023-06-15 10:15
 */
@Service
public class ElongRegisterService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
    * 方法
     */
    private String method = "hotel.member.register";
    /**
     * http
     */
    private String http = "https";
    /**
     * 默认缓存过期时间
     */
    private int cacheEpTime = NumConstant.NUM_60 * NumConstant.NUM_60 * NumConstant.NUM_24 * NumConstant.NUM_10;

    /**
     * hotelBaseDataCacheService
     */
    @Autowired
    private HotelBaseDataCacheService hotelBaseDataCacheService;

    /**
     * 注册会员
     *
     * @param config 配置
     * @param mobile 手机号
     * @return 电话号码
     */
    private Optional<ElongRegisterResponse> register(ElongConfig config, String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return Optional.empty();
        }
        ElongRegisterRequest request = new ElongRegisterRequest();
        String encryptMobile = encryptMobile(mobile, config.getAppKey());
        request.setMobile(encryptMobile);
        request.setConfig(config);
        try {
            String responseStr = ElongHttp.sendInvoke(method, request.toJson(), config, http, 0L);
            ElongRegisterResponse elongRegisterResponse = JacksonUtils.parseNonEmpty(responseStr, ElongRegisterResponse.class);
            if (Objects.isNull(elongRegisterResponse)) {
                logger.info("艺龙会员价格式化为空：request:{};result:{}", request.toJson(), responseStr);
                return Optional.empty();
            }
            return Optional.of(elongRegisterResponse);
        } catch (Exception ex) {
            logger.error("注册艺龙会员:{}", request.toJson(), ex);
        }
        return Optional.empty();
    }
    /**
     * 查询报价获取openId
     *
     * @param dto    请求dto
     * @param config 配置
     * @return openid
     */
    public String getOpenId(ElongConfig config, LinkHotelRateSearchDTO dto) {
        //不开启查询会员价
        if (!StringUtils.equalsIgnoreCase(config.getSfkqhyj(), "1")) {
            return StringUtils.EMPTY;
        }
        //获取手机号
        String mobile = dto.getYdrPhoneNumber();
        if (StringUtils.isBlank(mobile)) {
            return StringUtils.EMPTY;
        }
        //判断费控是否控制适用会员价
        List<String> kqhyjFysList = dto.getKqhyjFysList();
        if (ListUtil.isNotEmpty(kqhyjFysList)) {
            boolean canHyj = kqhyjFysList.stream().anyMatch(fy -> StringUtils.equalsIgnoreCase(FyEnum.ELONG.getFybh(), fy));
            if (!canHyj) {
                return StringUtils.EMPTY;
            }
        }
        if (StringUtils.equalsIgnoreCase(config.getCacheOpenId(), "1")) {
            String cacheOpenId = getCacheOpenId(config, mobile);
            if (StringUtils.isNotBlank(cacheOpenId)) {
                logger.info("缓存中的openId:{}", cacheOpenId);
                return cacheOpenId;
            }
        }
        Optional<ElongRegisterResponse> register = register(config, mobile);
        String openId = register.map(r -> {
            if (Objects.nonNull(r.getResult()) && StringUtils.isNotBlank(r.getResult().getOpenId())) {
                return r.getResult().getOpenId();
            }
            return StringUtils.EMPTY;
        }).orElse(StringUtils.EMPTY);
        //存入到缓存
        logger.info("openId:{}", openId);
        setCacheOpenId(config, mobile, openId);
        return openId;
    }
    /**
     * 验价查询openId
     *
     * @param dto    dto
     * @param config 配置
     * @return openId
     */
    public String getOpenId(ElongConfig config, LinkHotelValidateDTO dto) {
        //不开启查询会员价
        if (!StringUtils.equalsIgnoreCase(config.getSfkqhyj(), "1")) {
            return StringUtils.EMPTY;
        }
        String phoneNumber = dto.getYdrPhoneNumber();
        if (StringUtils.isBlank(phoneNumber)) {
            return StringUtils.EMPTY;
        }
        return getCacheOpenId(config, phoneNumber);
    }
    /**
     * 下单获取openId
     *
     * @param dto    dto
     * @param config 配置
     * @return openId
     */
    public String getOpenId(ElongConfig config, LinkHotelOrderBookDTO dto) {
        //不开启查询会员价
        if (!StringUtils.equalsIgnoreCase(config.getSfkqhyj(), "1")) {
            return StringUtils.EMPTY;
        }
        String phoneNumber = dto.getYdrPhoneNumber();
        if (StringUtils.isBlank(phoneNumber)) {
            return StringUtils.EMPTY;
        }
        return getCacheOpenId(config, phoneNumber);
    }
    /**
     * @param dto dto
     * @return LinkHotelRegistrationVO
     */
    public LinkHotelRegistrationVO registration(LinkHotelRegistrationDTO dto) {
        ElongConfig config = SupplierConfigUtils.parse(dto.getSupplier(), ElongConfig.class);
        ImmutableTriple<Boolean, String, String> triple = getOpenId(config, dto.getMobile());
        if (Boolean.FALSE.equals(triple.getLeft()) || StringUtils.isBlank(triple.getRight())) {
            return RateSearchApiRes.failRegistration(triple.getMiddle());
        }
        LinkHotelRegistrationVO vo = new LinkHotelRegistrationVO();
        vo.setMemberId(triple.getRight());
        return vo;
    }

    /**
     * 下单获取openId
     *
     * @param phoneNumber phoneNumber
     * @param config      配置
     * @return openId
     */
    public ImmutableTriple<Boolean, String, String> getOpenId(ElongConfig config, String phoneNumber) {
        //不开启查询会员价
        if (!StringUtils.equalsIgnoreCase(config.getSfkqhyj(), "1")) {
            return ImmutableTriple.of(false, "未开启查询会员价", StringUtils.EMPTY);
        }
        //获取手机号
        if (StringUtils.isBlank(phoneNumber)) {
            return ImmutableTriple.of(false, "手机号为空", StringUtils.EMPTY);
        }
        if (StringUtils.equalsIgnoreCase(config.getCacheOpenId(), "1")) {
            String cacheOpenId = getCacheOpenId(config, phoneNumber);
            if (StringUtils.isNotBlank(cacheOpenId)) {
                logger.info("缓存中的openId:{}", cacheOpenId);
                return ImmutableTriple.of(true, "", cacheOpenId);
            }
        }
        Optional<ElongRegisterResponse> register = register(config, phoneNumber);
        if (!register.isPresent()) {
            return ImmutableTriple.of(false, "供应商返回信息为空", StringUtils.EMPTY);
        }
        ElongRegisterResponse response = register.get();
        if (Objects.nonNull(response.getResult()) && StringUtils.isNotBlank(response.getResult().getOpenId())) {
           String openId = response.getResult().getOpenId();
            //存入到缓存
            logger.info("openId:{}", openId);
            setCacheOpenId(config, phoneNumber, openId);
            return ImmutableTriple.of(true, "", openId);
        }
        return ImmutableTriple.of(false, StringUtils.defaultIfBlank(response.getErrorMsg(), response.getCode()), StringUtils.EMPTY);
    }

    /**
     * 获取缓存中的 openId
     *
     * @param config 配置
     * @param mobile 手机号
     * @return openId
     */
    private String getCacheOpenId(ElongConfig config, String mobile) {
        String openIdkey = genOpenIdkey(config, mobile);
        Object obj = hotelBaseDataCacheService.getByHotelId(CachePrefix.HOTEL_MEMBER_CACHE_PREFIX, config, openIdkey);
        if (Objects.isNull(obj)) {
            return StringUtils.EMPTY;
        }
        return String.valueOf(obj);
    }

    /**
     * 获取缓存中的 openId
     *
     * @param config 配置
     * @param mobile 手机
     * @param openId openId
     */
    private void setCacheOpenId(ElongConfig config, String mobile, String openId) {
        if (StringUtils.isBlank(openId)) {
            return;
        }
        String openIdkey = genOpenIdkey(config, mobile);
        String cacheExpireTime = config.getCacheExpireTime();
        int epTime = NumberUtils.toInt(cacheExpireTime, cacheEpTime);
        hotelBaseDataCacheService.putByHotelId(CachePrefix.HOTEL_MEMBER_CACHE_PREFIX, config, openIdkey, openId, epTime);
    }

    /**
     * oepnId 的key
     *
     * @param config 配置
     * @param mobile 手机号
     * @return key
     */
    private String genOpenIdkey(ElongConfig config, String mobile) {
        return config.getAppKey() + SymbolConstant.HALF_COLON + mobile;
    }

    /**
     * 加密手机号
     *
     * @param mobile 手机号
     * @param appKey appkey
     * @return 加密和字符串
     */
    private String encryptMobile(String mobile, String appKey) {
        try {
            int length = appKey.length();
            String key = StringUtils.substring(appKey, length - NumConstant.NUM_8);
            String content = System.currentTimeMillis() / NumConstant.NUM_1000 + SymbolConstant.WELL + mobile;
            return ElongCipherUtil.desEncrypt(content, key);
        } catch (Exception ex) {
            logger.error("加密电话号码异常;mobile:{};appkey:{}", mobile, appKey, ex);
        }
        return StringUtils.EMPTY;
    }
}