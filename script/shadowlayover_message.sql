CREATE TABLE `sys_notice` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
                              `trace_id` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '链路Id',
                              `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                              `creator_id` bigint NOT NULL COMMENT '创建者Id',
                              `creator` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `updater_id` bigint NOT NULL COMMENT '更新者Id',
                              `updater` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新时间',
                              `update_time` datetime NOT NULL COMMENT '更新时间',
                              `notice_type` smallint NOT NULL COMMENT '通知类型;1通知 2公告',
                              `notice_title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知标题;公告标题',
                              `notice_content` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知内容;公告内容',
                              `status` smallint NOT NULL COMMENT '通知状态;状态0正常1禁用',
                              `remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注;备注',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统通知';