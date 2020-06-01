package com.rainbow.log.api.annotation;

import java.lang.annotation.*;

/**
 *  @Description 自定义日志注解
 *  @author liuhu
 *  @Date 2020/5/29 15:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RainbowLog {
    String description();

}
