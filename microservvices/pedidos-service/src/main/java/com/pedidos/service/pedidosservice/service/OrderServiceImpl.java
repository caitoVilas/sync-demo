package com.pedidos.service.pedidosservice.service;

import com.pedidos.service.pedidosservice.entity.Order;
import com.pedidos.service.pedidosservice.enums.OrderStatus;
import com.pedidos.service.pedidosservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;


    @Override
    public List<Order> getOrders() {
        log.info("inicio servicio buscar pedidos");
        return orderRepository.findByStatus();
    }

    @Override
    public void saveOrders() {
        List<Order> orders = new ArrayList<>();
        for (int o = 0; o < 1000; o++){
            Order order = Order.builder()
                    .identifier(UUID.randomUUID().toString())
                    .date(LocalDateTime.now())
                    .status(OrderStatus.READY)
                    .build();
            orders.add(order);
        }
        orderRepository.saveAll(orders);

    }
}
