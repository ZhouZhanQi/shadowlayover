package com.shadowlayover.common.db.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        log.debug(">>> start insert fill ...");
        this.strictInsertFill(metaObject, "create_time", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "update_time", () -> LocalDateTime.now(), LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug(">>> start update fill ...");
        this.strictUpdateFill(metaObject, "update_time", () -> LocalDateTime.now(), LocalDateTime.class);
    }
}
