package com.rainbow.common.security.configure;

import com.rainbow.common.core.constant.RainbowConstant;
import com.rainbow.common.core.entity.RainbowResponse;
import com.rainbow.common.core.entity.system.Role;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.common.security.handler.RainbowAccessDeniedHandler;
import com.rainbow.common.security.handler.RainbowAuthExceptionEntryPoint;
import com.rainbow.common.security.properties.RainbowCloudSecurityProperties;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 *  @Description  oauth 相关配置 利用自动装配实现
 *  @author liuhu
 *  @Date 2020/5/12 16:44
 */
@Configuration
public class RainbowSecurityAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "securityProperties")
    public RainbowCloudSecurityProperties securityProperties(){
        return new RainbowCloudSecurityProperties();
    }

    /**
     * @Description 注入 security加密类
     * @author liuhu
     * @createTime 2020-05-12 16:58:52
     * @param 
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
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

    /**
     * @Description feign 请求转发不会携带token  手动加入token
     * @author liuhu
     * @createTime 2020-05-13 18:57:32
     * @param
     * @return feign.RequestInterceptor
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 添加 gateway Token
            String gatewayToken = new String(Base64Utils.encode(RainbowConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(RainbowConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            // 得到当前的access_token 放入请求头
            String authorizationToken = RainbowUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, RainbowConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }

    /**
     * @Description 注入微服务防护
     * @author liuhu
     * @createTime 2020-05-19 16:42:07
     * @param
     * @return com.rainbow.common.security.configure.RainbowProtectConfigure
     */
    @Bean
    @ConditionalOnProperty(name = "rainbow.cloud.security.enable",havingValue = "true",matchIfMissing = true)
    public RainbowProtectConfigure protectConfigure(){
        return new RainbowProtectConfigure();
    }
}
