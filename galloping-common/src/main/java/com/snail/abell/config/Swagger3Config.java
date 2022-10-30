package com.snail.abell.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

/**
 * @author Abell
 * @date 2022/7/16
 */
@Configuration
public class Swagger3Config {

    @Value("${swagger.enabled}")
    private Boolean enabled;

    /**
     * 自定义一个Apikey
     * 这是一个包含在header中键名为Authorization的JWT标识
     */
    private ApiKey apiKey() {
        return new ApiKey("accessToken", "accessToken", "header");
    }

    /**
     * 配置JWT的SecurityContext 并设置全局生效
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("accessToken", authorizationScopes));
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enabled)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Swagger3接口文档")
                    .description("GallopingSnail-接口文档")
                    .contact(new Contact("GallopingSnail", "http://localhost:8111/swagger-ui/index.html", "1692178653@qq.com"))
                    .version("1.0")
                    .build();
        }

    }
