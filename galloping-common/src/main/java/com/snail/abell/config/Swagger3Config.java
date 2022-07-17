package com.snail.abell.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Abell
 * @date 2022/7/16
 */
@Configuration
public class Swagger3Config {

    @Value("${swagger.enabled}")
    private Boolean enabled;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enabled)
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
