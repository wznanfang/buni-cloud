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
CREATE TABLE `sys_auth`  (
                             `id` bigint NOT NULL COMMENT 'id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
                             `client_identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户端标识',
                             `token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'token',
                             `enable` tinyint NULL DEFAULT 1 COMMENT '是否启用(0:否，1:是)',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户鉴权' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES (1706122677032734721, '2023-09-25 09:45:19', '2024-06-20 20:11:13', 1704017394743595009, 'PostmanRuntime/7.33.0', 'tQb05z4RUBVwUnOfCnHO22v1UTuVf0Aa', 1, 0);
INSERT INTO `sys_auth` VALUES (1706233227360305153, '2023-09-25 17:04:36', '2024-06-20 20:11:13', 1706232888619925506, 'PostmanRuntime/7.33.0', 'AIJwxo2GEBgxzwpSl1Xke8Cpsc9BgzKc', 1, 0);
INSERT INTO `sys_auth` VALUES (1706233990895599617, '2023-09-25 17:07:38', '2024-06-20 20:11:13', 1, 'PostmanRuntime/7.33.0', 'HhNIfTrsdXo4ixGUHmhxtqREP0FYSueb', 1, 0);
INSERT INTO `sys_auth` VALUES (1740200067123892225, '2023-12-28 10:36:42', '2024-06-20 20:11:13', 1, 'Apifox/1.0.0 (https://apifox.com)', '6uw3gZ5sAE7V48lmqLELKm63eJ4sYs8D', 1, 0);
INSERT INTO `sys_auth` VALUES (1804041829918982147, '2024-06-21 14:41:04', '2024-06-21 14:41:48', 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60', '5r6gcGKURpkUWxhw1PVfUEg3DoX4ASdp', 1, 0);

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
                                  `id` bigint NOT NULL COMMENT 'id',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
                                  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级id',
                                  `type` tinyint NULL DEFAULT NULL COMMENT '0：模块，1：菜单，2：按钮',
                                  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标识码',
                                  `sort` tinyint NULL DEFAULT NULL COMMENT '序号',
                                  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '接口url',
                                  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES (1, '2023-12-28 10:49:30', '2024-06-20 20:11:18', '用户退出', 0, 2, 'login', 1, '/v1/loginOut/POST', 0);
INSERT INTO `sys_authority` VALUES (2, '2023-09-25 14:23:04', '2024-06-21 14:22:34', '根据id查询用户信息', 0, 2, 'sysUser-details', 1, '/v1/findById/{}/GET,/v1/sysUser/GET', 0);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `id` bigint NOT NULL COMMENT 'id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
                             `dept_type` tinyint NULL DEFAULT NULL COMMENT '部门类型',
                             `parent_id` bigint NULL DEFAULT NULL COMMENT '父级id',
                             `leader_user_id` bigint NULL DEFAULT NULL COMMENT '负责人',
                             `enable` tinyint NULL DEFAULT 1 COMMENT '是否启用(0:否，1：是)',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
                            `id` bigint NOT NULL COMMENT 'id',
                            `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
                            `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `url_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `parameter` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                            `elapsed_time` bigint NULL DEFAULT NULL COMMENT '耗时',
                            `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1707311156597075970, '2023-09-28 16:27:55', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1707311186900922369, '2023-09-28 16:28:02', '2024-06-20 20:11:33', '不逆', 'http://192.168.7.110:10001/v1/findById/1', '/v1/findById/1', 'GET', '127.0.0.1', '1', '【用户模块】-查询用户详情', 1, 0);
INSERT INTO `sys_log` VALUES (1740200067123892226, '2023-12-28 10:36:42', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740200211890180097, '2023-12-28 10:37:17', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740200232823951362, '2023-12-28 10:37:22', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740200453549199361, '2023-12-28 10:38:14', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740201143268937730, '2023-12-28 10:40:59', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740201420000727042, '2023-12-28 10:42:05', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740203693716267009, '2023-12-28 10:51:07', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740204186526015489, '2023-12-28 10:53:04', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740204217945546753, '2023-12-28 10:53:12', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740204635635310594, '2023-12-28 10:54:51', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740205189618982913, '2023-12-28 10:57:03', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740205533988118530, '2023-12-28 10:58:25', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740205688820850690, '2023-12-28 10:59:02', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740206001602682881, '2023-12-28 11:00:17', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740206149833580546, '2023-12-28 11:00:52', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740206901582864385, '2023-12-28 11:03:51', '2024-06-20 20:11:33', '', 'http://192.168.7.110:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '用户登录', 1, 0);
INSERT INTO `sys_log` VALUES (1740206981069119489, '2023-12-28 11:04:10', '2024-06-20 20:11:33', '不逆', 'http://192.168.7.110:10001/v1/loginOut', '/v1/loginOut', 'POST', '0:0:0:0:0:0:0:1', NULL, '【用户模块】-退出登录', 1, 0);
INSERT INTO `sys_log` VALUES (1792788061667827714, '2024-05-21 13:22:37', '2024-06-20 20:11:33', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '【用户模块】-用户登录', 817, 0);
INSERT INTO `sys_log` VALUES (1792803620543369218, '2024-05-21 14:24:26', '2024-06-20 20:11:33', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '【用户模块】-用户登录', 796, 0);
INSERT INTO `sys_log` VALUES (1792808440876609539, '2024-05-21 14:43:36', '2024-06-20 20:11:33', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '【用户模块】-用户登录', 755, 0);
INSERT INTO `sys_log` VALUES (1792809043040251905, '2024-05-21 14:45:59', '2024-06-20 20:11:33', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '0:0:0:0:0:0:0:1', '{\"username\":\"不逆\",\"password\":\"123456\"}', '【用户模块】-用户登录', 11, 0);
INSERT INTO `sys_log` VALUES (1804024519862784001, '2024-06-21 13:32:17', '2024-06-21 13:32:17', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 870, 0);
INSERT INTO `sys_log` VALUES (1804024675567931394, '2024-06-21 13:32:54', '2024-06-21 13:32:54', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 28, 0);
INSERT INTO `sys_log` VALUES (1804028141631787010, '2024-06-21 13:46:41', '2024-06-21 13:46:41', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 1043, 0);
INSERT INTO `sys_log` VALUES (1804030141010374658, '2024-06-21 13:54:38', '2024-06-21 13:54:38', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 858, 0);
INSERT INTO `sys_log` VALUES (1804030313132027905, '2024-06-21 13:55:25', '2024-06-21 13:55:25', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 28, 0);
INSERT INTO `sys_log` VALUES (1804030580548268034, '2024-06-21 13:56:22', '2024-06-21 13:56:22', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 24, 0);
INSERT INTO `sys_log` VALUES (1804030951995830274, '2024-06-21 13:57:51', '2024-06-21 13:57:51', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 3722, 0);
INSERT INTO `sys_log` VALUES (1804031176365928450, '2024-06-21 13:58:44', '2024-06-21 13:58:44', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 12602, 0);
INSERT INTO `sys_log` VALUES (1804034779352743938, '2024-06-21 14:13:03', '2024-06-21 14:13:03', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 895, 0);
INSERT INTO `sys_log` VALUES (1804034835225067522, '2024-06-21 14:13:17', '2024-06-21 14:13:17', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 24, 0);
INSERT INTO `sys_log` VALUES (1804035849974005762, '2024-06-21 14:17:19', '2024-06-21 14:17:19', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 25, 0);
INSERT INTO `sys_log` VALUES (1804036248839733249, '2024-06-21 14:18:54', '2024-06-21 14:18:54', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 32556, 0);
INSERT INTO `sys_log` VALUES (1804036629137195010, '2024-06-21 14:20:24', '2024-06-21 14:20:24', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 3572, 0);
INSERT INTO `sys_log` VALUES (1804039601816698882, '2024-06-21 14:32:13', '2024-06-21 14:32:13', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 2391, 0);
INSERT INTO `sys_log` VALUES (1804040797562449922, '2024-06-21 14:36:58', '2024-06-21 14:36:58', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 865, 0);
INSERT INTO `sys_log` VALUES (1804041507507027970, '2024-06-21 14:39:48', '2024-06-21 14:39:48', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 868, 0);
INSERT INTO `sys_log` VALUES (1804041829918982146, '2024-06-21 14:41:04', '2024-06-21 14:41:04', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 25, 0);
INSERT INTO `sys_log` VALUES (1804041888026869761, '2024-06-21 14:41:18', '2024-06-21 14:41:18', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 38, 0);
INSERT INTO `sys_log` VALUES (1804042006079750145, '2024-06-21 14:41:46', '2024-06-21 14:41:46', '', 'http://192.168.0.70:10001/v1/login', '/v1/login', 'POST', '192.168.0.70', '{\"username\":\"buni\",\"password\":\"123456\"}', '【用户模块】-用户登录', 25, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint NOT NULL COMMENT 'id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名字',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色\r\n' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2023-09-25 14:22:32', '2024-06-20 20:11:23', '管理员', 0);

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority`  (
                                       `id` bigint NOT NULL COMMENT 'id',
                                       `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                       `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                       `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
                                       `authority_id` bigint NULL DEFAULT NULL COMMENT '权限id',
                                       `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES (1, '2023-09-25 14:24:10', '2024-06-20 20:11:28', 1, 1, 0);
INSERT INTO `sys_role_authority` VALUES (2, '2023-12-28 10:49:59', '2024-06-20 20:11:28', 1, 2, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint NOT NULL COMMENT 'id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
                             `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名字',
                             `age` tinyint NULL DEFAULT NULL COMMENT '年龄',
                             `sex` tinyint NULL DEFAULT NULL COMMENT '性别',
                             `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
                             `admin` tinyint NULL DEFAULT 0 COMMENT '是否是超级管理员(0:否，1：是)',
                             `enable` tinyint NULL DEFAULT 1 COMMENT '是否启用(0:否，1：是)',
                             `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2023-09-26 10:25:43', '2024-06-21 10:20:39', 'buni', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '不逆', 18, 1, '1', 0, 1, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `id` bigint NOT NULL COMMENT 'id',
                                  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
                                  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
                                  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '2023-09-25 14:22:42', '2024-06-20 20:11:43', 1, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
