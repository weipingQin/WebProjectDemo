/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : mydb
Target Host     : localhost:3306
Target Database : mydb
Date: 2012-10-04 23:07:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `proid` varchar(64) NOT NULL DEFAULT '',
  `proname` varchar(64) DEFAULT NULL,
  `proprice` varchar(64) DEFAULT NULL,
  `proaddress` varchar(64) DEFAULT NULL,
  `proimage` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`proid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('161f01', '香蕉', '23', '海南', 'png-0002.png');
INSERT INTO `product` VALUES ('440e23', '火龙果', '30', '海南', 'png-0012.png');
INSERT INTO `product` VALUES ('5d3fff', '荔枝', '45', '广西', 'png-0004.png');
INSERT INTO `product` VALUES ('dcabde', '龙眼', '45', '广西', 'png-0011.png');
