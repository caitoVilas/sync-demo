package com.pedidos.appsync.config;

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
                .group("sync")
                .packagesToScan("com.pedidos.appsync")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Syncronizacion de pedidos")
                        .description("servicio de sincronizacion de pedidos")
                        .version("1.0"));
    }
}
