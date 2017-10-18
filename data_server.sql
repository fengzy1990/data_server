/*
Navicat MySQL Data Transfer

Source Server         : MyProject
Source Server Version : 50514
Source Host           : localhost:3306
Source Database       : data_server

Target Server Type    : MYSQL
Target Server Version : 50514
File Encoding         : 65001

Date: 2017-10-16 17:53:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `data_info`
-- ----------------------------
DROP TABLE IF EXISTS `data_info`;
CREATE TABLE `data_info` (
  `data_id` int(10) NOT NULL AUTO_INCREMENT,
  `data_name` varchar(50) DEFAULT NULL,
  `data_mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_info
-- ----------------------------
INSERT INTO `data_info` VALUES ('100001', '测试信息名称', '测试信息内容--test');
INSERT INTO `data_info` VALUES ('100002', '22244', '222444');
INSERT INTO `data_info` VALUES ('100003', '333', '333');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_permission` int(10) NOT NULL,
  `user_sex` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1001', '1001', '丽红', '2', '女');
INSERT INTO `users` VALUES ('1002', '1002', '张三', '1', '女');
INSERT INTO `users` VALUES ('admin', 'admin', '联通', '0', '男');
