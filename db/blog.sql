/*
 Navicat Premium Data Transfer

 Source Server         : mac_docker
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/04/2020 20:45:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` bigint(11) NOT NULL,
  `category_name` varchar(20) DEFAULT NULL,
  `father_id` bigint(11) DEFAULT NULL,
  `save_date` datetime DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` varchar(50) NOT NULL,
  `city_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL COMMENT '评论id',
  `commentator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论者',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论者邮箱',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建的日期',
  `delete_date` datetime DEFAULT NULL COMMENT '删除的日期',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态，0正常，1删除，默认0',
  `is_author` tinyint(1) DEFAULT NULL COMMENT '是否是作者，0否，1是，默认0',
  `father_id` bigint(20) DEFAULT NULL COMMENT '父id, 默认0',
  `reply_id` bigint(20) DEFAULT NULL COMMENT '回复的评论id',
  `post_id` varchar(60) DEFAULT NULL COMMENT '被评论的文章ID',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL,
  `ip` varchar(100) NOT NULL COMMENT 'ip',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT ' 所在城市',
  `device_manufacturer` varchar(100) DEFAULT NULL COMMENT '设备生产厂商',
  `device_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备类型',
  `os` varchar(100) DEFAULT NULL COMMENT '操作系统',
  `os_name` varchar(100) DEFAULT NULL COMMENT '操作系统名称',
  `os_version` varchar(100) DEFAULT NULL COMMENT '操作系统版本号',
  `border_group` varchar(100) DEFAULT NULL COMMENT '浏览器组',
  `border_name` varchar(100) DEFAULT NULL COMMENT '浏览器名字',
  `border_type` varchar(100) DEFAULT NULL COMMENT '浏览器类型',
  `browser_manufacturer` varchar(100) DEFAULT NULL COMMENT '浏览器生产商',
  `browser_version` varchar(100) DEFAULT NULL COMMENT '浏览器版本',
  `browser_engine` varchar(100) DEFAULT NULL COMMENT '浏览器的渲染引擎',
  `created_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `img_id` int(11) NOT NULL,
  `data` varchar(255) DEFAULT NULL,
  `post_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` bigint(20) NOT NULL,
  `user_id` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户端密匙',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户端支持的grant_type',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '重定向URI',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒)',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '预留字段',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户是否自动Approval操作',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='客户端信息';

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `public_date` datetime DEFAULT NULL,
  `content` text,
  `edit_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `save_date` datetime DEFAULT NULL,
  `is_open_comment` tinyint(1) DEFAULT NULL COMMENT '是否开启文章的评论，0->不开启，1->开启',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post_category
-- ----------------------------
DROP TABLE IF EXISTS `post_category`;
CREATE TABLE `post_category` (
  `category_id` bigint(20) DEFAULT NULL,
  `post_id` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `post_category_index` (`post_id`,`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post_tag
-- ----------------------------
DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` varchar(50) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_tag_index` (`post_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qiniu_image
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_image`;
CREATE TABLE `qiniu_image` (
  `id` bigint(100) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '外链',
  `name` varchar(100) NOT NULL COMMENT '图片名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` bigint(20) NOT NULL COMMENT '回复id',
  `reply_nickname` varchar(50) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `content` text,
  `create_date` datetime DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(15) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL,
  `avatar` longtext,
  `motto` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `blog_name` varchar(100) DEFAULT NULL COMMENT '博客名称',
  `start_year` varchar(20) DEFAULT NULL,
  `end_year` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` bigint(20) NOT NULL,
  `tag_name` varchar(100) DEFAULT NULL,
  `save_date` datetime DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(50) NOT NULL COMMENT '主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码密文',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `setting_id` bigint(20) DEFAULT NULL COMMENT '配置id',
  `weibo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信链接',
  `github` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'GitHub链接',
  `QQ` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ号',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除1：已删除，0：未删除',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效用户 1：有效，0：无效',
  `account_non_expired` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账号是否未过期',
  `credentials_non_expired` tinyint(1) NOT NULL DEFAULT '0' COMMENT '密码是否未过期',
  `account_non_locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否未锁定',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
