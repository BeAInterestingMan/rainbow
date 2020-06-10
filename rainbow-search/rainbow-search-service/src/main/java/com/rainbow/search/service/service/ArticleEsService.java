package com.rainbow.search.service.service;


import com.rainbow.common.core.entity.TableData;
import com.rainbow.search.api.dto.ArticleDto;
import com.rainbow.search.api.dto.ArticleRequest;

/**
*  @Description articleEs 业务层接口
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
public interface ArticleEsService {

    /**
     * @Description Es 分页查询文章
     * @author liuhu
     * @createTime 2020-06-10 17:38:53
     * @param articleRequest
     * @return com.rainbow.common.core.entity.TableData
     */
    TableData page(ArticleRequest articleRequest);
}
