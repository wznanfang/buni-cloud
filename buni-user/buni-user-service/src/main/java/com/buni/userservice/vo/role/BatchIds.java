package com.buni.userservice.vo.role;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BatchIds implements Serializable {

    /**
     * id集合
     */
    @NotEmpty(message = "id集合不能为空")
    private List<Long> ids;
}
