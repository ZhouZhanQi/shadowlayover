DROP TABLE IF EXISTS operate_log;
CREATE TABLE operate_log
(
    id                   BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    trace_id             VARCHAR(64)  NOT NULL COMMENT '链路Id',
    is_deleted           TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    creator_id           BIGINT(20) NOT NULL COMMENT '创建者Id',
    creator              VARCHAR(64)  NOT NULL COMMENT '创建者',
    create_time          DATETIME     NOT NULL COMMENT '创建时间',
    updater_id           BIGINT(20) NOT NULL COMMENT '更新者Id',
    updater              VARCHAR(64)  NOT NULL COMMENT '更新时间',
    update_time          DATETIME     NOT NULL COMMENT '更新时间',
    service_name         VARCHAR(128) NOT NULL COMMENT '服务名称',
    module_name          VARCHAR(128) NOT NULL COMMENT '模块名称',
    request_url          VARCHAR(255) COMMENT '请求地址',
    request_method       VARCHAR(16) COMMENT '请求方法',
    request_content_type VARCHAR(64) COMMENT '内容类型',
    query_param          VARCHAR(2048) COMMENT '请求参数',
    request_body_data    VARCHAR(4096) COMMENT '请求数据',
    response_data        TEXT COMMENT '响应数据',
    response_code        VARCHAR(64) COMMENT '响应编码',
    response_msg         VARCHAR(255) COMMENT '响应消息',
    PRIMARY KEY (id)
) COMMENT = '系统日志';

DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config
(
    id           BIGINT(20) NOT NULL COMMENT '主键Id',
    trace_id     VARCHAR(64)   NOT NULL COMMENT '链路Id',
    is_deleted   TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    creator_id   BIGINT(20) NOT NULL COMMENT '创建者Id',
    creator      VARCHAR(64)   NOT NULL COMMENT '创建者',
    create_time  DATETIME      NOT NULL COMMENT '创建时间',
    updater_id   BIGINT(20) NOT NULL COMMENT '更新者Id',
    updater      VARCHAR(64)   NOT NULL COMMENT '更新时间',
    update_time  DATETIME      NOT NULL COMMENT '更新时间',
    config_type  SMALLINT(6) NOT NULL COMMENT '配置类型',
    config_key   VARCHAR(64)   NOT NULL COMMENT '配置key',
    config_value VARCHAR(1024) NOT NULL COMMENT '配置value',
    sys_default  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否为系统默认',
    remark       VARCHAR(512) COMMENT '',
    PRIMARY KEY (id)
) COMMENT = '系统配置';

DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict
(
    id           BIGINT(20) NOT NULL COMMENT '主键Id',
    trace_id     VARCHAR(64)  NOT NULL COMMENT '链路Id',
    is_deleted   TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    creator_id   BIGINT(20) NOT NULL COMMENT '创建者Id',
    creator      VARCHAR(64)  NOT NULL COMMENT '创建者',
    create_time  DATETIME     NOT NULL COMMENT '创建时间',
    updater_id   BIGINT(20) NOT NULL COMMENT '更新者Id',
    updater      VARCHAR(64)  NOT NULL COMMENT '更新时间',
    update_time  DATETIME     NOT NULL COMMENT '更新时间',
    service_name VARCHAR(64)  NOT NULL COMMENT '服务名称',
    module_name  VARCHAR(64)  NOT NULL COMMENT '模块名称',
    dict_code    VARCHAR(64)  NOT NULL COMMENT '词典编码',
    dict_name    VARCHAR(128) NOT NULL COMMENT '词典名称',
    dict_desc    VARCHAR(512) COMMENT '词典描述',
    status       SMALLINT(6) NOT NULL COMMENT '状态;0正常1禁用',
    sort         INT(11) NOT NULL COMMENT '排序',
    PRIMARY KEY (id)
) COMMENT = '系统词典';

DROP TABLE IF EXISTS sys_dict_item;
CREATE TABLE sys_dict_item
(
    id              BIGINT(20) NOT NULL COMMENT '主键Id',
    trace_id        VARCHAR(64)  NOT NULL COMMENT '链路Id',
    is_deleted      TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    creator_id      BIGINT(20) NOT NULL COMMENT '创建者Id',
    creator         VARCHAR(64)  NOT NULL COMMENT '创建者',
    create_time     DATETIME     NOT NULL COMMENT '创建时间',
    updater_id      BIGINT(20) NOT NULL COMMENT '更新者Id',
    updater         VARCHAR(64)  NOT NULL COMMENT '更新时间',
    update_time     DATETIME     NOT NULL COMMENT '更新时间',
    dict_id         BIGINT(20) NOT NULL COMMENT '词典Id',
    dict_item_code  VARCHAR(64)  NOT NULL COMMENT '词典项编码',
    dict_item_name  VARCHAR(128) NOT NULL COMMENT '词典项名称',
    dict_item_value VARCHAR(512) NOT NULL COMMENT '词典项值',
    dict_item_desc  VARCHAR(255) COMMENT '词典项描述',
    sys_default     TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否为系统默认;0否1是',
    status          SMALLINT(6) COMMENT '状态;0启用1禁用',
    sort            INT(11) COMMENT '排序',
    PRIMARY KEY (id)
) COMMENT = '系统词典';