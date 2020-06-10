package com.rainbow.search.api.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
*  @Description article 实体
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@Data
@Document(indexName = "my_article",type = "article")
public class ArticleDto {



         @Field(type = FieldType.Integer)
         @Id
         private Integer id;

        /** 文章标题*/
         @Field(type = FieldType.Text,analyzer = "ik_smart")
         private String title;

        /** 分类*/
         @Field(type = FieldType.Keyword)
         private Integer category;

         /** 作者*/
         @Field(type = FieldType.Text,analyzer = "ik_smart")
         private String author;

        /** 文章内容*/
         @Field(type = FieldType.Text,analyzer = "ik_smart")
         private String content;

        /** 图片地址*/
         @Field(type = FieldType.Text,analyzer = "ik_smart")
         private String imagePath;

        /** 创建时间*/
         @Field(type = FieldType.Date)
         private Date createTime;

        /** 是否审核*/
         @Field(type = FieldType.Keyword)
         private Integer isAudit;

        /**是否删除 0-未删除  1已删除 */
         @Field(type = FieldType.Keyword)
         private String isDelete;

        /** 审核时间*/
         @Field(type = FieldType.Date)
         private Date auditTime;

         private String keyWord;
}