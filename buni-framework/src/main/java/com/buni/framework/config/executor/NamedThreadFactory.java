package com.buni.framework.config.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给自定义线程池的线程加上名字
 * @author zp.wei
 * @date 2023/9/22 10:18
 */
public class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }


    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, namePrefix + "-" + threadNumber.getAndIncrement());
    }


}
