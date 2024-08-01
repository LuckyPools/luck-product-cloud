DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
    `id` varchar(64) NOT NULL COMMENT 'ID',
    `create_by` varchar(255) NULL DEFAULT NULL COMMENT '创建人',
    `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(255) NULL DEFAULT NULL COMMENT '更新人',
    `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remarks` varchar(2000) NULL DEFAULT NULL COMMENT '备注',
    `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
    `name` varchar(64) NULL DEFAULT NULL COMMENT '姓名',
    `login_name` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
    `password` varchar(255) NULL DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', NULL, NULL, NULL, NULL, NULL, 0, '张三', 'zhangsan', '1');
INSERT INTO `sys_user` VALUES ('2', NULL, NULL, NULL, NULL, NULL, 0, '李四', 'lisi', '2');



CREATE TABLE `sys_log_request` (
   `id` varchar(64) NOT NULL COMMENT 'ID',
   `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
   `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
   `update_date` datetime DEFAULT NULL COMMENT '更新时间',
   `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
   `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
   `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
   `username` varchar(255) DEFAULT NULL COMMENT '用户名',
   `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
   `method` varchar(64) DEFAULT NULL COMMENT '请求方法',
   `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
   `params` varchar(2000) DEFAULT NULL COMMENT '请求参数',
   `agent` varchar(2000) DEFAULT NULL COMMENT '代理信息',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统请求日志';


CREATE TABLE `sys_role` (
    `id` varchar(64) NOT NULL COMMENT 'ID',
    `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
    `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
    `name` varchar(64)  DEFAULT NULL COMMENT '角色名称',
    `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
    `type` varchar(255) DEFAULT NULL COMMENT '角色类型',
    `sort` int DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';


CREATE TABLE `sys_role_menu` (
    `id` varchar(64) NOT NULL COMMENT 'ID',
    `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
    `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
    `role_id` varchar(64) DEFAULT NULL COMMENT '角色ID',
    `menu_id` varchar(255) DEFAULT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限';


CREATE TABLE `sys_menu` (
    `id` varchar(64) NOT NULL COMMENT 'ID',
    `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
    `create_date` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
    `update_date` datetime DEFAULT NULL COMMENT '更新时间',
    `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
    `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
    `name` varchar(255)  DEFAULT NULL COMMENT '菜单名称',
    `permission` varchar(255)  DEFAULT NULL COMMENT '权限',
    `parent_id` varchar(64)  DEFAULT NULL COMMENT '父菜单ID',
    `sort` int DEFAULT NULL COMMENT '排序',
    `type` varchar(255) DEFAULT NULL COMMENT '菜单类型',
    `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示：D_SYS_YES_NO',
    `path` varchar(255) DEFAULT NULL COMMENT '路由地址',
    `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
    `component` varchar(255) DEFAULT NULL COMMENT '组件名称',
    `component_path` varchar(255) DEFAULT NULL COMMENT '组件地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单';
