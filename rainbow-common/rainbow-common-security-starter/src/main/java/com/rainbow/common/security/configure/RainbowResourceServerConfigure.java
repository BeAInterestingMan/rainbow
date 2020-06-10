package com.rainbow.common.security.configure;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.rainbow.common.core.constant.EndpointConstant;
import com.rainbow.common.security.handler.RainbowAccessDeniedHandler;
import com.rainbow.common.security.handler.RainbowAuthExceptionEntryPoint;
import com.rainbow.common.security.properties.RainbowCloudSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
/**
 *  @Description 资源服务器配置
 *  @author liuhu
 *  @Date 2020/5/13 11:07
 */
@EnableResourceServer
@Configuration
@RequiredArgsConstructor
public class RainbowResourceServerConfigure extends ResourceServerConfigurerAdapter {

    private final RainbowAccessDeniedHandler accessDeniedHandler;

    private final RainbowAuthExceptionEntryPoint authExceptionEntryPoint;

    private final RainbowCloudSecurityProperties securityProperties;

    /**
     * @Description 配置处理403 401
     * @author liuhu
     * @createTime 2020-05-13 11:18:08
     * @param resources
     * @return void
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
          resources.authenticationEntryPoint(authExceptionEntryPoint)
                  .accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * @Description 所有请求必须认证通过才能访问
     * @author liuhu
     * @createTime 2020-05-13 11:18:27
     * @param http
     * @return void
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 免认证路径
        String[] anonUrls = StringUtils.split(securityProperties.getAnonUris(), StringPool.COMMA);
        if (ArrayUtils.isEmpty(anonUrls)) {
            anonUrls = new String[]{};
        }
       http.cors().disable()
               .requestMatchers()
               .antMatchers(EndpointConstant.ALL)
               .and()
               .authorizeRequests()
               .antMatchers(anonUrls)
               .permitAll()
               .antMatchers(EndpointConstant.ALL)
               .authenticated();

    }
}
