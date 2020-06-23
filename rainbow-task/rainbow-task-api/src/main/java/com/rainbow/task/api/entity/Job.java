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
*  @Date 2020-6-23 17:05:58
*/
@Data
@ApiModel("job")
@TableName("t_job")
public class Job {


        /** 任务id*/
        @ApiModelProperty(name = "JOB_ID",value = "任务id")
        @TableId(value = "JOB_ID", type = IdType.AUTO)
         private Long jobId;

        /** spring bean名称*/
        @ApiModelProperty(name = "BEAN_NAME",value = "spring bean名称")
        @TableField("BEAN_NAME")
         private String beanName;

        /** 方法名*/
        @ApiModelProperty(name = "METHOD_NAME",value = "方法名")
        @TableField("METHOD_NAME")
         private String methodName;

        /** 参数*/
        @ApiModelProperty(name = "PARAMS",value = "参数")
        @TableField("PARAMS")
         private String params;

        /** cron表达式*/
        @ApiModelProperty(name = "CRON_EXPRESSION",value = "cron表达式")
        @TableField("CRON_EXPRESSION")
         private String cronExpression;

        /** 任务状态  0：正常  1：暂停*/
        @ApiModelProperty(name = "STATUS",value = "任务状态  0：正常  1：暂停")
        @TableField("STATUS")
         private String status;

        /** 备注*/
        @ApiModelProperty(name = "REMARK",value = "备注")
        @TableField("REMARK")
         private String remark;

        /** 创建时间*/
        @ApiModelProperty(name = "CREATE_TIME",value = "创建时间")
        @TableField("CREATE_TIME")
         private Date createTime;
}