package com.rainbow.search.service.controller;


import com.rainbow.common.core.entity.TableData;
import com.rainbow.search.api.dto.ArticleRequest;
import com.rainbow.search.service.service.ArticleEsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
*  @Description article Controller
*  @author liuhu
*  @Date 2020-6-10 10:58:59
*/
@RestController
@RequestMapping("article")
@RequiredArgsConstructor
@Api(tags = "文章接口")
public class ArticleEsController {

    private final ArticleEsService articleEsService;

    @PostMapping("pageList")
    @ApiOperation("文章操作")
    public TableData page(@RequestBody ArticleRequest articleRequest){
       return articleEsService.page(articleRequest);
    }
}
