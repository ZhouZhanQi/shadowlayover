package com.shadowlayover.common.core.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/12/1-10:54
 * @desc: 上下文信息
 * </pre>
 */
@Data
@Builder
public class ShadowlayoverContext implements Serializable {

    private static final long serialVersionUID = 1832447871635643649L;

    /**
     * 用户信息
     */
    private LoginUser user;

    /**
     * 链路Id
     */
    private String traceId;
}
