package com.rainbow.job.api.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
*  @Date 2020-6-23 17:05:58
*/
@Data
@ApiModel("job")
@TableName("t_job")
public class Job {


        /** 任务id*/
        @ApiModelProperty(name = "jobId",value = "任务id")
        @TableId(value = "JOB_ID", type = IdType.AUTO)
         private Long jobId;

        /** spring bean名称*/
        @ApiModelProperty(name = "jobName",value = "spring bean名称")
        @TableField("JOB_NAME")
        private String jobName;

        /** spring bean名称*/
        @ApiModelProperty(name = "beanName",value = "spring bean名称")
        @TableField("BEAN_NAME")
         private String beanName;

        /** 方法名*/
        @ApiModelProperty(name = "methodName",value = "方法名")
        @TableField("METHOD_NAME")
         private String methodName;

        /** 参数*/
        @ApiModelProperty(name = "params",value = "参数")
        @TableField("PARAMS")
         private String params;

        /** cron表达式*/
        @ApiModelProperty(name = "cronExpression",value = "cron表达式")
        @TableField("CRON_EXPRESSION")
         private String cronExpression;

        /** 任务状态  0：正常  1：暂停*/
        @ApiModelProperty(name = "status",value = "任务状态  0：正常  1：暂停")
        @TableField("STATUS")
         private String status;

        /** 备注*/
        @ApiModelProperty(name = "remark",value = "备注")
        @TableField("REMARK")
         private String remark;

        /** 创建时间*/
        @ApiModelProperty(name = "createTime",value = "创建时间")
        @TableField("CREATE_TIME")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
         private Date createTime;
}