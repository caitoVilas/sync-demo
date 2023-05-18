package com.pedidos.appsync.config;

import com.pedidos.appsync.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(name = "ORDER-FEING-CLIENT", url = "${url.pedidos}")
public interface OrderFeingClient {

    @GetMapping
    List<Order> getOrders();
}
