package com.pedidos.appsync.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DatabaseConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/batchdb");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("");
        log.info("---conectado a: " + driverManagerDataSource.getUrl());
        return driverManagerDataSource;
    }
}
