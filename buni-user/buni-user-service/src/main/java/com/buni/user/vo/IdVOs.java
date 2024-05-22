package com.buni.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "Id集合VO")
@Data
public class IdVOs implements Serializable {

    /**
     * id集合
     */
    @Schema(description = "id集合")
    @NotEmpty(message = "id集合不能为空")
    private List<Long> ids;
}
