package com.buni.framework.config.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author zp.wei
 * @date 2023/9/22 10:52
 */
public interface RedisLock {

    boolean tryLock(String lockKey, String requestId, long expire, TimeUnit timeUnit);

    boolean tryLockForever(String lockKey, String requestId);

    boolean tryLock(String lockKey, String requestId);

    boolean releaseLock(String lockKey, String requestId);

    String get(String lockKey);

    long getSeqNo(String key, Long liveTime, TimeUnit timeUnit);

    long getSeqNo(String key);

    String getSeqNoAppend(String key, int len);

    long getSeqNo(String key, Long l);

    boolean tryLockWithSet(String lockKey, String lockValue, long expire, TimeUnit timeUnit);


}
