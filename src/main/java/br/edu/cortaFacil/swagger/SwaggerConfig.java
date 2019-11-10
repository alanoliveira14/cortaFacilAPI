package br.edu.cortaFacil.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketAPI(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .groupName("Corta-Facil")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.edu.cortaFacil.controllers"))
                .paths(PathSelectors.any())
                .build();

    }


    private ApiInfo getApiInfo(){

        return new ApiInfoBuilder()
                .title("Corta Facil")
                .version("1.0.0")
                .build();

    }

}
