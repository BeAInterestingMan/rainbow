package com.rainbow.server.system.api.dto;

import lombok.Data;

import java.util.Date;

/**
 *  @Description es查询条件
 *  @author liuhu
 *  @Date 2020/6/10 17:50
 */
@Data
public class ArticleRequest {


    /** 文章标题*/
    private String title;

    /** 分类*/
    private Integer category;

    /** 作者*/
    private String author;

    /** 文章内容*/
    private String content;

    /** 开始时间*/
    private Date startTime;

    /** 结束时间*/
    private Date endTime;

    /**是否删除 0-未删除  1已删除 */
    private String isDelete;

    /** 关键词全文检索*/
    private String keyWord;

    /** 是否审核*/
    private Integer isAudit;

    private Integer current;

    private Integer size;
}
