package com.rainbow.common.core.entity.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description 日志 实体
 *  @author liuhu
 *  @Date 2020/5/29 17:19
 */
@AllArgsConstructor
@ApiModel("日志")
@NoArgsConstructor
@Builder
@Data
@TableName("t_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty(name = "id",value = "id")
    private Long id;

    /**
     * 操作用户
     */
    @TableField("USERNAME")
    @ApiModelProperty(name = "username",value = "操作用户")
    private String username;

    /**
     * 操作内容
     */
    @TableField("OPERATION")
    @ApiModelProperty(name = "operation",value = "操作内容")
    private String operation;

    /**
     * 耗时
     */
    @TableField("TIME")
    @ApiModelProperty(name = "time",value = "耗时")
    private Long time;

    /**
     * 操作方法
     */
    @TableField("METHOD")
    @ApiModelProperty(name = "method",value = "操作方法")
    private String method;

    /**
     * 方法参数
     */
    @TableField("PARAMS")
    @ApiModelProperty(name = "方法参数",value = "params")
    private String params;

    /**
     * 操作者IP
     */
    @TableField("IP")
    private String ip;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 操作地点
     */
    @TableField("LOCATION")
    private String location;

    @TableField(exist = false)
    private  String createTimeFrom;

    @TableField(exist = false)
    private  String createTimeTo;
}
