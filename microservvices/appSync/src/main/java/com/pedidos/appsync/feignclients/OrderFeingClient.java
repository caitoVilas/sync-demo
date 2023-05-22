package com.pedidos.appsync.feignclients;

import com.pedidos.appsync.model.Order;
import com.pedidos.appsync.model.OrderList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pedidos-service", url = "${url.pedidos}")
public interface OrderFeingClient {
    @GetMapping
    List<Order> getOrders();

    @PostMapping("/update-status")
    void updateOrders(@RequestBody Order order);

    @PostMapping("/update")
   List<Order> updateStatus(@RequestBody OrderList orders);
}
