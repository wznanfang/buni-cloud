package com.buni.user.vo.user;

import com.buni.user.enums.BooleanEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Schema(description = "启用-禁用用户VO")
@Data
public class EnableVO implements Serializable {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 是否启用(0:否，1：是)
     */
    @Schema(description = "是否启用(0:否，1：是)")
    private BooleanEnum enable;


}
