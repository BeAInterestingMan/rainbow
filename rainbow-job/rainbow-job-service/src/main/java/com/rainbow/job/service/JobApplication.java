package com.rainbow.job.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rainbow.job.service.mapper")
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class,args);
    }
}
