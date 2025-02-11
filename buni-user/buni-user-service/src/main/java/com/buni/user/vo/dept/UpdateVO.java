package com.buni.user.vo.dept;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 15:31
 */
@Schema(description = "修改部门VO")
@Data
public class UpdateVO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 名字
     */
    @Schema(description = "名字")
    @Size(max = 50, message = "名字不能超过50")
    @NotBlank(message = "名字不能为空")
    private String name;

    /**
     * 父级id
     */
    @Schema(description = "父级id")
    @NotNull(message = "父级id不能为空")
    private Long parentId;

    /**
     * 负责人
     */
    @Schema(description = "负责人")
    @NotNull(message = "负责人不能为空")
    private Long leaderUserId;


}
