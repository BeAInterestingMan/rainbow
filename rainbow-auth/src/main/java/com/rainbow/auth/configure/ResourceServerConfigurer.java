package com.rainbow.auth.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *  @Description 资源服务器
 *  @author liuhu
 *  @Date 2020/5/7 14:42
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
    /**
     * @Description 所有请求都必须认证通过
     * @author liuhu
     * @createTime
     * @param http
     * @return void
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and()
                .cors()
                .disable();
    }
}
