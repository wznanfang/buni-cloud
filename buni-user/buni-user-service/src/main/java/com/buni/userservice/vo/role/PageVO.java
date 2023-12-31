package com.buni.userservice.vo.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buni.usercommon.entity.Authority;
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

}
