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