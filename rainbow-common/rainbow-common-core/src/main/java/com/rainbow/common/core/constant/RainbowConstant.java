package com.rainbow.common.core.constant;

public interface RainbowConstant {
    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    String OAUTH2_TOKEN_TYPE = "bearer";

    /**gateway 防护token key*/
    String GATEWAY_TOKEN_VALUE = "gateway_token";
    String GATEWAY_TOKEN_HEADER = "rainbow123456";

    String DEFAULT_PASSWORD = "rainbow";

    String DEFAULT_AVATAR = "default.jpg";
    /**
     * Java默认临时目录
     */
    public static final String JAVA_TEMP_DIR = "java.io.tmpdir";
}
