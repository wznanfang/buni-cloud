/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : 127.0.0.1:3306
 Source Schema         : buni

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 11/02/2025 09:56:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth`
(
    `id`              bigint                                                 NOT NULL COMMENT 'id',
    `create_time`     datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                               NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`         bigint                                                 NULL DEFAULT NULL COMMENT '用户id',
    `client_identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端标识',
    `token`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT 'token',
    `enable`          tinyint                                                NULL DEFAULT 1 COMMENT '是否启用(0:否，1:是)',
    `deleted`         tinyint                                                NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户鉴权'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth`
VALUES (1706122677032734721, '2023-09-25 09:45:19', '2024-06-20 20:11:13', 1704017394743595009, 'PostmanRuntime/7.33.0', 'tQb05z4RUBVwUnOfCnHO22v1UTuVf0Aa', 1,
        0);
INSERT INTO `sys_auth`
VALUES (1706233227360305153, '2023-09-25 17:04:36', '2024-06-20 20:11:13', 1706232888619925506, 'PostmanRuntime/7.33.0', 'AIJwxo2GEBgxzwpSl1Xke8Cpsc9BgzKc', 1,
        0);
INSERT INTO `sys_auth`
VALUES (1706233990895599617, '2023-09-25 17:07:38', '2024-06-20 20:11:13', 1, 'PostmanRuntime/7.33.0', 'HhNIfTrsdXo4ixGUHmhxtqREP0FYSueb', 1, 0);
INSERT INTO `sys_auth`
VALUES (1740200067123892225, '2023-12-28 10:36:42', '2024-06-20 20:11:13', 1, 'Apifox/1.0.0 (https://apifox.com)', '6uw3gZ5sAE7V48lmqLELKm63eJ4sYs8D', 1, 0);
INSERT INTO `sys_auth`
VALUES (1804041829918982147, '2024-06-21 14:41:04', '2024-06-21 14:41:48', 1,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60',
        '5r6gcGKURpkUWxhw1PVfUEg3DoX4ASdp', 1, 0);

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`
(
    `id`          bigint                                                 NOT NULL COMMENT 'id',
    `create_time` datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                               NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '名字',
    `parent_id`   bigint                                                 NULL DEFAULT NULL COMMENT '父级id',
    `type`        tinyint                                                NULL DEFAULT NULL COMMENT '0：模块，1：菜单，2：按钮',
    `code`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '标识码',
    `sort`        tinyint                                                NULL DEFAULT NULL COMMENT '序号',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接口url',
    `deleted`     tinyint                                                NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '权限'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority`
VALUES (1, '2023-12-28 10:49:30', '2024-06-20 20:11:18', '用户退出', 0, 2, 'login', 1, '/v1/loginOut/POST', 0);
INSERT INTO `sys_authority`
VALUES (2, '2023-09-25 14:23:04', '2024-06-21 14:22:34', '根据id查询用户信息', 0, 2, 'sysUser-details', 1, '/v1/findById/{}/GET,/v1/sysUser/GET', 0);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`             bigint                                                 NOT NULL COMMENT 'id',
    `create_time`    datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime                                               NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
    `parent_id`      bigint                                                 NULL DEFAULT NULL COMMENT '父级id',
    `leader_user_id` bigint                                                 NULL DEFAULT NULL COMMENT '负责人',
    `deleted`        tinyint                                                NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '部门'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`           bigint                                                  NOT NULL COMMENT 'id',
    `create_time`  datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                                NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `username`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NULL DEFAULT NULL COMMENT '用户名',
    `url`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL,
    `url_path`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NULL DEFAULT NULL,
    `method`       varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NULL DEFAULT NULL,
    `ip`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL,
    `parameter`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
    `description`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin   NULL DEFAULT NULL,
    `elapsed_time` bigint                                                  NULL DEFAULT NULL COMMENT '耗时',
    `deleted`      tinyint                                                 NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '系统日志'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint                                                NOT NULL COMMENT 'id',
    `create_time` datetime                                              NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名字',
    `deleted`     tinyint                                               NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '角色\r\n'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '2023-09-25 14:22:32', '2024-06-20 20:11:23', '管理员', 0);

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority`
(
    `id`           bigint   NOT NULL COMMENT 'id',
    `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `role_id`      bigint   NULL DEFAULT NULL COMMENT '角色id',
    `authority_id` bigint   NULL DEFAULT NULL COMMENT '权限id',
    `deleted`      tinyint  NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '角色权限'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority`
VALUES (1, '2023-09-25 14:24:10', '2024-06-20 20:11:28', 1, 1, 0);
INSERT INTO `sys_role_authority`
VALUES (2, '2023-12-28 10:49:59', '2024-06-20 20:11:28', 1, 2, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL COMMENT 'id',
    `create_time` datetime                                               DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                               DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `wx_open_id`  varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '微信openId',
    `username`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户名',
    `open_id`     varchar(50) COLLATE utf8mb4_bin                        DEFAULT NULL COMMENT '微信openId',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名字',
    `birthday`    varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '生日',
    `age`         tinyint                                                DEFAULT NULL COMMENT '年龄',
    `gender`      tinyint                                                DEFAULT NULL COMMENT '性别',
    `phone`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '电话',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
    `admin`       tinyint                                                DEFAULT '0' COMMENT '是否是超级管理员(0:否，1：是)',
    `enable`      tinyint                                                DEFAULT '1' COMMENT '是否启用(0:否，1：是)',
    `deleted`     tinyint                                                DEFAULT '0' COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC COMMENT ='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, '2023-09-26 10:25:43', '2024-06-21 10:20:39', 'buni', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '不逆', 18, 1, '1', NULL,
        0, 1, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint   NOT NULL COMMENT 'id',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `user_id`     bigint   NULL DEFAULT NULL COMMENT '用户id',
    `role_id`     bigint   NULL DEFAULT NULL COMMENT '角色id',
    `deleted`     tinyint  NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '用户角色'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, '2023-09-25 14:22:42', '2024-06-20 20:11:43', 1, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
