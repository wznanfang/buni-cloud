package com.buni.buniuserapi.log;

import cn.hutool.core.util.ObjUtil;
import com.buni.buniframework.util.HeaderUtil;
import com.buni.buniuserapi.service.SysLogApiService;
import com.buni.usercommon.entity.SysLog;
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

/**
 * @author zp.wei
 * @date 2023/9/26 9:34
 */
@Slf4j
@Aspect
@Component
public class SysLogRecordAspect {

    @Resource
    private SysLogApiService sysLogApiService;
    @Resource
    private ObjectMapper objectMapper;


    @Pointcut("@annotation(com.buni.buniuserapi.log.SysLogRecord)")
    private void sysLogRecord() {

    }


    @Around("sysLogRecord()")
    public Object setLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        // 将日志写入文件或者数据库
        try {
            HttpServletRequest request = HeaderUtil.getRequest();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            SysLogRecord sysLogRecord = method.getAnnotation(SysLogRecord.class);
            //日志入库
            SysLog sysLog = new SysLog();
            sysLog.setUsername(HeaderUtil.getUserName());
            sysLog.setUrl(String.valueOf(request.getRequestURL()));
            sysLog.setUrlPath(request.getServletPath());
            sysLog.setMethod(request.getMethod());
            sysLog.setParameter(ObjUtil.isEmpty(joinPoint.getArgs()) ? null : objectMapper.writeValueAsString(joinPoint.getArgs()[0]));
            sysLog.setIp(HeaderUtil.getIp());
            sysLog.setDescription(sysLogRecord.description());
            sysLog.setElapsedTime(elapsedTime);
            sysLogApiService.save(sysLog);
        } catch (Exception e) {
            log.error("日志记录错误：", e);
        }
        return result;
    }


}

