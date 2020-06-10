package com.rainbow.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "自定义生成配置")
public class GeneratorConfig {


    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "id")
    private Integer id;

    /**
     * 作者
     */
    @TableField("author")
    @ApiModelProperty(name = "author",value = "作者")
    private String author;

    /**
     * 基础包名
     */
    @TableField("base_package")
    @ApiModelProperty(name = "basePackage",value = "基础包名")
    private String basePackage;

    /**
     * entity文件存放路径
     */
    @TableField("entity_package")
    @ApiModelProperty(name = "entityPackage",value = "entity文件存放路径")
    private String entityPackage;

    /**
     * mapper文件存放路径
     */
    @TableField("mapper_package")
    @ApiModelProperty(name = "mapperPackage",value = "mapper文件存放路径")
    private String mapperPackage;

    /**
     * mapper xml文件存放路径
     */
    @TableField("mapper_xml_package")
    @ApiModelProperty(name = "mapperXmlPackage",value = "mapper xml文件存放路径")
    private String mapperXmlPackage;

    /**
     * servcie文件存放路径
     */
    @TableField("service_package")
    @ApiModelProperty(name = "servicePackage",value = "servcie文件存放路径")
    private String servicePackage;

    /**
     * serviceImpl文件存放路径
     */
    @TableField("service_impl_package")
    @ApiModelProperty(name = "serviceImplPackage",value = "serviceImpl文件存放路径")
    private String serviceImplPackage;

    /**
     * controller文件存放路径
     */
    @TableField("controller_package")
    @ApiModelProperty(name = "controllerPackage",value = " controller文件存放路径")
    private String controllerPackage;

    /**
     * swagger文件存放路径
     */
    @TableField("swagger_package")
    @ApiModelProperty(name = "swaggerPackage",value = " swagger文件存放路径")
    private String swaggerPackage;

    /**
     * 是否去除前缀
     */
    @TableField("is_trim")
    @ApiModelProperty(name = "isTrim",value = " 是否去除前缀")
    private String isTrim;

    /**
     * 前缀内容
     */
    @TableField("trim_value")
    @ApiModelProperty(name = "trimValue",value = " 前缀内容")
    private String trimValue;

    /**
     * java文件路径，固定值
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "javaPath",value = " java文件路径，固定值")
    private  String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "resourcesPath",value = "配置文件存放路径，固定值")
    private  String resourcesPath = "src/main/resources";

    /**
     * 表名
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "tableName",value = "表名")
    private  String tableName;

    /**
     * 表注释
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "tableComment",value = "表注释")
    private  String tableComment;

    /**
     * 数据库名称
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "databaseName",value = "数据库名称")
    private  String databaseName;

    /**
     * 数据表对应的类名
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "className",value = "数据表对应的类名")
    private  String className;

}