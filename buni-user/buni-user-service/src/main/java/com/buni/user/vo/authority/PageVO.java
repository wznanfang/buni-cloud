package com.buni.user.vo.authority;

import com.buni.framework.page.Page;
import com.buni.user.entity.SysAuthority;
import com.buni.user.enums.AuthTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "分页查询权限信息VO")
@Data
public class PageVO extends Page<SysAuthority> {

	/**
	 * 父id
	 */
	@Schema(description = "父id")
	private Long parentId;

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	private String name;

	/**
	 * 0：模块，1：菜单，2：按钮
	 */
	@Schema(description = "权限类型（0：模块，1：菜单，2：按钮）")
	private AuthTypeEnum type;

	/**
	 * 标识码
	 */
	@Schema(description = "标识码")
	private String code;

}
