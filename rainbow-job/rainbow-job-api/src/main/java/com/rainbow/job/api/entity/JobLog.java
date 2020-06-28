package com.rainbow.job.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
*  @Description jobLog 实体
*  @author liuhu
*  @Date 2020-6-28 15:02:16
*/
@Data
@ApiModel("jobLog")
@TableName("t_job_log")
public class JobLog {



    /** 任务日志id*/
        @ApiModelProperty(name = "LogId",value = "任务日志id")
        @TableId(value = "LOG_ID", type = IdType.AUTO)
         private Long logId;

        /** 任务id*/
        @ApiModelProperty(name = "JobId",value = "任务id")
        @TableField("JOB_ID")
         private Long jobId;

        /** spring bean名称*/
        @ApiModelProperty(name = "BeanName",value = "spring bean名称")
        @TableField("BEAN_NAME")
         private String beanName;

        /** 方法名*/
        @ApiModelProperty(name = "MethodName",value = "方法名")
        @TableField("METHOD_NAME")
         private String methodName;

        /** 参数*/
        @ApiModelProperty(name = "Params",value = "参数")
        @TableField("PARAMS")
         private String params;

        /** 任务状态    0：成功    1：失败*/
        @ApiModelProperty(name = "Status",value = "任务状态    0：成功    1：失败")
        @TableField("STATUS")
         private String status;

        /** 失败信息*/
        @ApiModelProperty(name = "Error",value = "失败信息")
        @TableField("ERROR")
         private String error;

        /** 耗时(单位：毫秒)*/
        @ApiModelProperty(name = "Times",value = "耗时(单位：毫秒)")
        @TableField("TIMES")
         private Long times;

        /** 创建时间*/
        @ApiModelProperty(name = "CreateTime",value = "创建时间")
        @TableField("CREATE_TIME")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
         private Date createTime;

        /** 任务名称*/
        @ApiModelProperty(name = "JobName",value = "任务名称")
        @TableField("JOB_NAME")
         private String jobName;
}