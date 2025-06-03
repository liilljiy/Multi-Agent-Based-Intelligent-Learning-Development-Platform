/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql数据库
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springboot-study

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2024-06-05 22:52:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('3', 'hello', 'dys', 'hello my name is DYS');
INSERT INTO `article` VALUES ('5', 'bbb', 'bbb', '哇哇哇哇');
INSERT INTO `article` VALUES ('8', 'dys', 'dys', 'dys');
INSERT INTO `article` VALUES ('9', 'dys1', 'dys', 'dys1');

-- ----------------------------
-- Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', '软件1701');
INSERT INTO `t_class` VALUES ('2', '计算机1802');
INSERT INTO `t_class` VALUES ('3', '英语1904');

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `stuno` int(32) NOT NULL,
  `stuname` varchar(50) NOT NULL,
  `grade` int(32) NOT NULL,
  `dept` varchar(50) NOT NULL,
  `classname` varchar(20) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`stuno`),
  KEY `fk1` (`class_id`),
  CONSTRAINT `t_student_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('2017010101', '张三', '2019', '软件工程', '软件1701', '1');
INSERT INTO `t_student` VALUES ('2017010102', 'sjq', '2017', '软件工程', '软件1701', '1');
INSERT INTO `t_student` VALUES ('2018020222', '李四', '2018', '计算机', '计算机1802', '2');
INSERT INTO `t_student` VALUES ('2019030430', '王五', '2019', '英语', '英语1904', '3');
INSERT INTO `t_student` VALUES ('2019040101', '赵六', '2019', '日语', '日语1901', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4', 'sjq', '123', '100');
INSERT INTO `user` VALUES ('5', 'sjq2', '123', '20');
INSERT INTO `user` VALUES ('7', 'sjq0', '1234', '18');
INSERT INTO `user` VALUES ('8', 'sjq1', '12345', '19');

-- ----------------------------
-- View structure for `view`
-- ----------------------------
DROP VIEW IF EXISTS `view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view` AS select `u`.`username` AS `username`,`u`.`password` AS `password` from `user` `u` ;
