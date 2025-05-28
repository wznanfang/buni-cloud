/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : buni

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 27/05/2025 20:54:01
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
INSERT INTO `sys_auth` VALUES (1891828505459355650, '2025-02-18 20:33:59', '2025-02-18 20:33:59', 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36 Edg/133.0.0.0', 'hdTxVEp7YDNAJgxFzNjZp2KNBev9AA3D', 1, 0);
INSERT INTO `sys_auth` VALUES (1920101961397235715, '2025-05-07 21:02:36', '2025-05-07 21:02:36', 1920101958557691906, 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/21A360 Safari/604.1 wechatdevtools/1.06.2412050 MicroMessenger/8.0.5 Language/zh_CN webview/', 'd8AFDNZsyAZn1BMlKHciBQl5GI8MgYs6', 1, 0);
INSERT INTO `sys_auth` VALUES (1920465127553744898, '2025-05-08 21:05:42', '2025-05-08 21:05:42', 1920465127511801858, 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/21A360 Safari/604.1 wechatdevtools/1.06.2412050 MicroMessenger/8.0.5 Language/zh_CN webview/', 'ZOr0HHrFFfFlaW5QB6pBLa3vSoB3D6DH', 1, 0);
INSERT INTO `sys_auth` VALUES (1925524730826002435, '2025-05-22 20:10:45', '2025-05-22 20:10:45', 1925524729576099841, 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/21A360 Safari/604.1 wechatdevtools/1.06.2412050 MicroMessenger/8.0.5 Language/zh_CN webview/', '3SjeBfDYWV0RsaBZIe16c5q5ttp5ID4t', 1, 0);
INSERT INTO `sys_auth` VALUES (1925527112850907139, '2025-05-22 20:20:13', '2025-05-22 20:20:13', 1925527111479369730, 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0.1 Mobile/21A360 Safari/604.1 wechatdevtools/1.06.2412050 MicroMessenger/8.0.5 Language/zh_CN webview/', 'Wcgnc9uGcRi4BJIIJ5k04Eej4ZqVO3ph', 1, 0);

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
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级id',
  `leader_user_id` bigint NULL DEFAULT NULL COMMENT '负责人',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_log` VALUES (1891828417810984961, '2025-02-18 20:33:38', '2025-02-18 20:33:38', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"PZLVF4e7p4MCG0SsC5nOy6vcJpfNNqE+FqqtJs60RZk=\"}', '【用户模块】-用户登录', 15, 0);
INSERT INTO `sys_log` VALUES (1891828505459355649, '2025-02-18 20:33:59', '2025-02-18 20:33:59', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 67, 0);
INSERT INTO `sys_log` VALUES (1891829594502000641, '2025-02-18 20:38:18', '2025-02-18 20:38:18', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 20, 0);
INSERT INTO `sys_log` VALUES (1891829828225396737, '2025-02-18 20:39:14', '2025-02-18 20:39:14', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 15, 0);
INSERT INTO `sys_log` VALUES (1892922329329864706, '2025-02-21 21:00:27', '2025-02-21 21:00:27', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1377, 0);
INSERT INTO `sys_log` VALUES (1892924243685707777, '2025-02-21 21:08:03', '2025-02-21 21:08:03', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 60117, 0);
INSERT INTO `sys_log` VALUES (1892926713874911233, '2025-02-21 21:17:52', '2025-02-21 21:17:52', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 9631, 0);
INSERT INTO `sys_log` VALUES (1892926820028551170, '2025-02-21 21:18:17', '2025-02-21 21:18:17', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 25, 0);
INSERT INTO `sys_log` VALUES (1892927335722500098, '2025-02-21 21:20:20', '2025-02-21 21:20:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1326, 0);
INSERT INTO `sys_log` VALUES (1892929508514566145, '2025-02-21 21:28:58', '2025-02-21 21:28:58', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1174, 0);
INSERT INTO `sys_log` VALUES (1892930380921393153, '2025-02-21 21:32:26', '2025-02-21 21:32:26', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1246, 0);
INSERT INTO `sys_log` VALUES (1892933259067682818, '2025-02-21 21:43:53', '2025-02-21 21:43:53', '', 'http://127.0.0.1:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1425, 0);
INSERT INTO `sys_log` VALUES (1892934053856608257, '2025-02-21 21:47:02', '2025-02-21 21:47:02', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1707, 0);
INSERT INTO `sys_log` VALUES (1894354198651015169, '2025-02-25 19:50:11', '2025-02-25 19:50:11', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1292, 0);
INSERT INTO `sys_log` VALUES (1894357261524668418, '2025-02-25 20:02:21', '2025-02-25 20:02:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1250, 0);
INSERT INTO `sys_log` VALUES (1894364946886352897, '2025-02-25 20:32:54', '2025-02-25 20:32:54', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1229, 0);
INSERT INTO `sys_log` VALUES (1894365229465001985, '2025-02-25 20:34:01', '2025-02-25 20:34:01', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1894365379985989634, '2025-02-25 20:34:37', '2025-02-25 20:34:37', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 16, 0);
INSERT INTO `sys_log` VALUES (1894365673906036738, '2025-02-25 20:35:47', '2025-02-25 20:35:47', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 16, 0);
INSERT INTO `sys_log` VALUES (1894365845947998209, '2025-02-25 20:36:28', '2025-02-25 20:36:28', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 11, 0);
INSERT INTO `sys_log` VALUES (1894366820083490818, '2025-02-25 20:40:20', '2025-02-25 20:40:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1894366845568081922, '2025-02-25 20:40:26', '2025-02-25 20:40:26', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 24, 0);
INSERT INTO `sys_log` VALUES (1894366848567009281, '2025-02-25 20:40:27', '2025-02-25 20:40:27', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 29, 0);
INSERT INTO `sys_log` VALUES (1894366849544282113, '2025-02-25 20:40:27', '2025-02-25 20:40:27', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 20, 0);
INSERT INTO `sys_log` VALUES (1894366850387337217, '2025-02-25 20:40:27', '2025-02-25 20:40:27', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 18, 0);
INSERT INTO `sys_log` VALUES (1894366852086030337, '2025-02-25 20:40:28', '2025-02-25 20:40:28', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 18, 0);
INSERT INTO `sys_log` VALUES (1894366853000388609, '2025-02-25 20:40:28', '2025-02-25 20:40:28', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894367894634811393, '2025-02-25 20:44:36', '2025-02-25 20:44:36', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1586, 0);
INSERT INTO `sys_log` VALUES (1894367906789904386, '2025-02-25 20:44:39', '2025-02-25 20:44:39', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 32, 0);
INSERT INTO `sys_log` VALUES (1894367907649736706, '2025-02-25 20:44:40', '2025-02-25 20:44:40', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 35, 0);
INSERT INTO `sys_log` VALUES (1894367908429877250, '2025-02-25 20:44:40', '2025-02-25 20:44:40', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 26, 0);
INSERT INTO `sys_log` VALUES (1894367909079994369, '2025-02-25 20:44:40', '2025-02-25 20:44:40', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 24, 0);
INSERT INTO `sys_log` VALUES (1894367910451531777, '2025-02-25 20:44:40', '2025-02-25 20:44:40', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 32, 0);
INSERT INTO `sys_log` VALUES (1894367911114231810, '2025-02-25 20:44:40', '2025-02-25 20:44:40', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 21, 0);
INSERT INTO `sys_log` VALUES (1894367911902760961, '2025-02-25 20:44:41', '2025-02-25 20:44:41', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 28, 0);
INSERT INTO `sys_log` VALUES (1894369065785151489, '2025-02-25 20:49:16', '2025-02-25 20:49:16', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 22, 0);
INSERT INTO `sys_log` VALUES (1894369073443950594, '2025-02-25 20:49:17', '2025-02-25 20:49:17', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369076849725442, '2025-02-25 20:49:18', '2025-02-25 20:49:18', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 16, 0);
INSERT INTO `sys_log` VALUES (1894369077634060290, '2025-02-25 20:49:18', '2025-02-25 20:49:18', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369078418395137, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 20, 0);
INSERT INTO `sys_log` VALUES (1894369079198535682, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 20, 0);
INSERT INTO `sys_log` VALUES (1894369079857041409, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1894369080582656002, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 29, 0);
INSERT INTO `sys_log` VALUES (1894369081245356034, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1894369081899667458, '2025-02-25 20:49:19', '2025-02-25 20:49:19', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 21, 0);
INSERT INTO `sys_log` VALUES (1894369082612699138, '2025-02-25 20:49:20', '2025-02-25 20:49:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 18, 0);
INSERT INTO `sys_log` VALUES (1894369083258621954, '2025-02-25 20:49:20', '2025-02-25 20:49:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369083980042241, '2025-02-25 20:49:20', '2025-02-25 20:49:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369084634353665, '2025-02-25 20:49:20', '2025-02-25 20:49:20', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 13, 0);
INSERT INTO `sys_log` VALUES (1894369086790225921, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 22, 0);
INSERT INTO `sys_log` VALUES (1894369087578755074, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369088228872193, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 14, 0);
INSERT INTO `sys_log` VALUES (1894369088878989313, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 13, 0);
INSERT INTO `sys_log` VALUES (1894369089600409602, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 14, 0);
INSERT INTO `sys_log` VALUES (1894369090313441282, '2025-02-25 20:49:21', '2025-02-25 20:49:21', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1894369090967752706, '2025-02-25 20:49:22', '2025-02-25 20:49:22', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 17, 0);
INSERT INTO `sys_log` VALUES (1894369091689172993, '2025-02-25 20:49:22', '2025-02-25 20:49:22', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 20, 0);
INSERT INTO `sys_log` VALUES (1894369092343484418, '2025-02-25 20:49:22', '2025-02-25 20:49:22', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 18, 0);
INSERT INTO `sys_log` VALUES (1894369092934881282, '2025-02-25 20:49:22', '2025-02-25 20:49:22', '', 'http://192.168.1.10:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1905933768671924226, '2025-03-29 18:43:16', '2025-03-29 18:43:16', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1235, 0);
INSERT INTO `sys_log` VALUES (1905933848015572994, '2025-03-29 18:43:35', '2025-03-29 18:43:35', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 33, 0);
INSERT INTO `sys_log` VALUES (1905933897487388674, '2025-03-29 18:43:46', '2025-03-29 18:43:46', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 15, 0);
INSERT INTO `sys_log` VALUES (1905934006035976193, '2025-03-29 18:44:12', '2025-03-29 18:44:12', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 15, 0);
INSERT INTO `sys_log` VALUES (1905934437264953346, '2025-03-29 18:45:55', '2025-03-29 18:45:55', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 10, 0);
INSERT INTO `sys_log` VALUES (1905934770187833345, '2025-03-29 18:47:14', '2025-03-29 18:47:14', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 10, 0);
INSERT INTO `sys_log` VALUES (1905934927948189698, '2025-03-29 18:47:52', '2025-03-29 18:47:52', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 11, 0);
INSERT INTO `sys_log` VALUES (1905935500609097729, '2025-03-29 18:50:09', '2025-03-29 18:50:09', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 22, 0);
INSERT INTO `sys_log` VALUES (1905936401814032385, '2025-03-29 18:53:43', '2025-03-29 18:53:43', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 9, 0);
INSERT INTO `sys_log` VALUES (1905936792429563906, '2025-03-29 18:55:17', '2025-03-29 18:55:17', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 11, 0);
INSERT INTO `sys_log` VALUES (1905936991705141249, '2025-03-29 18:56:04', '2025-03-29 18:56:04', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 5, 0);
INSERT INTO `sys_log` VALUES (1905938270867255297, '2025-03-29 19:01:09', '2025-03-29 19:01:09', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 1232, 0);
INSERT INTO `sys_log` VALUES (1905941110356537345, '2025-03-29 19:12:26', '2025-03-29 19:12:26', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"111\",\"password\":\"111\",\"name\":\"111\",\"sex\":1,\"age\":11,\"tel\":\"11\",\"enable\":0}', '【用户模块】-新增用户', 4, 0);
INSERT INTO `sys_log` VALUES (1905941195375079425, '2025-03-29 19:12:46', '2025-03-29 19:12:46', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"111\",\"password\":\"111\",\"name\":\"111\",\"sex\":1,\"age\":11,\"tel\":\"17675210797\",\"enable\":0}', '【用户模块】-新增用户', 16, 0);
INSERT INTO `sys_log` VALUES (1905942843380027394, '2025-03-29 19:19:19', '2025-03-29 19:19:19', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"111\",\"password\":\"111\",\"name\":\"驱蚊器无\",\"sex\":1,\"age\":1111,\"tel\":\"13588388925\",\"enable\":0}', '【用户模块】-新增用户', 910, 0);
INSERT INTO `sys_log` VALUES (1905943638473265154, '2025-03-29 19:22:29', '2025-03-29 19:22:29', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"1111\",\"password\":\"2jxm7KumH5WnQ7rX4dhpXQ==\",\"name\":\"1111\",\"sex\":1,\"age\":111,\"tel\":\"19332761519\",\"enable\":0}', '【用户模块】-新增用户', 43, 0);
INSERT INTO `sys_log` VALUES (1905943962508414978, '2025-03-29 19:23:46', '2025-03-29 19:23:46', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"2222\",\"password\":\"R66lWn6JPXjAV0B/Otbulw==\",\"name\":\"22222\",\"sex\":1,\"age\":222,\"tel\":\"15381672844\",\"enable\":0}', '【用户模块】-新增用户', 139, 0);
INSERT INTO `sys_log` VALUES (1905944034960822274, '2025-03-29 19:24:03', '2025-03-29 19:24:03', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"2222\",\"password\":\"9j4xKF/cxTKGmJBrrXKR6sLkujEMn+YJL/lAnX5d6Lg=\",\"name\":\"22222\",\"sex\":1,\"age\":22,\"tel\":\"15381672844\",\"enable\":0}', '【用户模块】-新增用户', 9, 0);
INSERT INTO `sys_log` VALUES (1905944580153233409, '2025-03-29 19:26:13', '2025-03-29 19:26:13', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"qwqe\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\",\"name\":\"王企鹅群无\",\"sex\":1,\"age\":12,\"tel\":\"15672921811\",\"enable\":0}', '【用户模块】-新增用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905958954574106625, '2025-03-29 20:23:20', '2025-03-29 20:23:20', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 19, 0);
INSERT INTO `sys_log` VALUES (1905959384142139393, '2025-03-29 20:25:03', '2025-03-29 20:25:03', '', 'http://192.168.31.236:10001/v1/login', '/v1/login', 'POST', '127.0.0.1', '{\"username\":\"buni\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\"}', '【用户模块】-用户登录', 5, 0);
INSERT INTO `sys_log` VALUES (1905961281519439873, '2025-03-29 20:32:35', '2025-03-29 20:32:35', 'buni', 'http://192.168.31.236:10001/v1/user/1905943638473265153', '/v1/user/1905943638473265153', 'DELETE', '127.0.0.1', '1905943638473265153', '【用户模块】-删除用户', 31, 0);
INSERT INTO `sys_log` VALUES (1905961663284989953, '2025-03-29 20:34:06', '2025-03-29 20:34:06', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905961667252801537, '2025-03-29 20:34:07', '2025-03-29 20:34:07', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 10, 0);
INSERT INTO `sys_log` VALUES (1905961704531775489, '2025-03-29 20:34:16', '2025-03-29 20:34:16', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905961729689210881, '2025-03-29 20:34:22', '2025-03-29 20:34:22', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 9, 0);
INSERT INTO `sys_log` VALUES (1905961732449062914, '2025-03-29 20:34:23', '2025-03-29 20:34:23', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905961735796117505, '2025-03-29 20:34:24', '2025-03-29 20:34:24', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 20, 0);
INSERT INTO `sys_log` VALUES (1905961746030219265, '2025-03-29 20:34:26', '2025-03-29 20:34:26', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 11, 0);
INSERT INTO `sys_log` VALUES (1905961749494714369, '2025-03-29 20:34:27', '2025-03-29 20:34:27', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905961751524757505, '2025-03-29 20:34:27', '2025-03-29 20:34:27', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 15, 0);
INSERT INTO `sys_log` VALUES (1905961752510418945, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 9, 0);
INSERT INTO `sys_log` VALUES (1905961753366056961, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905961754234277890, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 11, 0);
INSERT INTO `sys_log` VALUES (1905961755052167170, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905961755832307713, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905961756604059650, '2025-03-29 20:34:28', '2025-03-29 20:34:28', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905961938506829825, '2025-03-29 20:35:12', '2025-03-29 20:35:12', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905961995608084481, '2025-03-29 20:35:25', '2025-03-29 20:35:25', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905962048565366786, '2025-03-29 20:35:38', '2025-03-29 20:35:38', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962051308441601, '2025-03-29 20:35:39', '2025-03-29 20:35:39', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 13, 0);
INSERT INTO `sys_log` VALUES (1905962052268937218, '2025-03-29 20:35:39', '2025-03-29 20:35:39', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 10, 0);
INSERT INTO `sys_log` VALUES (1905962053019717634, '2025-03-29 20:35:39', '2025-03-29 20:35:39', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962053875355650, '2025-03-29 20:35:39', '2025-03-29 20:35:39', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962054651301889, '2025-03-29 20:35:40', '2025-03-29 20:35:40', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905962055427248129, '2025-03-29 20:35:40', '2025-03-29 20:35:40', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905962056895254529, '2025-03-29 20:35:40', '2025-03-29 20:35:40', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905962057667006466, '2025-03-29 20:35:40', '2025-03-29 20:35:40', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":0}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962058380038146, '2025-03-29 20:35:40', '2025-03-29 20:35:40', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944580086124546,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905962061357993986, '2025-03-29 20:35:41', '2025-03-29 20:35:41', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962062133940226, '2025-03-29 20:35:41', '2025-03-29 20:35:41', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962062918275073, '2025-03-29 20:35:42', '2025-03-29 20:35:42', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962065246113793, '2025-03-29 20:35:42', '2025-03-29 20:35:42', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905962066034642946, '2025-03-29 20:35:42', '2025-03-29 20:35:42', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905962066760257537, '2025-03-29 20:35:42', '2025-03-29 20:35:42', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962069247479810, '2025-03-29 20:35:43', '2025-03-29 20:35:43', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962069973094402, '2025-03-29 20:35:43', '2025-03-29 20:35:43', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962080089755649, '2025-03-29 20:35:46', '2025-03-29 20:35:46', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962080995725314, '2025-03-29 20:35:46', '2025-03-29 20:35:46', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962081788448769, '2025-03-29 20:35:46', '2025-03-29 20:35:46', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962082560200705, '2025-03-29 20:35:46', '2025-03-29 20:35:46', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905962083290009601, '2025-03-29 20:35:46', '2025-03-29 20:35:46', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 5, 0);
INSERT INTO `sys_log` VALUES (1905962084141453313, '2025-03-29 20:35:47', '2025-03-29 20:35:47', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962084913205250, '2025-03-29 20:35:47', '2025-03-29 20:35:47', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962086423154689, '2025-03-29 20:35:47', '2025-03-29 20:35:47', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905962087211683841, '2025-03-29 20:35:47', '2025-03-29 20:35:47', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 14, 0);
INSERT INTO `sys_log` VALUES (1905962087924715521, '2025-03-29 20:35:47', '2025-03-29 20:35:47', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 8, 0);
INSERT INTO `sys_log` VALUES (1905962090814590978, '2025-03-29 20:35:48', '2025-03-29 20:35:48', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":0}', '【用户模块】-启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905962092194516994, '2025-03-29 20:35:49', '2025-03-29 20:35:49', 'buni', 'http://192.168.31.236:10001/v1/user/forbidden', '/v1/user/forbidden', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"enable\":1}', '【用户模块】-启用-禁用用户', 7, 0);
INSERT INTO `sys_log` VALUES (1905965146050482179, '2025-03-29 20:47:57', '2025-03-29 20:47:57', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"12312\",\"password\":\"TioQtsXcWeaD1Q6NvXNjpg==\",\"name\":\"1111\",\"sex\":1,\"age\":11,\"tel\":\"13943643848\",\"enable\":0}', '【用户模块】-新增用户', 10, 0);
INSERT INTO `sys_log` VALUES (1905967462589747203, '2025-03-29 20:57:09', '2025-03-29 20:57:09', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'POST', '127.0.0.1', '{\"username\":\"其味无穷二\",\"password\":\"hcwcfsPBG6YTO/Ksi7v9cQ==\",\"name\":\"去问问群无\",\"sex\":1,\"age\":22,\"tel\":\"15132844569\",\"enable\":0}', '【用户模块】-新增用户', 9, 0);
INSERT INTO `sys_log` VALUES (1905972324220096514, '2025-03-29 21:16:28', '2025-03-29 21:16:28', 'buni', 'http://192.168.31.236:10001/v1/user', '/v1/user', 'PUT', '127.0.0.1', '{\"id\":1905944034939850753,\"name\":\"1111\",\"sex\":1,\"age\":22,\"tel\":\"15381672844\"}', '【用户模块】-编辑用户', 11, 0);
INSERT INTO `sys_log` VALUES (1905973471538401282, '2025-03-29 21:21:02', '2025-03-29 21:21:02', 'buni', 'http://192.168.31.236:10001/v1/user/batchEnable', '/v1/user/batchEnable', 'PUT', '127.0.0.1', '{\"idVOs\":{\"ids\":[1905944034939850753,1905944580086124546,1905965146050482178,1905967462589747202]},\"enable\":1}', '【用户模块】-批量启用-禁用用户', 14, 0);
INSERT INTO `sys_log` VALUES (1905973483542499330, '2025-03-29 21:21:04', '2025-03-29 21:21:04', 'buni', 'http://192.168.31.236:10001/v1/user/batchEnable', '/v1/user/batchEnable', 'PUT', '127.0.0.1', '{\"idVOs\":{\"ids\":[1905944034939850753,1905944580086124546,1905965146050482178,1905967462589747202]},\"enable\":0}', '【用户模块】-批量启用-禁用用户', 6, 0);
INSERT INTO `sys_log` VALUES (1905973495366242305, '2025-03-29 21:21:07', '2025-03-29 21:21:07', 'buni', 'http://192.168.31.236:10001/v1/user/batchEnable', '/v1/user/batchEnable', 'PUT', '127.0.0.1', '{\"idVOs\":{\"ids\":[1905944034939850753,1905944580086124546,1905965146050482178,1905967462589747202]},\"enable\":1}', '【用户模块】-批量启用-禁用用户', 10, 0);
INSERT INTO `sys_log` VALUES (1917122826819395585, '2025-04-29 15:44:35', '2025-04-29 15:44:35', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1y821w3Ta5Q43oRL0w3Nt42i2y821K\"', '【用户模块】-微信登录', 29142, 0);
INSERT INTO `sys_log` VALUES (1917123146509238274, '2025-04-29 15:45:51', '2025-04-29 15:45:51', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1lWIll2ujovf4LIZnl2SnR4O0lWIlq\"', '【用户模块】-微信登录', 5315, 0);
INSERT INTO `sys_log` VALUES (1917124579019239426, '2025-04-29 15:51:33', '2025-04-29 15:51:33', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1F2HGa1SwJvJ0Xm6Ga1uJWnN1F2HGz\"', '【用户模块】-微信登录', 885, 0);
INSERT INTO `sys_log` VALUES (1917124821483565058, '2025-04-29 15:52:31', '2025-04-29 15:52:31', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1pKA1w3GTDQ43Vv92w3UMUfs2pKA1B\"', '【用户模块】-微信登录', 394, 0);
INSERT INTO `sys_log` VALUES (1917125155144646658, '2025-04-29 15:53:50', '2025-04-29 15:53:50', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1lMj1w3rLmQ43F304w3EkPEv2lMj1k\"', '【用户模块】-微信登录', 973, 0);
INSERT INTO `sys_log` VALUES (1917126152151678977, '2025-04-29 15:57:48', '2025-04-29 15:57:48', '', 'http://192.168.1.10:10001/v1/loginOut', '/v1/loginOut', 'POST', '127.0.0.1', NULL, '【用户模块】-退出登录', 263, 0);
INSERT INTO `sys_log` VALUES (1917126243843358721, '2025-04-29 15:58:10', '2025-04-29 15:58:10', '', 'http://192.168.1.10:10001/v1/loginOut', '/v1/loginOut', 'POST', '127.0.0.1', NULL, '【用户模块】-退出登录', 5029, 0);
INSERT INTO `sys_log` VALUES (1917127121853800450, '2025-04-29 16:01:39', '2025-04-29 16:01:39', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1cIDkl2tAjuf41j5nl2vNSYn0cIDk4\"', '【用户模块】-微信登录', 855, 0);
INSERT INTO `sys_log` VALUES (1917129672477188098, '2025-04-29 16:11:47', '2025-04-29 16:11:47', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e18hIGa1bGKvJ0my6Ha1FtFmc28hIGp\"', '【用户模块】-微信登录', 1474, 0);
INSERT INTO `sys_log` VALUES (1917129709542252546, '2025-04-29 16:11:56', '2025-04-29 16:11:56', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1FGe0w3A5hP43OS21w3YQlCX1FGe0k\"', '【用户模块】-微信登录', 395, 0);
INSERT INTO `sys_log` VALUES (1917129817449111553, '2025-04-29 16:12:22', '2025-04-29 16:12:22', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1A9TFa1XwVuJ0uESIa15pwwT2A9TFb\"', '【用户模块】-微信登录', 11484, 0);
INSERT INTO `sys_log` VALUES (1917184238631583746, '2025-04-29 19:48:37', '2025-04-29 19:48:37', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1bHGll2GL4vf4eSjol22vRtb0bHGl3\"', '【用户模块】-微信登录', 1668, 0);
INSERT INTO `sys_log` VALUES (1917184420492410882, '2025-04-29 19:49:20', '2025-04-29 19:49:20', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b14NXll2JUlvf42VZkl27gHeb24NXl1\"', '【用户模块】-微信登录', 338, 0);
INSERT INTO `sys_log` VALUES (1917184455347077121, '2025-04-29 19:49:28', '2025-04-29 19:49:28', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1zKGll2OR4vf4JIqnl2yoqFA3zKGlz\"', '【用户模块】-微信登录', 376, 0);
INSERT INTO `sys_log` VALUES (1917188757197225986, '2025-04-29 20:06:34', '2025-04-29 20:06:34', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1EZs0w3ZraP43qn51w3BrvWC3EZs0X\"', '【用户模块】-微信登录', 368, 0);
INSERT INTO `sys_log` VALUES (1917191672360140802, '2025-04-29 20:18:09', '2025-04-29 20:18:09', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d14Exml2cwRvf4lSQkl2vDtLf24Exm-\"', '【用户模块】-微信登录', 399, 0);
INSERT INTO `sys_log` VALUES (1917208658435702786, '2025-04-29 21:25:39', '2025-04-29 21:25:39', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1wIZZv3WM5P43dM11w3uygLY1wIZZd\"', '【用户模块】-微信登录', 386, 0);
INSERT INTO `sys_log` VALUES (1917208682167074817, '2025-04-29 21:25:45', '2025-04-29 21:25:45', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c13Ym1w3N1tQ434Dk1w3xqqtn43Ym1G\"', '【用户模块】-微信登录', 322, 0);
INSERT INTO `sys_log` VALUES (1917208850471911425, '2025-04-29 21:26:25', '2025-04-29 21:26:25', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1tRx0w3rQDP4362ZZv3zzleP0tRx0O\"', '【用户模块】-微信登录', 344, 0);
INSERT INTO `sys_log` VALUES (1917208955820244993, '2025-04-29 21:26:50', '2025-04-29 21:26:50', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d18Z51w3JXbQ43qIYZv3ad3mX38Z51X\"', '【用户模块】-微信登录', 326, 0);
INSERT INTO `sys_log` VALUES (1917208975294398465, '2025-04-29 21:26:54', '2025-04-29 21:26:54', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1kQg0w3fLmP43YJU3w3UL0Mz1kQg02\"', '【用户模块】-微信登录', 224, 0);
INSERT INTO `sys_log` VALUES (1917208993170522114, '2025-04-29 21:26:59', '2025-04-29 21:26:59', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d19Evll2zaevf4B8Dml2fsdEZ39Evl6\"', '【用户模块】-微信登录', 214, 0);
INSERT INTO `sys_log` VALUES (1917209187765256194, '2025-04-29 21:27:45', '2025-04-29 21:27:45', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1yWx0w3YLDP43rdT2w3QCn2s4yWx04\"', '【用户模块】-微信登录', 314, 0);
INSERT INTO `sys_log` VALUES (1917210927566749697, '2025-04-29 21:34:40', '2025-04-29 21:34:40', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1zFnFa1cvnuJ0wglGa1DoH4Y2zFnFx\"', '【用户模块】-微信登录', 450, 0);
INSERT INTO `sys_log` VALUES (1920083570372415490, '2025-05-07 19:49:31', '2025-05-07 19:49:31', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c12fXZv3YUXR43gk00w374c3i22fXZk\"', '【用户模块】-微信登录', 1917, 0);
INSERT INTO `sys_log` VALUES (1920083933565587457, '2025-05-07 19:50:58', '2025-05-07 19:50:58', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c185Vkl2s90xf4hHUml2Bup4d185VkC\"', '【用户模块】-微信登录', 394, 0);
INSERT INTO `sys_log` VALUES (1920085382479515649, '2025-05-07 19:56:43', '2025-05-07 19:56:43', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1UqVkl2ElZwf4KOKkl2xZerP3UqVkH\"', '【用户模块】-微信登录', 365, 0);
INSERT INTO `sys_log` VALUES (1920085594379948034, '2025-05-07 19:57:34', '2025-05-07 19:57:34', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1zbCFa1pu3xJ0N3BHa1KqS8c4zbCFM\"', '【用户模块】-微信登录', 368, 0);
INSERT INTO `sys_log` VALUES (1920085656472424450, '2025-05-07 19:57:49', '2025-05-07 19:57:49', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1L8a2w3aLKP4300U3w3cGQqg1L8a2M\"', '【用户模块】-微信登录', 352, 0);
INSERT INTO `sys_log` VALUES (1920091089559969793, '2025-05-07 20:19:24', '2025-05-07 20:19:24', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b13ax0w313qR43T2U3w30cvHl03ax0I\"', '【用户模块】-微信登录', 1709, 0);
INSERT INTO `sys_log` VALUES (1920092555947331586, '2025-05-07 20:25:14', '2025-05-07 20:25:14', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1azO0w3rm9R43zE12w31rELT0azO0D\"', '【用户模块】-微信登录', 1545, 0);
INSERT INTO `sys_log` VALUES (1920093524978356225, '2025-05-07 20:29:05', '2025-05-07 20:29:05', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1I6EFa1La5xJ02iMHa1vWB751I6EFy\"', '【用户模块】-微信登录', 405, 0);
INSERT INTO `sys_log` VALUES (1920093998922125314, '2025-05-07 20:30:58', '2025-05-07 20:30:58', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1TRx0w3rBqR43HV82w3oRUE91TRx0G\"', '【用户模块】-微信登录', 398, 0);
INSERT INTO `sys_log` VALUES (1920094558995927041, '2025-05-07 20:33:11', '2025-05-07 20:33:11', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1rcE1w3BexS43NHu0w3ubNNL0rcE1K\"', '【用户模块】-微信登录', 349, 0);
INSERT INTO `sys_log` VALUES (1920095939433631746, '2025-05-07 20:38:40', '2025-05-07 20:38:40', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c12r61w3TlZS43QZS2w3DUfoy32r61F\"', '【用户模块】-微信登录', 1651, 0);
INSERT INTO `sys_log` VALUES (1920096311065743362, '2025-05-07 20:40:09', '2025-05-07 20:40:09', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c132Hkl2pvcxf4831ll2qL6I6232Hks\"', '【用户模块】-微信登录', 338, 0);
INSERT INTO `sys_log` VALUES (1920096550505975810, '2025-05-07 20:41:06', '2025-05-07 20:41:06', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1Qnlml2hHKxf4CTJll2xK2VR1QnlmP\"', '【用户模块】-微信登录', 349, 0);
INSERT INTO `sys_log` VALUES (1920097050789974017, '2025-05-07 20:43:05', '2025-05-07 20:43:05', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1SNE1w3GTqS43pra0w3F7Sus0SNE1b\"', '【用户模块】-微信登录', 360, 0);
INSERT INTO `sys_log` VALUES (1920099281534779393, '2025-05-07 20:51:57', '2025-05-07 20:51:57', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1AKHkl2kLcxf4Wziol2Hw0Ys1AKHkT\"', '【用户模块】-微信登录', 1800, 0);
INSERT INTO `sys_log` VALUES (1920100378915700738, '2025-05-07 20:56:19', '2025-05-07 20:56:19', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1Imi0w3LhBR43plg4w3L6RoN1Imi0t\"', '【用户模块】-微信登录', 373, 0);
INSERT INTO `sys_log` VALUES (1920100712400617474, '2025-05-07 20:57:38', '2025-05-07 20:57:38', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1prDml2ALsxf4avHkl24o5nS2prDmN\"', '【用户模块】-微信登录', 386, 0);
INSERT INTO `sys_log` VALUES (1920100978873139201, '2025-05-07 20:58:42', '2025-05-07 20:58:42', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1jmOll2AVhyf4DfYll2avBnD4jmOl7\"', '【用户模块】-微信登录', 357, 0);
INSERT INTO `sys_log` VALUES (1920101200512745473, '2025-05-07 20:59:35', '2025-05-07 20:59:35', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1dOF1w3gIqS436sa3w3SsHoI0dOF1l\"', '【用户模块】-微信登录', 381, 0);
INSERT INTO `sys_log` VALUES (1920101297808015362, '2025-05-07 20:59:58', '2025-05-07 20:59:58', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni1\",\"name\":\"不逆\",\"gender\":0,\"age\":8,\"phone\":\"15736408362\",\"birthday\":\"2017-05-07\"}', '【用户模块】-注册用户', 429, 0);
INSERT INTO `sys_log` VALUES (1920101961397235714, '2025-05-07 21:02:36', '2025-05-07 21:02:36', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni1\",\"name\":\"不逆\",\"gender\":0,\"age\":8,\"phone\":\"15736408362\",\"birthday\":\"2017-05-07\"}', '【用户模块】-注册用户', 983, 0);
INSERT INTO `sys_log` VALUES (1920102976087453697, '2025-05-07 21:06:38', '2025-05-07 21:06:38', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1ClpFa1g8fxJ0wsAHa17GlgR2ClpFs\"', '【用户模块】-微信登录', 1083, 0);
INSERT INTO `sys_log` VALUES (1920456392110309378, '2025-05-08 20:30:59', '2025-05-08 20:30:59', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1itR0w3U0bT43A4s0w3ckt4E3itR0m\"', '【用户模块】-微信登录', 1825, 0);
INSERT INTO `sys_log` VALUES (1920456799414976514, '2025-05-08 20:32:36', '2025-05-08 20:32:36', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1sSGFa1Fr0yJ0J8PHa1EOK3i2sSGFR\"', '【用户模块】-微信登录', 307, 0);
INSERT INTO `sys_log` VALUES (1920458996282408961, '2025-05-08 20:41:20', '2025-05-08 20:41:20', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a196S0w3AKbT433cZ0w3Tr0kk296S0h\"', '【用户模块】-微信登录', 1606, 0);
INSERT INTO `sys_log` VALUES (1920460353760509954, '2025-05-08 20:46:43', '2025-05-08 20:46:43', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b13bQll2Lhzwf4Llkol2FSZmr03bQlv\"', '【用户模块】-微信登录', 355, 0);
INSERT INTO `sys_log` VALUES (1920460382684430337, '2025-05-08 20:46:50', '2025-05-08 20:46:50', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e1jlk0w3GbDS43p6x3w3fLZfC4jlk0V\"', '【用户模块】-微信登录', 354, 0);
INSERT INTO `sys_log` VALUES (1920460597659287554, '2025-05-08 20:47:42', '2025-05-08 20:47:42', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1tKqFa1evJxJ0vbAFa1bvkrj0tKqFF\"', '【用户模块】-微信登录', 374, 0);
INSERT INTO `sys_log` VALUES (1920460734179688449, '2025-05-08 20:48:14', '2025-05-08 20:48:14', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1EYwGa1JoEwJ0xoWIa1w0Z0M3EYwGp\"', '【用户模块】-微信登录', 372, 0);
INSERT INTO `sys_log` VALUES (1920460860172386306, '2025-05-08 20:48:44', '2025-05-08 20:48:44', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1tUYFa1gJiyJ0hiAHa1h8ebo3tUYFq\"', '【用户模块】-微信登录', 319, 0);
INSERT INTO `sys_log` VALUES (1920461172123746305, '2025-05-08 20:49:59', '2025-05-08 20:49:59', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e1Ghill28Feyf4Czqll2kUM700GhilB\"', '【用户模块】-微信登录', 337, 0);
INSERT INTO `sys_log` VALUES (1920465030820511746, '2025-05-08 21:05:19', '2025-05-08 21:05:19', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c13PrFa167zxJ0UWpJa1T7nWW23PrFF\"', '【用户模块】-微信登录', 406, 0);
INSERT INTO `sys_log` VALUES (1920465127545356290, '2025-05-08 21:05:42', '2025-05-08 21:05:42', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni1\",\"name\":\"不逆\",\"gender\":0,\"age\":4,\"phone\":\"18527969642\",\"birthday\":\"2021-05-08\"}', '【用户模块】-注册用户', 37, 0);
INSERT INTO `sys_log` VALUES (1925160942171881473, '2025-05-21 20:05:11', '2025-05-21 20:05:11', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1EjEFa1QFbCJ02ReHa1PrgsF1EjEFI\"', '【用户模块】-微信登录', 1856, 0);
INSERT INTO `sys_log` VALUES (1925164684900388866, '2025-05-21 20:20:03', '2025-05-21 20:20:03', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1YRy0w3l9wX43mMT1w35Ys2s4YRy0c\"', '【用户模块】-微信登录', 1817, 0);
INSERT INTO `sys_log` VALUES (1925165143925018626, '2025-05-21 20:21:53', '2025-05-21 20:21:53', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1XYy0w3zgwX43RRO1w3KbNev1XYy0c\"', '【用户模块】-微信登录', 436, 0);
INSERT INTO `sys_log` VALUES (1925165288032915458, '2025-05-21 20:22:27', '2025-05-21 20:22:27', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d15Yh0w315HW43Av82w3G6C2C15Yh0s\"', '【用户模块】-微信登录', 453, 0);
INSERT INTO `sys_log` VALUES (1925166200931569666, '2025-05-21 20:26:05', '2025-05-21 20:26:05', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1RhQ0w3XKeX43BE52w3EmO2W0RhQ0k\"', '【用户模块】-微信登录', 428, 0);
INSERT INTO `sys_log` VALUES (1925166569405370370, '2025-05-21 20:27:33', '2025-05-21 20:27:33', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1je10w3ZDYW43YFH0w3089bt4je10Q\"', '【用户模块】-微信登录', 406, 0);
INSERT INTO `sys_log` VALUES (1925167188929236994, '2025-05-21 20:30:00', '2025-05-21 20:30:00', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e14u5000XeFhU1nVC300b0k6O04u50a\"', '【用户模块】-微信登录', 392, 0);
INSERT INTO `sys_log` VALUES (1925167460229402626, '2025-05-21 20:31:05', '2025-05-21 20:31:05', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1Iui0w3etHW43t4B2w31Nu5O0Iui0e\"', '【用户模块】-微信登录', 390, 0);
INSERT INTO `sys_log` VALUES (1925167712743280642, '2025-05-21 20:32:05', '2025-05-21 20:32:05', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c1ZHD000vediU1Vmn200goVIR3ZHD0h\"', '【用户模块】-微信登录', 417, 0);
INSERT INTO `sys_log` VALUES (1925167993061199873, '2025-05-21 20:33:12', '2025-05-21 20:33:12', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e1MavGa1bigBJ0CXOIa1hdYxa3MavGp\"', '【用户模块】-微信登录', 420, 0);
INSERT INTO `sys_log` VALUES (1925168392082116610, '2025-05-21 20:34:47', '2025-05-21 20:34:47', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1KLz0w3MhxX43L9c2w35DDZu3KLz0Z\"', '【用户模块】-微信登录', 427, 0);
INSERT INTO `sys_log` VALUES (1925168813974573058, '2025-05-21 20:36:28', '2025-05-21 20:36:28', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f15Sz0w3KkwX43YzJ1w3oxhRI15Sz0m\"', '【用户模块】-微信登录', 401, 0);
INSERT INTO `sys_log` VALUES (1925168823420145665, '2025-05-21 20:36:30', '2025-05-21 20:36:30', 'buni1', 'http://192.168.1.10:10001/v1/loginOut', '/v1/loginOut', 'POST', '127.0.0.1', NULL, '【用户模块】-退出登录', 1, 0);
INSERT INTO `sys_log` VALUES (1925168926105096194, '2025-05-21 20:36:55', '2025-05-21 20:36:55', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1aU50002VEhU17ys000l4lVA1aU50A\"', '【用户模块】-微信登录', 341, 0);
INSERT INTO `sys_log` VALUES (1925170209272713217, '2025-05-21 20:42:00', '2025-05-21 20:42:00', 'buni1', 'http://192.168.1.10:10001/v1/loginOut', '/v1/loginOut', 'POST', '127.0.0.1', NULL, '【用户模块】-退出登录', 1, 0);
INSERT INTO `sys_log` VALUES (1925170267376406529, '2025-05-21 20:42:14', '2025-05-21 20:42:14', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1VdA0w36OwX43Dm74w3SVdIM2VdA0Z\"', '【用户模块】-微信登录', 349, 0);
INSERT INTO `sys_log` VALUES (1925172202338865154, '2025-05-21 20:49:56', '2025-05-21 20:49:56', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c15Qp1w3W2DV43bXU1w3yAHPy45Qp1r\"', '【用户模块】-微信登录', 407, 0);
INSERT INTO `sys_log` VALUES (1925172496808366082, '2025-05-21 20:51:06', '2025-05-21 20:51:06', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1Rp0ll2s92Cf4IQSll2IAIzk0Rp0ly\"', '【用户模块】-微信登录', 384, 0);
INSERT INTO `sys_log` VALUES (1925173254740070402, '2025-05-21 20:54:07', '2025-05-21 20:54:07', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1lKPll2tFcBf4XX4ml2MN1b44lKPlx\"', '【用户模块】-微信登录', 375, 0);
INSERT INTO `sys_log` VALUES (1925522728444264450, '2025-05-22 20:02:48', '2025-05-22 20:02:48', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c16t81w3gGlW43W9i4w3eDmqZ06t813\"', '【用户模块】-微信登录', 1919, 0);
INSERT INTO `sys_log` VALUES (1925522867355418626, '2025-05-22 20:03:21', '2025-05-22 20:03:21', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1HyV000gUlhU1pvj000lR7gv1HyV0h\"', '【用户模块】-微信登录', 1438, 0);
INSERT INTO `sys_log` VALUES (1925522989506134018, '2025-05-22 20:03:50', '2025-05-22 20:03:50', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0c13NGFa18GACJ0ElWGa1nJkMz13NGFS\"', '【用户模块】-微信登录', 409, 0);
INSERT INTO `sys_log` VALUES (1925523188148371458, '2025-05-22 20:04:37', '2025-05-22 20:04:37', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni\",\"name\":\"不逆\",\"gender\":0,\"age\":0,\"phone\":\"15888734103\",\"birthday\":\"2025-05-22\"}', '【用户模块】-注册用户', 34, 0);
INSERT INTO `sys_log` VALUES (1925523925981016065, '2025-05-22 20:07:33', '2025-05-22 20:07:33', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e12z20w3VFuX43Q7t1w38OxOQ12z20H\"', '【用户模块】-微信登录', 1503, 0);
INSERT INTO `sys_log` VALUES (1925524031966883842, '2025-05-22 20:07:58', '2025-05-22 20:07:58', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni\",\"name\":\"不逆\",\"gender\":0,\"age\":0,\"phone\":\"19966322618\",\"birthday\":\"2025-05-22\"}', '【用户模块】-注册用户', 10, 0);
INSERT INTO `sys_log` VALUES (1925524051063549954, '2025-05-22 20:08:03', '2025-05-22 20:08:03', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni1\",\"name\":\"不逆\",\"gender\":0,\"age\":0,\"phone\":\"19966322618\",\"birthday\":\"2025-05-22\"}', '【用户模块】-注册用户', 39, 0);
INSERT INTO `sys_log` VALUES (1925524608545263617, '2025-05-22 20:10:16', '2025-05-22 20:10:16', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0a1g8qFa1IJRCJ0lJjGa1C7lUF2g8qF1\"', '【用户模块】-微信登录', 1374, 0);
INSERT INTO `sys_log` VALUES (1925524730826002434, '2025-05-22 20:10:45', '2025-05-22 20:10:45', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"build\",\"name\":\"不逆\",\"gender\":0,\"age\":0,\"phone\":\"15764926405\",\"birthday\":\"2025-05-22\"}', '【用户模块】-注册用户', 313, 0);
INSERT INTO `sys_log` VALUES (1925524771355561985, '2025-05-22 20:10:55', '2025-05-22 20:10:55', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b1oCyll2YxYBf4Xljll2CNLqj0oCyl9\"', '【用户模块】-微信登录', 422, 0);
INSERT INTO `sys_log` VALUES (1925524971746824194, '2025-05-22 20:11:42', '2025-05-22 20:11:42', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e1VXR0w3QfFW437GA3w37QVlB2VXR0V\"', '【用户模块】-微信登录', 415, 0);
INSERT INTO `sys_log` VALUES (1925525951364243458, '2025-05-22 20:15:36', '2025-05-22 20:15:36', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0e1Bf91w3HXoW43Ps60w3rwXT14Bf91v\"', '【用户模块】-微信登录', 1449, 0);
INSERT INTO `sys_log` VALUES (1925526747740938242, '2025-05-22 20:18:46', '2025-05-22 20:18:46', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0b13vW000UgmhU1v1Q100xCBb223vW0X\"', '【用户模块】-微信登录', 385, 0);
INSERT INTO `sys_log` VALUES (1925527034597777410, '2025-05-22 20:19:54', '2025-05-22 20:19:54', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1UUwGa1BYLBJ0KvtJa1cqhTU0UUwGT\"', '【用户模块】-微信登录', 395, 0);
INSERT INTO `sys_log` VALUES (1925527112850907138, '2025-05-22 20:20:13', '2025-05-22 20:20:13', '', 'http://192.168.1.10:10001/v1/register', '/v1/register', 'POST', '127.0.0.1', '{\"wxOpenId\":\"oXfio7fottJtAWjpcte_boXLFY6k\",\"username\":\"buni1\",\"name\":\"不逆\",\"avatar\":\"http://tmp/Gdd4N2MyiQmw2af357496cb78ef87f774c5df1aef4ae.jpeg\",\"gender\":0,\"age\":0,\"phone\":\"18693093509\",\"birthday\":\"2025-05-22\"}', '【用户模块】-注册用户', 357, 0);
INSERT INTO `sys_log` VALUES (1925527229146374145, '2025-05-22 20:20:41', '2025-05-22 20:20:41', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0d1X9DHa1FyEAJ0hnJHa1V675p0X9DHX\"', '【用户模块】-微信登录', 499, 0);
INSERT INTO `sys_log` VALUES (1925527414459113473, '2025-05-22 20:21:25', '2025-05-22 20:21:25', '', 'http://192.168.1.10:10001/v1/wxLogin', '/v1/wxLogin', 'POST', '127.0.0.1', '\"0f1CyS0w3qmEW43kiX1w3UZqwY3CyS0L\"', '【用户模块】-微信登录', 414, 0);

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
  `wx_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信openId',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名字',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生日',
  `age` tinyint NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `admin` tinyint NOT NULL DEFAULT 0 COMMENT '是否是超级管理员(0:否，1：是)',
  `enable` tinyint NOT NULL DEFAULT 1 COMMENT '是否启用(0:否，1：是)',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除(0:否，1：是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2023-09-26 10:25:43', '2025-03-29 18:55:58', NULL, 'buni', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '不逆', NULL, 18, 1, '1', NULL, 1, 1, 0);
INSERT INTO `sys_user` VALUES (1905943638473265153, '2025-03-29 19:22:29', '2025-03-29 20:32:35', NULL, '1111', '6105851562a482d1c32ff00d351b6ff7af33e8ba537385861e7fa4bba131016a', '1111', NULL, 111, 1, '19332761519', NULL, 0, 0, 1);
INSERT INTO `sys_user` VALUES (1905944034939850753, '2025-03-29 19:24:03', '2025-03-29 21:21:07', NULL, '2222', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '1111', NULL, 22, 1, '15381672844', NULL, 0, 1, 0);
INSERT INTO `sys_user` VALUES (1905944580086124546, '2025-03-29 19:26:13', '2025-03-29 21:21:07', NULL, 'qwqe', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '王企鹅群无', NULL, 12, 1, '15672921811', NULL, 0, 1, 0);
INSERT INTO `sys_user` VALUES (1905965146050482178, '2025-03-29 20:47:57', '2025-03-29 21:21:07', NULL, '12312', '2f8d31c420395ccbfe5d9ca7ce930b19af1e6942fa7538b18f5321cd474d225f', '1111', NULL, 11, 1, '13943643848', NULL, 0, 1, 0);
INSERT INTO `sys_user` VALUES (1905967462589747202, '2025-03-29 20:57:09', '2025-03-29 21:21:07', NULL, '其味无穷二', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '去问问群无', NULL, 22, 1, '15132844569', NULL, 0, 1, 0);
INSERT INTO `sys_user` VALUES (1925527111479369730, '2025-05-22 20:20:13', '2025-05-22 20:20:52', 'oXfio7fottJtAWjpcte_boXLFY6k', 'buni1', '2499b16d3cb8b6d49e3e76dcd072a1afc68c7706123c423426f450a27f71d4f8', '不逆', '2025-05-22', 0, 0, '18693093509', 'http://tmp/Gdd4N2MyiQmw2af357496cb78ef87f774c5df1aef4ae.jpeg', 1, 1, 0);

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
