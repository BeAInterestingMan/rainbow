package com.rainbow.generator.constant;
/**
 *  @Description 常量
 *  @author liuhu
 *  @Date 2020/6/9 11:56
 */
public interface GeneratorConstant {
    /**是否有前缀*/
    String TRIM_YES = "1";
    String TRIM_NO = "0";
    /** 驼峰标志*/
    String UNDER_LINE = "_";
    /**数据库类型*/
    String DATABASE_TYPE = "mysql";


    /**
     * 生成代码的临时目录
     */
    String TEMP_PATH = "gen_temp/";

    /**
     * java类型文件后缀
     */
    String JAVA_FILE_SUFFIX = ".java";
    /**
     * mapper文件类型后缀
     */
    String MAPPER_FILE_SUFFIX = "Mapper.java";
    /**
     * service文件类型后缀
     */
    String SERVICE_FILE_SUFFIX = "Service.java";
    /**
     * service impl文件类型后缀
     */
    String SERVICEIMPL_FILE_SUFFIX = "ServiceImpl.java";
    /**
     * controller文件类型后缀
     */
    String CONTROLLER_FILE_SUFFIX = "Controller.java";

    /**
     * swagger文件类型后缀
     */
    String SWAGGER_FILE_SUFFIX = "SwaggerConfigure.java";
    /**
     * mapper xml文件类型后缀
     */
    String MAPPERXML_FILE_SUFFIX = "Mapper.xml";
    /**
     * entity模板
     */
    String ENTITY_TEMPLATE = "entity.ftl";
    /**
     * mapper模板
     */
    String MAPPER_TEMPLATE = "mapper.ftl";
    /**
     * service接口模板
     */
    String SERVICE_TEMPLATE = "service.ftl";
    /**
     * service impl接口模板
     */
    String SERVICEIMPL_TEMPLATE = "serviceImpl.ftl";
    /**
     * controller接口模板
     */
    String CONTROLLER_TEMPLATE = "controller.ftl";

    /**
     * controller接口模板
     */
    String SWAGGER_TEMPLATE = "swagger.ftl";
    /**
     * mapper xml接口模板
     */
    String MAPPERXML_TEMPLATE = "mapperXml.ftl";

    /**
     * Java默认临时目录
     */
    String JAVA_TEMP_DIR = "java.io.tmpdir";
    /**
     * utf-8
     */
    String UTF8 = "utf-8";

     /**打包后缀*/
     String SUFFIX = "_code.zip";

     String DATE = "date";
     String DATETIME = "datetime";
     String TIMESTAMP = "timestamp";
     String DECIMAL = "decimal";
     String NUMERIC = "numeric";
    /**
     * 允许下载的文件类型，根据需求自己添加（小写）
     */
    String[] VALID_FILE_TYPE = {"xlsx", "zip"};
}
