package com.buni.user.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "更新用户头像VO")
public class UpdateAvatarVO implements Serializable {

    @Schema(description = "id")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "头像")
    @NotBlank(message = "头像不能为空")
    private String avatar;

}
