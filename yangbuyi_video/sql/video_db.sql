/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '编号',
  `pid` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '所属上级',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型(1:菜单,2:按钮)',
  `permission_value` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限值',
  `path` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_pid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限';

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO `auth_menu` VALUES ('1', '0', '全部数据', '0', null, null, null, null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195268474480156673', '1', '权限管理', '1', null, '/authority', 'Layout', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195268616021139457', '1195268474480156673', '用户管理', '1', null, 'user/list', 'authority/user/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195268788138598401', '1195268474480156673', '角色管理', '1', null, 'role/list', 'authority/role/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195268893830864898', '1195268474480156673', '菜单管理', '1', null, 'menu/list', 'authority/menu/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269143060602882', '1195268616021139457', '查看', '2', 'user.list', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269295926206466', '1195268616021139457', '添加', '2', 'user.add', 'user/add', 'authority/user/form', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269473479483394', '1195268616021139457', '修改', '2', 'user.update', 'user/update/:id', 'authority/user/form', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269547269873666', '1195268616021139457', '删除', '2', 'user.remove', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269821262782465', '1195268788138598401', '修改', '2', 'role.update', 'role/update/:id', 'authority/role/form', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195269903542444034', '1195268788138598401', '查看', '2', 'role.list', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270037005197313', '1195268788138598401', '添加', '2', 'role.add', 'role/form', 'authority/role/form', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270442602782721', '1195268788138598401', '删除', '2', 'role.remove', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270621548568578', '1195268788138598401', '角色权限', '2', 'role.acl', 'role/distribution/:id', 'authority/role/roleForm', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270744097742849', '1195268893830864898', '查看', '2', 'menu.list', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270810560684034', '1195268893830864898', '添加', '2', 'menu.add', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270862100291586', '1195268893830864898', '修改', '2', 'menu.update', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195270887933009922', '1195268893830864898', '删除', '2', 'menu.remove', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195349439240048642', '1', '创作者管理', '1', null, '/author', 'Layout', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195349699995734017', '1195349439240048642', '创作者列表', '1', null, 'table', 'service/author/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195349810561781761', '1195349439240048642', '添加创作者', '1', null, 'save', 'service/author/save', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195349876252971010', '1195349810561781761', '添加', '2', 'author.add', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195349979797753857', '1195349699995734017', '查看', '2', 'author.list', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350117270261762', '1195349699995734017', '修改', '2', 'author.update', 'authorEdit/:id', 'service/author/save', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350188359520258', '1195349699995734017', '删除', '2', 'author.remove', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350299365969922', '1', '作品分类', '1', null, '/category', 'Layout', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350397751758850', '1195350299365969922', '作品分类列表', '1', null, 'categoryList', 'service/category/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350500512206850', '1195350299365969922', '上传作品分类', '1', null, 'import', 'service/category/save', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350612172967938', '1195350397751758850', '查看', '2', 'category.list', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350687590748161', '1195350500512206850', '导入', '2', 'category.import', '', '', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350831744782337', '1', '作品管理', '1', null, '/content', 'Layout', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195350919074385921', '1195350831744782337', '作品列表', '1', null, 'list', 'service/content/list', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195351020463296513', '1195350831744782337', '添加作品', '1', null, 'info', 'service/content/info', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195351159672246274', '1195350919074385921', '发布作品', '2', 'content.publish', 'send/:id', 'service/content/send', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195351326706208770', '1195350919074385921', '编辑课程', '2', 'content.update', 'info/:id', 'service/content/info', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1195351566221938690', '1195350919074385921', '章节信息', '2', 'chapter.update', 'chapter/:id', 'service/content/chapter', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1196301740985311234', '1195268616021139457', '分配角色', '2', 'user.assgin', 'user/role/:id', 'authority/user/roleForm', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1453558404059430914', '1', '门户首页管理', '1', null, '/banner', 'Layout', null, null, '0');
INSERT INTO `auth_menu` VALUES ('1453558965571878913', '1453558404059430914', '焦点图管理', '1', null, 'list', 'service/banner/list', null, null, '0');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1348119214772592642', '超级管理员', null, null, '0');
INSERT INTO `auth_role` VALUES ('1351006891666735105', '普通管理员', null, null, '0');

-- ----------------------------
-- Table structure for auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_menu`;
CREATE TABLE `auth_role_menu` (
  `id` char(19) NOT NULL DEFAULT '',
  `role_id` char(19) NOT NULL DEFAULT '',
  `permission_id` char(19) NOT NULL DEFAULT '',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_permission_id` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单';

