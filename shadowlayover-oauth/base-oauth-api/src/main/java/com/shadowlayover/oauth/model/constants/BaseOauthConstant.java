package com.shadowlayover.oauth.model.constants;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 3:53 上午
 * @desc 认证服务常量
 */
public interface BaseOauthConstant {

    /**
     * 用户Id
     */
    String SHADOWLAYOVER_USER_ID = "shadowlayover_user_id";

    /**
     * 用户名
     */
    String SHADOWLAYOVER_USER_NAME = "shadowlayover_user_name";

    /**
     * 头像地址
     */
    String SHADOWLAYOVER_AVATAR = "shadowlayover_avatar";

    /**
     * 角色Id
     */
    String SHADOWLAYOVER_ROLE_ID = "shadowlayover_role_id";

    /**
     * 用户类型
     */
    String SHADOWLAYOVER_USER_TYPE = "shadowlayover_user_type";

    /**
     * 租户Id
     */
    String SHADOWLAYOVER_TENANT_ID = "shadowlayover_tenant_id";


    String OAUTH_MOBILE = "/oauth/mobile";
    
    /**
     * 刷新模式
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";
    
    /**
     * 默认手机号参数
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobilePhone";

    /**
     *
     */
    String CLIENT_TABLE = "sys_client";

    /**
     * 基础查询语句
     */
    String CLIENT_BASE = "select client_id, client_secret as client_secret, resource_ids, scope, " +
            "authorized_grant_types, redirect_uri, authorities, access_token_validity," +
            "refresh_token_validity, additional_information, autoapprove from " + CLIENT_TABLE;

    String SELECT_CLIENT_DETAIL_SQL = CLIENT_BASE + " where client_id = ?";
    String FIND_CLIENT_DETAIL_SQL = CLIENT_BASE + " order by client_id";
}
