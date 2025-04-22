package com.buni.user.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "角色信息getVO")
@Data
public class RoleGetVO implements Serializable {

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


}
