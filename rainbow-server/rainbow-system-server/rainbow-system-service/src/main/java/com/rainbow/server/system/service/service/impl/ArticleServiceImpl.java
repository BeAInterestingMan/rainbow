package com.rainbow.server.system.service.service.impl;


import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.entity.TableData;
import com.rainbow.server.system.api.dto.ArticleRequest;
import com.rainbow.server.system.api.entity.Article;
import com.rainbow.server.system.api.feign.ArticleFeign;
import com.rainbow.server.system.service.exception.SystemException;
import com.rainbow.server.system.service.mapper.ArticleMapper;
import com.rainbow.server.system.service.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*  @Description article 业务层实现类
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    private final ArticleFeign articleFeign;

    @Override
    public Article addArticle(Article article) {
        try {
            articleMapper.insert(article);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("新增文章失败");
        }
        return article;
    }

    @Override
    public Article getById(Integer id) {
        Article article = null;
        try {
            article = articleMapper.selectById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("根据ID查询失败");
        }
        return article;
    }

    @Override
    public TableData page(QueryRequest queryRequest, Article article) {
        TableData tableData = null;
        try {
            ArticleRequest articleRequest = new ArticleRequest();
            BeanUtils.copyProperties(article,articleRequest);
            articleRequest.setCurrent(queryRequest.getPageNum());
            articleRequest.setSize(queryRequest.getPageSize());
             tableData = (TableData)articleFeign.page(articleRequest).getBody();
        }catch (Exception e){
                 e.printStackTrace();
                 throw new SystemException("查询分页失败");
        }
        return tableData;
    }


}
