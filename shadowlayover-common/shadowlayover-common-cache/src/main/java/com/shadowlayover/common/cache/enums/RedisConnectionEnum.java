package com.shadowlayover.common.cache.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-17:53
 * @desc: redis链接枚举类
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum RedisConnectionEnum {
    
    /**
     * 单节点部署方式
     */
    STANDALONE("standalone", "单节点部署方式"),
    
    /**
     * 哨兵部署方式
     */
    SENTINEL("sentinel", "哨兵部署方式"),
    
    /**
     * 集群部署方式
     */
    CLUSTER("cluster", "集群方式"),
    
    /**
     * 主从部署方式
     */
    MASTERSLAVE("masterslave", "主从部署方式"),
    
    ;
    
    /**
     * 类型
     */
    private final String type;
    
    /**
     * 描述
     */
    private final String desc;
}
