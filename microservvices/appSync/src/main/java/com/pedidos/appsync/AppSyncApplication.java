package com.pedidos.appsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSyncApplication.class, args);
    }

}
