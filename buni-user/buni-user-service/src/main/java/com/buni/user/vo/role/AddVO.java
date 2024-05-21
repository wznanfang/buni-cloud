package com.buni.user.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 15:31
 */
@Schema(description = "新增角色")
@Data
public class AddVO implements Serializable {

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	@Size(max = 50, message = "名字不能超过50")
	@NotBlank(message = "名字不能为空")
	private String name;


}
