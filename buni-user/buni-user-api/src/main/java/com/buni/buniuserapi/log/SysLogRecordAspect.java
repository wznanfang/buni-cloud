package com.buni.buniuserapi.log;

import com.buni.buniframework.util.HeaderUtil;
import com.buni.buniuserapi.service.SysLogApiService;
import com.buni.usercommon.entity.SysLog;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

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


    @Pointcut("@annotation(com.buni.buniuserapi.log.SysLogRecord)")
    private void sysLogRecord() {

    }


    @AfterReturning(pointcut = "sysLogRecord()")
    public void doAfterReturning(JoinPoint joinPoint) {
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
            sysLog.setParameter(Arrays.toString(joinPoint.getArgs()));
            sysLog.setIp(HeaderUtil.getIp());
            sysLog.setDescription(sysLogRecord.description());
            sysLogApiService.save(sysLog);
        } catch (Exception e) {
            log.error("日志记录错误：", e);
        }
    }


}

