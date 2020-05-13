package com.rainbow.common.security.annotation;

import com.rainbow.common.security.configure.RainbowResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  @Description 自定义注解 加上该注解开启资源服务器配置
 *  @author liuhu
 *  @Date 2020/5/13 11:22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RainbowResourceServerConfigure.class)
public @interface EnableRainbowResourceServer {
}
