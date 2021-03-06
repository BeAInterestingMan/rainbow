package com.rainbow.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "rainbow.cloud.security")
public class RainbowCloudSecurityProperties {

    /**
     * 是否开启安全配置
     */
    private Boolean enable;
    /**
     * 免认证资源路径，支持通配符
     * 多个值时使用逗号分隔
     */
    private String anonUris;

}
