package cn.vetech.center.hotel.link.supply.service.aspect;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.validate.dto.LinkHotelValidateDTO;
import cn.vetech.center.hotel.link.constant.HotelLinkConstant;
import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.util.JsonMapperUtil;
import cn.vetech.center.hotel.link.util.VeDateUtils;
import cn.vetech.charge.cloud.cache.redis.config.VeRedisUtilService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author vetech
 * @since 2023/11/13
 */
public class RateBaseAspect {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RateBaseAspect.class);

    /**
     *
     */
    @Autowired
    protected VeRedisUtilService veRedisUtilService;


    /**
     * 压缩缓存  dev和release分支使用userDefinedGzipHessianRedisTemplate
     * 4.7.1分支使用gzipHessianRedisTemplate 因为userDefined 没有合并至4.7.1分支
     */
    @Resource(name = "userDefinedGzipHessianRedisTemplate")
    protected StringRedisTemplate redisTemplate;


    /**
     * 缓存失败的酒店信息
     *
     * @param dto dto
     */
    protected void cacheValidateFaildHotel(LinkHotelValidateDTO dto) {
        try {
            String validateFailHotelKey = getValidateFailHotelKey(dto.getLocalHotelId(), dto.getCheckInDate(), dto.getCheckOutDate());
            if (StringUtils.isBlank(validateFailHotelKey)) {
                logger.error("拼接验价失败的缓存key为空,验价对象:{}", JsonMapperUtil.toJsonStr(dto));
                return;
            }
            String val = StringUtils.join(Arrays.asList(dto.getFybh(), dto.getHotelId(), dto.getRoomId(), dto.getRatePlanId(), dto.getYsOrderId()), SymbolConstant.UNION);
            logger.info("供应商验价失败后记录失败的酒店信息,缓存key:{},缓存值:{}", validateFailHotelKey, val);
            redisTemplate.opsForValue().set(validateFailHotelKey, val, NumConstant.NUM_20, TimeUnit.MINUTES);
        }catch (Exception e){
            logger.error("供应商验价失败后记录失败的酒店信息,执行失败,验价对象:{}", JsonMapperUtil.toJsonStr(dto),e);
        }

    }


    protected String getValidateFailHotelKey(String localHotelId, String checkInDate, String checkOutDate) {
        if (StringUtils.isAnyBlank(localHotelId, checkInDate, checkOutDate)) {
            return StringUtils.EMPTY;
        }
        String validateFailKey = String.join(SymbolConstant.UNDER_LINE, localHotelId, VeDateUtils.convertDate(checkInDate), VeDateUtils.convertDate(checkOutDate));
        return veRedisUtilService.genKey(HotelLinkConstant.APPLICATION_NAME, validateFailKey);
    }


    /**
     * 判断是否有key
     *
     * @param key key
     * @return java.lang.Boolean
     */
    protected Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("执行redis的haskey异常", e);
            return Boolean.FALSE;
        }
    }


}
