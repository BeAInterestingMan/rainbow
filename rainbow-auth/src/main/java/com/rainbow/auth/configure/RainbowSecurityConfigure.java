package com.rainbow.auth.configure;

import com.rainbow.auth.service.impl.RainbowUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *  @Description  security web层配置
 *  @author liuhu
 *  @Date 2020/5/13 9:09
 */
@Order(2)
@EnableWebSecurity
public class RainbowSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private RainbowUserDetailService userDetailService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    /**
     * @Description oauth2.0密码模式认证器
     * @author liuhu
     * @createTime 2020-05-13 09:11:11
     * @param
     * @return org.springframework.security.authentication.AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                .requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
