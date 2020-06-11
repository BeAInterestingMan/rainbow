package com.rainbow.server.system.service.controller;



import com.rainbow.common.core.entity.QueryRequest;
import com.rainbow.common.core.utils.RainbowUtil;
import com.rainbow.server.system.api.entity.Article;
import com.rainbow.server.system.api.feign.ArticleFeign;
import com.rainbow.server.system.service.service.ArticleService;
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
public class ArticleController {

    private final ArticleService articleService;

    private final ArticleFeign articleFeign;

    /**
     * @Description 新增文章
     * @author liuhu
     * @createTime 2020-06-10 17:28:43
     * @param article
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping
    @ApiOperation("新增文章")
    public ResponseEntity add(@RequestBody Article article){
       return ResponseEntity.ok(articleService.addArticle(article));
    }

    /**
     * @Description 新增文章
     * @author liuhu
     * @createTime 2020-06-10 17:28:43
     * @param id
     * @return org.springframework.http.ResponseEntity
     */
    @GetMapping("{id}")
    @ApiOperation("新增文章")
    public ResponseEntity add(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleService.getById(id));
    }

    /**
     * @Description 查询文章分页
     * @author liuhu
     * @createTime 2020-06-11 15:46:15
     * @param article
     * @return org.springframework.http.ResponseEntity
     */
    @PostMapping("page")
    @ApiOperation("查询文章分页")
    public ResponseEntity page(@RequestBody  Article article){
        return ResponseEntity.ok(articleService.page(article));
    }
}
