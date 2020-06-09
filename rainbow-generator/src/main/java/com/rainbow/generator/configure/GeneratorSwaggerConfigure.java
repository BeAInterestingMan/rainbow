package com.rainbow.generator.configure;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  @Description Swagger 配置类
 *  @author liuhu
 *  @Date 2020/5/20 14:06
 */
@Configuration
@EnableSwagger2
public class GeneratorSwaggerConfigure {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rainbow.generator.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("rainbow接口文档")
                        .description("逆向工程接口文档")
                        .version("1.0")
                        .contact(new Contact("rainbow","https://github.com/BeAInterestingMan/rainbow","1649471814@qq.com"))
                        .license("The Apache License")
                        .licenseUrl("https://github.com/BeAInterestingMan/rainbow")
                        .build());
    }
}
