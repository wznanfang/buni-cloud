package com.buni.user.vo.userrole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zp.wei
 * @date 2024/7/15 14:39
 */
@Schema(description = "用户角色新增VO")
@Data
public class AddVO implements Serializable {


    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 角色id集合
     */
    @Schema(description = "角色id集合")
    private List<Long> roleIds;

}
