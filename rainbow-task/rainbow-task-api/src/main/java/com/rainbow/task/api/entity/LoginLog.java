package com.rainbow.task.api.entity;

import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*  @Description job 实体
*  @author liuhu
*  @Date 2020-6-23 17:08:09
*/
@Data
@ApiModel("job")
@TableName("t_login_log")
public class LoginLog {


        /** id*/
        @ApiModelProperty(name = "ID",value = "id")
        @TableId(value = "ID", type = IdType.AUTO)
         private Long id;

        /** 用户名*/
        @ApiModelProperty(name = "USERNAME",value = "用户名")
        @TableField("USERNAME")
         private String username;

        /** 登录时间*/
        @ApiModelProperty(name = "LOGIN_TIME",value = "登录时间")
        @TableField("LOGIN_TIME")
         private Date loginTime;

        /** 登录地点*/
        @ApiModelProperty(name = "LOCATION",value = "登录地点")
        @TableField("LOCATION")
         private String location;

        /** IP地址*/
        @ApiModelProperty(name = "IP",value = "IP地址")
        @TableField("IP")
         private String ip;

        /** 操作系统*/
        @ApiModelProperty(name = "SYSTEM",value = "操作系统")
        @TableField("SYSTEM")
         private String system;

        /** 浏览器*/
        @ApiModelProperty(name = "BROWSER",value = "浏览器")
        @TableField("BROWSER")
         private String browser;
}