package com.rainbow.server.system.service.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.TableData;
import com.rainbow.server.system.api.entity.Article;

/**
*  @Description article 业务层接口
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
public interface ArticleService {


    /**
     * @Description 新增
     * @author liuhu
     * @createTime 2020-06-10 13:28:33
     * @param article
     * @return com.rainbow.search.api.entity.Article
     */
    Article addArticle(Article article);

    /**
     * @Description 根据Id查询
     * @author liuhu
     * @createTime 2020-06-10 17:29:57
     * @param id
     * @return com.rainbow.server.system.api.entity.Article
     */
    Article getById(Integer id);

    TableData page( Article article);
}
