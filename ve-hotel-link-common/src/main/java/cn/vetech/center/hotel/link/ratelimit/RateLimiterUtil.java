package cn.vetech.center.hotel.link.ratelimt;


import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.entity.IBusinessKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 工具类
 *
 * @author wangkai
 * @since 2021年3月17日
 */
public class RateLimiterUtil {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterUtil.class);

    /**
     * @param shbh            shbh
     * @param businessKey businessKey
     * @param timeStr         timeStr
     * @param sleepStr        sleepStr
     * @param capacityStr     capacityStr
     * @param maxAttempStr    maxAttempStr
     * @return cn.vetech.center.hotel.link.ratelimt.RateLimiter
     */
    public static RateLimiter getRateLimiter(String shbh, IBusinessKey businessKey, String timeStr,
                                             String sleepStr, String capacityStr, String maxAttempStr) {
        String joinKey = joinKey(shbh, businessKey.getbKey());
        if (StringUtils.isBlank(joinKey)) {
            return null;
        }
        RateLimiter limiter = new RateLimiter();
        limiter.setKey(joinKey);
        try {
            Optional.ofNullable(capacityStr).map(Integer::valueOf).ifPresent(limiter::setCapacity);
            Optional.ofNullable(maxAttempStr).map(Integer::valueOf).ifPresent(limiter::setMaxAttemp);
            Optional.ofNullable(sleepStr).map(Integer::valueOf).ifPresent(limiter::setSleep);
            Optional.ofNullable(timeStr).map(Integer::valueOf).ifPresent(limiter::setTime);
            return limiter;
        } catch (Exception e) {
            LOGGER.error("限流对象，转换参数异常:{},不进行限流", joinKey, e);
        }
        return null;
    }

    /**
     * @param shbh            shbh
     * @param businessKey businessKey
     * @param timeStr         timeStr
     * @param capacityStr     capacityStr
     * @return cn.vetech.center.hotel.link.ratelimt.RateLimiter
     */
    public static RateLimiter getRateLimiter(String shbh, IBusinessKey businessKey, String timeStr, String capacityStr) {


        return getRateLimiter(shbh, businessKey, timeStr, null, capacityStr, null);

    }


    /**
     * @param shbh            shbh
     * @param businessKey businessKey
     * @return cn.vetech.center.hotel.link.ratelimt.RateLimiter
     */
    public static RateLimiter getRateLimiter(String shbh, IBusinessKey businessKey) {


        return getRateLimiter(shbh, businessKey, null, null);

    }

    /**
     * 拼接
     *
     * @param strings strings
     * @return java.lang.String
     */
    public static String joinKey(String... strings) {
        return Stream.of(strings).filter(StringUtils::isNotBlank).collect(Collectors.joining(SymbolConstant.UNDER_LINE));
    }



}