package com.buni.userservice.controller;


import com.buni.userservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @description 针对表【role(角色)】的数据库操作Controller
 * @createDate 2023-09-25 13:45:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class RoleController {

    private final RoleService roleService;



}
