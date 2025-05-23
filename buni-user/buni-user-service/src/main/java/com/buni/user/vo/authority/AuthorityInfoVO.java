package com.buni.user.vo.authority;

import com.buni.user.enums.AuthTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "权限信息infoVO")
@Data
public class AuthorityInfoVO implements Serializable {

	/**
	 * id
	 */
	@Schema(description = "id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	private String name;

	/**
	 * 父级id
	 */
	@Schema(description = "父级id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long parentId;

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

	/**
	 * 序号
	 */
	@Schema(description = "序号")
	private Integer sort;

	/**
	 * 接口url
	 */
	@Schema(description = "接口url")
	private String url;


}
