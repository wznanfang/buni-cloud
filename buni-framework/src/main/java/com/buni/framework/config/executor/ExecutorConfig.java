package com.buni.framework.config.executor;

import com.buni.framework.constant.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zp.wei
 * @date 2023/9/22 15:10
 */
@Configuration
public class ExecutorConfig {


    /**
     * 普通线程池
     * 用于处理一般任务
     *
     * @return
     */
    @Bean(CommonConstant.NORMAL_EXECUTOR_NAME)
    public ThreadPoolExecutor normalThreadPoolExecutor() {
        ThreadFactory threadFactory = new NamedThreadFactory(CommonConstant.EXECUTOR_NAME_PREFIX);
        return new ThreadPoolExecutor(CommonConstant.PROCESSORS, CommonConstant.PROCESSORS, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }


    /**
     * 大线程池
     * 用于处理大批数据量的任务
     *
     * @return
     */
    @Bean(CommonConstant.LARGE_EXECUTOR_NAME)
    public ThreadPoolExecutor largeThreadPoolExecutor() {
        ThreadFactory threadFactory = new NamedThreadFactory(CommonConstant.EXECUTOR_NAME_PREFIX);
        return new ThreadPoolExecutor(CommonConstant.PROCESSORS, CommonConstant.PROCESSORS * 2, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
