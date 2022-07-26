package com.snail.abell;

import com.snail.abell.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Abell
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = "com.snail.abell")
@EnableOpenApi
@MapperScan({"com.snail.abell.**.dao","com.snail.abell.**.dto"})
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
