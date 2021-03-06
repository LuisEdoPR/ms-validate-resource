package com.waes.demo.infrastructure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI(
      @Value("${info.project.version}") String appVersion,
      @Value("${info.project.title}") String title,
      @Value("${info.project.description}") String description) {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .version(appVersion)
            .title(title)
            .description(description)
            .termsOfService("http://swagger.io/terms/")
            .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
  }

}