package com.agisoft.marklogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class MarklogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarklogicApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                             .name("If-Non-Match")
                                             .description("ETAG")
                                             .modelRef(new ModelRef("string"))
                                             .parameterType("header")
                                             .required(false)
                                             .build()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())

                .build();
    }
}
