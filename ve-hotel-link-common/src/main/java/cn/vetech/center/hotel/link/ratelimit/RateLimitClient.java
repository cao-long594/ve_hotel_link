package cn.vetech.center.hotel.link.ratelimt;

import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.center.hotel.link.enums.CachePrefix;
import cn.vetech.charge.cloud.modules.utils.localcache.LocalCacheUtil;
import com.google.common.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * @author wangkai
 * @since 2021/3/17
 */
@Component
public class RateLimitClient {

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitClient.class);
    /**
     *
     */
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 使用本地缓存
     */
    private final Cache<String, com.google.common.util.concurrent.RateLimiter> rateLimiterCache = LocalCacheUtil.getCacheInstance(NumConstant.NUM_200, NumConstant.NUM_20, TimeUnit.MINUTES);


    /**
     * @param limiterDTO   limiterDTO
     * @param consumeCount 消耗的个数
     * @return boolean boolean
     */
    public boolean tryAccquire(RateLimiter limiterDTO, Integer consumeCount) {
        if (limiterDTO == null) {
            LOGGER.warn("限流-对象为空,不限流，放行");
            return true;
        }
        //校验数据
        checkParam(limiterDTO, consumeCount);

        Integer capacity = limiterDTO.getCapacity();
        Integer sleep = limiterDTO.getSleep();
        Integer time = limiterDTO.getTime();
        String key = CachePrefix.RATE_LIMIT.getKeyPrefix().concat(limiterDTO.getKey());
        //最大尝试次数
        Integer maxAttemp = limiterDTO.getMaxAttemp();

        com.google.common.util.concurrent.RateLimiter rateLimiter = null;
        try {
            rateLimiter = rateLimiterCache.get(key, () -> {
                return createLimiter(capacity, time, key);
            });
        } catch (Exception e) {
            LOGGER.error("执行异常", e);
            throw new RuntimeException(e);
        }
        for (int i = 0; i < maxAttemp; i++) {
            boolean tryAcquire = rateLimiter.tryAcquire(consumeCount, sleep, TimeUnit.MILLISECONDS);
            if (tryAcquire) {
                return tryAcquire;
            }
        }
        LOGGER.info("限流-接口超频,key:{},获取限流，获取个数:{},重试等待时间:{}ms,重试次数:{}", key, consumeCount, sleep,maxAttemp);
        return Boolean.FALSE;
    }

    private static com.google.common.util.concurrent.RateLimiter createLimiter(Integer capacity, Integer time, String key) {
        double permitsPerSecond = BigDecimal.valueOf(capacity).divide(BigDecimal.valueOf(time), NumConstant.NUM_2, RoundingMode.DOWN).doubleValue();
        if (permitsPerSecond<=0){
            LOGGER.warn("解析key:{}每秒限频频率小于等于0", key);
            permitsPerSecond = NumConstant.NUM_1;
        }
        return com.google.common.util.concurrent.RateLimiter.create(permitsPerSecond);
    }


    /**
     * @param rateLimiter rateLimiter
     * @return boolean boolean
     */
    public boolean tryAccquire(RateLimiter rateLimiter) {
        return tryAccquire(rateLimiter, NumConstant.NUM_1);

    }


    /**
     * 校验数据
     *
     * @param rateLimiter  rateLimiter
     * @param consumeCount consumeCount
     */
    private void checkParam(RateLimiter rateLimiter, Integer consumeCount) {
        Integer capacity = rateLimiter.getCapacity();
        Integer sleep = rateLimiter.getSleep();
        Integer maxAttemp = rateLimiter.getMaxAttemp();
        Integer time = rateLimiter.getTime();
        Assert.notNull(time, "时间范围为空");
        Assert.isTrue(time>0, "时间范围不能小于等于0");
        Assert.notNull(sleep, "休眠时间不能为空");
        Assert.notNull(rateLimiter, "参数不能为空");
        Assert.notNull(consumeCount, "消耗个数不能为空");
        Assert.notNull(capacity, "总容量不能为空");
        Assert.notNull(maxAttemp, "最大尝试获取次数不能为空");
        Assert.isTrue(consumeCount <= capacity, "消耗个数不能大于总容量");
    }
}
