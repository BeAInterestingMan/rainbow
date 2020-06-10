package com.rainbow.server.system.service.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.server.system.api.entity.Article;
import org.springframework.stereotype.Repository;

/**
*  @Description article 持久层接口
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
