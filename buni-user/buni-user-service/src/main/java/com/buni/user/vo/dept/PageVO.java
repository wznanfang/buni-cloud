package com.buni.user.vo.dept;

import com.buni.framework.page.Page;
import com.buni.user.entity.SysAuthority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "分页查询部门信息VO")
@Data
public class PageVO extends Page<SysAuthority> {


    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "负责人")
    private Long leaderUserId;


}
