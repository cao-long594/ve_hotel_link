package cn.vetech.center.hotel.link.config;

import cn.vetech.center.hotel.link.constant.NumConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 价格计划异步线程管理，防止开启线程过多照成应用等待
 *
 * @author xiechao
 */
@Configuration
@EnableAsync
public class JgjhThreadManage {
    /**
     * 日志服务
     */
    private static final Logger logger = LoggerFactory.getLogger(JgjhThreadManage.class);
    /**
     * 线程数量
     */
    private static final int THREAD_COUNT = 300;
    /**
     * 线程数量
     */
    private static final int THREAD_MAX_COUNT = 500;
    /**
     * 线程数量最大任务队列数量
     */
    private static final int THREAD_TASK_MAX_COUNT = 50000;

    /**
     * 异步线程配置
     *
     * @return 返回线程池配置
     */
    @Bean
    public Executor asyncJgjhServiceExecutor() {
        logger.info("start asyncJgjhServiceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(THREAD_COUNT);
        //配置最大线程数
        executor.setMaxPoolSize(THREAD_MAX_COUNT);
        //配置队列大小
        executor.setQueueCapacity(THREAD_TASK_MAX_COUNT);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-hotel-jgjh-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 实时报价最低价线程
     * @return java.util.concurrent.Executor
     */
    @Bean
    public Executor asyncRealTimePrice() {
        logger.info("start asyncRealTimePrice");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(NumConstant.NUM_200);
        //配置最大线程数
        executor.setMaxPoolSize(NumConstant.NUM_500);
        //配置队列大小
        executor.setQueueCapacity(NumConstant.NUM_100);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-realtimeprice--");
        executor.setAllowCoreThreadTimeOut(true);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 配置收集价格
     *
     * @return 返回线程池配置
     */
    @Bean
    public Executor asyncCollectPrice() {
        logger.info("start asyncasyncCollectPriceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(NumConstant.NUM_10);
        executor.setAllowCoreThreadTimeOut(true);
        //配置最大线程数
        executor.setMaxPoolSize(NumConstant.NUM_20);
        //配置队列大小
        executor.setQueueCapacity(NumConstant.NUM_10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("collect-price-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 通用线程池，使用丢弃老的策略
     *
     * @return 返回线程池配置
     */
    @Bean
    public Executor commonPool() {
        logger.info("start asyncasyncCollectPriceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(NumConstant.NUM_5);
        executor.setAllowCoreThreadTimeOut(true);
        //配置最大线程数
        executor.setMaxPoolSize(NumConstant.NUM_20);
        //配置队列大小
        executor.setQueueCapacity(NumConstant.NUM_30);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("common-pool");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}

