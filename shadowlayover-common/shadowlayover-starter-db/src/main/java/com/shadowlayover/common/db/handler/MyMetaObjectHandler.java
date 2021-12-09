package com.shadowlayover.common.db.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shadowlayover.common.core.context.ShadowlayoverThreadContext;
import com.shadowlayover.common.core.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author zhouzhanqi
 * @date 2021/7/22 10:20 上午
 * @desc 元对象处理器
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            //获取上下文信息
            log.debug(">>>>>> start insert fill ...");
        }
        String traceId = ShadowlayoverThreadContext.getContext().getTraceId();
        LoginUser contextUser = ShadowlayoverThreadContext.getContext().getUser();
        this.strictInsertFill(metaObject, "creatorId", () -> contextUser.getUserId(), Long.class);
        this.strictInsertFill(metaObject, "creator", () -> contextUser.getUserName(), String.class);
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updaterId", () -> contextUser.getUserId(), Long.class);
        this.strictInsertFill(metaObject, "updater", () -> contextUser.getUserName(), String.class);
        this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "traceId", () -> traceId, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            //获取上下文信息
            log.debug(">>>>>> start update fill ...");
        }
        String traceId = ShadowlayoverThreadContext.getContext().getTraceId();
        LoginUser contextUser = ShadowlayoverThreadContext.getContext().getUser();
        this.strictInsertFill(metaObject, "updaterId", () -> contextUser.getUserId(), Long.class);
        this.strictInsertFill(metaObject, "updater", () -> contextUser.getUserName(), String.class);
        this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "traceId", () -> traceId, String.class);
    }
}
