package com.buni.user.vo.user;


import com.buni.user.enums.BooleanEnum;
import com.buni.user.vo.IdVOs;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "批量启用-禁用用户VO")
public class BatchEnableVO implements Serializable {

    @Schema(description = "用户id")
    @Valid
    private IdVOs idVOs;

    @Schema(description = "是否启用(0:否，1：是)")
    @NotNull(message = "是否启用不能为空")
    private BooleanEnum enable;


}
