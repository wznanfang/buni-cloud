package com.buni.user.vo.authority;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buni.user.entity.Authority;
import com.buni.user.enums.AuthTypeEnum;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Data
public class PageVO extends Page<Authority> {

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 0：模块，1：菜单，2：按钮
	 */
	private AuthTypeEnum type;

	/**
	 * 标识码
	 */
	private String code;

}
