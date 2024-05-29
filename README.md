# buni-cloud，一个简单的springCloud基础框架
## 项目架构
> springCloud+nacos+dubbo+mybatis-plus+mysql+redis+minio+hutool+knife4j2
## 框架模块描述
1. buni-framework
> 基础框架模块——提供项目所需底层依赖以及异常拦截器，线程池，分布式锁，限流，缓存等全局相关工具类
2. buni-gateway
> 网关模块——权限校验等
3. buni-user
> 用户模块——用户管理,角色管理，权限管理，菜单管理
4. buni-file
> 文件模块——用于系统的全局文件上传，采用minio的oss存储