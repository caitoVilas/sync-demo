package com.pedidos.service.pedidosservice.service;

import com.pedidos.service.pedidosservice.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    void saveOrders();
}
