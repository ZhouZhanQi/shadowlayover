package com.shadowlayover.common.cache.support;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/3-10:03
 * @desc: 缓存消息
 * </pre>
 */
@Data
@AllArgsConstructor
public class CacheMessage implements Serializable {
 
    private static final long serialVersionUID = -5601050583542550440L;
    
    private String cacheName;
    
    private Object key;
}
