CREATE TABLE `tbl_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
                            `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
                            `status` int(2) NOT NULL DEFAULT '2' COMMENT '状态 1:可用,2不可用',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `username_key` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;