package com.rainbow.server.system.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @Description 自定义日志注解
 *  @author liuhu
 *  @Date 2020/5/29 15:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RainbowLog {
    String description();
}
