package com.buni.framework.config.executor;

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

    public final static String EXECUTOR_NAME = "threadPoolExecutor";

    private final static Integer PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Bean(EXECUTOR_NAME)
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadFactory threadFactory = new NamedThreadFactory("buni-cloud");
        return new ThreadPoolExecutor(PROCESSORS, PROCESSORS * 2, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
