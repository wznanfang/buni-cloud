package com.buni.framework.config.ratelimit;

import com.buni.framework.constant.CommonConstant;
import com.buni.framework.enums.ResultEnum;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author zp.wei
 * @date 2023/9/22 13:54
 */
@Aspect
@Component
public class RateLimiter {

    @Resource
    private RedisService redisService;


    @Around("@annotation(rateLimit)")
    public Object limit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = CommonConstant.RATE_LIMITER + joinPoint.getSignature().getName();
        int maxLimit = rateLimit.limit();
        int seconds = rateLimit.time();
        long currentTime = LocalDateTime.now().getSecond();
        long startTime = (currentTime / seconds) * seconds;
        long endTime = startTime + seconds;
        // 获取时间段内的请求量
        long allRequests = redisService.zSetCount(key, startTime, endTime);
        if (allRequests > maxLimit) {
            throw new CustomException(ResultEnum.FREQUENT_VISITS.getCode(), ResultEnum.FREQUENT_VISITS.getMessage());
        }
        redisService.zSetRemoveRangeByScore(key, 0L, currentTime - seconds);
        redisService.zSetAdd(key, currentTime, currentTime);
        redisService.setKeyTime(key, seconds + 1, TimeUnit.SECONDS);
        return joinPoint.proceed();
    }


}
