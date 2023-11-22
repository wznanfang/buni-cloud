package com.buni.userservice.vo.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 15:31
 */
@Data
public class UpdateVO implements Serializable {

	/**
	 * id
	 */
	@NotNull(message = "id不能为空")
	private Long id;

	/**
	 * 名字
	 */
	@Size(max = 50, message = "名字不能超过50")
	@NotBlank(message = "名字不能为空")
	private String name;

}
