package com.rainbow.generator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *  @Description 自动生成手动配置类
 *  @author liuhu
 *  @Date 2020/6/9 11:52
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rainbow.generator")
@PropertySource(value = "classpath:Generator.properties",encoding = "UTF-8")
public class GeneratorProperties {
    private String author;

    /**
     * 基础包名
     */
    private String basePackage;

    /**
     * entity文件存放路径
     */
    private String entityPackage;

    /**
     * mapper文件存放路径
     */
    private String mapperPackage;

    /**
     * mapper xml文件存放路径
     */
    private String mapperXmlPackage;

    /**
     * servcie文件存放路径
     */
    private String servicePackage;

    /**
     * serviceImpl文件存放路径
     */
    private String serviceImplPackage;

    /**
     * controller文件存放路径
     */
    private String controllerPackage;

    /**
     * 是否去除前缀
     */
    private String isTrim;

    /**
     * 前缀内容
     */
    private String trimValue;

    /**
     * java文件路径，固定值
     */
    private  String javaPath;
    /**
     * 配置文件存放路径，固定值
     */
    private  String resourcesPath;

    /**
     * 数据库名称
     */
    private  String databaseName;

    /**
     * 表名称
     */
    private  String tableName;

    /**
     * 表注释
     */
    private  String tableComment;

    /**
     * swagger文件存放路径
     */
    private String swaggerPackage;

}
