package com.buni.user.vo.authority;

import com.buni.framework.util.StringSerializer;
import com.buni.user.enums.AuthTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Data
public class AuthorityInfoVO implements Serializable {

	/**
	 * id
	 */
	@JsonSerialize(using = StringSerializer.class)
	private Long id;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 父级id
	 */
	private Long parentId;

	/**
	 * 0：模块，1：菜单，2：按钮
	 */
	private AuthTypeEnum type;

	/**
	 * 标识码
	 */
	private String code;

	/**
	 * 序号
	 */
	private Integer sort;

	/**
	 * 接口url
	 */
	private String url;


}
