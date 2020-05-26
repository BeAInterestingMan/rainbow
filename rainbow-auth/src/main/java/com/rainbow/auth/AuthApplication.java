package com.rainbow.auth;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 权限微服务启动类
 *  @author liuhu
 *  @Date 2020/5/7 14:23
 */
@EnableFeignClients("com.rainbow.bus.api.feign")
@SpringBootApplication
@EnableRainbowResourceServer
@MapperScan("com.rainbow.auth.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
