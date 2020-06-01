package com.rainbow.log.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 *  @Description 日志服务启动器
 *  @author liuhu
 *  @Date 2020/5/29 15:35
 */
@EnableRainbowResourceServer
@SpringBootApplication
@EnableFeignClients
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class);
    }
}
