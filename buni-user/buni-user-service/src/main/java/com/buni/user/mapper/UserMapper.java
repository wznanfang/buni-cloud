package com.buni.user.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buni.user.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-09-19 10:52:51
 * @Entity generator.domain.User;
 */
public interface UserMapper extends BaseMapper<User> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from user where username = #{username} and deleted = 0")
    User findByUsername(String username);

}




