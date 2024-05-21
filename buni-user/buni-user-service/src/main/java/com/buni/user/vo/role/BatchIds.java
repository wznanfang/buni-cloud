package com.buni.user.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("批量Id")
@Data
public class BatchIds implements Serializable {

    /**
     * id集合
     */
    @Schema(description = "id集合")
    @NotEmpty(message = "id集合不能为空")
    private List<Long> ids;
}
