package com.shadowlayover.common.web.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/30-17:15
 * @desc: 防止重复提交注解
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShadowlayoverRepeatSubmit {
}
