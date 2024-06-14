package com.buni.user.service;

import com.buni.user.entity.SysLog;

/**
 * @author zp.wei
 * @date 2023/9/26 9:31
 */
public interface SysLogDubboService {


    /**
     * 保存日志
     *
     * @param sysLog
     */
    void save(SysLog sysLog);


}
