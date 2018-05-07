/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : cloud_admin

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-05-07 12:44:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_attachs
-- ----------------------------
DROP TABLE IF EXISTS `t_b_attachs`;
CREATE TABLE `t_b_attachs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `suffix` varchar(10) DEFAULT NULL COMMENT '文件后缀',
  `attach_type` tinyint(4) DEFAULT NULL COMMENT '关联类型 1、新闻资讯 2、项目 3、资讯富文本 4、项目富文本',
  `rel_id` bigint(20) DEFAULT NULL COMMENT '主记录id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='附件记录表';

-- ----------------------------
-- Records of t_b_attachs
-- ----------------------------
INSERT INTO `t_b_attachs` VALUES ('1', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/82ac908b-ec82-4a22-aebf-ca116cf71244.jpg', '.jpg', '1', '1', '2018-05-05 01:50:18', '2018-05-05 01:50:19');
INSERT INTO `t_b_attachs` VALUES ('4', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/572a6fb2-9980-4861-9c5d-faf3031ecadc.png', '.png', '1', '2', '2018-05-05 03:01:50', '2018-05-05 11:56:31');
INSERT INTO `t_b_attachs` VALUES ('5', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/fa3bbe0a-f838-4f1c-bc82-600f7b57623e.jpg', '.jpg', '1', '2', '2018-05-05 03:01:50', '2018-05-05 11:56:32');
INSERT INTO `t_b_attachs` VALUES ('6', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/f6b19c1b-430b-4bcb-a1d5-90dad537835d.png', '.png', '1', '2', '2018-05-05 03:05:53', '2018-05-05 11:56:33');
INSERT INTO `t_b_attachs` VALUES ('7', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/cc83c28c-39df-4e0b-965e-0a7a7bf7d18a.jpg', '.jpg', '1', '2', '2018-05-05 03:05:53', '2018-05-05 11:56:33');
INSERT INTO `t_b_attachs` VALUES ('9', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/9458bc39-ceaf-4e03-9b18-2506e099fa09.png', '.png', '1', '3', '2018-05-05 03:09:28', '2018-05-05 11:56:35');
INSERT INTO `t_b_attachs` VALUES ('10', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/dbf14d45-be36-4f29-b007-3088423d200d.jpg', '.jpg', '1', '3', '2018-05-05 03:09:28', '2018-05-05 11:56:35');
INSERT INTO `t_b_attachs` VALUES ('14', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/51d150c6-c11d-47ec-95c3-50a121432f5a.png', '.png', '1', '3', '2018-05-05 11:52:37', '2018-05-05 11:56:36');
INSERT INTO `t_b_attachs` VALUES ('15', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/b7438e8e-4be7-43b3-abcb-a8d1c52467bf.jpg', '.jpg', '1', '3', '2018-05-05 11:52:37', '2018-05-05 11:56:38');
INSERT INTO `t_b_attachs` VALUES ('16', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/9e50b70a-cb18-4c3d-a4ba-9474f3e0439a.jpg', '.jpg', null, null, '2018-05-05 12:29:24', null);
INSERT INTO `t_b_attachs` VALUES ('17', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/2fdf2129-7cb3-4962-83fc-0e2ae0f8188d.jpg', '.jpg', null, null, '2018-05-05 13:43:04', null);
INSERT INTO `t_b_attachs` VALUES ('18', '6712.png', 'image/png', '1', '4204', '/images/2018-05-05/e9b8956f-edcc-4a1d-8bdb-bc7efe7343b9.png', '.png', null, null, '2018-05-05 13:43:15', null);
INSERT INTO `t_b_attachs` VALUES ('19', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/2931653c-437d-4946-bfe3-1f356dcb8355.png', '.png', null, null, '2018-05-05 13:43:27', null);
INSERT INTO `t_b_attachs` VALUES ('20', '图片.jpg', 'image/jpeg', '1', '3182', '/images/2018-05-05/302fd845-3630-48f1-8eef-c7e5798e3885.jpg', '.jpg', null, null, '2018-05-05 14:08:16', null);
INSERT INTO `t_b_attachs` VALUES ('21', '图片.png', 'image/png', '1', '3182', '/images/2018-05-05/4c0801d6-87f7-41c2-8552-9ef936bfc98e.png', '.png', null, null, '2018-05-05 14:09:30', null);
INSERT INTO `t_b_attachs` VALUES ('23', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/0f48b9ba-9ec7-44c1-b27e-4963019f88f9.jpg', '.jpg', '1', null, '2018-05-05 14:10:22', null);
INSERT INTO `t_b_attachs` VALUES ('24', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/ed3adbd1-591a-43a9-ace4-cae61dae6063.png', '.png', null, null, '2018-05-05 14:10:41', null);
INSERT INTO `t_b_attachs` VALUES ('25', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/c4532e8c-6bae-4f79-add7-4f95c35458dc.png', '.png', null, null, '2018-05-05 14:17:47', null);
INSERT INTO `t_b_attachs` VALUES ('26', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/dc2c5df4-7c19-465e-9278-4366a6229dd5.png', '.png', '1', null, '2018-05-05 14:18:14', null);
INSERT INTO `t_b_attachs` VALUES ('27', '上传图片.png', 'image/png', '1', '245079', '/images/2018-05-05/15c0d1bf-10c3-4db3-8350-e70f1a307fcb.png', '.png', null, null, '2018-05-05 14:19:54', null);
INSERT INTO `t_b_attachs` VALUES ('28', 'test.jpg', 'image/jpeg', '1', '590623', '/images/2018-05-05/7ae55d43-c149-4512-88d6-771298bd98dd.jpg', '.jpg', '1', '5', '2018-05-05 14:20:13', '2018-05-05 14:20:23');
INSERT INTO `t_b_attachs` VALUES ('29', '图片.png', 'image/png', '1', '3182', '/images/2018-05-05/0bc25486-3e6c-420c-92d5-80ecd0c369e4.png', '.png', '1', null, '2018-05-05 15:12:23', null);
INSERT INTO `t_b_attachs` VALUES ('30', '正面小图.jpg', 'image/jpeg', '1', '36302', '/images/2018-05-06/590b706f-85d7-4ff2-891f-04bf9ba43a56.jpg', '.jpg', '2', null, '2018-05-06 00:24:56', null);
INSERT INTO `t_b_attachs` VALUES ('31', '反面小图.jpg', 'image/jpeg', '1', '67269', '/images/2018-05-06/cdd92524-f339-4e9b-a039-b2ac027da8b6.jpg', '.jpg', '2', null, '2018-05-06 00:24:56', null);
INSERT INTO `t_b_attachs` VALUES ('36', '上传图片.png', 'image/png', '1', '245079', 'http://images.lbtang.club/5eed4a34de0147a6b9293967f950c387.png', '.png', '1', null, '2018-05-06 16:19:07', null);
INSERT INTO `t_b_attachs` VALUES ('37', '上传图片.png', 'image/png', '1', '245079', 'http://images.lbtang.club/37df916cfb1e41d19ee5d1249feb067b.png', '.png', '2', '17', '2018-05-06 18:27:27', '2018-05-06 18:27:31');
INSERT INTO `t_b_attachs` VALUES ('38', '6712.png', 'image/png', '1', '4204', 'http://images.lbtang.club/9fa45a70636042b8802e0ef32704a556.png', '.png', '2', '18', '2018-05-06 18:28:32', '2018-05-06 18:29:00');

-- ----------------------------
-- Table structure for t_b_captcha
-- ----------------------------
DROP TABLE IF EXISTS `t_b_captcha`;
CREATE TABLE `t_b_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of t_b_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for t_b_member
-- ----------------------------
DROP TABLE IF EXISTS `t_b_member`;
CREATE TABLE `t_b_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '密码盐值',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `invite_code` varchar(20) DEFAULT NULL COMMENT '邀请码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别 1 男 0 女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `address` varchar(500) DEFAULT NULL COMMENT '通讯地址',
  `photo` varchar(50) DEFAULT NULL COMMENT '头像存储uuid',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of t_b_member
-- ----------------------------
INSERT INTO `t_b_member` VALUES ('3', '18818524559', 'd343f07e46d11f2d9cc9a3b430a60be61c0e48b91b512e250a026e7007f74a6e', 'bjJ9MgaiO9UyQOPb356a', '2018-03-24 21:07:05', null, '刘玉珠', '1', '25', '赛发动范东安抚赛奥法懂啊', null, '741258@qq.com', 'liuyuzhu', '1', '2018-05-06 19:14:26');

-- ----------------------------
-- Table structure for t_b_news
-- ----------------------------
DROP TABLE IF EXISTS `t_b_news`;
CREATE TABLE `t_b_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '资讯标题',
  `type` tinyint(2) DEFAULT NULL COMMENT '资讯类型 1 广告 2 新闻',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `show_flag` tinyint(2) DEFAULT NULL COMMENT '是否显示 1 显示 2 不显示',
  `content` varchar(5000) DEFAULT NULL COMMENT '资讯内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='新闻资讯表';

