package com.rainbow.generator.controller;

import com.rainbow.generator.entity.GeneratorConfig;
import com.rainbow.generator.service.GeneratorConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @Description 配置类接口
 *  @author liuhu
 *  @Date 2020/6/9 13:21
 */
@RestController
@RequestMapping("generatorConfig")
@RequiredArgsConstructor
@Api(tags = "代码生成配置")
public class GeneratorConfigController {

    private final GeneratorConfigService configService;

    @PutMapping
    @ApiOperation("修改配置")
    public ResponseEntity update(@RequestBody GeneratorConfig generatorConfig){
        return ResponseEntity.ok(configService.updateGeneratorConfig(generatorConfig));
    }

    @GetMapping
    @ApiOperation("查询配置")
    public ResponseEntity selectList(){
        return ResponseEntity.ok(configService.findGeneratorConfig());
    }
}
