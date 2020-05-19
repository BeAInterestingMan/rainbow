package com.rainbow.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 *  @Description 网关属性
 *  @author liuhu
 *  @Date 2020/5/18 15:29
 */
@ConfigurationProperties(prefix = "rainbow.gateway")
@PropertySource("classpath:rainbowGateway.properties")
@Configuration
@Data
public class RainbowGatewayProperties {
    /**黑名单url*/
    private String forbidUrl;
}
