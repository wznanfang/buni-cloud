package com.buni.user.vo.authority;

import com.buni.user.enums.AuthTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "权限信息GetVO")
@Data
public class AuthorityGetVO implements Serializable {

	/**
	 * id
	 */
	@Schema(description = "id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;

	/**
	 * 创建时间
	 */
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;

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
