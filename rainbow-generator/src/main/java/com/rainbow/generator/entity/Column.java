package com.rainbow.generator.entity;

import lombok.Data;

/**
 *  @Description 列信息
 *  @author liuhu
 *  @Date 2020/6/8 17:56
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
}
