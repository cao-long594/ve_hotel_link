package cn.vetech.center.hotel.link.lock;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.lock.enums.ILockEnum;
import cn.vetech.charge.cloud.cache.api.IVeCacheManage;
import cn.vetech.charge.cloud.exception.SystemRuntimeException;
import cn.vetech.charge.cloud.modules.utils.net.IPUtil;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import cn.vetech.charge.errcode.hotel.HotelErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * cds分布式lock
 *
 * @author luqs
 * @version v1.0
 */
@Component
public class VeLinkDisLock {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VeLinkDisLock.class);
    /**
     * 分布式lock前缀
     */
    private static final String LOCK_PREFIX = "disLock:";
    /**
     * 应用名
     */
    private static final String APP_NAME = "ve-hotel-link";
    /**
     * 缓存
     */
    @Resource(name = "rawUserDefinedRedisManage")
    private IVeCacheManage veCacheManage;

    /**
     * 获取lock并执行
     *
     * @param lockEnum        lock枚举
     * @param lockUniKey      lock唯一key，注：若为空，则以lock枚举的key为准，否则lock枚举的key + lock唯一key
     * @param lockSuccessFunc lock成功函数
     */
    public void tryLockAndRun(ILockEnum lockEnum, String lockUniKey, Runnable lockSuccessFunc) {
        tryLockAndExecute(lockEnum, lockUniKey, (lockVal) -> null, () -> run(lockSuccessFunc));
    }

    /**
     * 获取lock并执行
     *
     * @param lockEnum        lock枚举
     * @param lockUniKey      lock唯一key，注：若为空，则以lock枚举的key为准，否则lock枚举的key + lock唯一key
     * @param lockFailFunc    lock失败函数
     * @param lockSuccessFunc lock成功函数
     */
    public void tryLockAndRun(ILockEnum lockEnum, String lockUniKey, Runnable lockFailFunc, Runnable lockSuccessFunc) {
        tryLockAndExecute(lockEnum, lockUniKey, (lockVal) -> run(lockFailFunc), () -> run(lockSuccessFunc));
    }

    /**
     * 获取lock并执行
     *
     * @param lockEnum        lock枚举
     * @param lockUniKey      lock唯一key，注：若为空，则以lock枚举的key为准，否则lock枚举的key + lock唯一key
     * @param lockFailFunc    lock失败函数
     * @param lockSuccessFunc lock成功函数
     * @param <R>             结果泛型
     * @return R
     */
    public <R> R tryLockAndExecute(ILockEnum lockEnum, String lockUniKey, Function<String, R> lockFailFunc, Supplier<R> lockSuccessFunc) {
        // 校验
        check(lockEnum);
        String cacheKey = LOCK_PREFIX + lockEnum.getLockKeyPrefix() + StringUtils.defaultIfBlank(lockUniKey, StringUtils.EMPTY);
        String cacheVal = IPUtil.getIp() + SymbolConstant.UNDER_LINE + Thread.currentThread().getId() + SymbolConstant.UNDER_LINE + VeDate.getStringDate();
        int waitTime = lockEnum.getWaitTime();
        int sleepCount = 0;
        boolean lockFlag = false;
        try {
            do {
                boolean setFlag = veCacheManage.setIfAbsent(APP_NAME, cacheKey, cacheVal, lockEnum.getLeaseTime());
                if (setFlag) {
                    lockFlag = true;
                    return lockSuccessFunc.get();
                }

                String lockVal = Objects.toString(veCacheManage.get(APP_NAME, cacheKey), "");
                if (waitTime <= 0 || sleepCount >= 1) {
                    logger.warn("分布式lock【{}】已被【{}】占用", cacheKey, lockVal);
                    return lockFailFunc.apply(lockVal);
                }

                logger.info("lock【{}】已被【{}】占用，等待【{}】ms再执行", cacheKey, lockVal, waitTime);
                sleepCount++;
                sleep(waitTime);
            } while (true);
        } catch (Exception e) {
            logger.error("分布式lock【{}】执行异常：", cacheKey, e);
     throw new SystemRuntimeException(HotelErrorCode.HOTEL_0000, e);
        } finally {
            if (lockFlag) {
                Object cacheValObj = veCacheManage.get(APP_NAME, cacheKey);
                String lockVal = Objects.toString(cacheValObj, "");
                if (StringUtils.equals(cacheVal, lockVal)) {
                    veCacheManage.remove(APP_NAME, cacheKey);
                    logger.info("【{}】释放lock【{}】成功", cacheKey, cacheVal);
                } else {
                    logger.info("【{}】无权释放新lock【{}】,旧lock【{}】", cacheKey, lockVal, cacheVal);
                }
            }
        }
    }

    /**
     * 校验
     *
     * @param lockEnum lock枚举
     */
    private void check(ILockEnum lockEnum) {
        if (StringUtils.isBlank(lockEnum.getLockKeyPrefix())) {
            logger.error("分布式锁key【{}】不可为空", lockEnum.getLockKeyPrefix());
            throw new SystemRuntimeException(HotelErrorCode.HOTEL_0000, "分布式锁key为空");
        }
        if (lockEnum.getWaitTime() < 0) {
            logger.error("分布式锁等待时长【{}】不可小于0", lockEnum.getWaitTime());
            throw new SystemRuntimeException(HotelErrorCode.HOTEL_0000, "分布式锁等待时长小于0");
        }
        if (lockEnum.getLeaseTime() <= 0) {
            logger.error("分布式锁占用时长【{}】不可小于等于0", lockEnum.getLeaseTime());
            throw new SystemRuntimeException(HotelErrorCode.HOTEL_0000, "分布式锁占用时长小于等于0");
        }
    }

    /**
     * sleep
     *
     * @param waitTime 等待时长
     */
    private void sleep(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            logger.error("cdsHotel分布式锁sleep异常：", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * run
     *
     * @param runnable 函数
     * @return null
     */
    private Object run(Runnable runnable) {
        runnable.run();
        return null;
    }
}
