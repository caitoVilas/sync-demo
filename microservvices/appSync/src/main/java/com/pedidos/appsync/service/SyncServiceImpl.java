package com.pedidos.appsync.service;

import com.pedidos.appsync.config.OrderFeingClient;
import com.pedidos.appsync.enums.OrderStatus;
import com.pedidos.appsync.exception.OrdersEmptyException;
import com.pedidos.appsync.model.Order;
import com.pedidos.appsync.model.WorkOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SyncServiceImpl implements SyncService {
    private final RestTemplate restTemplate;
    private final OrderFeingClient feingClient;
    @Value("${url.pedidos}")
    private String urlPedidos;

    @Override
    public List<Order> getOrders() {
        log.info("incio servicio obtener pedidos");
        try {
            log.info("llamando servicio externo obtener pedidos: {}", urlPedidos);
            List<Order> orders = feingClient.getOrders();

            if (orders.isEmpty()){
                log.error("no se encontraron ordenes para proceasr");
                throw new OrdersEmptyException("no se encontraron ordenes para proceasr");
            }
            List<WorkOrder> workOrders = this.procesWorkOrders(orders);
            log.info("construida lista de ordenes de trabajo");
            List<Order> updatedOrders  = this.updateStatus(orders);
            log.info("pedidos actualizados");

            return orders;
        }catch (ResourceAccessException e){
            log.error("el servicio de pedidos no responde");
        }

        return null;
    }

    private List<WorkOrder> procesWorkOrders(List<Order> orders){
        log.info("procesando pedidos obtenidos...");
        List<WorkOrder> workOrders = new ArrayList<>();
        log.info("construyendo ordenes de trabajo...");
        orders.forEach(order -> {
            WorkOrder workOrder = WorkOrder.builder()
                    .description(UUID.randomUUID().toString())
                    .date(LocalDateTime.now())
                    .order_id(order.getId())
                    .build();
            workOrders.add(workOrder);
        });

        return workOrders;
    }

    private List<Order> updateStatus(List<Order> orders){
        log.info("actualizando estando pedidos...");
        return  orders.stream().map(order -> {
            order.setStatus(OrderStatus.SYNC_COMPLETE);
            return order;
        }).collect(Collectors.toList());

    }
}
