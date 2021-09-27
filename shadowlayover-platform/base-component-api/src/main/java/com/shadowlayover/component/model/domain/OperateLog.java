package com.shadowlayover.component.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shadowlayover.common.db.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Getter
@Setter
@TableName("operate_log")
public class OperateLog extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 内容类型
     */
    private String requestContentType;

    /**
     * 请求参数
     */
    private String queryParam;

    /**
     * 请求数据
     */
    private String requestBodyData;

    /**
     * 响应数据
     */
    private String responseData;

    /**
     * 响应编码
     */
    private String responseCode;

    /**
     * 响应消息
     */
    private String responseMsg;


}
