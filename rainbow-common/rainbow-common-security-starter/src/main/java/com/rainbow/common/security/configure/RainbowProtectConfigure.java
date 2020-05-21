package com.rainbow.common.security.configure;

import com.rainbow.common.security.intercept.RainbowProtectInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 *  @Description 注册拦截器
 *  @author liuhu
 *  @Date 2020/5/19 16:42
 */
public class RainbowProtectConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new RainbowProtectInterceptor());
    }
}
