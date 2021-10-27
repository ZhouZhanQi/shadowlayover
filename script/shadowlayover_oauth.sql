CREATE TABLE sys_user
(
    id              BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    user_name       VARCHAR(32) NOT NULL COMMENT '用户名;用户名',
    password        VARCHAR(64) NOT NULL COMMENT '密码;密码',
    nickname        VARCHAR(64) COMMENT '昵称;昵称',
    real_name       VARCHAR(64) COMMENT '真实姓名;真实姓名',
    mobile_phone    CHAR(11) COMMENT '手机号码;手机号码',
    email           VARCHAR(64) COMMENT '邮箱;邮箱',
    sex             SMALLINT(2) DEFAULT 0 COMMENT '性别;性别',
    tenant_id       BIGINT(20) NOT NULL COMMENT '租户Id;租户Id',
    last_login_ip   VARCHAR(128) COMMENT '最后登录Ip;最后登录Ip',
    last_login_time DATETIME COMMENT '最后登录时间;最后登录时间',
    user_type       SMALLINT DEFAULT NULL COMMENT '用户类型; 10-平台用户, 20-租户用户',
    status          SMALLINT DEFAULT NULL COMMENT '用户状态; 0-正常, 1-禁用, 2-锁定',
    is_super        TINYINT(1) DEFAULT NULL COMMENT '是否为超级管理员',
    is_deleted      TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    trace_id        VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id      BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator         VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time     DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id      INT         NOT NULL COMMENT '更新人Id;更新人',
    updater         VARCHAR(64) COMMENT '更新人',
    update_time     DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统用户';


CREATE
UNIQUE INDEX uqx_user_name ON sys_user (user_name);

CREATE TABLE sys_role
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    role_code   VARCHAR(64) NOT NULL COMMENT '角色编码;角色编码',
    role_name   VARCHAR(64) NOT NULL COMMENT '角色名称;角色名称',
    data_scop   SMALLINT(2) NOT NULL COMMENT '数据权限;数据权限',
    order_num   INT(4) NOT NULL COMMENT '排序;排序',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    remark      VARCHAR(512) COMMENT '角色备注;橘色备注',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统角色';

CREATE TABLE sys_resource
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    resource_code VARCHAR(64) NOT NULL COMMENT '资源编码;资源编码',
    resource_name VARCHAR(64) NOT NULL COMMENT '资源名称;资源名称',
    parent_id     BIGINT(20) NOT NULL COMMENT '父资源Id;父资源Id',
    order_num     INT(4) NOT NULL COMMENT '排序;排序',
    path          VARCHAR(256) COMMENT '路由地址;路由地址',
    component     VARCHAR(256) COMMENT '组件路径;路由路径',
    is_frame      TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否为外链;是否为外链',
    is_cache      TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否缓存;是否缓存',
    is_visible    TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否显示;是否可见',
    icon          VARCHAR(256) COMMENT '资源图标;资源图标',
    resource_type SMALLINT(2) NOT NULL COMMENT '资源类型;资源类型',
    status        SMALLINT(2) NOT NULL COMMENT '状态;状态',
    remark        VARCHAR(512) COMMENT '资源备注;备注',
    is_deleted    TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id      VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id    BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator       VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time   DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id    INT         NOT NULL COMMENT '更新人Id;更新人',
    updater       VARCHAR(64) COMMENT '更新人',
    update_time   DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统资源';


CREATE
UNIQUE INDEX uqx_resource_code ON sys_resource (resource_code);

CREATE TABLE sys_tenant
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    tenant_code VARCHAR(64) NOT NULL COMMENT '租户编码;租户编码',
    tenant_name VARCHAR(64) NOT NULL COMMENT '租户名称;租户名称',
    status      SMALLINT(2) NOT NULL COMMENT '状态;租户状态',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统租户';

CREATE TABLE sys_dept
(
    id           BIGINT(20) NOT NULL COMMENT '主键Id;主键id',
    dept_name    VARCHAR(64) NOT NULL COMMENT '部门名称;部门名称',
    leader       VARCHAR(64) NOT NULL COMMENT '部门负责人;部门负责人',
    phone        VARCHAR(16) COMMENT '部门电话;部门电话',
    email        VARCHAR(64) COMMENT '部门邮件;部门邮箱',
    status       SMALLINT(2) NOT NULL COMMENT '部门状态;部门状态',
    order_num    INT(4) NOT NULL COMMENT '部门排序;部门排序',
    parent_id    BIGINT(20) NOT NULL COMMENT '父部门Id;父部门Id',
    tenant_id    BIGINT(20) NOT NULL COMMENT '部门所属租户;部门所属租户',
    is_deleted   TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id     VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id   BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator      VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    created_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id   INT         NOT NULL COMMENT '更新人Id;更新人',
    updater      VARCHAR(64) COMMENT '更新人',
    update_time  DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统租户部门';

CREATE TABLE sys_post
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    post_code   VARCHAR(64) NOT NULL COMMENT '职位编码;职位编码',
    post_name   VARCHAR(64) NOT NULL COMMENT '职位名称;职位名称',
    dept_id     BIGINT(20) NOT NULL COMMENT '部门Id;部门Id',
    order_num   INT(4) NOT NULL COMMENT '排序;排序',
    status      SMALLINT(2) NOT NULL COMMENT '状态;状态',
    remark      VARCHAR(512) COMMENT '备注;备注',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统租户职位';

CREATE TABLE sys_resource_permit
(
    id                BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键id',
    permit_code       VARCHAR(64)  NOT NULL COMMENT '权限编码;权限编码',
    permit_name       VARCHAR(64)  NOT NULL COMMENT '权限名称;权限名称',
    permit_action_uri VARCHAR(256) NOT NULL COMMENT '权限请求;权限URI',
    resource_code     VARCHAR(64)  NOT NULL COMMENT '权限所属资源;权限所属资源编码',
    is_deleted        TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id          VARCHAR(64)  NOT NULL COMMENT '调用链',
    creator_id        BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator           VARCHAR(64)  NOT NULL COMMENT '创建人;创建人',
    create_time       DATETIME     NOT NULL COMMENT '创建时间;创建时间',
    updater_id        INT          NOT NULL COMMENT '更新人Id;更新人',
    updater           VARCHAR(64) COMMENT '更新人',
    update_time       DATETIME     NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统资源权限';


CREATE
UNIQUE INDEX uqx_permit_code ON sys_resource_permit (permit_code);

CREATE TABLE sys_group
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    group_code  VARCHAR(64) NOT NULL COMMENT '用户组编码;用户组编码',
    group_name  VARCHAR(64) NOT NULL COMMENT '用户组名称;用户组名称',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统用户组';

CREATE TABLE sys_user_group
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    user_id     VARCHAR(32) NOT NULL COMMENT '用户Id',
    group_code  VARCHAR(32) NOT NULL COMMENT '用户组编码',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '用户组关联';

CREATE TABLE sys_user_post
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    user_id     BIGINT(20) NOT NULL COMMENT '用户Id;用户Id',
    post_id     BIGINT(20) NOT NULL COMMENT '职位Id;职位Id',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '用户职位关联';

CREATE TABLE sys_role_post
(
    id          BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    role_id     BIGINT(20) NOT NULL COMMENT '角色Id;角色Id',
    post_id     BIGINT(20) NOT NULL COMMENT '身份Id;身份Id',
    is_deleted  TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id    VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id  BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator     VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id  INT         NOT NULL COMMENT '更新人Id;更新人',
    updater     VARCHAR(64) COMMENT '更新人',
    update_time DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '角色身份关联';

CREATE TABLE sys_role_resource
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id;主键Id',
    role_id       BIGINT(20) NOT NULL COMMENT '角色Id;角色Id',
    resource_code VARCHAR(64) NOT NULL COMMENT '资源编码;资源编码',
    is_deleted    TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除;是否删除',
    trace_id      VARCHAR(64) NOT NULL COMMENT '调用链',
    creator_id    BIGINT(20) NOT NULL COMMENT '创建人Id',
    creator       VARCHAR(64) NOT NULL COMMENT '创建人;创建人',
    create_time   DATETIME    NOT NULL COMMENT '创建时间;创建时间',
    updater_id    INT         NOT NULL COMMENT '更新人Id;更新人',
    updater       VARCHAR(64) COMMENT '更新人',
    update_time   DATETIME    NOT NULL COMMENT '更新时间;更新时间',
    PRIMARY KEY (id)
) COMMENT = '角色资源关联';

CREATE TABLE `sys_client`
(
    `id`                     bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `client_id`              varchar(128) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '客户端Id',
    `client_secret`          varchar(1024) COLLATE utf8mb4_general_ci                      NOT NULL COMMENT '客户端密钥',
    `resource_ids`           varchar(256) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '资源集合',
    `scope`                  varchar(64) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '授权范围',
    `authorized_grant_types` varchar(64) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '授权类型',
    `authorities`            varchar(255) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '权限',
    `redirect_uri`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回调地址',
    `access_token_validity`  int                                                           NOT NULL COMMENT '令牌过期时间',
    `refresh_token_validity` int                                                           NOT NULL COMMENT '刷新令牌过期时间',
    `autoapprove`            varchar(16) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '是否自动授权',
    `additional_information` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '说明',
    `trace_id`               varchar(64) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '链路Id',
    `is_deleted`             tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    `creator_id`             bigint                                                        NOT NULL COMMENT '创建者Id',
    `creator`                varchar(64) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '创建者',
    `create_time`            datetime                                                      NOT NULL COMMENT '创建时间',
    `updater_id`             bigint                                                        NOT NULL COMMENT '更新者Id',
    `updater`                varchar(64) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '更新时间',
    `update_time`            datetime                                                      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT='客户端';

