package com.example.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();
        pars.add(new ParameterBuilder().name("version").description("版本")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).defaultValue("3.5.4").hidden(true).build());

        pars.add(new ParameterBuilder().name("platform")
                .description("平台：1安卓 2：ios")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).defaultValue("2").hidden(true).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.business.controller"))
                .paths(PathSelectors.any())
                .build()
                //.ignoredParameterTypes(User.class)
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("APP_API v3 接口文档")
                .description("更多请关注http://www.baidu.com")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("alevi").version("3.0").build();
    }
}
