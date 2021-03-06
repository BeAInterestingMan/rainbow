package com.rainbow.server.system.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *  @Description 系统微服务
 *  @author liuhu
 *  @Date 2020/5/13 13:09
 */
@EnableFeignClients("com.rainbow.server.system.api.feign")
@SpringBootApplication
@EnableRainbowResourceServer
@EnableAsync
@MapperScan("com.rainbow.server.system.service.mapper")
public class SystemServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApplication.class,args);
    }
}
