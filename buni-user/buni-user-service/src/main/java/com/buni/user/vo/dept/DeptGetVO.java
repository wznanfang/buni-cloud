package com.buni.user.vo.dept;

import com.buni.framework.util.StringSerializer;
import com.buni.user.enums.AuthTypeEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zp.wei
 * @date 2023/11/3 16:54
 */
@Schema(description = "部门信息GetVO")
@Data
public class DeptGetVO implements Serializable {

	/**
	 * id
	 */
	@Schema(description = "id")
	@JsonSerialize(using = StringSerializer.class)
	private Long id;

	/**
	 * 创建时间
	 */
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
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
	private Long parentId;

	/**
	 * 负责人
	 */
	@Schema(description = "负责人")
	private Long leaderUserId;


}
