package com.rainbow.server.system.api.feign;

import com.rainbow.common.core.entity.TableData;
import com.rainbow.server.system.api.dto.ArticleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *  @Description 文章feign
 *  @author liuhu
 *  @Date 2020/6/11 15:54
 */
@FeignClient(name = "rainbow-search")
@RequestMapping("article")
public interface ArticleFeign {

     @PostMapping("pageList")
     TableData page(@RequestBody ArticleRequest articleRequest);

}
