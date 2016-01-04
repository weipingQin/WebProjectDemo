/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : mydb
Target Host     : localhost:3306
Target Database : mydb
Date: 2012-10-04 23:07:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `pswd` varchar(64) DEFAULT NULL,
  `realname` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '123', 'jack');
