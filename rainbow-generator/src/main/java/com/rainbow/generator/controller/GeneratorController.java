package com.rainbow.generator.controller;

import com.rainbow.generator.entity.GeneratorConfig;
import com.rainbow.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 *  @Description 代码生成器控制层
 *  @author liuhu
 *  @Date 2020/6/8 16:29
 */
@RequestMapping("generator")
@RestController
@RequiredArgsConstructor
@Api(tags = "代码生成")
public class GeneratorController {

    private final GeneratorService generatorService;

    /**
     * @Description
     * @author liuhu
     * @createTime 2020-06-09 11:52:31
     * @param generatorConfig
     * @param response
     * @return void
     */
    @GetMapping
    @ApiOperation("代码生成")
    public void generate(GeneratorConfig generatorConfig, HttpServletResponse response) throws Exception {
        generatorService.generate(generatorConfig,response);
    }


}
