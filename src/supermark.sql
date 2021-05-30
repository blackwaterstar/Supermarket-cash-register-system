/*
 Navicat Premium Data Transfer

 Source Server         : jdbc
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : supermark

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 30/05/2021 12:19:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `Commodity_Id` int(20) NOT NULL AUTO_INCREMENT,
  `Commodity_Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Shelves` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Price` double(20, 2) NULL DEFAULT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`Commodity_Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1110015 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic STATS_AUTO_RECALC = 0;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1110001, '款泉水', '货架1', '生活用品', 5.00, 89100);
INSERT INTO `commodity` VALUES (1110002, '花生油', '货架1', '生活用品', 89.00, 89100);
INSERT INTO `commodity` VALUES (1110003, '锄头', '货架2', '农业用品', 130.00, 99922);
INSERT INTO `commodity` VALUES (1110004, '簸箕', '货架2', '农业用品', 24.00, 99937);
INSERT INTO `commodity` VALUES (1110005, '钢笔', '货架3', '文具', 5.00, 99945);
INSERT INTO `commodity` VALUES (1110006, '毛笔', '货架3', '文具', 8.00, 99838);
INSERT INTO `commodity` VALUES (1110007, '毛衣', '货架4', '服装', 130.00, 100000);
INSERT INTO `commodity` VALUES (1110008, '手机', '货架3', '电子产品', 700.00, 99999);
INSERT INTO `commodity` VALUES (1110009, '羽绒服', '货架4', '服装', 95.00, 99973);
INSERT INTO `commodity` VALUES (1110014, '口罩', '货架1', '生活用品', 3.00, 990);
INSERT INTO `commodity` VALUES (1110015, '书包', '货架3', '文具', 40.00, 998);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`member_id`) USING BTREE,
  UNIQUE INDEX `member_name`(`member_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123456182 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (123456180, 'test');
INSERT INTO `member` VALUES (123456181, 'test1');
INSERT INTO `member` VALUES (123456174, '哎啊');
INSERT INTO `member` VALUES (123456175, '张三');
INSERT INTO `member` VALUES (123456176, '李四');
INSERT INTO `member` VALUES (123456178, '爱德华');
INSERT INTO `member` VALUES (123456182, '特踏实');
INSERT INTO `member` VALUES (123456177, '王五');

-- ----------------------------
-- Table structure for member_sales
-- ----------------------------
DROP TABLE IF EXISTS `member_sales`;
CREATE TABLE `member_sales`  (
  `member_id` int(11) NOT NULL,
  `commodity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `allprice` decimal(10, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_sales
-- ----------------------------
INSERT INTO `member_sales` VALUES (123456177, '款泉水', 5.00, 100, 500.00);
INSERT INTO `member_sales` VALUES (123456177, '锄头', 130.00, 6, 780.00);
INSERT INTO `member_sales` VALUES (123456177, '钢笔', 5.00, 89, 445.00);
INSERT INTO `member_sales` VALUES (123456177, '毛笔', 8.00, 10, 80.00);
INSERT INTO `member_sales` VALUES (123456177, '羽绒服', 964.20, 1, 964.00);
INSERT INTO `member_sales` VALUES (123456177, '簸箕', 23.69, 7, 166.00);
INSERT INTO `member_sales` VALUES (123456177, '锄头', 130.00, 1, 130.00);
INSERT INTO `member_sales` VALUES (123456176, '钢笔', 5.00, 12, 60.00);
INSERT INTO `member_sales` VALUES (123456176, '钢笔', 5.00, 200, 950.00);
INSERT INTO `member_sales` VALUES (123456178, '毛笔', 8.00, 50, 380.00);
INSERT INTO `member_sales` VALUES (123456180, '钢笔', 5.00, 50, 237.50);
INSERT INTO `member_sales` VALUES (123456180, '锄头', 130.00, 3, 370.50);
INSERT INTO `member_sales` VALUES (123456181, '手机', 699.00, 1, 664.05);
INSERT INTO `member_sales` VALUES (123456182, '锄头', 130.00, 2, 247.00);

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales`  (
  `commodity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `number` int(255) NULL DEFAULT NULL,
  `allprice` decimal(10, 2) NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES ('簸箕', 23.69, 7, 166.00, '2020-12-27');
INSERT INTO `sales` VALUES ('锄头', 130.00, 1, 130.00, '2020-12-27');
INSERT INTO `sales` VALUES ('款泉水', 5.00, 100, 475.00, '2020-12-27');
INSERT INTO `sales` VALUES ('锄头', 130.00, 6, 741.00, '2020-12-27');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 89, 422.75, '2020-12-27');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 10, 76.00, '2020-12-27');
INSERT INTO `sales` VALUES ('簸箕', 23.69, 7, 157.70, '2020-12-27');
INSERT INTO `sales` VALUES ('锄头', 130.00, 1, 123.50, '2020-12-27');
INSERT INTO `sales` VALUES ('款泉水', 5.00, 100, 475.00, '2020-12-27');
INSERT INTO `sales` VALUES ('锄头', 130.00, 6, 741.00, '2020-12-27');
INSERT INTO `sales` VALUES ('羽绒服', 964.20, 2, 1831.60, '2020-12-27');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 89, 422.75, '2020-12-27');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 10, 76.00, '2020-12-27');
INSERT INTO `sales` VALUES ('羽绒服', 964.20, 1, 915.80, '2020-12-27');
INSERT INTO `sales` VALUES ('簸箕', 23.69, 7, 157.70, '2020-12-27');
INSERT INTO `sales` VALUES ('锄头', 130.00, 1, 123.50, '2020-12-27');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 12, 57.00, '2020-12-28');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 9, 72.00, '2020-12-28');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 30, 150.00, '2020-12-28');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 5, 25.00, '2020-12-28');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 50, 380.00, '2020-12-28');
INSERT INTO `sales` VALUES ('钢笔', 5.00, 50, 237.50, '2020-12-30');
INSERT INTO `sales` VALUES ('锄头', 130.00, 3, 370.50, '2020-12-30');
INSERT INTO `sales` VALUES ('锄头', 130.00, 2, 247.00, '2020-12-30');
INSERT INTO `sales` VALUES ('口罩', 3.00, 10, 30.00, '2020-12-30');
INSERT INTO `sales` VALUES ('书包', 40.00, 2, 80.00, '2020-12-30');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 3, 24.00, '2020-12-30');
INSERT INTO `sales` VALUES ('毛笔', 8.00, 10, 80.00, '2020-12-30');

-- ----------------------------
-- Table structure for shelves
-- ----------------------------
DROP TABLE IF EXISTS `shelves`;
CREATE TABLE `shelves`  (
  `shelves_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `shelves_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shelves_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shelves
-- ----------------------------
INSERT INTO `shelves` VALUES ('1', '货架1');
INSERT INTO `shelves` VALUES ('2', '货架2');
INSERT INTO `shelves` VALUES ('3', '货架3');
INSERT INTO `shelves` VALUES ('4', '货架4');
INSERT INTO `shelves` VALUES ('5', '货架5');

-- ----------------------------
-- Table structure for single
-- ----------------------------
DROP TABLE IF EXISTS `single`;
CREATE TABLE `single`  (
  `Commodity_Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Price` double(20, 2) NOT NULL,
  `number` int(11) NOT NULL,
  `allprice` double(11, 0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic STATS_AUTO_RECALC = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `User_Name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isLogin` int(40) NOT NULL DEFAULT 0,
  `Level` int(40) NOT NULL,
  PRIMARY KEY (`User_Name`) USING BTREE,
  UNIQUE INDEX `User_name`(`User_Name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('aaaa', '123456qaz', 0, 2);
INSERT INTO `tb_user` VALUES ('abc', '963258qwe', 0, 2);
INSERT INTO `tb_user` VALUES ('adaa', '123qaz', 0, 2);
INSERT INTO `tb_user` VALUES ('adad', '147qaz', 0, 3);
INSERT INTO `tb_user` VALUES ('admin', '123456qaz', 0, 1);
INSERT INTO `tb_user` VALUES ('def', '123456qaz', 0, 3);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shelves_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE,
  INDEX `shelves_id`(`shelves_id`) USING BTREE,
  CONSTRAINT `a` FOREIGN KEY (`shelves_id`) REFERENCES `shelves` (`shelves_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '生活用品', '1');
INSERT INTO `type` VALUES (2, '医用药物', '2');
INSERT INTO `type` VALUES (3, '化学品', '2');
INSERT INTO `type` VALUES (4, '农业用品', '2');
INSERT INTO `type` VALUES (5, '文具', '3');
INSERT INTO `type` VALUES (6, '服装', '4');
INSERT INTO `type` VALUES (7, '电子产品', '3');
INSERT INTO `type` VALUES (8, '熟食', '5');
INSERT INTO `type` VALUES (9, '冻食品', '3');

-- ----------------------------
-- Triggers structure for table sales
-- ----------------------------
DROP TRIGGER IF EXISTS `a`;
delimiter ;;
CREATE TRIGGER `a` AFTER INSERT ON `sales` FOR EACH ROW update commodity set number=number-new.number WHERE Commodity_Name = new.Commodity_Name
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
