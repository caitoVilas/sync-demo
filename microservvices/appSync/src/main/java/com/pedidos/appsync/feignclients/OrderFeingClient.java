package com.pedidos.appsync.feignclients;

import com.pedidos.appsync.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "pedidos-service", url = "${url.pedidos}")
public interface OrderFeingClient {
    @GetMapping
    List<Order> getOrders();
}
