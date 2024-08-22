package com.buni.user.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "新增用户统计VO")
public class UserStatisticsVO implements Serializable {

    @Schema(description = "新增用户数")
    private Integer newUserCount;

    @Schema(description = "日期")
    private LocalDate createTime;


}
