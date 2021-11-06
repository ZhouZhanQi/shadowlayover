package com.shadowlayover.common.cache.annotation;

import com.shadowlayover.common.cache.config.RedisCaffeineSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/5-15:50
 * @desc: 多级缓存
 * </pre>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ RedisCaffeineSelector.class })
public @interface EnableShadowlayoverCache {

    /**
     * 是否开启多级缓存
     * @return
     */
    boolean enableLayerCache() default false;
}
