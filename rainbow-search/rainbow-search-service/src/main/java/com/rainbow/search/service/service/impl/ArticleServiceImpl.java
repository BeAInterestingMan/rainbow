package com.rainbow.search.service.service.impl;


import com.rainbow.common.core.entity.TableData;
import com.rainbow.search.api.dto.ArticleDto;
import com.rainbow.search.api.dto.ArticleRequest;
import com.rainbow.search.service.exception.ElasticSearchException;
import com.rainbow.search.service.service.ArticleEsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
*  @Description article 业务层实现类
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl  implements ArticleEsService {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public TableData page(ArticleRequest articleRequest) {
       try {
           // 组装查询条件
           NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
           // 布尔查询 matchPhraseQuery是短语匹配
           BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

           if(StringUtils.isNotBlank(articleRequest.getAuthor())){
               //must 是必须匹配，可以多个相连接相当于sql中的and
               queryBuilder.must(QueryBuilders.matchPhraseQuery("author",articleRequest.getAuthor()));
           }
           if(null != articleRequest.getCategory()){
               queryBuilder.must(QueryBuilders.matchPhraseQuery("category",articleRequest.getCategory()));
           }
           if(null != articleRequest.getIsAudit()){
               queryBuilder.must(QueryBuilders.matchPhraseQuery("isAudit",articleRequest.getIsAudit()));
           }
           if(null != articleRequest.getIsDelete()){
               queryBuilder.must(QueryBuilders.matchPhraseQuery("isDelete",articleRequest.getIsDelete()));
           }
           // queryStringQuery 使用分词搜素
           if(StringUtils.isNotBlank(articleRequest.getKeyword())){
               queryBuilder.must(QueryBuilders.queryStringQuery(articleRequest.getKeyword()));
           }
           if(null != articleRequest.getStartTime() && null != articleRequest.getEndTime()){
               // rangeQuery 范围查询
               queryBuilder.must(QueryBuilders.rangeQuery("createTime").gt(articleRequest.getStartTime()).lt(articleRequest.getEndTime()));
           }
           // 排序查询
           FieldSortBuilder fsb = SortBuilders.fieldSort("createTime").order(SortOrder.ASC);
           // 分页条件
           PageRequest pageRequest = PageRequest.of(articleRequest.getCurrent()-1, articleRequest.getSize());
           NativeSearchQuery build = searchQueryBuilder.withQuery(queryBuilder)
                   .withSort(fsb)
                   .withPageable(pageRequest).build();
           AggregatedPage<ArticleDto> articles = elasticsearchTemplate.queryForPage(build, ArticleDto.class);
           long total = articles.getTotalElements();
           List<ArticleDto> content = articles.getContent();
           return    TableData.builder().total(total).rows(content).build();
       }catch (Exception e){
           e.printStackTrace();
           throw new ElasticSearchException("es查询文章失败");
       }
    }


}
