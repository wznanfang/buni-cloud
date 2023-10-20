package com.buni.userservice.vo.user;

import com.buni.usercommon.enums.SexEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 10:41
 */
@Data
public class UpdateVO {

    /**
     * 用户id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 年龄
     */
    private Integer age;


}
