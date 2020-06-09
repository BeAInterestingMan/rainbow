package com.rainbow.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *  @Description 代码生成器配置
 *  @author liuhu
 *  @Date 2020/6/8 17:05
 */
@Data
@TableName("t_generator_config")
public class GeneratorConfig {


    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 基础包名
     */
    @TableField("base_package")
    private String basePackage;

    /**
     * entity文件存放路径
     */
    @TableField("entity_package")
    private String entityPackage;

    /**
     * mapper文件存放路径
     */
    @TableField("mapper_package")
    private String mapperPackage;

    /**
     * mapper xml文件存放路径
     */
    @TableField("mapper_xml_package")
    private String mapperXmlPackage;

    /**
     * servcie文件存放路径
     */
    @TableField("service_package")
    @NotBlank(message = "{required}")
    private String servicePackage;

    /**
     * serviceImpl文件存放路径
     */
    @TableField("service_impl_package")
    private String serviceImplPackage;

    /**
     * controller文件存放路径
     */
    @TableField("controller_package")
    private String controllerPackage;

    /**
     * swagger文件存放路径
     */
    @TableField("swagger_package")
    private String swaggerPackage;

    /**
     * 是否去除前缀
     */
    @TableField("is_trim")
    private String isTrim;

    /**
     * 前缀内容
     */
    @TableField("trim_value")
    private String trimValue;

    /**
     * java文件路径，固定值
     */
    @TableField(exist = false)
    private  String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    @TableField(exist = false)
    private  String resourcesPath = "src/main/resources";

    /**
     * 表名
     */
    @TableField(exist = false)
    private  String tableName;

    /**
     * 表注释
     */
    @TableField(exist = false)
    private  String tableComment;

    /**
     * 数据库名称
     */
    @TableField(exist = false)
    private  String databaseName;

    /**
     * 数据表对应的类名
     */
    @TableField(exist = false)
    private  String className;

}