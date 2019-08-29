/*
 Navicat Premium Data Transfer

 Source Server         : aliyun@xxxxxxxx-root
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : xxxxxxxxx:3306
 Source Schema         : xbook

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/08/2019 21:31:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES (179, 53, 29, 2, 1, '2019-08-29 21:20:33', NULL);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (100001, 0, '计算机与互联网', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100002, 0, '小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100003, 0, '教育', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100004, 0, '文学', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100005, 0, '历史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100006, 100001, '编程与开发', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100007, 100001, '操作系统', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100008, 100001, '数据库', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100009, 100001, '网络与通讯', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100010, 100001, '云计算与大数据', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100011, 100002, '推理小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100012, 100002, '玄幻小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100013, 100002, '言情小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100014, 100002, '武侠小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100015, 100002, '军事小说', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100016, 100003, '教育学', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100017, 100003, '思想政治教育', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100018, 100003, '教学理论', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100019, 100003, '职业技术教育', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100020, 100003, '中国教育事业', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100021, 100004, '文学史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100022, 100004, '散文随笔', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100023, 100004, '影视文学', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100024, 100004, '诗歌词曲', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100025, 100004, '戏剧与曲艺', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100026, 100005, '中国史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100027, 100005, '欧洲史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100028, 100005, '美洲史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100029, 100005, '非洲史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
INSERT INTO `category` VALUES (100030, 100005, '亚洲史', 1, NULL, '2019-08-23 18:12:36', '2019-08-23 18:12:41');
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (139, 53, 156708448609522, 28, 'Netty权威指南（第2版）', '2019/08/28/lniZ65aq8vLtXpE.jpg', 85.00, 1, 85.00, '2019-08-29 21:14:46', NULL);
INSERT INTO `order_item` VALUES (140, 53, 156708448609522, 33, 'Redis实战', '2019/08/28/ZdPsRxStacp4X2N.jpg', 66.00, 2, 132.00, '2019-08-29 21:14:46', NULL);
COMMIT;

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_main
-- ----------------------------
BEGIN;
INSERT INTO `order_main` VALUES (121, 156708448609522, 53, 37, 217.00, 1, 0, 10, NULL, NULL, NULL, NULL, '2019-08-29 21:14:46', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应mmall_category表的主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text COMMENT '图片地址,json格式,扩展用',
  `detail` text COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (26, 100006, 'Java从入门到精通（第5版）', '297个应用实例+37个典型应用+30小时教学视频+海量开发资源库', '2019/08/27/9JDeOW6v42Qubmh.jpg', '2019/08/27/9JDeOW6v42Qubmh.jpg,2019/08/27/1udOjoTmgBfp6JV.jpg,2019/08/27/eVKTBAaHJcvQEim.jpg', '<p><img src=\"https://i.loli.net/2019/08/27/QwVft5Cy4l7X18Z.jpg\" width=\"790\"><br></p>', 69.00, 10000, 1, '2019-08-27 18:56:28', '2019-08-27 18:51:54');
INSERT INTO `product` VALUES (27, 100006, '大话Java：程序设计从入门到精通', '跟着孙悟空学Java，从此告别枯燥的技术学习', '2019/08/27/1OrzZJILmutQqsi.jpg', '2019/08/27/1OrzZJILmutQqsi.jpg,2019/08/27/qJp4KAT8tbMGRDI.jpg,2019/08/27/lzv3DKdSXkBsAen.jpg', '<p><img src=\"https://i.loli.net/2019/08/27/mwsQ6JhxaYclNfz.jpg\" width=\"790\"><br></p>', 76.00, 10000, 1, '2019-08-27 18:51:54', '2019-08-27 18:51:54');
INSERT INTO `product` VALUES (28, 100006, 'Netty权威指南（第2版）', '深入剖析Netty著作升级版，更全面系统讲解底层架构、实践与源码', '2019/08/28/lniZ65aq8vLtXpE.jpg', '2019/08/28/lniZ65aq8vLtXpE.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/eSmIDcEdf12qvNp.png\" width=\"790\"><br></p>', 85.00, 9999, 1, '2019-08-27 18:51:54', '2019-08-27 18:51:54');
INSERT INTO `product` VALUES (29, 100006, 'Spring Boot编程思想（核心篇）', '以SpringBoot为中心，议题发散至Spring技术栈', '2019/08/28/e4SvJYl9TIDyGQu.jpg', '2019/08/28/e4SvJYl9TIDyGQu.jpg,2019/08/28/zGJigoTUf8CBqam.jpg,2019/08/28/pZoGaNtQHFE4qhJ.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/Y2iuB6ZgXqHFrbz.jpg\" width=\"790\"><br></p>', 110.00, 10000, 1, '2019-08-27 18:51:54', '2019-08-27 18:51:54');
INSERT INTO `product` VALUES (30, 100006, 'Python编程 从入门到实践', 'Python3.5编程入门图书 机器学习 数据处理 网络爬虫热门编程语言', '2019/08/28/YoieCkS7n2XMHms.jpg', '2019/08/28/YoieCkS7n2XMHms.jpg,2019/08/28/AOeBtdDlJsgrIN4.jpg,2019/08/28/DOXjUt8TogledEC.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/CdpE7xzYBwPt5nJ.jpg\" width=\"790\"><br></p>', 62.00, 10000, 1, '2019-08-28 16:35:53', '2019-08-28 16:35:55');
INSERT INTO `product` VALUES (31, 100007, '鸟哥的Linux私房菜 基础学习篇 第四版', '畅销Linux入门书升级版 鸟哥教你从入门到精通 适用Linux系统应用和开发及运维的人员 涵盖linux内核 命令行 嵌入式 Shell与Shell Scripts技巧精粹', '2019/08/28/3zFAjK4wMJiNQh9.jpg', '2019/08/28/3zFAjK4wMJiNQh9.jpg,2019/08/28/RM9x23bcdwvmH87.jpg,2019/08/28/6PE5XHQUmaq9LsJ.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/DjXGxHoSInPigrC.jpg\" width=\"790\"><br></p>', 86.00, 10000, 1, '2019-08-28 16:42:07', '2019-08-28 16:42:09');
INSERT INTO `product` VALUES (32, 100008, 'MySQL 5.7从入门到精通（第2版）', '本书提供大量的数据库使用方法和操作案例，让你看得懂、学得会、做得出', '2019/08/28/RKGF6j4BZyX3Hz7.jpg', '2019/08/28/RKGF6j4BZyX3Hz7.jpg,2019/08/28/jC16LhSHOf4DrEv.jpg,2019/08/28/wTdv6N5tIXzZWcs.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/68VJDQ3yIX1mjTr.jpg\" width=\"790\"><br></p>', 109.00, 10000, 1, '2019-08-28 16:49:59', '2019-08-28 16:50:01');
INSERT INTO `product` VALUES (33, 100008, 'Redis实战', 'Redis设计与实现作者黄健宏译 Redis之父作序推荐 Redis开发与运维入门指南', '2019/08/28/ZdPsRxStacp4X2N.jpg', '2019/08/28/ZdPsRxStacp4X2N.jpg,2019/08/28/Xm9qwQ7gseBlMD5.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/vEcY6wVXMnPyR7z.jpg\" width=\"790\"><br></p>', 66.00, 9998, 1, '2019-08-28 16:53:44', '2019-08-28 16:53:46');
INSERT INTO `product` VALUES (34, 100009, '深入浅出 HTTPS：从原理到实战', '资深一线架构师用心之作，囊括括密码学、OpenSSL命令行、证书、TLS协议、HTTPS网站性能优化、HTTPS网站优秀实践、大型网站HTTPS架构设计等', '2019/08/28/NXg6nVAdE2UiSwI.jpg', '2019/08/28/NXg6nVAdE2UiSwI.jpg,2019/08/28/vuTMeOFDpVjUZ5Y.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/cqtY1XKJMRU5xSb.jpg\" width=\"790\"><br></p>', 85.00, 10000, 1, '2019-08-28 17:04:15', '2019-08-28 17:04:18');
INSERT INTO `product` VALUES (35, 100010, 'Hadoop + Spark 大数据巨量分析与机器学习整合开发实战', '手把手教你学习Hadoop + Spark免费赠送范例程序下载', '2019/08/28/cX4byhoI2Qzwlas.jpg', '2019/08/28/cX4byhoI2Qzwlas.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/jug8sFKki9RlEWP.jpg\" width=\"790\"><br></p>', 55.00, 10000, 1, '2019-08-28 17:10:15', '2019-08-28 17:10:17');
INSERT INTO `product` VALUES (36, 100011, '东野圭吾四大推理套装（共4册）', '东野圭吾4部经典推理小说合集，含《嫌疑人X的献身》《恶意》《新参者》《放学后》，获直木奖等多项大奖，领衔日本三大推理小说榜年度排行', '2019/08/28/qVCJan7oQ6hHB8y.jpg', '2019/08/28/qVCJan7oQ6hHB8y.jpg,2019/08/28/DNP3iIyHAe28UVz.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/bkGxIdwy1PHN6Fg.jpg\" width=\"790\"><br></p>', 149.00, 10000, 1, '2019-08-28 17:14:12', '2019-08-28 17:14:14');
INSERT INTO `product` VALUES (37, 100012, '斗破苍穹全集第一辑', '天蚕土豆代表作足本，升级典藏版。起点中文网霸主级小说，亿万斗迷心中无法超越的经典', '2019/08/28/9xv4cXitR7qropK.jpg', '2019/08/28/9xv4cXitR7qropK.jpg,2019/08/28/8IfdWlQnBoHb2OL.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/TzkZKJIdEQUuYxG.jpg\" width=\"790\"><br></p>', 158.00, 10000, 1, '2019-08-28 17:20:01', '2019-08-28 17:20:03');
INSERT INTO `product` VALUES (38, 100016, '教育学 普通高等教育国家级规划教材 第七版', '普通高等教育', '2019/08/28/lywVXCchKGzpxPq.jpg', '2019/08/28/lywVXCchKGzpxPq.jpg,2019/08/28/1sYNzCyu8JUIeXr.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/MBTOXRGJoD6vZgl.jpg\" width=\"790\"><br></p>', 66.00, 10000, 1, '2019-08-28 17:24:46', '2019-08-28 17:24:48');
INSERT INTO `product` VALUES (39, 100022, '林清玄散文精选', '（套装两册，包含《气清景明，繁花盛开》和《人间有味是清欢》两册，你心柔软，却有力量', '2019/08/28/M9LlD2WSUNBiPZw.jpg', '2019/08/28/M9LlD2WSUNBiPZw.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/Rm3ZNBYy2jiDCU7.jpg\" width=\"790\"><br></p>', 50.00, 10000, 1, '2019-08-28 17:45:22', '2019-08-28 17:45:24');
INSERT INTO `product` VALUES (40, 100022, '冰心散文集 人生必读书', '冰心原创代表作诗歌+散文，充满童真、母爱与自然美。含《小桔灯》、《寄小读者》', '2019/08/28/HB6i4dfChyRZKqa.jpg', '2019/08/28/HB6i4dfChyRZKqa.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/cQP3C6y15vFHswm.jpg\" width=\"790\"><br></p>', 10.00, 10000, 1, '2019-08-28 17:51:22', '2019-08-28 17:51:24');
INSERT INTO `product` VALUES (41, 100023, '科学怪人', '开创文学史上的科幻小说先河，作品自1818年面世后被译成100多种语言，改编成近100种影视作品全球传播，并入选BBC文化专栏评选的“25部ZUI伟大英国小说”榜单（9-12岁）', '2019/08/28/BYLt28JyHceF9ql.jpg', '2019/08/28/BYLt28JyHceF9ql.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/CBMblsZHw76nYIq.jpg\" width=\"790\"><br></p>', 22.00, 10000, 1, '2019-08-28 18:01:41', '2019-08-28 18:01:44');
INSERT INTO `product` VALUES (42, 100023, '延禧攻略（全两册）', '2018暑期爆款清宫大戏！匠心巨制，讲述勇气与胸怀的正能量佳作！秦岚、聂远、佘诗曼、吴谨言、谭卓等领衔主演', '2019/08/28/JDoCk2QKYwcVrWn.png', '2019/08/28/JDoCk2QKYwcVrWn.png,2019/08/28/PvAhlzjr5Wb8a7x.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/5DkSb2RmXiTKgJ7.jpg\" width=\"790\"><br></p>', 70.00, 10000, 1, '2019-08-28 18:06:34', '2019-08-28 18:06:36');
INSERT INTO `product` VALUES (43, 100023, '寂寞空庭春欲晚（十周年影视典藏版）', '匪我思存古代文艺小说唯美典范︱刘恺威、郑爽、张彬彬、米雪领衔主演同名大型清装宫廷剧', '2019/08/28/oCgli7ZfOF6QB5J.jpg', '2019/08/28/oCgli7ZfOF6QB5J.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/YaFuTXUo4ZWp3gN.jpg\" width=\"790\"><br></p>', 35.00, 10000, 1, '2019-08-28 18:10:54', '2019-08-28 18:10:56');
INSERT INTO `product` VALUES (44, 100026, '中国史通论（精装带函套）', '（一代汉学宗师、京都学派创始人内藤湖南代表作，对欧、美、日汉学研究有深远影响的史学名著，研究中国历史绕不过去的一座高峰，可与钱穆《国史大纲》媲美）', '2019/08/28/78dTrym2AGgzHoa.jpg', '2019/08/28/78dTrym2AGgzHoa.jpg,2019/08/28/LCK1NtJn8SiE2f4.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/foVut3CwIrxEh7i.jpg\" width=\"790\"><br></p>', 158.00, 10000, 1, '2019-08-28 18:16:01', '2019-08-28 18:16:03');
INSERT INTO `product` VALUES (45, 100027, '极简欧洲史', '你一定爱读的极简欧洲史（增订纪念版） The Shortest History of Europe', '2019/08/28/xPkwyCSNgFrRXDY.jpg', '2019/08/28/xPkwyCSNgFrRXDY.jpg', '<p><img src=\"https://i.loli.net/2019/08/28/16fp7nIBajDgshY.jpg\" width=\"790\"><br></p>', 55.00, 10000, 1, '2019-08-28 18:22:00', '2019-08-28 18:22:03');
COMMIT;

-- ----------------------------
-- Table structure for shipping
-- ----------------------------
DROP TABLE IF EXISTS `shipping`;
CREATE TABLE `shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping
-- ----------------------------
BEGIN;
INSERT INTO `shipping` VALUES (37, 53, 'zero', '13866668888', '13866668888', '重庆市', '渝北区', NULL, '洪湖西路24号', '', '2019-08-29 21:13:08', NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (53, 'pingjin', 'E10ADC3949BA59ABBE56E057F20F883E', '73826742@qq.com', '13888886666', '123456', '123456', 0, '2019-08-25 18:20:02', '2019-08-25 18:22:14');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
