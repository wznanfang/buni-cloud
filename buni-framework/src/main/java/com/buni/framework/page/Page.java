package com.buni.framework.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zp.wei
 * @date 2024/4/20 16:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    /**
     * 查询页 默认1
     */
    public long current = 1;

    /**
     * 每页查询条数 默认10
     */
    public long size = 10;


}

