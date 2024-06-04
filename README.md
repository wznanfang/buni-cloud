<h1 align="center">不逆-基础开发框架</h1>
    <p align="center">
    	<img src ="https://img.shields.io/github/languages/code-size/wznanfang/buni-cloud" alt="codesize"/>
    	<img src="https://img.shields.io/badge/SpringBoot-3.3.0-brightgreen" alt="SpringBoot"/>
    	<img src="https://img.shields.io/github/languages/count/wznanfang/buni-cloud" alt="languages"/>
        <img src="https://img.shields.io/badge/Java-17-skyblue" alt="Java"/>
    	<img src="https://img.shields.io/github/last-commit/wznanfang/buni-cloud" alt="lastcommit"/><br>
    	<img src="https://img.shields.io/badge/Author-wznanfang-orange" alt="Author"/>
    </p>
<hr>


## 简介
>buni-cloud —— 后端基础框架，用于框架快速搭建，功能快速开发
## 项目架构
> SpringCloud+Maven+Nacos+Dubbo+Mybatis-Plus+Mysql+Redis+Minio+Hutool+Knife4j2
## 框架模块描述
1. buni-framework
> 基础框架模块——提供项目所需底层依赖以及异常拦截器，线程池，分布式锁，限流，缓存等全局相关工具类
2. buni-gateway
> 网关模块——权限校验等
3. buni-user
> 用户模块——用户管理,角色管理，权限管理，菜单管理
4. buni-file
> 文件模块——用于系统的全局文件上传，采用minio的oss存储
