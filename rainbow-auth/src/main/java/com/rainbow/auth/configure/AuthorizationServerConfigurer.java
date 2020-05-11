package com.rainbow.auth.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 *  @Description 认证服务器
 *  @author liuhu
 *  @Date 2020/5/7 14:44
 */
@EnableAuthorizationServer
@Configuration
public class  AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    /**
     * @Description 客户端信息配置
     * @author liuhu
     * @createTime
     * @param clients
     * @return void
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService()
                .tokenStore()
    }


}
