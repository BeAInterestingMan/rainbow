package com.rainbow.server.system.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 系统微服务
 *  @author liuhu
 *  @Date 2020/5/13 13:09
 */
@EnableFeignClients
@SpringBootApplication
@EnableRainbowResourceServer
public class SystemServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApplication.class,args);
    }
}
