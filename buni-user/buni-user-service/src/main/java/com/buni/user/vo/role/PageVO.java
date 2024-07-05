package com.buni.user.vo.role;

import com.buni.framework.page.Page;
import com.buni.user.entity.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "分页查询角色VO")
@Data
public class PageVO extends Page<Authority> {

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	private String name;

}
