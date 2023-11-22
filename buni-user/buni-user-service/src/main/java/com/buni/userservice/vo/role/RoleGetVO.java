package com.buni.userservice.vo.role;

import com.buni.buniframework.util.StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Data
public class RoleGetVO implements Serializable {

	/**
	 * id
	 */
	@JsonSerialize(using = StringSerializer.class)
	private Long id;

	/**
	 * 名字
	 */
	private String name;


}
