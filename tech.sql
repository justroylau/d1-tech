/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.31.181
 Source Server Type    : MySQL
 Source Server Version : 100308
 Source Host           : 192.168.31.181:3306
 Source Schema         : tech

 Target Server Type    : MySQL
 Target Server Version : 100308
 File Encoding         : 65001

 Date: 15/11/2019 22:23:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_class
-- ----------------------------
DROP TABLE IF EXISTS `article_class`;
CREATE TABLE `article_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `superid` int(11) NULL DEFAULT NULL,
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_class
-- ----------------------------
INSERT INTO `article_class` VALUES (1, 1, 'test');
INSERT INTO `article_class` VALUES (2, 2, 'java');

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `see` int(11) NULL DEFAULT 0,
  `great` int(11) NULL DEFAULT 0,
  `createtime` int(11) NULL DEFAULT NULL,
  `isdelete` int(2) NULL DEFAULT 0,
  `contents` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classid` int(11) NULL DEFAULT NULL,
  `userid` int(11) NULL DEFAULT NULL,
  `updatetime` int(11) NULL DEFAULT NULL,
  `istop` int(2) NULL DEFAULT 0 COMMENT '0普通，1顶置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES (62, '发帖测试', 9, 0, 1573715599, 0, '<p>发帖测试</p>', NULL, 1, 1, 1573715599, 0);
INSERT INTO `articles` VALUES (63, '二测', 3, 0, 1573715617, 0, '<p>二测</p>', NULL, 1, 1, 1573715617, 0);
INSERT INTO `articles` VALUES (64, '123123123', 2, 0, 1573715978, 0, '<h2>123123213232323</h2><p>123123123123321123</p><pre class=\"brush:c#;toolbar:false\">c艹\nsay\nasdf</pre><p><br/></p>', NULL, 1, 1, 1573715978, 0);

-- ----------------------------
-- Table structure for articles_copy
-- ----------------------------
DROP TABLE IF EXISTS `articles_copy`;
CREATE TABLE `articles_copy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `see` int(11) NULL DEFAULT 0,
  `great` int(11) NULL DEFAULT 0,
  `createtime` int(11) NULL DEFAULT NULL,
  `isdelete` int(2) NULL DEFAULT 0,
  `contents` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sentence
-- ----------------------------
DROP TABLE IF EXISTS `sentence`;
CREATE TABLE `sentence`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentences` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sentence
-- ----------------------------
INSERT INTO `sentence` VALUES (1, 'test');
INSERT INTO `sentence` VALUES (2, 'test2');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lastlogintime` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users   Password = 123
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '67b19ba4c527474738a78342931f67a96c5ec86b0ab7f301', 1573715184, '备注admin', 'admin', 'email', 'photo', '别名', 1573715416);

SET FOREIGN_KEY_CHECKS = 1;
