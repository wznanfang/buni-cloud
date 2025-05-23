package com.buni.user.vo.user;

import com.buni.framework.page.Page;
import com.buni.user.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/21 15:39
 */
@Data
@Schema(description = "分页查询用户信息VO")
public class PageVO extends Page<SysUser> {


    /**
     * 用户名
     */
    @Schema(description = "用户名-姓名")
    private String inputSearch;


}
