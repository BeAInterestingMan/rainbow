package com.rainbow.common.security.configure;

import com.rainbow.common.core.entity.RainbowResponse;
import com.rainbow.common.core.entity.system.Role;
import com.rainbow.common.security.handler.RainbowAccessDeniedHandler;
import com.rainbow.common.security.handler.RainbowAuthExceptionEntryPoint;
import com.rainbow.common.security.properties.RainbowCloudSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *  @Description  oauth 相关配置 利用自动装配实现
 *  @author liuhu
 *  @Date 2020/5/12 16:44
 */
@Configuration
public class RainbowSecurityAutoConfigure {

    /**
     * @Description 注入 security加密类
     * @author liuhu
     * @createTime 2020-05-12 16:58:52
     * @param 
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public RainbowAccessDeniedHandler accessDeniedHandler(){
         return new RainbowAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public RainbowAuthExceptionEntryPoint authExceptionEntryPoint(){
        return new RainbowAuthExceptionEntryPoint();
    }


    @Bean
    @ConditionalOnMissingBean(name = "rainbowCloudSecurityProperties")
    public  RainbowCloudSecurityProperties rainbowCloudSecurityProperties(){
        return new RainbowCloudSecurityProperties();
    }

}
