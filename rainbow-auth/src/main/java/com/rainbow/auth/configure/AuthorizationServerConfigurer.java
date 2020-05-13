package com.rainbow.auth.configure;

import com.rainbow.auth.service.impl.RainbowUserDetailService;
import com.rainbow.auth.service.impl.RedisClientDetailsService;
import com.rainbow.auth.translator.RainbowWebExceptionTranslator;
import com.rainbow.common.security.handler.RainbowAccessDeniedHandler;
import com.rainbow.common.security.handler.RainbowAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.UUID;

/**
 *  @Description 认证服务器
 *  @author liuhu
 *  @Date 2020/5/7 14:44
 */
@EnableAuthorizationServer
@Configuration
public class  AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RainbowUserDetailService userDetailService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private RainbowWebExceptionTranslator translator;

    /**
     * @Description 客户端信息配置
     * @author liuhu
     * @createTime
     * @param clients
     * @return void
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(redisClientDetailsService);
    }

    /**
     * @Description 核心配置
     * @author liuhu
     * @createTime 2020-05-13 09:18:24
     * @param endpoints
     * @return void
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(translator);


    }

    /**
     * @Description 设置token存储方式 redis存储
     * @author liuhu
     * @createTime 2020-05-13 09:18:02
     * @param
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     */
    @Bean
    public TokenStore tokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    /**
     * @Description 默认token配置
     * @author liuhu
     * @createTime 2020-05-12 16:15:25
     * @param
     * @return org.springframework.security.oauth2.provider.token.DefaultTokenServices
     */
    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);
        return tokenServices;
    }
}
