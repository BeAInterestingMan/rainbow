package com.rainbow.search.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 *  @Description 检索服务启动类
 *  @author liuhu
 *  @Date 2020/6/10 10:04
 */
@SpringBootApplication
@EnableFeignClients
@EnableRainbowResourceServer
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
