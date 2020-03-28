/*
 Navicat MySQL Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/03/2020 09:16:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_album
-- ----------------------------
DROP TABLE IF EXISTS `cms_album`;
CREATE TABLE `cms_album` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '画册名称，必填，64个字符',
  `cover_pic` varchar(255) NOT NULL COMMENT '画册小图片，必填',
  `pic_count` int DEFAULT '0' COMMENT '包含的图片数量',
  `sort` int DEFAULT '1' COMMENT '排序，数字越大越靠后',
  `description` varchar(150) DEFAULT '' COMMENT '画册描述，150字符',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='相册表';

-- ----------------------------
-- Table structure for cms_album_pic
-- ----------------------------
DROP TABLE IF EXISTS `cms_album_pic`;
CREATE TABLE `cms_album_pic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `album_id` bigint NOT NULL COMMENT '归属的画册的id',
  `pic` varchar(1000) NOT NULL COMMENT '图片url，必填',
  `name` varchar(64) DEFAULT '' COMMENT '图片备注，名称，64字符',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '0:隐藏，1：展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='画册图片表';

-- ----------------------------
-- Table structure for cms_author
-- ----------------------------
DROP TABLE IF EXISTS `cms_author`;
CREATE TABLE `cms_author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '作者名称,最多50个字符，必填',
  `icon` varchar(1000) NOT NULL COMMENT '作者头像缩略图，必填',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `summary` varchar(1000) DEFAULT '' COMMENT '简介',
  `phone` varchar(64) DEFAULT '' COMMENT '手机号码',
  `status` tinyint(1) DEFAULT '1' COMMENT '帐号启用状态:0->禁用；1->启用',
  `note` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文作者，可能有其他人投稿，所以列出作者';

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `owner_id` bigint DEFAULT NULL COMMENT '归属对象的id，比如博文的id标识对博文的评论，或者其他的内容，或者使用默认的留言板',
  `from_id` bigint NOT NULL COMMENT '发布人id',
  `from_name` varchar(255) NOT NULL COMMENT '发布人名称',
  `from_avatar` varchar(255) DEFAULT '' COMMENT '发布人头像',
  `link_num` int NOT NULL DEFAULT '0' COMMENT '此评论获得的点赞数',
  `content` varchar(1000) NOT NULL COMMENT '评论内容，限定1000字符',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：对外展示，0：不展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论内容';

-- ----------------------------
-- Table structure for cms_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment_reply`;
CREATE TABLE `cms_comment_reply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `comment_id` bigint DEFAULT NULL COMMENT '对某个评论的回复，记录评论的id',
  `from_id` bigint NOT NULL COMMENT '发布人id',
  `from_name` varchar(255) NOT NULL COMMENT '发布人名称',
  `from_avatar` varchar(255) DEFAULT '' COMMENT '发布人头像',
  `to_id` bigint NOT NULL COMMENT '被回复人id',
  `to_name` varchar(255) NOT NULL COMMENT '被回复人名称',
  `to_avatar` varchar(255) DEFAULT '' COMMENT '被回复人头像',
  `content` varchar(1000) NOT NULL COMMENT '评论内容，限定1000字符',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：对外展示，0：不展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论的回复内容';

-- ----------------------------
-- Table structure for cms_feed_back
-- ----------------------------
DROP TABLE IF EXISTS `cms_feed_back`;
CREATE TABLE `cms_feed_back` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '最后修改时间，管理员修改状态或者修改意见备注，都修改时间',
  `nick` varchar(255) NOT NULL COMMENT '发布人名称',
  `contact` varchar(255) NOT NULL COMMENT '发布人名称',
  `content` varchar(1000) NOT NULL COMMENT '评论内容，限定1000字符',
  `note` varchar(1000) NOT NULL COMMENT '管理员对此反馈的处理意见，1000字符',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：提出意见，2：已处理，3：处理中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='意见反馈';

-- ----------------------------
-- Table structure for cms_post
-- ----------------------------
DROP TABLE IF EXISTS `cms_post`;
CREATE TABLE `cms_post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_thumbnail` varchar(1000) NOT NULL COMMENT '缩略图，文章列表大图，必填',
  `title` varchar(100) NOT NULL COMMENT '标题，必填',
  `summary` varchar(1000) NOT NULL COMMENT '简介,摘要，必填',
  `author_id` bigint NOT NULL COMMENT '作者的id，必填',
  `author_name` varchar(1000) NOT NULL COMMENT '作者名称，必填',
  `author_icon` varchar(1000) NOT NULL COMMENT '作者头像缩略图，必填',
  `category_id` bigint DEFAULT NULL COMMENT '分类的id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '最后修改时间',
  `read_count` int DEFAULT '0' COMMENT '阅读数',
  `status` tinyint(1) DEFAULT '2' COMMENT '1：展示，0：隐藏删除，2：草稿',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文';

-- ----------------------------
-- Table structure for cms_post_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_post_category`;
CREATE TABLE `cms_post_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '分类名称，必填',
  `icon` varchar(500) DEFAULT '' COMMENT '分类图标',
  `post_count` int DEFAULT '0' COMMENT '博文数量',
  `status` tinyint(1) DEFAULT '1' COMMENT '0:隐藏，1：展示',
  `sort` int DEFAULT '1' COMMENT '数字越大，越靠后',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文的分类';

-- ----------------------------
-- Table structure for cms_post_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_post_content`;
CREATE TABLE `cms_post_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL COMMENT '正文部分,转化为html的部分，必填',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1:html,2:md',
  `post_id` bigint NOT NULL COMMENT '博文id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文内容，支持html和md';

-- ----------------------------
-- Table structure for cms_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `cms_post_tag`;
CREATE TABLE `cms_post_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '标签名称，必填',
  `post_count` int DEFAULT '0' COMMENT '博文数量',
  `status` tinyint(1) DEFAULT '1' COMMENT '0:隐藏，1：展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='统计博文的标签';

-- ----------------------------
-- Table structure for cms_user_search_log
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_search_log`;
CREATE TABLE `cms_user_search_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `ip` varchar(64) DEFAULT '',
  `user_agent` varchar(255) DEFAULT '' COMMENT '浏览器登录类型',
  `keyword` varchar(100) NOT NULL COMMENT '搜索的关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='记录用户的搜索关键字';

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '登录账号,最长64字符',
  `password` varchar(64) NOT NULL COMMENT '密码，12位，数字和字母，区分大小写',
  `unionid` varchar(64) DEFAULT '' COMMENT '微信unionid，非必填',
  `icon` varchar(500) NOT NULL COMMENT '头像，必填，系统给默认头像',
  `email` varchar(100) DEFAULT '' COMMENT '邮箱，非必填',
  `nick_name` varchar(200) NOT NULL COMMENT '昵称，必填，随机生成',
  `note` varchar(500) DEFAULT '' COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户表';

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NOT NULL COMMENT '后台管理员id',
  `create_time` datetime NOT NULL COMMENT '创建记录时间',
  `ip` varchar(64) DEFAULT '' COMMENT '用户ip地址',
  `address` varchar(100) DEFAULT '',
  `user_agent` varchar(100) DEFAULT '' COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户登录日志表';

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NOT NULL COMMENT '管理员id',
  `permission_id` bigint NOT NULL COMMENT '权限id',
  `type` tinyint(1) NOT NULL COMMENT '0：减去权限，1：增加这个权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint NOT NULL COMMENT '管理员id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NOT NULL COMMENT '父级权限id',
  `name` varchar(100) NOT NULL COMMENT '名称，100字符，必填',
  `value` varchar(200) NOT NULL COMMENT '权限值，200字符，必填',
  `icon` varchar(500) DEFAULT '' COMMENT '图标',
  `type` tinyint(1) NOT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) NOT NULL COMMENT '前端资源路径，200字符，必填',
  `status` tinyint(1) DEFAULT '1' COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `sort` int NOT NULL COMMENT '排序，必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户权限表';

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称，100字符，必填',
  `description` varchar(500) NOT NULL COMMENT '描述，500字符，必填',
  `admin_count` int DEFAULT '0' COMMENT '后台用户数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户角色表';

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `permission_id` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(50) DEFAULT '' COMMENT '用户名称',
  `avatar` varchar(200) DEFAULT '' COMMENT '头像',
  `phone` varchar(20) DEFAULT '' COMMENT 'phone',
  `email` varchar(50) DEFAULT '' COMMENT 'email',
  `status` tinyint(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Table structure for ums_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_auth`;
CREATE TABLE `ums_user_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '注册类型：1：微信，2：qq，3：手机，4：email，5：用户名，6：github，7：微博。',
  `appid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '身份的唯一标识，登录账号、微信号openid，微博号，qq号等',
  `credential` varchar(100) DEFAULT '' COMMENT '站内密码，第三方为token',
  `verified` tinyint DEFAULT '0' COMMENT '1：验证账号，0：未验证',
  `unionid` varchar(100) DEFAULT '' COMMENT '微信的unionid，如果微信有开发平台，就有unionid',
  `user_id` bigint NOT NULL COMMENT '对应的用户的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录账号，一个用户有多个登录方式，';

SET FOREIGN_KEY_CHECKS = 1;
