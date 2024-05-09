package com.buni.user.vo.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Data
public class EnableVO {

    /**
     * 用户id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 是否启用(0:否，1：是)
     */
    private Boolean enable;


}
