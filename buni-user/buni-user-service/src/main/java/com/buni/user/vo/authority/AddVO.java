package com.buni.user.vo.authority;

import com.buni.user.enums.AuthTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 15:31
 */
@Schema(description = "新增权限VO")
@Data
public class AddVO implements Serializable {

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	@Size(max = 50, message = "名字不能超过50")
	@NotBlank(message = "名字不能为空")
	private String name;

	/**
	 * 父级id
	 */
	@Schema(description = "父级id")
	private Long parentId;

	/**
	 * 0：模块，1：菜单，2：按钮
	 */
	@Schema(description = "权限类型（0：模块，1：菜单，2：按钮）")
	@NotNull(message = "权限类型不能为空")
	private AuthTypeEnum type;

	/**
	 * 标识码
	 */
	@Schema(description = "标识码")
	@NotBlank(message = "标识码不能为空")
	private String code;

	/**
	 * 序号
	 */
	@Schema(description = "序号")
	@NotNull(message = "序号不能为空")
	private Integer sort;

	/**
	 * 接口url
	 */
	@Schema(description = "接口url")
	@Size(max = 255, message = "接口url不能超过255")
	@NotBlank(message = "接口url不能为空")
	private String url;

}