-- ----------------------------
-- Records of t_b_news
-- ----------------------------
INSERT INTO `t_b_news` VALUES ('1', '测试图片', '1', '1', '1', null, '2018-05-05 01:50:19', '2018-05-05 02:54:04');
INSERT INTO `t_b_news` VALUES ('2', '图片2', '1', '1', '2', null, '2018-05-05 03:02:10', null);
INSERT INTO `t_b_news` VALUES ('3', '图片3', '2', '1', '1', null, '2018-05-05 03:05:55', null);
INSERT INTO `t_b_news` VALUES ('5', '赛发动', '1', '1', '2', '<p>赛发动啊</p><p><u>或尝试哦</u></p><p><b>根据风景</b></p><p><b>跟战台风飓风</b></p><p><b><img src=\"/images/2018-05-05/15c0d1bf-10c3-4db3-8350-e70f1a307fcb.png\" alt=\"15c0d1bf-10c3-4db3-8350-e70f1a307fcb.png\"><br></b></p><p><b>钢结构出月</b></p><p>回避斩恒基</p><p>东方赛阿萨发的<img src=\"http://localhost:18080/static/layui/images/face/46.gif\" alt=\"[互粉]\"></p><p><strike>证件根据跟</strike></p>', '2018-05-05 14:20:23', '2018-05-05 14:22:10');

