/*
 Navicat MySQL Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : ms_blog

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 04/11/2021 22:13:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ms_article
-- ----------------------------
DROP TABLE IF EXISTS `ms_article`;
CREATE TABLE `ms_article`  (
  `id` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容简介',
  `content_md` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `type` int NULL DEFAULT NULL COMMENT '类型',
  `writer_id` bigint NOT NULL COMMENT '作者id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片资源',
  `likes` int NULL DEFAULT NULL COMMENT '点赞',
  `version` int NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_article
-- ----------------------------
INSERT INTO `ms_article` VALUES (1, 'ms-vue', 'ms-vue md ', '# msblog-ts\r\n\r\n\r\n- Get the project code\r\n\r\n```bash\r\ngit clone https://github.com.cnpmjs.org/my-msblog/msblog-ts.git\r\n```\r\n## Project setup\r\n```\r\nyarn install\r\n```\r\n\r\n### Compiles and hot-reloads for development\r\n```\r\nyarn serve\r\n```\r\n\r\n### Compiles and minifies for production\r\n```\r\nyarn build\r\n```\r\n\r\n### Lints and fixes files\r\n```\r\nyarn lint\r\n```\r\n\r\n### core\r\n\r\n|  说明 | 技术 |\r\n|  :----:  | :----: |\r\n| 框架 | vue3 |\r\n|  脚手架 | vue/cli@5.0.0-beta.4 |\r\n|  语言 | TypeScript@4.1.5 |\r\n| ui框架 | element-plus@1.1.0-beta.19|\r\n\r\n## Git 贡献提交规范\r\n- 参考  [vue](https://github.com/vuejs/vue/blob/dev/.github/COMMIT_CONVENTION.md) 规范  ([Angular](https://github.com/conventional-changelog/conventional-changelog/tree/master/packages/conventional-changelog-angular))\r\n    - `feat` 增加新功能\r\n    - `fix` 修复问题/BUG\r\n    - `style` 代码风格相关无影响运行结果的\r\n    - `perf` 优化/性能提升\r\n    - `refactor` 重构\r\n    - `revert` 撤销修改\r\n    - `test` 测试相关\r\n    - `docs` 文档/注释\r\n    - `chore` 依赖更新/脚手架配置修改等\r\n    - `workflow` 工作流改进\r\n    - `ci` 持续集成\r\n    - `types` 类型定义文件更改\r\n    - `wip` 开发中\r\n\r\n\r\n\r\n### Customize configuration\r\nSee [Configuration Reference](https://cli.vuejs.org/config/).\r\n', 3, 1393130419668688898, '2021-11-04 16:13:09', '2021-11-04 16:13:14', 'https://i.loli.net/2021/11/04/XMsbfOEHCGBcSaw.jpg', 0, 0);

-- ----------------------------
-- Table structure for ms_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `ms_article_tag`;
CREATE TABLE `ms_article_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tag_id` bigint NULL DEFAULT NULL,
  `article_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_article_tag
-- ----------------------------
INSERT INTO `ms_article_tag` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for ms_category
-- ----------------------------
DROP TABLE IF EXISTS `ms_category`;
CREATE TABLE `ms_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_category
-- ----------------------------
INSERT INTO `ms_category` VALUES (1, 1, '生活笔记');
INSERT INTO `ms_category` VALUES (2, 2, '经验分享');
INSERT INTO `ms_category` VALUES (3, 3, '技术总结');
INSERT INTO `ms_category` VALUES (4, 4, '其它');

-- ----------------------------
-- Table structure for ms_comment
-- ----------------------------
DROP TABLE IF EXISTS `ms_comment`;
CREATE TABLE `ms_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `article_id` bigint NOT NULL,
  `commenter_id` bigint NULL DEFAULT NULL,
  `respondent_id` bigint NULL DEFAULT 0,
  `parent_id` bigint NULL DEFAULT 0,
  `likes` int NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_comment
-- ----------------------------
INSERT INTO `ms_comment` VALUES (1, 'text', 1, 1393130419668688898, 0, 0, 0, '2021-06-02 10:32:34');
INSERT INTO `ms_comment` VALUES (2, 'dddd', 1, 1, 1393130419668688898, 1, 0, '2021-06-02 10:33:05');
INSERT INTO `ms_comment` VALUES (3, '111', 1, 1, 0, 0, 1, '2021-06-02 10:33:50');
INSERT INTO `ms_comment` VALUES (5, '222', 1, 1393130419668688898, 1, 1, 0, '2021-06-02 14:06:30');

-- ----------------------------
-- Table structure for ms_favorites
-- ----------------------------
DROP TABLE IF EXISTS `ms_favorites`;
CREATE TABLE `ms_favorites`  (
  `id` bigint NOT NULL,
  `article_id` bigint NOT NULL COMMENT '文章id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `create_time` datetime NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ms_favorites
-- ----------------------------

-- ----------------------------
-- Table structure for ms_flow
-- ----------------------------
DROP TABLE IF EXISTS `ms_flow`;
CREATE TABLE `ms_flow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL COMMENT '单日时间',
  `flow` int NULL DEFAULT NULL COMMENT '流量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ms_flow
-- ----------------------------
INSERT INTO `ms_flow` VALUES (1, '2021-10-29 00:00:00', 20);
INSERT INTO `ms_flow` VALUES (2, '2021-10-14 00:00:00', 18);

-- ----------------------------
-- Table structure for ms_logs
-- ----------------------------
DROP TABLE IF EXISTS `ms_logs`;
CREATE TABLE `ms_logs`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `time` datetime NULL DEFAULT NULL COMMENT '文件创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1439922427824001026 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ms_logs
-- ----------------------------
INSERT INTO `ms_logs` VALUES (1439922426272108545, 'ms-log-2021-06-06-0.log', '2021-06-06 17:05:20');
INSERT INTO `ms_logs` VALUES (1439922426326634498, 'ms-log-2021-06-13-0.log', '2021-06-13 18:48:30');
INSERT INTO `ms_logs` VALUES (1439922426326634499, 'ms-log-2021-06-27-0.log', '2021-06-27 16:43:23');
INSERT INTO `ms_logs` VALUES (1439922426389549057, 'ms-log-2021-06-28-0.log', '2021-06-28 19:50:01');
INSERT INTO `ms_logs` VALUES (1439922426389549058, 'ms-log-2021-07-19-0.log', '2021-07-19 16:24:43');
INSERT INTO `ms_logs` VALUES (1439922426452463617, 'ms-log-2021-07-21-0.log', '2021-07-21 10:18:36');
INSERT INTO `ms_logs` VALUES (1439922426452463618, 'ms-log-2021-07-23-0.log', '2021-07-23 23:55:09');
INSERT INTO `ms_logs` VALUES (1439922426515378178, 'ms-log-2021-07-24-0.log', '2021-07-24 23:41:56');
INSERT INTO `ms_logs` VALUES (1439922426515378179, 'ms-log-2021-07-25-0.log', '2021-07-25 22:55:27');
INSERT INTO `ms_logs` VALUES (1439922426582487041, 'ms-log-2021-07-26-0.log', '2021-07-26 16:28:03');
INSERT INTO `ms_logs` VALUES (1439922426582487042, 'ms-log-2021-07-27-0.log', '2021-07-27 11:20:46');
INSERT INTO `ms_logs` VALUES (1439922426645401602, 'ms-log-2021-07-28-0.log', '2021-07-28 21:31:48');
INSERT INTO `ms_logs` VALUES (1439922426645401603, 'ms-log-2021-07-29-0.log', '2021-07-29 20:41:47');
INSERT INTO `ms_logs` VALUES (1439922426645401604, 'ms-log-2021-07-30-0.log', '2021-07-30 19:23:51');
INSERT INTO `ms_logs` VALUES (1439922426712510466, 'ms-log-2021-07-31-0.log', '2021-07-31 20:12:36');
INSERT INTO `ms_logs` VALUES (1439922426712510467, 'ms-log-2021-08-01-0.log', '2021-08-01 22:28:36');
INSERT INTO `ms_logs` VALUES (1439922426779619330, 'ms-log-2021-08-02-0.log', '2021-08-02 19:28:24');
INSERT INTO `ms_logs` VALUES (1439922426779619331, 'ms-log-2021-08-03-0.log', '2021-08-03 20:15:58');
INSERT INTO `ms_logs` VALUES (1439922426842533889, 'ms-log-2021-08-04-0.log', '2021-08-04 21:43:57');
INSERT INTO `ms_logs` VALUES (1439922426842533890, 'ms-log-2021-08-05-0.log', '2021-08-05 15:53:21');
INSERT INTO `ms_logs` VALUES (1439922426909642754, 'ms-log-2021-08-06-0.log', '2021-08-06 19:36:06');
INSERT INTO `ms_logs` VALUES (1439922426909642755, 'ms-log-2021-08-07-0.log', '2021-08-07 20:30:15');
INSERT INTO `ms_logs` VALUES (1439922426976751618, 'ms-log-2021-08-08-0.log', '2021-08-08 18:35:45');
INSERT INTO `ms_logs` VALUES (1439922426976751619, 'ms-log-2021-08-09-0.log', '2021-08-09 19:15:16');
INSERT INTO `ms_logs` VALUES (1439922427043860482, 'ms-log-2021-08-11-0.log', '2021-08-11 19:34:54');
INSERT INTO `ms_logs` VALUES (1439922427043860483, 'ms-log-2021-08-12-0.log', '2021-08-12 21:54:34');
INSERT INTO `ms_logs` VALUES (1439922427043860484, 'ms-log-2021-08-13-0.log', '2021-08-13 18:42:35');
INSERT INTO `ms_logs` VALUES (1439922427110969345, 'ms-log-2021-08-14-0.log', '2021-08-14 21:57:16');
INSERT INTO `ms_logs` VALUES (1439922427110969346, 'ms-log-2021-08-15-0.log', '2021-08-15 23:45:54');
INSERT INTO `ms_logs` VALUES (1439922427173883906, 'ms-log-2021-08-16-0.log', '2021-08-16 20:10:21');
INSERT INTO `ms_logs` VALUES (1439922427173883907, 'ms-log-2021-08-17-0.log', '2021-08-17 19:54:43');
INSERT INTO `ms_logs` VALUES (1439922427236798466, 'ms-log-2021-08-18-0.log', '2021-08-18 16:22:11');
INSERT INTO `ms_logs` VALUES (1439922427236798467, 'ms-log-2021-08-19-0.log', '2021-08-19 14:50:58');
INSERT INTO `ms_logs` VALUES (1439922427299713026, 'ms-log-2021-08-21-0.log', '2021-08-21 21:20:34');
INSERT INTO `ms_logs` VALUES (1439922427299713027, 'ms-log-2021-08-23-0.log', '2021-08-23 20:15:28');
INSERT INTO `ms_logs` VALUES (1439922427362627586, 'ms-log-2021-08-24-0.log', '2021-08-24 20:04:53');
INSERT INTO `ms_logs` VALUES (1439922427362627587, 'ms-log-2021-08-25-0.log', '2021-08-25 20:08:57');
INSERT INTO `ms_logs` VALUES (1439922427429736450, 'ms-log-2021-08-26-0.log', '2021-08-26 21:23:49');
INSERT INTO `ms_logs` VALUES (1439922427429736451, 'ms-log-2021-08-27-0.log', '2021-08-27 21:45:59');
INSERT INTO `ms_logs` VALUES (1439922427496845314, 'ms-log-2021-08-28-0.log', '2021-08-28 21:53:45');
INSERT INTO `ms_logs` VALUES (1439922427496845315, 'ms-log-2021-08-29-0.log', '2021-08-29 19:14:18');
INSERT INTO `ms_logs` VALUES (1439922427496845316, 'ms-log-2021-08-30-0.log', '2021-08-30 19:57:10');
INSERT INTO `ms_logs` VALUES (1439922427559759873, 'ms-log-2021-09-02-0.log', '2021-09-02 21:13:55');
INSERT INTO `ms_logs` VALUES (1439922427559759874, 'ms-log-2021-09-04-0.log', '2021-09-04 19:11:41');
INSERT INTO `ms_logs` VALUES (1439922427626868738, 'ms-log-2021-09-05-0.log', '2021-09-05 16:15:42');
INSERT INTO `ms_logs` VALUES (1439922427626868739, 'ms-log-2021-09-06-0.log', '2021-09-06 21:07:36');
INSERT INTO `ms_logs` VALUES (1439922427626868740, 'ms-log-2021-09-07-0.log', '2021-09-07 17:41:39');
INSERT INTO `ms_logs` VALUES (1439922427689783297, 'ms-log-2021-09-08-0.log', '2021-09-08 17:16:44');
INSERT INTO `ms_logs` VALUES (1439922427689783298, 'ms-log-2021-09-12-0.log', '2021-09-12 23:47:11');
INSERT INTO `ms_logs` VALUES (1439922427756892161, 'ms-log-2021-09-13-0.log', '2021-09-13 23:09:56');
INSERT INTO `ms_logs` VALUES (1439922427756892162, 'ms-log-2021-09-14-0.log', '2021-09-14 20:39:33');
INSERT INTO `ms_logs` VALUES (1439922427824001026, 'ms-log.log', '2021-09-20 20:00:33');

-- ----------------------------
-- Table structure for ms_menu
-- ----------------------------
DROP TABLE IF EXISTS `ms_menu`;
CREATE TABLE `ms_menu`  (
  `id` int NOT NULL,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '与 Vue 路由中的 path 对应，即地址路径',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '与 Vue 路由中的 name 属性对应',
  `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端识别关键字，用于渲染导航栏（菜单）界面',
  `zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标类名',
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件名，用于解析路由对应的组件',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `deleted` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_menu
-- ----------------------------
INSERT INTO `ms_menu` VALUES (1, '/admin', 'AdminIndex', 'dashboard', '首页', 'el-icon-house', 'layout', 0, 0);
INSERT INTO `ms_menu` VALUES (2, '/admin/context', 'Context', 'content_management', '内容管理', 'el-icon-tickets', 'layout', 0, 0);
INSERT INTO `ms_menu` VALUES (3, '/admin/user', 'User', 'user_management', '用户管理', 'el-icon-user-solid', 'layout', 0, 0);
INSERT INTO `ms_menu` VALUES (4, '/admin/personal', 'Info', 'personal_information', '个人信息', 'el-icon-postcard', '/personal', 0, 0);
INSERT INTO `ms_menu` VALUES (5, '/admin/security', 'Security', 'security_settings', '安全设置', 'el-icon-lock', '/serurity', 0, 0);
INSERT INTO `ms_menu` VALUES (6, '/admin/user/profile', 'Profile', 'user_information', '用户信息', 'el-icon-menu', '/user-manage/userProfile', 3, 0);
INSERT INTO `ms_menu` VALUES (7, '/admin/user/role', 'RoleSetting', 'role_configuration', '角色配置', 'el-icon-set-up', '/user-manage/role', 3, 0);
INSERT INTO `ms_menu` VALUES (9, '/admin/context/banner', 'BannerManagement', 'advertising_management', '广告管理', 'el-icon-mobile', '/context/banner-management', 2, 1);
INSERT INTO `ms_menu` VALUES (10, '/admin/context/article', 'ArticleManagement', 'article_management', '文章管理', 'el-icon-edit-outline', '/context/article-management', 2, 0);
INSERT INTO `ms_menu` VALUES (11, '/admin/leave', 'leaveComments', 'message_management', '留言管理', 'el-icon-house', 'layout', 0, 1);
INSERT INTO `ms_menu` VALUES (12, '/admin/log', 'Logout', 'log_output', '日志输出', 'el-icon-files', '/log', 0, 0);
INSERT INTO `ms_menu` VALUES (13, '/admin/system', 'System', 'system_management', '系统管理', 'el-icon-s-tools', '/system', 0, 0);
INSERT INTO `ms_menu` VALUES (14, '/admin/context/tags', 'Tags', 'tags_management', '标签管理', 'el-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management\r\nel-icon-s-management', '/context/tags-management', 2, 0);
INSERT INTO `ms_menu` VALUES (15, '/admin/context/article/categories', 'ArticleCategories', 'article_categories', '文章分类', 'el-icon-s-grid', '/context/article-categories', 2, 0);

-- ----------------------------
-- Table structure for ms_permission
-- ----------------------------
DROP TABLE IF EXISTS `ms_permission`;
CREATE TABLE `ms_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_en` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `described` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_permission
-- ----------------------------
INSERT INTO `ms_permission` VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `ms_permission` VALUES (2, 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `ms_permission` VALUES (3, 'content_management', '内容管理', '/api/admin/content');

-- ----------------------------
-- Table structure for ms_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `ms_permission_menu`;
CREATE TABLE `ms_permission_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NULL DEFAULT NULL COMMENT 'permission id',
  `mid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_permission_menu
-- ----------------------------
INSERT INTO `ms_permission_menu` VALUES (194, 1, 1);
INSERT INTO `ms_permission_menu` VALUES (195, 1, 2);
INSERT INTO `ms_permission_menu` VALUES (196, 1, 3);
INSERT INTO `ms_permission_menu` VALUES (197, 1, 10);
INSERT INTO `ms_permission_menu` VALUES (198, 1, 9);
INSERT INTO `ms_permission_menu` VALUES (199, 1, 6);
INSERT INTO `ms_permission_menu` VALUES (200, 1, 7);
INSERT INTO `ms_permission_menu` VALUES (204, 3, 4);
INSERT INTO `ms_permission_menu` VALUES (205, 3, 5);
INSERT INTO `ms_permission_menu` VALUES (206, 3, 11);
INSERT INTO `ms_permission_menu` VALUES (207, 1, 13);
INSERT INTO `ms_permission_menu` VALUES (208, 1, 14);
INSERT INTO `ms_permission_menu` VALUES (209, 1, 12);

-- ----------------------------
-- Table structure for ms_role
-- ----------------------------
DROP TABLE IF EXISTS `ms_role`;
CREATE TABLE `ms_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_role
-- ----------------------------
INSERT INTO `ms_role` VALUES (1, 'SYSTEM_ADMIN', '系统管理员', 1);
INSERT INTO `ms_role` VALUES (2, 'CONTENT_MANAGER', '内容管理员', 1);
INSERT INTO `ms_role` VALUES (3, 'VISITOR', '访客', 1);

-- ----------------------------
-- Table structure for ms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ms_role_permission`;
CREATE TABLE `ms_role_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rid` bigint NULL DEFAULT NULL COMMENT 'role id',
  `pid` bigint NULL DEFAULT NULL COMMENT 'permission id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_role_permission
-- ----------------------------
INSERT INTO `ms_role_permission` VALUES (1, 1, 1);
INSERT INTO `ms_role_permission` VALUES (2, 1, 2);
INSERT INTO `ms_role_permission` VALUES (3, 1, 3);
INSERT INTO `ms_role_permission` VALUES (4, 2, 3);

-- ----------------------------
-- Table structure for ms_tag
-- ----------------------------
DROP TABLE IF EXISTS `ms_tag`;
CREATE TABLE `ms_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_tag
-- ----------------------------
INSERT INTO `ms_tag` VALUES (1, 'vue', 'vue', '2021-11-04 20:59:22');
INSERT INTO `ms_tag` VALUES (2, 'spring boot', 'spring boot', '2021-11-04 21:00:36');

-- ----------------------------
-- Table structure for ms_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人介绍',
  `sex` int NULL DEFAULT NULL COMMENT '性别(0:女性，1男性)',
  `email` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `version` int NULL DEFAULT 1,
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT ' 是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_user
-- ----------------------------
INSERT INTO `ms_user` VALUES (1, 'admin', 'f0dbe38dbeab67349e6c737c1d49a097', 'this is admin account introdciton,all permission，test', 1, 'admin@qq.com', '17759309269', '2021-05-20 17:47:14', '2021-10-09 21:20:49', 32, 0);
INSERT INTO `ms_user` VALUES (44444, '23', '321', 'test', 1, '4text@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-10-12 16:32:06', 6, 0);
INSERT INTO `ms_user` VALUES (3333333333, '25', '53', 'test', 0, '3text@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-10-12 17:11:11', 3, 0);
INSERT INTO `ms_user` VALUES (4343434343, '213123', '321', 'test', 0, 'test@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-10-14 22:59:15', 9, 0);
INSERT INTO `ms_user` VALUES (6666666666, '6', '6', 'test', 1, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-10-12 17:05:47', 3, 0);
INSERT INTO `ms_user` VALUES (12121212121, 'text2', '2222', 'test', 0, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (22222222222, 'qeqwe', '213', 'test', 0, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (77777777777, '7', '7', 'test', 0, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (444444444444, '4', '4', 'test', 1, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (555555555555, '5', '5', 'test', 1, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (432312312313123123, '44', '23', '321', 2, 'text@qq.com', '1234567890', '2021-05-13 16:02:35', NULL, 1, 0);
INSERT INTO `ms_user` VALUES (1392752115447533570, '11131', '131', 'test', 1, '123@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-05-13 17:39:15', 3, 0);
INSERT INTO `ms_user` VALUES (1393106891279986689, 'text1', '133', 'test', 1, 'text@qq.com', '1000001', '2021-05-14 15:32:20', '2021-05-14 15:32:20', 0, 0);
INSERT INTO `ms_user` VALUES (1393111612233474050, '12312', '29852fd8f42d63ef579aa46d8cd15183', 'test', 1, 'text@qq.com', '312331', '2021-05-14 15:51:06', '2021-05-14 15:51:06', 0, 0);
INSERT INTO `ms_user` VALUES (1393130419668688898, 'ms', '21232f297a57a5a743894a0e4a801fc3', 'ms', 1, 'mstext@qq.com', '1234567890', '2021-05-13 16:02:35', '2021-05-14 17:05:50', 0, 0);
INSERT INTO `ms_user` VALUES (1450800489004367873, 'test-add', '3a796012c73aed1fe6f390bb191d40ab', NULL, 0, 'add@123.com', '1234321', '2021-10-20 20:26:06', '2021-10-20 20:26:06', 0, 0);
INSERT INTO `ms_user` VALUES (1452921905195642882, 'tableadd', '3e9a00f12acde32bb0bb71b79698db0d', NULL, 1, '', '', '2021-10-26 16:55:51', '2021-10-26 16:55:51', 0, 0);

-- ----------------------------
-- Table structure for ms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ms_user_role`;
CREATE TABLE `ms_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NULL DEFAULT NULL,
  `rid` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1452921905719930883 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ms_user_role
-- ----------------------------
INSERT INTO `ms_user_role` VALUES (1, 1, 1);
INSERT INTO `ms_user_role` VALUES (4, 1393130419668688898, 3);
INSERT INTO `ms_user_role` VALUES (5, 44444, 3);
INSERT INTO `ms_user_role` VALUES (6, 3333333333, 3);
INSERT INTO `ms_user_role` VALUES (7, 4343434343, 3);
INSERT INTO `ms_user_role` VALUES (8, 6666666666, 3);
INSERT INTO `ms_user_role` VALUES (9, 12121212121, 3);
INSERT INTO `ms_user_role` VALUES (10, 22222222222, 3);
INSERT INTO `ms_user_role` VALUES (11, 77777777777, 3);
INSERT INTO `ms_user_role` VALUES (12, 444444444444, 3);
INSERT INTO `ms_user_role` VALUES (13, 555555555555, 3);
INSERT INTO `ms_user_role` VALUES (14, 432312312313123123, 3);
INSERT INTO `ms_user_role` VALUES (15, 1392752115447533570, 3);
INSERT INTO `ms_user_role` VALUES (16, 1393106891279986689, 3);
INSERT INTO `ms_user_role` VALUES (17, 1393111612233474050, 3);
INSERT INTO `ms_user_role` VALUES (1450800489763536897, 1450800489004367873, 3);
INSERT INTO `ms_user_role` VALUES (1452921905719930882, 1452921905195642882, 3);

SET FOREIGN_KEY_CHECKS = 1;
