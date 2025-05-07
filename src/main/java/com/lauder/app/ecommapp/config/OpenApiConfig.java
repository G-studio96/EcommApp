package com.lauder.app.ecommapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

        @Bean
    public OpenAPI LauderDatabaseOpenApi() {
            return new OpenAPI().info(new Info().title("eCommence REST API").description("Online eCommence Platform").version("1.0"));

        }
}
