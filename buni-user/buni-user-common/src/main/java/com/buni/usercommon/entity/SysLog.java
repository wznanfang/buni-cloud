package com.buni.usercommon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author zp.wei
 * @date 2022/2/25 15:27
 */
@TableName(value = "sysLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {


    /**
     * 描述
     */
    private String description;

    /**
     * 用户名
     */
    private String username;

    /**
     * 消耗时间
     */
    private Long spendTime;

    /**
     * url
     */
    private String url;

    /**
     * urlPath
     */
    private String urlPath;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private String parameter;


}
