package com.shadowlayover.common.web.annotation;

import com.shadowlayover.common.web.config.AsyncAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/3-14:02
 * @desc: 自动装配异步处理配置
 * </pre>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ AsyncAutoConfiguration.class })
public @interface EnableShadowlayoverAsync {
}
