package com.amalitech.smartEcommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart E-Commerce API")
                        .version("v0.0.1")
                        .description("API documentation for Smart E-Commerce System")
                        .contact(new Contact().name("Smart E-Commerce Team").email("devteam@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}
