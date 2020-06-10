package com.rainbow.server.system.api.feign;

import com.rainbow.server.system.api.dto.ArticleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "rainbow-search")
@RequestMapping("article")
public interface ArticleFeign {

    @GetMapping("page")
    ResponseEntity page(@RequestBody ArticleRequest articleRequest);
}
