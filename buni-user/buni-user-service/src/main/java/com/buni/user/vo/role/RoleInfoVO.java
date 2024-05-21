package com.buni.user.vo.role;

import com.buni.framework.util.StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "角色信息info")
@Data
public class RoleInfoVO implements Serializable {

	/**
	 * id
	 */
	@Schema(description = "id")
	@JsonSerialize(using = StringSerializer.class)
	private Long id;

	/**
	 * 名字
	 */
	@Schema(description = "名字")
	private String name;


}
