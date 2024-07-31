package com.buni.user.log;

import cn.hutool.core.util.ObjUtil;
import com.buni.framework.util.HeaderUtil;
import com.buni.user.entity.SysLog;
import com.buni.user.service.SysLogDubboService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zp.wei
 * @date 2023/9/26 9:34
 */
@Slf4j
@Aspect
@Component
public class SysLogRecordAspect {

    @Resource
    private SysLogDubboService sysLogDubboService;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private ThreadPoolExecutor normalThreadPoolExecutor;


    @Pointcut("@annotation(com.buni.user.log.SysLogRecord)")
    private void sysLogRecord() {

    }


    @Around("sysLogRecord()")
    public Object setLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            saveSysLog(joinPoint, startTime);
        }
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long startTime) {
        try {
            HttpServletRequest request = HeaderUtil.getRequest();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            SysLogRecord sysLogRecord = method.getAnnotation(SysLogRecord.class);
            // 构建SysLog对象
            SysLog sysLog = new SysLog();
            sysLog.setUsername(HeaderUtil.getUserName());
            sysLog.setUrl(String.valueOf(request.getRequestURL()));
            sysLog.setUrlPath(request.getServletPath());
            sysLog.setMethod(request.getMethod());
            sysLog.setParameter(ObjUtil.isEmpty(joinPoint.getArgs()) ? null : objectMapper.writeValueAsString(joinPoint.getArgs()[0]));
            sysLog.setIp(HeaderUtil.getIp());
            sysLog.setDescription(sysLogRecord.description());
            sysLog.setElapsedTime(System.currentTimeMillis() - startTime);
            // 异步保存日志
            CompletableFuture.runAsync(() -> sysLogDubboService.save(sysLog), normalThreadPoolExecutor);
        } catch (Exception e) {
            log.error("日志记录失败,失败原因是----------：{}", e.getMessage());
        }
    }


}

