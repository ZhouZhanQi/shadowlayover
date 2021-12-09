package com.shadowlayover.common.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.shadowlayover.common.core.model.ShadowlayoverContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/18-15:21
 * @desc: 令牌信息
 * </pre>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShadowlayoverThreadContext {
    private static final ThreadLocal<ShadowlayoverContext> threadLocalMap = new TransmittableThreadLocal<>();
    
    public static void setContext(ShadowlayoverContext shadowlayoverContext) {
        threadLocalMap.set(shadowlayoverContext);
    }
    
    public static ShadowlayoverContext getContext() {
        return threadLocalMap.get();
    }
    
    public static void clear() {
        threadLocalMap.remove();
    }
}
