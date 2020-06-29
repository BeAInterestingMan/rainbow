package com.rainbow.job.service;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRainbowResourceServer
@MapperScan("com.rainbow.job.service.mapper")
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class,args);
    }
}
