package com.rainbow.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *  @Description 代码生成器启动类
 *  @author liuhu
 *  @Date 2020/6/8 17:54
 */
@SpringBootApplication
@MapperScan("com.rainbow.generator.mapper")
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class,args);
    }
}
