package com.buni.user.vo.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buni.user.entity.User;
import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/21 15:39
 */
@Data
public class PageVO extends Page<User> {


    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

}
