package com.rainbow.bus.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  @Description 消息投递启动器
 *  @author liuhu
 *  @Date 2020/5/22 17:43
 */
@EnableFeignClients
@SpringBootApplication
@EnableRainbowResourceServer
@MapperScan("com.rainbow.bus.service.mapper")
@EnableScheduling
public class BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class,args);
    }
}
