package com.shadowlayover.common.db.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author zhouzhanqi
 * @date 2021/7/22 10:20 上午
 * @desc 元对象处理器
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            //获取上下文信息
            log.debug(">>>>>> start insert fill ...");
        }
        this.strictInsertFill(metaObject, "creator_id", () -> 0L, Long.class);
        this.strictInsertFill(metaObject, "creator", () -> "admin", String.class);
        this.strictInsertFill(metaObject, "create_time", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updater_id", () -> 0L, Long.class);
        this.strictInsertFill(metaObject, "updater", () -> "admin", String.class);
        this.strictInsertFill(metaObject, "update_time", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "trace_id", () -> UUID.randomUUID().toString().replaceAll("-", ""), String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            //获取上下文信息
            log.debug(">>>>>> start update fill ...");
        }
        this.strictInsertFill(metaObject, "updater_id", () -> 0L, Long.class);
        this.strictInsertFill(metaObject, "updater", () -> "admin", String.class);
        this.strictUpdateFill(metaObject, "update_time", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "trace_id", () -> UUID.randomUUID().toString().replaceAll("-", ""), String.class);
    }
}
