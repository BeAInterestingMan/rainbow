package com.rainbow.generator.helper;


import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.rainbow.generator.constant.GeneratorConstant;
import com.rainbow.generator.entity.Column;
import com.rainbow.generator.entity.GeneratorConfig;
import com.rainbow.generator.util.GeneratorUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.Objects;

/**
 *  @Description 代码生成器工具类
 *  @author liuhu
 *  @Date 2020/6/9 12:01
 */
@Slf4j
@Component
public class GeneratorHelper {

    /**
     * @Description 获得生成文件路径
     * @author liuhu
     * @createTime 2020-06-09 11:57:40
     * @param configure 配置类
     * @param packagePath 基本路径
     * @param suffix 后缀
     * @param xml 是否是xml
     * @param swagger 是否swagger
     * @return java.lang.String
     */
    private static String getFilePath(GeneratorConfig configure, String packagePath, String suffix,boolean xml,boolean swagger) {
        String filePath = "";
        if(xml){
            filePath = GeneratorConstant.TEMP_PATH + configure.getResourcesPath() +
                    packageConvertPath(configure.getBasePackage() + "." + packagePath);
        }else {
            filePath = GeneratorConstant.TEMP_PATH + configure.getJavaPath() +
                    packageConvertPath(configure.getBasePackage() + "." + packagePath);
        }
        if(swagger){
            filePath += suffix;
        }else {
            filePath += configure.getClassName() + suffix;
        }
        return filePath;
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public void generateEntityFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.JAVA_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getEntityPackage(), suffix, false,false);
        String templateName = GeneratorConstant.ENTITY_TEMPLATE;
        File entityFile = new File(path);
        JSONObject data = toJsonObject(configure);
        data.put("hasDate", false);
        data.put("hasBigDecimal", false);
        // 属性转换
        columns.forEach(c -> {
            c.setField(GeneratorUtil.underscoreToCamel(StringUtils.lowerCase(c.getName())));
            if (StringUtils.containsAny(c.getType(), GeneratorConstant.DATE, GeneratorConstant.DATETIME, GeneratorConstant.TIMESTAMP)) {
                data.put("hasDate", true);
            }
            if (StringUtils.containsAny(c.getType(), GeneratorConstant.DECIMAL, GeneratorConstant.NUMERIC)) {
                data.put("hasBigDecimal", true);
            }
        });
        data.put("columns", columns);
        this.generateFileByTemplate(templateName, entityFile, data);
    }

    public void generateMapperFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.MAPPER_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getMapperPackage(), suffix, false,false);
        String templateName = GeneratorConstant.MAPPER_TEMPLATE;
        File mapperFile = new File(path);
        generateFileByTemplate(templateName, mapperFile, toJsonObject(configure));
    }

    public void generateServiceFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.SERVICE_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getServicePackage(), suffix, false,false);
        String templateName = GeneratorConstant.SERVICE_TEMPLATE;
        File serviceFile = new File(path);
        generateFileByTemplate(templateName, serviceFile, toJsonObject(configure));
    }

    public void generateServiceImplFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.SERVICEIMPL_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getServiceImplPackage(), suffix, false,false);
        String templateName = GeneratorConstant.SERVICEIMPL_TEMPLATE;
        File serviceImplFile = new File(path);
        generateFileByTemplate(templateName, serviceImplFile, toJsonObject(configure));
    }

    public void generateControllerFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.CONTROLLER_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getControllerPackage(), suffix, false,false);
        String templateName = GeneratorConstant.CONTROLLER_TEMPLATE;
        File controllerFile = new File(path);
        generateFileByTemplate(templateName, controllerFile, toJsonObject(configure));
    }

    public void generateMapperXmlFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.MAPPERXML_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getMapperXmlPackage(), suffix, true,false);
        String templateName = GeneratorConstant.MAPPERXML_TEMPLATE;
        File mapperXmlFile = new File(path);
        JSONObject data = toJsonObject(configure);
        columns.forEach(c -> c.setField(GeneratorUtil.underscoreToCamel(StringUtils.lowerCase(c.getName()))));
        data.put("columns", columns);
        generateFileByTemplate(templateName, mapperXmlFile, data);
    }

    /**
     * @Description 根据模板生成文件
     * @author liuhu
     * @createTime 2020-06-09 12:00:10
     * @param templateName 模板名称
     * @param file 文件
     * @param data 数据
     * @return void
     */
    @SuppressWarnings("all")
    private void generateFileByTemplate(String templateName, File file, Object data) throws Exception {
        Template template = getTemplate(templateName);
        Files.createParentDirs(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8), 10240)) {
            template.process(data, out);
        } catch (Exception e) {
            String message = "代码生成异常";
            log.error(message, e);
            throw new Exception(message);
        }
    }

    /**
     * @Description 转json对象
     * @author liuhu
     * @createTime 2020-06-09 12:00:55
     * @param o
     * @return com.alibaba.fastjson.JSONObject
     */
    private JSONObject toJsonObject(Object o) {
        return JSONObject.parseObject(JSONObject.toJSON(o).toString());
    }

    /**
     * @Description 获得freemarker模板
     * @author liuhu
     * @createTime 2020-06-09 12:01:08
     * @param templateName
     * @return freemarker.template.Template
     */
    private Template getTemplate(String templateName) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        String templatePath = GeneratorHelper.class.getResource("/generator/templates/").getPath();
        File file = new File(templatePath);
        if (!file.exists()) {
            templatePath = System.getProperties().getProperty(GeneratorConstant.JAVA_TEMP_DIR);
            file = new File(templatePath + File.separator + templateName);
            FileUtils.copyInputStreamToFile(Objects.requireNonNull(GeneratorHelper.class.getClassLoader().getResourceAsStream("classpath:generator/templates/" + templateName)), file);
        }
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding(GeneratorConstant.UTF8);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return configuration.getTemplate(templateName);

    }

    public void generateSwaggerFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        String suffix = GeneratorConstant.SWAGGER_FILE_SUFFIX;
        String path = getFilePath(configure, configure.getSwaggerPackage(), suffix, false,true);
        String templateName = GeneratorConstant.SWAGGER_TEMPLATE;
        File swaggerFile = new File(path);
        generateFileByTemplate(templateName, swaggerFile, toJsonObject(configure));
    }
}
