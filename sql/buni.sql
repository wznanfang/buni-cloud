/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : buni

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 28/12/2023 11:06:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `client_identity` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端标识',
  `token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'token',
  `can_use` tinyint(4) NULL DEFAULT 1 COMMENT '是否能使用(0:否，1:是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户鉴权' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES (1706122677032734721, '2023-09-25 09:45:19', '2023-09-25 15:47:11', 1704017394743595009, 'PostmanRuntime/7.33.0', 'tQb05z4RUBVwUnOfCnHO22v1UTuVf0Aa', 1);
INSERT INTO `auth` VALUES (1706233227360305153, '2023-09-25 17:04:36', '2023-09-25 17:04:36', 1706232888619925506, 'PostmanRuntime/7.33.0', 'AIJwxo2GEBgxzwpSl1Xke8Cpsc9BgzKc', 1);
INSERT INTO `auth` VALUES (1706233990895599617, '2023-09-25 17:07:38', '2023-09-28 16:27:55', 1, 'PostmanRuntime/7.33.0', 'HhNIfTrsdXo4ixGUHmhxtqREP0FYSueb', 1);
INSERT INTO `auth` VALUES (1740200067123892225, '2023-12-28 10:36:42', '2023-12-28 11:03:51', 1, 'Apifox/1.0.0 (https://apifox.com)', '6uw3gZ5sAE7V48lmqLELKm63eJ4sYs8D', 1);

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0：模块，1：菜单，2：按钮',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标识码',
  `sort` tinyint(4) NULL DEFAULT NULL COMMENT '序号',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接口url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '2023-12-28 10:49:30', '2023-12-28 10:49:33', '用户退出', 0, 2, 'login', 1, '/v1/loginOut/POST');
INSERT INTO `authority` VALUES (2, '2023-09-25 14:23:04', '2023-09-25 14:23:06', '根据id查询用户信息', 0, 2, 'user-details', 1, '/v1/findById/{id}/GET,/v1/user/GET');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2023-09-25 14:22:32', '2023-09-25 14:22:34', '管理员');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `authority_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (1, '2023-09-25 14:24:10', '2023-09-25 14:24:12', 1, 1);
INSERT INTO `role_authority` VALUES (2, '2023-12-28 10:49:59', '2023-12-28 10:50:01', 1, 2);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `url_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `parameter` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `elapsed_time` bigint NULL DEFAULT NULL COMMENT '耗时',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1707311156597075970, '2023-09-28 16:27:55', '2023-09-28 16:27:55', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1707311186900922369, '2023-09-28 16:28:02', '2023-09-28 16:28:02', '不逆', 'http://192.168.7.110:10001/v1/findById/1', '/v1/findById/1', 'GET', '127.0.0.1', '1', '【用户模块】-查询用户详情',1);
INSERT INTO `sys_log` VALUES (1740200067123892226, '2023-12-28 10:36:42', '2023-12-28 10:36:42', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740200211890180097, '2023-12-28 10:37:17', '2023-12-28 10:37:17', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740200232823951362, '2023-12-28 10:37:22', '2023-12-28 10:37:22', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740200453549199361, '2023-12-28 10:38:14', '2023-12-28 10:38:14', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740201143268937730, '2023-12-28 10:40:59', '2023-12-28 10:40:59', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740201420000727042, '2023-12-28 10:42:05', '2023-12-28 10:42:05', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740203693716267009, '2023-12-28 10:51:07', '2023-12-28 10:51:07', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740204186526015489, '2023-12-28 10:53:04', '2023-12-28 10:53:04', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740204217945546753, '2023-12-28 10:53:12', '2023-12-28 10:53:12', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740204635635310594, '2023-12-28 10:54:51', '2023-12-28 10:54:51', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740205189618982913, '2023-12-28 10:57:03', '2023-12-28 10:57:03', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740205533988118530, '2023-12-28 10:58:25', '2023-12-28 10:58:25', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740205688820850690, '2023-12-28 10:59:02', '2023-12-28 10:59:02', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740206001602682881, '2023-12-28 11:00:17', '2023-12-28 11:00:17', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740206149833580546, '2023-12-28 11:00:52', '2023-12-28 11:00:52', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740206901582864385, '2023-12-28 11:03:51', '2023-12-28 11:03:51', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录',1);
INSERT INTO `sys_log` VALUES (1740206981069119489, '2023-12-28 11:04:10', '2023-12-28 11:04:10', '不逆', 'http://192.168.7.110:10001/v1/loginOut', '/v1/loginOut', 'POST', '0:0:0:0:0:0:0:1', NULL, '【用户模块】-退出登录',1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
  `age` tinyint(4) NULL DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `admin` tinyint(4) NULL DEFAULT 0 COMMENT '是否是超级管理员(0:否，1：是)',
  `enable` tinyint(4) NULL DEFAULT 1 COMMENT '是否启用(0:否，1：是)',
  `deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2023-09-26 10:25:43', '2023-09-26 10:25:43', '不逆', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '不逆', 18, 1, '1', 0, 1, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '2023-09-25 14:22:42', '2023-09-25 14:22:45', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
