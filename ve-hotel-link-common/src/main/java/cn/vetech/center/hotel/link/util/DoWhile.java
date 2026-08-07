package cn.vetech.center.hotel.link.util;

import cn.vetech.center.hotel.link.constant.NumConstant;
import cn.vetech.charge.cloud.modules.utils.base.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * do while结构
 *
 * @author luqs
 * @version v1.0
 */
public class DoWhile {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DoWhile.class);

    /**
     * 执行
     *
     * @param bizSupplier    业务函数
     * @param retryPredicate 是否重试函数（true：重试）
     * @param sleepTime      睡眠时间（ms）（最多500ms）
     * @param <R>            结果泛型
     * @return R
     */
    @Nullable
    public static <R> R execute(Supplier<R> bizSupplier, Predicate<R> retryPredicate, int sleepTime) {
        return execute(bizSupplier, retryPredicate, () -> null, NumConstant.NUM_3, sleepTime);
    }

    /**
     * 执行
     *
     * @param bizSupplier    业务函数
     * @param retryPredicate 是否重试函数（true：重试）
     * @param retryTime      重试次数（最多10次）
     * @param sleepTime      睡眠时间（ms）（最多500ms）
     * @param <R>            结果泛型
     * @return R
     */
    @Nullable
    public static <R> R execute(Supplier<R> bizSupplier, Predicate<R> retryPredicate, int retryTime, int sleepTime) {
        return execute(bizSupplier, retryPredicate, () -> null, retryTime, sleepTime);
    }

    /**
     * 执行
     *
     * @param bizSupplier     业务函数
     * @param retryPredicate  是否重试函数（true：重试）
     * @param defaultSupplier 默认函数，即重试结束后默认返回
     * @param sleepTime       睡眠时间（ms）（最多500ms）
     * @param <R>             结果泛型
     * @return R
     */
    public static <R> R execute(Supplier<R> bizSupplier, Predicate<R> retryPredicate, Supplier<R> defaultSupplier, int sleepTime) {
        return execute(bizSupplier, retryPredicate, defaultSupplier, NumConstant.NUM_3, sleepTime);
    }

    /**
     * 执行
     *
     * @param bizSupplier     业务函数
     * @param retryPredicate  是否重试函数（true：重试）
     * @param defaultSupplier 默认函数，即重试结束后默认返回
     * @param retryTime       重试次数（最多10次）
     * @param sleepTime       睡眠时间（ms）（最多500ms）
     * @param <R>             结果泛型
     * @return R
     */
    public static <R> R execute(Supplier<R> bizSupplier, Predicate<R> retryPredicate, Supplier<R> defaultSupplier, int retryTime, int sleepTime) {
        // 重试次数，不宜过多，暂默认最多10次！！！
        retryTime = retryTime > 0 ? Math.min(retryTime, NumConstant.NUM_10) : 0;
        // 睡眠时间，不宜过长，暂默认最多500ms！！！
        sleepTime = sleepTime > 0 ? Math.min(sleepTime, NumConstant.NUM_500) : 0;
        int retryCount = 0;
        boolean retryFlag;
        do {
            R r = bizSupplier.get();
            // 若不重试，则直接返回
            if (!retryPredicate.test(r)) {
                return r;
            }

            retryCount++;
            retryFlag = retryCount <= retryTime;
            if (retryFlag) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    logger.error("doWhile线程sleep异常：", e);
                    Thread.currentThread().interrupt();
                }
            }
        } while (retryFlag);
        return defaultSupplier.get();
    }

}