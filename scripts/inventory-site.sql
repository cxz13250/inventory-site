/*
 Navicat Premium Data Transfer

 Source Server         : cxz
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : inventory-site

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 01/10/2018 15:41:03 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `bank_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `account`
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('1', '29889', '华夏银行', '2017-12-21 01:29:30', b'0', '岳云鹏', '6514276712352324658'), ('2', '50000', '招商银行', '2017-12-21 01:31:53', b'0', '郭德纲', '6517233354899321167'), ('3', '20000', '工商银行', '2017-12-21 01:35:02', b'0', '于谦', '6517788883456958348'), ('4', '40000', '农业银行', '2017-12-21 01:35:47', b'0', '马三立', '6517178825583667877'), ('5', '30000', '建设银行', '2017-12-21 01:37:09', b'0', '曹云金', '6517276993444856318');
COMMIT;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `super_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `category`
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('1', '2017-12-20 20:15:15', b'0', '灯具上方较平，安装时底部完全贴在屋顶上所以称之为吸顶灯。', '吸顶灯', '-1'), ('2', '2017-12-21 02:05:26', b'0', '吊装在室内天花板上的高级装饰用照明灯。', '吊灯', '-1'), ('3', '2017-12-21 02:05:52', b'0', '安装在室内墙壁上的辅助照明装饰灯具。', '壁灯', '-1'), ('4', '2017-12-21 02:06:43', b'0', '不知道怎么描述。', '过道灯', '-1'), ('5', '2017-12-21 02:07:21', b'0', '顾名思义就是在客厅的灯。', '客厅灯', '-1'), ('6', '2017-12-21 02:07:47', b'0', '又称为省电灯泡、电子灯泡、紧凑型荧光灯及一体式荧光灯，是指将荧光灯与镇流器（安定器）组合成一个整体的照明设备。 ', '节能灯', '-1'), ('7', '2017-12-21 02:08:32', b'0', '一块电致发光的半导体材料芯片，用银胶或白胶固化到支架上，然后用银线或金线连接芯片和电路板，四周用环氧树脂密封，起到保护内部芯线的作用，最后安装外壳，所以 LED 灯的抗震性能好。', 'LED灯', '-1'), ('8', '2017-12-21 02:09:08', b'0', '指有一个螺口灯头，可以直接装上白炽灯或节能灯的灯具。筒灯是一种嵌入到天花板内光线下射式的照明灯具。', '筒灯', '-1'), ('9', '2017-12-21 11:52:33', b'0', '没什么好描述的', '分段式吸顶灯', '1'), ('10', '2017-12-22 22:33:51', b'0', '一股西方的神秘力量。', '欧式吊灯', '2'), ('11', '2017-12-22 22:35:13', b'0', '欧式树脂吊灯', '树脂吊灯', '2'), ('12', '2017-12-22 22:36:36', b'0', '不懂。', '方罩吸顶灯', '1'), ('13', '2017-12-22 22:36:50', b'0', '不懂', '圆球吸顶灯', '1'), ('14', '2017-12-22 22:37:16', b'0', '不懂。', '小长方罩吸顶灯', '1'), ('15', '2017-12-22 22:38:31', b'0', '可能是放在镜子前面的吧。', '镜前壁灯', '3'), ('16', '2017-12-22 22:38:47', b'0', '不懂。', '玉柱壁灯', '3'), ('17', '2017-12-22 22:39:02', b'0', '不懂。', '双头花边杯壁灯', '3'), ('18', '2017-12-22 22:40:36', b'0', '可能比较扁吧。', '扁球吸顶灯', '1'), ('19', '2017-12-22 22:56:32', b'0', '顾名思义', '花型吸顶灯', '1');
COMMIT;

-- ----------------------------
--  Table structure for `complimentary`
-- ----------------------------
DROP TABLE IF EXISTS `complimentary`;
CREATE TABLE `complimentary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `custom_id` bigint(20) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `complimentary`
-- ----------------------------
BEGIN;
INSERT INTO `complimentary` VALUES ('1', '[{\"goodName\":\"赛朵描金花纹小号吸顶灯\",\"money\":250,\"sum\":2}]', '2017-12-23 02:22:49', '1', b'0', '250', '3', null), ('2', '[{\"money\":250,\"goodName\":\"赛朵描金花纹小号吸顶灯\",\"sum\":2}]', '2017-12-23 13:08:56', '1', b'0', '250', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `custom`
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pay` double DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `receive` double DEFAULT NULL,
  `receive_limit` double DEFAULT NULL,
  `salesman_id` varchar(255) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `custom`
-- ----------------------------
BEGIN;
INSERT INTO `custom` VALUES ('1', '杭州市阿里巴巴总部', '2017-12-21 01:47:53', 'mayun@alibaba.com', b'0', '5', '13188888888', '马云', '-111', '3100000', '0', '0', '刘锦涛', '1'), ('2', '深圳市南山区科技园飞亚达大厦10楼101室', '2017-12-21 01:50:23', 'mht@qq.com', b'0', '5', '13166666666', '马化腾', '2030', '518057', '0', '0', '秦泽民', '1'), ('3', '北京市北四环西路58号理想国际大厦12层', '2017-12-21 01:55:28', 'lyh@baidu.com', b'0', '5', '15188888888', '李彦宏', '0', '100080', '0', '0', '刘锦涛', '2'), ('4', '北京市朝阳区北辰西路8号北辰世纪中心A座', '2017-12-21 01:57:14', 'lqd@jingdong.com', b'0', '4', '15166666666', '刘强东', '940', '100176', '0', '0', '秦泽民', '2'), ('5', '浙江省杭州市滨江区网商路599号', '2017-12-21 01:59:50', 'dinglei@163.com', b'0', '4', '17588888888', '丁磊', '0', '510665', '0', '0', '刘锦涛', '1'), ('6', '中国北京海淀区创业路6号联想新大厦', '2017-12-21 02:01:24', 'lcz@lenovo.com', b'0', '4', '17566666666', '柳传志', '0', '100085', '0', '0', '秦泽民', '1'), ('7', '深圳市龙岗区坂田华为基地', '2017-12-21 02:02:58', 'rzf@huawei.com', b'0', '4', '18188888888', '任正非', '0', '518129', '0', '0', '刘锦涛', '1'), ('8', '北京市海淀区科学院南路2号院3号楼', '2017-12-21 02:04:17', 'zcy@souhu.com', b'0', '3', '18166666666', '张朝阳', '0', '100190', '0', '0', '秦泽民', '1');
COMMIT;

-- ----------------------------
--  Table structure for `good_item`
-- ----------------------------
DROP TABLE IF EXISTS `good_item`;
CREATE TABLE `good_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `extra` varchar(255) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `good_name` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `sum` double DEFAULT NULL,
  `good_model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `good_item`
-- ----------------------------
BEGIN;
INSERT INTO `good_item` VALUES ('1', '无', '6', '赛朵花型水晶吸顶灯', '10', '1', '468', '4680', null), ('2', '无', '5', '赛朵欧美六头玻璃灯罩吊灯', '10', '1', '599', '5990', null), ('3', '啊啊啊', '3', '赛朵描金花纹小号吸顶灯', '10', '2', '125', '1250', 'SD-FLXS-'), ('4', null, '12', '赛朵简约时尚led水晶玄关灯', '10', '2', '78', '780', 'SD-HR007-12-3W'), ('5', '啊啊', '8', '赛朵欧美玻璃印花罩吸顶灯', '10', '3', '163', '1630', 'DBJ-YD-035-XDD');
COMMIT;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` bigint(20) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `current_cost_price` double DEFAULT NULL,
  `current_retail_price` double DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `inventory` bigint(20) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `retail_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('1', '9', '150', '169', '499', b'0', '0', 'AKE-YJQ-9459-X', '赛朵水晶玻璃', '450'), ('3', '18', '66', '67', '135', b'0', '0', 'SD-FLXS-', '赛朵描金花纹小号吸顶灯', '125'), ('5', '10', '220', '220', '559', b'0', '0', 'AKE-SM-D0008-DD', '赛朵欧美六头玻璃灯罩吊灯', '599'), ('6', '19', '280', '280', '468', b'0', '0', 'DBJ-MC-YH8041-XDD', '赛朵花型水晶吸顶灯', '468'), ('7', '13', '15', '15', '60', b'0', '0', 'SD-PFE-ZLL-XDD', '亚克力紫罗兰吸顶灯', '50'), ('8', '18', '144', '144', '163', b'0', '0', 'DBJ-YD-035-XDD', '赛朵欧美玻璃印花罩吸顶灯', '163'), ('9', '11', '356', '356', '739', b'0', '0', 'SD-HT-97056-6L-DD', '赛朵光明之神阿波罗树脂灯', '839'), ('10', '7', '15', '15', '25', b'0', '0', 'SD-MPZM-THD088-NB', '赛朵3w联体透镜暖白光射灯', '25'), ('11', '7', '45', '45', '95', b'0', '0', 'SD-FLXS-GJ-T-001-5W', '赛朵欧美精美天花射灯5W暖白光', '95'), ('12', '7', '38', '38', '78', b'0', '10', 'SD-HR007-12-3W', '赛朵简约时尚led水晶玄关灯', '78'), ('13', '8', '18', '18', '38', b'0', '0', 'JLN-GY-NB01-3W', '杰雷诺陶瓷欧式筒灯', '38'), ('14', '15', '66', '66', '143', b'0', '0', 'DBJ-XY-BR033-1', '赛朵现代透明水晶壁灯', '143');
COMMIT;

-- ----------------------------
--  Table structure for `manage_info`
-- ----------------------------
DROP TABLE IF EXISTS `manage_info`;
CREATE TABLE `manage_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_income` double(20,0) DEFAULT NULL,
  `sale_discount` double(20,0) DEFAULT NULL,
  `goods_income` double(20,0) DEFAULT NULL,
  `goods_discount` double(20,0) DEFAULT NULL,
  `sale_cost` double(20,0) DEFAULT NULL,
  `goods_cost` double(20,0) DEFAULT NULL,
  `profit` double(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `manage_info`
-- ----------------------------
BEGIN;
INSERT INTO `manage_info` VALUES ('1', '100000', '2000', '20000', '1000', '16000', '20000', '84000');
COMMIT;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('1', '商品列表', '商品管理', '2', '/goods/list'), ('2', '分类列表', '商品管理', '2', '/category/list'), ('3', '赠送单', '库存管理', '2', '/stock/complimentary/list'), ('4', '报溢单', '库存管理', '2', '/stock/list?type=3'), ('5', '报警单', '库存管理', '2', '/stock/list?type=1'), ('6', '报损单', '库存管理', '2', '/stock/list?type=2'), ('7', '客户列表', '客户管理', '3', '/custom/list'), ('8', '添加客户', '客户管理', '3', '/custom/detail'), ('9', '进货单', '进货管理', '3', '/purchase/list?type=1'), ('10', '退货单', '进货管理', '3', '/purchase/list?type=0'), ('11', '销售单', '销售管理', '3', '/sale/order/list?type=1'), ('12', '退货单', '销售管理', '3', '/sale/order/list?type=0'), ('13', '账户列表', '账户管理', '4', '/account/list'), ('14', '添加账户', '账户管理', '4', '/account/detail'), ('15', '收款单', '财务管理', '4', '/receipt/list'), ('16', '付款单', '财务管理', '4', '/payment/list'), ('17', '促销策略', '销售管理', '5', '/sale/strategy/list'), ('18', '日志列表', '系统日志', '5', '/user/operation'), ('19', '日志列表', '系统日志', '1', '/user/operation'), ('20', '用户列表', '用户管理', '1', '/user/list'), ('21', '添加用户', '用户管理', '1', '/user/detail'), ('22', '赠送单', '库存管理', '5', '/stock/complimentary/list'), ('23', '报溢单', '库存管理', '5', '/stock/list?type=3'), ('24', '报警单', '库存管理', '5', '/stock/list?type=1'), ('25', '报损单', '库存管理', '5', '/stock/list?type=2'), ('26', '进货单', '进货管理', '5', '/purchase/list?type=1'), ('27', '退货单', '进货管理', '5', '/purchase/list?type=0'), ('28', '销售单', '销售管理', '5', '/sale/order/list?type=1'), ('29', '退货单', '销售管理', '5', '/sale/order/list?type=0'), ('30', '收款单', '财务管理', '5', '/receipt/list'), ('31', '付款单', '财务管理', '5', '/payment/list'), ('32', '赠送单', '库存管理', '1', '/stock/complimentary/list'), ('33', '报溢单', '库存管理', '1', '/stock/list?type=3'), ('34', '报警单', '库存管理', '1', '/stock/list?type=1'), ('35', '报损单', '库存管理', '1', '/stock/list?type=2'), ('36', '进货单', '进货管理', '1', '/purchase/list?type=1'), ('37', '退货单', '进货管理', '1', '/purchase/list?type=0'), ('38', '销售单', '销售管理', '1', '/sale/order/list?type=1'), ('39', '退货单', '销售管理', '1', '/sale/order/list?type=0'), ('40', '收款单', '财务管理', '1', '/receipt/list'), ('41', '付款单', '财务管理', '1', '/payment/list'), ('42', '经营情况', '财务管理', '4', '/manage/info'), ('43', '经营情况', '财务管理', '5', '/manage/info'), ('44', '经营情况', '财务管理', '1', '/manage/info');
COMMIT;

-- ----------------------------
--  Table structure for `payment`
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `total` double(20,0) DEFAULT NULL,
  `custom` bigint(20) DEFAULT NULL,
  `account` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `payment`
-- ----------------------------
BEGIN;
INSERT INTO `payment` VALUES ('1', '2017-12-23 02:07:03', b'0', 'XJFYD-2017-12-23 02:07:02.61', 'Mr.哈哈哈', '2', '111', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `payment_entry`
-- ----------------------------
DROP TABLE IF EXISTS `payment_entry`;
CREATE TABLE `payment_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `extra` varchar(255) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `payment_entry`
-- ----------------------------
BEGIN;
INSERT INTO `payment_entry` VALUES ('1', 'asdsa', '111', 'sad', '1');
COMMIT;

-- ----------------------------
--  Table structure for `purchase_order`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `repository` varchar(255) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `supplier` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `type` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `purchase_order`
-- ----------------------------
BEGIN;
INSERT INTO `purchase_order` VALUES ('1', '2017-12-22 23:11:32', b'0', '非常好', 'JHTHD-2017-12-22-00001', 'Mr.哈哈哈', '南京玄武仓库', '3', '2', '10670', b'1'), ('2', '2017-12-23 01:48:11', b'0', '啊啊啊', 'JHD-2017-12-23-00002', 'Mr.哈哈哈', '南京玄武仓库', '2', '2', '2030', b'1'), ('3', '2017-12-31 22:53:21', b'0', '啊啊', 'JHD-2017-12-31-00003', 'Mr.哈哈哈', '南京玄武仓库', '0', '1', '1630', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `receipt`
-- ----------------------------
DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `custom_id` bigint(20) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `transfer_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `receipt`
-- ----------------------------
BEGIN;
INSERT INTO `receipt` VALUES ('1', '2017-12-23 01:19:46', '1', b'0', 'SKD-2017-12-23-00001', 'Mr.哈哈哈', '0', '12', '[{\"money\":12,\"accountName\":\"岳云鹏\",\"extra\":\"11\"}]'), ('2', '2017-12-23 01:24:26', '1', b'0', 'SKD-2017-12-23-00002', 'Mr.哈哈哈', '0', '2', '[{\"money\":2,\"accountName\":\"郭德纲\",\"extra\":\"as\"}]'), ('3', '2017-12-23 01:33:22', '1', b'0', 'SKD-2017-12-23-00003', 'Mr.哈哈哈', '0', '12', '[{\"money\":12,\"accountName\":\"岳云鹏\",\"extra\":\"1\"}]'), ('4', '2017-12-23 01:38:24', '1', b'0', 'SKD-2017-12-23-00004', 'Mr.哈哈哈', '0', '1', '[{\"money\":1,\"accountName\":\"岳云鹏\",\"extra\":\"a\"}]'), ('5', '2017-12-23 01:42:07', '2', b'0', 'SKD-2017-12-23-00005', 'Mr.哈哈哈', '3', '1', '[{\"money\":1,\"accountName\":\"岳云鹏\",\"extra\":\"aa\"}]'), ('6', '2017-12-23 01:44:15', '1', b'0', 'SKD-2017-12-23-00006', 'Mr.哈哈哈', '1', '1', '[{\"money\":1,\"accountName\":\"曹云金\",\"extra\":\"1\"}]');
COMMIT;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', '2017-12-16 18:01:48', '系统管理员'), ('2', '2017-12-16 18:02:32', '库存管理人员'), ('3', '2017-12-16 18:02:44', '进货销售人员'), ('4', '2017-12-16 18:03:01', '财务人员'), ('5', '2017-12-16 18:03:15', '总经理');
COMMIT;

-- ----------------------------
--  Table structure for `sale_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sale_detail`;
CREATE TABLE `sale_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_delete` bit(1) DEFAULT NULL,
  `good_name` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `sale_time` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sale_order`
-- ----------------------------
DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE `sale_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `custom_id` bigint(20) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `repository` varchar(255) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `type` bit(1) DEFAULT NULL,
  `sales_man` varchar(255) DEFAULT NULL,
  `final_total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sale_order`
-- ----------------------------
BEGIN;
INSERT INTO `sale_order` VALUES ('1', '2017-12-23 00:50:12', '3', b'0', '10', '哈哈', 'XSTHD-2017-12-23-00001', 'Mr.哈哈哈', '南京玄武仓库', '2', '4500', b'1', '刘锦涛', '4490'), ('2', '2017-12-23 01:09:01', '4', b'0', '10', '哈哈', 'XSTHD-2017-12-23-00002', 'Mr.哈哈哈', '南京玄武仓库', '2', '950', b'1', '秦泽民', '940'), ('3', '2018-01-09 14:50:09', '3', b'0', '20', '好', 'XSTHD-2018-01-09-00003', 'Mr.哈哈哈', '南京玄武仓库', '0', '250', b'1', '刘锦涛', '230');
COMMIT;

-- ----------------------------
--  Table structure for `sale_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `sale_order_item`;
CREATE TABLE `sale_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `sum` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sale_order_item`
-- ----------------------------
BEGIN;
INSERT INTO `sale_order_item` VALUES ('1', '2017-12-23 00:50:12', '哈哈', '1', '1', '450', '10', '4500'), ('2', '2017-12-23 01:09:01', null, '3', '2', '95', '10', '950'), ('3', '2018-01-09 14:50:09', '好', '10', '3', '25', '10', '250');
COMMIT;

-- ----------------------------
--  Table structure for `sale_strategy`
-- ----------------------------
DROP TABLE IF EXISTS `sale_strategy`;
CREATE TABLE `sale_strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sale_strategy`
-- ----------------------------
BEGIN;
INSERT INTO `sale_strategy` VALUES ('1', '日光灯管一块钱一斤', b'0', '2018-01-31 22:01:00', '策略测试一', '2017-12-06 20:01:00');
COMMIT;

-- ----------------------------
--  Table structure for `stock_order`
-- ----------------------------
DROP TABLE IF EXISTS `stock_order`;
CREATE TABLE `stock_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `stock_order`
-- ----------------------------
BEGIN;
INSERT INTO `stock_order` VALUES ('1', '2017-12-23 02:44:46', b'0', '1', '2', '1', '1'), ('2', '2017-12-23 02:48:21', b'0', '7', '1', '2', '1');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '2017-12-16 17:58:02', b'0', 'admin', 'admin', 'Mr.哈哈哈', 'ISMvKXpXpadDiUoOSoAfww=='), ('5', '2017-12-24 12:26:39', b'0', 'sales@qq.com', '17522244433', '张全蛋', 'ntCDsUNuX0DvmEsoJV7vGA=='), ('6', '2017-12-24 12:29:35', b'0', 'stock@qq.com', '18713151617', '王尼玛', 'kIiAIJpk6lOa6Nxf234KkQ=='), ('7', '2017-12-24 12:33:28', b'0', 'finacial@qq.com', '17513141516', '熊大', '8GiVzMN/zOjWzR0sz11EiQ=='), ('8', '2017-12-24 12:34:08', b'0', 'manager@qq.com', '17645162341', '光头强', 'HQJYwkQKjRnnFikrIx4xkA==');
COMMIT;

-- ----------------------------
--  Table structure for `user_2_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_2_role`;
CREATE TABLE `user_2_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_2_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_2_role` VALUES ('1', '2017-12-16 18:03:46', '1', '1'), ('2', '2017-12-16 18:04:01', '2', '1'), ('3', '2017-12-16 18:04:10', '3', '1'), ('4', '2017-12-16 18:04:16', '4', '1'), ('5', '2017-12-16 18:04:24', '5', '1'), ('6', '2017-12-24 12:26:45', '3', '5'), ('7', '2017-12-24 12:29:35', '2', '6'), ('8', '2017-12-24 12:33:28', '4', '7'), ('9', '2017-12-24 12:34:08', '5', '8');
COMMIT;

-- ----------------------------
--  Table structure for `user_operation`
-- ----------------------------
DROP TABLE IF EXISTS `user_operation`;
CREATE TABLE `user_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_operation`
-- ----------------------------
BEGIN;
INSERT INTO `user_operation` VALUES ('1', '2017-12-16 23:18:19', '127.0.0.1', 'login', '1'), ('2', '2017-12-16 23:21:29', '127.0.0.1', 'login', '1'), ('3', '2017-12-16 23:25:38', '127.0.0.1', 'login', '1'), ('4', '2017-12-16 23:27:18', '127.0.0.1', 'login', '1'), ('5', '2017-12-16 23:27:24', '127.0.0.1', 'login', '1'), ('6', '2017-12-17 00:17:16', '127.0.0.1', 'login', '1'), ('7', '2017-12-17 00:40:16', '127.0.0.1', 'login', '1'), ('8', '2017-12-17 01:06:28', '127.0.0.1', 'login', '1'), ('9', '2017-12-17 15:12:54', '127.0.0.1', 'login', '1'), ('10', '2017-12-17 15:58:44', '127.0.0.1', 'login', '1'), ('11', '2017-12-17 15:59:00', '127.0.0.1', 'login', '1'), ('12', '2017-12-17 16:01:32', '127.0.0.1', 'login', '1'), ('13', '2017-12-17 16:13:08', '127.0.0.1', 'register', '0'), ('14', '2017-12-17 16:31:05', '127.0.0.1', 'login', '1'), ('15', '2017-12-17 16:36:13', '127.0.0.1', 'login', '1'), ('16', '2017-12-17 16:39:21', '127.0.0.1', 'login', '1'), ('17', '2017-12-17 16:42:28', '127.0.0.1', 'login', '1'), ('18', '2017-12-17 16:54:58', '127.0.0.1', 'login', '1'), ('19', '2017-12-17 23:53:52', '127.0.0.1', 'login', '1'), ('20', '2017-12-18 13:37:44', '127.0.0.1', 'login', '1'), ('21', '2017-12-18 13:38:17', '127.0.0.1', 'register', '0'), ('22', '2017-12-18 13:38:27', '127.0.0.1', 'login', '1'), ('23', '2017-12-18 13:41:23', '127.0.0.1', 'register', '0'), ('24', '2017-12-18 13:46:58', '127.0.0.1', 'register', '0'), ('25', '2017-12-18 13:47:51', '127.0.0.1', 'register', '0'), ('26', '2017-12-18 13:55:49', '127.0.0.1', 'login', '1'), ('27', '2017-12-18 13:56:01', '127.0.0.1', 'register', '0'), ('28', '2017-12-18 13:56:31', '127.0.0.1', 'login', '1'), ('29', '2017-12-18 15:05:50', '127.0.0.1', 'login', '1'), ('30', '2017-12-18 16:13:15', '127.0.0.1', 'login', '1'), ('31', '2017-12-18 19:25:19', '127.0.0.1', 'login', '1'), ('32', '2017-12-18 21:43:45', '127.0.0.1', 'login', '1'), ('33', '2017-12-19 00:33:31', '127.0.0.1', 'login', '1'), ('34', '2017-12-19 00:50:07', '127.0.0.1', 'login', '1'), ('35', '2017-12-19 01:18:06', '127.0.0.1', 'login', '1'), ('36', '2017-12-19 01:40:15', '127.0.0.1', 'login', '1'), ('37', '2017-12-19 14:53:17', '127.0.0.1', 'login', '1'), ('38', '2017-12-19 14:59:42', '127.0.0.1', 'login', '1'), ('39', '2017-12-20 00:11:55', '127.0.0.1', 'login', '1'), ('40', '2017-12-20 00:34:48', '127.0.0.1', 'login', '1'), ('41', '2017-12-20 01:07:29', '127.0.0.1', 'login', '1'), ('42', '2017-12-20 01:09:30', '127.0.0.1', 'login', '1'), ('43', '2017-12-20 01:18:56', '127.0.0.1', 'login', '1'), ('44', '2017-12-20 01:28:40', '127.0.0.1', 'login', '1'), ('45', '2017-12-20 19:44:08', '127.0.0.1', 'login', '1'), ('46', '2017-12-20 20:13:52', '127.0.0.1', 'login', '1'), ('47', '2017-12-20 20:44:39', '127.0.0.1', 'login', '1'), ('48', '2017-12-20 20:52:26', '127.0.0.1', 'login', '1'), ('49', '2017-12-21 01:26:24', '127.0.0.1', 'login', '1'), ('50', '2017-12-21 01:43:51', '127.0.0.1', 'login', '1'), ('51', '2017-12-21 10:58:51', '127.0.0.1', 'login', '1'), ('52', '2017-12-21 11:22:08', '127.0.0.1', 'login', '1'), ('53', '2017-12-21 13:07:34', '127.0.0.1', 'login', '1'), ('54', '2017-12-21 16:54:02', '127.0.0.1', 'login', '1'), ('55', '2017-12-22 17:26:50', '127.0.0.1', 'login', '1'), ('56', '2017-12-22 19:30:13', '127.0.0.1', 'login', '1'), ('57', '2017-12-22 19:32:01', '127.0.0.1', 'login', '1'), ('58', '2017-12-22 20:13:16', '127.0.0.1', 'login', '1'), ('59', '2017-12-22 20:18:33', '127.0.0.1', 'login', '1'), ('60', '2017-12-22 20:20:00', '127.0.0.1', 'login', '1'), ('61', '2017-12-22 20:23:35', '127.0.0.1', 'login', '1'), ('62', '2017-12-22 22:32:36', '127.0.0.1', 'login', '1'), ('63', '2017-12-22 22:37:44', '127.0.0.1', 'login', '1'), ('64', '2017-12-23 01:25:51', '127.0.0.1', 'login', '1'), ('65', '2017-12-23 01:46:17', '127.0.0.1', 'login', '1'), ('66', '2017-12-23 02:12:58', '127.0.0.1', 'login', '1'), ('67', '2017-12-23 02:13:53', '127.0.0.1', 'login', '1'), ('68', '2017-12-23 12:40:12', '127.0.0.1', 'login', '1'), ('69', '2017-12-23 13:06:02', '127.0.0.1', 'login', '1'), ('70', '2017-12-24 11:23:01', '127.0.0.1', 'login', '1'), ('71', '2017-12-24 12:33:28', '127.0.0.1', 'register', '1'), ('72', '2017-12-24 12:34:08', '127.0.0.1', 'register', '1'), ('73', '2017-12-24 12:34:23', '127.0.0.1', 'login', '8'), ('74', '2017-12-24 12:39:53', '127.0.0.1', 'login', '6'), ('75', '2017-12-24 12:40:49', '127.0.0.1', 'login', '7'), ('76', '2017-12-24 12:43:58', '127.0.0.1', 'login', '5'), ('77', '2017-12-24 12:44:31', '127.0.0.1', 'login', '1'), ('78', '2017-12-24 12:51:16', '127.0.0.1', 'login', '1'), ('79', '2017-12-24 12:52:58', '127.0.0.1', 'login', '1'), ('80', '2017-12-24 12:53:43', '127.0.0.1', 'login', '5'), ('81', '2017-12-24 13:00:15', '127.0.0.1', 'login', '1'), ('82', '2017-12-31 22:52:58', '127.0.0.1', 'login', '1'), ('83', '2018-01-06 14:56:26', '127.0.0.1', 'login', '1'), ('84', '2018-01-06 14:56:47', '127.0.0.1', 'login', '8'), ('85', '2018-01-09 14:48:59', '127.0.0.1', 'login', '1'), ('86', '2018-01-09 14:50:51', '127.0.0.1', 'login', '8'), ('87', '2018-01-09 14:53:52', '127.0.0.1', 'login', '6'), ('88', '2018-01-09 15:26:19', '127.0.0.1', 'login', '7'), ('89', '2018-01-09 15:26:42', '127.0.0.1', 'login', '6'), ('90', '2018-01-09 15:27:02', '127.0.0.1', 'login', '5');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