-- ----------------------------
-- Records of auth_role_menu
-- ----------------------------
INSERT INTO `auth_role_menu` VALUES ('1350991893892608002', '1348118110915665922', '1', '0');
INSERT INTO `auth_role_menu` VALUES ('1350991893892608003', '1348118110915665922', '1195268474480156673', '0');
INSERT INTO `auth_role_menu` VALUES ('1350991893892608004', '1348118110915665922', '1195268616021139457', '0');
INSERT INTO `auth_role_menu` VALUES ('1350991893892608005', '1348118110915665922', '1195269143060602882', '0');
INSERT INTO `auth_role_menu` VALUES ('1350991893892608006', '1348118110915665922', '1195269295926206466', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948291604481', '1347755932735954946', '1', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993089', '1347755932735954946', '1195268474480156673', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993090', '1347755932735954946', '1195268616021139457', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993091', '1347755932735954946', '1195269143060602882', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993092', '1347755932735954946', '1195269295926206466', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993093', '1347755932735954946', '1195269473479483394', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993094', '1347755932735954946', '1195269547269873666', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993095', '1347755932735954946', '1195268788138598401', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993096', '1347755932735954946', '1195269821262782465', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948299993097', '1347755932735954946', '1195269903542444034', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381698', '1347755932735954946', '1195270037005197313', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381699', '1347755932735954946', '1195270442602782721', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381700', '1347755932735954946', '1195270621548568578', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381701', '1347755932735954946', '1195268893830864898', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381702', '1347755932735954946', '1195270744097742849', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381703', '1347755932735954946', '1195270810560684034', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381704', '1347755932735954946', '1195270862100291586', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381705', '1347755932735954946', '1195270887933009922', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381706', '1347755932735954946', '1195349439240048642', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381707', '1347755932735954946', '1195349699995734017', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381708', '1347755932735954946', '1195349979797753857', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381709', '1347755932735954946', '1195350117270261762', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381710', '1347755932735954946', '1195350188359520258', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948308381711', '1347755932735954946', '1195349810561781761', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770305', '1347755932735954946', '1195349876252971010', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770306', '1347755932735954946', '1195350299365969922', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770307', '1347755932735954946', '1195350397751758850', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770308', '1347755932735954946', '1195350612172967938', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770309', '1347755932735954946', '1195350500512206850', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770310', '1347755932735954946', '1195350687590748161', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948316770311', '1347755932735954946', '1195350831744782337', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948866224129', '1347755932735954946', '1195350919074385921', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948866224130', '1347755932735954946', '1195351159672246274', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948866224131', '1347755932735954946', '1195351326706208770', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948866224132', '1347755932735954946', '1195351566221938690', '0');
INSERT INTO `auth_role_menu` VALUES ('1351007948866224133', '1347755932735954946', '1195351020463296513', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791233', '1350991014435139586', '1', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791234', '1350991014435139586', '1195268474480156673', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791235', '1350991014435139586', '1195268616021139457', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791236', '1350991014435139586', '1195269143060602882', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791237', '1350991014435139586', '1195269295926206466', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001613791238', '1350991014435139586', '1195269473479483394', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985537', '1350991014435139586', '1195269547269873666', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985538', '1350991014435139586', '1195268788138598401', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985539', '1350991014435139586', '1195269821262782465', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985540', '1350991014435139586', '1195269903542444034', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985541', '1350991014435139586', '1195270037005197313', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985542', '1350991014435139586', '1195270442602782721', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985543', '1350991014435139586', '1195270621548568578', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985544', '1350991014435139586', '1195268893830864898', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985545', '1350991014435139586', '1195270744097742849', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985546', '1350991014435139586', '1195270810560684034', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985547', '1350991014435139586', '1195270862100291586', '0');
INSERT INTO `auth_role_menu` VALUES ('1351008001617985548', '1350991014435139586', '1195270887933009922', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229605949442', '1351006891666735105', '1', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338049', '1351006891666735105', '1195268474480156673', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338050', '1351006891666735105', '1195268616021139457', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338051', '1351006891666735105', '1195269143060602882', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338052', '1351006891666735105', '1195268788138598401', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338053', '1351006891666735105', '1195269821262782465', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338054', '1351006891666735105', '1195269903542444034', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229614338055', '1351006891666735105', '1195270037005197313', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229626920962', '1351006891666735105', '1195270442602782721', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229626920963', '1351006891666735105', '1195270621548568578', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229626920964', '1351006891666735105', '1195268893830864898', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229631115265', '1351006891666735105', '1195270744097742849', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229631115266', '1351006891666735105', '1195270810560684034', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229631115267', '1351006891666735105', '1195270862100291586', '0');
INSERT INTO `auth_role_menu` VALUES ('1362309229631115268', '1351006891666735105', '1195270887933009922', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341470035970', '1348119214772592642', '1', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341478424578', '1348119214772592642', '1195268474480156673', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341478424579', '1348119214772592642', '1195268616021139457', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813186', '1348119214772592642', '1195269143060602882', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813187', '1348119214772592642', '1195269295926206466', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813188', '1348119214772592642', '1195269473479483394', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813189', '1348119214772592642', '1195269547269873666', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813190', '1348119214772592642', '1196301740985311234', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813191', '1348119214772592642', '1195268788138598401', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813192', '1348119214772592642', '1195269821262782465', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813193', '1348119214772592642', '1195269903542444034', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813194', '1348119214772592642', '1195270037005197313', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813195', '1348119214772592642', '1195270442602782721', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341486813196', '1348119214772592642', '1195270621548568578', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341495201794', '1348119214772592642', '1195268893830864898', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341495201795', '1348119214772592642', '1195270744097742849', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341495201796', '1348119214772592642', '1195270810560684034', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341495201797', '1348119214772592642', '1195270862100291586', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341495201798', '1348119214772592642', '1195270887933009922', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396097', '1348119214772592642', '1195349439240048642', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396098', '1348119214772592642', '1195349699995734017', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396099', '1348119214772592642', '1195349979797753857', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396100', '1348119214772592642', '1195350117270261762', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396101', '1348119214772592642', '1195350188359520258', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396102', '1348119214772592642', '1195349810561781761', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396103', '1348119214772592642', '1195349876252971010', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396104', '1348119214772592642', '1195350299365969922', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396105', '1348119214772592642', '1195350397751758850', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396106', '1348119214772592642', '1195350612172967938', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396107', '1348119214772592642', '1195350500512206850', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564341499396108', '1348119214772592642', '1195350687590748161', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344674484225', '1348119214772592642', '1195350831744782337', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344674484226', '1348119214772592642', '1195350919074385921', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344674484227', '1348119214772592642', '1195351159672246274', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344674484228', '1348119214772592642', '1195351326706208770', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344682872833', '1348119214772592642', '1195351566221938690', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344682872834', '1348119214772592642', '1195351020463296513', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344682872835', '1348119214772592642', '1453558404059430914', '0');
INSERT INTO `auth_role_menu` VALUES ('1453564344682872836', '1348119214772592642', '1453558965571878913', '0');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '会员id',
  `username` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '微信openid',
  `password` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `salt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户签名',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1347755932735954946', 'admin', '96e79218965eb72c92a549dd5a330112', 'fm1', null, null, '0');
INSERT INTO `auth_user` VALUES ('1351012085309816834', 'test001', '96e79218965eb72c92a549dd5a330112', '1', null, null, '0');
INSERT INTO `auth_user` VALUES ('1450356052305309698', 'yby', 'e10adc3949ba59abbe56e057f20f883e', '杨不易', null, null, '0');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '主键id',
  `role_id` char(19) NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` char(19) NOT NULL DEFAULT '0' COMMENT '用户id',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('1349592060048891906', '1348118110915665922', '1348099827084746753', '0');
INSERT INTO `auth_user_role` VALUES ('1351011400027643905', '1348119214772592642', '1350991014435139586', '0');
INSERT INTO `auth_user_role` VALUES ('1351011567346819073', '1351006891666735105', '1351011544819212289', '0');
INSERT INTO `auth_user_role` VALUES ('1351015100523659265', '1351006891666735105', '1351012085309816834', '0');
INSERT INTO `auth_user_role` VALUES ('1352912148655144961', '1348119214772592642', '1347755932735954946', '0');
INSERT INTO `auth_user_role` VALUES ('1450356074317017090', '1348119214772592642', '1450356052305309698', '0');
INSERT INTO `auth_user_role` VALUES ('1450356074325405698', '1351006891666735105', '1450356052305309698', '0');

-- ----------------------------
-- Table structure for cms_banner
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '链接地址',
  `image_bg` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '背景颜色',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`title`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='首页banner表';

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
INSERT INTO `cms_banner` VALUES ('1', '焦点图1', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/banner01.png', 'http://www.fmjava.com', '#3366ff', '1', '0');
INSERT INTO `cms_banner` VALUES ('1453593841650061313', '新增焦点图', 'https://oss-yby.yangbuyi.top/2021/10/28/f34c96d4eac241c7882968a71d9878663.png', 'https://yangbuyi.top', '#4119d6', '3', '0');
INSERT INTO `cms_banner` VALUES ('1453596314259369986', '杨不易呀', 'https://oss-yby.yangbuyi.top/2021/10/28/b0243e35dd2242589a14161c225193452.png', 'https://baidu.com', '#866f62', '4', '0');
INSERT INTO `cms_banner` VALUES ('1453596642593681409', '还要一张发', 'https://oss-yby.yangbuyi.top/2021/10/28/77b3045357dc4099a82e4d5d4623111d0.png', 'https://yangbuyi.top', '#0084ff', '1', '0');
INSERT INTO `cms_banner` VALUES ('1453600455480197122', '测试redis', 'https://oss-yby.yangbuyi.top/2021/10/28/e35aa85c1cae456abf54596ac373d969bg1.jpg', 'https://yangbuyi.top', '#5c6b83', '1', '0');
INSERT INTO `cms_banner` VALUES ('1453609729451245569', '倒萨倒萨倒萨倒萨倒萨啊', 'https://oss-yby.yangbuyi.top/2021/10/28/f34c96d4eac241c7882968a71d9878663.png', 'https://yangbuyi.top', '', '1', '1');
INSERT INTO `cms_banner` VALUES ('2', '焦点图2', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/banner02.png', 'http://www.fmjava.com', '#ff761c', '2', '0');

-- ----------------------------
-- Table structure for lmonkey_user
-- ----------------------------
DROP TABLE IF EXISTS `lmonkey_user`;
CREATE TABLE `lmonkey_user` (
  `USER_ID` varchar(32) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of lmonkey_user
-- ----------------------------

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '支付金额（分）',
  `transaction_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COLLATE utf8mb4_unicode_ci COMMENT '其他属性',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='支付日志表';

-- ----------------------------
-- Records of pay_log
-- ----------------------------
INSERT INTO `pay_log` VALUES ('1346305943719571457', '20210105120131081', '2021-01-05 12:02:05', '88.00', '2021010522001475370501422573', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010522001475370501422573\",\"out_trade_no\":\"20210105120131081\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');
INSERT INTO `pay_log` VALUES ('1346316343848603650', '20210105124255498', '2021-01-05 12:43:24', '88.00', '2021010522001475370501423029', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010522001475370501423029\",\"out_trade_no\":\"20210105124255498\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');
INSERT INTO `pay_log` VALUES ('1346427409626820609', '20210105200406516', '2021-01-05 20:04:45', '88.00', '2021010522001475370501422887', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010522001475370501422887\",\"out_trade_no\":\"20210105200406516\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');
INSERT INTO `pay_log` VALUES ('1346734347962982402', '20210106162352293', '2021-01-06 16:24:24', '11.00', '2021010622001475370501423378', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010622001475370501423378\",\"out_trade_no\":\"20210106162352293\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');
INSERT INTO `pay_log` VALUES ('1346785507461734401', '20210106194725595', '2021-01-06 19:47:42', '88.00', '2021010622001475370501423670', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010622001475370501423670\",\"out_trade_no\":\"20210106194725595\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');
INSERT INTO `pay_log` VALUES ('1346961382945738754', '2247712496283323', '2021-01-07 07:26:34', '12.00', '2021010722001475370501423971', 'TRADE_SUCCESS', '2', '{\"transaction_id\":\"2021010722001475370501423971\",\"out_trade_no\":\"2247712496283323\",\"trade_state\":\"TRADE_SUCCESS\"}', '0');

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `content_id` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '作品id',
  `content_title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作品名称',
  `content_cover` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作品封面',
  `author_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者名称',
  `user_id` varchar(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户id',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `mobile` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '订单金额（分）',
  `pay_type` tinyint(3) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_order_no` (`order_no`) USING BTREE,
  KEY `idx_content_id` (`content_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='订单';

-- ----------------------------
-- Records of pay_order
-- ----------------------------
INSERT INTO `pay_order` VALUES ('1346009525305896962', '20210104162412309', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346300127574646786', '20210105113857760', '1321336659541139458', '作品标题-生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/28/6185bfb7f021446fa3d174740070fec6基础班3 .png', 'test2', '1341407389878714369', '高新强', '', '11.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346300127574646787', '20210105113857840', '1321336659541139458', '作品标题-生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/28/6185bfb7f021446fa3d174740070fec6基础班3 .png', 'test2', '1341407389878714369', '高新强', '', '11.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346303226284519425', '20210105115116762', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346303226284519426', '20210105115116181', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346304072967696385', '20210105115438085', '1323915951118626817', '科技-数码--标题1', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/04/8fb400523e0741bfb80739b4fe3f872640-自增与自减加强训练.png', 'test221', '1341407389878714369', '高新强', '', '12.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346304977150586882', '20210105115814343', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346305806712631297', '20210105120131243', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346305806712631298', '20210105120131081', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346316224831033345', '20210105124255498', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346421981316640769', '20210105194310693', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341407389878714369', '高新强', '', '88.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346427248112562177', '20210105200406516', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1346427056160149506', '桜', '', '88.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346734215355867137', '20210106162352293', '1321336659541139458', '作品标题-生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/28/6185bfb7f021446fa3d174740070fec6基础班3 .png', 'test2', '1341407389878714369', '高新强', '', '11.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346785439698558977', '20210106194725595', '1320633170208923650', '作品标题生活-电影', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', 'gxq', '1341378962605867009', '疯码Java-高新强', '', '88.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1346961300317949954', '2247712496283323', '1321723907591823362', 'it编程', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/29/a0cb3fe0dc914fb0a1e7c169ad7ce0ecSpringBoot框架.png', 'test221', '1341407389878714369', '高新强', '', '12.00', '2', '1', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1451611986727440385', '2299682472600728', '1449386177602232321', '工作流activity', 'https://oss-yby.yangbuyi.top/2021/10/16/a97175db84be4aef94e5de22b537b532evection.bpmn.png', 'test221', '1448232787013222401', 'TrouBles', '', '120.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1451613567493509121', '9069861819780213', '1336529953053081602', 'asdfasdfasdfa', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/12/09/72020b33396e48b3bbf64ef9bafad486v7.png', 'aaa', '1448232787013222401', 'TrouBles', '', '10.00', '2', '0', '0', '2021-10-23 10:22:26', '2021-10-23 10:22:26');
INSERT INTO `pay_order` VALUES ('1453641806385221633', '8252705731961913', '1320633170208923650', '从零玩转Java基础', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', '杨不易呀', '1453564754294423553', 'A', '', '88.00', '2', '0', '0', '2021-10-28 16:36:29', '2021-10-28 16:36:29');
INSERT INTO `pay_order` VALUES ('1453642228114096129', '3861086825764216', '1448484238369181697', '从零玩转Java基础', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '杨不易呀', '1453564754294423553', 'A', '', '12.00', '2', '0', '0', '2021-10-28 16:38:09', '2021-10-28 16:38:09');
INSERT INTO `pay_order` VALUES ('1453650326438518786', '0567652985350856', '1448484238369181697', '从零玩转Java基础', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '杨不易呀', '1448232787013222401', 'TrouBles', '', '12.00', '2', '0', '0', '2021-10-28 17:10:20', '2021-10-28 17:10:20');
INSERT INTO `pay_order` VALUES ('1453660031323885569', '6835705195978412', '1448484238369181697', '从零玩转Java基础', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '杨不易呀', '1448232787013222401', 'TrouBles', '', '12.00', '2', '0', '0', '2021-10-28 17:48:54', '2021-10-28 17:48:54');
INSERT INTO `pay_order` VALUES ('1453660783161327617', '4770187061274165', '1448484238369181697', '从零玩转Java基础', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '杨不易呀', '1448232787013222401', 'TrouBles', '', '12.00', '2', '0', '0', '2021-10-28 17:51:53', '2021-10-28 17:51:53');
INSERT INTO `pay_order` VALUES ('1453666807918321665', '7946949239919770', '1448484238369181697', '从零玩转Java基础', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '杨不易呀', '1448232787013222401', 'TrouBles', '', '12.00', '2', '1', '0', '2021-10-28 18:15:49', '2021-10-28 18:16:15');

-- ----------------------------
-- Table structure for portal_user
-- ----------------------------
DROP TABLE IF EXISTS `portal_user`;
CREATE TABLE `portal_user` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
  `openid` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of portal_user
-- ----------------------------
INSERT INTO `portal_user` VALUES ('1338744757631819777', null, '18588666017', '81dc9bdb52d04dc20036dbd8313ed055', 'fmjava', null, null, 'http://edu-image.nosdn.127.net/4DC4257CE559B29ED80DDA6BE0E4E0DC.jpg?imageView&thumbnail=120y120&quality=100', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1341378962605867009', 'o91wv6bsCDjmVCkkLS3lpyjyvrNE', '', null, '疯码Java-高新强', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/XIgWJcou2SqI63H9pTVSmLU5XffloorUfAmXgGgJhd97jbaOnlRRiamv3iaCRE6PKvJkIgMSw1LK8nCaxNusgzibQ/132', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1341407389878714369', 'o91wv6aKxddS-5fQe2XJUlanLRXM', '', null, '高新强', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/PVAXjIjJIaj26HwBhvG2H3jyjB3uyjaGMRRuSWHIOo7HFcgaoEHQckMxV4niakWVVbFLvRoGX4KopSaTkRj7LEA/132', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1346427056160149506', 'o91wv6bV5kYzhrSpStP5zmsl8tic', '', null, '桜', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/nhHwdZicJ3XQIibiaQoKUiabMCj9yQBNnqvibv5ib0sXkk22FQxzf1K1RBDhMKjcqYQeUpWvIlrGtc3MLUQXFSFZfzzA/132', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1448156888800653314', null, '17773596535', 'e10adc3949ba59abbe56e057f20f883e', '杨不易呀', null, null, 'https://tse4-mm.cn.bing.net/th/id/OIP-C.F-pKmXxYK0lHIU6W41RSXgHaHY?pid=ImgDet&rs=1', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1448232787013222401', 'o91wv6WA0lBcnPZvYb4Q3SDvKxXA', '', null, 'TrouBles', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erkNTfQPROPBsVVnERwbto4QBnbUMbxJdk0zyLj3pEKia9FOdnVGgXOnPhVLxcTL39jvQYHQeNjw9Q/132', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1448234376893513729', 'o91wv6e_czjZn3bfXRkZHA6W2BEU', '', null, '杨不易', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/6bFGbibQpwlJdHkepeKPzUuTiczia8CXCajkGMn1BgMF8TeA0PcregwOT0kgYXVFEyF7pKcdgiaSr5tqwcyPzwAT9Q/132', null, '0', '0');
INSERT INTO `portal_user` VALUES ('1453564754294423553', 'o91wv6Qy9hRxP2jU-QGMAQMNYvj0', '', null, 'A', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/GWKzIMpuGQjUsuQLt1c96aicCGVJvEBvtNIBuMwJbNzYlV8B83OFdiciblC2P6mJnJnuJhicPNtX4BxFnMmcDPbl2A/132', null, '0', '0');

-- ----------------------------
-- Table structure for video_author
-- ----------------------------
DROP TABLE IF EXISTS `video_author`;
CREATE TABLE `video_author` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创作者ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创作者姓名',
  `intro` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创作者简介',
  `career` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者经历介绍',
  `level` int(10) unsigned NOT NULL COMMENT '1:普通创作者 2:特约创作者',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创作者头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='创作者';

-- ----------------------------
-- Records of video_author
-- ----------------------------
INSERT INTO `video_author` VALUES ('1', 'test221', 'aaaa', 'aaa', '0', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/24/020b4af2bfd5401e8b9813318b024ad7file.png', '0', '0', '2020-09-20 15:39:40', '2021-01-14 14:33:40');
INSERT INTO `video_author` VALUES ('1309048340722147329', 'gxq1111', 'intro111', 'career', '0', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/24/020b4af2bfd5401e8b9813318b024ad7file.png', '0', '0', '2020-09-24 16:33:40', '2020-09-28 23:39:29');
INSERT INTO `video_author` VALUES ('1310529597436022785', 'aaa', 'aaaaaa', 'aaaa', '0', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/24/020b4af2bfd5401e8b9813318b024ad7file.png', '0', '0', '2020-09-28 18:39:39', '2020-09-28 18:39:39');
INSERT INTO `video_author` VALUES ('1315098899908636673', 'aaaa', 'asdfasdfasdfa', 'aaaa', '0', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/11/2cef78a214d04bc88bb77bf63141c999file.png', '1', '0', '2020-10-11 09:16:26', '2020-10-11 09:16:26');
INSERT INTO `video_author` VALUES ('1448480395057123329', '杨不易呀', '杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨', '杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀杨不易呀', '0', 'https://oss-yby.yangbuyi.top/2021/10/14/dc36757555f4406a8c4ba2dd7ca14f27file.png', '0', '0', '2021-10-14 10:46:52', '2021-10-22 15:04:20');
INSERT INTO `video_author` VALUES ('2', 'test2', '', null, '0', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/24/020b4af2bfd5401e8b9813318b024ad7file.png', '0', '0', '2020-09-20 15:39:57', '2020-09-20 15:40:00');
INSERT INTO `video_author` VALUES ('3', 'gxq', '', null, '1', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/24/020b4af2bfd5401e8b9813318b024ad7file.png', '0', '0', '2020-09-22 12:42:35', '2020-09-22 12:42:39');

-- ----------------------------
-- Table structure for video_category
-- ----------------------------
DROP TABLE IF EXISTS `video_category`;
CREATE TABLE `video_category` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类别ID',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='科目分类';

-- ----------------------------
-- Records of video_category
-- ----------------------------
INSERT INTO `video_category` VALUES ('1316398091156787201', '科技', '0', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091253256193', '数码', '1316398091156787201', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091370696705', '汽车', '1316398091156787201', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091437805570', '生活', '0', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091513303041', '电影', '1316398091437805570', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091622354945', '美食', '1316398091437805570', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091676880898', '职场技能', '0', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1316398091760766977', 'IT编程', '1316398091676880898', '0', '2020-10-14 23:18:57', '2020-10-14 23:18:57');
INSERT INTO `video_category` VALUES ('1448520269072125954', '职业技能', '0', '0', '2021-10-14 13:25:19', '2021-10-14 13:25:19');
INSERT INTO `video_category` VALUES ('1448520269139234818', 'NUXT.JS', '1448520269072125954', '0', '2021-10-14 13:25:19', '2021-10-14 13:25:19');
INSERT INTO `video_category` VALUES ('1448520269156012034', 'Java', '1448520269072125954', '0', '2021-10-14 13:25:19', '2021-10-14 13:25:19');
INSERT INTO `video_category` VALUES ('1448520269185372162', '分布式项目', '1448520269072125954', '0', '2021-10-14 13:25:19', '2021-10-14 13:25:19');
INSERT INTO `video_category` VALUES ('1448520269210537986', '微服务项目', '1448520269072125954', '0', '2021-10-14 13:25:19', '2021-10-14 13:25:19');
INSERT INTO `video_category` VALUES ('1448520644097429505', '分布式SessionId', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644122595330', 'JWT', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644139372546', 'SpringSecurity', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644160344066', 'SpringBoot', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644177121282', 'Mysql', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644202287105', 'Orale', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644219064321', 'linux', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');
INSERT INTO `video_category` VALUES ('1448520644235841537', 'Docker', '1448520269072125954', '0', '2021-10-14 13:26:48', '2021-10-14 13:26:48');

-- ----------------------------
-- Table structure for video_chapter
-- ----------------------------
DROP TABLE IF EXISTS `video_chapter`;
CREATE TABLE `video_chapter` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节ID',
  `content_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品ID',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_course_id` (`content_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='作品章节';

-- ----------------------------
-- Records of video_chapter
-- ----------------------------
INSERT INTO `video_chapter` VALUES ('1323181700345638913', '1320633170208923650', '第一章节', '1', '2020-11-02 16:34:36', '2020-11-02 17:07:13');
INSERT INTO `video_chapter` VALUES ('1323181728690745346', '1320633170208923650', '第二章节', '0', '2020-11-02 16:34:42', '2020-11-02 16:34:42');
INSERT INTO `video_chapter` VALUES ('1323916012850393089', '1323915951118626817', '第一章', '0', '2020-11-04 17:12:29', '2020-11-04 17:12:29');
INSERT INTO `video_chapter` VALUES ('1323916095612399617', '1323915951118626817', '第二章', '0', '2020-11-04 17:12:49', '2020-11-04 17:12:49');
INSERT INTO `video_chapter` VALUES ('1326765394088050689', '1323915951118626817', '新章节', '0', '2020-11-12 13:54:55', '2020-11-12 13:54:55');
INSERT INTO `video_chapter` VALUES ('1326768173204520962', '1323915951118626817', 'asdfasdf', '0', '2020-11-12 14:05:57', '2020-11-12 14:05:57');
INSERT INTO `video_chapter` VALUES ('1327068614098313218', '1323915951118626817', 'bbbb', '0', '2020-11-13 09:59:48', '2020-11-13 09:59:48');
INSERT INTO `video_chapter` VALUES ('1327070863755182082', '1320586096603983874', 'aa', '0', '2020-11-13 10:08:44', '2020-11-13 10:08:44');
INSERT INTO `video_chapter` VALUES ('1336526165131001858', '1336526145921089538', 'adfasdfa', '0', '2020-12-09 12:20:44', '2020-12-09 12:20:44');
INSERT INTO `video_chapter` VALUES ('1336529487338536962', '1336529462655057921', 'aasdfasd', '0', '2020-12-09 12:33:56', '2020-12-09 12:33:56');
INSERT INTO `video_chapter` VALUES ('1448484310527987714', '1448484238369181697', '第一章节', '1', '2021-10-14 11:02:26', '2021-10-14 11:02:26');
INSERT INTO `video_chapter` VALUES ('1449387809282957314', '1449386177602232321', '第一节', '1', '2021-10-16 22:52:37', '2021-10-16 22:52:37');
INSERT INTO `video_chapter` VALUES ('1449405176490729473', '1449386177602232321', '第二节', '2', '2021-10-17 00:01:37', '2021-10-17 00:01:37');
INSERT INTO `video_chapter` VALUES ('1451423204474667009', '1451423070118526977', '前端作品测试', '1', '2021-10-22 13:40:33', '2021-10-22 13:40:33');

-- ----------------------------
-- Table structure for video_content
-- ----------------------------
DROP TABLE IF EXISTS `video_content`;
CREATE TABLE `video_content` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品ID',
  `author_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者ID',
  `category_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类ID',
  `category_parent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类父级ID',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '价格，设置为0则可免费观看',
  `content_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '作品个数',
  `cover` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '作品',
  `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_title` (`title`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_author_id` (`author_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='作品表';

-- ----------------------------
-- Records of video_content
-- ----------------------------
INSERT INTO `video_content` VALUES ('1320633170208923650', '1448480395057123329', '1316398091760766977', '1316398091676880898', '从零玩转Java基础', '88.00', '120', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/26/d24ef4fd04e24cfdb93134ed3911dd80基础班3 .png', '0', '737', '1', 'Normal', '0', '2020-10-26 15:47:39', '2021-10-28 16:36:28');
INSERT INTO `video_content` VALUES ('1321336659541139458', '2', '1316398091513303041', '1316398091437805570', '作品标题-生活-电影', '11.00', '10', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/28/6185bfb7f021446fa3d174740070fec6基础班3 .png', '0', '54', '1', 'Normal', '0', '2020-10-28 14:23:04', '2021-10-22 14:08:11');
INSERT INTO `video_content` VALUES ('1321723907591823362', '1', '1316398091760766977', '1316398091676880898', 'it编程', '12.00', '11', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/10/29/a0cb3fe0dc914fb0a1e7c169ad7ce0ecSpringBoot框架.png', '0', '25', '1', 'Normal', '0', '2020-10-29 16:01:51', '2021-10-22 13:50:49');
INSERT INTO `video_content` VALUES ('1323915951118626817', '1', '1316398091253256193', '1316398091156787201', '科技-数码--标题1', '12.00', '12', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/11/04/8fb400523e0741bfb80739b4fe3f872640-自增与自减加强训练.png', '0', '17', '1', 'Normal', '0', '2020-11-04 17:12:15', '2021-10-14 10:42:24');
INSERT INTO `video_content` VALUES ('1336526145921089538', '1309048340722147329', '1316398091253256193', '1316398091156787201', '工业机器人课程', '0.00', '111', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/12/09/b964cedc1e3848088b3508ce62957259v2.png', '0', '7', '1', 'Normal', '0', '2020-12-09 12:20:39', '2021-10-28 14:12:43');
INSERT INTO `video_content` VALUES ('1336526557554278402', '1310529597436022785', '1316398091253256193', '1316398091156787201', 'adffasdfasdfa', '0.00', '11', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/12/09/ed325ddf48d84c819c4e99be43715dcev3.png', '0', '5', '1', 'Normal', '0', '2020-12-09 12:22:17', '2021-10-14 10:42:18');
INSERT INTO `video_content` VALUES ('1336529462655057921', '1309048340722147329', '1316398091760766977', '1316398091676880898', 'asdfasdfasdfa', '12.00', '1', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/12/09/f999206102624458b483d61b892bf2c9v6.png', '0', '12', '1', 'Normal', '0', '2020-12-09 12:33:50', '2021-10-14 10:42:15');
INSERT INTO `video_content` VALUES ('1336529953053081602', '1310529597436022785', '1316398091622354945', '1316398091437805570', 'asdfasdfasdfa', '10.00', '12', 'https://fmjava01.oss-cn-hangzhou.aliyuncs.com/2020/12/09/72020b33396e48b3bbf64ef9bafad486v7.png', '0', '46', '1', 'Normal', '0', '2020-12-09 12:35:47', '2021-10-14 10:41:35');
INSERT INTO `video_content` VALUES ('1448484238369181697', '1448480395057123329', '1316398091760766977', '1316398091676880898', '从零玩转Java基础', '12.00', '12', 'https://oss-yby.yangbuyi.top/2021/10/14/823abd9c618b4b5c938e06b1284c37bblogo.jpg', '0', '65', '1', 'Normal', '0', '2021-10-14 11:02:09', '2021-10-28 18:42:08');
INSERT INTO `video_content` VALUES ('1449386177602232321', '1', '1448520644160344066', '1448520269072125954', '工作流activity', '120.00', '1', 'https://oss-yby.yangbuyi.top/2021/10/16/a97175db84be4aef94e5de22b537b532evection.bpmn.png', '0', '85', '1', 'Normal', '0', '2021-10-16 22:46:08', '2021-10-28 14:59:17');
INSERT INTO `video_content` VALUES ('1451423070118526977', '1448480395057123329', '1448520269156012034', '1448520269072125954', '测试前端视频播放', '0.00', '1', 'https://oss-yby.yangbuyi.top/2021/10/22/a685592f2bfc4018ac3b8d7ef819c3d4screenshot.png', '0', '16', '1', 'Normal', '0', '2021-10-22 13:40:01', '2021-10-28 18:42:44');

-- ----------------------------
-- Table structure for video_content_description
-- ----------------------------
DROP TABLE IF EXISTS `video_content_description`;
CREATE TABLE `video_content_description` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品ID',
  `description` longtext COLLATE utf8mb4_unicode_ci COMMENT '作品简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='作品简介';

-- ----------------------------
-- Records of video_content_description
-- ----------------------------
INSERT INTO `video_content_description` VALUES ('1319187205794955265', 'asdfas', '2020-10-22 16:01:54', '2020-10-22 16:01:54');
INSERT INTO `video_content_description` VALUES ('1319537966865371137', 'abcd3', '2020-10-23 15:15:42', '2020-10-23 15:15:42');
INSERT INTO `video_content_description` VALUES ('1319547396642205698', 'asdfas', '2020-10-23 15:53:10', '2020-10-23 15:53:10');
INSERT INTO `video_content_description` VALUES ('1319549397165821953', '122', '2020-10-23 16:01:07', '2020-10-23 16:01:07');
INSERT INTO `video_content_description` VALUES ('1320586096603983874', 'aa', '2020-10-26 12:40:35', '2020-11-24 11:14:29');
INSERT INTO `video_content_description` VALUES ('1320586961507217409', 'aa', '2020-10-26 12:44:02', '2020-11-19 14:49:00');
INSERT INTO `video_content_description` VALUES ('1320598904523616257', '', '2020-10-26 13:31:29', '2020-10-26 13:31:29');
INSERT INTO `video_content_description` VALUES ('1320598942603702274', '', '2020-10-26 13:31:38', '2020-10-26 13:31:38');
INSERT INTO `video_content_description` VALUES ('1320622654392528897', '', '2020-10-26 15:05:51', '2020-10-26 15:05:51');
INSERT INTO `video_content_description` VALUES ('1320630382745788417', '简介111111', '2020-10-26 15:36:34', '2020-10-26 15:36:34');
INSERT INTO `video_content_description` VALUES ('1320633170208923650', '<h1>简介123</h1>\n<p>adfasdfsdfasdf</p>', '2020-10-26 15:47:39', '2021-10-14 11:00:31');
INSERT INTO `video_content_description` VALUES ('1321336659541139458', '111', '2020-10-28 14:23:04', '2020-11-18 15:35:10');
INSERT INTO `video_content_description` VALUES ('1321723907591823362', '1', '2020-10-29 16:01:51', '2020-11-18 15:36:46');
INSERT INTO `video_content_description` VALUES ('1323915951118626817', '<h1>asdfasdfasdfaf</h1>', '2020-11-04 17:12:15', '2021-01-22 15:12:50');
INSERT INTO `video_content_description` VALUES ('1336526145921089538', 'asdfa', '2020-12-09 12:20:39', '2021-01-04 15:42:54');
INSERT INTO `video_content_description` VALUES ('1336526557554278402', 'asdfasdf', '2020-12-09 12:22:17', '2020-12-09 12:22:17');
INSERT INTO `video_content_description` VALUES ('1336529462655057921', 'asdfasdf', '2020-12-09 12:33:50', '2021-01-20 11:22:53');
INSERT INTO `video_content_description` VALUES ('1336529953053081602', 'asdfasdfasdfasd', '2020-12-09 12:35:47', '2021-01-20 11:21:07');
INSERT INTO `video_content_description` VALUES ('1448484238369181697', '<p><img src=\"data:image/jpeg;base64,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\" alt=\"顶顶顶\" width=\"543\" height=\"541\" /></p>', '2021-10-14 11:02:09', '2021-10-28 18:40:54');
INSERT INTO `video_content_description` VALUES ('1449386177602232321', '<pre style=\"font-size: 11.3pt; font-family: \'Fira Code\', monospace;\"><span style=\"color: #6a8759;\">getIndexData</span>getIndexDatagetIndexDatagetIn<br />dexDatagetIndexDatagetIndexDatagetIndexDatagetI<br />ndexDatagetIndexDatagetIndexData<span style=\"font-size: 11.3pt;\">getIndexDatagetInd<br />exDatagetIndexDatagetIndexDatagetIndexDatagetIndexDataget<br />IndexDatagetIndexData</span>getIndexData<span style=\"font-size: 11.3pt;\">getIndexDatagetIndexDatagetIndexData<br />getIndexDatagetIndexDatagetIndexDatagetIndexDatagetIndexData</span>getIndexData<span style=\"font-size: 11.3pt;\">getI<br />ndexDatagetIndexDatagetIndexDatagetIndexDatagetIndexDatagetIndexDatagetIndexDatagetIn<br />dexData</span>getIndexData<span style=\"font-size: 11.3pt;\">getIndexDatagetIndexDatagetIndexDatagetIndexDatagetIndexDatagetIndex<br />DatagetIndexDatagetIndexData</span></pre>', '2021-10-16 22:46:08', '2021-10-20 01:33:51');
INSERT INTO `video_content_description` VALUES ('1451423070118526977', '<p>111</p>', '2021-10-22 13:40:01', '2021-10-22 13:53:13');

-- ----------------------------
-- Table structure for video_content_video
-- ----------------------------
DROP TABLE IF EXISTS `video_content_video`;
CREATE TABLE `video_content_video` (
  `id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频ID',
  `content_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作品ID',
  `chapter_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_course_id` (`content_id`) USING BTREE,
  KEY `idx_chapter_id` (`chapter_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT COMMENT='作品视频';

-- ----------------------------
-- Records of video_content_video
-- ----------------------------
INSERT INTO `video_content_video` VALUES ('1', '1320633170208923650', '1323181700345638913', '第111小节122323', '52bf6e4bc3094e1793f7a851fbb9362d', 'testvod.mp4', '0', '0', '1', '15.3', 'Empty', '0', '1', '2020-10-29 16:17:21', '2020-12-29 10:55:04');
INSERT INTO `video_content_video` VALUES ('1323519770144174081', '1320633170208923650', '1323181700345638913', 'aaaa', '6c688b0d042b493c91f4eb46aed4e853', '01-计算机语言概述.mp4', '2', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-03 14:57:58', '2020-12-29 22:48:07');
INSERT INTO `video_content_video` VALUES ('1323519812980600833', '1320633170208923650', '1323181700345638913', '丫头-抖音', 'c5834030379a4c7b98ab8068a2f37d65', '丫头-冬瓜西瓜哈密瓜', '0', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-03 14:58:08', '2020-11-03 14:58:08');
INSERT INTO `video_content_video` VALUES ('1323526670378283009', '1320633170208923650', '1323181700345638913', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '1', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-03 15:25:23', '2020-11-03 15:25:23');
INSERT INTO `video_content_video` VALUES ('1323915366231322625', '1320633170208923650', '1323181728690745346', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '0', '0', '1', '15.3', 'Empty', '0', '1', '2020-11-04 17:09:55', '2020-11-04 17:09:55');
INSERT INTO `video_content_video` VALUES ('1323916035860344834', '1323915951118626817', '1323916012850393089', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '0', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-04 17:12:35', '2020-11-22 15:08:39');
INSERT INTO `video_content_video` VALUES ('1323916066977886210', '1323915951118626817', '1323916012850393089', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '0', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-04 17:12:42', '2020-11-04 17:12:42');
INSERT INTO `video_content_video` VALUES ('1323916119662538753', '1323915951118626817', '1323916095612399617', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '0', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-04 17:12:55', '2020-11-06 16:20:02');
INSERT INTO `video_content_video` VALUES ('1327251081145040898', '1320633170208923650', '1323181700345638913', '丫头-抖音', '836b7eaf3e2f4533b0a7d02d03714026', '丫头-抖音', '0', '0', '1', '15.3', 'Empty', '0', '1', '2020-11-13 22:04:52', '2020-11-13 22:04:52');
INSERT INTO `video_content_video` VALUES ('1448484476228161537', '1448484238369181697', '1448484310527987714', '1.什么是Java？', '48844a50a34647898ddadc170df2cde6', 'testvod.mp4', '1', '0', '0', '15.3', 'Empty', '0', '1', '2021-10-14 11:03:05', '2021-10-28 16:37:50');
INSERT INTO `video_content_video` VALUES ('1449387937418944514', '1449386177602232321', '1449387809282957314', '1111', '221664286c3e434c8ab7f5b101a9607a', '01_Spring Cloud Alibaba简介.mp4', '1', '0', '0', '15.3', 'Empty', '0', '1', '2021-10-16 22:53:07', '2021-10-16 22:54:35');
INSERT INTO `video_content_video` VALUES ('1449390782063026178', '1449386177602232321', '1449387809282957314', '222', 'b8593405ef314bf2ab95b96b679313e5', '01_Spring Cloud Alibaba简介.mp4', '2', '0', '1', '15.3', 'Empty', '0', '1', '2021-10-16 23:04:25', '2021-10-16 23:04:25');
INSERT INTO `video_content_video` VALUES ('1449405349929394178', '1449386177602232321', '1449405176490729473', '第二节', '45afc79fbba74ce590fb9a2e6873e472', 'testvod.mp4', '1', '0', '0', '15.3', 'Empty', '0', '1', '2021-10-17 00:02:19', '2021-10-17 00:02:19');
INSERT INTO `video_content_video` VALUES ('1450515426140717058', '1449386177602232321', '1449405176490729473', '01-撩课学院-项目开篇(掌握)', '48844a50a34647898ddadc170df2cde6', '01-撩课学院-项目开篇(掌握).mp4', '2', '0', '1', '0', 'Empty', '0', '1', '2021-10-20 01:33:22', '2021-10-20 01:33:22');
INSERT INTO `video_content_video` VALUES ('1451426482918834178', '1451423070118526977', '1451423204474667009', '11111', 'b80b22da00b94e558bfad78632ae7f7c', '9124c1b9af772190e0928f9f3409ad65.mp4', '1', '0', '1', '0', 'Empty', '0', '1', '2021-10-22 13:53:34', '2021-10-22 13:53:34');
INSERT INTO `video_content_video` VALUES ('3', '1320633170208923650', '1323181728690745346', '第1小节', 'b8593405ef314bf2ab95b96b679313e5', '01_Spring Cloud Alibaba简介.mp4', '0', '0', '0', '15.3', 'Empty', '0', '1', '2020-11-01 11:52:57', '2020-11-01 11:53:01');
