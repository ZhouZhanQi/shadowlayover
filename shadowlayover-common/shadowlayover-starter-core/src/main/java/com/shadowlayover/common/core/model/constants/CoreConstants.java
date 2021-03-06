package com.shadowlayover.common.core.model.constants;

/**
 * @author zhouzhanqi
 * @date 2021/10/10 1:07 上午
 * @desc 基础静态常量
 */
public interface CoreConstants {

    /**
     * 基础远程client路径
     */
    String BASE_CLIENT_PACKAGE = "com.shadowlayover.*.client";
    
    /**
     * 链路追踪Id
     */
    String SHADOWLOYOVER_TRACE_ID = "shadowlayover-trace-id";

    /**
     * 用户信息
     */
    String SHADOWLOYOVER_USER_INFO = "shadowlayover-user-info";

    /**
     * 日志链路Id
     */
    String LOG_TRACE_ID = "traceId";
}
