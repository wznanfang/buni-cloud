package com.buni.user.service.impl;

import com.buni.user.service.SysLogApiService;
import com.buni.user.entity.SysLog;
import com.buni.user.service.SysLogService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zp.wei
 * @date 2023/9/26 9:34
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysLogApiServiceImpl implements SysLogApiService {

    @Resource
    private SysLogService sysLogService;


    /**
     * 保存日志
     * @param sysLog 日志实体
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogService.save(sysLog);
    }


}
