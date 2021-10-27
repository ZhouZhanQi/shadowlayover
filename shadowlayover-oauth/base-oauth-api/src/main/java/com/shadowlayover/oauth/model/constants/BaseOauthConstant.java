package com.shadowlayover.oauth.model.constants;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 3:53 上午
 * @desc 认证服务常量
 */
public interface BaseOauthConstant {
    
    
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
    String CLIENT_BASE = "select client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, " +
            "authorized_grant_types, web_server_redirect_uri, authorities, access_token_expires_in," +
            "refresh_token_expires_in, description, autoapprove from " + CLIENT_TABLE;

    String SELECT_CLIENT_DETAIL_SQL = CLIENT_BASE + " where client_id = ?";
    String FIND_CLIENT_DETAIL_SQL = CLIENT_BASE + " order by client_id";
}
