package com.rainbow.auth.configure;

import com.rainbow.common.security.handler.RainbowAccessDeniedHandler;
import com.rainbow.common.security.handler.RainbowAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private RainbowAccessDeniedHandler deniedHandler;

    @Autowired
    private RainbowAuthExceptionEntryPoint exceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/*")
                .and()
                .authorizeRequests()
                .antMatchers("/*").authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
       resources.accessDeniedHandler(deniedHandler)
               .authenticationEntryPoint(exceptionEntryPoint);
    }
}
