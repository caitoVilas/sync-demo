package com.pedidos.workorder.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("work-order")
                .packagesToScan("com.pedidos.workorder")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Servicio ordenes de trabajo")
                        .description("servicio ordenes de trabajo")
                        .version("1.0"));
    }
}
