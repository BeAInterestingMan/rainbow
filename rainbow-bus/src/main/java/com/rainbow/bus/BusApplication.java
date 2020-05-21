package com.rainbow.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 消息微服务启动类
 *  @author liuhu
 *  @Date 2020/5/21 17:57
 */
@EnableFeignClients
@SpringBootApplication
public class BusApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class);
    }
}
