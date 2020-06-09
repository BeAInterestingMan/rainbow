package com.rainbow.generator.service.impl;

import com.rainbow.generator.constant.GeneratorConstant;
import com.rainbow.generator.entity.Column;
import com.rainbow.generator.entity.GeneratorConfig;
import com.rainbow.generator.exception.GeneratorException;
import com.rainbow.generator.helper.GeneratorHelper;
import com.rainbow.generator.mapper.GeneratorMapper;
import com.rainbow.generator.properties.GeneratorProperties;
import com.rainbow.generator.service.GeneratorConfigService;
import com.rainbow.generator.service.GeneratorService;
import com.rainbow.generator.util.FileUtil;
import com.rainbow.generator.util.GeneratorUtil;
import lombok.RequiredArgsConstructor;


import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

/**
 *  @Description 代码生成器业务层实现类
 *  @author liuhu
 *  @Date 2020/6/8 17:07
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GeneratorServiceImpl implements GeneratorService {

    private final GeneratorConfigService generatorConfigService;

    private final GeneratorMapper generatorMapper;

    private final GeneratorHelper generatorHelper;

    private final GeneratorProperties generatorProperties;
    @Override
    public void generate(GeneratorConfig generatorConfig, HttpServletResponse response)  throws Exception {
        String tableComment="";
        String databaseName="";
        String tableName="";
        if(StringUtils.isNotBlank(generatorConfig.getTableComment())){
            tableComment = generatorConfig.getTableComment();
        }
        if(StringUtils.isNotBlank(generatorConfig.getDatabaseName())){
            databaseName = generatorConfig.getDatabaseName();
        }
        if(StringUtils.isNotBlank(generatorConfig.getTableName())){
            tableName = generatorConfig.getTableName();
        }
        // 先走配置文件   找不到在查表
        BeanUtils.copyProperties(generatorProperties,generatorConfig);
        if(StringUtils.isBlank(generatorConfig.getTableName())){
            generatorConfig =generatorConfigService.findGeneratorConfig();
            if(null == generatorConfig){
                throw new GeneratorException("配置不能为空");
            }
            generatorConfig.setTableComment(tableComment);
            generatorConfig.setTableName(tableName);
            generatorConfig.setDatabaseName(databaseName);
        }
        // 是否有前缀
        String className = generatorConfig.getTableName();
        if (GeneratorConstant.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(generatorConfig.getTableName(), generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }
        generatorConfig.setTableName(generatorConfig.getTableName());
        generatorConfig.setClassName(GeneratorUtil.underscoreToCamel(className));
        generatorConfig.setTableComment(generatorConfig.getTableComment());
        // 查询出表所属字段信息
        List<Column> columns = generatorMapper.getColumns(GeneratorConstant.DATABASE_TYPE, generatorConfig.getDatabaseName(), generatorConfig.getTableName());
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        generatorHelper.generateSwaggerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + GeneratorConstant.SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, generatorConfig.getTableName() + GeneratorConstant.SUFFIX, true, response);
        // 删除临时目录
        FileUtil.delete(GeneratorConstant.TEMP_PATH);
    }

    /**
     * @Description 解决配置文件中文乱码
     * @author liuhu
     * @createTime 2020-06-09 10:34:20
     * @param
     * @return java.lang.String
     */
    public String translate(String tableComponent){
        InputStream inputStream = this.getClass().getResourceAsStream("/Generator.properties");
        InputStreamResource resource = new InputStreamResource(inputStream);
        // UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");

        ResourcePropertySource rp = null;
        try {
            rp = new ResourcePropertySource(encodedResource);
            String property = (String) rp.getProperty(tableComponent);
            return property;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneratorException("配置文件转换中文失败");
        }

    }
}
