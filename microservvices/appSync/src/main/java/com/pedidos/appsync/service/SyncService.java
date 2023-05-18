package com.pedidos.appsync.service;

import com.pedidos.appsync.model.Order;

import java.util.List;

public interface SyncService {
    List<Order> getOrders();
}
