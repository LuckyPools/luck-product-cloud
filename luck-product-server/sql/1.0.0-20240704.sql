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
    `username` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
    `password` varchar(255) NULL DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-用户';
;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-请求日志';


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-角色';


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-角色权限';


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-菜单';

INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('1', '1', '2024-09-11 19:50:26', '1', '2024-09-11 19:50:36', '密码：1', 0, '张三', 'zhangsan', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('10', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '郭双', 'guoshuang', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('2', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '李四', 'lisi', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('3', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '王五', 'lisi', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('4', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '赵六', 'zhaoliu', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('5', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '朱七', 'zhuqi', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('6', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '林八', 'lingba', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('7', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '苏九儿', 'sujiu', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('8', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '石头', 'shitou', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('9', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '石一', 'shiyi', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('12', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '封夕', 'fengxi', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');
INSERT INTO `luck_product`.`sys_user`(`id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `is_delete`, `name`, `username`, `password`) VALUES ('13', '1', '2024-09-07 19:50:30', '1', '2024-09-11 19:50:38', '密码：1', 0, '燕朝雪', 'yanchaoxue', '$2a$10$6H.bBrjttIut1E.kx69vTunItpW2XmMCXszvX/VtuKZEUIBecqftm');

ALTER TABLE sys_user COMMENT '系统管理-用户';
ALTER TABLE sys_log_request COMMENT '系统管理-请求日志';
ALTER TABLE sys_role COMMENT '系统管理-角色';
ALTER TABLE sys_role_menu COMMENT '系统管理-角色权限';
ALTER TABLE sys_menu COMMENT '系统管理-菜单';

CREATE TABLE `sys_dict_type` (
     `id` varchar(64) NOT NULL COMMENT 'ID',
     `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
     `create_date` datetime DEFAULT NULL COMMENT '创建时间',
     `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
     `update_date` datetime DEFAULT NULL COMMENT '更新时间',
     `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
     `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
     `name` varchar(255)  DEFAULT NULL COMMENT '字典类型名称',
     `code` varchar(255)  DEFAULT NULL COMMENT '字典类型编码',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-字典类型';


CREATE TABLE `sys_dict_item` (
     `id` varchar(64) NOT NULL COMMENT 'ID',
     `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
     `create_date` datetime DEFAULT NULL COMMENT '创建时间',
     `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
     `update_date` datetime DEFAULT NULL COMMENT '更新时间',
     `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
     `is_delete` tinyint(1) NOT NULL COMMENT '是否删除',
     `name` varchar(255)  DEFAULT NULL COMMENT '字典项名称',
     `code` varchar(255)  DEFAULT NULL COMMENT '字典项编码',
     `dict_id` varchar(255)  DEFAULT NULL COMMENT '字典类型ID',
     `dict_code` varchar(255)  DEFAULT NULL COMMENT '字典类型编码',
     `parent_id` varchar(255)  DEFAULT NULL COMMENT '父级ID',
     `parent_ids` varchar(2000)  DEFAULT NULL COMMENT '所有父级ID路径',
     `filter` varchar(255) DEFAULT NULL COMMENT '分类标识',
     `sort` int DEFAULT NULL COMMENT '排序',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理-字典项';


