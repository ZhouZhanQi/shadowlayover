package com.shadowlayover.common.seata.interceptor;

import io.seata.core.context.RootContext;
import io.seata.integration.http.TransactionPropagationInterceptor;
import io.seata.integration.http.XidResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/16-11:05
 * @desc: seata事务拦截器
 * </pre>
 */
@Slf4j
public class ShadowlayoverTransactionPropagationInterceptor extends TransactionPropagationInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String xid = RootContext.getXID();
        String rpcXid = request.getHeader("TX_XID");
        if (log.isDebugEnabled()) {
            log.debug("xid in RootContext[{}] xid in HttpContext[{}]", xid, rpcXid);
        }

        if (StringUtils.isNoneBlank(xid, rpcXid)) {
            RootContext.bind(rpcXid);
            if (log.isDebugEnabled()) {
                log.debug("bind[{}] to RootContext", rpcXid);
            }
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        super.postHandle(request, response, handler, modelAndView);
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (RootContext.inGlobalTransaction()) {
            XidResource.cleanXid(request.getHeader(RootContext.KEY_XID));
        }
    }
}
