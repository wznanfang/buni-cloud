package com.buni.user.vo.user;


import com.buni.user.enums.BooleanEnum;
import com.buni.user.vo.IdVOs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "批量启用-禁用用户VO")
public class BatchEnableVO implements Serializable {

    @Schema(description = "用户id")
    private IdVOs idVOs;

    @Schema(description = "是否启用(0:否，1：是)")
    private BooleanEnum enable;


}
