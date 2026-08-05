package cn.vetech.center.hotel.link.base;

import cn.vetech.center.hotel.link.enums.BusinessKeyEnum;
import cn.vetech.center.hotel.link.ratelimt.RateLimitClient;
import cn.vetech.center.hotel.link.ratelimt.RateLimiterUtil;
import cn.vetech.charge.cloud.modules.utils.time.VeDate;
import com.google.common.util.concurrent.AtomicLongMap;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author vetech
 * @since 25/02/14
 */
public class BaseRecordCountSize {


    /**
     * 增加限流
     */
    @Autowired
    protected RateLimitClient rateLimitClient;
    /**
     * 日志工具
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    private final AtomicLongMap<String> countMap = AtomicLongMap.create();


    private static final String TIME = "1";


    private static final String CAPACITY = "2";


    /**
     * 默认尝试获取锁
     *
     * @param businessKeyEnum businessKeyEnum
     * @param keyPrfixe       keyPrfixe
     * @return boolean
     */
    protected boolean defaultTryAccquire(BusinessKeyEnum businessKeyEnum, String keyPrfixe) {
        return tryAccquire(businessKeyEnum, keyPrfixe, TIME, CAPACITY);
    }


    /**
     * 尝试获取锁
     *
     * @param businessKeyEnum businessKeyEnum
     * @param keyPrfixe       keyPrfixe
     * @param time            time
     * @param capacity        capacity
     * @return boolean
     */
    protected boolean tryAccquire(BusinessKeyEnum businessKeyEnum, String keyPrfixe, String time, String capacity) {
        return rateLimitClient.tryAccquire(RateLimiterUtil.getRateLimiter(keyPrfixe, businessKeyEnum, time, capacity));
    }


    /**
     * 记录推送信息
     *
     * @param type   type
     * @param object object
     */
    public void recordCountAndSize(String type, Object object) {
        try {
            long objectSize = ObjectSizeCalculator.getObjectSize(object);
            String shortmm = VeDate.getStringDateShortmm();
            String countKey = String.join("-", shortmm, type, "count");
            String sizeKey = String.join("-", shortmm, type, "size");
            countMap.incrementAndGet(countKey);
            countMap.addAndGet(sizeKey, objectSize);
            if (countMap.size() > 100) {
                synchronized (countMap) {
                    if (countMap.size() > 100) {
                        logger.info("记录清除前记录的请求信息和大小：{}", countMap);
                        countMap.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("记录日志出现异常", e);
        }
    }

}