-- ----------------------------
-- Table structure for t_b_project
-- ----------------------------
DROP TABLE IF EXISTS `t_b_project`;
CREATE TABLE `t_b_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(200) DEFAULT NULL COMMENT '项目名称',
  `movie_id` bigint(20) DEFAULT NULL COMMENT '影片id',
  `project_cost` decimal(10,2) DEFAULT NULL COMMENT '项目成本',
  `project_header` varchar(255) DEFAULT NULL COMMENT '项目发起人',
  `financing_money` decimal(10,2) DEFAULT NULL COMMENT '筹资金额',
  `start_time` varchar(20) DEFAULT NULL COMMENT '投资开始时间',
  `end_time` varchar(20) DEFAULT NULL COMMENT '投资结束时间',
  `project_label` varchar(255) DEFAULT NULL COMMENT '项目标签 如 永久版权 永久福利等',
  `project_cover_charge` decimal(10,2) DEFAULT NULL COMMENT '项目服务费',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `project_type` tinyint(2) DEFAULT NULL COMMENT '项目类型 1 网络电影 2 院线电影',
  `project_status` tinyint(2) DEFAULT NULL COMMENT '项目状态 1 即将上线 2 正在募集 3 募集完成',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  `examine_status` tinyint(2) DEFAULT NULL COMMENT '审核状态 1、新建 2、已提交未审核 3 驳回 4 审核通过',
  `publish_status` tinyint(2) DEFAULT NULL COMMENT '是否发布 1 发布 2 未发布',
  `project_des` text COMMENT '项目介绍',
  `remark` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `index_flag` tinyint(4) DEFAULT '1' COMMENT '是否推送到首页，1、是；2、否',
  `project_video` varchar(500) DEFAULT NULL COMMENT '项目视频地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='项目表';

-- ----------------------------
-- Records of t_b_project
-- ----------------------------
INSERT INTO `t_b_project` VALUES ('15', '测试项目', null, '100.00', '强', '150.00', '2018-05-07', '2018-05-21', 'dfse', '200.00', '2018-05-06 01:29:33', '1', '2', '2018-05-06 17:09:03', '1', '4', '1', null, 'sadsadsadsadxcvxc赛发动范', '1', null);
INSERT INTO `t_b_project` VALUES ('16', 'qaz 去啊', null, '22.00', '去玩儿', '222.00', '2018-05-02', '2018-05-22', '1奥赛', '333.00', '2018-05-06 18:24:54', '1', '2', null, '1', '1', '2', '<p>赛风赛风东</p><p>跟赵很刚</p><p><b>赛发动范东</b></p><p><b>崔本仓泵跟</b></p>', null, '1', null);
INSERT INTO `t_b_project` VALUES ('17', '赛发动', null, '4444.00', '扥盖房', '444.00', '2018-05-08', '2018-05-21', '色热无若', '43543.00', '2018-05-06 18:27:31', '1', '3', null, '1', '1', '2', null, null, '1', null);
INSERT INTO `t_b_project` VALUES ('18', '1去啊', null, '231.00', '爱玩儿啊', '2344.00', '2018-05-08', '2018-05-15', '12阿瑟大', '234.00', '2018-05-06 18:29:00', '1', '1', null, '1', '1', '2', '扥该人该', null, '1', null);
INSERT INTO `t_b_project` VALUES ('19', '东非', null, '44.00', '饿死我', '324.00', '2018-05-17', '2018-05-23', '赛风赛', '34.00', '2018-05-06 18:30:33', '1', '1', null, '1', '1', '2', null, null, '1', 'http://images.lbtang.club/979426fade2d4b21a5ebb72a6167967a.mp4');

-- ----------------------------
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态 1、可用 2、已删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'layui-icon layui-icon-shezhi', '0', '1');
INSERT INTO `t_sys_menu` VALUES ('2', '1', '用户列表', '/user/userList', null, '1', 'fa fa-user', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('3', '1', '角色管理', '/role/roleList', null, '1', 'fa fa-user-secret', '2', '1');
INSERT INTO `t_sys_menu` VALUES ('4', '1', '菜单管理', '/menu/menuList', null, '1', 'fa fa-th-list', '3', '2');
INSERT INTO `t_sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select,sys:user:info', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list,sys:role:info', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select,sys:menu:info', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0', '2');
INSERT INTO `t_sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7', '2');
INSERT INTO `t_sys_menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6', '2');
INSERT INTO `t_sys_menu` VALUES ('31', '0', '项目管理', '', null, '0', 'layui-icon layui-icon-zzfilm', '3', '1');
INSERT INTO `t_sys_menu` VALUES ('40', '0', '会员管理', '', null, '0', 'layui-icon layui-icon-iconfontwodehaoyou', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('41', '40', '会员列表', '/member/memberList', null, '1', 'layui-icon layui-icon-iconfontwodehaoyou', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('42', '41', '查看', null, 'sys:member:list,sys:member:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('43', '41', '新增', null, 'sys:member:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('44', '41', '修改', null, 'sys:member:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('45', '41', '删除', null, 'sys:member:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('46', '0', '资讯管理', null, null, '0', 'layui-icon layui-icon-znewspaper-l', '2', '1');
INSERT INTO `t_sys_menu` VALUES ('47', '46', '新闻资讯', '/news/newsList', null, '1', 'layui-icon layui-icon-znewspaper-l', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('48', '47', '查看', null, 'sys:news:list,sys:news:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('49', '47', '新增', null, 'sys:news:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('50', '47', '修改', null, 'sys:news:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('51', '47', '删除', null, 'sys:news:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('52', '31', '项目管理', '/project/projectList', null, '1', 'layui-icon layui-icon-zzfilm', '6', '1');
INSERT INTO `t_sys_menu` VALUES ('53', '52', '查看', null, 'project:project:list,project:project:info', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('54', '52', '新增', null, 'project:project:save', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('55', '52', '修改', null, 'project:project:update', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('56', '52', '删除', null, 'project:project:delete', '2', null, '6', '1');
INSERT INTO `t_sys_menu` VALUES ('57', '52', '提审', null, 'project:project:approval', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('59', '52', '发布', null, 'project:project:publish', '2', null, '0', '1');
INSERT INTO `t_sys_menu` VALUES ('60', '31', '项目审核', '/project/approvalList', null, '1', null, '2', '1');
INSERT INTO `t_sys_menu` VALUES ('61', '60', '审核', null, 'project:project:examine,project:project:approvallist', '2', null, '0', '1');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '项目审核员', '审核项目专有角色', '1', '2018-04-03 00:26:20');
INSERT INTO `t_sys_role` VALUES ('2', '管理员', '管理员', '1', '2018-04-03 00:30:28');
INSERT INTO `t_sys_role` VALUES ('3', '测试角色', '从虚弱的额外人额外', '1', null);

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('5', '2', '1');
INSERT INTO `t_sys_role_menu` VALUES ('6', '2', '2');
INSERT INTO `t_sys_role_menu` VALUES ('7', '2', '15');
INSERT INTO `t_sys_role_menu` VALUES ('8', '2', '16');
INSERT INTO `t_sys_role_menu` VALUES ('9', '2', '17');
INSERT INTO `t_sys_role_menu` VALUES ('10', '2', '18');
INSERT INTO `t_sys_role_menu` VALUES ('11', '2', '3');
INSERT INTO `t_sys_role_menu` VALUES ('12', '2', '19');
INSERT INTO `t_sys_role_menu` VALUES ('13', '2', '20');
INSERT INTO `t_sys_role_menu` VALUES ('14', '2', '21');
INSERT INTO `t_sys_role_menu` VALUES ('15', '2', '22');
INSERT INTO `t_sys_role_menu` VALUES ('16', '2', '31');
INSERT INTO `t_sys_role_menu` VALUES ('17', '2', '52');
INSERT INTO `t_sys_role_menu` VALUES ('18', '2', '53');
INSERT INTO `t_sys_role_menu` VALUES ('19', '2', '54');
INSERT INTO `t_sys_role_menu` VALUES ('20', '2', '55');
INSERT INTO `t_sys_role_menu` VALUES ('21', '2', '56');
INSERT INTO `t_sys_role_menu` VALUES ('22', '2', '57');
INSERT INTO `t_sys_role_menu` VALUES ('23', '2', '58');
INSERT INTO `t_sys_role_menu` VALUES ('24', '2', '59');
INSERT INTO `t_sys_role_menu` VALUES ('25', '2', '40');
INSERT INTO `t_sys_role_menu` VALUES ('26', '2', '41');
INSERT INTO `t_sys_role_menu` VALUES ('27', '2', '42');
INSERT INTO `t_sys_role_menu` VALUES ('28', '2', '43');
INSERT INTO `t_sys_role_menu` VALUES ('29', '2', '44');
INSERT INTO `t_sys_role_menu` VALUES ('30', '2', '45');
INSERT INTO `t_sys_role_menu` VALUES ('31', '2', '46');
INSERT INTO `t_sys_role_menu` VALUES ('32', '2', '47');
INSERT INTO `t_sys_role_menu` VALUES ('33', '2', '48');
INSERT INTO `t_sys_role_menu` VALUES ('34', '2', '49');
INSERT INTO `t_sys_role_menu` VALUES ('35', '2', '50');
INSERT INTO `t_sys_role_menu` VALUES ('36', '2', '51');
INSERT INTO `t_sys_role_menu` VALUES ('46', '3', '31');
INSERT INTO `t_sys_role_menu` VALUES ('47', '3', '52');
INSERT INTO `t_sys_role_menu` VALUES ('48', '3', '53');
INSERT INTO `t_sys_role_menu` VALUES ('49', '3', '54');
INSERT INTO `t_sys_role_menu` VALUES ('50', '3', '55');
INSERT INTO `t_sys_role_menu` VALUES ('51', '3', '56');
INSERT INTO `t_sys_role_menu` VALUES ('52', '3', '57');
INSERT INTO `t_sys_role_menu` VALUES ('53', '3', '59');
INSERT INTO `t_sys_role_menu` VALUES ('54', '1', '1');
INSERT INTO `t_sys_role_menu` VALUES ('55', '1', '2');
INSERT INTO `t_sys_role_menu` VALUES ('56', '1', '15');
INSERT INTO `t_sys_role_menu` VALUES ('57', '1', '18');
INSERT INTO `t_sys_role_menu` VALUES ('58', '1', '31');
INSERT INTO `t_sys_role_menu` VALUES ('59', '1', '60');
INSERT INTO `t_sys_role_menu` VALUES ('60', '1', '61');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `t_sys_user` VALUES ('2', 'liuyuzhu', 'b5f936619f46ac44a63e82e14c8911e6d150e1e9b218a774cd2134b6a4a14980', 'MWjcJMWL5tUmQFOGaquY', 'liuyuzhu@126.com', '15888888888', '1', '1', '2018-04-03 00:26:43');
INSERT INTO `t_sys_user` VALUES ('3', 'movie', '31fc99389a9725abbba22cb0cc4ccd23027a33227bbca2ad33538c69215fa4fd', 'dwxeZIvgBATW9x52DFrW', 'movie@126.com', '18818544574', '1', '1', '2018-04-03 00:29:47');
INSERT INTO `t_sys_user` VALUES ('11', 'testper', '8d37c638f2ff3df5735699065b851dbab8bd88a84dfc1ec7b1c8bd09a1f0711c', 'hKs8YlEqGYqf39hvKxNh', '12321@126.com', '18818524559', '1', '1', '2018-05-01 17:09:16');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '2', '1');
INSERT INTO `t_sys_user_role` VALUES ('2', '3', '2');
INSERT INTO `t_sys_user_role` VALUES ('5', '10', '3');
INSERT INTO `t_sys_user_role` VALUES ('6', '11', '1');

-- ----------------------------
-- Table structure for t_sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_token`;
CREATE TABLE `t_sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of t_sys_user_token
-- ----------------------------
INSERT INTO `t_sys_user_token` VALUES ('1', '44296ec2012bfbdb369fb7bd92504700', '2018-04-09 21:57:34', '2018-04-09 20:57:34');
INSERT INTO `t_sys_user_token` VALUES ('2', '027dcd44cc1500d7d3b9fc0b225a3aee', '2018-04-06 16:36:31', '2018-04-06 15:36:31');
INSERT INTO `t_sys_user_token` VALUES ('3', 'b0ab0f033e4248cf20cc8302c75a611a', '2018-04-03 01:00:48', '2018-04-03 00:30:48');
