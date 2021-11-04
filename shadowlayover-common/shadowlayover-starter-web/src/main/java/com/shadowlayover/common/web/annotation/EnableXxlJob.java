package com.shadowlayover.common.web.annotation;

import com.shadowlayover.common.web.config.XxlJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/30-15:50
 * @desc: 自动装配xxl-job
 * </pre>
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ XxlJobAutoConfiguration.class })
public @interface EnableXxlJob {
}
