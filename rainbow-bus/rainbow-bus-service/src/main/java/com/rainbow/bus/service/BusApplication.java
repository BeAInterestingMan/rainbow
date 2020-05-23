package com.rainbow.bus.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *  @Description 消息投递启动器
 *  @author liuhu
 *  @Date 2020/5/22 17:43
 */
@SpringBootApplication
@MapperScan("com.rainbow.bus.service.mapper")
public class BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class,args);
    }
}
