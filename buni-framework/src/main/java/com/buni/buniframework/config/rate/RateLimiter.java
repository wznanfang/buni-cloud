package com.buni.buniframework.config.rate;

import com.buni.buniframework.enums.ResultEnum;
import com.buni.buniframework.exception.CustomException;
import com.buni.buniframework.service.RedisService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        String key = "rate:limit:" + joinPoint.getSignature().getName();
        int maxLimit = rateLimit.limit();
        int seconds = rateLimit.time();
        long currentTime = LocalDateTime.now().getSecond();
        long startTime = (currentTime / seconds) * seconds;
        long endTime = startTime + seconds;
        //获取时间段内的请求量
        long allRequests = redisService.setCount(key, startTime, endTime);
        if (allRequests > maxLimit) {
            throw new CustomException(ResultEnum.FREQUENT_VISITS.getCode(), ResultEnum.FREQUENT_VISITS.getMessage());
        }
        redisService.setRemoveRangeByScore(key, 0L, currentTime - seconds);
        redisService.setAdd(key, currentTime, currentTime);
        redisService.setKeyTime(key, seconds + 1);
        return joinPoint.proceed();
    }


}
