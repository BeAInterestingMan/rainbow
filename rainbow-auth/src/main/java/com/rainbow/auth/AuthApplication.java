package com.rainbow.auth;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @Description 权限微服务启动类
 *  @author liuhu
 *  @Date 2020/5/7 14:23
 */
@SpringBootApplication
@EnableRainbowResourceServer
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
