package com.rainbow.gateway.controller;

import com.rainbow.common.core.entity.RainbowResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
/**
 *  @Description 网关熔断 rollback
 *  @author liuhu
 *  @Date 2020/5/19 14:43
 */
@RestController
public class FallbackController {

    @RequestMapping("fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<RainbowResponse> systemFallback(@PathVariable String name) {
        String response = String.format("访问%s超时或者服务不可用", name);
        return Mono.just(new RainbowResponse().message(response));
    }

}