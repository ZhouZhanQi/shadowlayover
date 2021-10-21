package com.shadowlayover.common.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.shadowlayover.common.core.model.LoginUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/18-15:21
 * @desc: 令牌信息
 * </pre>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShadowlayoverUserContext {
    private static final ThreadLocal<LoginUser> threadLocalMap = new TransmittableThreadLocal<>();
    
    public static void setUserContext(LoginUser user) {
        threadLocalMap.set(user);
    }
    
    public static LoginUser getUserContext() {
        return threadLocalMap.get();
    }
    
    public static void clear() {
        threadLocalMap.remove();
    }
}
