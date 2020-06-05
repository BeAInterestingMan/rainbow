package com.rainbow.upload;

import com.rainbow.common.security.annotation.EnableRainbowResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  @Description 文书上传服务启动器
 *  @author liuhu
 *  @Date 2020/6/4 15:46
 */
@SpringBootApplication
@EnableFeignClients
@EnableRainbowResourceServer
@MapperScan("com.rainbow.upload.mapper")
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class,args);
    }
}
