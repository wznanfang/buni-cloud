package com.buni.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.usercommon.entity.SysLog;
import com.buni.userservice.mapper.SysLogMapper;
import com.buni.userservice.service.SysLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【SysLog(用户鉴权)】的数据库操作Service实现
 * @createDate 2023-09-22 16:55:29
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


}