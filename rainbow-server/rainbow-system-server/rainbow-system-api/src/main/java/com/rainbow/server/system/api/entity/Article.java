package com.rainbow.server.system.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rainbow.common.core.entity.QueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;



/**
*  @Description article 实体
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@Data
@ApiModel("文章实体")
@TableName("t_article")
public class Article {


        /** id */
         @ApiModelProperty(name = "id",value = "")
         @TableId(value = "id", type = IdType.AUTO)
         private Integer id;

        /** 文章标题*/
        @ApiModelProperty(name = "title",value = "文章标题")
        @TableField("title")
         private String title;

        /** 分类*/
        @ApiModelProperty(name = "category",value = "分类")
        @TableField("category")
         private String category;

        /** 作者*/
        @ApiModelProperty(name = "author",value = "作者")
        @TableField("author")
         private String author;

        /** 文章内容*/
        @ApiModelProperty(name = "content",value = "文章内容")
        @TableField("content")
        private String content;

        /** 图片地址*/
        @ApiModelProperty(name = "image_path",value = "图片地址")
        @TableField("image_path")
         private String imagePath;

        /** 创建时间*/
        @ApiModelProperty(name = "create_time",value = "创建时间")
        @TableField("create_time")
         private Date createTime;

        /** 创建人Id*/
        @ApiModelProperty(name = "create_id",value = "创建人Id")
        @TableField("create_id")
         private Integer createId;

        /** */
        @ApiModelProperty(name = "create_name",value = "")
        @TableField("create_name")
         private String createName;

        /** */
        @ApiModelProperty(name = "update_time",value = "")
        @TableField("update_time")
         private Date updateTime;

        /** 更新人ID*/
        @ApiModelProperty(name = "update_id",value = "更新人ID")
        @TableField("update_id")
         private Integer updateId;

        /** 更新人*/
        @ApiModelProperty(name = "update_name",value = "更新人")
        @TableField("update_name")
         private String updateName;

        /** 是否审核*/
        @ApiModelProperty(name = "is_audit",value = "是否审核")
        @TableField("is_audit")
         private Integer isAudit;

        /** */
        @ApiModelProperty(name = "is_delete",value = "")
        @TableField("is_delete")
         private String isDelete;

        /** 审核时间*/
        @ApiModelProperty(name = "audit_time",value = "审核时间")
        @TableField("audit_time")
         private Date auditTime;

        /** 审核人ID*/
        @ApiModelProperty(name = "audit_id",value = "审核人ID")
        @TableField("audit_id")
         private Integer auditId;

        @TableField(exist = false)
        private QueryRequest queryRequest;

        @TableField(exist = false)
        private String keyword;
}