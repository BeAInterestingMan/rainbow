<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${mapperPackage}.${className}Mapper">
    <!-- 基础返回结果集 -->
    <resultMap id="${className}Map" type="${basePackage}.${entityPackage}.${className}">
        <#if columns??>
        <#list columns as column>
         <#if column.isKey = true>
              <id property="${column.field}" column="${column.name}"/>
         <#else>
             <result property="${column.field}" column="${column.name}"/>
         </#if>
        </#list>
        </#if>
    </resultMap>


    <!-- 基础列 -->
    <sql id="baseColumn">
        <#if columns??>
            <#list columns as column>
                <#if column_has_next>
 ${column.name},<#else>${column.name}
                </#if>
            </#list>
        </#if>
    </sql>
</mapper>
